package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.member;

import org.geojson.GeoJsonObject;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.GeometryProperty;
import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Geometry;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiGeometryMember implements MemberBuilder {

    /**
     * @param gmlGeometry
     * @param geometries
     * @throws ParserException
     */
    @Override
    public void builMember(@Nonnull(when = NEVER) MultiGeometry gmlGeometry, @Nonnull(when = NEVER) List<Geometry> geometries) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(geometries != null, "The Parameter geometries must not be null.");
        if (gmlGeometry.isSetGeometryMember()) {
            for (GeometryProperty property : gmlGeometry.getGeometryMember()) {
                AbstractGeometry abstractGeometry = property.getAbstractGeometry();
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractGeometry.isSetSrsDimension()))
                    abstractGeometry.setSrsDimension(gmlGeometry.getSrsDimension());
                geometries.add(geometryParser.parseGeometry(abstractGeometry));
            }
        }
    }

    /**
     * @param gmlGeometry
     * @param geometries
     * @throws ParserException
     */
    @Override
    public void builMemberAsGeoJson(@Nonnull(when = NEVER) MultiGeometry gmlGeometry, @Nonnull(when = NEVER) List<GeoJsonObject> geometries) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(geometries != null, "The Parameter geometries must not be null.");
        if (gmlGeometry.isSetGeometryMember()) {
            for (GeometryProperty property : gmlGeometry.getGeometryMember()) {
                AbstractGeometry abstractGeometry = property.getAbstractGeometry();
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractGeometry.isSetSrsDimension()))
                    abstractGeometry.setSrsDimension(gmlGeometry.getSrsDimension());
                geometries.add(geometryParser.parseGeometryAsGeoJson(abstractGeometry));
            }
        }
    }
}
