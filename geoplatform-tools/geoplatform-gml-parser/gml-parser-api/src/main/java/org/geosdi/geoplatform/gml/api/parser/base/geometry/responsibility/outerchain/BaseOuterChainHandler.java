package org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.outerchain;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.responsibility.BaseGeometryHandler;
import org.locationtech.jts.geom.Geometry;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class BaseOuterChainHandler<A extends AbstractGeometry, G extends Geometry, P extends AbstractParser, GeoJson extends GeoJsonObject>
        extends BaseGeometryHandler<A, G, P, GeoJson> {

    protected final AbstractGMLBaseSRSParser srsParser;

    /**
     * @param theSRSParser
     */
    protected BaseOuterChainHandler(@Nonnull(when = NEVER) AbstractGMLBaseSRSParser theSRSParser) {
        checkArgument(theSRSParser != null, "The Parameter srsParser must not be null.");
        this.srsParser = theSRSParser;
    }
}