package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member;

import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.PointArrayProperty;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Point;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiPointMembersBuilder extends MultiPointMemberBuilder {

    /**
     * @param thePointParser
     */
    public MultiPointMembersBuilder(@Nonnull(when = NEVER) GMLBasePointParser thePointParser) {
        super(thePointParser);
    }

    /**
     * @param gmlGeometry
     * @param points
     * @throws ParserException
     */
    @Override
    public void builMember(@Nonnull(when = NEVER) MultiPoint gmlGeometry, @Nonnull(when = NEVER) List<Point> points) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(points != null, "The Parameter points must not be null.");
        if (gmlGeometry.isSetPointMembers()) {
            PointArrayProperty pointArrayProp = gmlGeometry.getPointMembers();
            for (org.geosdi.geoplatform.gml.api.Point point : pointArrayProp.getPoint()) {
                if ((gmlGeometry.isSetSrsDimension()) && !(point.isSetSrsDimension()))
                    point.setSrsDimension(gmlGeometry.getSrsDimension());
                points.add(pointParser.parseGeometry(point));
            }
        }
    }

    /**
     * @param gmlGeometry
     * @param points
     * @throws ParserException
     */
    @Override
    public void builMemberAsGeoJson(@Nonnull(when = When.NEVER) MultiPoint gmlGeometry, @Nonnull(when = When.NEVER) List<org.geojson.Point> points) throws ParserException {
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        checkArgument(points != null, "The Parameter points must not be null.");
        if (gmlGeometry.isSetPointMembers()) {
            PointArrayProperty pointArrayProp = gmlGeometry.getPointMembers();
            for (org.geosdi.geoplatform.gml.api.Point point : pointArrayProp.getPoint()) {
                if ((gmlGeometry.isSetSrsDimension()) && !(point.isSetSrsDimension()))
                    point.setSrsDimension(gmlGeometry.getSrsDimension());
                points.add(pointParser.parseGeometryAsGeoJson(point));
            }
        }
    }
}