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
package org.kuali.kfs.module.tem.businessobject;

import java.sql.Date;

import org.kuali.kfs.sys.businessobject.PaymentDocumentationLocation;
import org.kuali.kfs.sys.businessobject.options.PaymentDocumentationLocationValuesFinder;
import org.kuali.kfs.sys.businessobject.options.PaymentMethodValuesFinder;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;

/**
 * BusinessObject which holds fields representing those a travel document needs to make a payment
 */
public class TravelPayment extends PersistableBusinessObjectBase {
    private String documentNumber;
    private KualiDecimal checkTotalAmount;  //editable on TA, read only on TR, RELO, ENT
    private boolean attachmentCode;
    private boolean specialHandlingCode;
    private String checkStubText;
    protected String documentationLocationCode;
    protected Date dueDate;
    protected String paymentMethodCode;
    private boolean immediatePaymentIndicator;
    private Date extractDate;
    private Date paidDate;
    private Date cancelDate;
    private boolean alienPaymentCode;
    private String specialHandlingPersonName;
    private String specialHandlingLine1Addr;
    private String specialHandlingLine2Addr;
    private String specialHandlingCityName;
    private String specialHandlingStateCode;
    private String specialHandlingZipCode;
    private String specialHandlingCountryCode;
    protected boolean editW9W8BENbox; // do we need this?
    protected boolean payeeW9CompleteCode;
    private String payeeTypeCode;
    protected boolean exceptionAttachedIndicator;

    private PaymentDocumentationLocation paymentDocumentationLocation;

    /**
     * @return the document number of the document this travel payment is associated with
     */
    public String getDocumentNumber() {
        return this.documentNumber;
    }

    /**
     * Sets the document number of the document this travel payment is associated with
     * @param documentNumber the document number of the associated travel document
     */
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    /**
     * @return the total payment of the check generated by the associated document
     */
    public KualiDecimal getCheckTotalAmount() {
        return checkTotalAmount;
    }

    /**
     * Sets the total payment of the check generated by the associated document
     * @param checkTotalAmount a check amount
     */
    public void setCheckTotalAmount(KualiDecimal disbVchrCheckTotalAmount) {
        this.checkTotalAmount = disbVchrCheckTotalAmount;
    }

    /**
     * @return true if there is a check attachment, false otherwise
     */
    public boolean isAttachmentCode() {
        return attachmentCode;
    }

    /**
     * Sets whether this travel payment has a check attachment
     * @param attachmentCode set to true to mean that there is an attachment with this check; defaults to false
     */
    public void setAttachmentCode(boolean disbVchrAttachmentCode) {
        this.attachmentCode = disbVchrAttachmentCode;
    }

    /**
     * @return whether this payment includes instructions for special handling
     */
    public boolean isSpecialHandlingCode() {
        return specialHandlingCode;
    }

    /**
     * Sets whether this payment includes instructions for special handling
     * @param specialHandlingCode true if payment includes instructions for special handling; defaults to false
     */
    public void setSpecialHandlingCode(boolean disbVchrSpecialHandlingCode) {
        this.specialHandlingCode = disbVchrSpecialHandlingCode;
    }

    /**
     * @return the text to write on the check
     */
    public String getCheckStubText() {
        return checkStubText;
    }

    /**
     * Sets the text to write on the check
     * @param checkStubText the text to write on the check
     */
    public void setCheckStubText(String disbVchrCheckStubText) {
        this.checkStubText = disbVchrCheckStubText;
    }

    /**
     * @return whether this payment should be made ASAP once the document is approved
     */
    public boolean isImmediatePaymentIndicator() {
        return immediatePaymentIndicator;
    }

    /**
     * Set if the payment should be made ASAP once the associated document is approved
     * @param immediatePaymentIndicator true if this payment should be made ASAP once the document is approved; defaults to false
     */
    public void setImmediatePaymentIndicator(boolean immediatePaymentIndicator) {
        this.immediatePaymentIndicator = immediatePaymentIndicator;
    }

    /**
     * @return the date the payment associated with this document was extracted by PDP
     */
    public Date getExtractDate() {
        return extractDate;
    }

    /**
     * Sets the date the document associated with this payment was extracted by PDP
     * @param extractDate the date the document associated with this payment was extracted by PDP
     */
    public void setExtractDate(Date extractDate) {
        this.extractDate = extractDate;
    }

    /**
     * @return the date the document associated with this payment was paid in PDP
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     * Sets the date the document associated with this payment was paid in PDP
     * @param paidDate the date the document associated with this payment was paid in PDP
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * @return the date the document associated with this payment was canceled in PDP
     */
    public Date getCancelDate() {
        return cancelDate;
    }

