package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.member;

import org.geosdi.geoplatform.gml.api.MultiCurve;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.member.geojson.MultiCurveMemberGeoJsonBuilder;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.LineString;

import javax.annotation.Nonnull;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MultiCurveMemberBuilder extends MultiCurveMemberGeoJsonBuilder {

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    void buildMember(@Nonnull(when = NEVER) List<LineString> lines, @Nonnull(when = NEVER) MultiCurve gmlGeometry) throws ParserException;
}