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
package org.geosdi.geoplatform.gml.api.parser.base.geometry.multi.geometry;

import com.google.common.base.Preconditions;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gml.api.AbstractGeometricAggregate;
import org.geosdi.geoplatform.gml.api.AbstractGeometry;
import org.geosdi.geoplatform.gml.api.GeometryArrayProperty;
import org.geosdi.geoplatform.gml.api.GeometryProperty;
import org.geosdi.geoplatform.gml.api.MultiGeometry;
import org.geosdi.geoplatform.gml.api.MultiGeometryProperty;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseParser;
import org.geosdi.geoplatform.gml.api.parser.base.AbstractGMLBaseSRSParser;
import org.geosdi.geoplatform.gml.api.parser.base.geometry.sextante.GMLBaseSextanteParser;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.BaseParameterEnum;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.GMLBaseParametersRepo;
import org.geosdi.geoplatform.gml.api.parser.exception.ParserException;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GMLBaseMultiGeometryParser extends AbstractGMLBaseParser<MultiGeometry, MultiGeometryProperty, GeometryCollection> {

    private GMLBaseSextanteParser geometryParser = GMLBaseParametersRepo.getDefaultSextanteParser();
    private MemberBuilder multiGeometryMember = new MultiGeometryMember();
    private MemberBuilder multiGeometryMembers = new MultiGeometryMembers();

    public GMLBaseMultiGeometryParser(GeometryFactory theGeometryFactory,
            AbstractGMLBaseSRSParser theSrsParser) {
        super(theGeometryFactory, theSrsParser);
    }

    public GMLBaseMultiGeometryParser() {
        this(GMLBaseParametersRepo.getDefaultGeometryFactory(),
                GMLBaseParametersRepo.getDefaultSRSParser());
    }

    @Override
    protected GeometryCollection canParseGeometry(MultiGeometry gmlGeometry)
            throws ParserException {

        List<Geometry> geometries = new ArrayList<Geometry>();

        this.multiGeometryMember.builMember(gmlGeometry, geometries);
        this.multiGeometryMember.builMember(gmlGeometry, geometries);

        Preconditions.checkArgument(!geometries.isEmpty(),
                "GeometryMember and GeometryMembers "
                + "can't be both null.");

        return geometryFactory.createGeometryCollection(geometries.toArray(
                new Geometry[geometries.size()]));

    }

    @Override
    public GeometryCollection parseGeometry(MultiGeometryProperty propertyType)
            throws ParserException {
        Preconditions.checkNotNull(propertyType, "The MultiGeometry Property"
                + " must be not null.");

        if (propertyType.isSetGeometricAggregate()) {
            AbstractGeometricAggregate geometryAggregate = propertyType.getAbstractGeometricAggregate();

            if (geometryAggregate instanceof MultiGeometry) {
                return super.parseGeometry((MultiGeometry) geometryAggregate);
            }
        }

        throw new ParserException("There is no GML MultiGeometry to parse.");
    }

    protected interface MemberBuilder {

        void builMember(MultiGeometry gmlGeometry,
                List<Geometry> geometries) throws ParserException;
    }

    protected class MultiGeometryMember implements MemberBuilder {

        @Override
        public void builMember(MultiGeometry gmlGeometry,
                List<Geometry> geometries) throws ParserException {

            if (gmlGeometry.isSetGeometryMember()) {
                for (GeometryProperty property : gmlGeometry.getGeometryMember()) {
                    geometries.add(geometryParser.parseGeometry(
                            property.getAbstractGeometry()));
                }
            }
        }
    }

    protected class MultiGeometryMembers implements MemberBuilder {

        @Override
        public void builMember(MultiGeometry gmlGeometry,
                List<Geometry> geometries) throws ParserException {

            if (gmlGeometry.isSetGeometryMembers()) {
                GeometryArrayProperty geometryArrayProperty = gmlGeometry.getGeometryMembers();

                for (AbstractGeometry geometry : geometryArrayProperty.getAbstractGeometry()) {
                    geometries.add(geometryParser.parseGeometry(geometry));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "GMLBaseMultiGeometryParser{" + "geometryParser=" + geometryParser + '}';
    }
}
