/*
 * The Kuali Financial System, a comprehensive financial management system for higher education.
 * 
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kfs.module.tem.document.web.struts;

import static org.kuali.kfs.module.tem.TemPropertyConstants.NEW_ACTUAL_EXPENSE_LINES;

import java.util.Observable;
import java.util.Observer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.businessobject.ActualExpense;
import org.kuali.kfs.module.tem.businessobject.ExpenseTypeObjectCode;
import org.kuali.kfs.module.tem.document.TravelDocument;
import org.kuali.kfs.module.tem.document.service.TravelDocumentService;
import org.kuali.kfs.module.tem.document.validation.event.AddActualExpenseDetailLineEvent;
import org.kuali.kfs.module.tem.document.web.bean.TravelMvcWrapperBean;
import org.kuali.kfs.module.tem.service.AccountingDistributionService;
import org.kuali.kfs.module.tem.service.TravelExpenseService;
import org.kuali.kfs.module.tem.util.ExpenseUtils;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.ObjectUtils;

public class AddActualExpenseDetailEvent implements Observer {

    public static Logger LOG = Logger.getLogger(AddActualExpenseDetailEvent.class);

    protected volatile TravelExpenseService travelExpenseService;

    private static final int WRAPPER_ARG_IDX       = 0;
    private static final int SELECTED_LINE_ARG_IDX = 1;

    @SuppressWarnings("null")
    @Override
    public void update(Observable arg0, Object arg1) {
        if (!(arg1 instanceof Object[])) {
            return;
        }
        final Object[] args = (Object[]) arg1;
        LOG.debug(args[WRAPPER_ARG_IDX]);
        if (!(args[WRAPPER_ARG_IDX] instanceof TravelMvcWrapperBean)) {
            return;
        }
        final TravelMvcWrapperBean wrapper = (TravelMvcWrapperBean) args[WRAPPER_ARG_IDX];

        final TravelDocument document = wrapper.getTravelDocument();
        final Integer index = (Integer) args[SELECTED_LINE_ARG_IDX];

        final ActualExpense newActualExpenseLine = wrapper.getNewActualExpenseLines().get(index);
        ActualExpense line = document.getActualExpenses().get(index);

        if(newActualExpenseLine != null){
            if (StringUtils.isBlank(newActualExpenseLine.getExpenseTypeCode())) {
                newActualExpenseLine.setExpenseTypeCode(line.getExpenseTypeCode());
            }

            newActualExpenseLine.refreshReferenceObject(TemPropertyConstants.EXPENSE_TYPE_OBJECT_CODE);
            if (ObjectUtils.isNull(newActualExpenseLine.getExpenseTypeObjectCode())) {
                if (StringUtils.isBlank(newActualExpenseLine.getExpenseTypeCode())) {
                    newActualExpenseLine.setExpenseTypeCode(line.getExpenseTypeCode());
                }
                if (!StringUtils.isBlank(document.getTripTypeCode()) && !ObjectUtils.isNull(document.getTraveler()) && !StringUtils.isBlank(document.getTraveler().getTravelerTypeCode())) {
                    final ExpenseTypeObjectCode expenseTypeObjectCode = SpringContext.getBean(TravelExpenseService.class).getExpenseType(newActualExpenseLine.getExpenseTypeCode(), document.getDocumentTypeName(), document.getTripTypeCode(), document.getTraveler().getTravelerTypeCode());
                    newActualExpenseLine.setExpenseTypeObjectCode(expenseTypeObjectCode);
                    newActualExpenseLine.setExpenseTypeObjectCodeId(expenseTypeObjectCode.getExpenseTypeObjectCodeId());
                }
            }
        }

        boolean rulePassed = true;

        // check any business rules
        rulePassed &= getRuleService().applyRules(new AddActualExpenseDetailLineEvent<ActualExpense>(NEW_ACTUAL_EXPENSE_LINES + "["+index + "]", document, line, newActualExpenseLine));

        if (rulePassed){
            if(newActualExpenseLine != null && line != null){
                newActualExpenseLine.setExpenseLineTypeCode(null);
                if (newActualExpenseLine.getExpenseTypeObjectCodeId() == null || newActualExpenseLine.getExpenseTypeObjectCodeId().equals(new Long(0L))) {
                    newActualExpenseLine.setExpenseTypeObjectCode(null); // there's no vaule but the jsp layer does not handle the null-wrapping proxy that well
                }
                document.addExpenseDetail(newActualExpenseLine, index);
                newActualExpenseLine.setExpenseDetails(null);
            }

            KualiDecimal detailTotal = line.getTotalDetailExpenseAmount();

            ActualExpense newExpense = getTravelExpenseService().createNewDetailForActualExpense(line);
            if (detailTotal.isLessEqual(line.getExpenseAmount())){
                KualiDecimal remainderExpense = line.getExpenseAmount().subtract(detailTotal);
                KualiDecimal remainderConverted = line.getConvertedAmount().subtract(new KualiDecimal(detailTotal.bigDecimalValue().multiply(line.getCurrencyRate())));
                newExpense.setExpenseAmount(remainderExpense);
                newExpense.setConvertedAmount(remainderConverted);
            }
            else{
                newExpense.setExpenseAmount(KualiDecimal.ZERO);
            }
            wrapper.getNewActualExpenseLines().add(index,newExpense);
            wrapper.getNewActualExpenseLines().remove(index+1);

            ExpenseUtils.calculateMileage(document, document.getActualExpenses());

            wrapper.setDistribution(getAccountingDistributionService().buildDistributionFrom(document));
        }

    }

    /**
     * Gets the travelReimbursementService attribute.
     *
     * @return Returns the travelReimbursementService.
     */
    protected TravelDocumentService getTravelDocumentService() {
        return SpringContext.getBean(TravelDocumentService.class);
    }

    protected KualiRuleService getRuleService() {
        return SpringContext.getBean(KualiRuleService.class);
    }

    protected AccountingDistributionService getAccountingDistributionService() {
        return SpringContext.getBean(AccountingDistributionService.class);
    }

    protected TravelExpenseService getTravelExpenseService() {
        if (travelExpenseService == null) {
            travelExpenseService = SpringContext.getBean(TravelExpenseService.class);
        }
        return travelExpenseService;
    }
}
