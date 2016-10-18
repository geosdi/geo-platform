package org.geosdi.geoplatform.hibernate.validator.support;

import org.geosdi.geoplatform.hibernate.validator.GPUserValidator;
import org.geosdi.geoplatform.hibernate.validator.model.GPUser;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

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
        for (int i = 0; i < 10; i++) {
            new GPUserValidatorTask(gpUserValidator, i).start();
        }
        Thread.sleep(500);
    }

    static class GPUserValidatorTask extends Thread {

        private final GPUserValidator gpUserValidator;
        private final int i;

        /**
         * Allocates a new {@code Thread} object. This constructor has the same
         * effect as {@linkplain Thread#Thread(ThreadGroup, Runnable, String) Thread}
         * {@code (null, null, gname)}, where {@code gname} is a newly generated
         * name. Automatically generated names are of the form
         * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
         */
        public GPUserValidatorTask(GPUserValidator gpUserValidator, int i) {
            this.gpUserValidator = gpUserValidator;
            this.i = i;
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
            GPUser gpUser = createGPUser();
            gpUser.setUserName("");
            if (i % 2 == 0) {
                logger.info("########################IT_MESSAGE : {}\n", this.gpUserValidator
                        .validate(gpUser, Locale.ITALIAN));
            } else {
                logger.info("########################EN_MESSAGE : {}\n", this.gpUserValidator
                        .validate(gpUser, Locale.ENGLISH));
            }
        }
    }
}