    /**
     * Sets the date the document associated with this payment was canceled in PDP
     * @param cancelDate the date the document associated with this payment was canceled in PDP
     */
    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    /**
     * @return whether this payment is being made to a non-citizen
     */
    public boolean isAlienPaymentCode() {
        return alienPaymentCode;
    }

    /**
     * Sets whether this payment is being made to a non-citizen
     * @param alienPaymentCode true if this payment is being made to a non-citizen; defaults to false
     */
    public void setAlienPaymentCode(boolean disbVchrAlienPaymentCode) {
        this.alienPaymentCode = disbVchrAlienPaymentCode;
    }

    /**
     * @return the name to send to for special handling
     */
    public String getSpecialHandlingPersonName() {
        return specialHandlingPersonName;
    }

    /**
     * Sets the name to send to for special handling
     * @param specialHandlingPersonName the name to send to for special handling
     */
    public void setSpecialHandlingPersonName(String disbVchrSpecialHandlingPersonName) {
        this.specialHandlingPersonName = disbVchrSpecialHandlingPersonName;
    }

    /**
     * @return the first line of address to send to for special handling
     */
    public String getSpecialHandlingLine1Addr() {
        return specialHandlingLine1Addr;
    }

    /**
     * Sets the first line of address to send to for special handling
     * @param specialHandlingLine1Addr the first line of address to send to for special handling
     */
    public void setSpecialHandlingLine1Addr(String disbVchrSpecialHandlingLine1Addr) {
        this.specialHandlingLine1Addr = disbVchrSpecialHandlingLine1Addr;
    }

    /**
     * @return the second line of address to send to for special handling
     */
    public String getSpecialHandlingLine2Addr() {
        return specialHandlingLine2Addr;
    }

    /**
     * Sets the second line of address to send to for special handling
     * @param specialHandlingLine2Addr the second line of address to send to for special handling
     */
    public void setSpecialHandlingLine2Addr(String disbVchrSpecialHandlingLine2Addr) {
        this.specialHandlingLine2Addr = disbVchrSpecialHandlingLine2Addr;
    }

    /**
     * @return the name of the city to send to for special handling
     */
    public String getSpecialHandlingCityName() {
        return specialHandlingCityName;
    }

    /**
     * Sets the name of the city to send to for special handling
     * @param specialHandlingCityName the name of the city to send to for special handling
     */
    public void setSpecialHandlingCityName(String disbVchrSpecialHandlingCityName) {
        this.specialHandlingCityName = disbVchrSpecialHandlingCityName;
    }

    /**
     * @return the postal abbreviation of the state name to send to for special handling
     */
    public String getSpecialHandlingStateCode() {
        return specialHandlingStateCode;
    }

    /**
     * Sets the postal abbreviation of the state name to send to for special handling
     * @param specialHandlingStateCode the postal abbreviation of the state name to send to for special handling
     */
    public void setSpecialHandlingStateCode(String disbVchrSpecialHandlingStateCode) {
        this.specialHandlingStateCode = disbVchrSpecialHandlingStateCode;
    }

    /**
     * @return the postal code to send to for special handling
     */
    public String getSpecialHandlingZipCode() {
        return specialHandlingZipCode;
    }

    /**
     * Sets the postal code to send to for special handling
     * @param specialHandlingZipCode  the postal code to send to for special handling
     */
    public void setSpecialHandlingZipCode(String disbVchrSpecialHandlingZipCode) {
        this.specialHandlingZipCode = disbVchrSpecialHandlingZipCode;
    }

    /**
     * @return the country code to send to for special handling
     */
    public String getSpecialHandlingCountryCode() {
        return specialHandlingCountryCode;
    }

    /**
     * Sets the country code to send to for special handling
     * @param specialHandlingCountryCode the country code to send to for special handling
     */
    public void setSpecialHandlingCountryCode(String disbVchrSpecialHandlingCountryCode) {
        this.specialHandlingCountryCode = disbVchrSpecialHandlingCountryCode;
    }

    /**
     * @return the location of documentation justifying this payment
     */
    public String getDocumentationLocationCode() {
        return documentationLocationCode;
    }

    /**
     * Sets the location of documentation justifying this payment
     * @param documentationLocationCode the location of documentation justifying this payment
     */
    public void setDocumentationLocationCode(String disbursementVoucherDocumentationLocationCode) {
        this.documentationLocationCode = disbursementVoucherDocumentationLocationCode;
    }

