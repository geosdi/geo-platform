/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.persistence.demo;

import org.geosdi.geoplatform.persistence.demo.bootstrap.SpringDataAppConfig;
import org.geosdi.geoplatform.persistence.demo.dao.spring.SpringCarDAO;
import org.geosdi.geoplatform.persistence.demo.model.Car;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;
import java.util.Objects;

import static io.reactivex.rxjava3.core.Flowable.fromIterable;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.springframework.data.domain.Example.of;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPPersistenceLoaderDemoConfig.class, SpringDataAppConfig.class, GPPersistenceSpringDataLoaderConfig.class},
        loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(value = {"jpa", "springData"})
@FixMethodOrder(value = NAME_ASCENDING)
public class PersistenceSpringTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private SpringCarDAO springCarDAO;

    @Test
    public void a_testSpringProfile() {
        Car car =  springCarDAO.findByPlate("AR793JJ");
        logger.info("Persistence Spring JPA DATA Test - Car Found @@@@@@@@@@@@@@@@@@@@@@@@@ {}", car);
    }

    @Test
    public void b_testSpringProfile() {
        ExampleMatcher matcher = matching()
                .withMatcher("plate", exact());
        Car car = new Car();
        car.setPlate("CT149JS");
        List<Car> cars = this.springCarDAO.findAll(of(car, matcher));
        assertEquals(1, cars.size());
    }

    @Test
    public void c_testSpringProfile() {
        ExampleMatcher matcher = matching()
                .withMatcher("model", contains().caseSensitive());
        Car car = new Car();
        car.setModel("Fiat");
        List<Car> cars = this.springCarDAO.findAll(of(car, matcher));
        assertEquals(2, cars.size());
        fromIterable(cars)
                .filter(Objects::nonNull)
                .subscribe(this.springCarDAO::delete, Throwable::printStackTrace);
    }
}