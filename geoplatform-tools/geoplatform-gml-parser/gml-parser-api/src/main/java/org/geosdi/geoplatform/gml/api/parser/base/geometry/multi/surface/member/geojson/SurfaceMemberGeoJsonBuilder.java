package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.member.geojson;

import org.geojson.Polygon;
import org.geosdi.geoplatform.gml.api.MultiSurface;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface SurfaceMemberGeoJsonBuilder extends Serializable {

    /**
     * @param gmlGeometry
     * @param polygons
     * @throws ParserException
     */
    void buildMemberAsGeoJson(@Nonnull(when = NEVER) MultiSurface gmlGeometry, @Nonnull(when = NEVER) List<Polygon> polygons) throws ParserException;
}