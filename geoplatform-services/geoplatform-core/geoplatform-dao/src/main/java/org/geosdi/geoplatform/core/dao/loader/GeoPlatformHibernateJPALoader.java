package org.geosdi.geoplatform.core.dao.loader;

import org.geosdi.geoplatform.persistence.loader.PersistenceLoaderConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
@Import(value = {PersistenceLoaderConfigurer.class})
class GeoPlatformHibernateJPALoader {
}
