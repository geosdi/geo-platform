package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.member;

import org.geosdi.geoplatform.gml.api.AbstractCurve;
import org.geosdi.geoplatform.gml.api.CurveArrayProperty;
import org.geosdi.geoplatform.gml.api.MultiCurve;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.LineString;

import javax.annotation.Nonnull;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MultiCurveMembers implements MultiCurveMemberBuilder {

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    @Override
    public void buildMember(@Nonnull(when = NEVER) List<LineString> lines, @Nonnull(when = NEVER) MultiCurve gmlGeometry) throws ParserException {
        checkArgument(lines != null, "The Parameter lines must not be null.");
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        if (gmlGeometry.isSetCurveMembers()) {
            CurveArrayProperty curveArrayProperty = gmlGeometry.getCurveMembers();
            List<? extends AbstractCurve> abstractCurves = curveArrayProperty.getAbstractCurve();
            for (AbstractCurve abstractCurve : abstractCurves) {
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractCurve.isSetSrsName()))
                    abstractCurve.setSrsDimension(gmlGeometry.getSrsDimension());
                lineStringHandler.parseGeometry(lines, abstractCurve);
            }
        }
    }

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    @Override
    public void buildGeoJsonMember(@Nonnull(when = NEVER) List<org.geojson.LineString> lines, @Nonnull(when = NEVER) MultiCurve gmlGeometry) throws ParserException {
        checkArgument(lines != null, "The Parameter lines must not be null.");
        checkArgument(gmlGeometry != null, "The Parameter gmlGeometry must not be null.");
        if (gmlGeometry.isSetCurveMembers()) {
            CurveArrayProperty curveArrayProperty = gmlGeometry.getCurveMembers();
            List<? extends AbstractCurve> abstractCurves = curveArrayProperty.getAbstractCurve();
            for (AbstractCurve abstractCurve : abstractCurves) {
                if ((gmlGeometry.isSetSrsDimension()) && !(abstractCurve.isSetSrsName()))
                    abstractCurve.setSrsDimension(gmlGeometry.getSrsDimension());
                lineStringHandler.parseGeometryAsGeoJson(lines, abstractCurve);
            }
        }
    }
}