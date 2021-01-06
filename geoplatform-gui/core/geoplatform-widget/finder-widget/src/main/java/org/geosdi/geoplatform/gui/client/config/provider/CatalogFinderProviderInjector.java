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
package org.geosdi.geoplatform.gui.client.config.provider;

import com.google.gwt.inject.client.AbstractGinModule;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.resources.GPCatalogConfigurator;
import org.geosdi.geoplatform.gui.client.widget.components.filters.spatial.CatalogBBoxComponent;
import org.geosdi.geoplatform.gui.client.widget.components.filters.spatial.CatalogCheckBoxComponent;
import org.geosdi.geoplatform.gui.client.widget.components.filters.spatial.CatalogComboBoxComponent;
import org.geosdi.geoplatform.gui.client.widget.components.search.pagination.MetadataSelectionManager;
import org.geosdi.geoplatform.gui.client.widget.components.search.tooltip.GPCatalogRecordsToolTip;
import org.geosdi.geoplatform.gui.factory.map.DefaultMapFactory;
import org.geosdi.geoplatform.gui.factory.map.GeoPlatformMapFactory;
import org.geosdi.geoplatform.gui.responce.AreaInfo;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.responce.TextInfo;
import org.geosdi.geoplatform.gui.responce.TimeInfo;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.event.MapMoveEndListener;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogFinderProviderInjector extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(CatalogFinderBean.class).toProvider(CatalogFinderBeanProvider.class).
                in(Singleton.class);

        bind(TextInfo.class).toProvider(TextInfoProvider.class).
                in(Singleton.class);

        bind(AreaInfo.class).toProvider(AreaInfoProvider.class).
                in(Singleton.class);

        bind(TimeInfo.class).toProvider(TimeInfoProvider.class).
                in(Singleton.class);

        bind(MapMoveEndListener.class).toProvider(
                CatalogMapMoveListenerProvider.class).
                in(Singleton.class);

        bind(GeoPlatformMapFactory.class).to(DefaultMapFactory.class);

        bind(MapWidget.class).toProvider(
                CatalogMapWidgetProvider.class).in(Singleton.class);

        bind(LonLat.class).toProvider(
                LonLatItalyProvider.class).in(Singleton.class);

        bind(CatalogBBoxComponent.class).toProvider(
                CatalogBBoxComponentProvider.class).in(Singleton.class);

        bind(CatalogComboBoxComponent.class).toProvider(
                CatalogComboBoxComponentProvider.class).in(Singleton.class);

        bind(CatalogCheckBoxComponent.class).toProvider(
                CatalogCheckBoxComponentProvider.class).in(Singleton.class);

        bind(GPCatalogRecordsToolTip.class).toProvider(
                CatalogRecordsToolTipProvider.class).in(Singleton.class);

        bind(MetadataSelectionManager.class).toProvider(
                MetadataSelectionManagerProvider.class).in(Singleton.class);
        
        bind(GPCatalogConfigurator.class).toProvider(
                GPCatalogConfiguratorProvider.class).in(Singleton.class);
    }
}
