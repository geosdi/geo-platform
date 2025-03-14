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

import org.geosdi.geoplatform.persistence.demo.dao.ICarDAO;
import org.geosdi.geoplatform.persistence.demo.dao.ICarPartDAO;
import org.geosdi.geoplatform.persistence.demo.model.Car;
import org.geosdi.geoplatform.persistence.demo.model.CarPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPPersistenceLoaderDemoConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(value = {"jpa"})
public class PersistenceJpaTest {

    private final static String PART_NAME = "Gearbox";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private ICarDAO jpaCarDAO;
    //
    @Autowired
    private ICarPartDAO jpaCarPartDAO;

    @After
    public void tearDown() throws Exception {
        removeAll();
    }

    @Before
    public void setUp() {
        insert();
    }

    @Test
    public void testJpaProfile() throws Exception {
        assertEquals(100, this.jpaCarDAO.count().intValue());
        logger.info("FOUND PART @@@@@@@@@@@@@@@@@@@@@@@@@@: {}", this.jpaCarPartDAO.findByPartName(PART_NAME + 0).getPartName());
        CarPart test = this.jpaCarPartDAO.findByPartName(PART_NAME + 0);
        assertNotNull("The car part is not null", test);
        assertEquals(100, this.jpaCarDAO.findAll().size());
        Car car = this.jpaCarDAO.findByPlate("AR7930");
        assertNotNull(car);
        car.setModel("MODEL_TEST");
        this.jpaCarDAO.update(car);
        logger.info("##################################UPDATE_CAR : {}\n", car.getModel());
        CarPart carPart = this.jpaCarPartDAO.findByPartName(PART_NAME + 20);
        assertNotNull(carPart);
        carPart.setCar(car);
        this.jpaCarPartDAO.update(carPart);
        assertTrue(carPart.getCar().getId().equals(car.getId()));
        logger.info("########################################FIND_CAR_BY_ID : {}\n", this.jpaCarDAO.find(car.getId()));
    }

    private void insert() {
        for (int i = 0; i < 100; i++) {
            Car car = new Car();
            car.setPlate("AR793" + i);
            car.setModel("Fiat Model " + i);
            CarPart carPart = new CarPart();
            carPart.setPartName(PART_NAME + i);
            carPart.setCar(car);
            jpaCarDAO.persist(car);
            jpaCarPartDAO.persist(carPart);
        }
    }

    private void removeAll() throws Exception {
        jpaCarDAO.removeAll();
        logger.info("REMOVED ALL CARS ##################################");
    }
}
