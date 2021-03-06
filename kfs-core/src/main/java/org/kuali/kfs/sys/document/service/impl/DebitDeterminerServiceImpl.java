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
package org.kuali.kfs.sys.document.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.kfs.gl.service.impl.StringHelper;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail;
import org.kuali.kfs.sys.businessobject.SystemOptions;
import org.kuali.kfs.sys.document.AccountingDocument;
import org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource;
import org.kuali.kfs.sys.document.service.AccountingDocumentRuleHelperService;
import org.kuali.kfs.sys.document.service.DebitDeterminerService;
import org.kuali.kfs.sys.service.OptionsService;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * Default implementation of the DebitDeterminerService.
 */
public class DebitDeterminerServiceImpl implements DebitDeterminerService {
    private static Logger LOG = Logger.getLogger(DebitDeterminerServiceImpl.class);
    protected static final String isDebitCalculationIllegalStateExceptionMessage = "an invalid debit/credit check state was detected";
    protected static final String isErrorCorrectionIllegalStateExceptionMessage = "invalid (error correction) document not allowed";
    protected static final String isInvalidLineTypeIllegalArgumentExceptionMessage = "invalid accounting line type";

    private AccountingDocumentRuleHelperService accountingDocumentRuleUtil;
    private OptionsService optionsService;

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#disallowErrorCorrectionDocumentCheck(org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource)
     */
    @Override
    public void disallowErrorCorrectionDocumentCheck(GeneralLedgerPendingEntrySource poster) {
        LOG.debug("disallowErrorCorrectionDocumentCheck(AccountingDocumentRuleBase, AccountingDocument) - start");

        if (isErrorCorrection(poster)) {
            throw new IllegalStateException(isErrorCorrectionIllegalStateExceptionMessage);
        }

        LOG.debug("disallowErrorCorrectionDocumentCheck(AccountingDocumentRuleBase, AccountingDocument) - end");
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isAsset(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isAsset(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isAsset(AccountingLine) - start");

        boolean returnboolean = isAssetTypeCode(accountingDocumentRuleUtil.getObjectCodeTypeCodeWithoutSideEffects(postable));
        LOG.debug("isAsset(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isAssetTypeCode(java.lang.String)
     */
    @Override
    public boolean isAssetTypeCode(String objectTypeCode) {
        LOG.debug("isAssetTypeCode(String) - start");

        boolean returnboolean = optionsService.getCurrentYearOptions().getFinancialObjectTypeAssetsCd().equals(objectTypeCode);
        LOG.debug("isAssetTypeCode(String) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitCode(java.lang.String)
     */
    @Override
    public boolean isDebitCode(String debitCreditCode) {
        LOG.debug("isDebitCode(String) - start");

        boolean returnboolean = StringUtils.equals(KFSConstants.GL_DEBIT_CODE, debitCreditCode);
        LOG.debug("isDebitCode(String) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitConsideringNothingPositiveOnly(org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource, org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isDebitConsideringNothingPositiveOnly(GeneralLedgerPendingEntrySource poster, GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isDebitConsideringNothingPositiveOnly(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - start");

        boolean isDebit = isDebitConsideringNothingPositiveOrNegative(poster,postable);

        // non error correction document with non positive amount
        if (!isErrorCorrection(poster) && !isDebit) {
            throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
        }

        LOG.debug("isDebitConsideringNothingPositiveOnly(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - end");
        return isDebit;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitConsideringNothingPositiveOrNegative(org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource, org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isDebitConsideringNothingPositiveOrNegative(GeneralLedgerPendingEntrySource poster, GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isDebitConsideringNothingPositiveOrNegative(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - start");

        boolean isDebit = false;
        KualiDecimal amount = postable.getAmount();
        boolean isPositiveAmount = amount.isPositive();
        // isDebit if income/liability/expense/asset and line amount is positive
        if (isPositiveAmount && (isIncomeOrLiability(postable) || isExpenseOrAsset(postable))) {
            isDebit = true;
        }
        else {
          isDebit = false;
        }

        LOG.debug("isDebitConsideringNothingPositiveOnly(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - end");
        return isDebit;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitConsideringSection(org.kuali.kfs.sys.document.AccountingDocument, org.kuali.kfs.sys.businessobject.AccountingLine)
     */
    @Override
    public boolean isDebitConsideringSection(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        LOG.debug("isDebitConsideringSection(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - start");

        KualiDecimal amount = accountingLine.getAmount();
        // zero amounts are not allowed
        if (amount.isZero()) {
            throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
        }
        boolean isDebit = false;
        boolean isPositiveAmount = accountingLine.getAmount().isPositive();
        // source line
        if (accountingLine.isSourceAccountingLine()) {
            // income/liability/expense/asset
            if (isIncomeOrLiability(accountingLine) || isExpenseOrAsset(accountingLine)) {
                isDebit = !isPositiveAmount;
            }
            else {
                throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
            }
        }
        // target line
        else {
            if (accountingLine.isTargetAccountingLine()) {
                if (isIncomeOrLiability(accountingLine) || isExpenseOrAsset(accountingLine)) {
                    isDebit = isPositiveAmount;
                }
                else {
                    throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
                }
            }
            else {
                throw new IllegalArgumentException(isInvalidLineTypeIllegalArgumentExceptionMessage);
            }
        }

        LOG.debug("isDebitConsideringSection(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - end");
        return isDebit;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitConsideringSectionAndTypePositiveOnly(org.kuali.kfs.sys.document.AccountingDocument, org.kuali.kfs.sys.businessobject.AccountingLine)
     */
    @Override
    public boolean isDebitConsideringSectionAndTypePositiveOnly(AccountingDocument accountingDocument, AccountingLine accountingLine) {
        LOG.debug("isDebitConsideringSectionAndTypePositiveOnly(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - start");

        boolean isDebit = false;
        KualiDecimal amount = accountingLine.getAmount();
        boolean isPositiveAmount = amount.isPositive();
        // non error correction - only allow amount >0
        if (!isPositiveAmount && !isErrorCorrection(accountingDocument)) {
            throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
        }
        // source line
        if (accountingLine.isSourceAccountingLine()) {
            // could write below block in one line using == as XNOR operator, but that's confusing to read:
            // isDebit = (rule.isIncomeOrLiability(accountingLine) == isPositiveAmount);
            if (isPositiveAmount) {
                isDebit = isIncomeOrLiability(accountingLine);
            }
            else {
                isDebit = isExpenseOrAsset(accountingLine);
            }
        }
        // target line
        else {
            if (accountingLine.isTargetAccountingLine()) {
                if (isPositiveAmount) {
                    isDebit = isExpenseOrAsset(accountingLine);
                }
                else {
                    isDebit = isIncomeOrLiability(accountingLine);
                }
            }
            else {
                throw new IllegalArgumentException(isInvalidLineTypeIllegalArgumentExceptionMessage);
            }
        }

        LOG.debug("isDebitConsideringSectionAndTypePositiveOnly(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - end");
        return isDebit;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isDebitConsideringType(org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource, org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isDebitConsideringType(GeneralLedgerPendingEntrySource poster, GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isDebitConsideringType(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - start");

        KualiDecimal amount = postable.getAmount();
        // zero amounts are not allowed
        if (amount.isZero()) {
            throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
        }
        boolean isDebit = false;
        boolean isPositiveAmount = postable.getAmount().isPositive();

        // income/liability
        if (isIncomeOrLiability(postable)) {
            isDebit = !isPositiveAmount;
        }
        // expense/asset
        else {
            if (isExpenseOrAsset(postable)) {
                isDebit = isPositiveAmount;
            }
            else {
                throw new IllegalStateException(isDebitCalculationIllegalStateExceptionMessage);
            }
        }

        LOG.debug("isDebitConsideringType(AccountingDocumentRuleBase, AccountingDocument, AccountingLine) - end");
        return isDebit;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isErrorCorrection(org.kuali.kfs.sys.document.GeneralLedgerPendingEntrySource)
     */
    @Override
    public boolean isErrorCorrection(GeneralLedgerPendingEntrySource poster) {
        return StringUtils.isNotBlank(poster.getFinancialSystemDocumentHeader().getFinancialDocumentInErrorNumber());
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isExpense(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isExpense(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isExpense(AccountingLine) - start");

        boolean returnboolean = accountingDocumentRuleUtil.isExpense(postable);
        LOG.debug("isExpense(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isExpenseOrAsset(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isExpenseOrAsset(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isExpenseOrAsset(AccountingLine) - start");

        boolean returnboolean = isAsset(postable) || isExpense(postable);
        LOG.debug("isExpenseOrAsset(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isIncome(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isIncome(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isIncome(AccountingLine) - start");

        boolean returnboolean = accountingDocumentRuleUtil.isIncome(postable);
        LOG.debug("isIncome(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isIncomeOrLiability(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isIncomeOrLiability(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isIncomeOrLiability(AccountingLine) - start");

        boolean returnboolean = isLiability(postable) || isIncome(postable);
        LOG.debug("isIncomeOrLiability(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isLiability(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
     */
    @Override
    public boolean isLiability(GeneralLedgerPendingEntrySourceDetail postable) {
        LOG.debug("isLiability(AccountingLine) - start");

        boolean returnboolean = isLiabilityTypeCode(accountingDocumentRuleUtil.getObjectCodeTypeCodeWithoutSideEffects(postable));
        LOG.debug("isLiability(AccountingLine) - end");
        return returnboolean;
    }

    /**
     * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isLiabilityTypeCode(java.lang.String)
     */
    @Override
    public boolean isLiabilityTypeCode(String objectTypeCode) {
        LOG.debug("isLiabilityTypeCode(String) - start");

        boolean returnboolean = optionsService.getCurrentYearOptions().getFinObjectTypeLiabilitiesCode().equals(objectTypeCode);
        LOG.debug("isLiabilityTypeCode(String) - end");
        return returnboolean;
    }

    @Override
    public String getConvertedAmount(String objectType , String debitCreditCode , String amount ) {
        SystemOptions systemOption = optionsService.getCurrentYearOptions();

        // If entries lack a debit and credit code that means they're budget entries and should already be signed appropriately + or -.
        // so we should just be able to grab those without having to do any change.
        if(StringHelper.isNullOrEmpty(debitCreditCode)) {
            return amount;
        }

        if(systemOption.getFinancialObjectTypeAssetsCd().equals(objectType)
                ||systemOption.getFinObjTypeExpendNotExpCode().equals(objectType)
                ||systemOption.getFinObjTypeExpenditureexpCd().equals(objectType)
                ||systemOption.getFinObjTypeExpendNotExpCode().equals(objectType)
                ||systemOption.getFinancialObjectTypeTransferExpenseCd().equals(objectType)) {

            if  (KFSConstants.GL_CREDIT_CODE.equals(debitCreditCode)) {
                amount = "-" + amount;
            }
        }
        else if (systemOption.getFinObjTypeCshNotIncomeCd().equals(objectType)
                || systemOption.getFinObjectTypeFundBalanceCd().equals(objectType)
                || systemOption.getFinObjectTypeIncomecashCode().equals(objectType)
                || systemOption.getFinObjTypeCshNotIncomeCd().equals(objectType)
                || systemOption.getFinObjectTypeLiabilitiesCode().equals(objectType)
                || systemOption.getFinancialObjectTypeTransferIncomeCd().equals(objectType)) {
            if (KFSConstants.GL_DEBIT_CODE.equals(debitCreditCode)) {
              amount = "-" + amount;
            }

        }

        return amount;
    }





/**
 * @see org.kuali.kfs.sys.document.service.DebitDeterminerService#isRevenue(org.kuali.kfs.sys.businessobject.GeneralLedgerPendingEntrySourceDetail)
 */
@Override
public boolean isRevenue(GeneralLedgerPendingEntrySourceDetail postable) {
    LOG.debug("isRevenue(AccountingLine) - start");

    boolean returnboolean = !isExpense(postable);
    LOG.debug("isRevenue(AccountingLine) - end");
    return returnboolean;
}

/**
 * Sets the accountingDocumentRuleUtils attribute value.
 * @param accountingDocumentRuleUtils The accountingDocumentRuleUtils to set.
 */
public void setAccountingDocumentRuleUtils(AccountingDocumentRuleHelperService accountingDocumentRuleUtil) {
    this.accountingDocumentRuleUtil = accountingDocumentRuleUtil;
}

/**
 * Sets the optionsService attribute value.
 * @param optionsService The optionsService to set.
 */
public void setOptionsService(OptionsService optionsService) {
    this.optionsService = optionsService;
}

/**
 * Gets the isDebitCalculationIllegalStateExceptionMessage attribute.
 * @return Returns the isDebitCalculationIllegalStateExceptionMessage.
 */
@Override
public String getDebitCalculationIllegalStateExceptionMessage() {
    return isDebitCalculationIllegalStateExceptionMessage;
}

/**
 * Gets the isErrorCorrectionIllegalStateExceptionMessage attribute.
 * @return Returns the isErrorCorrectionIllegalStateExceptionMessage.
 */
@Override
public String getErrorCorrectionIllegalStateExceptionMessage() {
    return isErrorCorrectionIllegalStateExceptionMessage;
}

/**
 * Gets the isInvalidLineTypeIllegalArgumentExceptionMessage attribute.
 * @return Returns the isInvalidLineTypeIllegalArgumentExceptionMessage.
 */
@Override
public String getInvalidLineTypeIllegalArgumentExceptionMessage() {
    return isInvalidLineTypeIllegalArgumentExceptionMessage;
}


}
