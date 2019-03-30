package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.member;

import org.geosdi.geoplatform.gml.api.MultiSurface;
import org.geosdi.geoplatform.gml.api.SurfaceProperty;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Polygon;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SurfaceMember implements SurfaceMemberBuilder {

    protected final GMLBasePolygonParser polygonParser;

    /**
     * @param thePolygonParser
     */
    public SurfaceMember(@Nonnull(when = NEVER) GMLBasePolygonParser thePolygonParser) {
        checkArgument(thePolygonParser != null, "The Parameter polygonParser must not be null.");
        this.polygonParser = thePolygonParser;
    }

    /**
     * @param gmlGeometry
     * @param polygons
     * @throws ParserException
     */
    @Override
    public void buildMember(@Nonnull(when = NEVER) MultiSurface gmlGeometry, @Nonnull(when = NEVER) List<Polygon> polygons) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(polygons != null, "The Parameter polygons must not be null");
        if (gmlGeometry.isSetSurfaceMember()) {
            for (SurfaceProperty surfaceProperty : gmlGeometry.getSurfaceMember()) {
                org.geosdi.geoplatform.gml.api.Polygon abstractSurface = (org.geosdi.geoplatform.gml.api.Polygon) surfaceProperty.getAbstractSurface();
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractSurface.isSetSrsDimension()))
                    abstractSurface.setSrsDimension(gmlGeometry.getSrsDimension());
                polygons.add(polygonParser.parseGeometry(abstractSurface));
            }
        }
    }

    /**
     * @param gmlGeometry
     * @param polygons
     * @throws ParserException
     */
    @Override
    public void buildMemberAsGeoJson(@Nonnull(when = NEVER) MultiSurface gmlGeometry, @Nonnull(when = NEVER) List<org.geojson.Polygon> polygons) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(polygons != null, "The Parameter polygons must not be null");
        if (gmlGeometry.isSetSurfaceMember()) {
            for (SurfaceProperty surfaceProperty : gmlGeometry.getSurfaceMember()) {
                org.geosdi.geoplatform.gml.api.Polygon abstractSurface = (org.geosdi.geoplatform.gml.api.Polygon) surfaceProperty.getAbstractSurface();
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractSurface.isSetSrsDimension()))
                    abstractSurface.setSrsDimension(gmlGeometry.getSrsDimension());
                polygons.add(polygonParser.parseGeometryAsGeoJson(abstractSurface));
            }
        }
    }
}