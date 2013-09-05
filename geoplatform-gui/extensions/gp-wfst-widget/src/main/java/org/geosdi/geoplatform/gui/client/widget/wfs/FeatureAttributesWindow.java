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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.google.common.collect.Lists;
import java.util.List;
import javax.inject.Inject;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.LayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class FeatureAttributesWindow extends GeoPlatformWindow {

    @Inject
    private LayerSchemaBinder layerSchemaBinder;
    private FormPanel attributesFormPanel;
    private GPEventBus bus;
    private List<FeatureAttributeRow> featureAttributeRowList;

    @Inject
    public FeatureAttributesWindow(GPEventBus bus) {
        super(Boolean.TRUE);
        this.bus = bus;
    }

    @Override
    public void reset() {
        super.reset();
        for (FeatureAttributeRow featureAttributeRow : GeoPlatformUtils.safeList(featureAttributeRowList)) {
            featureAttributeRow.resetValue();
        }
    }

    public void bindValues() {
        for (FeatureAttributeRow featureAttributeRow : GeoPlatformUtils.safeList(featureAttributeRowList)) {
            featureAttributeRow.bindAttributeValue();
        }
    }

    @Override
    public void addComponent() {
        this.attributesFormPanel = new FormPanel();
        this.attributesFormPanel.setHeaderVisible(false);
        super.add(this.attributesFormPanel);
        Button saveButton = new Button(ButtonsConstants.INSTANCE.saveText(),
                BasicWidgetResources.ICONS.save(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                bindValues();
                //And now you can add save logic using featureAttributeRowList elements
            }
        });
        saveButton.disable();
        FormButtonBinding formButtonBinding = new FormButtonBinding(attributesFormPanel);
        formButtonBinding.addButton(saveButton);
        Button resetButton = new Button(ButtonsConstants.INSTANCE.resetText(),
                BasicWidgetResources.ICONS.reset(), new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                FeatureAttributesWindow.this.reset();
            }
        });
        super.addButton(saveButton);
    }

    @Override
    public void initSize() {
        super.setSize(346, 380);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(WFSTWidgetConstants.INSTANCE.
                FeatureAttributesWindow_headingText());
        super.setScrollMode(Style.Scroll.AUTOY);
    }

    @Override
    protected void beforeRender() {
        super.beforeRender();
        this.attributesFormPanel.removeAll();
        this.buildFields();
    }

    private void buildFields() {
        LayerSchemaDTO layerSchemaDTO = this.layerSchemaBinder.getLayerSchemaDTO();
        if (layerSchemaDTO != null) {
            this.featureAttributeRowList = Lists.<FeatureAttributeRow>newArrayListWithCapacity(
                    layerSchemaDTO.getAttributes().size());
            for (AttributeDTO attribute : GeoPlatformUtils.safeList(layerSchemaDTO.getAttributes())) {
                FeatureAttributeRow featureAttributeRow = new FeatureAttributeRow(attribute, this.bus);
                this.featureAttributeRowList.add(featureAttributeRow);
                this.attributesFormPanel.add(featureAttributeRow.getConditionAttributeField());
            }
        }
    }
}
