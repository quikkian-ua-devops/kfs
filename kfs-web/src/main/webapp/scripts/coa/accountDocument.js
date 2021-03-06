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

function loadChartCodeUsingAccountNumber(accountNumberField, coaCodePropertyName) {
    var accountNumber = dwr.util.getValue(accountNumberField);
    
	var dwrReply = {
		callback: function (param) {
			if ( typeof param == "boolean" && param == false) {	
				loadChartCodeForBenefitExpenseTransferDocument(accountNumber, coaCodePropertyName);
			}
		},	
		errorHandler:function( errorMessage ) { 
			window.status = errorMessage;
		}
	};
	AccountService.accountsCanCrossCharts(dwrReply);	
}

function loadChartCodeForBenefitExpenseTransferDocument(accountNumber, coaCodePropertyName) {
	var chartCodePropertyName = coaCodePropertyName;
	
	if (accountNumber == "") {
	}
	else {
		var dwrReply = {
				callback: function (data) {
				if ( data != null && typeof data == "object" ) {  
					dwr.util.setValue(chartCodePropertyName, data.chartOfAccountsCode, {escapeHtml:true});					
				}
				else {
					clearRecipients(coaCodePropertyName); 
				}
			},
			errorHandler:function( errorMessage ) { 
				clearRecipients(coaCodePropertyName); 
			}
		};
		AccountService.getUniqueAccountForAccountNumber(accountNumber, dwrReply);	    
	}
}

function onblur_subFundGroup( sfgField, callbackFunction ) {
    var subFundGroup = getElementValue(sfgField.name);

    if (subFundGroup != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
		SubFundGroupService.getByPrimaryId( subFundGroup, dwrReply );
    }
}

/* Same as onblur_subFundGroup */
function updateSubFundGroup( sfgFieldName, callbackFunction ) {
    var subFundGroup = getElementValue(sfgFieldName);

    if (subFundGroup != "") {
		var dwrReply = {
			callback:callbackFunction,
			errorHandler:function( errorMessage ) { 
				window.status = errorMessage;
			}
		};
		SubFundGroupService.getByPrimaryId( subFundGroup, dwrReply );
    }
}

function onblur_accountRestrictedStatusCode( codeField, callbackFunction ) {
	var subFundGroupFieldName = findElPrefix( codeField.name ) + ".subFundGroupCode";
	updateSubFundGroup( subFundGroupFieldName, callbackFunction );
}

function checkRestrictedStatusCode_Callback( data ) {
	if ( data.accountRestrictedStatusCode != "" ) {
		if ( kualiElements["document.newMaintainableObject.accountRestrictedStatusCode"].type.toLowerCase() != "hidden" ) {
			setElementValue( "document.newMaintainableObject.accountRestrictedStatusCode", data.accountRestrictedStatusCode );
		}
	}
}

function update_laborBenefitRateCategoryCode ( codeField ) {
	var acctTypeCd = codeField.value;
	
	var dwrReply = {
		    callback:function(data) {
		    	if(kualiElements["document.newMaintainableObject.laborBenefitRateCategoryCode"].value == ''){
		    		setElementValue ( "document.newMaintainableObject.laborBenefitRateCategoryCode",data);
		    		alert("Setting the Labor Benefit Rate Category Code to the default described in the system parameter DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE");
		    	}
		    },
		    errorHandler:function( errorMessage ) {
		    	window.status = errorMessage;
		    }
		};
	AccountService.getDefaultLaborBenefitRateCategoryCodeForAccountType(acctTypeCd, dwrReply);
}



function update_laborBenefitRateCategoryCode ( codeField ) {
	var acctTypeCd = codeField.value;
	
	var dwrReply = {
		    callback:function(data) {
		    	if(kualiElements["document.newMaintainableObject.laborBenefitRateCategoryCode"].value == ''){
		    		setElementValue ( "document.newMaintainableObject.laborBenefitRateCategoryCode",data);
		    		alert("Setting the Labor Benefit Rate Category Code to the default described in the system parameter DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE");
		    	}
		    },
		    errorHandler:function( errorMessage ) {
		    	window.status = errorMessage;
		    }
		};
	AccountService.getDefaultLaborBenefitRateCategoryCodeForAccountType(acctTypeCd, dwrReply);
}

