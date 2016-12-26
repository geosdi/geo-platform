package org.geosdi.geoplatform.support.mail.spring.configuration.freemarker;

import net.jcip.annotations.Immutable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Profile(value = "GPMailFreeMarkerSupport")
@Component(value = "gpFreeMarkerConfigLocation")
public class GPFreeMarkerConfigLocation implements IGPFreeMarkerConfigLocation {

    @Value(value = "gpMailConfigurator{gp.mail.freemarker.loader.paths:@null}")
    private String freeMarkerConfigLocation;

    /**
     * @return {@link String}
     */
    @Override
    public Resource getFreeMarkerConfigLocation() {
        return ((this.freeMarkerConfigLocation != null) && !(this.freeMarkerConfigLocation.isEmpty()))
                ? new ClassPathResource(this.freeMarkerConfigLocation) : new ClassPathResource("template");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "freeMarkerConfigLocation = '" + getFreeMarkerConfigLocation() +
                '}';
    }
}
