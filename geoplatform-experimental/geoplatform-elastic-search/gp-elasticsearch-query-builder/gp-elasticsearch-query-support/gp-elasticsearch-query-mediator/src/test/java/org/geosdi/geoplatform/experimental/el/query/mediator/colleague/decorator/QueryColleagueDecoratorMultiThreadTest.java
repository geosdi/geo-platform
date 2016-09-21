package org.geosdi.geoplatform.experimental.el.query.mediator.colleague.decorator;

import org.geosdi.geoplatform.experimental.el.query.mediator.colleague.GPElasticSearchQueryColleague;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Query-Colleague-Decorator-Test.xml"})
public class QueryColleagueDecoratorMultiThreadTest {

    @GeoPlatformLog
    static Logger logger;
    //
    private static final String QUERY_TEMPLATE = "DO IT ${command}";
    //
    @Resource(name = "queryColleagueBase")
    private GPElasticSearchQueryColleague queryColleagueBase;
    @Resource(name = "queryColleagueSimple")
    private GPElasticSearchQueryColleague queryColleagueSimple;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.queryColleagueBase);
    }

    @Test
    public void queryColleagueDecoratorMultiThreadMockBaseTest() throws Exception {
        IGPElasticSearchQueryColleagueDecorator baseColleagueQueryDecorator = GPElasticSearchQueryColleagueDecorator
                .of(this.queryColleagueBase);
        for (int i = 0; i < 100; i++) {
            new QueryColleagueDecoratorTask(baseColleagueQueryDecorator, "MockBase", i).start();
        }
        Thread.sleep(500);
    }

    @Test
    public void queryColleagueDecoratorMultiThreadMockSimpleTest() throws Exception {
        IGPElasticSearchQueryColleagueDecorator baseColleagueQueryDecorator = GPElasticSearchQueryColleagueDecorator
                .of(this.queryColleagueSimple);
        for (int i = 0; i < 100; i++) {
            new QueryColleagueDecoratorTask(baseColleagueQueryDecorator, "MockSimple", i).start();
        }
        Thread.sleep(500);
    }

    static class QueryColleagueDecoratorTask extends Thread {

        private static final Logger logger = LoggerFactory.getLogger(QueryColleagueDecoratorTask.class);
        //
        private final int count;
        private final IGPElasticSearchQueryColleagueDecorator baseColleagueQueryDecorator;
        private final String queryColleagueDecoratorName;

        public QueryColleagueDecoratorTask(IGPElasticSearchQueryColleagueDecorator theOpenAMValidateTokenRequest,
                String theQueryColleagueDecoratorName, int theCount) {
            this.baseColleagueQueryDecorator = theOpenAMValidateTokenRequest;
            this.queryColleagueDecoratorName = theQueryColleagueDecoratorName;
            this.count = theCount;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p/>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see {@link Thread#Thread(ThreadGroup, Runnable, String)}
         */
        @Override
        public void run() {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("command", this.queryColleagueDecoratorName.concat("#".concat(Thread.currentThread().getName()
                        + " - " + count)));
                String result = baseColleagueQueryDecorator.executeQueryColleague(QUERY_TEMPLATE, map);
                logger.info("#######################RESULT : {}\n", result);
            } catch (Exception ex) {
                logger.error("####################ERROR : {}\n", ex.getMessage());
            }
        }
    }
}
