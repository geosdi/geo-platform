package org.geosdi.geoplatform.websocket.support.config.scheduler;

import org.geosdi.geoplatform.configurator.bootstrap.Develop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Develop
class GPWebSocketServerTaskSchedulerConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketServerTaskSchedulerConfig.class);

    /**
     * @return {@link TaskScheduler}
     */
    @Bean
    public TaskScheduler geoPlatformWebSocketServerTaskScheduler() {
        logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Building GeoPlatformWebSocketTaskScheduler.\n");
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 8);
        taskScheduler.setWaitForTasksToCompleteOnShutdown(TRUE);
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskScheduler.setThreadNamePrefix("GeoPlatformWebSocketServerTaskScheduler#");
        return taskScheduler;
    }
}