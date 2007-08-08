/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.module.budget.web.struts.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.kfs.util.SpringServiceLocator;
import org.kuali.module.budget.BCConstants.OrgSelOpMode;
import org.kuali.module.budget.bo.BudgetConstructionOrganizationReports;
import org.kuali.module.chart.bo.Org;

/**
 * This class...
 */
public class OrganizationSelectionTreeForm extends KualiForm {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(OrganizationSelectionTreeForm.class);
    
    private BudgetConstructionOrganizationReports pointOfViewOrg;
    private boolean hideDetails = false;
    private String operatingModeTitle;

    private String currentPointOfViewKeyCode;
    private String previousPointOfViewKeyCode;

    //passed parms
    private String returnAnchor;
    private String returnFormKey;
    private String operatingMode;

    /**
     * Constructs a OrganizationSelectionTreeForm.java.
     */
    public OrganizationSelectionTreeForm() {
        super();
        this.setPointOfViewOrg(new BudgetConstructionOrganizationReports());
    }

    /**
     * @see org.kuali.core.web.struts.form.KualiForm#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        // TODO Auto-generated method stub
        super.populate(request);
        
        // check for point of view change
        if (getCurrentPointOfViewKeyCode() != null){
            String[] flds = getCurrentPointOfViewKeyCode().split("[-]");
            setPreviousPointOfViewKeyCode(getCurrentPointOfViewKeyCode());

            HashMap map = new HashMap();
            map.put("chartOfAccountsCode", flds[0]);
            map.put("organizationCode", flds[1]);
            pointOfViewOrg = (BudgetConstructionOrganizationReports) SpringServiceLocator.getBusinessObjectService().findByPrimaryKey(BudgetConstructionOrganizationReports.class, map);
        }
        
    }

    /**
     * Gets the pointOfViewOrg attribute. 
     * @return Returns the pointOfViewOrg.
     */
    public BudgetConstructionOrganizationReports getPointOfViewOrg() {
        return pointOfViewOrg;
    }

    /**
     * Sets the pointOfViewOrg attribute value.
     * @param pointOfViewOrg The pointOfViewOrg to set.
     */
    public void setPointOfViewOrg(BudgetConstructionOrganizationReports pointOfViewOrg) {
        this.pointOfViewOrg = pointOfViewOrg;
    }

    /**
     * Gets the returnAnchor attribute. 
     * @return Returns the returnAnchor.
     */
    public String getReturnAnchor() {
        return returnAnchor;
    }

    /**
     * Sets the returnAnchor attribute value.
     * @param returnAnchor The returnAnchor to set.
     */
    public void setReturnAnchor(String returnAnchor) {
        this.returnAnchor = returnAnchor;
    }

    /**
     * Gets the returnFormKey attribute. 
     * @return Returns the returnFormKey.
     */
    public String getReturnFormKey() {
        return returnFormKey;
    }

    /**
     * Sets the returnFormKey attribute value.
     * @param returnFormKey The returnFormKey to set.
     */
    public void setReturnFormKey(String returnFormKey) {
        this.returnFormKey = returnFormKey;
    }

    /**
     * Gets the hideDetails attribute. 
     * @return Returns the hideDetails.
     */
    public boolean isHideDetails() {
        return hideDetails;
    }

    /**
     * Sets the hideDetails attribute value.
     * @param hideDetails The hideDetails to set.
     */
    public void setHideDetails(boolean hideDetails) {
        this.hideDetails = hideDetails;
    }

    /**
     * Gets the operatingMode attribute. 
     * @return Returns the operatingMode.
     */
    public String getOperatingMode() {
        return operatingMode;
    }

    /**
     * Sets the operatingMode attribute value.
     * @param operatingMode The operatingMode to set.
     */
    public void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }

    /**
     * Gets the operatingModeTitle attribute. 
     * @return Returns the operatingModeTitle.
     */
    public String getOperatingModeTitle() {
        return operatingModeTitle;
    }

    /**
     * Sets the operatingModeTitle attribute value.
     * @param operatingModeTitle The operatingModeTitle to set.
     */
    public void setOperatingModeTitle(String operatingModeTitle) {
        this.operatingModeTitle = operatingModeTitle;
    }

    /**
     * Gets the currentPointOfViewKeyCode attribute. 
     * @return Returns the currentPointOfViewKeyCode.
     */
    public String getCurrentPointOfViewKeyCode() {
        return currentPointOfViewKeyCode;
    }

    /**
     * Sets the currentPointOfViewKeyCode attribute value.
     * @param currentPointOfViewKeyCode The currentPointOfViewKeyCode to set.
     */
    public void setCurrentPointOfViewKeyCode(String currentPointOfViewKeyCode) {
        this.currentPointOfViewKeyCode = currentPointOfViewKeyCode;
    }

    /**
     * Gets the previousPointOfViewKeyCode attribute. 
     * @return Returns the previousPointOfViewKeyCode.
     */
    public String getPreviousPointOfViewKeyCode() {
        return previousPointOfViewKeyCode;
    }

    /**
     * Sets the previousPointOfViewKeyCode attribute value.
     * @param previousPointOfViewKeyCode The previousPointOfViewKeyCode to set.
     */
    public void setPreviousPointOfViewKeyCode(String previousPointOfViewKeyCode) {
        this.previousPointOfViewKeyCode = previousPointOfViewKeyCode;
    }


}
