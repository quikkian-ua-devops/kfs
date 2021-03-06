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
package org.kuali.kfs.module.tem.document.validation.impl;

import org.kuali.kfs.module.tem.TemPropertyConstants;
import org.kuali.kfs.module.tem.document.TravelDocumentBase;
import org.kuali.kfs.sys.KFSKeyConstants;
import org.kuali.kfs.sys.document.validation.GenericValidation;
import org.kuali.kfs.sys.document.validation.event.AttributedDocumentEvent;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.util.GlobalVariables;

public class TravelAuthAccountingLineBlanketTripTypeValidation extends GenericValidation {

    @Override
    public boolean validate(AttributedDocumentEvent event) {
        boolean rulePassed = true;
        TravelDocumentBase travelDocument = (TravelDocumentBase) event.getDocument();
        if (travelDocument.getSourceAccountingLines() != null || travelDocument.getSourceTotal().isGreaterThan(KualiDecimal.ZERO)) {
            if (travelDocument.isBlanketTravel()) {
                // If the user selects Blanket Trip Type, accounting lines are not required since there will be nothing to encumber.
                // (NOTE: Blanket Travel implies in-state travel)
                GlobalVariables.getMessageMap().putError(TemPropertyConstants.NEW_SOURCE_ACCTG_LINE, KFSKeyConstants.ERROR_CUSTOM, "Accounting Line is not applicable for Blanket travel.");
                rulePassed = false;
            }
        }

        return rulePassed;
    }
}
