/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.line;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import org.geosdi.geoplatform.gml.api.LineStringProperty;
import org.geosdi.geoplatform.gml.api.MultiLineString;
import org.geosdi.geoplatform.gml.api.MultiLineStringProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.line.GMLBaseLineStringParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiLineStringParser extends AbstractGMLBaseParser<MultiLineString, MultiLineStringProperty, com.vividsolutions.jts.geom.MultiLineString> {

    private GMLBaseLineStringParser lineStringParser;

    public GMLBaseMultiLineStringParser(GeometryFactory theGeometryFactory, AbstractGMLBaseSRSParser theSrsParser,
            GMLBaseLineStringParser theLineStringParser) {
        super(theGeometryFactory, theSrsParser);
        this.lineStringParser = theLineStringParser;
    }

    @Override
    protected com.vividsolutions.jts.geom.MultiLineString canParseGeometry(MultiLineString gmlGeometry)
            throws ParserException {
        Preconditions.checkState(gmlGeometry.isSetLineStringMember(), "The LineStringMember is not present.");
        List<LineString> lines = new ArrayList<>(gmlGeometry.getLineStringMember().size());
        for (LineStringProperty lineProperty : gmlGeometry.getLineStringMember()) {
            org.geosdi.geoplatform.gml.api.LineString lineString = lineProperty.getLineString();
            if ((gmlGeometry.isSetSrsDimension()) && !(lineString.isSetSrsDimension()))
                lineString.setSrsDimension(gmlGeometry.getSrsDimension());
            lines.add(lineStringParser.parseGeometry(lineString));
        }
        return geometryFactory.createMultiLineString(lines.toArray(new LineString[lines.size()]));
    }

    @Override
    public com.vividsolutions.jts.geom.MultiLineString parseGeometry(MultiLineStringProperty propertyType)
            throws ParserException {
        Preconditions.checkNotNull(propertyType, "The MultiLineString Property must not be null.");
        if (propertyType.isSetMultiLineString()) {
            return super.parseGeometry(propertyType.getMultiLineString());
        }
        throw new ParserException("There is no GML MultiLineString to Parse.");
    }
}
