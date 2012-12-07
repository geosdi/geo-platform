/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.surface;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gml.api.AbstractSurface;
import org.geosdi.geoplatform.gml.api.MultiSurface;
import org.geosdi.geoplatform.gml.api.MultiSurfaceProperty;
import org.geosdi.geoplatform.gml.api.SurfaceArrayProperty;
import org.geosdi.geoplatform.gml.api.SurfaceProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.polygon.GMLBasePolygonParser;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiSurfaceParser extends AbstractGMLBaseParser<MultiSurface, MultiSurfaceProperty, MultiPolygon> {

    private GMLBasePolygonParser polygonParser;
    private SurfaceMemberBuilder surfaceMember = new SurfaceMember();
    private SurfaceMemberBuilder surfaceMembers = new SurfaceMembers();

    public GMLBaseMultiSurfaceParser(GeometryFactory theGeometryFactory,
            AbstractGMLBaseSRSParser theSrsParser,
            GMLBasePolygonParser thePolygonParser) {
        super(theGeometryFactory, theSrsParser);

        this.polygonParser = thePolygonParser;
    }

    @Override
    protected MultiPolygon canParseGeometry(MultiSurface gmlGeometry)
            throws ParserException {

        List<Polygon> polygons = new ArrayList<Polygon>();

        this.surfaceMember.buildMember(gmlGeometry, polygons);
        this.surfaceMembers.buildMember(gmlGeometry, polygons);

        Preconditions.checkArgument(!polygons.isEmpty(), "SurfaceMember and "
                + "SurfaceMembers can't be both null.");

        return geometryFactory.createMultiPolygon(polygons.toArray(
                new Polygon[polygons.size()]));
    }

    @Override
    public MultiPolygon parseGeometry(MultiSurfaceProperty propertyType)
            throws ParserException {

        Preconditions.checkNotNull(propertyType, "The MultiSurface Property "
                + "must not be null.");

        if (propertyType.isSetMultiSurface()) {
            return super.parseGeometry(propertyType.getMultiSurface());
        }

        throw new ParserException("There is no GML MultiSurface "
                + "Geometry to parse.");
    }

    protected interface SurfaceMemberBuilder {

        void buildMember(MultiSurface gmlGeometry,
                List<Polygon> polygon) throws ParserException;
    }

    protected class SurfaceMember implements SurfaceMemberBuilder {

        @Override
        public void buildMember(MultiSurface gmlGeometry,
                List<Polygon> polygon) throws ParserException {

            if (gmlGeometry.isSetSurfaceMember()) {
                for (SurfaceProperty surfaceProperty : gmlGeometry.getSurfaceMember()) {
                    polygon.add(
                            polygonParser.parseGeometry(
                            (org.geosdi.geoplatform.gml.api.Polygon) surfaceProperty.getAbstractSurface()));
                }
            }
        }
    }

    protected class SurfaceMembers implements SurfaceMemberBuilder {

        @Override
        public void buildMember(MultiSurface gmlGeometry,
                List<Polygon> polygon) throws ParserException {

            if (gmlGeometry.isSetSurfaceMembers()) {
                SurfaceArrayProperty surfArrayProperty = gmlGeometry.getSurfaceMembers();
                for (AbstractSurface surface : surfArrayProperty.getAbstractSurface()) {
                    polygon.add(polygonParser.parseGeometry(
                            (org.geosdi.geoplatform.gml.api.Polygon) surface));
                }
            }
        }
    }
}
