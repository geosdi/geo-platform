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
package org.geosdi.geoplatform.gui.client.config;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.user.client.ui.ToggleButton;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.config.annotation.DragFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.EditFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.EraseFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.GetFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.ResetButton;
import org.geosdi.geoplatform.gui.client.config.annotation.ReshapeFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.ResizeFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.RotateFeatureToggleButton;
import org.geosdi.geoplatform.gui.client.config.annotation.SaveButton;
import org.geosdi.geoplatform.gui.client.config.provider.button.ResetButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.button.SaveButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.DragFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.EditFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.EraseFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.GetFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.ReshapeFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.ResizeFeatureToggleButtonProvider;
import org.geosdi.geoplatform.gui.client.config.provider.togglebutton.RotateFeatureToggleButtonProvider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureInjectorButtonProvider extends AbstractGinModule {
    
    @Override
    protected void configure() {
        bind(Button.class).annotatedWith(ResetButton.class).toProvider(
                ResetButtonProvider.class);
        
        bind(Button.class).annotatedWith(SaveButton.class).toProvider(
                SaveButtonProvider.class);
        
        bind(ToggleButton.class).annotatedWith(DragFeatureToggleButton.class).toProvider(
                DragFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(EditFeatureToggleButton.class).toProvider(
                EditFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(GetFeatureToggleButton.class).toProvider(
                GetFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(ReshapeFeatureToggleButton.class).toProvider(
                ReshapeFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(ResizeFeatureToggleButton.class).toProvider(
                ResizeFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(RotateFeatureToggleButton.class).toProvider(
                RotateFeatureToggleButtonProvider.class).in(Singleton.class);
        
        bind(ToggleButton.class).annotatedWith(EraseFeatureToggleButton.class).toProvider(
                EraseFeatureToggleButtonProvider.class).in(Singleton.class);
    }

}
