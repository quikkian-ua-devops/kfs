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
package org.kuali.module.labor.batch;

import org.kuali.kfs.batch.AbstractStep;
import org.kuali.module.labor.service.LaborYearEndBalanceForwardService;

/**
 * This class...
 */
public class LaborYearEndBalanceForwardStep extends AbstractStep {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LaborYearEndBalanceForwardStep.class);

    private LaborYearEndBalanceForwardService laborYearEndBalanceForwardService; 

    /**
     * @see org.kuali.kfs.batch.Step#execute()
     */
    public boolean execute() {
        laborYearEndBalanceForwardService.forwardBalance();
        return true;
    }

    /**
     * Sets the laborYearEndBalanceForwardService attribute value.
     * @param laborYearEndBalanceForwardService The laborYearEndBalanceForwardService to set.
     */
    public void setLaborYearEndBalanceForwardService(LaborYearEndBalanceForwardService laborYearEndBalanceForwardService) {
        this.laborYearEndBalanceForwardService = laborYearEndBalanceForwardService;
    }
}