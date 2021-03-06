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
package org.kuali.kfs.module.ec.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kuali.kfs.integration.ld.LaborLedgerBalance;
import org.kuali.kfs.sys.ObjectUtil;
import org.kuali.rice.core.api.util.type.KualiDecimal;

/**
 * To provide a set of utilities to consolidate/group the specified ledger balances and build a returning ledger balance Map.
 */
public class LedgerBalanceConsolidationHelper {

    /**
     * consolidate the amount of the given ledger balance into the balance with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains the consolidated balance records. Its key can be the combined string value
     *        of the given consolidation keys.
     * @param ledgerBalance the given ledger balance to be consolidated
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void consolidateLedgerBalances(Map<String, LaborLedgerBalance> ledgerBalanceMap, LaborLedgerBalance ledgerBalance, List<String> consolidationKeys) {
        String consolidationKeyFieldsAsString = ObjectUtil.concatPropertyAsString(ledgerBalance, consolidationKeys);

        consolidateLedgerBalances(ledgerBalanceMap, ledgerBalance, consolidationKeyFieldsAsString);
    }

    /**
     * consolidate the amount of the given ledger balance into the balance with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains the consolidated balance records. Its key can be the combined string value
     *        of the given consolidation keys.
     * @param ledgerBalance the given ledger balance to be consolidated
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void consolidateLedgerBalances(Map<String, LaborLedgerBalance> ledgerBalanceMap, LaborLedgerBalance ledgerBalance, String consolidationKeyFieldsAsString) {
        if (ledgerBalanceMap.containsKey(consolidationKeyFieldsAsString)) {
            LaborLedgerBalance existingBalance = ledgerBalanceMap.get(consolidationKeyFieldsAsString);
            addLedgerBalanceAmounts(existingBalance, ledgerBalance);
        }
        else {
            ledgerBalanceMap.put(consolidationKeyFieldsAsString, ledgerBalance);
        }
    }

    /**
     * consolidate the amounts of the given ledger balances into the balances with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains the consolidated balance records. Its key can be the combined string value
     *        of the given consolidation keys.
     * @param ledgerBalances the given ledger balances to be consolidated
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void consolidateLedgerBalances(Map<String, LaborLedgerBalance> ledgerBalanceMap, Collection<LaborLedgerBalance> ledgerBalances, List<String> consolidationKeys) {
        for (LaborLedgerBalance balance : ledgerBalances) {
            consolidateLedgerBalances(ledgerBalanceMap, balance, consolidationKeys);
        }
    }

    /**
     * group the given ledger balance into the list of balances with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains a set of ledger balance lists. Its key can be the combined string value of
     *        the given consolidation keys.
     * @param ledgerBalance the given ledger balance to be grouped
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void groupLedgerBalancesByKeys(Map<String, List<LaborLedgerBalance>> ledgerBalanceMap, LaborLedgerBalance ledgerBalance, List<String> consolidationKeys) {
        String consolidationKeyFieldsAsString = ObjectUtil.concatPropertyAsString(ledgerBalance, consolidationKeys);
        groupLedgerBalancesByKeys(ledgerBalanceMap, ledgerBalance, consolidationKeyFieldsAsString);
    }

    /**
     * group the given ledger balance into the list of balances with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains a set of ledger balance lists. Its key can be the combined string value of
     *        the given consolidation keys.
     * @param ledgerBalance the given ledger balance to be grouped
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void groupLedgerBalancesByKeys(Map<String, List<LaborLedgerBalance>> ledgerBalanceMap, LaborLedgerBalance ledgerBalance, String consolidationKeyFieldsAsString) {
        if (ledgerBalanceMap.containsKey(consolidationKeyFieldsAsString)) {
            List<LaborLedgerBalance> balanceList = ledgerBalanceMap.get(consolidationKeyFieldsAsString);
            balanceList.add(ledgerBalance);
        }
        else {
            List<LaborLedgerBalance> balanceList = new ArrayList<LaborLedgerBalance>();
            balanceList.add(ledgerBalance);
            ledgerBalanceMap.put(consolidationKeyFieldsAsString, balanceList);
        }
    }

    /**
     * group the given ledger balances into the lists of balances with the same values of specified key fields
     *
     * @param ledgerBalanceMap the hash map that contains a set of ledger balance lists. Its key can be the combined string value of
     *        the given consolidation keys.
     * @param ledgerBalance the given ledger balances to be grouped
     * @param consolidationKeys the given key field names used to build the keys of ledgerBalanceMap
     */
    public static void groupLedgerBalancesByKeys(Map<String, List<LaborLedgerBalance>> ledgerBalanceMap, Collection<LaborLedgerBalance> ledgerBalances, List<String> consolidationKeys) {
        for (LaborLedgerBalance balance : ledgerBalances) {
            groupLedgerBalancesByKeys(ledgerBalanceMap, balance, consolidationKeys);
        }
    }

