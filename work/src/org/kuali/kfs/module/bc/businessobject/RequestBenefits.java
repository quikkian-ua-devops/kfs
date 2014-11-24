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
package org.kuali.kfs.module.bc.businessobject;

import java.util.LinkedHashMap;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.api.util.type.KualiInteger;
import org.kuali.rice.core.api.util.type.KualiPercent;
import org.kuali.rice.krad.bo.TransientBusinessObjectBase;

/**
 * Holds the single line benefits impact screen detail information for a particular request amount
 */
public class RequestBenefits extends TransientBusinessObjectBase {
    private Integer universityFiscalYear;
    private String chartOfAccountsCode;
    private String financialObjectCode;
    private String financialObjectBenefitsTypeCode;
    private String financialObjectBenefitsTypeDescription;
    private KualiPercent positionFringeBenefitPercent;
    private String positionFringeBenefitObjectCode;
    private String positionFringeBenefitObjectCodeName;
    private KualiInteger fringeDetailAmount;
    private KualiInteger accountLineAnnualBalanceAmount;
    private String laborBenefitRateCategoryCode;
    private String laborBenefitRateCategoryCodeDesc;
    private String accountNumber;

    /**
     * Constructs a RequestBenefits.java.
     */
    public RequestBenefits() {
        super();
    }

    /**
     * Gets the universityFiscalYear attribute. 
     * @return Returns the universityFiscalYear.
     */
    public Integer getUniversityFiscalYear() {
        return universityFiscalYear;
    }

    /**
     * Sets the universityFiscalYear attribute value.
     * @param universityFiscalYear The universityFiscalYear to set.
     */
    public void setUniversityFiscalYear(Integer universityFiscalYear) {
        this.universityFiscalYear = universityFiscalYear;
    }

    /**
     * Gets the chartOfAccountsCode attribute. 
     * @return Returns the chartOfAccountsCode.
     */
    public String getChartOfAccountsCode() {
        return chartOfAccountsCode;
    }

    /**
     * Sets the chartOfAccountsCode attribute value.
     * @param chartOfAccountsCode The chartOfAccountsCode to set.
     */
    public void setChartOfAccountsCode(String chartOfAccountsCode) {
        this.chartOfAccountsCode = chartOfAccountsCode;
    }

    /**
     * Gets the financialObjectCode attribute. 
     * @return Returns the financialObjectCode.
     */
    public String getFinancialObjectCode() {
        return financialObjectCode;
    }

    /**
     * Sets the financialObjectCode attribute value.
     * @param financialObjectCode The financialObjectCode to set.
     */
    public void setFinancialObjectCode(String financialObjectCode) {
        this.financialObjectCode = financialObjectCode;
    }

    /**
     * Gets the financialObjectBenefitsTypeCode attribute. 
     * @return Returns the financialObjectBenefitsTypeCode.
     */
    public String getFinancialObjectBenefitsTypeCode() {
        return financialObjectBenefitsTypeCode;
    }

    /**
     * Sets the financialObjectBenefitsTypeCode attribute value.
     * @param financialObjectBenefitsTypeCode The financialObjectBenefitsTypeCode to set.
     */
    public void setFinancialObjectBenefitsTypeCode(String financialObjectBenefitsTypeCode) {
        this.financialObjectBenefitsTypeCode = financialObjectBenefitsTypeCode;
    }

    /**
     * Gets the financialObjectBenefitsTypeDescription attribute. 
     * @return Returns the financialObjectBenefitsTypeDescription.
     */
    public String getFinancialObjectBenefitsTypeDescription() {
        return financialObjectBenefitsTypeDescription;
    }

    /**
     * Sets the financialObjectBenefitsTypeDescription attribute value.
     * @param financialObjectBenefitsTypeDescription The financialObjectBenefitsTypeDescription to set.
     */
    public void setFinancialObjectBenefitsTypeDescription(String financialObjectBenefitsTypeDescription) {
        this.financialObjectBenefitsTypeDescription = financialObjectBenefitsTypeDescription;
    }

    /**
     * Gets the positionFringeBenefitPercent attribute. 
     * @return Returns the positionFringeBenefitPercent.
     */
    public KualiPercent getPositionFringeBenefitPercent() {
        return positionFringeBenefitPercent;
    }

