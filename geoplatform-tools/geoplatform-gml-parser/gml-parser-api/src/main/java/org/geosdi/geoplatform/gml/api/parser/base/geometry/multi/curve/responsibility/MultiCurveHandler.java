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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.responsibility;

import org.geosdi.geoplatform.gml.api.AbstractCurve;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.curve.responsibility.geojson.MultiCurveGeoJsonHandler;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.locationtech.jts.geom.LineString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class MultiCurveHandler extends MultiCurveGeoJsonHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected MultiCurveHandler successor;

    MultiCurveHandler() {
    }

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    public abstract void parseGeometry(List<LineString> lines, AbstractCurve gmlGeometry) throws ParserException;

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    protected void forwardParseGeometry(List<LineString> lines, AbstractCurve gmlGeometry) throws ParserException {
        if (successor != null) {
            successor.parseGeometry(lines, gmlGeometry);
        } else {
            throw new ParserException("There are no Rings in Chain to parse this unespected Curve Type : " + gmlGeometry);
        }
    }

    /**
     * @param lines
     * @param gmlGeometry
     * @throws ParserException
     */
    @Override
    protected void forwardParseGeometryAsGeoJson(List<org.geojson.LineString> lines, AbstractCurve gmlGeometry) throws ParserException {
        if (successor != null) {
            successor.parseGeometryAsGeoJson(lines, gmlGeometry);
        } else {
            throw new ParserException("There are no Rings in Chain to parse this unespected Curve Type : " + gmlGeometry);
        }
    }

    /**
     * @param gmlGeometry
     * @return {@link Boolean}
     */
    protected abstract boolean isCompatibleGeometry(Object gmlGeometry);

    /**
     * @param successor the successor to set
     */
    public void setSuccessor(MultiCurveHandler successor) {
        this.successor = successor;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {" + "successor = "
                + successor + '}';
    }
}