package org.geosdi.geoplatform.persistence.configuration.properties;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPersistenceHibProperties extends Serializable {

    /**
     * @return {@link String}
     */
    String getHibDatabasePlatform();

    /**
     * @return {@link Boolean}
     */
    Boolean isHibShowSql();

    /**
     * @return {@link Boolean}
     */
    Boolean isHibGenerateDdl();

    /**
     * @return {@link String}
     */
    String getHibHbm2ddlAuto();

    /**
     * @return {@link Boolean}
     */
    Boolean isHibGenerateStatistics();

    /**
     * @return {@link String}
     */
    String getHibDefaultSchema();
}