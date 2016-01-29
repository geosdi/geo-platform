/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2016 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.config;

import com.google.gwt.inject.client.AbstractGinModule;
import org.geosdi.geoplatform.gui.client.model.binder.FeatureIdBinder;
import org.geosdi.geoplatform.gui.client.model.binder.IFeatureIdBinder;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.binder.LayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureMapWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.IFeatureMapWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.binding.grid.WFSFeatureGridBinding;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.AttributeCustomFieldsMap;
import org.geosdi.geoplatform.gui.client.widget.wfs.builder.feature.FeatureAttributesWindowBuilder;
import org.geosdi.geoplatform.gui.client.widget.wfs.initializer.FeatureMapInitializer;
import org.geosdi.geoplatform.gui.client.widget.wfs.initializer.IFeatureMapInitializer;
import org.geosdi.geoplatform.gui.client.widget.wfs.layout.responsibility.FeatureAttributesLayoutHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.layout.responsibility.FeatureSelectionLayoutHandler;
import org.geosdi.geoplatform.gui.factory.map.DefaultMapFactory;
import org.geosdi.geoplatform.gui.factory.map.GeoPlatformMapFactory;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;

import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureAttributesWidget;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureInjectorModule extends AbstractGinModule {
    
    @Override
    protected void configure() {
        bind(GPEventBus.class).to(GPEventBusImpl.class).in(Singleton.class);
        bind(FeatureWidget.class).in(Singleton.class);
        bind(FeatureAttributesWidget.class).in(Singleton.class);
        bind(IFeatureMapWidget.class).to(FeatureMapWidget.class).in(
                Singleton.class);
        bind(GeoPlatformMapFactory.class).to(DefaultMapFactory.class);
        
        bind(IFeatureMapInitializer.class).to(FeatureMapInitializer.class).in(Singleton.class);
        
        bind(FeatureSelectionLayoutHandler.class).in(Singleton.class);
        bind(FeatureAttributesLayoutHandler.class).in(Singleton.class);
        
        bind(ILayerSchemaBinder.class).to(LayerSchemaBinder.class).in(
                Singleton.class);
        
        bind(IFeatureIdBinder.class).to(FeatureIdBinder.class).in(
                Singleton.class);
        
        bind(FeatureAttributesWindowBuilder.class).asEagerSingleton();
        bind(WFSFeatureGridBinding.class).asEagerSingleton();
        
        requestStaticInjection(AttributeCustomFieldsMap.class);
    }
    
}
