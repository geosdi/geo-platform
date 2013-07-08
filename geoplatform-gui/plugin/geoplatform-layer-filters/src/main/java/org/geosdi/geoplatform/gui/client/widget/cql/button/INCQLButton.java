/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
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
public class INCQLButton extends AdvancedCQLButton {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private List<TextField<String>> fieldList = Lists.newArrayList();
    private TextField<String> parameter1 = new TextField<String>();
    private TextField<String> parameter2 = new TextField<String>();
    private ContentPanel parameterPanel = new ContentPanel(new FormLayout());
    private Listener keyUplistener;
    private Button insertButton;
    private FormData formData;

    public INCQLButton(TextArea textArea, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(textArea, "IN");
        this.treePanel = treePanel;
        super.setTitle("Tests whether a feature ID value is (not) in a given set. "
                + "ID values are integers or string literals, can be joined whit NOT operator");
    }

    @Override
    protected void initialize() {
        formData = new FormData("98%");
        final CQLLayerAttributesComboBox attributesComboBox = new CQLLayerAttributesComboBox(this.treePanel);
        attributesComboBox.setFieldLabel("Select an attribute (optional)");
        this.parameter1.setFieldLabel("Parameter 1");
        this.parameter2.setFieldLabel("Parameter 2");
        this.parameterPanel.setHeaderVisible(Boolean.FALSE);
        this.parameterPanel.add(parameter1, formData);
        this.parameterPanel.add(parameter2, formData);
        this.fieldList.add(parameter1);
        this.fieldList.add(parameter2);
        this.insertButton = new Button("Insert", new SelectionListener<ButtonEvent>() {
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
                for (TextField<String> textField : fieldList) {
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
                add(new Label("Please, insert the required parameters."));
                add(attributesComboBox, formData);
                add(parameterPanel, formData);
                Button addExpressionButton = new Button("Add Parameter");
                addExpressionButton.setIcon(BasicWidgetResources.ICONS.done());
                addExpressionButton.setToolTip("Adds a new parameter field");
                addExpressionButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        INCQLButton.this.addParameterFieldRow();
                    }
                });
                add(addExpressionButton);
                add(new Label("The result will be: 'Attribute IN (parameter1, ..., parameterN)'"));
                insertButton.disable();
                addButton(insertButton);
            }

            @Override
            public void initSize() {
                super.setSize("370", "350");
            }

            @Override
            public void setWindowProperties() {
                super.setHeading("IN Parameter Selection");
                super.setLayout(new FormLayout());
            }
        };

        this.keyUplistener = new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                for (TextField<String> textField : fieldList) {
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
        TextField<String> parameterField = new TextField<String>();
        parameterField.setFieldLabel("Parameter " + (this.fieldList.size() + 1));
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

    private Button generateDeleteButton(final TextField<String> parameterField,
            final HorizontalPanel fieldRow) {
        Button cancelFilterButton = new Button();
        cancelFilterButton.setIcon(BasicWidgetResources.ICONS.delete());
        cancelFilterButton.setToolTip("Remove this parameter field");
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
