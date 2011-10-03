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

import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SliderEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SliderField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode.GPRasterKeyValue;
import org.geosdi.geoplatform.gui.client.model.memento.save.GPMementoSaveCache;
import org.geosdi.geoplatform.gui.client.widget.binding.GeoPlatformBindingWidget;
import org.geosdi.geoplatform.gui.client.widget.binding.field.GPSliderField;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;
import org.geosdi.geoplatform.gui.client.widget.tab.DisplayLayersTabItem;
import org.geosdi.geoplatform.gui.impl.map.event.OpacityLayerMapEvent;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPLayerDisplayBinding extends GeoPlatformBindingWidget<GPRasterBean> {

    private Slider slider;
    private GPSliderField sliderField;
    private GPRasterOpacityFieldBinding opacityFieldBinding;
    private OpacityLayerMapEvent opacityEvent = new OpacityLayerMapEvent();

    @Override
    public FormPanel createFormPanel() {
        FormPanel fp = new FormPanel();
        fp.setHeaderVisible(false);
        fp.setFrame(true);
        fp.setLayout(new FlowLayout());

        setSliderProperties();

        final FieldSet opacityFieldSet = new FieldSet();
        opacityFieldSet.setHeading("Layer Opacity");
        opacityFieldSet.setCollapsible(true);

        opacityFieldSet.addListener(Events.Collapse, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                ((DisplayLayersTabItem) opacityFieldSet.getParent().getParent()).updateWindowSize();
            }
        });

        sliderField = new GPSliderField(this.slider);
        sliderField.setName(GPRasterKeyValue.OPACITY.toString());

        sliderField.removeAllListeners();

        opacityFieldSet.add(sliderField);

        fp.add(opacityFieldSet);

        return fp;
    }

    @Override
    public void addFieldsBinding() {
        this.opacityFieldBinding = new GPRasterOpacityFieldBinding(sliderField,
                GPRasterKeyValue.OPACITY.name());
        this.formBinding.addFieldBinding(this.opacityFieldBinding);
    }

    /**
     * Create and set Slider Properties
     * 
     */
    private void setSliderProperties() {
        this.slider = new Slider();
        slider.setMaxValue(100);

        slider.addPlugin(createSliderPlugin());

        slider.setMessage("{0}% opacity");

        slider.addListener(Events.Change, new Listener<SliderEvent>() {

            @Override
            public void handleEvent(SliderEvent be) {
                opacityFieldBinding.setModelProperty(be.getNewValue());
            }
        });

        slider.setData("text", "Choose Opacity Value for Layer");
    }

    /**
     * Create Plugin for Slider
     * 
     * @return ComponentPlugin
     */
    private ComponentPlugin createSliderPlugin() {
        ComponentPlugin plugin = new ComponentPlugin() {

            @Override
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {

                    @Override
                    public void handleEvent(ComponentEvent be) {
                        El elem = sliderField.el();
                        // should style in external CSS  rather than directly  
                        elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>"
                                + slider.getData("text") + "</div>"));
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

        public GPRasterOpacityFieldBinding(SliderField field, String property) {
            super(field, property);
        }

        @Override//From view to model
        public void setModelProperty(Object val) {
            //Copying the value on memento before changes
            GPMementoSaveCache.getInstance().copyOriginalLayerProperties((GPLayerTreeModel)GPLayerDisplayBinding.this.getModel());
            ((GPRasterBean) GPLayerDisplayBinding.this.getModel()).setOpacity(((Integer) val).floatValue() / 100);
            opacityEvent.setLayerBean((GPRasterBean) GPLayerDisplayBinding.this.getModel());
            GPHandlerManager.fireEvent(opacityEvent);
        }

        /**
         * Updates the field's value and original value with the model value. Updating
         * the original value will reset the field to a non-dirty state.
         * 
         * @param updateOriginalValue true to update the original value
         */
        @Override//From model to view
        public void updateField(boolean updateOriginalValue) {      
            Float opacity = new Float(((GPRasterBean) GPLayerDisplayBinding.this.getModel()).getOpacity() * 100);
            ((SliderField) field).setValue(opacity.intValue());
        }
    }
}
