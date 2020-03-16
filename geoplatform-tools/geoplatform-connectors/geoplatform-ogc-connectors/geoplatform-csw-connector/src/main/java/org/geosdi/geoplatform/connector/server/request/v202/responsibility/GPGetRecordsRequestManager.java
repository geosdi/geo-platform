package org.geosdi.geoplatform.connector.server.request.v202.responsibility;

import org.geosdi.geoplatform.connector.server.request.CatalogGetRecordsRequest;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGetRecordsRequestManager extends Serializable {

    /**
     * @param request
     * @param filterType
     * @throws Exception
     */
    void filterGetRecordsRequest(@Nonnull(when = NEVER) CatalogGetRecordsRequest request, @Nonnull(when = NEVER) FilterType filterType) throws Exception;
}