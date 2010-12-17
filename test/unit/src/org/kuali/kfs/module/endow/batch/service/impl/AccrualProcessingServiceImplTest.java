/*
 * Copyright 2010 The Kuali Foundation.
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
package org.kuali.kfs.module.endow.batch.service.impl;

import static org.kuali.kfs.sys.fixture.UserNameFixture.kfs;

import java.math.BigDecimal;
import java.sql.Date;

import org.kuali.kfs.module.endow.EndowConstants;
import org.kuali.kfs.module.endow.EndowTestConstants;
import org.kuali.kfs.module.endow.businessobject.ClassCode;
import org.kuali.kfs.module.endow.businessobject.EndowmentTransactionCode;
import org.kuali.kfs.module.endow.businessobject.HoldingTaxLot;
import org.kuali.kfs.module.endow.businessobject.KEMID;
import org.kuali.kfs.module.endow.businessobject.RegistrationCode;
import org.kuali.kfs.module.endow.businessobject.Security;
import org.kuali.kfs.module.endow.businessobject.SecurityReportingGroup;
import org.kuali.kfs.module.endow.document.service.HoldingTaxLotService;
import org.kuali.kfs.module.endow.document.service.KEMService;
import org.kuali.kfs.module.endow.fixture.ClassCodeFixture;
import org.kuali.kfs.module.endow.fixture.EndowmentTransactionCodeFixture;
import org.kuali.kfs.module.endow.fixture.HoldingTaxLotFixture;
import org.kuali.kfs.module.endow.fixture.HoldingTaxLotRebalanceFixture;
import org.kuali.kfs.module.endow.fixture.KemIdFixture;
import org.kuali.kfs.module.endow.fixture.RegistrationCodeFixture;
import org.kuali.kfs.module.endow.fixture.SecurityFixture;
import org.kuali.kfs.module.endow.fixture.SecurityReportingGroupFixture;
import org.kuali.kfs.module.endow.util.KEMCalculationRoundingHelper;
import org.kuali.kfs.sys.ConfigureContext;
import org.kuali.kfs.sys.context.KualiTestBase;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.kfs.sys.context.TestUtils;

@ConfigureContext(session = kfs)
public class AccrualProcessingServiceImplTest extends KualiTestBase {

    private Security security;
    private HoldingTaxLotService holdingTaxLotService;
    private KEMService kemService;
    private AccrualProcessingServiceImpl accrualProcessingService;


    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        holdingTaxLotService = SpringContext.getBean(HoldingTaxLotService.class);
        kemService = SpringContext.getBean(KEMService.class);
        accrualProcessingService = (AccrualProcessingServiceImpl) TestUtils.getUnproxiedService("mockAccrualProcessingService");
    }

    /**
     * 
     */
    private void createDataFixtures() {

        // setup dummy data
        RegistrationCode registrationCode = RegistrationCodeFixture.REGISTRATION_CODE_RECORD.createRegistrationCode();
        KEMID kemid = KemIdFixture.OPEN_KEMID_RECORD.createKemidRecord();
        SecurityReportingGroup reportingGroup = SecurityReportingGroupFixture.REPORTING_GROUP.createSecurityReportingGroup();
        EndowmentTransactionCode endowmentTransactionCode = EndowmentTransactionCodeFixture.INCOME_TRANSACTION_CODE.createEndowmentTransactionCode();

        ClassCode classCode = ClassCodeFixture.ACCRUAL_PROCESSING_CLASS_CODE.createClassCodeRecord();

        security = SecurityFixture.ACTIVE_SECURITY.createSecurityRecord();
        security.setClassCode(classCode);

        // need to insert into END_HLDG_TAX_LOT_REBAL_T TABLE because of constraints....
        HoldingTaxLotRebalanceFixture.HOLDING_TAX_LOT_REBALANCE_RECORD_FOR_ACCRUAL_PROC.createHoldingTaxLotRebalanceRecord();

        HoldingTaxLotFixture.HOLDING_TAX_LOT_RECORD_FOR_ACCRUAL_PROC.createHoldingTaxLotRecord();
    }

    /**
     * Validates that processAccrualForAutomatedCashManagement computes the accrual amount correctly.
     */
    public void testProcessAccrualForAutomatedCashManagement() {

        createDataFixtures();
        security.getClassCode().setSecurityAccrualMethod(EndowConstants.AccrualMethod.AUTOMATED_CASH_MANAGEMENT);
        security.getClassCode().refreshReferenceObject("accrualMethod");

        accrualProcessingService.processAccrualForAutomatedCashManagement(security);

        // compute accrual amount= (security rate * holding units)/nr of days in year
        BigDecimal accrual = KEMCalculationRoundingHelper.divide(EndowTestConstants.SECURITY_RATE.multiply(EndowTestConstants.HOLDING_UNITS), new BigDecimal(kemService.getNumberOfDaysInCalendarYear()), 5);

        HoldingTaxLot holdingTaxLot = holdingTaxLotService.getByPrimaryKey(EndowTestConstants.TEST_KEMID, EndowTestConstants.TEST_SEC_ID, EndowTestConstants.TEST_REGISTRATION_CD, 1, EndowConstants.IncomePrincipalIndicator.PRINCIPAL);

        assertTrue(accrual.compareTo(holdingTaxLot.getCurrentAccrual()) == 0);
    }

    /**
     * Validates that processAccrualForDividends computes the accrual amount correctly.
     */
    public void testProcessAccrualForDividends() {

        createDataFixtures();
        security.getClassCode().setSecurityAccrualMethod(EndowConstants.AccrualMethod.DIVIDENDS);
        security.getClassCode().refreshReferenceObject("accrualMethod");

        security.setDividendAmount(EndowTestConstants.SECURITY_DVND_AMT);
        security.setExDividendDate(kemService.getCurrentDate());

        accrualProcessingService.processAccrualForDividends(security);

        // calculate the accrual amound as: holding units * security dividend amount
        BigDecimal accrual = KEMCalculationRoundingHelper.multiply(EndowTestConstants.SECURITY_RATE, EndowTestConstants.SECURITY_DVND_AMT, 5);

        HoldingTaxLot holdingTaxLot = holdingTaxLotService.getByPrimaryKey(EndowTestConstants.TEST_KEMID, EndowTestConstants.TEST_SEC_ID, EndowTestConstants.TEST_REGISTRATION_CD, 1, EndowConstants.IncomePrincipalIndicator.PRINCIPAL);

        assertTrue(accrual.compareTo(holdingTaxLot.getCurrentAccrual()) == 0);
    }

    /**
     * Validates that processAccrualForTimeDeposits computes the accrual amount correctly.
     */
    public void testProcessAccrualForTimeDeposits() {

        createDataFixtures();
        security.getClassCode().setSecurityAccrualMethod(EndowConstants.AccrualMethod.TIME_DEPOSITS);
        security.getClassCode().refreshReferenceObject("accrualMethod");

        accrualProcessingService.processAccrualForTimeDeposits(security);

        // compute accrual amount= (security rate * holding units)/nr of days in year
        BigDecimal accrual = KEMCalculationRoundingHelper.divide(EndowTestConstants.SECURITY_RATE.multiply(EndowTestConstants.HOLDING_UNITS), new BigDecimal(kemService.getNumberOfDaysInCalendarYear()), 5);

        HoldingTaxLot holdingTaxLot = holdingTaxLotService.getByPrimaryKey(EndowTestConstants.TEST_KEMID, EndowTestConstants.TEST_SEC_ID, EndowTestConstants.TEST_REGISTRATION_CD, 1, EndowConstants.IncomePrincipalIndicator.PRINCIPAL);

        assertTrue(accrual.compareTo(holdingTaxLot.getCurrentAccrual()) == 0);
    }

    /**
     * Validates that processAccrualForTreasuryNotesAndBonds computes the accrual amount correctly.
     */
    public void testProcessAccrualForTreasuryNotesAndBonds() {

        createDataFixtures();
        security.getClassCode().setSecurityAccrualMethod(EndowConstants.AccrualMethod.DISCOUNT_BONDS);
        security.getClassCode().refreshReferenceObject("accrualMethod");

        security.setIncomePayFrequency(EndowTestConstants.FREQ_CD_SEMIANUALLY_MARCH_15);
        security.setIncomeNextPayDate(Date.valueOf(EndowTestConstants.SEPT_15_2010_TEST_DATE));

        accrualProcessingService.processAccrualForTreasuryNotesAndBonds(security);

        // compute accrual amount as ((holding units * security rate)/2)/number of days since last income paid date
        BigDecimal accrual = KEMCalculationRoundingHelper.divide(EndowTestConstants.HOLDING_UNITS.multiply(EndowTestConstants.SECURITY_RATE).divide(new BigDecimal(2)), new BigDecimal(EndowTestConstants.NR_OF_DAY_IN_SEMIANNUAL_INTERVAL), 5);

        HoldingTaxLot holdingTaxLot = holdingTaxLotService.getByPrimaryKey(EndowTestConstants.TEST_KEMID, EndowTestConstants.TEST_SEC_ID, EndowTestConstants.TEST_REGISTRATION_CD, 1, EndowConstants.IncomePrincipalIndicator.PRINCIPAL);

        assertTrue(accrual.compareTo(holdingTaxLot.getCurrentAccrual()) == 0);
    }

}
