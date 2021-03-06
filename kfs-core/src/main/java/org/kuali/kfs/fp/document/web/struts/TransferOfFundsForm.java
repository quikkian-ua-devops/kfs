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
package org.kuali.kfs.fp.document.web.struts;

import org.kuali.kfs.fp.document.TransferOfFundsDocument;
import org.kuali.kfs.sys.web.struts.KualiAccountingDocumentFormBase;

/**
 * This class is the form class for the Transfer of Funds document. This method extends the parent
 * KualiTransactionalDocumentFormBase class which contains all of the common form methods and form attributes needed by the Transfer
 * of Funds document. It adds a new method which is a convenience method for getting at the Transfer of Funds document easier.
 */
public class TransferOfFundsForm extends KualiAccountingDocumentFormBase {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a TransferOfFundsForm instance and sets up the appropriately casted document.
     */
    public TransferOfFundsForm() {
        super();
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "TF";
    }
    
    /**
     * @return Returns the serviceBillingDocument.
     */
    public TransferOfFundsDocument getTransferOfFundsDocument() {
        return (TransferOfFundsDocument) getDocument();
    }
}
