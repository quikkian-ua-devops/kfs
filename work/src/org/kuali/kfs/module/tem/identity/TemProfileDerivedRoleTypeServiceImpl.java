/*
 * Copyright 2011 The Kuali Foundation.
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
package org.kuali.kfs.module.tem.identity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kfs.module.tem.businessobject.TemProfile;
import org.kuali.kfs.sys.KFSPropertyConstants;
import org.kuali.rice.core.api.membership.MemberType;
import org.kuali.rice.kim.api.role.RoleMembership;
import org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase;
import org.kuali.rice.krad.service.BusinessObjectService;

@SuppressWarnings("deprecation")
public class TemProfileDerivedRoleTypeServiceImpl extends DerivedRoleTypeServiceBase {
    protected BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.rice.kns.kim.type.DataDictionaryTypeServiceBase#getRequiredAttributes()
     */
    @Override
    protected List<String> getRequiredAttributes() {
        final List<String> attrs = new ArrayList<String>(super.getRequiredAttributes());
        attrs.add(TemKimAttributes.PROFILE_PRINCIPAL_ID);
        return Collections.unmodifiableList(attrs);
    }

    /**
     * @see org.kuali.rice.kns.kim.role.DerivedRoleTypeServiceBase#getRoleMembersFromDerivedRole(java.lang.String, java.lang.String, java.util.Map)
     */
    @Override
    public List<RoleMembership> getRoleMembersFromDerivedRole(String namespaceCode, String roleName, Map<String, String> qualification) {
        validateRequiredAttributesAgainstReceived(qualification);
        final List<RoleMembership> members = new ArrayList<RoleMembership>(1);
        if (qualification!=null && !qualification.isEmpty()) {
            // This is to allow users to create/edit their own profile. If the principalId from the profile
            // matches, the profile fields are unmasked. Otherwise the other roles will handle the appropriate
            // masking/unmasking of fields (see TemProfileOrganizationHierarchyRoleTypeServiceImpl).
            String principalId = qualification.get(TemKimAttributes.PROFILE_PRINCIPAL_ID);
            if (StringUtils.isNotBlank(principalId)) {
                // does this profile principal id have an actual profile?
                if (hasProfile(principalId)) {
                    members.add(RoleMembership.Builder.create("", "", principalId, MemberType.PRINCIPAL, null).build());
                }
            }
        }
        return members;
    }

    /**
     * Determines if the user with the given principal id has a profile record
     * @param profilePrincipalId the principal id to check for a profile for
     * @return true if a profile was found, false otherwise
     */
    protected boolean hasProfile(String profilePrincipalId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(KFSPropertyConstants.PRINCIPAL_ID, profilePrincipalId);
        fieldValues.put(KFSPropertyConstants.ACTIVE, Boolean.TRUE);
        final int profileCount = getBusinessObjectService().countMatching(TemProfile.class, fieldValues);
        return profileCount > 0;
    }

    @Override
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}