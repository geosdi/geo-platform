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
package org.geosdi.geoplatform.gml.impl.v311.jts.parameter;

import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parser.jts.DefaultJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.coordinate.JTSCoordinateParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.line.JTSLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.linearring.JTSLinearRingParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.geometry.JTSMultiGeometryParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.line.JTSMultiLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.point.JTSMultiPointParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.polygon.JTSMultiPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.point.JTSPointParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.polygon.JTSPolygonParser;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.sextante.JTSSextanteParser;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterEnum;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterValue;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.store.JTSParameterValueStore;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;

import static org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class JTSParametersRepo {

    private static final GPImplementorStore<JTSParameterValue<? extends Object>> store = new JTSParameterValueStore();

    private JTSParametersRepo() {
    }

    /**
     * @return {@link AbstractGMLObjectFactory}
     */
    public static AbstractGMLObjectFactory getDefaultGmlObjectFactory() {
        try {
            return (AbstractGMLObjectFactory) store.getImplementorByKey(DEFAULT_OBJECT_FACTORY).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link DefaultJTSSRSParser}
     */
    public static DefaultJTSSRSParser getDefaultSRSParser() {
        try {
            return (DefaultJTSSRSParser) store.getImplementorByKey(JTSParameterEnum.DEFAULT_JTS_SRS_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSCoordinateParser}
     */
    public static JTSCoordinateParser getDefaultCoordinateParser() {
        try {
            return (JTSCoordinateParser) store.getImplementorByKey(DEFAULT_JTS_COORDINATE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSPointParser}
     */
    public static JTSPointParser getDefaultPointParser() {
        try {
            return (JTSPointParser) store.getImplementorByKey(DEFAULT_JTS_POINT_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSLineStringParser}
     */
    public static JTSLineStringParser getDefaultLineStringParser() {
        try {
            return (JTSLineStringParser) store.getImplementorByKey(DEFAULT_JTS_LINE_STRING_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSLinearRingParser}
     */
    public static JTSLinearRingParser getDefaultLinearRingParser() {
        try {
            return (JTSLinearRingParser) store.getImplementorByKey(DEFAULT_JTS_LINEAR_RING_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSPolygonParser}
     */
    public static JTSPolygonParser getDefaultPolygonParser() {
        try {
            return (JTSPolygonParser) store.getImplementorByKey(DEFAULT_JTS_POLYGON_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSMultiPointParser}
     */
    public static JTSMultiPointParser getDefaultMultiPointParser() {
        try {
            return (JTSMultiPointParser) store.getImplementorByKey(DEFAULT_JTS_MULTI_POINT_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSMultiLineStringParser}
     */
    public static JTSMultiLineStringParser getDefaultMultiLineParser() {
        try {
            return (JTSMultiLineStringParser) store.getImplementorByKey(DEFAULT_JTS_MULTI_LINE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSMultiPolygonParser}
     */
    public static JTSMultiPolygonParser getDefaultMultiPolygonParser() {
        try {
            return (JTSMultiPolygonParser) store.getImplementorByKey(DEFAULT_JTS_MULTI_POLYGON_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSMultiGeometryParser}
     */
    public static JTSMultiGeometryParser getDefaultMultiGeometryParser() {
        try {
            return (JTSMultiGeometryParser) store.getImplementorByKey(DEFAULT_JTS_MULTI_GEOMETRY_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link JTSSextanteParser}
     */
    public static JTSSextanteParser getDefaultSextanteParser() {
        try {
            return (JTSSextanteParser) store.getImplementorByKey(DEFAULT_JTS_SEXTANTE_PARSER).getValue();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}