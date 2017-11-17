package org.geosdi.geoplatform.scheduler.email;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPSchedulerVelocityEngineConfig {

    @Bean(name = "schedulerVelocityEngine")
    @Scope(value = "prototype")
    public VelocityEngine schedulerVelocityEngine() throws VelocityException {

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.addProperty("resource.loader", "class");
        velocityEngine.addProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.addProperty("parser.pool.class", "org.apache.velocity.runtime.ParserPoolImpl");
        velocityEngine.addProperty("parser.pool.size", 40);
        velocityEngine.init();
        return velocityEngine;
    }
}
