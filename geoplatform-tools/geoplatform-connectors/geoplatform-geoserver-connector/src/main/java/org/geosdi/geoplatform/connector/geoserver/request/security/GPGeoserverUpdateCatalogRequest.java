package org.geosdi.geoplatform.connector.geoserver.request.security;

import com.google.common.io.CharStreams;
import net.jcip.annotations.ThreadSafe;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.GPGeoserverCatalog;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.IGPGeoserverCatalog;
import org.geosdi.geoplatform.connector.geoserver.model.security.catalog.IGPGeoserverCatalogMode;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.json.GPJsonPutConnectorRequest;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.BufferedReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.ThreadLocal.withInitial;
import static javax.annotation.meta.When.NEVER;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@ThreadSafe
public class GPGeoserverUpdateCatalogRequest extends GPJsonPutConnectorRequest<Boolean> implements GeoserverUpdateCatalogRequest {

    private final ThreadLocal<IGPGeoserverCatalogMode> catalogMode;

    /**
     * @param theServerConnector
     * @param theJacksonSupport
     */
    public GPGeoserverUpdateCatalogRequest(@Nonnull(when = NEVER) GPServerConnector theServerConnector, @Nonnull(when = NEVER) JacksonSupport theJacksonSupport) {
        super(theServerConnector, theJacksonSupport);
        this.catalogMode = withInitial(() -> null);
    }

    /**
     * @param theCatalogMode
     * @return {@link GeoserverUpdateCatalogRequest}
     */
    @Override
    public <CatalogMode extends IGPGeoserverCatalogMode> GeoserverUpdateCatalogRequest withCatalogMode(@Nonnull(when = NEVER) CatalogMode theCatalogMode) {
        this.catalogMode.set(theCatalogMode);
        return this;
    }

    /**
     * @return {@link HttpEntity}
     */
    @Override
    protected HttpEntity prepareHttpEntity() throws Exception {
        IGPGeoserverCatalogMode catalogMode = this.catalogMode.get();
        checkArgument(catalogMode != null, "The Parameter catalogMode must not be null.");
        IGPGeoserverCatalog geoserverCatalog = new GPGeoserverCatalog(catalogMode);
        String geoserverCatalogAsString = this.jacksonSupport.getDefaultMapper().writeValueAsString(geoserverCatalog);
        logger.debug("#############################CATALOG_MODE_BODY : \n{}\n", geoserverCatalogAsString);
        return new StringEntity(geoserverCatalogAsString, APPLICATION_JSON);
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
     * @param reader
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    protected Boolean readInternal(BufferedReader reader) throws Exception {
        String value = CharStreams.toString(reader);
        return ((value != null) && (value.trim().isEmpty()) ? TRUE : FALSE);
    }

    /**
     * @return {@link Class<Boolean>}
     */
    @Override
    protected Class<Boolean> forClass() {
        return Boolean.class;
    }
}