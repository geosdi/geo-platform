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
package org.geosdi.geoplatform.gui.client.config;

import com.google.gwt.inject.client.AbstractGinModule;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.config.provider.choise.GetMapKVPHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.GetMapSimpleHandlerProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.encoder.GetMapUrlEncoderProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.ComponentPluginNotificationEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.ComponentPluginResetEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapIncorrectStatusEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapCorrectStatusEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapDisableStatusEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapExecuteEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapHideWidgetEventProvider;
import org.geosdi.geoplatform.gui.client.config.provider.choise.event.WmsGetMapResetEventProvider;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.encoder.GetMapUrlEncoder;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.handler.GetMapKvpHandler;
import org.geosdi.geoplatform.gui.client.configuration.getmap.choise.handler.GetMapSimpleHandler;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.ComponentPluginNotificationEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.ComponentPluginResetEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapIncorrectStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapCorrectStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapDisableStatusEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapExecuteEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapHideWidgetEvent;
import org.geosdi.geoplatform.gui.client.puregwt.getmap.event.WmsGetMapResetEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WmsGetMapProviderConfigurator extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(GetMapSimpleHandler.class).toProvider(
                GetMapSimpleHandlerProvider.class).in(Singleton.class);

        bind(GetMapKvpHandler.class).toProvider(
                GetMapKVPHandlerProvider.class).in(Singleton.class);

        bind(WmsGetMapIncorrectStatusEvent.class).toProvider(
                WmsGetMapIncorrectStatusEventProvider.class).in(Singleton.class);

        bind(WmsGetMapCorrectStatusEvent.class).toProvider(
                WmsGetMapCorrectStatusEventProvider.class).in(Singleton.class);

        bind(WmsGetMapExecuteEvent.class).toProvider(
                WmsGetMapExecuteEventProvider.class).in(Singleton.class);

        bind(WmsGetMapResetEvent.class).toProvider(
                WmsGetMapResetEventProvider.class).in(Singleton.class);

        bind(WmsGetMapDisableStatusEvent.class).toProvider(
                WmsGetMapDisableStatusEventProvider.class).in(Singleton.class);

        bind(WmsGetMapHideWidgetEvent.class).toProvider(
                WmsGetMapHideWidgetEventProvider.class).in(Singleton.class);

        bind(GetMapUrlEncoder.class).toProvider(GetMapUrlEncoderProvider.class).in(
                Singleton.class);

        bind(ComponentPluginNotificationEvent.class).toProvider(
                ComponentPluginNotificationEventProvider.class);

        bind(ComponentPluginResetEvent.class).toProvider(
                ComponentPluginResetEventProvider.class);
    }

}
