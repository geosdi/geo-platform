package org.geosdi.geoplatform.connector.geoserver.request.security.catalog;

import net.jcip.annotations.ThreadSafe;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalog;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonGetConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverGetCatalogRequest extends GPJsonGetConnectorRequest<GPGeoserverCatalog> {

    /**
     * @param server
     * @param theJacksonSupport
     */
    public GPGeoserverGetCatalogRequest(@Nonnull(when = NEVER) GPServerConnector server, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(server, theJacksonSupport);
    }

    /**
     * @return {@link String}
     */
    @Override
    protected String createUriPath() throws Exception {
        String baseURI = this.serverURI.toString();
        return ((baseURI.endsWith("/") ? baseURI.concat("security/acl/catalog.json") : baseURI.concat("/security/acl/catalog.json")));
    }

    /**
     * @return {@link Class<GPGeoserverCatalog>}
     */
    @Override
    protected Class<GPGeoserverCatalog> forClass() {
        return GPGeoserverCatalog.class;
    }
}
