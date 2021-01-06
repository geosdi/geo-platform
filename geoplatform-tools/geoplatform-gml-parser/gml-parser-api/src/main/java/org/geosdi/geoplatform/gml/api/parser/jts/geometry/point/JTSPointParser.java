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
package org.geosdi.geoplatform.gml.api.parser.jts.geometry.point;

import org.geosdi.geoplatform.gml.api.DirectPosition;
import org.geosdi.geoplatform.gml.api.Point;
import org.geosdi.geoplatform.gml.api.PointProperty;
import org.geosdi.geoplatform.gml.api.jaxb.AbstractGMLObjectFactory;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.AbstractJTSSRSParser;
import org.geosdi.geoplatform.gml.api.parser.jts.coordinate.CoordinateParser;

import javax.xml.bind.JAXBElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class JTSPointParser extends AbstractJTSParser<Point, PointProperty, org.locationtech.jts.geom.Point> {

    private CoordinateParser coordinateParser;

    /**
     * @param theGmlObjectFactory
     * @param theSrsParser
     * @param theCoordinateParser
     */
    public JTSPointParser(AbstractGMLObjectFactory theGmlObjectFactory, AbstractJTSSRSParser theSrsParser,
            CoordinateParser theCoordinateParser) {
        super(theGmlObjectFactory, theSrsParser);
        this.coordinateParser = theCoordinateParser;
    }

    /**
     * @param jtsGeometry
     * @return {@link Point}
     * @throws ParserException
     */
    @Override
    protected Point canParseGeometry(org.locationtech.jts.geom.Point jtsGeometry) throws ParserException {
        Point point = gmlObjectFactory.createPointType();
        if (!jtsGeometry.isEmpty()) {
            DirectPosition directPosition = coordinateParser.parseCoordinate(jtsGeometry.getCoordinate());
            point.setPos(directPosition);
        }
        return point;
    }

    /**
     * @param jtsGeometry
     * @return {@link PointProperty}
     * @throws ParserException
     */
    @Override
    public PointProperty parseProperty(org.locationtech.jts.geom.Point jtsGeometry) throws ParserException {
        checkNotNull(jtsGeometry, "The JTS Point must not be null.");
        PointProperty pointProperty = gmlObjectFactory.createPointPropertyType();
        pointProperty.setPoint(super.parseGeometry(jtsGeometry));
        return pointProperty;
    }

    /**
     * @param geometry
     * @return {@link JAXBElement<? extends Point>}
     * @throws ParserException
     */
    @Override
    public JAXBElement<? extends Point> buildJAXBElement(org.locationtech.jts.geom.Point geometry)
            throws ParserException {
        return gmlObjectFactory.createPoint(super.parseGeometry(geometry));
    }
}