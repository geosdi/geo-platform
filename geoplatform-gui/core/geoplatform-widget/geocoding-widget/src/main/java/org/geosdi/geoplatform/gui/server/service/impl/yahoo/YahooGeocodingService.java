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
package org.geosdi.geoplatform.gui.server.service.impl.yahoo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.yahoo.YahooGeocodeBean;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.GPYahooGeocodeRoot;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.GPYahooResult;
import org.geosdi.geoplatform.gui.oxm.model.yahoo.enums.ResponseStatus;
import org.geosdi.geoplatform.gui.server.service.IGeocodingService;
import org.geosdi.geoplatform.oxm.jaxb.GPJaxbMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
@Component("yahooGeocodingService")
public class YahooGeocodingService implements IGeocodingService {

    // URL prefix to the geocoder
    private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://where.yahooapis.com/geocode";
    //
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPJaxbMarshaller geocoderYahooJaxbMarshaller;
    //
    private ArrayList<GeocodingBean> beans;

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.server.service.IGeocodingService#findLocations(java.lang.String)
     */
    @Override
    public ArrayList<GeocodingBean> findLocations(String address)
            throws IOException {
        this.beans = new ArrayList<GeocodingBean>();

        URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?location="
                + URLEncoder.encode(address, "UTF-8")
                + "&locale=it_IT"
                + "&appid=oyPe8o3V34EgAqJ2h4KP8KDsxgsYfqncfoLF7nagje.a1wUYJeHBE2aQaua7");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        GPYahooGeocodeRoot oxmBean = (GPYahooGeocodeRoot) this.geocoderYahooJaxbMarshaller.
                unmarshal(conn.getInputStream());

        if (oxmBean.getError().equals(
                ResponseStatus.EnumResponseStatus.CODE_NO_ERROR.getValue())) {
            for (GPYahooResult result : oxmBean.getResultList()) {
                beans.add(new YahooGeocodeBean(result));
            }
        }

        return beans;
    }

}
