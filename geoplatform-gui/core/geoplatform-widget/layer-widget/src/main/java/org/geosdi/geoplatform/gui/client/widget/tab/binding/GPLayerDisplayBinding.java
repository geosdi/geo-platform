/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.tab.binding;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode.GPRasterKeyValue;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.model.GPRasterBean;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPLayerDisplayBinding extends GeoPlatformBindingWidget<GPRasterBean> {

    private Slider slider = new Slider();

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        fp.setFrame(true);
        fp.setLayout(new FlowLayout());

        setSliderProperties();
        
        FieldSet sliderFieldSet = new FieldSet();
        sliderFieldSet.setHeading("Layer Opacity");
        sliderFieldSet.setCollapsible(true);
        
        
        SliderField sliderField = new SliderField(this.slider);
        sliderField.setName(GPRasterKeyValue.OPACITY.toString());
        sliderField.setFieldLabel("Opacity");

        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(5, 5, 5, 5));

        sliderFieldSet.add(sliderField, data);
        
        fp.add(sliderFieldSet);

        return fp;
    }

    @Override
    public void addFieldsBinding() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void setSliderProperties() {
        slider.setWidth(200);
        slider.setMaxValue(100);

        slider.addListener(Events.Change, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                System.out.println("TEST ****************** " + slider.getValue());
            }
        });

        slider.setMessage("{0} opacity");
        slider.addPlugin(this.createSliderPlugin());
        slider.setData("text", "Select opacity for Layer");
    }

    /**
     * Create Plugin for Slider
     * 
     * @return SliderComponent Plugin
     */
    private ComponentPlugin createSliderPlugin() {
        ComponentPlugin plugin = new ComponentPlugin() {

            @Override
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {

                    @Override
                    public void handleEvent(ComponentEvent be) {
                        El elem = be.getComponent().el().findParent(".x-form-element", 3);
                        // should style in external CSS  rather than directly  
                        elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>"
                                + be.getComponent().getData("text") + "</div>"));
                    }
                });
            }
        };

        return plugin;
    }
    
    /**
     * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
     * @email  giuseppe.lascaleia@geosdi.org
     * 
     * Internal Class GPLayerAliasFieldBinding to map bi-directional Binding
     * 
     */
    private class GPRasterOpacityFieldBinding extends GPFieldBinding {

        public GPRasterOpacityFieldBinding(Field field, String property) {
            super(field, property);
        }
        
        @Override
        public void setModelProperty(Object val) {
            ((GPRasterBean) model).setOpacity(((Float) val).floatValue() / 100);
            
        }
        
    }
}
