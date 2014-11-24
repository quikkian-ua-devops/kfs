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
package org.kuali.kfs.module.ld.document.service;

import org.kuali.kfs.module.ld.document.SalaryExpenseTransferDocument;

/**
 * Validates the transfers from an SET document do not conflict with any employee effort report generated by the Effort
 * Certification module.
 */
public interface SalaryTransferPeriodValidationService {

    /**
     * Checks the transfers that will be made by the expense lines do not impact an effort certification document.
     * 
     * @param document - salary expense document to validate
     * @return boolean indicating whether the transfer are valid (true) or not (false)
     */
    public boolean validateTransfers(SalaryExpenseTransferDocument document);

    /**
     * Disapproves the salary expense document due to effort validation errors.
     * 
     * @param document - document to cancel
     */
    public void disapproveSalaryExpenseDocument(SalaryExpenseTransferDocument document) throws Exception;

}
