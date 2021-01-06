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
package org.geosdi.geoplatform.support.google.services;

import com.google.maps.PendingResult;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.LocationType;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.google.spring.services.geocoding.GPGeocodingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Geocoding-Test.xml"})
public class GPGeocodingServiceTest extends GPBaseConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Autowired
    private GPGeocodingService gpGeocodingService;

    @Before
    public void setUp() {
        Assert.assertNotNull(gpGeocodingService);
    }

    @Test
    public void gpErrorGeocodingTest() throws Exception {
        GeocodingResult[] errorResults = gpGeocodingService.newRequest().address("Romaaaaa").await();
        assertTrue((errorResults.length == 0));
        GeocodingResult[] results = gpGeocodingService.newRequest().address("Roma").region("it").await();
        assertTrue((results != null) && (results.length > 0));
        logger.info("###########################Location : {} - Geometry : {}\n", results[0].formattedAddress, results[0].geometry);
    }

    @Test
    public void gpTitoScaloPlaceIDTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address("Tito Potenza").await();
        assertTrue((results != null) && (results.length > 0));
        logger.info("###########################Location : {} - Geometry : {} - PlaceID : {}\n", results[0].formattedAddress, results[0].geometry, results[0].placeId);
    }

    @Test
    public void gpSimpleGeocodingTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address("Potenza").await();
        checkResult(results);
    }

    @Test
    public void gpGeocodingAsyncTest() throws Exception {
        final List<GeocodingResult[]> resps = new ArrayList<>();
        PendingResult.Callback<GeocodingResult[]> callback
                = new PendingResult.Callback<GeocodingResult[]>() {

            @Override
            public void onResult(GeocodingResult[] result) {
                resps.add(result);
            }

            @Override
            public void onFailure(Throwable e) {
                logger.error("###############ERROR : {}", e);
            }
        };
        gpGeocodingService.newRequest().address("Potenza").setCallback(callback);
        Thread.sleep(2500);
        assertFalse(resps.isEmpty());
        assertNotNull(resps.get(0));
        checkResult(resps.get(0));
    }

    @Test
    public void gpReverseGeocodingTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest()
                .latlng(new LatLng(40.6404067, 15.8056041)).await();
        assertTrue("Address contain 'Potenza'",
                results[0].formattedAddress.contains("Potenza"));
    }

    @Test
    public void gpGeocodingWithRegionTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address(
                "Marsicovetere").region("it").await();
        assertNotNull(results);
        assertEquals("85050 Marsicovetere, Province of Potenza, Italy",
                results[0].formattedAddress);
    }

    @Test
    public void gpGeocodingTheGoogleplexTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest()
                .address("1600 Amphitheatre Parkway, Mountain View, CA").await();
        assertNotNull(results);
        assertEquals("1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA",
                results[0].formattedAddress);
    }

    @Test
    public void gpGeocodingWithComponentFilterTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest()
                .address("Marsicovetere")
                .components(ComponentFilter.country("it"), ComponentFilter
                        .postalCode("85050")).await();
        assertNotNull(results);
        assertEquals("85050 Marsicovetere, Province of Potenza, Italy",
                results[0].formattedAddress);
    }

    @Test
    public void gpGeocodingBoundsTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address(
                "Marsicovetere").bounds(new LatLng(40.373706, 15.821933),
                new LatLng(40.378703, 15.830559)).await();
        Assert.assertNotNull(results);
        Assert.assertEquals("85050 Marsicovetere, Province of Potenza, Italy",
                results[0].formattedAddress);
    }

    @Test
    public void gpReverseGeocodingSimpleTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest()
                .latlng(new LatLng(46.1413888888889, 12.2144444444444)).await();
        assertNotNull(results);
        logger.info("{}\n", results[0].formattedAddress);
    }

    private void checkResult(GeocodingResult[] results) {
        Assert.assertNotNull(results);
        Assert.assertNotNull(results[0].geometry);
        Assert.assertNotNull(results[0].geometry.location);
        Assert.assertEquals(40.6404067, results[0].geometry.location.lat,
                0.000001);
        Assert.assertEquals(15.8056041, results[0].geometry.location.lng,
                0.000001);
        Assert.assertEquals(LocationType.APPROXIMATE,
                results[0].geometry.locationType);
    }
}
