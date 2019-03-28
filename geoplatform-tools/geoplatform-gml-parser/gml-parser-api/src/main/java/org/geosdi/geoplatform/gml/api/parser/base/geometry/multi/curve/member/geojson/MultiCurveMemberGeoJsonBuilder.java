package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.member.geojson;

import org.geojson.LineString;
import org.geosdi.geoplatform.gml.api.MultiCurve;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.responsibility.LineStringHandler;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.responsibility.MultiCurveHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface MultiCurveMemberGeoJsonBuilder extends Serializable {

    MultiCurveHandler lineStringHandler = new LineStringHandler();

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    void buildGeoJsonMember(@Nonnull(when = NEVER) List<LineString> lines, @Nonnull(when = NEVER) MultiCurve gmlGeometry) throws ParserException;
}