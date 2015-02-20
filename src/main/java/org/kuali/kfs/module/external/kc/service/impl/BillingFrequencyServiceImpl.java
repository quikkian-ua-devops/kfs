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
package org.kuali.kfs.module.external.kc.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.kuali.kfs.module.external.kc.KcConstants;
import org.kuali.kfs.module.external.kc.businessobject.BillingFrequency;
import org.kuali.kfs.module.external.kc.businessobject.BillingFrequencyMapping;
import org.kuali.kfs.module.external.kc.dto.FrequencyDto;
import org.kuali.kfs.module.external.kc.service.BillingFrequencyService;
import org.kuali.kfs.module.external.kc.service.ExternalizableBusinessObjectService;
import org.kuali.kfs.module.external.kc.util.GlobalVariablesExtractHelper;
import org.kuali.kfs.module.external.kc.webService.FrequencyWebSoapService;
import org.kuali.kfs.sys.KFSConstants;
import org.kuali.kra.external.frequency.FrequencyWebService;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.krad.bo.ExternalizableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class was generated by Apache CXF 2.2.10
 * Thu Sep 30 05:29:28 HST 2010
 * Generated source version: 2.2.10
 *
 */

public class BillingFrequencyServiceImpl implements ExternalizableBusinessObjectService, BillingFrequencyService {
    protected static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BillingFrequencyServiceImpl.class);

    protected BusinessObjectService businessObjectService;
    protected ConfigurationService configurationService;

    protected FrequencyWebService getWebService() {
        // first attempt to get the service from the KSB - works when KFS & KC share a Rice instance
        FrequencyWebService frequencyWebService = (FrequencyWebService) GlobalResourceLoader.getService(KcConstants.Frequency.SERVICE);

        // if we couldn't get the service from the KSB, get as web service - for when KFS & KC have separate Rice instances
        if (frequencyWebService == null) {
            LOG.warn("Couldn't get FrequencyWebService from KSB, setting it up as SOAP web service - expected behavior for bundled Rice, but not when KFS & KC share a standalone Rice instance.");
            FrequencyWebSoapService ss =  null;
            try {
                ss = new FrequencyWebSoapService();
            }
            catch (MalformedURLException ex) {
                LOG.error("Could not intialize AwardWebSoapService: " + ex.getMessage());
                throw new RuntimeException("Could not intialize AwardWebSoapService: " + ex.getMessage());
            }
            frequencyWebService = ss.getFrequencyWebServicePort();
        }

        return frequencyWebService;
    }

    @Override
    public ExternalizableBusinessObject findByPrimaryKey(Map primaryKeys) {
        //use the proposal number as its the awardId on the KC side.
        FrequencyDto dto  = this.getWebService().getFrequency((String)primaryKeys.get(KcConstants.BillingFrequencyService.FREQUENCY));
        return createBillingFrequency(dto);
    }

    @Override
    public Collection findMatching(Map fieldValues) {
        List<FrequencyDto> result = null;

        try {
            if (fieldValues.isEmpty()) {
                result = this.getWebService().findAll();
            } else {
                result = this.getWebService().findMatching((String) fieldValues.get(KcConstants.BillingFrequencyService.FREQUENCY), (String) fieldValues.get(KcConstants.BillingFrequencyService.FREQUENCY_DESCRIPTION));
            }
        } catch (WebServiceException ex) {
            GlobalVariablesExtractHelper.insertError(KcConstants.WEBSERVICE_UNREACHABLE, getConfigurationService().getPropertyValueAsString(KFSConstants.KC_APPLICATION_URL_KEY));
        }

        if (result == null) {
            return new ArrayList();
        } else {
            return createBillingFrequency(result);
        }
    }

    @Override
    public BillingFrequency createBillingFrequency(FrequencyDto kcFrequency) {
        if (kcFrequency != null) {
            return createBillingFrequency(kcFrequency, getFrequencyMapping(kcFrequency.getFrequencyCode()));
        } else {
            return null;
        }
    }

    protected List<BillingFrequency> createBillingFrequency(List<FrequencyDto> kcFrequencies) {
        List<BillingFrequency> result = new ArrayList<BillingFrequency>();
        for (FrequencyDto dto : kcFrequencies) {
            BillingFrequencyMapping mapping = getFrequencyMapping(dto.getFrequencyCode());
            if (mapping != null) {
                result.add(createBillingFrequency(dto, mapping));
            }
        }
        return result;
    }

    protected BillingFrequency createBillingFrequency(FrequencyDto kcFrequency, BillingFrequencyMapping mapping) {
        BillingFrequency freq = new BillingFrequency();
        freq.setKcFrequencyCode(kcFrequency.getFrequencyCode());
        freq.setFrequencyDescription(kcFrequency.getDescription());
        if (mapping != null) {
            freq.setFrequency(mapping.getFrequency());
            freq.setGracePeriodDays(mapping.getGracePeriodDays());
            freq.setActive(mapping.isActive());
        } else {
            freq.setActive(false);
        }
        return freq;

    }

    protected BillingFrequencyMapping getFrequencyMapping(String kcFrequencyCode) {
        Map<String, String> values = new HashMap<String, String>();
        values.put(KcConstants.BillingFrequencyService.KC_FREQUENCY_CODE, kcFrequencyCode);
        Collection<BillingFrequencyMapping> mappings = getBusinessObjectService().findMatching(BillingFrequencyMapping.class, values);
        if (mappings != null && !mappings.isEmpty()) {
            return mappings.iterator().next();
        } else {
            return null;
        }
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

 }