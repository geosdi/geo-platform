/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.GPUserValidator;
import org.geosdi.geoplatform.hibernate.validator.model.GPUser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Locale.ENGLISH;
import static java.util.Locale.ITALIAN;
import static java.util.stream.Stream.iterate;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.hibernate.validator.support.GPUserValidatorTest.createGPUser;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPUserValidatorMultiThreadTest {

    private static final Logger logger = LoggerFactory.getLogger(GPUserValidatorMultiThreadTest.class);
    //
    private static GPUserValidator gpUserValidator;

    @BeforeClass
    public static void beforeClass() {
        gpUserValidator = new GPUserValidator();
    }

    @Test
    public void validateGPUserTest() throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(20);
        AtomicInteger counter = new AtomicInteger(0);
        iterate(0, n -> n + 1)
                .limit(20)
                .map(i -> new GPUserValidatorTask(gpUserValidator, startSignal, doneSignal, counter))
                .forEach(Thread::start);
        startSignal.countDown();
        doneSignal.await();
        logger.info("#####################EXECUTED {} VALIDATION TASKS.", counter.get());
    }

    static class GPUserValidatorTask extends Thread {

        private final GPUserValidator gpUserValidator;
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private final AtomicInteger counter;

        /**
         * Allocates a new {@code Thread} object. This constructor has the same
         * effect as {@linkplain Thread#Thread(ThreadGroup, Runnable, String) Thread}
         * {@code (null, null, gname)}, where {@code gname} is a newly generated
         * name. Automatically generated names are of the form
         * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
         */
        GPUserValidatorTask(@Nonnull(when = NEVER) GPUserValidator theGPUserValidator, @Nonnull(when = NEVER) CountDownLatch theStartSignal,
                @Nonnull(when = NEVER) CountDownLatch theDoneSignal, @Nonnull(when = NEVER) AtomicInteger theCounter) {
            checkArgument(theGPUserValidator != null, "The Parameter gpUserValidator must not be null.");
            checkArgument(theStartSignal != null, "The Parameter startSignal must not be null.");
            checkArgument(theDoneSignal != null, "The Parameter doneSignal must not be null.");
            checkArgument(theCounter != null, "The Parameter counter must not be null.");
            this.gpUserValidator = theGPUserValidator;
            this.startSignal = theStartSignal;
            this.doneSignal = theDoneSignal;
            this.counter = theCounter;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see Thread#Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            try {
                startSignal.await();
                GPUser gpUser = createGPUser();
                gpUser.setUserName("");
                gpUser.setPassword(null);
                gpUser.setRegistrationDate(null);
                if (this.counter.getAndIncrement() % 2 == 0) {
                    logger.info("########################IT_MESSAGE : {}\n", this.gpUserValidator.validate(gpUser, ITALIAN));
                } else {
                    logger.info("########################EN_MESSAGE : {}\n", this.gpUserValidator.validate(gpUser, ENGLISH));
                }
                doneSignal.countDown();
            } catch (Exception ex) {

            }
        }
    }
}
