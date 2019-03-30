package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.member;

import org.geosdi.geoplatform.gml.api.AbstractSurface;
import org.geosdi.geoplatform.gml.api.MultiSurface;
import org.geosdi.geoplatform.gml.api.SurfaceArrayProperty;
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
public class SurfaceMembers extends SurfaceMember {

    /**
     * @param thePolygonParser
     */
    public SurfaceMembers(@Nonnull(when = NEVER) GMLBasePolygonParser thePolygonParser) {
        super(thePolygonParser);
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
        if (gmlGeometry.isSetSurfaceMembers()) {
            SurfaceArrayProperty surfArrayProperty = gmlGeometry.getSurfaceMembers();
            for (AbstractSurface surface : surfArrayProperty.getAbstractSurface()) {
                if ((gmlGeometry.isSetSrsDimension()) && !(((org.geosdi.geoplatform.gml.api.Polygon) surface).isSetSrsDimension()))
                    ((org.geosdi.geoplatform.gml.api.Polygon) surface).setSrsDimension(gmlGeometry.getSrsDimension());
                polygons.add(polygonParser.parseGeometry((org.geosdi.geoplatform.gml.api.Polygon) surface));
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
        if (gmlGeometry.isSetSurfaceMembers()) {
            SurfaceArrayProperty surfArrayProperty = gmlGeometry.getSurfaceMembers();
            for (AbstractSurface surface : surfArrayProperty.getAbstractSurface()) {
                if ((gmlGeometry.isSetSrsDimension()) && !(((org.geosdi.geoplatform.gml.api.Polygon) surface).isSetSrsDimension()))
                    ((org.geosdi.geoplatform.gml.api.Polygon) surface).setSrsDimension(gmlGeometry.getSrsDimension());
                polygons.add(polygonParser.parseGeometryAsGeoJson((org.geosdi.geoplatform.gml.api.Polygon) surface));
            }
        }
    }
}