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

import com.google.maps.model.*;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.google.spring.services.distance.GPDistanceMatrixService;
import org.geosdi.geoplatform.support.google.spring.services.geocoding.GPGeocodingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.geosdi.geoplatform.support.google.spring.services.distance.Unit.K;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Distance-Test.xml"})
public class GPDistanceMatrixServiceTest extends GPBaseConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Autowired
    private GPDistanceMatrixService gpDistanceMatrixService;
    @Autowired
    private GPGeocodingService gpGeocodingService;

    @Before
    public void setUp() {
        Assert.assertNotNull(gpDistanceMatrixService);
    }

    @Test
    public void gpErrorDistanceTest() throws Exception {
        DistanceMatrix distanceMatrix = this.gpDistanceMatrixService
                .getDistanceMatrix(new String[]{"sdfjhdsjf"}, new String[]{"sdfihdsf"})
                .mode(TravelMode.DRIVING).language("it").await();
        Assert.assertNotNull(distanceMatrix);
        DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
        DistanceMatrixElement element = distanceMatrixRow.elements[0];
        logger.info("######################ERROR Status : {}\n", element.status);

    }

    @Test
    public void titoRomaDistanceTest() throws Exception {
        DistanceMatrix distanceMatrix = this.gpDistanceMatrixService
                .getDistanceMatrix(new String[]{"Tito"}, new String[]{"Roma"})
                .mode(TravelMode.DRIVING).language("it").await();
        Assert.assertNotNull(distanceMatrix);
        DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
        for (DistanceMatrixElement element : distanceMatrixRow.elements) {
            logger.info("######################Tito - Roma : KM : {} - Duration : {} - Fare : {}\n",
                    element.distance, element.duration, element.fare);
        }
    }

    @Test
    public void romaFirenzeDistanceTest() throws Exception {
        DistanceMatrix distanceMatrix = this.gpDistanceMatrixService
                .getDistanceMatrix(new String[]{"Roma"}, new String[]{"Firenze"})
                .mode(TravelMode.DRIVING).language("it").await();
        Assert.assertNotNull(distanceMatrix);
        DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
        for (DistanceMatrixElement element : distanceMatrixRow.elements) {
            logger.info("######################Roma - Firenze : KM : {} - Duration : {} - Fare : {}\n",
                    element.distance, element.duration, element.fare);
        }
    }

    @Test
    public void salernoBolognaDistanceTest() throws Exception {
        DistanceMatrix distanceMatrix = this.gpDistanceMatrixService
                .getDistanceMatrix(new String[]{"Salerno"}, new String[]{"Bologna"})
                .mode(TravelMode.DRIVING).language("it").await();
        Assert.assertNotNull(distanceMatrix);
        DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
        for (DistanceMatrixElement element : distanceMatrixRow.elements) {
            logger.info("######################Salerno - Bologna : KM : {} - Duration : {} - Fare : {}\n",
                    element.distance, element.duration, element.fare);
        }
    }

    @Test
    public void casertaTorinoDistanceTest() throws Exception {
        DistanceMatrix distanceMatrix = this.gpDistanceMatrixService
                .getDistanceMatrix(new String[]{"Caserta"}, new String[]{"Torino"})
                .mode(TravelMode.DRIVING).language("it").await();
        Assert.assertNotNull(distanceMatrix);
        DistanceMatrixRow distanceMatrixRow = distanceMatrix.rows[0];
        for (DistanceMatrixElement element : distanceMatrixRow.elements) {
            logger.info("######################KM : {} - Duration : {} - Fare : {}\n",
                    element.distance, element.duration, element.fare);
        }
    }

    @Test
    public void titoParaguaiDistanceTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address("Tito Potenza").await();
        Assert.assertTrue((results != null) && (results.length > 0));
        logger.info("###########################Location : {} - Geometry : {} - PlaceID : {}\n",
                results[0].formattedAddress, results[0].geometry, results[0].placeId);
        GeocodingResult[] resultsParaguai = gpGeocodingService.newRequest().address("Paraguai").await();
        Assert.assertTrue((resultsParaguai != null) && (resultsParaguai.length > 0));
        logger.info("###########################Location : {} - Geometry : {} - PlaceID : {}\n",
                resultsParaguai[0].formattedAddress, resultsParaguai[0].geometry, resultsParaguai[0].placeId);
        logger.info("{}", this.gpDistanceMatrixService
                .distance(results[0].geometry.location.lat, results[0].geometry.location.lng,
                        resultsParaguai[0].geometry.location.lat, resultsParaguai[0].geometry.location.lng,
                        K).intValue());
    }

    @Test
    public void titoRomaDistanceDirectTest() throws Exception {
        GeocodingResult[] results = gpGeocodingService.newRequest().address("Tito Potenza").await();
        Assert.assertTrue((results != null) && (results.length > 0));
        logger.info("###########################Location : {} - Geometry : {} - PlaceID : {}\n",
                results[0].formattedAddress, results[0].geometry, results[0].placeId);
        GeocodingResult[] resultsRoma = gpGeocodingService.newRequest().address("Roma").region("it").await();
        Assert.assertTrue((resultsRoma != null) && (resultsRoma.length > 0));
        logger.info("###########################Location : {} - Geometry : {} - PlaceID : {}\n",
                resultsRoma[0].formattedAddress, resultsRoma[0].geometry, resultsRoma[0].placeId);
        logger.info("################################DISTANCE : {}\n", this.gpDistanceMatrixService
                .distance(results[0].geometry.location.lat, results[0].geometry.location.lng,
                        resultsRoma[0].geometry.location.lat, resultsRoma[0].geometry.location.lng,
                        K).doubleValue());
    }
}
