/*
 * Copyright 2006-2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.module.purap.bo;

import java.util.LinkedHashMap;

import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.kfs.KFSPropertyConstants;
import org.kuali.module.purap.document.AssignContractManagerDocument;
import org.kuali.module.purap.document.RequisitionDocument;
import org.kuali.module.vendor.bo.ContractManager;

/**
 * Assign Contract Manager Detail Business Object.
 * Defines attributes in Assign Contract Manager tab.
 */
public class AssignContractManagerDetail extends PersistableBusinessObjectBase {

	private String documentNumber;
	private Integer requisitionIdentifier;
	private Integer contractManagerCode;

    private RequisitionDocument requisition;
    private ContractManager contractManager;
    private AssignContractManagerDocument assignContractManagerDocument;
    
    /**
     * Default constructor.
     */
    public AssignContractManagerDetail() {

	}

    /**
     * Constructs a AssignContractManagerDetail object from an existing AssignContractManagerDocument object.
     */
    public AssignContractManagerDetail(AssignContractManagerDocument acmDocument, RequisitionDocument requisitionDocument) {
        this.documentNumber = acmDocument.getDocumentNumber();
        this.assignContractManagerDocument = acmDocument;
        this.requisition = requisitionDocument;
        this.requisitionIdentifier = requisitionDocument.getPurapDocumentIdentifier();
    }

	public String getDocumentNumber() { 
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Integer getRequisitionIdentifier() { 
		return requisitionIdentifier;
	}

	public void setRequisitionIdentifier(Integer requisitionIdentifier) {
		this.requisitionIdentifier = requisitionIdentifier;
	}

	public Integer getContractManagerCode() { 
		return contractManagerCode;
	}

	public void setContractManagerCode(Integer contractManagerCode) {
		this.contractManagerCode = contractManagerCode;
	}

    public ContractManager getContractManager() {
        return contractManager;
    }

    /**
     * @deprecated
     */
    public void setContractManager(ContractManager contractManager) {
        this.contractManager = contractManager;
    }

    public RequisitionDocument getRequisition() {
        return requisition;
    }

    /**
     * @deprecated
     */
    public void setRequisition(RequisitionDocument requisition) {
        this.requisition = requisition;
    }

    public AssignContractManagerDocument getAssignContractManagerDocument() {
        return assignContractManagerDocument;
    }

    public void setAssignContractManagerDocument(AssignContractManagerDocument assignContractManagerDocument) {
        this.assignContractManagerDocument = assignContractManagerDocument;
    }    

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap m = new LinkedHashMap();      
        m.put(KFSPropertyConstants.DOCUMENT_NUMBER, this.documentNumber);
        if (this.requisitionIdentifier != null) {
            m.put("requisitionIdentifier", this.requisitionIdentifier.toString());
        }
        return m;
    }
    
}
