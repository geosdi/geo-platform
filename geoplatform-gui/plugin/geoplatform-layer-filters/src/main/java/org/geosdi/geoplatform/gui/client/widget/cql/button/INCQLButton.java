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
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.cql.combobox.CQLLayerAttributesComboBox;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.GPSecureStringTextField;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes;
import org.geosdi.geoplatform.gui.model.tree.GPLayerAttributes.GPAttributeKey;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class INCQLButton extends AdvancedCQLButton {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private List<GPSecureStringTextField> fieldList = Lists.<GPSecureStringTextField>newArrayList();
    private GPSecureStringTextField parameter1 = new GPSecureStringTextField();
    private GPSecureStringTextField parameter2 = new GPSecureStringTextField();
    private ContentPanel parameterPanel = new ContentPanel(new FormLayout());
    private Listener keyUplistener;
    private Button insertButton;
    private FormData formData;

    public INCQLButton(TextArea textArea, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(textArea, LayerFiltersModuleConstants.INSTANCE.INCQLButton_buttonText());
        this.treePanel = treePanel;
        super.setTitle(LayerFiltersModuleConstants.INSTANCE.INCQLButton_titleText());
    }

    @Override
    protected void initialize() {
        formData = new FormData("98%");
        final CQLLayerAttributesComboBox attributesComboBox = new CQLLayerAttributesComboBox(this.treePanel);
        attributesComboBox.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.INCQLButton_attributeLabelText());
        this.parameter1.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.INCQLButton_parameter1LabelText());
        this.parameter2.setFieldLabel(LayerFiltersModuleConstants.INSTANCE.INCQLButton_parameter2LabelText());
        this.parameterPanel.setHeaderVisible(Boolean.FALSE);
        this.parameterPanel.add(parameter1, formData);
        this.parameterPanel.add(parameter2, formData);
        this.fieldList.add(parameter1);
        this.fieldList.add(parameter2);
        this.insertButton = new Button(ButtonsConstants.INSTANCE.insertText(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent be) {
                String attribute = null;
                if (attributesComboBox.getValue() != null) {
                    attribute = attributesComboBox.getValue().get(
                            GPAttributeKey.ATTRIBUTE_VALUE.toString()).toString();
                }
                attribute = attribute != null ? attribute + " " : "";
                StringBuilder stringToInsert = new StringBuilder(attribute);
                stringToInsert.append("IN(");
                boolean isFirstIteration = true;
                for (GPSecureStringTextField textField : fieldList) {
                    String parameterValue = textField.getValue();
                    if (isFirstIteration) {
                        stringToInsert.append(parameterValue);
                        isFirstIteration = false;
                    } else {
                        stringToInsert.append(", ");
                        stringToInsert.append(parameterValue);
                    }
                }
                stringToInsert.append(")");
                INCQLButton.super.insertTextIntoFilterArea(stringToInsert.toString());
                window.hide();
            }
        });
        super.window = new GeoPlatformWindow(true) {
            @Override
            public void addComponent() {
                add(new Label(LayerFiltersModuleConstants.INSTANCE.INCQLButton_windowResultLabelText()));
                add(attributesComboBox, formData);
                add(parameterPanel, formData);
                Button addExpressionButton = new Button(LayerFiltersModuleConstants.INSTANCE.
                        INCQLButton_windowAddParameterButtonText());
                addExpressionButton.setIcon(
                        AbstractImagePrototype.create(BasicWidgetResources.ICONS.done()));
                addExpressionButton.setToolTip(LayerFiltersModuleConstants.INSTANCE.
                        INCQLButton_windowExpressionTooltipText());
                addExpressionButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        INCQLButton.this.addParameterFieldRow();
                    }
                });
                add(addExpressionButton);
                add(new Label(LayerFiltersModuleConstants.INSTANCE.INCQLButton_windowResultLabelText()));
                insertButton.disable();
                addButton(insertButton);
            }

            @Override
            public void initSize() {
                super.setSize("370", "350");
            }

            @Override
            public void setWindowProperties() {
                super.setHeadingHtml(LayerFiltersModuleConstants.INSTANCE.INCQLButton_windowHeadingText());
                super.setLayout(new FormLayout());
            }
        };

        this.keyUplistener = new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                for (GPSecureStringTextField textField : fieldList) {
                    String parameterValue = textField.getValue();
                    if (parameterValue == null || parameterValue.isEmpty()) {
                        insertButton.disable();
                        return;
                    }
                }
                insertButton.enable();
            }
        };
        parameter1.addListener(Events.OnKeyUp, keyUplistener);
        parameter2.addListener(Events.OnKeyUp, keyUplistener);
        attributesComboBox.addSelectionChangedListener(new SelectionChangedListener<GPLayerAttributes>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<GPLayerAttributes> se) {
                keyUplistener.handleEvent(se);
            }
        });
        super.initialized = Boolean.TRUE;
    }

    private void addParameterFieldRow() {
        GPSecureStringTextField parameterField = new GPSecureStringTextField();
        parameterField.setFieldLabel(LayerFiltersModuleMessages.INSTANCE.
                INCQLButton_parameterFieldLabelMessage(this.fieldList.size() + 1));
        parameterField.addListener(Events.OnKeyUp, keyUplistener);
        this.fieldList.add(parameterField);
        this.insertButton.disable();
        HorizontalPanel fieldRow = new HorizontalPanel();
        LayoutContainer layoutContainer = new LayoutContainer(new FormLayout());
        layoutContainer.add(parameterField);
        fieldRow.setSpacing(2);
        fieldRow.add(layoutContainer);
        Button deleteRowButton = this.generateDeleteButton(parameterField, fieldRow);
        fieldRow.add(deleteRowButton);
        this.parameterPanel.add(fieldRow, formData);
        this.parameterPanel.layout();
    }

    private Button generateDeleteButton(final GPSecureStringTextField parameterField,
            final HorizontalPanel fieldRow) {
        Button cancelFilterButton = new Button();
        cancelFilterButton.setIcon(
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.delete()));
        cancelFilterButton.setToolTip(LayerFiltersModuleConstants.INSTANCE.INCQLButton_removeParameterLabelText());
        cancelFilterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                fieldList.remove(parameterField);
                parameterPanel.remove(fieldRow);
            }
        });
        return cancelFilterButton;
    }
}