function update_laborBenefitRateCategoryCode ( codeField ) {
	var acctTypeCd = codeField.value;
	
	var dwrReply = {
		    callback:function(data) {
		    	if(kualiElements["document.newMaintainableObject.laborBenefitRateCategoryCode"].value == ''){
		    		setElementValue ( "document.newMaintainableObject.laborBenefitRateCategoryCode",data);
		    		alert("Setting the Labor Benefit Rate Category Code to the default described in the system parameter DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE");
		    	}
		    },
		    errorHandler:function( errorMessage ) {
		    	window.status = errorMessage;
		    }
		};
	AccountService.getDefaultLaborBenefitRateCategoryCodeForAccountType(acctTypeCd, dwrReply);
}

function update_laborBenefitRateCategoryCode ( codeField ) {
	var acctTypeCd = codeField.value;
	
	var dwrReply = {
		    callback:function(data) {
		    	if(kualiElements["document.newMaintainableObject.laborBenefitRateCategoryCode"].value == ''){
		    		setElementValue ( "document.newMaintainableObject.laborBenefitRateCategoryCode",data);
		    		alert("Setting the Labor Benefit Rate Category Code to the default described in the system parameter DEFAULT_BENEFIT_RATE_CATEGORY_CODE_BY_ACCOUNT_TYPE");
		    	}
		    },
		    errorHandler:function( errorMessage ) {
		    	window.status = errorMessage;
		    }
		};
	AccountService.getDefaultLaborBenefitRateCategoryCodeForAccountType(acctTypeCd, dwrReply);
}

function onblur_accountNumber( accountNumberField, coaCodePropertyName ) {
    //var coaCodeFieldName = findCoaFieldName( accountNumberField.name );
	var accountNumberFieldName = accountNumberField.name;
	var coaCodeFieldName = findElPrefix(accountNumberFieldName) + "." + coaCodePropertyName;
    var accountNumber = getElementValue( accountNumberFieldName );	    
	//alert("coaCodeFieldName = " + coaCodeFieldName + ", accountNumberFieldName = " + accountNumberFieldName);

	var dwrReply = {
		callback: function (param) {
			if ( typeof param == "boolean" && param == false) {	
				loadChartCode(accountNumber, coaCodeFieldName);
			}
		},	
		errorHandler:function( errorMessage ) { 
			window.status = errorMessage;
		}
	};
	AccountService.accountsCanCrossCharts(dwrReply);	
}

/**
 * Special case for chartCode-accountNumber fields that could contain wild cards "@"/"#". 
 */
function onblur_accountNumber_wildCard( accountNumberField, coaCodePropertyName ) {
    //var coaCodeFieldName = findCoaFieldName( accountNumberField.name );
	var accountNumberFieldName = accountNumberField.name;
	var coaCodeFieldName = findElPrefix(accountNumberFieldName) + "." + coaCodePropertyName;
    var accountNumber = getElementValue( accountNumberFieldName );	    
	//alert("coaCodeFieldName = " + coaCodeFieldName + ", accountNumberFieldName = " + accountNumberFieldName);

	var dwrReply = {
		callback: function (param) {
			if ( typeof param == "boolean" && param == false) {	
				// if accountNumber is wild card, copy it to chart code
				if (accountNumber == "@" || accountNumber == "#") {
					setRecipientValue( coaCodeFieldName, accountNumber);
				}
				// otherwise retrieve chart code from account as usual
				else {
					loadChartCode(accountNumber, coaCodeFieldName);
				}
			}
		},	
		errorHandler:function( errorMessage ) { 
			window.status = errorMessage;
		}
	};
	AccountService.accountsCanCrossCharts(dwrReply);	
}

/**
 * Special case when a new Account is created, the chartCode-accountNumber fields in the document can use the new account that's being created;
 * in which case chart code shall be populated from the PK chart code in the document instead of retrieving it from DB using the account number, 
 * as the new account doesn't exist in the DB yet.
 */
function onblur_accountNumber_newAccount( accountNumberField, coaCodePropertyName ) {
    //var coaCodeFieldName = findCoaFieldName( accountNumberField.name );
	var accountNumberFieldName = accountNumberField.name;
	var coaCodeFieldName = findElPrefix(accountNumberFieldName) + "." + coaCodePropertyName;
    var accountNumber = getElementValue( accountNumberFieldName );	 
	//alert("coaCodeFieldName = " + coaCodeFieldName + ", accountNumberFieldName = " + accountNumberFieldName);

    var chartCodePKName = "document.newMaintainableObject.chartOfAccountsCode";
    var accountNumberPKName = "document.newMaintainableObject.accountNumber";
    var chartCodePK = getElementValue(chartCodePKName);
    var accountNumberPK = getElementValue(accountNumberPKName);
    //alert("chartCodePK = " + chartCodePK + ", accountNumberPK = " + accountNumberPK);

	var dwrReply = {
		callback: function (param) {
			if ( typeof param == "boolean" && param == false) {	
				// if accountNumber is the same as accountNumberPK, copy chartCodePK to chart code
				if (accountNumber == accountNumberPK) {
					//alert("accountNumber == accountNumberPK");
					setRecipientValue(coaCodeFieldName, chartCodePK);
				}
				// otherwise retrieve chart code from account as usual
				else {
					loadChartCode(accountNumber, coaCodeFieldName);
				}
			}
		},	
		errorHandler:function( errorMessage ) { 
			window.status = errorMessage;
		}
	};
	AccountService.accountsCanCrossCharts(dwrReply);	
}