    /**
     * @return the date when this payment should ideally be made
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the ideal date for this payment
     * @param dueDate the ideal date for this payment to be complete by
     */
    public void setDueDate(Date disbursementVoucherDueDate) {
        this.dueDate = disbursementVoucherDueDate;
    }

    /**
     * @return the method of payment - check/ACH, wire transfer, or foreign draft
     */
    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    /**
     * Sets the code for the method of payment
     * @param paymentMethodCode the code for the method of payment
     */
    public void setPaymentMethodCode(String disbVchrPaymentMethodCode) {
        this.paymentMethodCode = disbVchrPaymentMethodCode;
    }

    /**
     * @return the full record of the location where documentation justifying this payment exists
     */
    public PaymentDocumentationLocation getPaymentDocumentationLocation() {
        return paymentDocumentationLocation;
    }

    /**
     * This method is really for the ORM to call.  Really, developers should just call refresh.
     * @param paymentDocumentationLocation don't send a param and avoid calling this method
     */
    public void setPaymentDocumentationLocation(PaymentDocumentationLocation paymentDocumentationLocation) {
        this.paymentDocumentationLocation = paymentDocumentationLocation;
    }

    /**
     * Returns the name associated with the payment method code
     *
     * @return String the name associated with the payment method code
     */
    public String getPaymentMethodName() {
        return new PaymentMethodValuesFinder().getKeyLabel(this.paymentMethodCode);
    }

    /**
     * Does not set a name of a payment method.  The real name is going to come from a standard location.
     * @param paymentMethodName a payment method name which will be diligently ignored
     */
    public void setPaymentMethodName(String paymentMethodName) {
        // I'm just here to make bean utils happy, not to actually set values.
    }

    /**
     * Returns the name associated with the documentation location name
     *
     * @return String the name associated with the documentation location name
     */
    public String getPaymentDocumentationLocationName() {
        return new PaymentDocumentationLocationValuesFinder().getKeyLabel(this.documentationLocationCode);
    }

    /**
     * Fails to set the documentation location name
     * @param paymentDocumentationLocationName a name of a documentation location which will be blithely tossed away
     */
    public void setPaymentDocumentationLocationName(String paymentDocumentationLocationName) {
        // this setter is here just for the sake of bean utils
    }

    /**
     * Based on which pdp dates are present (extract, paid, canceled), determines a String for the status
     * @return a String representation of the status
     */
    public String getPaymentPdpStatus() {
        if (cancelDate != null) {
            return "Canceled";
        }
        if (paidDate != null) {
            return "Paid";
        }
        if (extractDate != null) {
            return "Extracted";
        }
        return "Pre-Extraction";
    }

    /**
     * Pretends to set the PDP status for this document
     *
     * @param status the status to pretend to set
     */
    public void setPaymentPdpStatus(String status) {
        // don't do nothing, 'cause this ain't a real field
    }

    /**
     * @return
     */
    public boolean isEditW9W8BENbox() {
        return editW9W8BENbox;
    }

    /**
     *
     * @param editW9W8BENbox
     */
    public void setEditW9W8BENbox(boolean editW9W8BENbox) {
        this.editW9W8BENbox = editW9W8BENbox;
    }

    /**
     * @return whether the payee's W9 happy fun time form is complete
     */
    public boolean isPayeeW9CompleteCode() {
        return payeeW9CompleteCode;
    }

    /**
     * Sets whether the payee's W9 tax form has been completed
     * @param payeeW9CompleteCode true if the payee's/traveler's W9 was completed; defaults to false
     */
    public void setPayeeW9CompleteCode(boolean payeeW9CompleteCode) {
        this.payeeW9CompleteCode = payeeW9CompleteCode;
    }

    /**
     * @return the code representing the type of the payee - either E for employee, V for Vendor, or C for Customer
     */
    public String getPayeeTypeCode() {
        return payeeTypeCode;
    }

    /**
     * Sets the type code for the payee
     * @param payeeTypeCode the type code for the payee
     */
    public void setPayeeTypeCode(String payeeTypeCode) {
        this.payeeTypeCode = payeeTypeCode;
    }

    /**
     * @return true if an exception is attached to this payment, false otherwise
     */
    public boolean isExceptionAttachedIndicator() {
        return exceptionAttachedIndicator;
    }

    /**
     * Sets whether this payment has had an exception attached to it or not
     * @param exceptionAttachedIndicator true if the payment has had an exception attached to it; defaults to false
     */
    public void setExceptionAttachedIndicator(boolean exceptionAttachedIndicator) {
        this.exceptionAttachedIndicator = exceptionAttachedIndicator;
    }
}
