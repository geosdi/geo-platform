/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.api.parser.base.parameter;

import org.geosdi.geoplatform.gml.api.parser.base.DefaultSRSBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.coordinate.CoordinateBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.curve.GMLBaseCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.GMLBaseLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.linerarring.GMLBaseLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.GMLBaseMultiCurveParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry.GMLBaseMultiGeometryParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line.GMLBaseMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.point.GMLBaseMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.polygon.GMLBaseMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface.GMLBaseMultiSurfaceParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.point.GMLBasePointParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterValue;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.store.BaseParameterValueStore;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.locationtech.jts.geom.GeometryFactory;

import static org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class GMLBaseParametersRepo {

    private static final GPImplementorStore<BaseParameterValue<? extends Object>> store = new BaseParameterValueStore();

    private GMLBaseParametersRepo() {
    }

    /**
     * @return {@link GeometryFactory}
     */
    public static GeometryFactory getDefaultGeometryFactory() {
        try {
            return (GeometryFactory) store.getImplementorByKey(DEFAULT_GEOMETRY_FACTORY).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link DefaultSRSBaseParser}
     */
    public static DefaultSRSBaseParser getDefaultSRSParser() {
        try {
            return (DefaultSRSBaseParser) store.getImplementorByKey(DEFAULT_SRS_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBasePointParser}
     */
    public static GMLBasePointParser getDefaultPointParser() {
        try {
            return (GMLBasePointParser) store.getImplementorByKey(DEFAULT_POINT_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseLineStringParser}
     */
    public static GMLBaseLineStringParser getDefaultLineStringParser() {
        try {
            return (GMLBaseLineStringParser) store.getImplementorByKey(DEFAULT_LINE_STRING_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseLinearRingParser}
     */
    public static GMLBaseLinearRingParser getDefaultLinearRingParser() {
        try {
            return (GMLBaseLinearRingParser) store.getImplementorByKey(DEFAULT_LINEAR_RING_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBasePolygonParser}
     */
    public static GMLBasePolygonParser getDefaultPolygonParser() {
        try {
            return (GMLBasePolygonParser) store.getImplementorByKey(DEFAULT_POLYGON_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiPointParser}
     */
    public static GMLBaseMultiPointParser getDefaultMultiPointParser() {
        try {
            return (GMLBaseMultiPointParser) store.getImplementorByKey(DEFAULT_MULTI_POINT_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiLineStringParser}
     */
    public static GMLBaseMultiLineStringParser getDefaultMultiLineStringParser() {
        try {
            return (GMLBaseMultiLineStringParser) store.getImplementorByKey(DEFAULT_MULTI_LINE_STRING_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiPolygonParser}
     */
    public static GMLBaseMultiPolygonParser getDefaultMultiPolygonParser() {
        try {
            return (GMLBaseMultiPolygonParser) store.getImplementorByKey(DEFAULT_MULTI_POLYGON_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiSurfaceParser}
     */
    public static GMLBaseMultiSurfaceParser getDefaultMultiSurfaceParser() {
        try {
            return (GMLBaseMultiSurfaceParser) store.getImplementorByKey(DEFAULT_MULTI_SURFACE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseSextanteParser}
     */
    public static GMLBaseSextanteParser getDefaultSextanteParser() {
        try {
            return (GMLBaseSextanteParser) store.getImplementorByKey(DEFAULT_SEXTANTE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiGeometryParser}
     */
    public static GMLBaseMultiGeometryParser getDefaultMultiGeometryParser() {
        try {
            return (GMLBaseMultiGeometryParser) store.getImplementorByKey(DEFAULT_MULTI_GEOMETRY_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link CoordinateBaseParser}
     */
    public static CoordinateBaseParser getDefaultCoordinateBaseParser() {
        try {
            return (CoordinateBaseParser) store.getImplementorByKey(DEFAULT_COORDINATE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseCurveParser}
     */
    public static GMLBaseCurveParser getDefaultCurveParser() {
        try {
            return (GMLBaseCurveParser) store.getImplementorByKey(DEFAULT_CURVE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link GMLBaseMultiCurveParser}
     */
    public static GMLBaseMultiCurveParser getDefaultMultiCurveParser() {
        try {
            return (GMLBaseMultiCurveParser) store.getImplementorByKey(DEFAULT_MULTI_CURVE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link String[]}
     */
    public static String[] getSRSFormatParameterPatterns() {
        try {
            return (String[]) store.getImplementorByKey(DEFAULT_SRS_PARAMETER_FORMAT).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}