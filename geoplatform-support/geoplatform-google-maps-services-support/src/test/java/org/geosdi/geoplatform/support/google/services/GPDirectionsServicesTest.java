/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.google.services;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.errors.NotFoundException;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.TravelMode;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.google.spring.services.directions.GPDirectionsService;
import static org.hamcrest.core.IsNot.not;
import org.joda.time.DateTime;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Test.xml"})
public class GPDirectionsServicesTest extends GPBaseConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    @Autowired
    private GPDirectionsService gpDirectionsService;

    @Before
    public void setUp() {
        Assert.assertNotNull(gpDirectionsService);
    }

    @Test
    public void gpGetDirectionsTest() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.getDirections("Potenza",
                "Roma").mode(TravelMode.DRIVING).await();
        assertNotNull(routes);
        assertNotNull(routes[0]);
        assertThat(routes[0].overviewPolyline.decodePath().size(), not(0));
        assertEquals("85100 Potenza, Italy", routes[0].legs[0].startAddress);
        assertEquals("Rome, Italy", routes[0].legs[0].endAddress);
        logger.info("@@@@@@@@@@@@@@@START_ADDRESS : {}\n",
                routes[0].legs[0].startAddress);
        logger.info("@@@@@@@@@@@@@@@END_ADDRESS : {}\n",
                routes[0].legs[0].endAddress);

        DirectionsStep[] steps = routes[0].legs[0].steps;

        Assert.assertEquals(40, steps.length);

        logger.info("@@@@@@@@@@@@@@@@@@@@FOUND {} STEPS\n", steps.length);

        for (DirectionsStep step : steps) {
            logger.info("@@@@@@@@@@@@@@@@@@@@@@ START_POINT : {} - "
                    + "END_POINT : {} - HTML_INSTRUCTION : {} - POLYLINE : {}\n",
                    step.startLocation, step.endLocation, step.htmlInstructions,
                    step.polyline.decodePath());
        }
    }

    @Test
    public void gpTravelModeTest() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.newRequest()
                .mode(TravelMode.WALKING)
                .origin("Via Provinciale, Marsicovetere")
                .destination("Via Nazionale, Marsicovetere").await();
        assertNotNull(routes);
        assertEquals(TravelMode.WALKING, routes[0].legs[0].steps[0].travelMode);
    }

    @Test
    public void gpTravelModeBicyclingTest() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.newRequest()
                .origin("New York")
                .destination("Montreal")
                .language("it")
                .avoid(RouteRestriction.HIGHWAYS)
                .mode(TravelMode.BICYCLING)
                .await();
        assertNotNull(routes);
        assertEquals(TravelMode.BICYCLING, routes[0].legs[0].steps[0].travelMode);
        assertEquals("1 giorno 12 ore", routes[0].legs[0].duration.humanReadable);
        assertEquals("402 mi", routes[0].legs[0].distance.humanReadable);
    }

    @Test
    public void gpResponseTimesCorrectTest() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.newRequest()
                .language("it")
                .region("it")
                .origin("Siracusa")
                .destination("Roma")
                .await();
        assertNotNull(routes);
        assertNotNull(routes[0]);
        assertNotNull(routes[0].legs);
        assertNotNull(routes[0].legs[0]);
        assertEquals("861 km", routes[0].legs[0].distance.humanReadable);
        assertNotNull(routes[0].legs[0].duration);
        assertEquals("9 ore 10 min", routes[0].legs[0].duration.humanReadable);
    }

    @Test
    public void gpDirectionsValDAgriTest() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.newRequest()
                .mode(TravelMode.WALKING)
                .language("it")
                .origin("Marsicovetere PZ, Italy")
                .destination("Paterno PZ, Italy")
                .await();
        assertNotNull(routes);
        assertNotNull(routes[0]);
        assertNotNull(routes[0].legs);
        assertNotNull(routes[0].legs[0]);
        assertEquals("11,7 km", routes[0].legs[0].distance.humanReadable);
        assertEquals(TravelMode.WALKING, routes[0].legs[0].steps[0].travelMode);
        assertNotNull(routes[0].legs[0].duration);
        assertEquals("2 ore 15 min", routes[0].legs[0].duration.humanReadable);
    }

    @Test
    public void gpRomeToMilanAvoidingHighways() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.newRequest()
                .language("it")
                .origin("Roma")
                .region("it")
                .destination("Milano")
                .mode(TravelMode.TRANSIT)
                .departureTime(new DateTime())
                .avoid(RouteRestriction.HIGHWAYS)
                .await();
        assertEquals(Boolean.TRUE, (routes.length > 0));

        assertEquals("569 km", routes[0].legs[0].distance.humanReadable);
        assertEquals("4 ore 25 min",
                routes[0].legs[0].duration.humanReadable);
        assertNotNull(routes[0].legs[0].arrivalTime);
        assertNotNull(routes[0].legs[0].departureTime);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFound() throws Exception {
        DirectionsRoute[] routes = gpDirectionsService.getDirections(
                "sdjfhsjfhsjf",
                "sjdfhjsdf7w7r").await();
    }
}
