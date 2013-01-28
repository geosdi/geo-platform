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

import com.google.gwt.inject.client.AbstractGinModule;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.DescribeFeatureTypeHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.LayerTypeHandlerManager;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.RasterTypeHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.ConcreteLayerSchemaHandler;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.LayerSchemaHandlerManager;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.schema.LayerSchemaParserHandler;
import org.geosdi.geoplatform.gui.client.config.provider.DescribeFeatureTypeHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.FeatureLonLatItalyProvider;
import org.geosdi.geoplatform.gui.client.config.provider.FeatureMapWidgetProvider;
import org.geosdi.geoplatform.gui.client.config.provider.FeatureProtocolCRUDOptionsProvider;
import org.geosdi.geoplatform.gui.client.config.provider.FeatureSelectHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.FeatureUnSelectHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.LayerSchemaHandlerManagerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.LayerTypeHandlerManagerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.RasterTypeHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.VectorLayerProvider;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.IFeatureWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.DescribeFeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.dispatcher.GPDescribeFeatureDispatcher;
import org.geosdi.geoplatform.gui.client.widget.wfs.handler.FeatureSelectHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.handler.FeatureUnSelectHandler;
import org.geosdi.geoplatform.gui.factory.map.DefaultMapFactory;
import org.geosdi.geoplatform.gui.factory.map.GeoPlatformMapFactory;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.GPEventBusImpl;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.layer.Vector;
import org.gwtopenmaps.openlayers.client.protocol.WFSProtocolCRUDOptions;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class FeatureInjectorModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(GPEventBus.class).to(GPEventBusImpl.class).in(Singleton.class);

        bind(IFeatureWidget.class).to(FeatureWidget.class).in(Singleton.class);

        bind(GeoPlatformMapFactory.class).to(DefaultMapFactory.class);

        bind(MapWidget.class).toProvider(FeatureMapWidgetProvider.class)
                .in(Singleton.class);

        bind(LayerTypeHandlerManager.class).toProvider(
                LayerTypeHandlerManagerProvider.class).in(Singleton.class);

        bind(DescribeFeatureTypeHandler.class).toProvider(
                DescribeFeatureTypeHandlerProvider.class).in(Singleton.class);

        bind(RasterTypeHandler.class).toProvider(RasterTypeHandlerProvider.class).in(
                Singleton.class);

        bind(DescribeFeatureDispatcher.class).to(
                GPDescribeFeatureDispatcher.class).in(Singleton.class);

        bind(LayerSchemaParserHandler.class).to(ConcreteLayerSchemaHandler.class).in(
                Singleton.class);

        bind(LayerSchemaHandlerManager.class).toProvider(
                LayerSchemaHandlerManagerProvider.class).in(Singleton.class);

        bind(Vector.class).toProvider(VectorLayerProvider.class).in(
                Singleton.class);

        bind(FeatureSelectHandler.class).toProvider(
                FeatureSelectHandlerProvider.class).in(
                Singleton.class);

        bind(FeatureUnSelectHandler.class).toProvider(
                FeatureUnSelectHandlerProvider.class).in(
                Singleton.class);

        bind(LonLat.class).toProvider(FeatureLonLatItalyProvider.class).in(
                Singleton.class);

        bind(WFSProtocolCRUDOptions.class).toProvider(
                FeatureProtocolCRUDOptionsProvider.class).in(Singleton.class);
    }
}
