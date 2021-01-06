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
package org.geosdi.geoplatform.gui.server.gwt;

import java.io.IOException;
import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.service.GeocodingRemote;
import org.geosdi.geoplatform.gui.client.widget.map.ReverseGeoCoderProvider;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.service.IGeocodingService;
import org.geosdi.geoplatform.gui.server.service.IReverseGeocoding;
import org.geosdi.geoplatform.gui.server.spring.GPAutoInjectingRemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Deprecated
public class GeocodingRemoteImpl extends GPAutoInjectingRemoteServiceServlet
        implements GeocodingRemote {

    private static final long serialVersionUID = 8960403782525028063L;
    //
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    // Google Service
    @Autowired
    private IGeocodingService googleGeocodingService;
    //
    @Autowired
    private IReverseGeocoding googleReverseGeocoding;
    //
    // Yahoo Service
    @Autowired
    private IGeocodingService yahooGeocodingService;
    //
    @Autowired
    private IReverseGeocoding yahooReverseGeocoding;

    @Override
    public ArrayList<GeocodingBean> findLocations(String search)
            throws GeoPlatformException {
        try {
            return this.googleGeocodingService.findLocations(search);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new GeoPlatformException(e.getMessage());
        }
    }

    @Override
    public ArrayList<GeocodingBean> findLocations(String search, String geocodingService)
            throws GeoPlatformException {
        try {
            if (geocodingService.equals(ReverseGeoCoderProvider.GOOGLE.getProvider())) {
                return this.googleGeocodingService.findLocations(search);
            } else {
                return this.yahooGeocodingService.findLocations(search);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new GeoPlatformException(e.getMessage());
        }
    }

    @Override
    public GeocodingBean findLocation(double lat, double lon,
            ReverseGeoCoderProvider provider)
            throws GeoPlatformException {
        try {
            if (provider == ReverseGeoCoderProvider.YAHOO) {
                return this.yahooReverseGeocoding.findLocation(lat, lon);
            } else {
                return this.googleReverseGeocoding.findLocation(lat, lon);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new GeoPlatformException(e.getMessage());
        }
    }
}
