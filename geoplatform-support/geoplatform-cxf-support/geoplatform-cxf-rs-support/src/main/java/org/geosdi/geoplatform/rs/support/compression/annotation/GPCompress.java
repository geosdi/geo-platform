package org.geosdi.geoplatform.rs.support.compression.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface GPCompress {

    /**
     * @return {@link Integer}
     */
    int thrushold() default 512;

    /**
     * @return {@link Boolean}
     */
    boolean syncFlush() default false;
}