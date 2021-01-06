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
package org.geosdi.geoplatform.gui.client.widget.cql.button;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLayerAttributesComboBox;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class BBOXCQLButton extends AdvancedCQLButton {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private NumberField minXField = new NumberField();
    private NumberField maxXField = new NumberField();
    private NumberField minYField = new NumberField();
    private NumberField maxYField = new NumberField();
    private FormData formData;

    public BBOXCQLButton(TextArea textArea, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(textArea, LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_buttonText());
        this.treePanel = treePanel;
        super.setTitle(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_titleText());
    }

    @Override
    protected void initialize() {
        formData = new FormData("98%");
        final CQLLayerAttributesComboBox attributesComboBox = new CQLLayerAttributesComboBox(this.treePanel);
        attributesComboBox.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_attributeComboBoxLabelText());
        this.minXField.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_minXLabelText());
        this.maxXField.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_maxXLabelText());
        this.minYField.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_minYLabelText());
        this.maxYField.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_maxYLabelText());
        final Button insertButton = new Button(ButtonsConstants.INSTANCE.insertText(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent be) {
                String theGeom = attributesComboBox.getValue().get(
                        GPAttributeKey.ATTRIBUTE_VALUE.toString()).toString();
                BBOXCQLButton.super.insertTextIntoFilterArea("BBOX(" + theGeom
                        + ", " + minXField.getValue() + ", " + maxXField.getValue()
                        + ", " + minYField.getValue() + ", " + maxYField.getValue() + ")");
                window.hide();
            }
        });
        super.window = new GeoPlatformWindow(true) {
            @Override
            public void addComponent() {
                add(new Label(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_windowInsertParametersLabelText()));
                add(attributesComboBox, formData);
                add(minXField, formData);
                add(minYField, formData);
                add(maxXField, formData);
                add(maxYField, formData);
                add(new Label(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_windowResultLabelText()));
                insertButton.disable();
                addButton(insertButton);
            }

            @Override
            public void initSize() {
                super.setSize("300", "280");
            }

            @Override
            public void setWindowProperties() {
                super.setHeadingHtml(LayerFiltersModuleConstants.INSTANCE.BBOXCQLButton_windowHeadingText());
                super.setLayout(new FormLayout());
            }
        };

        final Listener listener = new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                String theGeomValue = attributesComboBox.getValue().get(
                        GPAttributeKey.ATTRIBUTE_VALUE.toString()).toString();
                Number minXValue = minXField.getValue();
                Number maxXValue = maxXField.getValue();
                Number maxYValue = maxYField.getValue();
                Number minYValue = minYField.getValue();
                if (minXValue == null || minXValue.toString().isEmpty()
                        || maxXValue == null || maxXValue.toString().isEmpty()
                        || maxYValue == null || maxYValue.toString().isEmpty()
                        || minYValue == null || minYValue.toString().isEmpty()
                        || theGeomValue == null || theGeomValue.isEmpty()) {
                    insertButton.disable();
                } else {
                    insertButton.enable();
                }
            }
        };
        minXField.addListener(Events.OnKeyUp, listener);
        maxXField.addListener(Events.OnKeyUp, listener);
        maxYField.addListener(Events.OnKeyUp, listener);
        minYField.addListener(Events.OnKeyUp, listener);
        attributesComboBox.addSelectionChangedListener(new SelectionChangedListener<GPLayerAttributes>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPLayerAttributes> se) {
                listener.handleEvent(se);
            }
        });
        super.initialized = Boolean.TRUE;
    }
}
