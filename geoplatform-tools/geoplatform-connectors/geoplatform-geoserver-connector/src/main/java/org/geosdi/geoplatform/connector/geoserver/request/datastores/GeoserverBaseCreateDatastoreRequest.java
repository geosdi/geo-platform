package org.geosdi.geoplatform.connector.geoserver.request.datastores;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.geopkg.IGPGeopackageDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPPostgisDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape.IGPShapeFileDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape.IGPShapeFilesDirDatastoreBodyBuilder;
import org.geosdi.geoplatform.connector.server.request.GPConnectorRequest;

import javax.annotation.Nonnull;

import java.util.Map;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GeoserverBaseCreateDatastoreRequest<Request extends GeoserverBaseCreateDatastoreRequest, Response extends Object> extends GPConnectorRequest<Response> {

    /**
     * @param theWorkspaceName
     */
    Request withWorkspaceName(@Nonnull(when = NEVER) String theWorkspaceName);

    /**
     * <p>
     *      <ul>To create {@link IGPGeoserverCreateDatastoreBody} see :
     *          <li>{@link IGPPostgisDatastoreBodyBuilder.GPPostgisDatastoreBodyBuilder#postgisDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPGeopackageDatastoreBodyBuilder.GPGeopackageDatastoreBodyBuilder#geopackageDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPShapeFileDatastoreBodyBuilder.GPShapeFileDatastoreBodyBuilder#shapeFileDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPShapeFilesDirDatastoreBodyBuilder.GPShapeFilesDirDatastoreBodyBuilder#shapeFilesDirDatastoreBodyBuilder()}</li>
     *          <li>{@link IGPGeoserverCreateDatastoreBody#of(String, String, Boolean, Map)}</li>
     *      </ul>
     * </p>
     *
     * @param theDatastoreBody
     */
    Request withDatastoreBody(@Nonnull(when = NEVER) IGPGeoserverCreateDatastoreBody theDatastoreBody);
}