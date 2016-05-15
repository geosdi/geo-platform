package org.geosdi.geoplatform.support.jackson.jts.serializer.geometry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.vividsolutions.jts.geom.Geometry;
import org.geosdi.geoplatform.support.jackson.jts.bridge.store.ImplementorStore;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.GeometryWriter;
import org.geosdi.geoplatform.support.jackson.jts.serializer.geometry.writer.bridge.implementor.GeometryWriterImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPGeoJsonGeometryHandler<G extends Geometry> implements IGPGeoJsonGeometryHandler<G> {

    private static final ImplementorStore<GeometryWriterImplementor> store = GeometryWriter.GEOMETRY_WRITER_IMPLEMENTOR_STORE;
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private IGPGeoJsonGeometryHandler successor;

    /**
     * @param geometry
     * @param jsonGenerator
     * @throws Exception
     */
    protected void forwardParseGeometry(Geometry geometry, JsonGenerator jsonGenerator)
            throws Exception {
        if (successor != null) {
            successor.parseGeometry(geometry, jsonGenerator);
        } else {
            throw new IllegalStateException("Geometry : " + geometry + " can't be parsed as GeoJson Geometry");
        }
    }

    /**
     * @return {@link GeometryWriterImplementor}
     * @throws Exception
     */
    protected GeometryWriterImplementor getGeometryWriterImplementor() throws Exception {
        return store.getImplementorByKey(getCompatibleGeometry());
    }

    /**
     * @param geometry
     * @return {@link Boolean}
     */
    protected abstract Boolean canParseGeometry(Geometry geometry);

    /**
     * @param theSuccessor
     */
    @Override
    public <Successor extends IGPGeoJsonGeometryHandler> void setSuccessor(Successor theSuccessor) {
        this.successor = theSuccessor;
    }
}
