package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.member;

import org.geosdi.geoplatform.gml.api.MultiSurface;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.member.geojson.SurfaceMemberGeoJsonBuilder;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface SurfaceMemberBuilder extends SurfaceMemberGeoJsonBuilder {

    /**
     * @param gmlGeometry
     * @param polygons
     * @throws ParserException
     */
    void buildMember(@Nonnull(when = NEVER) MultiSurface gmlGeometry, @Nonnull(when = NEVER) List<Polygon> polygons) throws ParserException;
}