/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kfs.sys.document.web;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.kuali.kfs.sys.businessobject.AccountingLine;
import org.kuali.kfs.sys.document.datadictionary.AccountingLineViewOverrideFieldDefinition;
import org.kuali.kfs.sys.document.service.AccountingLineFieldRenderingTransformation;
import org.kuali.kfs.sys.document.web.renderers.OverrideFieldRenderer;
import org.kuali.rice.kns.util.FieldUtils;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.web.ui.Field;

/**
 * An override field to be displayed for a field
 */
public class AccountingLineViewOverrideField implements RenderableElement {
    private AccountingLineViewField parent;
    private AccountingLineViewOverrideFieldDefinition definition;
    private Field overrideField;
    private int tabIndex;
    private int arbitrarilyHighIndex;
    
    /**
     * Constructs a AccountingLineViewOverrideField
     * @param field the owning accounting line view field
     * @param accountingLineClass the class of the accounting line we're rendering
     */
    public AccountingLineViewOverrideField(AccountingLineViewField field, AccountingLineViewOverrideFieldDefinition definition, Class<? extends AccountingLine> accountingLineClass) {
        this.parent = field;
        this.definition = definition;
        overrideField = FieldUtils.getPropertyField(accountingLineClass, definition.getName(), false);
    }

    /**
     * Adds our override field (though not our override needed field - we'll let Struts handle the value population on that
     * @see org.kuali.kfs.sys.document.web.RenderableElement#appendFields(java.util.List)
     */
    public void appendFields(List<Field> fields) {
        fields.add(overrideField);
    }

    /**
     * This is not an action block
     * @see org.kuali.kfs.sys.document.web.RenderableElement#isActionBlock()
     */
    public boolean isActionBlock() {
        return false;
    }

    /**
     * Empty if our parent AccountingLineViewField is empty
     * @see org.kuali.kfs.sys.document.web.RenderableElement#isEmpty()
     */
    public boolean isEmpty() {
        return parent.isEmpty();
    }

    /**
     * Hidden if our parent AccountingLineViewField is hidden
     * @see org.kuali.kfs.sys.document.web.RenderableElement#isHidden()
     */
    public boolean isHidden() {
        return parent.isHidden();
    }

    /**
     * 
     * @see org.kuali.kfs.sys.document.web.RenderableElement#populateWithTabIndexIfRequested(int[], int)
     */
    public void populateWithTabIndexIfRequested(int[] passIndexes, int reallyHighIndex) {
        tabIndex = passIndexes[0];
        arbitrarilyHighIndex = reallyHighIndex;
    }

    /**
     * 
     * @see org.kuali.kfs.sys.document.web.RenderableElement#renderElement(javax.servlet.jsp.PageContext, javax.servlet.jsp.tagext.Tag, org.kuali.kfs.sys.document.web.AccountingLineRenderingContext)
     */
    public void renderElement(PageContext pageContext, Tag parentTag, AccountingLineRenderingContext renderingContext) throws JspException {
        OverrideFieldRenderer renderer = new OverrideFieldRenderer();
        renderer.setField(overrideField);
        renderer.setArbitrarilyHighTabIndex(arbitrarilyHighIndex);
        renderer.setTabIndex(tabIndex);
        renderer.setReadOnly(parent.isReadOnly());
        renderer.setOverrideNeededValue(getOverrideNeededValue(renderingContext.getAccountingLine()));
        renderer.setAccountingLine(renderingContext.getAccountingLine());
        renderer.render(pageContext, parentTag);
        renderer.clear();
    }
    
    /**
     * Retrieves the value of the override needed value associated with the override field
     * @param accountingLine the accounting line to get the override needed value from
     * @return a "Yes" if the override needed value is true, "No" if it is false
     */
    protected String getOverrideNeededValue(AccountingLine accountingLine) {
        String overrideNeededPropertyName = overrideField.getPropertyName()+"Needed";
        Boolean value = (Boolean)ObjectUtils.getPropertyValue(accountingLine, overrideNeededPropertyName);
        return value != null && value.booleanValue() ? "Yes" : "No";
    }

    /**
     * Runs a field transformation against all the overrides encapsulated within this field
     * @param fieldTransformation the field transformation which will utterly change our fields
     * @param accountingLine the accounting line being rendered
     * @param editModes the current document edit modes
     * @param unconvertedValues a Map of unconvertedValues
     */
    public void transformField(AccountingLineFieldRenderingTransformation fieldTransformation, AccountingLine accountingLine, Map editModes, Map unconvertedValues) {
        fieldTransformation.transformField(accountingLine, overrideField, definition, editModes, unconvertedValues);
    }
    
    /**
     * Sets the accounting Line Property
     * @param propertyPrefix the accounting line property
     */
    public void setAccountingLineProperty(String propertyPrefix) {
        overrideField.setPropertyPrefix(propertyPrefix);
    }
}
