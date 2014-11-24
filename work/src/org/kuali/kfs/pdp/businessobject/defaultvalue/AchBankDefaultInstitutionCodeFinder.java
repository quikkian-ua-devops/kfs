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
package org.kuali.kfs.pdp.businessobject.defaultvalue;

import org.kuali.kfs.pdp.PdpConstants;
import org.kuali.rice.krad.valuefinder.ValueFinder;

/**
 * Returns the next ACH Account identifier available.
 */
public class AchBankDefaultInstitutionCodeFinder implements ValueFinder {

    /**
     * @see org.kuali.rice.krad.valuefinder.ValueFinder#getValue()
     */
    public String getValue() {
        return PdpConstants.ACH_BANK_DATA_VIEW_CODE_DEFAULT;
    }

}
