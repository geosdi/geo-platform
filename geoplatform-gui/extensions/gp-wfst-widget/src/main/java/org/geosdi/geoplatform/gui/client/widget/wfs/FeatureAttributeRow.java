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

import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.handler.IDateSelectedHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFields;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFieldsMap;
import org.geosdi.geoplatform.gui.client.widget.wfs.time.TimeInputWidget;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class FeatureAttributeRow implements IDateSelectedHandler {

    private AttributeDTO attributeDTO;
    private TextField<String> conditionAttributeField;
    private TimeInputWidget timeInputWidget;

    public FeatureAttributeRow(AttributeDTO attributeDTO, GPEventBus bus) {
        this.timeInputWidget = new TimeInputWidget(bus);
        bus.addHandlerToSource(IDateSelectedHandler.TYPE, timeInputWidget,
                this);
        this.attributeDTO = attributeDTO;
        this.buildRow();
    }

    public TextField<String> getConditionAttributeField() {
        return conditionAttributeField;
    }

    public boolean isValid() {
        return this.conditionAttributeField.isValid();
    }

    public void bindAttributeValue() {
        this.attributeDTO.setValue(
                this.conditionAttributeField.getValue());
    }

    public void resetValue() {
        this.conditionAttributeField.setValue(this.attributeDTO.getValue());
    }

    private void buildRow() {
        AttributeCustomFields customFields =
                AttributeCustomFieldsMap.getAttributeCustomFields(
                this.attributeDTO.getType());
        this.conditionAttributeField = new TextField<String>();
        conditionAttributeField.setValidator(customFields.getValidator());
        
        System.out.println(
                "Nillable: " + this.attributeDTO.isNillable() + this.attributeDTO.getName());
        
        conditionAttributeField.setAllowBlank(this.attributeDTO.isNillable());
        conditionAttributeField.setToolTip(
                "Datatype: " + this.attributeDTO.getType());
        
        if (this.attributeDTO.isDateType()) {
            conditionAttributeField.addHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    timeInputWidget.show();
                }

            }, ClickEvent.getType());
        }
        conditionAttributeField.setFieldLabel(this.attributeDTO.getName());
    }

    @Override
    public void dateSelected(String date) {
        this.conditionAttributeField.setValue(date);
    }

}
