/*
 * Copyright 2012 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.module.ar.report.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.ar.ArConstants;
import org.kuali.kfs.module.ar.ArPropertyConstants;
import org.kuali.kfs.module.ar.businessobject.CollectionActivityReport;
import org.kuali.kfs.module.ar.businessobject.CollectionActivityType;
import org.kuali.kfs.module.ar.businessobject.CustomerInvoiceDetail;
import org.kuali.kfs.module.ar.businessobject.Event;
import org.kuali.kfs.module.ar.document.ContractsGrantsInvoiceDocument;
import org.kuali.kfs.module.ar.document.service.ContractsGrantsInvoiceDocumentService;
import org.kuali.kfs.module.ar.report.service.CollectionActivityReportService;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.kfs.sys.document.service.FinancialSystemDocumentService;
import org.kuali.kfs.sys.service.NonTransactional;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is used to get the services for PDF generation and other services for Collection Activity Report
 */
public class CollectionActivityReportServiceImpl implements CollectionActivityReportService {
    protected ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService;
    protected BusinessObjectService businessObjectService;
    protected FinancialSystemDocumentService financialSystemDocumentService;
    protected PersonService personService;
    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CollectionActivityReportServiceImpl.class);

    /**
     * Gets the businessObjectService attribute.
     *
     * @return Returns the businessObjectService.
     */
    @NonTransactional
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     *
     * @param businessObjectService The businessObjectService to set.
     */
    @NonTransactional
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the contractsGrantsInvoiceDocumentService attribute.
     *
     * @return Returns the contractsGrantsInvoiceDocumentService.
     */
    @NonTransactional
    public ContractsGrantsInvoiceDocumentService getContractsGrantsInvoiceDocumentService() {
        return contractsGrantsInvoiceDocumentService;
    }

    /**
     * Sets the contractsGrantsInvoiceDocumentService attribute value.
     *
     * @param contractsGrantsInvoiceDocumentService The contractsGrantsInvoiceDocumentService to set.
     */
    @NonTransactional
    public void setContractsGrantsInvoiceDocumentService(ContractsGrantsInvoiceDocumentService contractsGrantsInvoiceDocumentService) {
        this.contractsGrantsInvoiceDocumentService = contractsGrantsInvoiceDocumentService;
    }

    /**
     * @see org.kuali.kfs.module.ar.report.service.ContractsGrantsAgingReportService#filterContractsGrantsAgingReport(java.util.Map)
     */
    @Override
    @Transactional
    public List<CollectionActivityReport> filterEventsForCollectionActivity(Map lookupFormFields) {

        List<CollectionActivityReport> displayList = new ArrayList<>();

        Map<String,String> fieldValues = new HashMap<>();

        String collectorPrincName = (String)lookupFormFields.get(ArPropertyConstants.COLLECTOR_PRINC_NAME);
        String collector = (String)lookupFormFields.get(ArPropertyConstants.CollectionActivityReportFields.COLLECTOR);
        String proposalNumber = (String)lookupFormFields.get(ArPropertyConstants.CollectionActivityReportFields.PROPOSAL_NUMBER);
        String agencyNumber = (String)lookupFormFields.get(ArPropertyConstants.CollectionActivityReportFields.AGENCY_NUMBER);
        String invoiceNumber = (String)lookupFormFields.get(ArPropertyConstants.CollectionActivityReportFields.INVOICE_NUMBER);
        String accountNumber = (String)lookupFormFields.get(ArPropertyConstants.CollectionActivityReportFields.ACCOUNT_NUMBER);

        // Getting final docs
        fieldValues.put(KFSPropertyConstants.DOCUMENT_HEADER+"."+KFSPropertyConstants.WORKFLOW_DOCUMENT_STATUS_CODE, StringUtils.join(getFinancialSystemDocumentService().getSuccessfulDocumentStatuses(), "|"));
        fieldValues.put(KFSPropertyConstants.DOCUMENT_HEADER+"."+KFSPropertyConstants.WORKFLOW_DOCUMENT_TYPE_NAME, ArConstants.ArDocumentTypeCodes.CONTRACTS_GRANTS_INVOICE);

        if (!StringUtils.isBlank(proposalNumber)) {
            fieldValues.put(KFSPropertyConstants.PROPOSAL_NUMBER, proposalNumber);
        }

        if (!StringUtils.isBlank(invoiceNumber)) {
            fieldValues.put(ArPropertyConstants.CustomerInvoiceDocumentFields.DOCUMENT_NUMBER, invoiceNumber);
        }

        if (!StringUtils.isBlank(accountNumber)) {
            fieldValues.put(ArPropertyConstants.CustomerInvoiceDocumentFields.ACCOUNT_NUMBER, accountNumber);
        }

        // Filter Invoice docs according to criteria.
        Collection<ContractsGrantsInvoiceDocument> contractsGrantsInvoiceDocs = contractsGrantsInvoiceDocumentService.retrieveAllCGInvoicesByCriteria(fieldValues);

        if (!CollectionUtils.isEmpty(contractsGrantsInvoiceDocs)) {
            String collectorPrincipalId = null;
            if (StringUtils.isNotEmpty(collectorPrincName.trim())) {
                Person collUser = personService.getPersonByPrincipalName(collectorPrincName);
                if (ObjectUtils.isNotNull(collUser)) {
                    collectorPrincipalId = collUser.getPrincipalId();
                }
            }


            for (Iterator<ContractsGrantsInvoiceDocument> iter = contractsGrantsInvoiceDocs.iterator(); iter.hasNext();) {
                ContractsGrantsInvoiceDocument document = iter.next();

                if (!canDocumentBeViewed(document, collectorPrincipalId)) {
                    iter.remove();
                }
                if (!StringUtils.isBlank(agencyNumber) && !matchesAgencyNumber(document, agencyNumber)) {
                    iter.remove();
                }
            }
        }

        if (!CollectionUtils.isEmpty(contractsGrantsInvoiceDocs)) {
            for (ContractsGrantsInvoiceDocument cgInvoiceDocument : contractsGrantsInvoiceDocs) {
                List<Event> events = cgInvoiceDocument.getEvents();
                List<CustomerInvoiceDetail> details = cgInvoiceDocument.getSourceAccountingLines();
                final String accountNum = (!CollectionUtils.isEmpty(details) && !ObjectUtils.isNull(details.get(0))) ? details.get(0).getAccountNumber() : "";
                if (CollectionUtils.isNotEmpty(events)) {
                    for (Event event : events) {
                        if (ObjectUtils.isNull(event.getEventRouteStatus()) || !StringUtils.equals(event.getEventRouteStatus(), DocumentStatus.SAVED.getCode())) {
                            CollectionActivityReport collectionActivityReport = new CollectionActivityReport();
                            collectionActivityReport.setAccountNumber(accountNum);
                            convertEventToCollectionActivityReport(collectionActivityReport, event);
                            displayList.add(collectionActivityReport);
                        }
                    }
                }
            }
        }

        return displayList;
    }

    /**
     * Determines if the given CINV can be viewed for the given collector and by the current user
     * @param document the CINV document to test
     * @param collectorPrincipalId the collector principal id to test if it exists
     * @return true if the document can be viewed, false otherwise
     */
    protected boolean canDocumentBeViewed(ContractsGrantsInvoiceDocument document, String collectorPrincipalId) {
        final Person user = GlobalVariables.getUserSession().getPerson();
        return (StringUtils.isBlank(collectorPrincipalId) || contractsGrantsInvoiceDocumentService.canViewInvoice(document, collectorPrincipalId))
                && contractsGrantsInvoiceDocumentService.canViewInvoice(document, user.getPrincipalId());
    }

    /**
     * Determines if the given document matches the passed in agency number
     * @param document the document to check
     * @param agencyNumber the agency number to verify against
     * @return true if the document matches the given agency number, false otherwise
     */
    protected boolean matchesAgencyNumber(ContractsGrantsInvoiceDocument document, String agencyNumber) {
        if (!ObjectUtils.isNull(document) && !ObjectUtils.isNull(document.getAward()) && !StringUtils.isBlank(document.getAward().getAgencyNumber())) {
            final String documentAgencyNumber = document.getAward().getAgencyNumber();
            return StringUtils.equals(documentAgencyNumber, agencyNumber);
        }
        return false;
    }

    /**
     * This method is used to convert the Event Object into collection activity report.
     *
     * @param collectionActivityReport
     * @param event
     * @return Returns the Object of CollectionActivityReport class.
     */
    protected CollectionActivityReport convertEventToCollectionActivityReport(CollectionActivityReport collectionActivityReport, Event event) {

        if (ObjectUtils.isNull(event)) {
            LOG.error("an invalid(null) argument was given");
            throw new IllegalArgumentException("an invalid(null) argument was given");
        }

        // account no
        collectionActivityReport.setInvoiceNumber(event.getInvoiceNumber());
        collectionActivityReport.setActivityDate(event.getActivityDate());

        // Activity Type
        CollectionActivityType collectionActivityType = event.getCollectionActivityType();

        if (ObjectUtils.isNotNull(collectionActivityType)) {
            collectionActivityReport.setActivityType(collectionActivityType.getActivityDescription());
        }

        collectionActivityReport.setActivityComment(event.getActivityText());
        collectionActivityReport.setFollowupDate(event.getFollowupDate());
        collectionActivityReport.setProposalNumber(event.getInvoiceDocument().getProposalNumber());
        collectionActivityReport.setCompleted(event.isCompleted());
        collectionActivityReport.setCompletedDate(event.getCompletedDate());

        String userPrincId = event.getUserPrincipalId();
        Person person = personService.getPerson(userPrincId);

        if (ObjectUtils.isNotNull(person)) {
            collectionActivityReport.setUserPrincipalId(person.getName());
        }

        if (ObjectUtils.isNotNull(event.getInvoiceDocument())) {
            collectionActivityReport.setChartOfAccountsCode(event.getInvoiceDocument().getBillByChartOfAccountCode());
        }
        if (ObjectUtils.isNotNull(event.getInvoiceDocument().getAward()) && ObjectUtils.isNotNull(event.getInvoiceDocument().getAward().getAgency())) {
            collectionActivityReport.setAgencyNumber(event.getInvoiceDocument().getAward().getAgency().getAgencyNumber());
            collectionActivityReport.setAgencyName(event.getInvoiceDocument().getAward().getAgency().getFullName());
        }
        return collectionActivityReport;
    }

    @NonTransactional
    public PersonService getPersonService() {
        return personService;
    }

    @NonTransactional
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public FinancialSystemDocumentService getFinancialSystemDocumentService() {
        return financialSystemDocumentService;
    }

    public void setFinancialSystemDocumentService(FinancialSystemDocumentService financialSystemDocumentService) {
        this.financialSystemDocumentService = financialSystemDocumentService;
    }
}
