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
package org.geosdi.geoplatform.gml.api.parser.jts.geometry.sextante.responsibility;

import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.gml.api.parser.jts.geometry.multi.line.JTSMultiLineStringParser;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiLineString;

import javax.xml.bind.JAXBElement;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractJTSSextanteMultiLineHandler extends JTSSextanteHandler {

    /**
     * @param geometry
     * @return {@link AbstractGeometry}
     * @throws ParserException
     */
    @Override
    public AbstractGeometry parseGeometry(Geometry geometry) throws ParserException {
        checkArgument(isCompatibleParser(), "The JTS Parser is not compatible. The parser must be an instance of "
                + "JTSMultiLineStringParser and not : " + acquireParser());
        return (isCompatibleGeometry(geometry) ? acquireParser().parseGeometry(geometry)
                : super.forwardParseGeometry(geometry));
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    protected boolean isCompatibleParser() {
        return (acquireParser() instanceof JTSMultiLineStringParser);
    }

    /**
     * @param jtsGeometry
     * @return {@link Boolean}
     */
    @Override
    protected boolean isCompatibleGeometry(Object jtsGeometry) {
        return (jtsGeometry instanceof MultiLineString);
    }

    /**
     * @param geometry
     * @return {@link JAXBElement<AbstractGeometry>}
     * @throws ParserException
     */
    @Override
    public JAXBElement<? extends AbstractGeometry> buildJAXBElement(Geometry geometry) throws ParserException {
        return (isCompatibleGeometry(geometry) ? acquireParser().buildJAXBElement(geometry)
                : super.forwardBuildJAXBElement(geometry));
    }
}