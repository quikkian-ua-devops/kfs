<%--
 Copyright 2005-2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/jsp/kfs/kfsTldHeader.jsp"%>
<kul:page showDocumentInfo="false" htmlFormAction="cabPurApLine" renderMultipart="true"
	showTabButtons="true" docTitle="Purchasing / Accounts Payable Transactions" transactionalDocument="false">
	
	<kul:tabTop tabTitle="Purchase Order Processing" defaultOpen="true">
		<div class="tab-container" align=center>
			<c:set var="cabPurApDocumentAttributes"	value="${DataDictionary.PurchasingAccountsPayableDocument.attributes}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="datatable">
				<tr>
        			<td colspan="2" class="subhead">Purchase Order Processing</td>
   				</tr>
   				<tr>
   					<th class="grid"><kul:htmlAttributeLabel attributeEntry="${cabPurApDocumentAttributes.purchaseOrderIdentifier}" readOnly="true" /></th>
        			<td class="grid"><kul:htmlControlAttribute property="purchaseOrderIdentifier" attributeEntry="${cabPurApDocumentAttributes.purchaseOrderIdentifier}" readOnly="true"/></td>								
    			</tr>
    			<tr>
   					<th class="grid"><kul:htmlAttributeLabel attributeEntry="${cabPurApDocumentAttributes.purApContactEmailAddress}" readOnly="true" /></th>
        			<td class="grid"><kul:htmlControlAttribute property="purApContactEmailAddress" attributeEntry="${cabPurApDocumentAttributes.purApContactEmailAddress}" readOnly="true"/></td>								
    			</tr>
    			<tr>
   					<th class="grid"><kul:htmlAttributeLabel attributeEntry="${cabPurApDocumentAttributes.purApContactPhoneNumber}" readOnly="true" /></th>
        			<td class="grid"><kul:htmlControlAttribute property="purApContactPhoneNumber" attributeEntry="${cabPurApDocumentAttributes.purApContactPhoneNumber}" readOnly="true"/></td>								
    			</tr>
    		</table>
		</div>
	</kul:tabTop>

	<kul:panelFooter />
	<div id="globalbuttons" class="globalbuttons">
        <c:if test="${not readOnly}">
	        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_save.gif" styleClass="globalbuttons" 
	        	property="methodToCall.save" title="save" alt="save"/>
	    
	        <html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" styleClass="globalbuttons" 
	        	property="methodToCall.close" title="close" alt="close"/>
        </c:if>		
    </div>
</kul:page>