/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public interface LayerFiltersModuleConstants extends Constants {

    public LayerFiltersModuleConstants INSTANCE = GWT.create(LayerFiltersModuleConstants.class);

    @DefaultStringValue("CQL ERROR")
    String CQLErrorText();
    
    @DefaultStringValue("CQL Success")
    String CQLSuccessText();

    /**
     * start CQLFilterWidget
     */
    @DefaultStringValue("CQL FILTER EDITOR")
    String CQLFilterWidget_headingText();

    /**
     * start CQLFilterBasicTab
     */
    @DefaultStringValue("Advanced Editing")
    String CQLFilterTabWidget_advancedTabTitleText();

    @DefaultStringValue("Basic Editing")
    String CQLFilterTabWidget_filterBasicTabTitleText();

    /**
     * start CQLFilterBasicTab
     */
    @DefaultStringValue("Add expression")
    String CQLFilterBasicTab_addExpressionText();

    @DefaultStringValue("Add a new expression line")
    String CQLFilterBasicTab_addExpressionTooltipText();

    @DefaultStringValue("Remove this filter line")
    String CQLFilterBasicTab_cancelFilterTooltipText();

    /**
     * start CQLFilterAdvancedTab
     */
    @DefaultStringValue("Always exclude features to which this filter is applied")
    String CQLFilterAdvancedTab_excludeOperatorTooltipText();

    @DefaultStringValue("Always include features to which this filter is applied")
    String CQLFilterAdvancedTab_includeOperatorTooltipText();

    @DefaultStringValue("Applied to attributes: "
            + "Tests whether a featuretype does not have a given attribute")
    String CQLFilterAdvancedTab_doesNotExistOperatorTooltipText();

    @DefaultStringValue("Applied to attributes: Tests whether a featuretype does have a given attribute")
    String CQLFilterAdvancedTab_existOperatorTooltipText();

    @DefaultStringValue("Tests whether a value is (non-)null, can be joined whit NOT operator")
    String CQLFilterAdvancedTab_isNullOperatorTooltipText();

    @DefaultStringValue("Negation of a condition")
    String CQLFilterAdvancedTab_notOperatorTooltipText();

    @DefaultStringValue("The % character is a wild-card for any number of characters")
    String CQLFilterAdvancedTab_jollyCharacterTooltipText();

    @DefaultStringValue("Use apex before a string element")
    String CQLFilterAdvancedTab_apexElementTooltipText();

    @DefaultStringValue("Select Operator")
    String CQLFilterAdvancedTab_operatorEmptyText();

    @DefaultStringValue("Select Logical Operator")
    String CQLFilterAdvancedTab_logicalOperatorEmptyText();

    @DefaultStringValue("Select Layer Attribute")
    String CQLFilterAdvancedTab_attributeEmptyText();

    /**
     * start CQLLogicalOperatorComboBox
     */
    @DefaultStringValue("CQL Operator")
    String CQLLogicalOperatorComboBox_fieldLabelText();

    @DefaultStringValue("Loading data...")
    String CQLLogicalOperatorComboBox_loadingText();

    /**
     * start CQLLayerAttributesComboBox
     */
    @DefaultStringValue("Label Values")
    String CQLLayerAttributesComboBox_fieldLabelText();

    @DefaultStringValue("Loading data...")
    String CQLLayerAttributesComboBox_loadingText();

    /**
     * start TimeCQLButton
     */
    @DefaultStringValue("Enter the time")
    String TimeCQLButton_timeFieldText();

    @DefaultStringValue("Enter the date")
    String TimeCQLButton_dateFieldText();

    @DefaultStringValue("The result will be: 'Temporal Predicate time'")
    String TimeCQLButton_windowResultLabelText();

    @DefaultStringValue("Please, select the required parameters.")
    String TimeCQLButton_windowSelectLabelText();

    @DefaultStringValue("Time Filter Composition")
    String TimeCQLButton_windowHeadingText();

    @DefaultStringValue("Before")
    String TimeCQLButton_beforeRadioGroupLabelText();

    @DefaultStringValue("After")
    String TimeCQLButton_afterRadioGroupLabelText();

    @DefaultStringValue("During")
    String TimeCQLButton_duringRadioGroupLabelText();

    @DefaultStringValue("Before or During")
    String TimeCQLButton_beforeOrDuringRadioGroupLabelText();

    @DefaultStringValue("During or After")
    String TimeCQLButton_duringOrAfterRadioGroupLabelText();

    @DefaultStringValue("Select Temporal Predicate")
    String TimeCQLButton_radioGroupFieldLabelText();

    @DefaultStringValue("Temporal Predicate")
    String TimeCQLButton_radioGroupText();

    @DefaultStringValue("Time Filter")
    String TimeCQLButton_buttonText();

    @DefaultStringValue("Temporal predicates specify the relationship of a time-valued"
            + " expression to a time or time period")
    String TimeCQLButton_titleText();

    /**
     * start INCQLButton
     */
    @DefaultStringValue("Remove this parameter field")
    String INCQLButton_removeParameterLabelText();

    @DefaultStringValue("Select an attribute (optional)")
    String INCQLButton_attributeLabelText();

    @DefaultStringValue("Parameter 2")
    String INCQLButton_parameter2LabelText();

    @DefaultStringValue("Parameter 1")
    String INCQLButton_parameter1LabelText();

    @DefaultStringValue("Tests whether a feature ID value is (not) in a given set. "
            + "ID values are integers or string literals, can be joined whit NOT operator")
    String INCQLButton_titleText();

    @DefaultStringValue("IN")
    String INCQLButton_buttonText();

    @DefaultStringValue("The result will be: 'Attribute IN (parameter1, ..., parameterN)'")
    String INCQLButton_windowResultLabelText();

    @DefaultStringValue("Please, insert the required parameters.")
    String INCQLButton_windowInsertLabelText();

    @DefaultStringValue("Add parameter")
    String INCQLButton_windowAddParameterButtonText();

    @DefaultStringValue("Adds a new parameter field")
    String INCQLButton_windowExpressionTooltipText();

    @DefaultStringValue("IN Parameter Selection")
    String INCQLButton_windowHeadingText();

    /**
     * start BetweenCQLButton
     */
    @DefaultStringValue("The result will be: 'Comparison parameter BETWEEN First Parameter AND Second Parameter'")
    String BetweenCQLButton_windowResultLabelText();

    @DefaultStringValue("Please, insert the required parameters.")
    String BetweenCQLButton_windowInsertLabelText();

    @DefaultStringValue("Between Parameter Selection")
    String BetweenCQLButton_windowHeadingText();

    @DefaultStringValue("First Parameter")
    String BetweenCQLButton_firstParameterLabelText();

    @DefaultStringValue("Secoond Parameter")
    String BetweenCQLButton_secondParameterLabelText();

    @DefaultStringValue("Comparison Parameter")
    String BetweenCQLButton_comparisonLabelText();

    @DefaultStringValue("BETWEEN")
    String BetweenCQLButton_buttonText();

    @DefaultStringValue("Tests whether a value lies in or outside a range (inclusive), "
            + "can be joined whit NOT operator")
    String BetweenCQLButton_titleText();

    /**
     * start BBOXCQLButton
     */
    @DefaultStringValue("BBOX Parameter Selection")
    String BBOXCQLButton_windowHeadingText();

    @DefaultStringValue("The result will be: 'BBOX(Geometry Parameter, minX, minY, maxX, maxY)'")
    String BBOXCQLButton_windowResultLabelText();

    @DefaultStringValue("Please, insert the required parameters.")
    String BBOXCQLButton_windowInsertParametersLabelText();

    @DefaultStringValue("Min Y coordinate")
    String BBOXCQLButton_minYLabelText();

    @DefaultStringValue("Max Y coordinate")
    String BBOXCQLButton_maxYLabelText();

    @DefaultStringValue("Min X coordinate")
    String BBOXCQLButton_minXLabelText();

    @DefaultStringValue("Max X coordinate")
    String BBOXCQLButton_maxXLabelText();

    @DefaultStringValue("Select the geometry attribute")
    String BBOXCQLButton_attributeComboBoxLabelText();

    @DefaultStringValue("BBOX")
    String BBOXCQLButton_buttonText();

    @DefaultStringValue("Tests whether a geometry intersects a bounding box specified "
            + "by its minimum and maximum X and Y values. The optional CRS is a string containing an SRS code "
            + "(For example, 'EPSG:4326'. The default is to use the CRS of the queried layer)")
    String BBOXCQLButton_titleText();

    @DefaultStringValue("Unique Value")
    String CQLFilterAdvancedTab_uniqueOperatorTooltipText();

    @DefaultStringValue("Guide to create CQL Filter")
    String CQLFilterAdvancedTab_cqlGuideTooltipText();

    @DefaultStringValue("CQL Filter Guide")
    String CQLFilterAdvancedTab_cqlGuideHeaderText();
}
