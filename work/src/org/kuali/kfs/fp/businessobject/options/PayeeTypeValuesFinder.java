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
package org.kuali.kfs.fp.businessobject.options;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kfs.fp.document.validation.impl.DisbursementVoucherRuleConstants.PayeeType;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * This class returns list of payee type value pairs.
 */
public class PayeeTypeValuesFinder extends KeyValuesBase{
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PayeeTypeValuesFinder.class);

    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        
        for(PayeeType payeeType : PayeeType.values()) {
            keyValues.add(new KeyLabelPair(payeeType.getPayeeTypeCode(), payeeType.getPayeeTypeName()));
        }
        
        return keyValues;
    }
}
