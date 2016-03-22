package org.geosdi.geoplatform.experimental.openam.api.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NameBinding
public @interface GPOpenAMSupport {
}
