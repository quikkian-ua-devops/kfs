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
package org.kuali.kfs.module.cam.fixture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.kfs.module.cam.businessobject.BarcodeInventoryErrorDetail;
import org.kuali.kfs.module.cam.document.BarcodeInventoryErrorDocument;
import org.kuali.rice.krad.service.BusinessObjectService;

public enum BarcodeInventoryErrorDetailPredicateFixture {
    DATA();

    private BusinessObjectService businessObjectService;    
    private int testDataPos;
    private static Properties properties;
    static {
        String propertiesFileName = "org/kuali/kfs/module/cam/document/service/barcode_inventory_predicate.properties";
        properties = new Properties();
        try {
            properties.load(BarcodeInventoryErrorDetailPredicateFixture.class.getClassLoader().getResourceAsStream(propertiesFileName));
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static String TEST_RECORD="testRecord";
    static String RESULT="result";
    static String DOCUMENT="document";
    static String BCIE="bcie";
    static String FIELD_NAMES="fieldNames";
    static String NUM_OF_REC="numOfRecords";
    static String DELIMINATOR="deliminator";

    private BarcodeInventoryErrorDetailPredicateFixture() {  
    }

    public List<BarcodeInventoryErrorDetail> getBarcodeInventoryDetail() {
        Integer numOfRecords = new Integer(properties.getProperty(BCIE+"."+NUM_OF_REC));                        
        List<BarcodeInventoryErrorDetail> details = new ArrayList<BarcodeInventoryErrorDetail>();

        String deliminator = properties.getProperty(DELIMINATOR);
        String fieldNames = properties.getProperty(BCIE+"."+FIELD_NAMES);

        for(int i=1;i<=numOfRecords.intValue();i++) {
            String propertyKey = BCIE+"."+TEST_RECORD + i;
            details.add(CamsFixture.DATA_POPULATOR.buildTestDataObject(BarcodeInventoryErrorDetail.class, properties, propertyKey, fieldNames, deliminator));
        }
        return details;
    }


    public List<BarcodeInventoryErrorDetail> getExpectedResults() {
        Integer numOfRecords = new Integer(properties.getProperty(BCIE+"."+NUM_OF_REC));                        
        List<BarcodeInventoryErrorDetail> details = new ArrayList<BarcodeInventoryErrorDetail>();

        String deliminator = properties.getProperty(DELIMINATOR);
        String fieldNames = properties.getProperty(BCIE+"."+FIELD_NAMES);

        for(int i=1;i<=numOfRecords.intValue();i++) {
            String propertyKey = RESULT+"."+TEST_RECORD + i;
            details.add(CamsFixture.DATA_POPULATOR.buildTestDataObject(BarcodeInventoryErrorDetail.class, properties, propertyKey, fieldNames, deliminator));
        }
        return details;
    }


    public BarcodeInventoryErrorDocument getBarcodeInventoryErrorDocument() {
        BarcodeInventoryErrorDocument barcodeInventoryErrorDocument;

        String deliminator = properties.getProperty(DELIMINATOR);
        String fieldNames = properties.getProperty(DOCUMENT+"."+FIELD_NAMES);

        String propertyKey = DOCUMENT+"."+TEST_RECORD+"1";
        barcodeInventoryErrorDocument = CamsFixture.DATA_POPULATOR.buildTestDataObject(BarcodeInventoryErrorDocument.class, properties, propertyKey, fieldNames, deliminator);
 
        return barcodeInventoryErrorDocument;        
    }
}
