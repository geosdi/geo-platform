/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Singleton
public class FeatureWidget extends GeoPlatformWindow implements IFeatureWidget {
    
    private FeatureMapWidget mapWidget;
    private FeatureAttributesWidget attributesWidget;
    private FeatureStatusBar statusBar;
    private GPEventBus bus;
    
    @Inject
    public FeatureWidget(FeatureMapWidget mapWidget,
            FeatureAttributesWidget attributesWidget,
            FeatureStatusBar statusBar,
            GPEventBus bus) {
        super(true);
        this.mapWidget = mapWidget;
        this.attributesWidget = attributesWidget;
        this.statusBar = statusBar;
        this.bus = bus;
    }
    
    @Override
    public void addComponent() {
        this.addCenterWidget();
        this.addEastWidget();
        this.createStatusBar();
    }
    
    @Override
    public void initSize() {
        super.setSize(1000, 650);
        super.setHeading("GeoPlatform Feature UI");
        super.setIcon(LayerResources.ICONS.vector());
    }
    
    @Override
    public void setWindowProperties() {
        super.setResizable(false);
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
        
        super.setLayout(new BorderLayout());
    }
    
    private void addCenterWidget() {
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(0));
        
        super.add(this.mapWidget, data);
    }
    
    private void addEastWidget() {
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.EAST, 300);
        data.setMargins(new Margins(0));
        
        super.add(this.attributesWidget, data);
    }
    
    private void createStatusBar() {
        super.setButtonAlign(Style.HorizontalAlignment.RIGHT);
        super.getButtonBar().add(this.statusBar);
        
        Button close = new Button("Close", BasicWidgetResources.ICONS.cancel(),
                                  new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });
        super.addButton(close);
    }
    
    @Override
    public void reset() {
        this.mapWidget.reset();
        this.attributesWidget.reset();
        this.statusBar.reset();
    }
    
    @Override
    public void showWidget(GPLayerBean layer,
            LayerSchemaDTO schema) {
        if (layer instanceof GPVectorBean) {
            ((GPVectorBean) layer).setFeatureNameSpace(
                    schema.getTargetNamespace());
            ((GPVectorBean) layer).setGeometryName(
                    schema.getGeometry().getName());
        }
        
        this.mapWidget.bind(layer, schema);
        
        super.show();
    }
}
