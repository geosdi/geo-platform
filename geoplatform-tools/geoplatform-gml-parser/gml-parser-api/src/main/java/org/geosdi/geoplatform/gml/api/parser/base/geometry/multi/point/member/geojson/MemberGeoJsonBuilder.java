package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.member.geojson;

import org.geojson.Point;
import org.geosdi.geoplatform.gml.api.MultiPoint;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MemberGeoJsonBuilder extends Serializable {

    /**
     * @param gmlGeometry
     * @param points
     * @throws ParserException
     */
    void builMemberAsGeoJson(@Nonnull(when = When.NEVER) MultiPoint gmlGeometry, @Nonnull(when = When.NEVER) List<Point> points) throws ParserException;
}