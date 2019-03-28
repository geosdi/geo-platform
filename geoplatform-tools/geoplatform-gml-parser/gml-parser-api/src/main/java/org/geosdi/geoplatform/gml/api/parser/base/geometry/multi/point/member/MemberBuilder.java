package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member;

import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member.geojson.MemberGeoJsonBuilder;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.Point;

import javax.annotation.Nonnull;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MemberBuilder extends MemberGeoJsonBuilder {

    /**
     * @param gmlGeometry
     * @param points
     * @throws ParserException
     */
    void builMember(@Nonnull(when = NEVER) MultiPoint gmlGeometry, @Nonnull(when = NEVER) List<Point> points) throws ParserException;
}