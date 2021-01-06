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
package org.geosdi.geoplatform.experimental.mongodb.template;

import org.geosdi.geoplatform.experimental.mongodb.loader.GPMongoConfigLoader;
import org.geosdi.geoplatform.experimental.mongodb.model.Address;
import org.geosdi.geoplatform.experimental.mongodb.repositories.AddressRepository;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GPMongoConfigLoader.class},
        loader = AnnotationConfigContextLoader.class)
public class GPMongoConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    static final String GP_MONGO_KEY = "GP_MONGO_FILE_PROP";
    //
    private static final Point DUS = new Point(6.810036, 51.224088);
    //
    @Resource(name = "mongoTemplate")
    MongoTemplate mongoTemplate;
    @Autowired
    AddressRepository addressRepo;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty(GP_MONGO_KEY, "gp-mongo-test.prop");
    }

    @AfterClass
    public static void afterClass() {
        System.clearProperty(GP_MONGO_KEY);
    }

    @Before
    public void setUp() {
        Assert.assertNotNull("GPSpringMongo Template must not NULL",
                mongoTemplate);
        Assert.assertNotNull("Address Repo must not be NULL",
                addressRepo);

        addressRepo.save(Stream.of(new Address("A", 0.001, -0.002),
                new Address("B", 1, 1), new Address("C", 0.5, 0.5),
                new Address("D", -0.5, -0.5), new Address("Berlin", 13.405838, 52.531261),
                new Address("Cologne", 6.921272, 50.960157),
                new Address("Dusseldorf", 6.810036, 51.224088))
                .collect(toList()));
    }

    @Test
    public void shouldFindSelf() {
        List<Address> addresses = addressRepo.findByLocationNear(DUS, new Distance(1,
                Metrics.KILOMETERS));

        Assert.assertEquals(1, addresses.size());

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ shouldFindSelf Found "
                + ": {}\n\n", addresses);
    }

    @Test
    public void shouldFindAroundOrigin() {
        List<Address> addresses = addressRepo.findByLocationWithin(new Circle(0, 0, 0.75));
        Assert.assertEquals(3, addresses.size());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ shouldFindAroundOrigin() Found : {}\n\n", addresses);
    }

    @Test
    public void shouldFindWithinBox() {
        List<Address> addresses = addressRepo.findByLocationWithin(new Box(new Point(0.25, 0.25),
                new Point(1, 1)));
        Assert.assertEquals(2, addresses.size());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ shouldFindWithinBox() Found "
                + ": {}\n\n", addresses);
    }

    @Test
    @Ignore(value = "MASSIVE TEST")
    public void insertMassiveAddressTest() throws Exception {
        long time = 0;

        ExecutorService executor = Executors.newFixedThreadPool(100);

        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>(100);

        for (int i = 0; i < 100; i++) {
            tasks.add(new MongoInsertMassiveAddress());
        }

        List<Future<Long>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(10, TimeUnit.MINUTES);

        if (flag) {
            for (Future<Long> future : results) {
                time += future.get();
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }

        logger.info("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ insertMassiveAddressTest "
                        + ": Executed {} Inserts in {} Minutes \n", 4000,
                TimeUnit.MILLISECONDS.toMinutes(time));
    }

    @After
    public void tearDown() {
        this.mongoTemplate.dropCollection(Address.class);
    }

    private class MongoInsertMassiveAddress implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            List<Address> addresses = new ArrayList<Address>(40);
            for (int i = 0; i < 40; i++) {
                Address a = new Address("Address" + UUID.randomUUID(),
                        Math.random(), Math.random());
                addresses.add(a);
            }

            addressRepo.save(addresses);

            return System.currentTimeMillis() - start;
        }

    }

}
