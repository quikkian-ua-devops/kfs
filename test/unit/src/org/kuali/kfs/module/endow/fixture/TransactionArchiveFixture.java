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
package org.kuali.kfs.module.endow.fixture;

import java.math.BigDecimal;
import java.sql.Date;

import org.kuali.kfs.module.endow.businessobject.TransactionArchive;
import org.kuali.kfs.module.endow.document.CashDecreaseDocument;
import org.kuali.kfs.module.endow.document.EndowmentTransactionalDocumentBase;
import org.kuali.kfs.sys.DocumentTestUtils;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public enum TransactionArchiveFixture {
    // Transaction Archive Fixture
    TRANSACTION_ARCHIVE_RECORD_1("TEST", //documentNumber
            new Integer(1), //lineNumber
            "F", //lineTypeCode
            "ECI", //typeCode...documentTypeName
            "C", //subTypeCode
            "M", //srcTypeCode
            "TESTKEMID", //kemid
            "TST123", // etranCode
            "I", //incomePrincipalIndicatorCode
            BigDecimal.valueOf(10.00), // principalCashAmount
            BigDecimal.valueOf(20.00), //incomeCashAmount
            false,  //corpusIndicator
            Date.valueOf("2010-12-16") // postedDate
    ),

    TRANSACTION_ARCHIVE_RECORD_2("TEST", //documentNumber
            new Integer(2), //lineNumber
            "F", //lineTypeCode
            "EAD", //typeCode...documentTypeName
            "C", //subTypeCode
            "M", //srcTypeCode
            "TESTKEMID", //kemid
            "TST124", // etranCode
            "I", //incomePrincipalIndicatorCode
            BigDecimal.valueOf(10.00), // principalCashAmount
            BigDecimal.valueOf(20.00), //incomeCashAmount
            false, //corpusIndicator
            Date.valueOf("2010-12-16") // postedDate            
    );

    public final String documentNumber;
    public final Integer lineNumber;
    public final String lineTypeCode;
    public final String typeCode;
    public final String subTypeCode;
    public final String srcTypeCode;
    public final String kemid;
    public final String etranCode;
    public final String incomePrincipalIndicatorCode;
    public final BigDecimal principalCashAmount;
    public final BigDecimal incomeCashAmount;
    public final boolean corpusIndicator;
    public final Date postedDate;
    
    // default record...
    private TransactionArchiveFixture(String documentNumber, Integer lineNumber, String lineTypeCode, String typeCode, String subTypeCode,
                                      String srcTypeCode, String kemid, String etranCode, String incomePrincipalIndicatorCode, 
                                      BigDecimal principalCashAmount, BigDecimal incomeCashAmount, boolean corpusIndicator, Date postedDate) {

        this.documentNumber = documentNumber;
        this.lineNumber = lineNumber;
        this.lineTypeCode = lineTypeCode;
        this.typeCode = typeCode;
        this.subTypeCode = subTypeCode;
        this.srcTypeCode = srcTypeCode;
        this.kemid = kemid;
        this.etranCode = etranCode;
        this.incomePrincipalIndicatorCode = incomePrincipalIndicatorCode;
        this.principalCashAmount = principalCashAmount;
        this.incomeCashAmount = incomeCashAmount;
        this.corpusIndicator = corpusIndicator;
        this.postedDate = postedDate;
    }

    /**
     * This method creates a default Fee Method record and saves it to table
     * 
     * @return transactionArchive record
     */
    public TransactionArchive createTransactionArchiveRecord() {
        EndowmentTransactionalDocumentBase endowmentTransactionDocument = null;

        DocumentService documentService = SpringContext.getBean(DocumentService.class);

        try {
            endowmentTransactionDocument = (EndowmentTransactionalDocumentBase) DocumentTestUtils.createDocument(SpringContext.getBean(DocumentService.class), CashDecreaseDocument.class);
            SpringContext.getBean(DocumentService.class).saveDocument(endowmentTransactionDocument);
        }
        catch (WorkflowException e) {
            throw new RuntimeException("Document creation failed.");
        }
        
        TransactionArchive transactionArchive = new TransactionArchive();
        transactionArchive.setDocumentNumber(endowmentTransactionDocument.getDocumentNumber());
        transactionArchive.setLineNumber(this.lineNumber);
        transactionArchive.setLineTypeCode(this.lineTypeCode);
        transactionArchive.setTypeCode(this.typeCode);
        transactionArchive.setSubTypeCode(this.subTypeCode);
        transactionArchive.setSrcTypeCode(this.srcTypeCode);
        transactionArchive.setKemid(this.kemid);
        transactionArchive.setEtranCode(this.etranCode);
        transactionArchive.setIncomePrincipalIndicatorCode(this.incomePrincipalIndicatorCode);
        transactionArchive.setPrincipalCashAmount(this.principalCashAmount);
        transactionArchive.setIncomeCashAmount(this.incomeCashAmount);
        transactionArchive.setCorpusIndicator(this.corpusIndicator);
        transactionArchive.setPostedDate(this.postedDate);
        
        saveTransactionArchiveRecord(transactionArchive);
        
        return transactionArchive;
    }

    /**
     * Method to save the business object....
     */
    private void saveTransactionArchiveRecord(TransactionArchive transactionArchive) {
        BusinessObjectService businessObjectService = SpringContext.getBean(BusinessObjectService.class);
        businessObjectService.save(transactionArchive);
    }
}