    /**
     * add the monthly amounts of the second ledger balance with those of the first one
     *
     * @param ledgerBalance the given ledger balance, which holds the summerized monthly amounts
     * @param anotherLedgerBalance the given ledger balance, which contributes monthly amounts
     */
    public static void addLedgerBalanceAmounts(LaborLedgerBalance ledgerBalance, LaborLedgerBalance anotherLedgerBalance) {
        if (anotherLedgerBalance == null) {
            return;
        }

        if (ledgerBalance == null) {
            ledgerBalance = anotherLedgerBalance;
            return;
        }

        for (AccountingPeriodMonth period : AccountingPeriodMonth.values()) {
            KualiDecimal amount = anotherLedgerBalance.getAmountByPeriod(period.periodCode);
            ledgerBalance.addAmount(period.periodCode, amount);
        }
    }

    /**
     * @deprecated Use calculateTotalAmountWithinReportPeriod(LaborLedgerBalance,Map<Integer,Set<String>>) instead. Boolean allFiscalYears was dropped because EffortCertificationExtractServiceImpl.getConsolidationKeys allows a consolidation key for fiscal year to be added which is a solution that doesn't suffer from the bugs as the allFiscalYears solution
     * @since deprecated as of 5.2.2
     * Summarizes the balance amounts of a given ledger balance within the specified report periods
     *
     * @param ledgerBalance the given labor ledger balance
     * @param reportPeriods the given report periods
     * @param allFiscalYears allows for total amount calculation across all fiscal years. This is useful if the ledger balances have already been consolidated which is the case once we build the document
     * @return the total amounts of the given balance within the specified report periods
     */
    @Deprecated
    public static KualiDecimal calculateTotalAmountWithinReportPeriod(LaborLedgerBalance ledgerBalance, Map<Integer, Set<String>> reportPeriods, boolean allFiscalYears) {
        return calculateTotalAmountWithinReportPeriod(ledgerBalance, reportPeriods);
    }

    /**
     * Summarizes the balance amounts of a given ledger balance within the specified report periods
     *
     * @param ledgerBalance the given labor ledger balance
     * @param reportPeriods the given report periods
     * @return the total amounts of the given balance within the specified report periods
     */
    public static KualiDecimal calculateTotalAmountWithinReportPeriod(LaborLedgerBalance ledgerBalance, Map<Integer, Set<String>> reportPeriods) {
        Integer fiscalYear = ledgerBalance.getUniversityFiscalYear();
        KualiDecimal totalAmount = KualiDecimal.ZERO;

        Set<String> periodCodes = reportPeriods.get(fiscalYear);
        for (String period : periodCodes) {
            totalAmount = totalAmount.add(ledgerBalance.getAmountByPeriod(period));
        }
        return totalAmount;
    }

    /**
     * @deprecated Use calculateTotalAmountWithinReportPeriod(Collection<LaborLedgerBalance>,Map<Integer, Set<String>>) instead. Boolean allFiscalYears was dropped because EffortCertificationExtractServiceImpl.getConsolidationKeys allows a consolidation key for fiscal year to be added which is a solution that doesn't suffer from the bugs as the allFiscalYears solution
     * @since deprecated as of 5.2.2
     * Summarizes the balance amounts of the given ledger balances within the specified report periods
     *
     * @param ledgerBalance the given labor ledger balances
     * @param reportPeriods the given report periods
     * @param allFiscalYears allows for total amount calculation across all fiscal years. This is useful if the ledger balances have already been consolidated
     * @return the total amounts of the given balances within the specified report periods
     */
    @Deprecated
    public static KualiDecimal calculateTotalAmountWithinReportPeriod(Collection<LaborLedgerBalance> ledgerBalances, Map<Integer, Set<String>> reportPeriods, boolean allFiscalYears) {
        return calculateTotalAmountWithinReportPeriod(ledgerBalances, reportPeriods);
    }

    /**
     * Summarizes the balance amounts of the given ledger balances within the specified report periods
     *
     * @param ledgerBalance the given labor ledger balances
     * @param reportPeriods the given report periods
     * @return the total amounts of the given balances within the specified report periods
     */
    public static KualiDecimal calculateTotalAmountWithinReportPeriod(Collection<LaborLedgerBalance> ledgerBalances, Map<Integer, Set<String>> reportPeriods) {
        KualiDecimal totalAmount = KualiDecimal.ZERO;

        for (LaborLedgerBalance ledgerBalance : ledgerBalances) {
            KualiDecimal totalAmountForOneBalance = calculateTotalAmountWithinReportPeriod(ledgerBalance, reportPeriods);
            totalAmount = totalAmount.add(totalAmountForOneBalance);
        }
        return totalAmount;
    }
}