    /**
     * Sets the positionFringeBenefitPercent attribute value.
     * @param positionFringeBenefitPercent The positionFringeBenefitPercent to set.
     */
    public void setPositionFringeBenefitPercent(KualiPercent positionFringeBenefitPercent) {
        this.positionFringeBenefitPercent = positionFringeBenefitPercent;
    }

    /**
     * Gets the positionFringeBenefitObjectCode attribute. 
     * @return Returns the positionFringeBenefitObjectCode.
     */
    public String getPositionFringeBenefitObjectCode() {
        return positionFringeBenefitObjectCode;
    }

    /**
     * Sets the positionFringeBenefitObjectCode attribute value.
     * @param positionFringeBenefitObjectCode The positionFringeBenefitObjectCode to set.
     */
    public void setPositionFringeBenefitObjectCode(String positionFringeBenefitObjectCode) {
        this.positionFringeBenefitObjectCode = positionFringeBenefitObjectCode;
    }

    /**
     * Gets the positionFringeBenefitObjectCodeName attribute. 
     * @return Returns the positionFringeBenefitObjectCodeName.
     */
    public String getPositionFringeBenefitObjectCodeName() {
        return positionFringeBenefitObjectCodeName;
    }

    /**
     * Sets the positionFringeBenefitObjectCodeName attribute value.
     * @param positionFringeBenefitObjectCodeName The positionFringeBenefitObjectCodeName to set.
     */
    public void setPositionFringeBenefitObjectCodeName(String positionFringeBenefitObjectCodeName) {
        this.positionFringeBenefitObjectCodeName = positionFringeBenefitObjectCodeName;
    }

    /**
     * Gets the fringeDetailAmount attribute. 
     * @return Returns the fringeDetailAmount.
     */
    public KualiInteger getFringeDetailAmount() {
        return fringeDetailAmount;
    }

    /**
     * Sets the fringeDetailAmount attribute value.
     * @param fringeDetailAmount The fringeDetailAmount to set.
     */
    public void setFringeDetailAmount(KualiInteger fringeDetailAmount) {
        this.fringeDetailAmount = fringeDetailAmount;
    }

    /**
     * Gets the accountLineAnnualBalanceAmount attribute. 
     * @return Returns the accountLineAnnualBalanceAmount.
     */
    public KualiInteger getAccountLineAnnualBalanceAmount() {
        return accountLineAnnualBalanceAmount;
    }

    /**
     * Sets the accountLineAnnualBalanceAmount attribute value.
     * @param accountLineAnnualBalanceAmount The accountLineAnnualBalanceAmount to set.
     */
    public void setAccountLineAnnualBalanceAmount(KualiInteger accountLineAnnualBalanceAmount) {
        this.accountLineAnnualBalanceAmount = accountLineAnnualBalanceAmount;
    }

    /**
     * Gets the laborBenefitRateCategoryCode attribute. 
     * @return Returns the laborBenefitRateCategoryCode.
     */
    public String getLaborBenefitRateCategoryCode() {
        return laborBenefitRateCategoryCode;
    }

    /**
     * Sets the laborBenefitRateCategoryCode attribute value.
     * @param laborBenefitRateCategoryCode The laborBenefitRateCategoryCode to set.
     */
    public void setLaborBenefitRateCategoryCode(String laborBenefitRateCategoryCode) {
        this.laborBenefitRateCategoryCode = laborBenefitRateCategoryCode;
    }

    /**
     * Gets the laborBenefitRateCategoryCodeDesc attribute. 
     * @return Returns the laborBenefitRateCategoryCodeDesc.
     */
    public String getLaborBenefitRateCategoryCodeDesc() {
        return laborBenefitRateCategoryCodeDesc;
    }

    /**
     * Sets the laborBenefitRateCategoryCodeDesc attribute value.
     * @param laborBenefitRateCategoryCodeDesc The laborBenefitRateCategoryCodeDesc to set.
     */
    public void setLaborBenefitRateCategoryCodeDesc(String laborBenefitRateCategoryCodeDesc) {
        this.laborBenefitRateCategoryCodeDesc = laborBenefitRateCategoryCodeDesc;
    }

    /**
     * Gets the accountNumber attribute. 
     * @return Returns the accountNumber.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the accountNumber attribute value.
     * @param accountNumber The accountNumber to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