function loadChartCode( accountNumber, coaCodeFieldName ) {
	if (accountNumber == "") {
		clearRecipients(coaCodeFieldName);    		
	}
	else {
		var dwrReply = {
				callback: function (data) {
				//alert("chartOfAccountsCode = " + data.chartOfAccountsCode + ", accountNumber = " + accountNumber);
				if ( data != null && typeof data == "object" ) {   
					//var coaValue = data.chartOfAccountsCode + " - " + data.chartOfAccounts.finChartOfAccountDescription;
					setRecipientValue( coaCodeFieldName, data.chartOfAccountsCode );
				}
				else {
					clearRecipients(coaCodeFieldName); 
				}
			},
			errorHandler:function( errorMessage ) { 
				clearRecipients(coaCodeFieldName); 
				window.status = errorMessage;
			}
		};
		AccountService.getUniqueAccountForAccountNumber( accountNumber, dwrReply );	    
	}
}
function onblur_responsibilityCenterCode( chartOfAccountsCode, organizationCode, responsibilityCenterCode ) {  
	
	if(document.getElementById("document.newMaintainableObject.chartOfAccountsCode") != null){
		var coaCode = dwr.util.getValue( "document.newMaintainableObject.chartOfAccountsCode" ).toUpperCase();
	}else{
		var coaCode = document.getElementById("document.newMaintainableObject.chartOfAccountsCode.div").innerHTML.replace(/^[\s(&nbsp;)]+/g,'').replace(/[\s(&nbsp;)]+$/g,'');
	}
	
	var orgCode = dwr.util.getValue( "document.newMaintainableObject.organizationCode" );
	var responsibilityCenterCode = document.getElementById("document.newMaintainableObject.organization.responsibilityCenterCode.div");
	var responsibilityCenterName = document.getElementById("document.newMaintainableObject.organization.responsibilityCenter.responsibilityCenterName.div");
	
	if(orgCode == ''){
		dwr.util.setValue(responsibilityCenterCode, "", true);
		dwr.util.setValue(responsibilityCenterName, "", true);
	} else if(coaCode == ''){
		dwr.util.setValue( responsibilityCenterCode, wrapError( "chart code is empty" ), { escapeHtml:false } );
		dwr.util.setValue(responsibilityCenterName, "", { escapeHtml:false });
	} else {
		var dwrReply = {
			callback:function(data) {
				if ( data != null && typeof data == 'object' ) {
					responsibilityCenterCode.innerHTML = data.responsibilityCenterCode;
					updateResponsibilityCenterName(data.responsibilityCenterCode);
				}else{
					dwr.util.setValue( responsibilityCenterCode, wrapError( "RC Code not found" ), { escapeHtml:false } );
					dwr.util.setValue( responsibilityCenterName, "", { escapeHtml:false } );
				}
			},
			errorHandler:function( errorMessage ) { 
				responsibilityCenterCode.innerHTML = "Unable to get RC Code."; 
			}
		};
		OrganizationService.getByPrimaryId( coaCode,orgCode,dwrReply );
	}
	
}


function updateResponsibilityCenterName(responsibilityCenterCode){
	var dwrReply = {
		callback:function(data){
		if ( data != null && typeof data == 'object' ) {
				var responsibilityCenterName = document.getElementById("document.newMaintainableObject.organization.responsibilityCenter.responsibilityCenterName.div");
				responsibilityCenterName.innerHTML = data.responsibilityCenterName;
				
		}
		},
		errorHandler:function (errorMessage){
			responsibilityCenterName.innerHTML = "Unable to get RC Name";
		}
	};
	ResponsibilityCenterService.getByPrimaryId( responsibilityCenterCode,dwrReply );
}

/*
function findCoaFieldName( accountNumberFieldName ) {
	var index = accountNumberFieldName.indexOf("AccountNumber");    
	var coaCodeFieldName = accountNumberFieldName.substring(0, index) + "ChartOfAccountsCode";
	return coaCodeFieldName;
}    
*/
