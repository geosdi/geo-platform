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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.gml.v320 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StatusReference_QNAME = new QName("http://www.opengis.net/gml", "statusReference");
    private final static QName _SourceDimensions_QNAME = new QName("http://www.opengis.net/gml", "sourceDimensions");
    private final static QName _DynamicFeatureCollection_QNAME = new QName("http://www.opengis.net/gml", "DynamicFeatureCollection");
    private final static QName _BSpline_QNAME = new QName("http://www.opengis.net/gml", "BSpline");
    private final static QName _Identifier_QNAME = new QName("http://www.opengis.net/gml", "identifier");
    private final static QName _SourceCRS_QNAME = new QName("http://www.opengis.net/gml", "sourceCRS");
    private final static QName _UsesCartesianCS_QNAME = new QName("http://www.opengis.net/gml", "usesCartesianCS");
    private final static QName _LinearRing_QNAME = new QName("http://www.opengis.net/gml", "LinearRing");
    private final static QName _DefinitionCollection_QNAME = new QName("http://www.opengis.net/gml", "DefinitionCollection");
    private final static QName _CylindricalCS_QNAME = new QName("http://www.opengis.net/gml", "CylindricalCS");
    private final static QName _AbstractGML_QNAME = new QName("http://www.opengis.net/gml", "AbstractGML");
    private final static QName _MultiSolidProperty_QNAME = new QName("http://www.opengis.net/gml", "multiSolidProperty");
    private final static QName _UsesTimeCS_QNAME = new QName("http://www.opengis.net/gml", "usesTimeCS");
    private final static QName _ParameterValueGroup_QNAME = new QName("http://www.opengis.net/gml", "ParameterValueGroup");
    private final static QName _UnitOfMeasure_QNAME = new QName("http://www.opengis.net/gml", "unitOfMeasure");
    private final static QName _MaximalComplex_QNAME = new QName("http://www.opengis.net/gml", "maximalComplex");
    private final static QName _TimeTopologyComplex_QNAME = new QName("http://www.opengis.net/gml", "TimeTopologyComplex");
    private final static QName _Track_QNAME = new QName("http://www.opengis.net/gml", "track");
    private final static QName _ArcByCenterPoint_QNAME = new QName("http://www.opengis.net/gml", "ArcByCenterPoint");
    private final static QName _PosList_QNAME = new QName("http://www.opengis.net/gml", "posList");
    private final static QName _TimeInterval_QNAME = new QName("http://www.opengis.net/gml", "timeInterval");
    private final static QName _MetaDataProperty_QNAME = new QName("http://www.opengis.net/gml", "metaDataProperty");
    private final static QName _AbstractOperation_QNAME = new QName("http://www.opengis.net/gml", "AbstractOperation");
    private final static QName _Category_QNAME = new QName("http://www.opengis.net/gml", "Category");
    private final static QName _MultiSolidDomain_QNAME = new QName("http://www.opengis.net/gml", "multiSolidDomain");
    private final static QName _AssociationName_QNAME = new QName("http://www.opengis.net/gml", "associationName");
    private final static QName _AbstractTimePrimitive_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimePrimitive");
    private final static QName _MultiSolid_QNAME = new QName("http://www.opengis.net/gml", "MultiSolid");
    private final static QName _VerticalDatumRef_QNAME = new QName("http://www.opengis.net/gml", "verticalDatumRef");
    private final static QName _OrientableCurve_QNAME = new QName("http://www.opengis.net/gml", "OrientableCurve");
    private final static QName _MultiPosition_QNAME = new QName("http://www.opengis.net/gml", "multiPosition");
    private final static QName _SurfaceMember_QNAME = new QName("http://www.opengis.net/gml", "surfaceMember");
    private final static QName _DefinitionRef_QNAME = new QName("http://www.opengis.net/gml", "definitionRef");
    private final static QName _DataSource_QNAME = new QName("http://www.opengis.net/gml", "dataSource");
    private final static QName _Solid_QNAME = new QName("http://www.opengis.net/gml", "Solid");
    private final static QName _MultiCurveCoverage_QNAME = new QName("http://www.opengis.net/gml", "MultiCurveCoverage");
    private final static QName _MultiGeometry_QNAME = new QName("http://www.opengis.net/gml", "MultiGeometry");
    private final static QName _TopoComplex_QNAME = new QName("http://www.opengis.net/gml", "TopoComplex");
    private final static QName _CoverageMappingRule_QNAME = new QName("http://www.opengis.net/gml", "CoverageMappingRule");
    private final static QName _CoordinateSystem_QNAME = new QName("http://www.opengis.net/gml", "coordinateSystem");
    private final static QName _MultiCurveProperty_QNAME = new QName("http://www.opengis.net/gml", "multiCurveProperty");
    private final static QName _PolygonPatches_QNAME = new QName("http://www.opengis.net/gml", "polygonPatches");
    private final static QName _OperationParameterGroupRef_QNAME = new QName("http://www.opengis.net/gml", "operationParameterGroupRef");
    private final static QName _TimePeriod_QNAME = new QName("http://www.opengis.net/gml", "TimePeriod");
    private final static QName _EngineeringDatumRef_QNAME = new QName("http://www.opengis.net/gml", "engineeringDatumRef");
    private final static QName _AbstractCurve_QNAME = new QName("http://www.opengis.net/gml", "AbstractCurve");
    private final static QName _Clothoid_QNAME = new QName("http://www.opengis.net/gml", "Clothoid");
    private final static QName _AbstractValue_QNAME = new QName("http://www.opengis.net/gml", "AbstractValue");
    private final static QName _AbstractTopology_QNAME = new QName("http://www.opengis.net/gml", "AbstractTopology");
    private final static QName _VerticalCRSRef_QNAME = new QName("http://www.opengis.net/gml", "verticalCRSRef");
    private final static QName _AbstractGeometricPrimitive_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeometricPrimitive");
    private final static QName _DefaultCodeSpace_QNAME = new QName("http://www.opengis.net/gml", "defaultCodeSpace");
    private final static QName _TimeCoordinateSystem_QNAME = new QName("http://www.opengis.net/gml", "TimeCoordinateSystem");
    private final static QName _GeometryMember_QNAME = new QName("http://www.opengis.net/gml", "geometryMember");
    private final static QName _Null_QNAME = new QName("http://www.opengis.net/gml", "Null");
    private final static QName _TopoCurve_QNAME = new QName("http://www.opengis.net/gml", "TopoCurve");
    private final static QName _ProjectedCRSRef_QNAME = new QName("http://www.opengis.net/gml", "projectedCRSRef");
    private final static QName _MultiEdgeOf_QNAME = new QName("http://www.opengis.net/gml", "multiEdgeOf");
    private final static QName _TimeNode_QNAME = new QName("http://www.opengis.net/gml", "TimeNode");
    private final static QName _Curve_QNAME = new QName("http://www.opengis.net/gml", "Curve");
    private final static QName _SemiMajorAxis_QNAME = new QName("http://www.opengis.net/gml", "semiMajorAxis");
    private final static QName _CurveMembers_QNAME = new QName("http://www.opengis.net/gml", "curveMembers");
    private final static QName _LocationName_QNAME = new QName("http://www.opengis.net/gml", "locationName");
    private final static QName _Array_QNAME = new QName("http://www.opengis.net/gml", "Array");
    private final static QName _Pos_QNAME = new QName("http://www.opengis.net/gml", "pos");
    private final static QName _AbstractObject_QNAME = new QName("http://www.opengis.net/gml", "AbstractObject");
    private final static QName _GeocentricCRS_QNAME = new QName("http://www.opengis.net/gml", "GeocentricCRS");
    private final static QName _AbstractGriddedSurface_QNAME = new QName("http://www.opengis.net/gml", "AbstractGriddedSurface");
    private final static QName _ValueProperty_QNAME = new QName("http://www.opengis.net/gml", "valueProperty");
    private final static QName _CartesianCS_QNAME = new QName("http://www.opengis.net/gml", "CartesianCS");
    private final static QName _Exterior_QNAME = new QName("http://www.opengis.net/gml", "exterior");
    private final static QName _EllipsoidPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "ellipsoid");
    private final static QName _UsesValue_QNAME = new QName("http://www.opengis.net/gml", "usesValue");
    private final static QName _GenericMetaData_QNAME = new QName("http://www.opengis.net/gml", "GenericMetaData");
    private final static QName _CategoryList_QNAME = new QName("http://www.opengis.net/gml", "CategoryList");
    private final static QName _IncludesSingleCRS_QNAME = new QName("http://www.opengis.net/gml", "includesSingleCRS");
    private final static QName _AbstractTimeTopologyPrimitive_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimeTopologyPrimitive");
    private final static QName _GridDomain_QNAME = new QName("http://www.opengis.net/gml", "gridDomain");
    private final static QName _DoubleOrNilReasonTupleList_QNAME = new QName("http://www.opengis.net/gml", "doubleOrNilReasonTupleList");
    private final static QName _MultiSurface_QNAME = new QName("http://www.opengis.net/gml", "MultiSurface");
    private final static QName _MinimumOccurs_QNAME = new QName("http://www.opengis.net/gml", "minimumOccurs");
    private final static QName _Angle_QNAME = new QName("http://www.opengis.net/gml", "angle");
    private final static QName _AxisAbbrev_QNAME = new QName("http://www.opengis.net/gml", "axisAbbrev");
    private final static QName _CurveMember_QNAME = new QName("http://www.opengis.net/gml", "curveMember");
    private final static QName _DecimalMinutes_QNAME = new QName("http://www.opengis.net/gml", "decimalMinutes");
    private final static QName _TopoPrimitiveMembers_QNAME = new QName("http://www.opengis.net/gml", "topoPrimitiveMembers");
    private final static QName _IncludesValue_QNAME = new QName("http://www.opengis.net/gml", "includesValue");
    private final static QName _ProjectedCRS_QNAME = new QName("http://www.opengis.net/gml", "ProjectedCRS");
    private final static QName _OperationMethodRef_QNAME = new QName("http://www.opengis.net/gml", "operationMethodRef");
    private final static QName _Tin_QNAME = new QName("http://www.opengis.net/gml", "Tin");
    private final static QName _EngineeringCRSRef_QNAME = new QName("http://www.opengis.net/gml", "engineeringCRSRef");
    private final static QName _TopoCurveProperty_QNAME = new QName("http://www.opengis.net/gml", "topoCurveProperty");
    private final static QName _Face_QNAME = new QName("http://www.opengis.net/gml", "Face");
    private final static QName _TimeOrdinalEra_QNAME = new QName("http://www.opengis.net/gml", "TimeOrdinalEra");
    private final static QName _DomainSet_QNAME = new QName("http://www.opengis.net/gml", "domainSet");
    private final static QName _EngineeringDatumPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "engineeringDatum");
    private final static QName _DefinitionProxy_QNAME = new QName("http://www.opengis.net/gml", "DefinitionProxy");
    private final static QName _Seconds_QNAME = new QName("http://www.opengis.net/gml", "seconds");
    private final static QName _CubicSpline_QNAME = new QName("http://www.opengis.net/gml", "CubicSpline");
    private final static QName _ResultOf_QNAME = new QName("http://www.opengis.net/gml", "resultOf");
    private final static QName _AbstractTimeSlice_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimeSlice");
    private final static QName _DerivedCRS_QNAME = new QName("http://www.opengis.net/gml", "DerivedCRS");
    private final static QName _ConversionPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "conversion");
    private final static QName _AbstractDiscreteCoverage_QNAME = new QName("http://www.opengis.net/gml", "AbstractDiscreteCoverage");
    private final static QName _PrimeMeridianPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "primeMeridian");
    private final static QName _CenterLineOf_QNAME = new QName("http://www.opengis.net/gml", "centerLineOf");
    private final static QName _GreenwichLongitude_QNAME = new QName("http://www.opengis.net/gml", "greenwichLongitude");
    private final static QName _TargetCRS_QNAME = new QName("http://www.opengis.net/gml", "targetCRS");
    private final static QName _ReversePropertyName_QNAME = new QName("http://www.opengis.net/gml", "reversePropertyName");
    private final static QName _TemporalCRSRef_QNAME = new QName("http://www.opengis.net/gml", "temporalCRSRef");
    private final static QName _ComponentReferenceSystem_QNAME = new QName("http://www.opengis.net/gml", "componentReferenceSystem");
    private final static QName _AbstractCRS_QNAME = new QName("http://www.opengis.net/gml", "AbstractCRS");
    private final static QName _OperationParameterGroup_QNAME = new QName("http://www.opengis.net/gml", "OperationParameterGroup");
    private final static QName _ArcString_QNAME = new QName("http://www.opengis.net/gml", "ArcString");
    private final static QName _MultiExtentOf_QNAME = new QName("http://www.opengis.net/gml", "multiExtentOf");
    private final static QName _UsesObliqueCartesianCS_QNAME = new QName("http://www.opengis.net/gml", "usesObliqueCartesianCS");
    private final static QName _TopoPoint_QNAME = new QName("http://www.opengis.net/gml", "TopoPoint");
    private final static QName _OperationParameterPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "operationParameter");
    private final static QName _MultiSurfaceDomain_QNAME = new QName("http://www.opengis.net/gml", "multiSurfaceDomain");
    private final static QName _MethodFormula_QNAME = new QName("http://www.opengis.net/gml", "methodFormula");
    private final static QName _GeodeticDatum_QNAME = new QName("http://www.opengis.net/gml", "GeodeticDatum");
    private final static QName _Status_QNAME = new QName("http://www.opengis.net/gml", "status");
    private final static QName _CoordinateSystemRef_QNAME = new QName("http://www.opengis.net/gml", "coordinateSystemRef");
    private final static QName _QuantityTypeReference_QNAME = new QName("http://www.opengis.net/gml", "quantityTypeReference");
    private final static QName _CompositeCurve_QNAME = new QName("http://www.opengis.net/gml", "CompositeCurve");
    private final static QName _ImageDatumPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "imageDatum");
    private final static QName _BoundedBy_QNAME = new QName("http://www.opengis.net/gml", "boundedBy");
    private final static QName _UsesVerticalDatum_QNAME = new QName("http://www.opengis.net/gml", "usesVerticalDatum");
    private final static QName _ModifiedCoordinate_QNAME = new QName("http://www.opengis.net/gml", "modifiedCoordinate");
    private final static QName _AbstractSingleCRS_QNAME = new QName("http://www.opengis.net/gml", "AbstractSingleCRS");
    private final static QName _DirectedObservation_QNAME = new QName("http://www.opengis.net/gml", "DirectedObservation");
    private final static QName _RangeMeaning_QNAME = new QName("http://www.opengis.net/gml", "rangeMeaning");
    private final static QName _MultiPointDomain_QNAME = new QName("http://www.opengis.net/gml", "multiPointDomain");
    private final static QName _ConcatenatedOperation_QNAME = new QName("http://www.opengis.net/gml", "ConcatenatedOperation");
    private final static QName _GeneralConversionRef_QNAME = new QName("http://www.opengis.net/gml", "generalConversionRef");
    private final static QName _UsesAffineCS_QNAME = new QName("http://www.opengis.net/gml", "usesAffineCS");
    private final static QName _AbstractParametricCurveSurface_QNAME = new QName("http://www.opengis.net/gml", "AbstractParametricCurveSurface");
    private final static QName _CircleByCenterPoint_QNAME = new QName("http://www.opengis.net/gml", "CircleByCenterPoint");
    private final static QName _Sphere_QNAME = new QName("http://www.opengis.net/gml", "Sphere");
    private final static QName _SolidArrayProperty_QNAME = new QName("http://www.opengis.net/gml", "solidArrayProperty");
    private final static QName _CartesianCSRef_QNAME = new QName("http://www.opengis.net/gml", "cartesianCSRef");
    private final static QName _AbstractSurfacePatch_QNAME = new QName("http://www.opengis.net/gml", "AbstractSurfacePatch");
    private final static QName _ImageCRSRef_QNAME = new QName("http://www.opengis.net/gml", "imageCRSRef");
    private final static QName _Origin_QNAME = new QName("http://www.opengis.net/gml", "origin");
    private final static QName _PointMembers_QNAME = new QName("http://www.opengis.net/gml", "pointMembers");
    private final static QName _Degrees_QNAME = new QName("http://www.opengis.net/gml", "degrees");
    private final static QName _Quantity_QNAME = new QName("http://www.opengis.net/gml", "Quantity");
    private final static QName _Minutes_QNAME = new QName("http://www.opengis.net/gml", "minutes");
    private final static QName _File_QNAME = new QName("http://www.opengis.net/gml", "File");
    private final static QName _ImageDatumRef_QNAME = new QName("http://www.opengis.net/gml", "imageDatumRef");
    private final static QName _DataBlock_QNAME = new QName("http://www.opengis.net/gml", "DataBlock");
    private final static QName _PolarCS_QNAME = new QName("http://www.opengis.net/gml", "PolarCS");
    private final static QName _EnvelopeWithTimePeriod_QNAME = new QName("http://www.opengis.net/gml", "EnvelopeWithTimePeriod");
    private final static QName _TopoVolume_QNAME = new QName("http://www.opengis.net/gml", "TopoVolume");
    private final static QName _ArcByBulge_QNAME = new QName("http://www.opengis.net/gml", "ArcByBulge");
    private final static QName _Target_QNAME = new QName("http://www.opengis.net/gml", "target");
    private final static QName _AbstractGeneralConversion_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeneralConversion");
    private final static QName _VerticalCSRef_QNAME = new QName("http://www.opengis.net/gml", "verticalCSRef");
    private final static QName _CompoundCRSRef_QNAME = new QName("http://www.opengis.net/gml", "compoundCRSRef");
    private final static QName _Formula_QNAME = new QName("http://www.opengis.net/gml", "formula");
    private final static QName _Group_QNAME = new QName("http://www.opengis.net/gml", "group");
    private final static QName _ValuesOfGroup_QNAME = new QName("http://www.opengis.net/gml", "valuesOfGroup");
    private final static QName _MappingRule_QNAME = new QName("http://www.opengis.net/gml", "MappingRule");
    private final static QName _DataSourceReference_QNAME = new QName("http://www.opengis.net/gml", "dataSourceReference");
    private final static QName _OperationMethod_QNAME = new QName("http://www.opengis.net/gml", "OperationMethod");
    private final static QName _DmsAngle_QNAME = new QName("http://www.opengis.net/gml", "dmsAngle");
    private final static QName _DefinedByConversion_QNAME = new QName("http://www.opengis.net/gml", "definedByConversion");
    private final static QName _Count_QNAME = new QName("http://www.opengis.net/gml", "Count");
    private final static QName _Direction_QNAME = new QName("http://www.opengis.net/gml", "direction");
    private final static QName _TimeCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "timeCS");
    private final static QName _DirectedNode_QNAME = new QName("http://www.opengis.net/gml", "directedNode");
    private final static QName _TopoPrimitiveMember_QNAME = new QName("http://www.opengis.net/gml", "topoPrimitiveMember");
    private final static QName _AbstractScalarValueList_QNAME = new QName("http://www.opengis.net/gml", "AbstractScalarValueList");
    private final static QName _CompoundCRS_QNAME = new QName("http://www.opengis.net/gml", "CompoundCRS");
    private final static QName _MultiSurfaceCoverage_QNAME = new QName("http://www.opengis.net/gml", "MultiSurfaceCoverage");
    private final static QName _AbstractReference_QNAME = new QName("http://www.opengis.net/gml", "abstractReference");
    private final static QName _MultiPoint_QNAME = new QName("http://www.opengis.net/gml", "MultiPoint");
    private final static QName _TargetElement_QNAME = new QName("http://www.opengis.net/gml", "targetElement");
    private final static QName _GeodeticDatumRef_QNAME = new QName("http://www.opengis.net/gml", "geodeticDatumRef");
    private final static QName _Vector_QNAME = new QName("http://www.opengis.net/gml", "vector");
    private final static QName _AxisDirection_QNAME = new QName("http://www.opengis.net/gml", "axisDirection");
    private final static QName _MovingObjectStatus_QNAME = new QName("http://www.opengis.net/gml", "MovingObjectStatus");
    private final static QName _MultiCenterLineOf_QNAME = new QName("http://www.opengis.net/gml", "multiCenterLineOf");
    private final static QName _Ellipsoid_QNAME = new QName("http://www.opengis.net/gml", "Ellipsoid");
    private final static QName _MultiPointCoverage_QNAME = new QName("http://www.opengis.net/gml", "MultiPointCoverage");
    private final static QName _DynamicFeature_QNAME = new QName("http://www.opengis.net/gml", "DynamicFeature");
    private final static QName _DirectedTopoSolid_QNAME = new QName("http://www.opengis.net/gml", "directedTopoSolid");
    private final static QName _BaseGeographicCRS_QNAME = new QName("http://www.opengis.net/gml", "baseGeographicCRS");
    private final static QName _UsesTemporalDatum_QNAME = new QName("http://www.opengis.net/gml", "usesTemporalDatum");
    private final static QName _TemporalCS_QNAME = new QName("http://www.opengis.net/gml", "TemporalCS");
    private final static QName _MultiPointProperty_QNAME = new QName("http://www.opengis.net/gml", "multiPointProperty");
    private final static QName _ArcStringByBulge_QNAME = new QName("http://www.opengis.net/gml", "ArcStringByBulge");
    private final static QName _TransformationRef_QNAME = new QName("http://www.opengis.net/gml", "transformationRef");
    private final static QName _TemporalDatum_QNAME = new QName("http://www.opengis.net/gml", "TemporalDatum");
    private final static QName _Container_QNAME = new QName("http://www.opengis.net/gml", "container");
    private final static QName _PixelInCell_QNAME = new QName("http://www.opengis.net/gml", "pixelInCell");
    private final static QName _TimeEdge_QNAME = new QName("http://www.opengis.net/gml", "TimeEdge");
    private final static QName _CompositeValue_QNAME = new QName("http://www.opengis.net/gml", "CompositeValue");
    private final static QName _ImageDatum_QNAME = new QName("http://www.opengis.net/gml", "ImageDatum");
    private final static QName _TimeReferenceSystem_QNAME = new QName("http://www.opengis.net/gml", "TimeReferenceSystem");
    private final static QName _EngineeringDatum_QNAME = new QName("http://www.opengis.net/gml", "EngineeringDatum");
    private final static QName _AbstractMetaData_QNAME = new QName("http://www.opengis.net/gml", "AbstractMetaData");
    private final static QName _AbstractRing_QNAME = new QName("http://www.opengis.net/gml", "AbstractRing");
    private final static QName _TimePosition_QNAME = new QName("http://www.opengis.net/gml", "timePosition");
    private final static QName _OperationVersion_QNAME = new QName("http://www.opengis.net/gml", "operationVersion");
    private final static QName _IntegerValue_QNAME = new QName("http://www.opengis.net/gml", "integerValue");
    private final static QName _AffineCS_QNAME = new QName("http://www.opengis.net/gml", "AffineCS");
    private final static QName _MultiSurfaceProperty_QNAME = new QName("http://www.opengis.net/gml", "multiSurfaceProperty");
    private final static QName _UsesEllipsoid_QNAME = new QName("http://www.opengis.net/gml", "usesEllipsoid");
    private final static QName _LocationReference_QNAME = new QName("http://www.opengis.net/gml", "locationReference");
    private final static QName _DefinitionMember_QNAME = new QName("http://www.opengis.net/gml", "definitionMember");
    private final static QName _SurfaceArrayProperty_QNAME = new QName("http://www.opengis.net/gml", "surfaceArrayProperty");
    private final static QName _TopoSurface_QNAME = new QName("http://www.opengis.net/gml", "TopoSurface");
    private final static QName _EllipsoidalCSRef_QNAME = new QName("http://www.opengis.net/gml", "ellipsoidalCSRef");
    private final static QName _UsesImageDatum_QNAME = new QName("http://www.opengis.net/gml", "usesImageDatum");
    private final static QName _Cone_QNAME = new QName("http://www.opengis.net/gml", "Cone");
    private final static QName _TopoSurfaceProperty_QNAME = new QName("http://www.opengis.net/gml", "topoSurfaceProperty");
    private final static QName _Observation_QNAME = new QName("http://www.opengis.net/gml", "Observation");
    private final static QName _TemporalDatumPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "temporalDatum");
    private final static QName _LineString_QNAME = new QName("http://www.opengis.net/gml", "LineString");
    private final static QName _TimeOrdinalReferenceSystem_QNAME = new QName("http://www.opengis.net/gml", "TimeOrdinalReferenceSystem");
    private final static QName _BaseUnit_QNAME = new QName("http://www.opengis.net/gml", "BaseUnit");
    private final static QName _UsesGeodeticDatum_QNAME = new QName("http://www.opengis.net/gml", "usesGeodeticDatum");
    private final static QName _ParameterValuePropertyElement_QNAME = new QName("http://www.opengis.net/gml", "parameterValue");
    private final static QName _CompositeSolid_QNAME = new QName("http://www.opengis.net/gml", "CompositeSolid");
    private final static QName _Envelope_QNAME = new QName("http://www.opengis.net/gml", "Envelope");
    private final static QName _UsesTemporalCS_QNAME = new QName("http://www.opengis.net/gml", "usesTemporalCS");
    private final static QName _AbstractSurface_QNAME = new QName("http://www.opengis.net/gml", "AbstractSurface");
    private final static QName _FeatureMembers_QNAME = new QName("http://www.opengis.net/gml", "featureMembers");
    private final static QName _DerivedCRSRef_QNAME = new QName("http://www.opengis.net/gml", "derivedCRSRef");
    private final static QName _BaseSurface_QNAME = new QName("http://www.opengis.net/gml", "baseSurface");
    private final static QName _PrimeMeridianRef_QNAME = new QName("http://www.opengis.net/gml", "primeMeridianRef");
    private final static QName _PointMember_QNAME = new QName("http://www.opengis.net/gml", "pointMember");
    private final static QName _UserDefinedCSRef_QNAME = new QName("http://www.opengis.net/gml", "userDefinedCSRef");
    private final static QName _IncludesParameter_QNAME = new QName("http://www.opengis.net/gml", "includesParameter");
    private final static QName _Position_QNAME = new QName("http://www.opengis.net/gml", "position");
    private final static QName _ValueArray_QNAME = new QName("http://www.opengis.net/gml", "ValueArray");
    private final static QName _UserDefinedCS_QNAME = new QName("http://www.opengis.net/gml", "UserDefinedCS");
    private final static QName _CylindricalCSRef_QNAME = new QName("http://www.opengis.net/gml", "cylindricalCSRef");
    private final static QName _UsesSphericalCS_QNAME = new QName("http://www.opengis.net/gml", "usesSphericalCS");
    private final static QName _DescriptionReference_QNAME = new QName("http://www.opengis.net/gml", "descriptionReference");
    private final static QName _Dictionary_QNAME = new QName("http://www.opengis.net/gml", "Dictionary");
    private final static QName _BaseCurve_QNAME = new QName("http://www.opengis.net/gml", "baseCurve");
    private final static QName _BaseGeodeticCRS_QNAME = new QName("http://www.opengis.net/gml", "baseGeodeticCRS");
    private final static QName _GeneralTransformationRef_QNAME = new QName("http://www.opengis.net/gml", "generalTransformationRef");
    private final static QName _GeodeticDatumPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "geodeticDatum");
    private final static QName _AbstractGeneralDerivedCRS_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeneralDerivedCRS");
    private final static QName _UnitDefinition_QNAME = new QName("http://www.opengis.net/gml", "UnitDefinition");
    private final static QName _CoverageFunction_QNAME = new QName("http://www.opengis.net/gml", "coverageFunction");
    private final static QName _SuperComplex_QNAME = new QName("http://www.opengis.net/gml", "superComplex");
    private final static QName _MultiSolidCoverage_QNAME = new QName("http://www.opengis.net/gml", "MultiSolidCoverage");
    private final static QName _PassThroughOperationRef_QNAME = new QName("http://www.opengis.net/gml", "passThroughOperationRef");
    private final static QName _GridCoverage_QNAME = new QName("http://www.opengis.net/gml", "GridCoverage");
    private final static QName _ValidTime_QNAME = new QName("http://www.opengis.net/gml", "validTime");
    private final static QName _SolidProperty_QNAME = new QName("http://www.opengis.net/gml", "solidProperty");
    private final static QName _CoordinateSystemAxisRef_QNAME = new QName("http://www.opengis.net/gml", "coordinateSystemAxisRef");
    private final static QName _OperationRef_QNAME = new QName("http://www.opengis.net/gml", "operationRef");
    private final static QName _TriangulatedSurface_QNAME = new QName("http://www.opengis.net/gml", "TriangulatedSurface");
    private final static QName _StringValue_QNAME = new QName("http://www.opengis.net/gml", "stringValue");
    private final static QName _AbstractDatum_QNAME = new QName("http://www.opengis.net/gml", "AbstractDatum");
    private final static QName _QuantityExtent_QNAME = new QName("http://www.opengis.net/gml", "QuantityExtent");
    private final static QName _LineStringSegment_QNAME = new QName("http://www.opengis.net/gml", "LineStringSegment");
    private final static QName _AbstractGeneralOperationParameter_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeneralOperationParameter");
    private final static QName _CompositeSurface_QNAME = new QName("http://www.opengis.net/gml", "CompositeSurface");
    private final static QName _AbstractCoordinateSystem_QNAME = new QName("http://www.opengis.net/gml", "AbstractCoordinateSystem");
    private final static QName _BaseCRS_QNAME = new QName("http://www.opengis.net/gml", "baseCRS");
    private final static QName _PrimeMeridian_QNAME = new QName("http://www.opengis.net/gml", "PrimeMeridian");
    private final static QName _SolidMembers_QNAME = new QName("http://www.opengis.net/gml", "solidMembers");
    private final static QName _Circle_QNAME = new QName("http://www.opengis.net/gml", "Circle");
    private final static QName _CurveProperty_QNAME = new QName("http://www.opengis.net/gml", "curveProperty");
    private final static QName _ConventionalUnit_QNAME = new QName("http://www.opengis.net/gml", "ConventionalUnit");
    private final static QName _GeometricComplex_QNAME = new QName("http://www.opengis.net/gml", "GeometricComplex");
    private final static QName _Description_QNAME = new QName("http://www.opengis.net/gml", "description");
    private final static QName _UsesVerticalCS_QNAME = new QName("http://www.opengis.net/gml", "usesVerticalCS");
    private final static QName _SurfaceMembers_QNAME = new QName("http://www.opengis.net/gml", "surfaceMembers");
    private final static QName _VerticalCS_QNAME = new QName("http://www.opengis.net/gml", "VerticalCS");
    private final static QName _PolygonPatch_QNAME = new QName("http://www.opengis.net/gml", "PolygonPatch");
    private final static QName _AbstractCoordinateOperation_QNAME = new QName("http://www.opengis.net/gml", "AbstractCoordinateOperation");
    private final static QName _AbstractSolid_QNAME = new QName("http://www.opengis.net/gml", "AbstractSolid");
    private final static QName _AffinePlacement_QNAME = new QName("http://www.opengis.net/gml", "AffinePlacement");
    private final static QName _Boolean_QNAME = new QName("http://www.opengis.net/gml", "Boolean");
    private final static QName _AffineCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "affineCS");
    private final static QName _AbstractCurveSegment_QNAME = new QName("http://www.opengis.net/gml", "AbstractCurveSegment");
    private final static QName _Axis_QNAME = new QName("http://www.opengis.net/gml", "axis");
    private final static QName _SurfaceProperty_QNAME = new QName("http://www.opengis.net/gml", "surfaceProperty");
    private final static QName _ConcatenatedOperationRef_QNAME = new QName("http://www.opengis.net/gml", "concatenatedOperationRef");
    private final static QName _RealizationEpoch_QNAME = new QName("http://www.opengis.net/gml", "realizationEpoch");
    private final static QName _QuantityList_QNAME = new QName("http://www.opengis.net/gml", "QuantityList");
    private final static QName _EllipsoidalCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "ellipsoidalCS");
    private final static QName _ParameterValue_QNAME = new QName("http://www.opengis.net/gml", "ParameterValue");
    private final static QName _TemporalDatumRef_QNAME = new QName("http://www.opengis.net/gml", "temporalDatumRef");
    private final static QName _CountList_QNAME = new QName("http://www.opengis.net/gml", "CountList");
    private final static QName _Remarks_QNAME = new QName("http://www.opengis.net/gml", "remarks");
    private final static QName _TemporalCRS_QNAME = new QName("http://www.opengis.net/gml", "TemporalCRS");
    private final static QName _CategoryExtent_QNAME = new QName("http://www.opengis.net/gml", "CategoryExtent");
    private final static QName _RangeParameters_QNAME = new QName("http://www.opengis.net/gml", "rangeParameters");
    private final static QName _Measure_QNAME = new QName("http://www.opengis.net/gml", "measure");
    private final static QName _Members_QNAME = new QName("http://www.opengis.net/gml", "members");
    private final static QName _RangeSet_QNAME = new QName("http://www.opengis.net/gml", "rangeSet");
    private final static QName _PolyhedralSurface_QNAME = new QName("http://www.opengis.net/gml", "PolyhedralSurface");
    private final static QName _RectifiedGridDomain_QNAME = new QName("http://www.opengis.net/gml", "rectifiedGridDomain");
    private final static QName _Name_QNAME = new QName("http://www.opengis.net/gml", "name");
    private final static QName _Edge_QNAME = new QName("http://www.opengis.net/gml", "Edge");
    private final static QName _ConversionRef_QNAME = new QName("http://www.opengis.net/gml", "conversionRef");
    private final static QName _AbstractInlineProperty_QNAME = new QName("http://www.opengis.net/gml", "abstractInlineProperty");
    private final static QName _GeneralOperationParameter_QNAME = new QName("http://www.opengis.net/gml", "generalOperationParameter");
    private final static QName _ValueComponent_QNAME = new QName("http://www.opengis.net/gml", "valueComponent");
    private final static QName _Isolated_QNAME = new QName("http://www.opengis.net/gml", "isolated");
    private final static QName _RoughConversionToPreferredUnit_QNAME = new QName("http://www.opengis.net/gml", "roughConversionToPreferredUnit");
    private final static QName _DatumRef_QNAME = new QName("http://www.opengis.net/gml", "datumRef");
    private final static QName _PassThroughOperation_QNAME = new QName("http://www.opengis.net/gml", "PassThroughOperation");
    private final static QName _VerticalDatum_QNAME = new QName("http://www.opengis.net/gml", "VerticalDatum");
    private final static QName _EllipsoidRef_QNAME = new QName("http://www.opengis.net/gml", "ellipsoidRef");
    private final static QName _TimeCS_QNAME = new QName("http://www.opengis.net/gml", "TimeCS");
    private final static QName _PointProperty_QNAME = new QName("http://www.opengis.net/gml", "pointProperty");
    private final static QName _ValueFile_QNAME = new QName("http://www.opengis.net/gml", "valueFile");
    private final static QName _AbstractGeneralParameterValue_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeneralParameterValue");
    private final static QName _PolarCSRef_QNAME = new QName("http://www.opengis.net/gml", "polarCSRef");
    private final static QName _Node_QNAME = new QName("http://www.opengis.net/gml", "Node");
    private final static QName _GeographicCRS_QNAME = new QName("http://www.opengis.net/gml", "GeographicCRS");
    private final static QName _AbstractCoverage_QNAME = new QName("http://www.opengis.net/gml", "AbstractCoverage");
    private final static QName _GeodeticCRS_QNAME = new QName("http://www.opengis.net/gml", "GeodeticCRS");
    private final static QName _Using_QNAME = new QName("http://www.opengis.net/gml", "using");
    private final static QName _OperationParameter_QNAME = new QName("http://www.opengis.net/gml", "OperationParameter");
    private final static QName _VerticalDatumPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "verticalDatum");
    private final static QName _DynamicMembers_QNAME = new QName("http://www.opengis.net/gml", "dynamicMembers");
    private final static QName _Arc_QNAME = new QName("http://www.opengis.net/gml", "Arc");
    private final static QName _MaximumOccurs_QNAME = new QName("http://www.opengis.net/gml", "maximumOccurs");
    private final static QName _Surface_QNAME = new QName("http://www.opengis.net/gml", "Surface");
    private final static QName _AbstractImplicitGeometry_QNAME = new QName("http://www.opengis.net/gml", "AbstractImplicitGeometry");
    private final static QName _Shell_QNAME = new QName("http://www.opengis.net/gml", "Shell");
    private final static QName _AbstractStrictAssociationRole_QNAME = new QName("http://www.opengis.net/gml", "abstractStrictAssociationRole");
    private final static QName _Point_QNAME = new QName("http://www.opengis.net/gml", "Point");
    private final static QName _AbstractTimeComplex_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimeComplex");
    private final static QName _MultiCoverage_QNAME = new QName("http://www.opengis.net/gml", "multiCoverage");
    private final static QName _SolidMember_QNAME = new QName("http://www.opengis.net/gml", "solidMember");
    private final static QName _Geodesic_QNAME = new QName("http://www.opengis.net/gml", "Geodesic");
    private final static QName _OrientableSurface_QNAME = new QName("http://www.opengis.net/gml", "OrientableSurface");
    private final static QName _BooleanList_QNAME = new QName("http://www.opengis.net/gml", "BooleanList");
    private final static QName _CoordOperation_QNAME = new QName("http://www.opengis.net/gml", "coordOperation");
    private final static QName _Member_QNAME = new QName("http://www.opengis.net/gml", "member");
    private final static QName _MaximumValue_QNAME = new QName("http://www.opengis.net/gml", "maximumValue");
    private final static QName _AnchorDefinition_QNAME = new QName("http://www.opengis.net/gml", "anchorDefinition");
    private final static QName _Rectangle_QNAME = new QName("http://www.opengis.net/gml", "Rectangle");
    private final static QName _ObliqueCartesianCS_QNAME = new QName("http://www.opengis.net/gml", "ObliqueCartesianCS");
    private final static QName _DerivationUnitTerm_QNAME = new QName("http://www.opengis.net/gml", "derivationUnitTerm");
    private final static QName _Polygon_QNAME = new QName("http://www.opengis.net/gml", "Polygon");
    private final static QName _History_QNAME = new QName("http://www.opengis.net/gml", "history");
    private final static QName _DirectedEdge_QNAME = new QName("http://www.opengis.net/gml", "directedEdge");
    private final static QName _DerivedCRSType_QNAME = new QName("http://www.opengis.net/gml", "derivedCRSType");
    private final static QName _CoordinateSystemAxis_QNAME = new QName("http://www.opengis.net/gml", "CoordinateSystemAxis");
    private final static QName _TopoVolumeProperty_QNAME = new QName("http://www.opengis.net/gml", "topoVolumeProperty");
    private final static QName _Ring_QNAME = new QName("http://www.opengis.net/gml", "Ring");
    private final static QName _CatalogSymbol_QNAME = new QName("http://www.opengis.net/gml", "catalogSymbol");
    private final static QName _ExtentOf_QNAME = new QName("http://www.opengis.net/gml", "extentOf");
    private final static QName _DerivedUnit_QNAME = new QName("http://www.opengis.net/gml", "DerivedUnit");
    private final static QName _DmsAngleValue_QNAME = new QName("http://www.opengis.net/gml", "dmsAngleValue");
    private final static QName _CountExtent_QNAME = new QName("http://www.opengis.net/gml", "CountExtent");
    private final static QName _SphericalCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "sphericalCS");
    private final static QName _UsesOperation_QNAME = new QName("http://www.opengis.net/gml", "usesOperation");
    private final static QName _ValueList_QNAME = new QName("http://www.opengis.net/gml", "valueList");
    private final static QName _ValueOfParameter_QNAME = new QName("http://www.opengis.net/gml", "valueOfParameter");
    private final static QName _UsesPrimeMeridian_QNAME = new QName("http://www.opengis.net/gml", "usesPrimeMeridian");
    private final static QName _MultiGeometryProperty_QNAME = new QName("http://www.opengis.net/gml", "multiGeometryProperty");
    private final static QName _MultiCenterOf_QNAME = new QName("http://www.opengis.net/gml", "multiCenterOf");
    private final static QName _TupleList_QNAME = new QName("http://www.opengis.net/gml", "tupleList");
    private final static QName _AbstractGeneralOperationParameterRef_QNAME = new QName("http://www.opengis.net/gml", "abstractGeneralOperationParameterRef");
    private final static QName _Conversion_QNAME = new QName("http://www.opengis.net/gml", "Conversion");
    private final static QName _TopoPointProperty_QNAME = new QName("http://www.opengis.net/gml", "topoPointProperty");
    private final static QName _CrsRef_QNAME = new QName("http://www.opengis.net/gml", "crsRef");
    private final static QName _CoordinateOperationRef_QNAME = new QName("http://www.opengis.net/gml", "coordinateOperationRef");
    private final static QName _AbstractTimeObject_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimeObject");
    private final static QName _MultiCurveDomain_QNAME = new QName("http://www.opengis.net/gml", "multiCurveDomain");
    private final static QName _SphericalCSRef_QNAME = new QName("http://www.opengis.net/gml", "sphericalCSRef");
    private final static QName _BooleanValue_QNAME = new QName("http://www.opengis.net/gml", "booleanValue");
    private final static QName _TopoComplexProperty_QNAME = new QName("http://www.opengis.net/gml", "topoComplexProperty");
    private final static QName _RectifiedGridCoverage_QNAME = new QName("http://www.opengis.net/gml", "RectifiedGridCoverage");
    private final static QName _FeatureCollection_QNAME = new QName("http://www.opengis.net/gml", "FeatureCollection");
    private final static QName _CartesianCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "cartesianCS");
    private final static QName _Interior_QNAME = new QName("http://www.opengis.net/gml", "interior");
    private final static QName _SubComplex_QNAME = new QName("http://www.opengis.net/gml", "subComplex");
    private final static QName _MinimumValue_QNAME = new QName("http://www.opengis.net/gml", "minimumValue");
    private final static QName _Location_QNAME = new QName("http://www.opengis.net/gml", "location");
    private final static QName _TemporalCSRef_QNAME = new QName("http://www.opengis.net/gml", "temporalCSRef");
    private final static QName _ObliqueCartesianCSRef_QNAME = new QName("http://www.opengis.net/gml", "obliqueCartesianCSRef");
    private final static QName _Value_QNAME = new QName("http://www.opengis.net/gml", "value");
    private final static QName _TargetDimensions_QNAME = new QName("http://www.opengis.net/gml", "targetDimensions");
    private final static QName _TimeCalendar_QNAME = new QName("http://www.opengis.net/gml", "TimeCalendar");
    private final static QName _RectifiedGrid_QNAME = new QName("http://www.opengis.net/gml", "RectifiedGrid");
    private final static QName _DirectedFace_QNAME = new QName("http://www.opengis.net/gml", "directedFace");
    private final static QName _PriorityLocation_QNAME = new QName("http://www.opengis.net/gml", "priorityLocation");
    private final static QName _EngineeringCRS_QNAME = new QName("http://www.opengis.net/gml", "EngineeringCRS");
    private final static QName _EllipsoidalCS_QNAME = new QName("http://www.opengis.net/gml", "EllipsoidalCS");
    private final static QName _CurveArrayProperty_QNAME = new QName("http://www.opengis.net/gml", "curveArrayProperty");
    private final static QName _Duration_QNAME = new QName("http://www.opengis.net/gml", "duration");
    private final static QName _PointRep_QNAME = new QName("http://www.opengis.net/gml", "pointRep");
    private final static QName _AbstractContinuousCoverage_QNAME = new QName("http://www.opengis.net/gml", "AbstractContinuousCoverage");
    private final static QName _GridFunction_QNAME = new QName("http://www.opengis.net/gml", "GridFunction");
    private final static QName _CenterOf_QNAME = new QName("http://www.opengis.net/gml", "centerOf");
    private final static QName _PointArrayProperty_QNAME = new QName("http://www.opengis.net/gml", "pointArrayProperty");
    private final static QName _UsesParameter_QNAME = new QName("http://www.opengis.net/gml", "usesParameter");
    private final static QName _GeographicCRSRef_QNAME = new QName("http://www.opengis.net/gml", "geographicCRSRef");
    private final static QName _AbstractFeatureCollection_QNAME = new QName("http://www.opengis.net/gml", "AbstractFeatureCollection");
    private final static QName _Scope_QNAME = new QName("http://www.opengis.net/gml", "scope");
    private final static QName _Bag_QNAME = new QName("http://www.opengis.net/gml", "Bag");
    private final static QName _TimeCalendarEra_QNAME = new QName("http://www.opengis.net/gml", "TimeCalendarEra");
    private final static QName _ConversionToPreferredUnit_QNAME = new QName("http://www.opengis.net/gml", "conversionToPreferredUnit");
    private final static QName _OffsetCurve_QNAME = new QName("http://www.opengis.net/gml", "OffsetCurve");
    private final static QName _AbstractScalarValue_QNAME = new QName("http://www.opengis.net/gml", "AbstractScalarValue");
    private final static QName _Coordinates_QNAME = new QName("http://www.opengis.net/gml", "coordinates");
    private final static QName _MultiCurve_QNAME = new QName("http://www.opengis.net/gml", "MultiCurve");
    private final static QName _UsesEngineeringDatum_QNAME = new QName("http://www.opengis.net/gml", "usesEngineeringDatum");
    private final static QName _UsesEllipsoidalCS_QNAME = new QName("http://www.opengis.net/gml", "usesEllipsoidalCS");
    private final static QName _EdgeOf_QNAME = new QName("http://www.opengis.net/gml", "edgeOf");
    private final static QName _LinearCSRef_QNAME = new QName("http://www.opengis.net/gml", "linearCSRef");
    private final static QName _GeometryMembers_QNAME = new QName("http://www.opengis.net/gml", "geometryMembers");
    private final static QName _LocationKeyWord_QNAME = new QName("http://www.opengis.net/gml", "LocationKeyWord");
    private final static QName _DictionaryEntry_QNAME = new QName("http://www.opengis.net/gml", "dictionaryEntry");
    private final static QName _AbstractSingleOperation_QNAME = new QName("http://www.opengis.net/gml", "AbstractSingleOperation");
    private final static QName _ImageCRS_QNAME = new QName("http://www.opengis.net/gml", "ImageCRS");
    private final static QName _UsesCS_QNAME = new QName("http://www.opengis.net/gml", "usesCS");
    private final static QName _SingleCRSRef_QNAME = new QName("http://www.opengis.net/gml", "singleCRSRef");
    private final static QName _IndirectEntry_QNAME = new QName("http://www.opengis.net/gml", "indirectEntry");
    private final static QName _OperationParameterRef_QNAME = new QName("http://www.opengis.net/gml", "operationParameterRef");
    private final static QName _AbstractGeneralTransformation_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeneralTransformation");
    private final static QName _Bezier_QNAME = new QName("http://www.opengis.net/gml", "Bezier");
    private final static QName _DirectedObservationAtDistance_QNAME = new QName("http://www.opengis.net/gml", "DirectedObservationAtDistance");
    private final static QName _Patches_QNAME = new QName("http://www.opengis.net/gml", "patches");
    private final static QName _MultiLocation_QNAME = new QName("http://www.opengis.net/gml", "multiLocation");
    private final static QName _AbstractFeature_QNAME = new QName("http://www.opengis.net/gml", "AbstractFeature");
    private final static QName _FeatureProperty_QNAME = new QName("http://www.opengis.net/gml", "featureProperty");
    private final static QName _Transformation_QNAME = new QName("http://www.opengis.net/gml", "Transformation");
    private final static QName _TrianglePatches_QNAME = new QName("http://www.opengis.net/gml", "trianglePatches");
    private final static QName _TimeClock_QNAME = new QName("http://www.opengis.net/gml", "TimeClock");
    private final static QName _LinearCS_QNAME = new QName("http://www.opengis.net/gml", "LinearCS");
    private final static QName _AbstractTopoPrimitive_QNAME = new QName("http://www.opengis.net/gml", "AbstractTopoPrimitive");
    private final static QName _SphericalCS_QNAME = new QName("http://www.opengis.net/gml", "SphericalCS");
    private final static QName _UsesSingleOperation_QNAME = new QName("http://www.opengis.net/gml", "usesSingleOperation");
    private final static QName _TopoSolid_QNAME = new QName("http://www.opengis.net/gml", "TopoSolid");
    private final static QName _VerticalCSPropertyElement_QNAME = new QName("http://www.opengis.net/gml", "verticalCS");
    private final static QName _Triangle_QNAME = new QName("http://www.opengis.net/gml", "Triangle");
    private final static QName _GeocentricCRSRef_QNAME = new QName("http://www.opengis.net/gml", "geocentricCRSRef");
    private final static QName _AbstractAssociationRole_QNAME = new QName("http://www.opengis.net/gml", "abstractAssociationRole");
    private final static QName _ValueComponents_QNAME = new QName("http://www.opengis.net/gml", "valueComponents");
    private final static QName _QuantityType_QNAME = new QName("http://www.opengis.net/gml", "quantityType");
    private final static QName _FeatureMember_QNAME = new QName("http://www.opengis.net/gml", "featureMember");
    private final static QName _LocationString_QNAME = new QName("http://www.opengis.net/gml", "LocationString");
    private final static QName _UsesAxis_QNAME = new QName("http://www.opengis.net/gml", "usesAxis");
    private final static QName _AbstractGeometricAggregate_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeometricAggregate");
    private final static QName _Method_QNAME = new QName("http://www.opengis.net/gml", "method");
    private final static QName _IntegerValueList_QNAME = new QName("http://www.opengis.net/gml", "integerValueList");
    private final static QName _AnchorPoint_QNAME = new QName("http://www.opengis.net/gml", "anchorPoint");
    private final static QName _UsesMethod_QNAME = new QName("http://www.opengis.net/gml", "usesMethod");
    private final static QName _Definition_QNAME = new QName("http://www.opengis.net/gml", "Definition");
    private final static QName _GeodesicString_QNAME = new QName("http://www.opengis.net/gml", "GeodesicString");
    private final static QName _AbstractTimeGeometricPrimitive_QNAME = new QName("http://www.opengis.net/gml", "AbstractTimeGeometricPrimitive");
    private final static QName _Cylinder_QNAME = new QName("http://www.opengis.net/gml", "Cylinder");
    private final static QName _SingleOperationRef_QNAME = new QName("http://www.opengis.net/gml", "singleOperationRef");
    private final static QName _Subject_QNAME = new QName("http://www.opengis.net/gml", "subject");
    private final static QName _Segments_QNAME = new QName("http://www.opengis.net/gml", "segments");
    private final static QName _VerticalCRS_QNAME = new QName("http://www.opengis.net/gml", "VerticalCRS");
    private final static QName _Grid_QNAME = new QName("http://www.opengis.net/gml", "Grid");
    private final static QName _TimeInstant_QNAME = new QName("http://www.opengis.net/gml", "TimeInstant");
    private final static QName _AbstractGeometry_QNAME = new QName("http://www.opengis.net/gml", "AbstractGeometry");
    private final static QName _GridTypeLimits_QNAME = new QName("http://www.opengis.net/gml", "limits");
    private final static QName _GridTypeAxisName_QNAME = new QName("http://www.opengis.net/gml", "axisName");
    private final static QName _GridTypeAxisLabels_QNAME = new QName("http://www.opengis.net/gml", "axisLabels");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.gml.v320
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AbstractGriddedSurfaceType.Rows }
     * 
     */
    public AbstractGriddedSurfaceType.Rows createAbstractGriddedSurfaceTypeRows() {
        return new AbstractGriddedSurfaceType.Rows();
    }

    /**
     * Create an instance of {@link TinType }
     * 
     */
    public TinType createTinType() {
        return new TinType();
    }

    /**
     * Create an instance of {@link ClothoidType }
     * 
     */
    public ClothoidType createClothoidType() {
        return new ClothoidType();
    }

    /**
     * Create an instance of {@link ProcedurePropertyType }
     * 
     */
    public ProcedurePropertyType createProcedurePropertyType() {
        return new ProcedurePropertyType();
    }

    /**
     * Create an instance of {@link OperationParameterType }
     * 
     */
    public OperationParameterType createOperationParameterType() {
        return new OperationParameterType();
    }

    /**
     * Create an instance of {@link DefinitionType }
     * 
     */
    public DefinitionType createDefinitionType() {
        return new DefinitionType();
    }

    /**
     * Create an instance of {@link ArcType }
     * 
     */
    public ArcType createArcType() {
        return new ArcType();
    }

    /**
     * Create an instance of {@link ArcStringType }
     * 
     */
    public ArcStringType createArcStringType() {
        return new ArcStringType();
    }

    /**
     * Create an instance of {@link VerticalDatumPropertyType }
     * 
     */
    public VerticalDatumPropertyType createVerticalDatumPropertyType() {
        return new VerticalDatumPropertyType();
    }

    /**
     * Create an instance of {@link DynamicFeatureMemberType }
     * 
     */
    public DynamicFeatureMemberType createDynamicFeatureMemberType() {
        return new DynamicFeatureMemberType();
    }

    /**
     * Create an instance of {@link GeodeticCRSType }
     * 
     */
    public GeodeticCRSType createGeodeticCRSType() {
        return new GeodeticCRSType();
    }

    /**
     * Create an instance of {@link SurfaceType }
     * 
     */
    public SurfaceType createSurfaceType() {
        return new SurfaceType();
    }

    /**
     * Create an instance of {@link ShellType }
     * 
     */
    public ShellType createShellType() {
        return new ShellType();
    }

    /**
     * Create an instance of {@link AssociationRoleType }
     * 
     */
    public AssociationRoleType createAssociationRoleType() {
        return new AssociationRoleType();
    }

    /**
     * Create an instance of {@link PointType }
     * 
     */
    public PointType createPointType() {
        return new PointType();
    }

    /**
     * Create an instance of {@link IsolatedPropertyType }
     * 
     */
    public IsolatedPropertyType createIsolatedPropertyType() {
        return new IsolatedPropertyType();
    }

    /**
     * Create an instance of {@link ValuePropertyType }
     * 
     */
    public ValuePropertyType createValuePropertyType() {
        return new ValuePropertyType();
    }

    /**
     * Create an instance of {@link AbstractGeneralOperationParameterPropertyType }
     * 
     */
    public AbstractGeneralOperationParameterPropertyType createAbstractGeneralOperationParameterPropertyType() {
        return new AbstractGeneralOperationParameterPropertyType();
    }

    /**
     * Create an instance of {@link ConversionToPreferredUnitType }
     * 
     */
    public ConversionToPreferredUnitType createConversionToPreferredUnitType() {
        return new ConversionToPreferredUnitType();
    }

    /**
     * Create an instance of {@link InlinePropertyType }
     * 
     */
    public InlinePropertyType createInlinePropertyType() {
        return new InlinePropertyType();
    }

    /**
     * Create an instance of {@link ConversionPropertyType }
     * 
     */
    public ConversionPropertyType createConversionPropertyType() {
        return new ConversionPropertyType();
    }

    /**
     * Create an instance of {@link EdgeType }
     * 
     */
    public EdgeType createEdgeType() {
        return new EdgeType();
    }

    /**
     * Create an instance of {@link PolarCSPropertyType }
     * 
     */
    public PolarCSPropertyType createPolarCSPropertyType() {
        return new PolarCSPropertyType();
    }

    /**
     * Create an instance of {@link GeographicCRSType }
     * 
     */
    public GeographicCRSType createGeographicCRSType() {
        return new GeographicCRSType();
    }

    /**
     * Create an instance of {@link NodeType }
     * 
     */
    public NodeType createNodeType() {
        return new NodeType();
    }

    /**
     * Create an instance of {@link PassThroughOperationType }
     * 
     */
    public PassThroughOperationType createPassThroughOperationType() {
        return new PassThroughOperationType();
    }

    /**
     * Create an instance of {@link TimeCSType }
     * 
     */
    public TimeCSType createTimeCSType() {
        return new TimeCSType();
    }

    /**
     * Create an instance of {@link EllipsoidPropertyType }
     * 
     */
    public EllipsoidPropertyType createEllipsoidPropertyType() {
        return new EllipsoidPropertyType();
    }

    /**
     * Create an instance of {@link VerticalDatumType }
     * 
     */
    public VerticalDatumType createVerticalDatumType() {
        return new VerticalDatumType();
    }

    /**
     * Create an instance of {@link DatumPropertyType }
     * 
     */
    public DatumPropertyType createDatumPropertyType() {
        return new DatumPropertyType();
    }

    /**
     * Create an instance of {@link PointPropertyType }
     * 
     */
    public PointPropertyType createPointPropertyType() {
        return new PointPropertyType();
    }

    /**
     * Create an instance of {@link RingType }
     * 
     */
    public RingType createRingType() {
        return new RingType();
    }

    /**
     * Create an instance of {@link TopoVolumePropertyType }
     * 
     */
    public TopoVolumePropertyType createTopoVolumePropertyType() {
        return new TopoVolumePropertyType();
    }

    /**
     * Create an instance of {@link DirectedEdgePropertyType }
     * 
     */
    public DirectedEdgePropertyType createDirectedEdgePropertyType() {
        return new DirectedEdgePropertyType();
    }

    /**
     * Create an instance of {@link CoordinateOperationAccuracyElement }
     * 
     */
    public CoordinateOperationAccuracyElement createCoordinateOperationAccuracyElement() {
        return new CoordinateOperationAccuracyElement();
    }

    /**
     * Create an instance of {@link CoordinateSystemAxisType }
     * 
     */
    public CoordinateSystemAxisType createCoordinateSystemAxisType() {
        return new CoordinateSystemAxisType();
    }

    /**
     * Create an instance of {@link CodeWithAuthorityType }
     * 
     */
    public CodeWithAuthorityType createCodeWithAuthorityType() {
        return new CodeWithAuthorityType();
    }

    /**
     * Create an instance of {@link DerivedUnitType }
     * 
     */
    public DerivedUnitType createDerivedUnitType() {
        return new DerivedUnitType();
    }

    /**
     * Create an instance of {@link UnitDefinitionType }
     * 
     */
    public UnitDefinitionType createUnitDefinitionType() {
        return new UnitDefinitionType();
    }

    /**
     * Create an instance of {@link DMSAngleType }
     * 
     */
    public DMSAngleType createDMSAngleType() {
        return new DMSAngleType();
    }

    /**
     * Create an instance of {@link SurfacePropertyType }
     * 
     */
    public SurfacePropertyType createSurfacePropertyType() {
        return new SurfacePropertyType();
    }

    /**
     * Create an instance of {@link CodeType }
     * 
     */
    public CodeType createCodeType() {
        return new CodeType();
    }

    /**
     * Create an instance of {@link OrientableSurfaceType }
     * 
     */
    public OrientableSurfaceType createOrientableSurfaceType() {
        return new OrientableSurfaceType();
    }

    /**
     * Create an instance of {@link CoordinateOperationPropertyType }
     * 
     */
    public CoordinateOperationPropertyType createCoordinateOperationPropertyType() {
        return new CoordinateOperationPropertyType();
    }

    /**
     * Create an instance of {@link MultiSurfacePropertyType }
     * 
     */
    public MultiSurfacePropertyType createMultiSurfacePropertyType() {
        return new MultiSurfacePropertyType();
    }

    /**
     * Create an instance of {@link SolidPropertyType }
     * 
     */
    public SolidPropertyType createSolidPropertyType() {
        return new SolidPropertyType();
    }

    /**
     * Create an instance of {@link GeodesicType }
     * 
     */
    public GeodesicType createGeodesicType() {
        return new GeodesicType();
    }

    /**
     * Create an instance of {@link GeodesicStringType }
     * 
     */
    public GeodesicStringType createGeodesicStringType() {
        return new GeodesicStringType();
    }

    /**
     * Create an instance of {@link HistoryPropertyType }
     * 
     */
    public HistoryPropertyType createHistoryPropertyType() {
        return new HistoryPropertyType();
    }

    /**
     * Create an instance of {@link PolygonType }
     * 
     */
    public PolygonType createPolygonType() {
        return new PolygonType();
    }

    /**
     * Create an instance of {@link RectangleType }
     * 
     */
    public RectangleType createRectangleType() {
        return new RectangleType();
    }

    /**
     * Create an instance of {@link DerivationUnitTermType }
     * 
     */
    public DerivationUnitTermType createDerivationUnitTermType() {
        return new DerivationUnitTermType();
    }

    /**
     * Create an instance of {@link ObliqueCartesianCSType }
     * 
     */
    public ObliqueCartesianCSType createObliqueCartesianCSType() {
        return new ObliqueCartesianCSType();
    }

    /**
     * Create an instance of {@link CompositeSurfaceType }
     * 
     */
    public CompositeSurfaceType createCompositeSurfaceType() {
        return new CompositeSurfaceType();
    }

    /**
     * Create an instance of {@link OperationPropertyType }
     * 
     */
    public OperationPropertyType createOperationPropertyType() {
        return new OperationPropertyType();
    }

    /**
     * Create an instance of {@link TriangulatedSurfaceType }
     * 
     */
    public TriangulatedSurfaceType createTriangulatedSurfaceType() {
        return new TriangulatedSurfaceType();
    }

    /**
     * Create an instance of {@link QuantityExtentType }
     * 
     */
    public QuantityExtentType createQuantityExtentType() {
        return new QuantityExtentType();
    }

    /**
     * Create an instance of {@link LineStringSegmentType }
     * 
     */
    public LineStringSegmentType createLineStringSegmentType() {
        return new LineStringSegmentType();
    }

    /**
     * Create an instance of {@link ConventionalUnitType }
     * 
     */
    public ConventionalUnitType createConventionalUnitType() {
        return new ConventionalUnitType();
    }

    /**
     * Create an instance of {@link SurfaceArrayPropertyType }
     * 
     */
    public SurfaceArrayPropertyType createSurfaceArrayPropertyType() {
        return new SurfaceArrayPropertyType();
    }

    /**
     * Create an instance of {@link VerticalCSType }
     * 
     */
    public VerticalCSType createVerticalCSType() {
        return new VerticalCSType();
    }

    /**
     * Create an instance of {@link GeometricComplexType }
     * 
     */
    public GeometricComplexType createGeometricComplexType() {
        return new GeometricComplexType();
    }

    /**
     * Create an instance of {@link StringOrRefType }
     * 
     */
    public StringOrRefType createStringOrRefType() {
        return new StringOrRefType();
    }

    /**
     * Create an instance of {@link VerticalCSPropertyType }
     * 
     */
    public VerticalCSPropertyType createVerticalCSPropertyType() {
        return new VerticalCSPropertyType();
    }

    /**
     * Create an instance of {@link SingleCRSPropertyType }
     * 
     */
    public SingleCRSPropertyType createSingleCRSPropertyType() {
        return new SingleCRSPropertyType();
    }

    /**
     * Create an instance of {@link SolidArrayPropertyType }
     * 
     */
    public SolidArrayPropertyType createSolidArrayPropertyType() {
        return new SolidArrayPropertyType();
    }

    /**
     * Create an instance of {@link CurvePropertyType }
     * 
     */
    public CurvePropertyType createCurvePropertyType() {
        return new CurvePropertyType();
    }

    /**
     * Create an instance of {@link CircleType }
     * 
     */
    public CircleType createCircleType() {
        return new CircleType();
    }

    /**
     * Create an instance of {@link PrimeMeridianType }
     * 
     */
    public PrimeMeridianType createPrimeMeridianType() {
        return new PrimeMeridianType();
    }

    /**
     * Create an instance of {@link MultiSolidCoverageType }
     * 
     */
    public MultiSolidCoverageType createMultiSolidCoverageType() {
        return new MultiSolidCoverageType();
    }

    /**
     * Create an instance of {@link TopoComplexMemberType }
     * 
     */
    public TopoComplexMemberType createTopoComplexMemberType() {
        return new TopoComplexMemberType();
    }

    /**
     * Create an instance of {@link CoverageFunctionType }
     * 
     */
    public CoverageFunctionType createCoverageFunctionType() {
        return new CoverageFunctionType();
    }

    /**
     * Create an instance of {@link PassThroughOperationPropertyType }
     * 
     */
    public PassThroughOperationPropertyType createPassThroughOperationPropertyType() {
        return new PassThroughOperationPropertyType();
    }

    /**
     * Create an instance of {@link CoordinateSystemAxisPropertyType }
     * 
     */
    public CoordinateSystemAxisPropertyType createCoordinateSystemAxisPropertyType() {
        return new CoordinateSystemAxisPropertyType();
    }

    /**
     * Create an instance of {@link TimePrimitivePropertyType }
     * 
     */
    public TimePrimitivePropertyType createTimePrimitivePropertyType() {
        return new TimePrimitivePropertyType();
    }

    /**
     * Create an instance of {@link GridCoverageType }
     * 
     */
    public GridCoverageType createGridCoverageType() {
        return new GridCoverageType();
    }

    /**
     * Create an instance of {@link RangeParametersType }
     * 
     */
    public RangeParametersType createRangeParametersType() {
        return new RangeParametersType();
    }

    /**
     * Create an instance of {@link MeasureType }
     * 
     */
    public MeasureType createMeasureType() {
        return new MeasureType();
    }

    /**
     * Create an instance of {@link TemporalCRSType }
     * 
     */
    public TemporalCRSType createTemporalCRSType() {
        return new TemporalCRSType();
    }

    /**
     * Create an instance of {@link CategoryExtentType }
     * 
     */
    public CategoryExtentType createCategoryExtentType() {
        return new CategoryExtentType();
    }

    /**
     * Create an instance of {@link PolyhedralSurfaceType }
     * 
     */
    public PolyhedralSurfaceType createPolyhedralSurfaceType() {
        return new PolyhedralSurfaceType();
    }

    /**
     * Create an instance of {@link RectifiedGridDomainType }
     * 
     */
    public RectifiedGridDomainType createRectifiedGridDomainType() {
        return new RectifiedGridDomainType();
    }

    /**
     * Create an instance of {@link DomainSetType }
     * 
     */
    public DomainSetType createDomainSetType() {
        return new DomainSetType();
    }

    /**
     * Create an instance of {@link RangeSetType }
     * 
     */
    public RangeSetType createRangeSetType() {
        return new RangeSetType();
    }

    /**
     * Create an instance of {@link ArrayAssociationType }
     * 
     */
    public ArrayAssociationType createArrayAssociationType() {
        return new ArrayAssociationType();
    }

    /**
     * Create an instance of {@link AffineCSPropertyType }
     * 
     */
    public AffineCSPropertyType createAffineCSPropertyType() {
        return new AffineCSPropertyType();
    }

    /**
     * Create an instance of {@link PolygonPatchType }
     * 
     */
    public PolygonPatchType createPolygonPatchType() {
        return new PolygonPatchType();
    }

    /**
     * Create an instance of {@link org.geosdi.geoplatform.xml.gml.v320.Boolean }
     * 
     */
    public org.geosdi.geoplatform.xml.gml.v320.Boolean createBoolean() {
        return new org.geosdi.geoplatform.xml.gml.v320.Boolean();
    }

    /**
     * Create an instance of {@link AbstractSolidType }
     * 
     */
    public AbstractSolidType createAbstractSolidType() {
        return new AbstractSolidType();
    }

    /**
     * Create an instance of {@link AffinePlacementType }
     * 
     */
    public AffinePlacementType createAffinePlacementType() {
        return new AffinePlacementType();
    }

    /**
     * Create an instance of {@link EllipsoidalCSPropertyType }
     * 
     */
    public EllipsoidalCSPropertyType createEllipsoidalCSPropertyType() {
        return new EllipsoidalCSPropertyType();
    }

    /**
     * Create an instance of {@link MeasureOrNilReasonListType }
     * 
     */
    public MeasureOrNilReasonListType createMeasureOrNilReasonListType() {
        return new MeasureOrNilReasonListType();
    }

    /**
     * Create an instance of {@link TemporalDatumPropertyType }
     * 
     */
    public TemporalDatumPropertyType createTemporalDatumPropertyType() {
        return new TemporalDatumPropertyType();
    }

    /**
     * Create an instance of {@link ParameterValueType }
     * 
     */
    public ParameterValueType createParameterValueType() {
        return new ParameterValueType();
    }

    /**
     * Create an instance of {@link ConcatenatedOperationPropertyType }
     * 
     */
    public ConcatenatedOperationPropertyType createConcatenatedOperationPropertyType() {
        return new ConcatenatedOperationPropertyType();
    }

    /**
     * Create an instance of {@link TrianglePatchArrayPropertyType }
     * 
     */
    public TrianglePatchArrayPropertyType createTrianglePatchArrayPropertyType() {
        return new TrianglePatchArrayPropertyType();
    }

    /**
     * Create an instance of {@link SurfacePatchArrayPropertyType }
     * 
     */
    public SurfacePatchArrayPropertyType createSurfacePatchArrayPropertyType() {
        return new SurfacePatchArrayPropertyType();
    }

    /**
     * Create an instance of {@link TransformationType }
     * 
     */
    public TransformationType createTransformationType() {
        return new TransformationType();
    }

    /**
     * Create an instance of {@link TimeClockType }
     * 
     */
    public TimeClockType createTimeClockType() {
        return new TimeClockType();
    }

    /**
     * Create an instance of {@link TimeReferenceSystemType }
     * 
     */
    public TimeReferenceSystemType createTimeReferenceSystemType() {
        return new TimeReferenceSystemType();
    }

    /**
     * Create an instance of {@link FeaturePropertyType }
     * 
     */
    public FeaturePropertyType createFeaturePropertyType() {
        return new FeaturePropertyType();
    }

    /**
     * Create an instance of {@link LinearCSType }
     * 
     */
    public LinearCSType createLinearCSType() {
        return new LinearCSType();
    }

    /**
     * Create an instance of {@link MultiPointPropertyType }
     * 
     */
    public MultiPointPropertyType createMultiPointPropertyType() {
        return new MultiPointPropertyType();
    }

    /**
     * Create an instance of {@link TriangleType }
     * 
     */
    public TriangleType createTriangleType() {
        return new TriangleType();
    }

    /**
     * Create an instance of {@link TopoSolidType }
     * 
     */
    public TopoSolidType createTopoSolidType() {
        return new TopoSolidType();
    }

    /**
     * Create an instance of {@link GeocentricCRSPropertyType }
     * 
     */
    public GeocentricCRSPropertyType createGeocentricCRSPropertyType() {
        return new GeocentricCRSPropertyType();
    }

    /**
     * Create an instance of {@link SphericalCSType }
     * 
     */
    public SphericalCSType createSphericalCSType() {
        return new SphericalCSType();
    }

    /**
     * Create an instance of {@link DictionaryEntryType }
     * 
     */
    public DictionaryEntryType createDictionaryEntryType() {
        return new DictionaryEntryType();
    }

    /**
     * Create an instance of {@link ImageCRSType }
     * 
     */
    public ImageCRSType createImageCRSType() {
        return new ImageCRSType();
    }

    /**
     * Create an instance of {@link CoordinateSystemPropertyType }
     * 
     */
    public CoordinateSystemPropertyType createCoordinateSystemPropertyType() {
        return new CoordinateSystemPropertyType();
    }

    /**
     * Create an instance of {@link SecondDefiningParameterPropertyElement }
     * 
     */
    public SecondDefiningParameterPropertyElement createSecondDefiningParameterPropertyElement() {
        return new SecondDefiningParameterPropertyElement();
    }

    /**
     * Create an instance of {@link GeometryArrayPropertyType }
     * 
     */
    public GeometryArrayPropertyType createGeometryArrayPropertyType() {
        return new GeometryArrayPropertyType();
    }

    /**
     * Create an instance of {@link LinearCSPropertyType }
     * 
     */
    public LinearCSPropertyType createLinearCSPropertyType() {
        return new LinearCSPropertyType();
    }

    /**
     * Create an instance of {@link DirectedObservationAtDistanceType }
     * 
     */
    public DirectedObservationAtDistanceType createDirectedObservationAtDistanceType() {
        return new DirectedObservationAtDistanceType();
    }

    /**
     * Create an instance of {@link DirectedObservationType }
     * 
     */
    public DirectedObservationType createDirectedObservationType() {
        return new DirectedObservationType();
    }

    /**
     * Create an instance of {@link ObservationType }
     * 
     */
    public ObservationType createObservationType() {
        return new ObservationType();
    }

    /**
     * Create an instance of {@link IndirectEntryType }
     * 
     */
    public IndirectEntryType createIndirectEntryType() {
        return new IndirectEntryType();
    }

    /**
     * Create an instance of {@link BezierType }
     * 
     */
    public BezierType createBezierType() {
        return new BezierType();
    }

    /**
     * Create an instance of {@link BSplineType }
     * 
     */
    public BSplineType createBSplineType() {
        return new BSplineType();
    }

    /**
     * Create an instance of {@link OperationParameterPropertyType }
     * 
     */
    public OperationParameterPropertyType createOperationParameterPropertyType() {
        return new OperationParameterPropertyType();
    }

    /**
     * Create an instance of {@link CurveSegmentArrayPropertyType }
     * 
     */
    public CurveSegmentArrayPropertyType createCurveSegmentArrayPropertyType() {
        return new CurveSegmentArrayPropertyType();
    }

    /**
     * Create an instance of {@link TargetPropertyType }
     * 
     */
    public TargetPropertyType createTargetPropertyType() {
        return new TargetPropertyType();
    }

    /**
     * Create an instance of {@link SingleOperationPropertyType }
     * 
     */
    public SingleOperationPropertyType createSingleOperationPropertyType() {
        return new SingleOperationPropertyType();
    }

    /**
     * Create an instance of {@link CylinderType }
     * 
     */
    public CylinderType createCylinderType() {
        return new CylinderType();
    }

    /**
     * Create an instance of {@link VerticalCRSType }
     * 
     */
    public VerticalCRSType createVerticalCRSType() {
        return new VerticalCRSType();
    }

    /**
     * Create an instance of {@link TimeInstantType }
     * 
     */
    public TimeInstantType createTimeInstantType() {
        return new TimeInstantType();
    }

    /**
     * Create an instance of {@link GridType }
     * 
     */
    public GridType createGridType() {
        return new GridType();
    }

    /**
     * Create an instance of {@link ValueArrayPropertyType }
     * 
     */
    public ValueArrayPropertyType createValueArrayPropertyType() {
        return new ValueArrayPropertyType();
    }

    /**
     * Create an instance of {@link OperationMethodPropertyType }
     * 
     */
    public OperationMethodPropertyType createOperationMethodPropertyType() {
        return new OperationMethodPropertyType();
    }

    /**
     * Create an instance of {@link CartesianCSPropertyType }
     * 
     */
    public CartesianCSPropertyType createCartesianCSPropertyType() {
        return new CartesianCSPropertyType();
    }

    /**
     * Create an instance of {@link AbstractRingPropertyType }
     * 
     */
    public AbstractRingPropertyType createAbstractRingPropertyType() {
        return new AbstractRingPropertyType();
    }

    /**
     * Create an instance of {@link FeatureCollectionType }
     * 
     */
    public FeatureCollectionType createFeatureCollectionType() {
        return new FeatureCollectionType();
    }

    /**
     * Create an instance of {@link LocationPropertyType }
     * 
     */
    public LocationPropertyType createLocationPropertyType() {
        return new LocationPropertyType();
    }

    /**
     * Create an instance of {@link TemporalCSPropertyType }
     * 
     */
    public TemporalCSPropertyType createTemporalCSPropertyType() {
        return new TemporalCSPropertyType();
    }

    /**
     * Create an instance of {@link MultiCurveDomainType }
     * 
     */
    public MultiCurveDomainType createMultiCurveDomainType() {
        return new MultiCurveDomainType();
    }

    /**
     * Create an instance of {@link SphericalCSPropertyType }
     * 
     */
    public SphericalCSPropertyType createSphericalCSPropertyType() {
        return new SphericalCSPropertyType();
    }

    /**
     * Create an instance of {@link RectifiedGridCoverageType }
     * 
     */
    public RectifiedGridCoverageType createRectifiedGridCoverageType() {
        return new RectifiedGridCoverageType();
    }

    /**
     * Create an instance of {@link ObliqueCartesianCSPropertyType }
     * 
     */
    public ObliqueCartesianCSPropertyType createObliqueCartesianCSPropertyType() {
        return new ObliqueCartesianCSPropertyType();
    }

    /**
     * Create an instance of {@link TimeCalendarType }
     * 
     */
    public TimeCalendarType createTimeCalendarType() {
        return new TimeCalendarType();
    }

    /**
     * Create an instance of {@link RectifiedGridType }
     * 
     */
    public RectifiedGridType createRectifiedGridType() {
        return new RectifiedGridType();
    }

    /**
     * Create an instance of {@link MultiGeometryPropertyType }
     * 
     */
    public MultiGeometryPropertyType createMultiGeometryPropertyType() {
        return new MultiGeometryPropertyType();
    }

    /**
     * Create an instance of {@link CoordinatesType }
     * 
     */
    public CoordinatesType createCoordinatesType() {
        return new CoordinatesType();
    }

    /**
     * Create an instance of {@link MeasureListType }
     * 
     */
    public MeasureListType createMeasureListType() {
        return new MeasureListType();
    }

    /**
     * Create an instance of {@link PrimeMeridianPropertyType }
     * 
     */
    public PrimeMeridianPropertyType createPrimeMeridianPropertyType() {
        return new PrimeMeridianPropertyType();
    }

    /**
     * Create an instance of {@link CRSPropertyType }
     * 
     */
    public CRSPropertyType createCRSPropertyType() {
        return new CRSPropertyType();
    }

    /**
     * Create an instance of {@link TopoPointPropertyType }
     * 
     */
    public TopoPointPropertyType createTopoPointPropertyType() {
        return new TopoPointPropertyType();
    }

    /**
     * Create an instance of {@link ConversionType }
     * 
     */
    public ConversionType createConversionType() {
        return new ConversionType();
    }

    /**
     * Create an instance of {@link BagType }
     * 
     */
    public BagType createBagType() {
        return new BagType();
    }

    /**
     * Create an instance of {@link TimeCalendarEraType }
     * 
     */
    public TimeCalendarEraType createTimeCalendarEraType() {
        return new TimeCalendarEraType();
    }

    /**
     * Create an instance of {@link OffsetCurveType }
     * 
     */
    public OffsetCurveType createOffsetCurveType() {
        return new OffsetCurveType();
    }

    /**
     * Create an instance of {@link GeographicCRSPropertyType }
     * 
     */
    public GeographicCRSPropertyType createGeographicCRSPropertyType() {
        return new GeographicCRSPropertyType();
    }

    /**
     * Create an instance of {@link PointArrayPropertyType }
     * 
     */
    public PointArrayPropertyType createPointArrayPropertyType() {
        return new PointArrayPropertyType();
    }

    /**
     * Create an instance of {@link EngineeringDatumPropertyType }
     * 
     */
    public EngineeringDatumPropertyType createEngineeringDatumPropertyType() {
        return new EngineeringDatumPropertyType();
    }

    /**
     * Create an instance of {@link MultiCurveType }
     * 
     */
    public MultiCurveType createMultiCurveType() {
        return new MultiCurveType();
    }

    /**
     * Create an instance of {@link EngineeringCRSType }
     * 
     */
    public EngineeringCRSType createEngineeringCRSType() {
        return new EngineeringCRSType();
    }

    /**
     * Create an instance of {@link PriorityLocationPropertyType }
     * 
     */
    public PriorityLocationPropertyType createPriorityLocationPropertyType() {
        return new PriorityLocationPropertyType();
    }

    /**
     * Create an instance of {@link DirectedFacePropertyType }
     * 
     */
    public DirectedFacePropertyType createDirectedFacePropertyType() {
        return new DirectedFacePropertyType();
    }

    /**
     * Create an instance of {@link GridFunctionType }
     * 
     */
    public GridFunctionType createGridFunctionType() {
        return new GridFunctionType();
    }

    /**
     * Create an instance of {@link EllipsoidalCSType }
     * 
     */
    public EllipsoidalCSType createEllipsoidalCSType() {
        return new EllipsoidalCSType();
    }

    /**
     * Create an instance of {@link CurveArrayPropertyType }
     * 
     */
    public CurveArrayPropertyType createCurveArrayPropertyType() {
        return new CurveArrayPropertyType();
    }

    /**
     * Create an instance of {@link GeocentricCRSType }
     * 
     */
    public GeocentricCRSType createGeocentricCRSType() {
        return new GeocentricCRSType();
    }

    /**
     * Create an instance of {@link CartesianCSType }
     * 
     */
    public CartesianCSType createCartesianCSType() {
        return new CartesianCSType();
    }

    /**
     * Create an instance of {@link ArrayType }
     * 
     */
    public ArrayType createArrayType() {
        return new ArrayType();
    }

    /**
     * Create an instance of {@link DirectPositionType }
     * 
     */
    public DirectPositionType createDirectPositionType() {
        return new DirectPositionType();
    }

    /**
     * Create an instance of {@link GridDomainType }
     * 
     */
    public GridDomainType createGridDomainType() {
        return new GridDomainType();
    }

    /**
     * Create an instance of {@link AbstractGeneralParameterValuePropertyType }
     * 
     */
    public AbstractGeneralParameterValuePropertyType createAbstractGeneralParameterValuePropertyType() {
        return new AbstractGeneralParameterValuePropertyType();
    }

    /**
     * Create an instance of {@link GenericMetaDataType }
     * 
     */
    public GenericMetaDataType createGenericMetaDataType() {
        return new GenericMetaDataType();
    }

    /**
     * Create an instance of {@link CodeOrNilReasonListType }
     * 
     */
    public CodeOrNilReasonListType createCodeOrNilReasonListType() {
        return new CodeOrNilReasonListType();
    }

    /**
     * Create an instance of {@link TimePeriodType }
     * 
     */
    public TimePeriodType createTimePeriodType() {
        return new TimePeriodType();
    }

    /**
     * Create an instance of {@link ProjectedCRSPropertyType }
     * 
     */
    public ProjectedCRSPropertyType createProjectedCRSPropertyType() {
        return new ProjectedCRSPropertyType();
    }

    /**
     * Create an instance of {@link TimeNodeType }
     * 
     */
    public TimeNodeType createTimeNodeType() {
        return new TimeNodeType();
    }

    /**
     * Create an instance of {@link CurveType }
     * 
     */
    public CurveType createCurveType() {
        return new CurveType();
    }

    /**
     * Create an instance of {@link MultiCurvePropertyType }
     * 
     */
    public MultiCurvePropertyType createMultiCurvePropertyType() {
        return new MultiCurvePropertyType();
    }

    /**
     * Create an instance of {@link VerticalCRSPropertyType }
     * 
     */
    public VerticalCRSPropertyType createVerticalCRSPropertyType() {
        return new VerticalCRSPropertyType();
    }

    /**
     * Create an instance of {@link TimeCoordinateSystemType }
     * 
     */
    public TimeCoordinateSystemType createTimeCoordinateSystemType() {
        return new TimeCoordinateSystemType();
    }

    /**
     * Create an instance of {@link GeometryPropertyType }
     * 
     */
    public GeometryPropertyType createGeometryPropertyType() {
        return new GeometryPropertyType();
    }

    /**
     * Create an instance of {@link TopoCurveType }
     * 
     */
    public TopoCurveType createTopoCurveType() {
        return new TopoCurveType();
    }

    /**
     * Create an instance of {@link SecondDefiningParameterElement }
     * 
     */
    public SecondDefiningParameterElement createSecondDefiningParameterElement() {
        return new SecondDefiningParameterElement();
    }

    /**
     * Create an instance of {@link LengthType }
     * 
     */
    public LengthType createLengthType() {
        return new LengthType();
    }

    /**
     * Create an instance of {@link DerivedCRSType }
     * 
     */
    public DerivedCRSType createDerivedCRSType() {
        return new DerivedCRSType();
    }

    /**
     * Create an instance of {@link GeneralConversionPropertyType }
     * 
     */
    public GeneralConversionPropertyType createGeneralConversionPropertyType() {
        return new GeneralConversionPropertyType();
    }

    /**
     * Create an instance of {@link AngleType }
     * 
     */
    public AngleType createAngleType() {
        return new AngleType();
    }

    /**
     * Create an instance of {@link ResultType }
     * 
     */
    public ResultType createResultType() {
        return new ResultType();
    }

    /**
     * Create an instance of {@link CubicSplineType }
     * 
     */
    public CubicSplineType createCubicSplineType() {
        return new CubicSplineType();
    }

    /**
     * Create an instance of {@link TemporalCRSPropertyType }
     * 
     */
    public TemporalCRSPropertyType createTemporalCRSPropertyType() {
        return new TemporalCRSPropertyType();
    }

    /**
     * Create an instance of {@link TopoPrimitiveArrayAssociationType }
     * 
     */
    public TopoPrimitiveArrayAssociationType createTopoPrimitiveArrayAssociationType() {
        return new TopoPrimitiveArrayAssociationType();
    }

    /**
     * Create an instance of {@link ProjectedCRSType }
     * 
     */
    public ProjectedCRSType createProjectedCRSType() {
        return new ProjectedCRSType();
    }

    /**
     * Create an instance of {@link MultiSurfaceType }
     * 
     */
    public MultiSurfaceType createMultiSurfaceType() {
        return new MultiSurfaceType();
    }

    /**
     * Create an instance of {@link TimeOrdinalEraType }
     * 
     */
    public TimeOrdinalEraType createTimeOrdinalEraType() {
        return new TimeOrdinalEraType();
    }

    /**
     * Create an instance of {@link DefinitionProxyType }
     * 
     */
    public DefinitionProxyType createDefinitionProxyType() {
        return new DefinitionProxyType();
    }

    /**
     * Create an instance of {@link EngineeringCRSPropertyType }
     * 
     */
    public EngineeringCRSPropertyType createEngineeringCRSPropertyType() {
        return new EngineeringCRSPropertyType();
    }

    /**
     * Create an instance of {@link FaceType }
     * 
     */
    public FaceType createFaceType() {
        return new FaceType();
    }

    /**
     * Create an instance of {@link TopoCurvePropertyType }
     * 
     */
    public TopoCurvePropertyType createTopoCurvePropertyType() {
        return new TopoCurvePropertyType();
    }

    /**
     * Create an instance of {@link MultiSolidPropertyType }
     * 
     */
    public MultiSolidPropertyType createMultiSolidPropertyType() {
        return new MultiSolidPropertyType();
    }

    /**
     * Create an instance of {@link LinearRingType }
     * 
     */
    public LinearRingType createLinearRingType() {
        return new LinearRingType();
    }

    /**
     * Create an instance of {@link DictionaryType }
     * 
     */
    public DictionaryType createDictionaryType() {
        return new DictionaryType();
    }

    /**
     * Create an instance of {@link CylindricalCSType }
     * 
     */
    public CylindricalCSType createCylindricalCSType() {
        return new CylindricalCSType();
    }

    /**
     * Create an instance of {@link UnitOfMeasureType }
     * 
     */
    public UnitOfMeasureType createUnitOfMeasureType() {
        return new UnitOfMeasureType();
    }

    /**
     * Create an instance of {@link TimeCSPropertyType }
     * 
     */
    public TimeCSPropertyType createTimeCSPropertyType() {
        return new TimeCSPropertyType();
    }

    /**
     * Create an instance of {@link ParameterValueGroupType }
     * 
     */
    public ParameterValueGroupType createParameterValueGroupType() {
        return new ParameterValueGroupType();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link DynamicFeatureCollectionType }
     * 
     */
    public DynamicFeatureCollectionType createDynamicFeatureCollectionType() {
        return new DynamicFeatureCollectionType();
    }

    /**
     * Create an instance of {@link DynamicFeatureType }
     * 
     */
    public DynamicFeatureType createDynamicFeatureType() {
        return new DynamicFeatureType();
    }

    /**
     * Create an instance of {@link SolidType }
     * 
     */
    public SolidType createSolidType() {
        return new SolidType();
    }

    /**
     * Create an instance of {@link MultiCurveCoverageType }
     * 
     */
    public MultiCurveCoverageType createMultiCurveCoverageType() {
        return new MultiCurveCoverageType();
    }

    /**
     * Create an instance of {@link OrientableCurveType }
     * 
     */
    public OrientableCurveType createOrientableCurveType() {
        return new OrientableCurveType();
    }

    /**
     * Create an instance of {@link PolygonPatchArrayPropertyType }
     * 
     */
    public PolygonPatchArrayPropertyType createPolygonPatchArrayPropertyType() {
        return new PolygonPatchArrayPropertyType();
    }

    /**
     * Create an instance of {@link MultiGeometryType }
     * 
     */
    public MultiGeometryType createMultiGeometryType() {
        return new MultiGeometryType();
    }

    /**
     * Create an instance of {@link TopoComplexType }
     * 
     */
    public TopoComplexType createTopoComplexType() {
        return new TopoComplexType();
    }

    /**
     * Create an instance of {@link MappingRuleType }
     * 
     */
    public MappingRuleType createMappingRuleType() {
        return new MappingRuleType();
    }

    /**
     * Create an instance of {@link DirectPositionListType }
     * 
     */
    public DirectPositionListType createDirectPositionListType() {
        return new DirectPositionListType();
    }

    /**
     * Create an instance of {@link TimeIntervalLengthType }
     * 
     */
    public TimeIntervalLengthType createTimeIntervalLengthType() {
        return new TimeIntervalLengthType();
    }

    /**
     * Create an instance of {@link MetaDataPropertyType }
     * 
     */
    public MetaDataPropertyType createMetaDataPropertyType() {
        return new MetaDataPropertyType();
    }

    /**
     * Create an instance of {@link ArcByCenterPointType }
     * 
     */
    public ArcByCenterPointType createArcByCenterPointType() {
        return new ArcByCenterPointType();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link MultiSolidDomainType }
     * 
     */
    public MultiSolidDomainType createMultiSolidDomainType() {
        return new MultiSolidDomainType();
    }

    /**
     * Create an instance of {@link MultiSolidType }
     * 
     */
    public MultiSolidType createMultiSolidType() {
        return new MultiSolidType();
    }

    /**
     * Create an instance of {@link EngineeringDatumType }
     * 
     */
    public EngineeringDatumType createEngineeringDatumType() {
        return new EngineeringDatumType();
    }

    /**
     * Create an instance of {@link DomainOfValidityElement }
     * 
     */
    public DomainOfValidityElement createDomainOfValidityElement() {
        return new DomainOfValidityElement();
    }

    /**
     * Create an instance of {@link TimePositionType }
     * 
     */
    public TimePositionType createTimePositionType() {
        return new TimePositionType();
    }

    /**
     * Create an instance of {@link CompositeValueType }
     * 
     */
    public CompositeValueType createCompositeValueType() {
        return new CompositeValueType();
    }

    /**
     * Create an instance of {@link ImageDatumType }
     * 
     */
    public ImageDatumType createImageDatumType() {
        return new ImageDatumType();
    }

    /**
     * Create an instance of {@link TopoSurfaceType }
     * 
     */
    public TopoSurfaceType createTopoSurfaceType() {
        return new TopoSurfaceType();
    }

    /**
     * Create an instance of {@link ImageDatumPropertyType }
     * 
     */
    public ImageDatumPropertyType createImageDatumPropertyType() {
        return new ImageDatumPropertyType();
    }

    /**
     * Create an instance of {@link ConeType }
     * 
     */
    public ConeType createConeType() {
        return new ConeType();
    }

    /**
     * Create an instance of {@link AffineCSType }
     * 
     */
    public AffineCSType createAffineCSType() {
        return new AffineCSType();
    }

    /**
     * Create an instance of {@link MultiPointCoverageType }
     * 
     */
    public MultiPointCoverageType createMultiPointCoverageType() {
        return new MultiPointCoverageType();
    }

    /**
     * Create an instance of {@link MovingObjectStatusType }
     * 
     */
    public MovingObjectStatusType createMovingObjectStatusType() {
        return new MovingObjectStatusType();
    }

    /**
     * Create an instance of {@link EllipsoidType }
     * 
     */
    public EllipsoidType createEllipsoidType() {
        return new EllipsoidType();
    }

    /**
     * Create an instance of {@link ArcStringByBulgeType }
     * 
     */
    public ArcStringByBulgeType createArcStringByBulgeType() {
        return new ArcStringByBulgeType();
    }

    /**
     * Create an instance of {@link TransformationPropertyType }
     * 
     */
    public TransformationPropertyType createTransformationPropertyType() {
        return new TransformationPropertyType();
    }

    /**
     * Create an instance of {@link TemporalDatumType }
     * 
     */
    public TemporalDatumType createTemporalDatumType() {
        return new TemporalDatumType();
    }

    /**
     * Create an instance of {@link TimeEdgeType }
     * 
     */
    public TimeEdgeType createTimeEdgeType() {
        return new TimeEdgeType();
    }

    /**
     * Create an instance of {@link ContainerPropertyType }
     * 
     */
    public ContainerPropertyType createContainerPropertyType() {
        return new ContainerPropertyType();
    }

    /**
     * Create an instance of {@link DirectedTopoSolidPropertyType }
     * 
     */
    public DirectedTopoSolidPropertyType createDirectedTopoSolidPropertyType() {
        return new DirectedTopoSolidPropertyType();
    }

    /**
     * Create an instance of {@link TemporalCSType }
     * 
     */
    public TemporalCSType createTemporalCSType() {
        return new TemporalCSType();
    }

    /**
     * Create an instance of {@link ValueArrayType }
     * 
     */
    public ValueArrayType createValueArrayType() {
        return new ValueArrayType();
    }

    /**
     * Create an instance of {@link UserDefinedCSType }
     * 
     */
    public UserDefinedCSType createUserDefinedCSType() {
        return new UserDefinedCSType();
    }

    /**
     * Create an instance of {@link CylindricalCSPropertyType }
     * 
     */
    public CylindricalCSPropertyType createCylindricalCSPropertyType() {
        return new CylindricalCSPropertyType();
    }

    /**
     * Create an instance of {@link UserDefinedCSPropertyType }
     * 
     */
    public UserDefinedCSPropertyType createUserDefinedCSPropertyType() {
        return new UserDefinedCSPropertyType();
    }

    /**
     * Create an instance of {@link GeodeticDatumPropertyType }
     * 
     */
    public GeodeticDatumPropertyType createGeodeticDatumPropertyType() {
        return new GeodeticDatumPropertyType();
    }

    /**
     * Create an instance of {@link GeneralTransformationPropertyType }
     * 
     */
    public GeneralTransformationPropertyType createGeneralTransformationPropertyType() {
        return new GeneralTransformationPropertyType();
    }

    /**
     * Create an instance of {@link GeodeticCRSPropertyType }
     * 
     */
    public GeodeticCRSPropertyType createGeodeticCRSPropertyType() {
        return new GeodeticCRSPropertyType();
    }

    /**
     * Create an instance of {@link CompositeSolidType }
     * 
     */
    public CompositeSolidType createCompositeSolidType() {
        return new CompositeSolidType();
    }

    /**
     * Create an instance of {@link EnvelopeType }
     * 
     */
    public EnvelopeType createEnvelopeType() {
        return new EnvelopeType();
    }

    /**
     * Create an instance of {@link TimeOrdinalReferenceSystemType }
     * 
     */
    public TimeOrdinalReferenceSystemType createTimeOrdinalReferenceSystemType() {
        return new TimeOrdinalReferenceSystemType();
    }

    /**
     * Create an instance of {@link LineStringType }
     * 
     */
    public LineStringType createLineStringType() {
        return new LineStringType();
    }

    /**
     * Create an instance of {@link TopoSurfacePropertyType }
     * 
     */
    public TopoSurfacePropertyType createTopoSurfacePropertyType() {
        return new TopoSurfacePropertyType();
    }

    /**
     * Create an instance of {@link BaseUnitType }
     * 
     */
    public BaseUnitType createBaseUnitType() {
        return new BaseUnitType();
    }

    /**
     * Create an instance of {@link FeatureArrayPropertyType }
     * 
     */
    public FeatureArrayPropertyType createFeatureArrayPropertyType() {
        return new FeatureArrayPropertyType();
    }

    /**
     * Create an instance of {@link DerivedCRSPropertyType }
     * 
     */
    public DerivedCRSPropertyType createDerivedCRSPropertyType() {
        return new DerivedCRSPropertyType();
    }

    /**
     * Create an instance of {@link MultiPointDomainType }
     * 
     */
    public MultiPointDomainType createMultiPointDomainType() {
        return new MultiPointDomainType();
    }

    /**
     * Create an instance of {@link ConcatenatedOperationType }
     * 
     */
    public ConcatenatedOperationType createConcatenatedOperationType() {
        return new ConcatenatedOperationType();
    }

    /**
     * Create an instance of {@link CircleByCenterPointType }
     * 
     */
    public CircleByCenterPointType createCircleByCenterPointType() {
        return new CircleByCenterPointType();
    }

    /**
     * Create an instance of {@link SphereType }
     * 
     */
    public SphereType createSphereType() {
        return new SphereType();
    }

    /**
     * Create an instance of {@link TopoPointType }
     * 
     */
    public TopoPointType createTopoPointType() {
        return new TopoPointType();
    }

    /**
     * Create an instance of {@link GeodeticDatumType }
     * 
     */
    public GeodeticDatumType createGeodeticDatumType() {
        return new GeodeticDatumType();
    }

    /**
     * Create an instance of {@link MultiSurfaceDomainType }
     * 
     */
    public MultiSurfaceDomainType createMultiSurfaceDomainType() {
        return new MultiSurfaceDomainType();
    }

    /**
     * Create an instance of {@link OperationParameterGroupType }
     * 
     */
    public OperationParameterGroupType createOperationParameterGroupType() {
        return new OperationParameterGroupType();
    }

    /**
     * Create an instance of {@link BoundingShapeType }
     * 
     */
    public BoundingShapeType createBoundingShapeType() {
        return new BoundingShapeType();
    }

    /**
     * Create an instance of {@link CompositeCurveType }
     * 
     */
    public CompositeCurveType createCompositeCurveType() {
        return new CompositeCurveType();
    }

    /**
     * Create an instance of {@link OperationMethodType }
     * 
     */
    public OperationMethodType createOperationMethodType() {
        return new OperationMethodType();
    }

    /**
     * Create an instance of {@link Count }
     * 
     */
    public Count createCount() {
        return new Count();
    }

    /**
     * Create an instance of {@link DirectionPropertyType }
     * 
     */
    public DirectionPropertyType createDirectionPropertyType() {
        return new DirectionPropertyType();
    }

    /**
     * Create an instance of {@link VectorType }
     * 
     */
    public VectorType createVectorType() {
        return new VectorType();
    }

    /**
     * Create an instance of {@link CompoundCRSType }
     * 
     */
    public CompoundCRSType createCompoundCRSType() {
        return new CompoundCRSType();
    }

    /**
     * Create an instance of {@link DirectedNodePropertyType }
     * 
     */
    public DirectedNodePropertyType createDirectedNodePropertyType() {
        return new DirectedNodePropertyType();
    }

    /**
     * Create an instance of {@link TopoPrimitiveMemberType }
     * 
     */
    public TopoPrimitiveMemberType createTopoPrimitiveMemberType() {
        return new TopoPrimitiveMemberType();
    }

    /**
     * Create an instance of {@link MultiPointType }
     * 
     */
    public MultiPointType createMultiPointType() {
        return new MultiPointType();
    }

    /**
     * Create an instance of {@link MultiSurfaceCoverageType }
     * 
     */
    public MultiSurfaceCoverageType createMultiSurfaceCoverageType() {
        return new MultiSurfaceCoverageType();
    }

    /**
     * Create an instance of {@link Quantity }
     * 
     */
    public Quantity createQuantity() {
        return new Quantity();
    }

    /**
     * Create an instance of {@link DataBlockType }
     * 
     */
    public DataBlockType createDataBlockType() {
        return new DataBlockType();
    }

    /**
     * Create an instance of {@link FileType }
     * 
     */
    public FileType createFileType() {
        return new FileType();
    }

    /**
     * Create an instance of {@link ImageCRSPropertyType }
     * 
     */
    public ImageCRSPropertyType createImageCRSPropertyType() {
        return new ImageCRSPropertyType();
    }

    /**
     * Create an instance of {@link DegreesType }
     * 
     */
    public DegreesType createDegreesType() {
        return new DegreesType();
    }

    /**
     * Create an instance of {@link EnvelopeWithTimePeriodType }
     * 
     */
    public EnvelopeWithTimePeriodType createEnvelopeWithTimePeriodType() {
        return new EnvelopeWithTimePeriodType();
    }

    /**
     * Create an instance of {@link PolarCSType }
     * 
     */
    public PolarCSType createPolarCSType() {
        return new PolarCSType();
    }

    /**
     * Create an instance of {@link ArcByBulgeType }
     * 
     */
    public ArcByBulgeType createArcByBulgeType() {
        return new ArcByBulgeType();
    }

    /**
     * Create an instance of {@link TopoVolumeType }
     * 
     */
    public TopoVolumeType createTopoVolumeType() {
        return new TopoVolumeType();
    }

    /**
     * Create an instance of {@link OperationParameterGroupPropertyType }
     * 
     */
    public OperationParameterGroupPropertyType createOperationParameterGroupPropertyType() {
        return new OperationParameterGroupPropertyType();
    }

    /**
     * Create an instance of {@link CompoundCRSPropertyType }
     * 
     */
    public CompoundCRSPropertyType createCompoundCRSPropertyType() {
        return new CompoundCRSPropertyType();
    }

    /**
     * Create an instance of {@link AngleChoiceType }
     * 
     */
    public AngleChoiceType createAngleChoiceType() {
        return new AngleChoiceType();
    }

    /**
     * Create an instance of {@link TimeTopologyPrimitivePropertyType }
     * 
     */
    public TimeTopologyPrimitivePropertyType createTimeTopologyPrimitivePropertyType() {
        return new TimeTopologyPrimitivePropertyType();
    }

    /**
     * Create an instance of {@link GridLimitsType }
     * 
     */
    public GridLimitsType createGridLimitsType() {
        return new GridLimitsType();
    }

    /**
     * Create an instance of {@link RingPropertyType }
     * 
     */
    public RingPropertyType createRingPropertyType() {
        return new RingPropertyType();
    }

    /**
     * Create an instance of {@link VolumeType }
     * 
     */
    public VolumeType createVolumeType() {
        return new VolumeType();
    }

    /**
     * Create an instance of {@link CountPropertyType }
     * 
     */
    public CountPropertyType createCountPropertyType() {
        return new CountPropertyType();
    }

    /**
     * Create an instance of {@link ShellPropertyType }
     * 
     */
    public ShellPropertyType createShellPropertyType() {
        return new ShellPropertyType();
    }

    /**
     * Create an instance of {@link QuantityPropertyType }
     * 
     */
    public QuantityPropertyType createQuantityPropertyType() {
        return new QuantityPropertyType();
    }

    /**
     * Create an instance of {@link TimeInstantPropertyType }
     * 
     */
    public TimeInstantPropertyType createTimeInstantPropertyType() {
        return new TimeInstantPropertyType();
    }

    /**
     * Create an instance of {@link ScaleType }
     * 
     */
    public ScaleType createScaleType() {
        return new ScaleType();
    }

    /**
     * Create an instance of {@link LineStringSegmentArrayPropertyType }
     * 
     */
    public LineStringSegmentArrayPropertyType createLineStringSegmentArrayPropertyType() {
        return new LineStringSegmentArrayPropertyType();
    }

    /**
     * Create an instance of {@link DefinitionBaseType }
     * 
     */
    public DefinitionBaseType createDefinitionBaseType() {
        return new DefinitionBaseType();
    }

    /**
     * Create an instance of {@link TimeNodePropertyType }
     * 
     */
    public TimeNodePropertyType createTimeNodePropertyType() {
        return new TimeNodePropertyType();
    }

    /**
     * Create an instance of {@link LinearRingPropertyType }
     * 
     */
    public LinearRingPropertyType createLinearRingPropertyType() {
        return new LinearRingPropertyType();
    }

    /**
     * Create an instance of {@link TimeOrdinalEraPropertyType }
     * 
     */
    public TimeOrdinalEraPropertyType createTimeOrdinalEraPropertyType() {
        return new TimeOrdinalEraPropertyType();
    }

    /**
     * Create an instance of {@link KnotPropertyType }
     * 
     */
    public KnotPropertyType createKnotPropertyType() {
        return new KnotPropertyType();
    }

    /**
     * Create an instance of {@link TimeTopologyComplexPropertyType }
     * 
     */
    public TimeTopologyComplexPropertyType createTimeTopologyComplexPropertyType() {
        return new TimeTopologyComplexPropertyType();
    }

    /**
     * Create an instance of {@link RelatedTimeType }
     * 
     */
    public RelatedTimeType createRelatedTimeType() {
        return new RelatedTimeType();
    }

    /**
     * Create an instance of {@link BooleanPropertyType }
     * 
     */
    public BooleanPropertyType createBooleanPropertyType() {
        return new BooleanPropertyType();
    }

    /**
     * Create an instance of {@link GeometricPrimitivePropertyType }
     * 
     */
    public GeometricPrimitivePropertyType createGeometricPrimitivePropertyType() {
        return new GeometricPrimitivePropertyType();
    }

    /**
     * Create an instance of {@link TimeCalendarEraPropertyType }
     * 
     */
    public TimeCalendarEraPropertyType createTimeCalendarEraPropertyType() {
        return new TimeCalendarEraPropertyType();
    }

    /**
     * Create an instance of {@link GridLengthType }
     * 
     */
    public GridLengthType createGridLengthType() {
        return new GridLengthType();
    }

    /**
     * Create an instance of {@link KnotType }
     * 
     */
    public KnotType createKnotType() {
        return new KnotType();
    }

    /**
     * Create an instance of {@link TimeEdgePropertyType }
     * 
     */
    public TimeEdgePropertyType createTimeEdgePropertyType() {
        return new TimeEdgePropertyType();
    }

    /**
     * Create an instance of {@link CodeListType }
     * 
     */
    public CodeListType createCodeListType() {
        return new CodeListType();
    }

    /**
     * Create an instance of {@link FormulaType }
     * 
     */
    public FormulaType createFormulaType() {
        return new FormulaType();
    }

    /**
     * Create an instance of {@link SpeedType }
     * 
     */
    public SpeedType createSpeedType() {
        return new SpeedType();
    }

    /**
     * Create an instance of {@link TimeCalendarPropertyType }
     * 
     */
    public TimeCalendarPropertyType createTimeCalendarPropertyType() {
        return new TimeCalendarPropertyType();
    }

    /**
     * Create an instance of {@link TimePeriodPropertyType }
     * 
     */
    public TimePeriodPropertyType createTimePeriodPropertyType() {
        return new TimePeriodPropertyType();
    }

    /**
     * Create an instance of {@link CategoryPropertyType }
     * 
     */
    public CategoryPropertyType createCategoryPropertyType() {
        return new CategoryPropertyType();
    }

    /**
     * Create an instance of {@link DirectionVectorType }
     * 
     */
    public DirectionVectorType createDirectionVectorType() {
        return new DirectionVectorType();
    }

    /**
     * Create an instance of {@link DirectionDescriptionType }
     * 
     */
    public DirectionDescriptionType createDirectionDescriptionType() {
        return new DirectionDescriptionType();
    }

    /**
     * Create an instance of {@link TimeClockPropertyType }
     * 
     */
    public TimeClockPropertyType createTimeClockPropertyType() {
        return new TimeClockPropertyType();
    }

    /**
     * Create an instance of {@link GridEnvelopeType }
     * 
     */
    public GridEnvelopeType createGridEnvelopeType() {
        return new GridEnvelopeType();
    }

    /**
     * Create an instance of {@link GeometricComplexPropertyType }
     * 
     */
    public GeometricComplexPropertyType createGeometricComplexPropertyType() {
        return new GeometricComplexPropertyType();
    }

    /**
     * Create an instance of {@link AreaType }
     * 
     */
    public AreaType createAreaType() {
        return new AreaType();
    }

    /**
     * Create an instance of {@link SequenceRuleType }
     * 
     */
    public SequenceRuleType createSequenceRuleType() {
        return new SequenceRuleType();
    }

    /**
     * Create an instance of {@link TimeType }
     * 
     */
    public TimeType createTimeType() {
        return new TimeType();
    }

    /**
     * Create an instance of {@link AbstractGriddedSurfaceType.Rows.Row }
     * 
     */
    public AbstractGriddedSurfaceType.Rows.Row createAbstractGriddedSurfaceTypeRowsRow() {
        return new AbstractGriddedSurfaceType.Rows.Row();
    }

    /**
     * Create an instance of {@link TinType.ControlPoint }
     * 
     */
    public TinType.ControlPoint createTinTypeControlPoint() {
        return new TinType.ControlPoint();
    }

    /**
     * Create an instance of {@link ClothoidType.RefLocation }
     * 
     */
    public ClothoidType.RefLocation createClothoidTypeRefLocation() {
        return new ClothoidType.RefLocation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "statusReference")
    public JAXBElement<ReferenceType> createStatusReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_StatusReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "sourceDimensions")
    public JAXBElement<BigInteger> createSourceDimensions(BigInteger value) {
        return new JAXBElement<BigInteger>(_SourceDimensions_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DynamicFeatureCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DynamicFeatureCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "DynamicFeature")
    public JAXBElement<DynamicFeatureCollectionType> createDynamicFeatureCollection(DynamicFeatureCollectionType value) {
        return new JAXBElement<DynamicFeatureCollectionType>(_DynamicFeatureCollection_QNAME, DynamicFeatureCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BSplineType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "BSpline", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<BSplineType> createBSpline(BSplineType value) {
        return new JAXBElement<BSplineType>(_BSpline_QNAME, BSplineType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeWithAuthorityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "identifier")
    public JAXBElement<CodeWithAuthorityType> createIdentifier(CodeWithAuthorityType value) {
        return new JAXBElement<CodeWithAuthorityType>(_Identifier_QNAME, CodeWithAuthorityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "sourceCRS")
    public JAXBElement<CRSPropertyType> createSourceCRS(CRSPropertyType value) {
        return new JAXBElement<CRSPropertyType>(_SourceCRS_QNAME, CRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesCartesianCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "cartesianCS")
    public JAXBElement<CartesianCSPropertyType> createUsesCartesianCS(CartesianCSPropertyType value) {
        return new JAXBElement<CartesianCSPropertyType>(_UsesCartesianCS_QNAME, CartesianCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearRingType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LinearRing", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractRing")
    public JAXBElement<LinearRingType> createLinearRing(LinearRingType value) {
        return new JAXBElement<LinearRingType>(_LinearRing_QNAME, LinearRingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DefinitionCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<DictionaryType> createDefinitionCollection(DictionaryType value) {
        return new JAXBElement<DictionaryType>(_DefinitionCollection_QNAME, DictionaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CylindricalCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CylindricalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<CylindricalCSType> createCylindricalCS(CylindricalCSType value) {
        return new JAXBElement<CylindricalCSType>(_CylindricalCS_QNAME, CylindricalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGMLType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGML", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AbstractGMLType> createAbstractGML(AbstractGMLType value) {
        return new JAXBElement<AbstractGMLType>(_AbstractGML_QNAME, AbstractGMLType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSolidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiSolidProperty")
    public JAXBElement<MultiSolidPropertyType> createMultiSolidProperty(MultiSolidPropertyType value) {
        return new JAXBElement<MultiSolidPropertyType>(_MultiSolidProperty_QNAME, MultiSolidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesTimeCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "timeCS")
    public JAXBElement<TimeCSPropertyType> createUsesTimeCS(TimeCSPropertyType value) {
        return new JAXBElement<TimeCSPropertyType>(_UsesTimeCS_QNAME, TimeCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterValueGroupType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ParameterValueGroup", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralParameterValue")
    public JAXBElement<ParameterValueGroupType> createParameterValueGroup(ParameterValueGroupType value) {
        return new JAXBElement<ParameterValueGroupType>(_ParameterValueGroup_QNAME, ParameterValueGroupType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnitOfMeasureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "unitOfMeasure")
    public JAXBElement<UnitOfMeasureType> createUnitOfMeasure(UnitOfMeasureType value) {
        return new JAXBElement<UnitOfMeasureType>(_UnitOfMeasure_QNAME, UnitOfMeasureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoComplexMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "maximalComplex")
    public JAXBElement<TopoComplexMemberType> createMaximalComplex(TopoComplexMemberType value) {
        return new JAXBElement<TopoComplexMemberType>(_MaximalComplex_QNAME, TopoComplexMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeTopologyComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeTopologyComplex", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeComplex")
    public JAXBElement<TimeTopologyComplexType> createTimeTopologyComplex(TimeTopologyComplexType value) {
        return new JAXBElement<TimeTopologyComplexType>(_TimeTopologyComplex_QNAME, TimeTopologyComplexType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "track", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "history")
    public JAXBElement<HistoryPropertyType> createTrack(HistoryPropertyType value) {
        return new JAXBElement<HistoryPropertyType>(_Track_QNAME, HistoryPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcByCenterPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ArcByCenterPoint", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<ArcByCenterPointType> createArcByCenterPoint(ArcByCenterPointType value) {
        return new JAXBElement<ArcByCenterPointType>(_ArcByCenterPoint_QNAME, ArcByCenterPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectPositionListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "posList")
    public JAXBElement<DirectPositionListType> createPosList(DirectPositionListType value) {
        return new JAXBElement<DirectPositionListType>(_PosList_QNAME, DirectPositionListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeIntervalLengthType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "timeInterval")
    public JAXBElement<TimeIntervalLengthType> createTimeInterval(TimeIntervalLengthType value) {
        return new JAXBElement<TimeIntervalLengthType>(_TimeInterval_QNAME, TimeIntervalLengthType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetaDataPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "metaDataProperty")
    public JAXBElement<MetaDataPropertyType> createMetaDataProperty(MetaDataPropertyType value) {
        return new JAXBElement<MetaDataPropertyType>(_MetaDataProperty_QNAME, MetaDataPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCoordinateOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleOperation")
    public JAXBElement<AbstractCoordinateOperationType> createAbstractOperation(AbstractCoordinateOperationType value) {
        return new JAXBElement<AbstractCoordinateOperationType>(_AbstractOperation_QNAME, AbstractCoordinateOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Category }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Category", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValue")
    public JAXBElement<Category> createCategory(Category value) {
        return new JAXBElement<Category>(_Category_QNAME, Category.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSolidDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiSolidDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<MultiSolidDomainType> createMultiSolidDomain(MultiSolidDomainType value) {
        return new JAXBElement<MultiSolidDomainType>(_MultiSolidDomain_QNAME, MultiSolidDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "associationName")
    public JAXBElement<String> createAssociationName(String value) {
        return new JAXBElement<String>(_AssociationName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimePrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimePrimitive", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeObject")
    public JAXBElement<AbstractTimePrimitiveType> createAbstractTimePrimitive(AbstractTimePrimitiveType value) {
        return new JAXBElement<AbstractTimePrimitiveType>(_AbstractTimePrimitive_QNAME, AbstractTimePrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSolidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiSolid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<MultiSolidType> createMultiSolid(MultiSolidType value) {
        return new JAXBElement<MultiSolidType>(_MultiSolid_QNAME, MultiSolidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "verticalDatumRef")
    public JAXBElement<VerticalDatumPropertyType> createVerticalDatumRef(VerticalDatumPropertyType value) {
        return new JAXBElement<VerticalDatumPropertyType>(_VerticalDatumRef_QNAME, VerticalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrientableCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OrientableCurve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurve")
    public JAXBElement<OrientableCurveType> createOrientableCurve(OrientableCurveType value) {
        return new JAXBElement<OrientableCurveType>(_OrientableCurve_QNAME, OrientableCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPosition")
    public JAXBElement<MultiPointPropertyType> createMultiPosition(MultiPointPropertyType value) {
        return new JAXBElement<MultiPointPropertyType>(_MultiPosition_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "surfaceMember")
    public JAXBElement<SurfacePropertyType> createSurfaceMember(SurfacePropertyType value) {
        return new JAXBElement<SurfacePropertyType>(_SurfaceMember_QNAME, SurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "definitionRef")
    public JAXBElement<ReferenceType> createDefinitionRef(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_DefinitionRef_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dataSource")
    public JAXBElement<StringOrRefType> createDataSource(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_DataSource_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Solid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSolid")
    public JAXBElement<SolidType> createSolid(SolidType value) {
        return new JAXBElement<SolidType>(_Solid_QNAME, SolidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurveCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiCurveCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<MultiCurveCoverageType> createMultiCurveCoverage(MultiCurveCoverageType value) {
        return new JAXBElement<MultiCurveCoverageType>(_MultiCurveCoverage_QNAME, MultiCurveCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiGeometryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiGeometry", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<MultiGeometryType> createMultiGeometry(MultiGeometryType value) {
        return new JAXBElement<MultiGeometryType>(_MultiGeometry_QNAME, MultiGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoComplex", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopology")
    public JAXBElement<TopoComplexType> createTopoComplex(TopoComplexType value) {
        return new JAXBElement<TopoComplexType>(_TopoComplex_QNAME, TopoComplexType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MappingRuleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CoverageMappingRule", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<MappingRuleType> createCoverageMappingRule(MappingRuleType value) {
        return new JAXBElement<MappingRuleType>(_CoverageMappingRule_QNAME, MappingRuleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinateSystem")
    public JAXBElement<CoordinateSystemPropertyType> createCoordinateSystem(CoordinateSystemPropertyType value) {
        return new JAXBElement<CoordinateSystemPropertyType>(_CoordinateSystem_QNAME, CoordinateSystemPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCurveProperty")
    public JAXBElement<MultiCurvePropertyType> createMultiCurveProperty(MultiCurvePropertyType value) {
        return new JAXBElement<MultiCurvePropertyType>(_MultiCurveProperty_QNAME, MultiCurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonPatchArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "polygonPatches", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "patches")
    public JAXBElement<PolygonPatchArrayPropertyType> createPolygonPatches(PolygonPatchArrayPropertyType value) {
        return new JAXBElement<PolygonPatchArrayPropertyType>(_PolygonPatches_QNAME, PolygonPatchArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationParameterGroupRef")
    public JAXBElement<OperationParameterPropertyType> createOperationParameterGroupRef(OperationParameterPropertyType value) {
        return new JAXBElement<OperationParameterPropertyType>(_OperationParameterGroupRef_QNAME, OperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimePeriodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimePeriod", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeGeometricPrimitive")
    public JAXBElement<TimePeriodType> createTimePeriod(TimePeriodType value) {
        return new JAXBElement<TimePeriodType>(_TimePeriod_QNAME, TimePeriodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "engineeringDatumRef")
    public JAXBElement<EngineeringDatumPropertyType> createEngineeringDatumRef(EngineeringDatumPropertyType value) {
        return new JAXBElement<EngineeringDatumPropertyType>(_EngineeringDatumRef_QNAME, EngineeringDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCurve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricPrimitive")
    public JAXBElement<AbstractCurveType> createAbstractCurve(AbstractCurveType value) {
        return new JAXBElement<AbstractCurveType>(_AbstractCurve_QNAME, AbstractCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClothoidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Clothoid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<ClothoidType> createClothoid(ClothoidType value) {
        return new JAXBElement<ClothoidType>(_Clothoid_QNAME, ClothoidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<Object> createAbstractValue(Object value) {
        return new JAXBElement<Object>(_AbstractValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTopologyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTopology", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<AbstractTopologyType> createAbstractTopology(AbstractTopologyType value) {
        return new JAXBElement<AbstractTopologyType>(_AbstractTopology_QNAME, AbstractTopologyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "verticalCRSRef")
    public JAXBElement<VerticalCRSPropertyType> createVerticalCRSRef(VerticalCRSPropertyType value) {
        return new JAXBElement<VerticalCRSPropertyType>(_VerticalCRSRef_QNAME, VerticalCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometricPrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeometricPrimitive", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometry")
    public JAXBElement<AbstractGeometricPrimitiveType> createAbstractGeometricPrimitive(AbstractGeometricPrimitiveType value) {
        return new JAXBElement<AbstractGeometricPrimitiveType>(_AbstractGeometricPrimitive_QNAME, AbstractGeometricPrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "defaultCodeSpace")
    public JAXBElement<String> createDefaultCodeSpace(String value) {
        return new JAXBElement<String>(_DefaultCodeSpace_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCoordinateSystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeCoordinateSystem", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "TimeReferenceSystem")
    public JAXBElement<TimeCoordinateSystemType> createTimeCoordinateSystem(TimeCoordinateSystemType value) {
        return new JAXBElement<TimeCoordinateSystemType>(_TimeCoordinateSystem_QNAME, TimeCoordinateSystemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geometryMember")
    public JAXBElement<GeometryPropertyType> createGeometryMember(GeometryPropertyType value) {
        return new JAXBElement<GeometryPropertyType>(_GeometryMember_QNAME, GeometryPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Null")
    public JAXBElement<List<String>> createNull(List<String> value) {
        return new JAXBElement<List<String>>(_Null_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoCurve")
    public JAXBElement<TopoCurveType> createTopoCurve(TopoCurveType value) {
        return new JAXBElement<TopoCurveType>(_TopoCurve_QNAME, TopoCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProjectedCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "projectedCRSRef")
    public JAXBElement<ProjectedCRSPropertyType> createProjectedCRSRef(ProjectedCRSPropertyType value) {
        return new JAXBElement<ProjectedCRSPropertyType>(_ProjectedCRSRef_QNAME, ProjectedCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiEdgeOf")
    public JAXBElement<MultiCurvePropertyType> createMultiEdgeOf(MultiCurvePropertyType value) {
        return new JAXBElement<MultiCurvePropertyType>(_MultiEdgeOf_QNAME, MultiCurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeNodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeNode", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeTopologyPrimitive")
    public JAXBElement<TimeNodeType> createTimeNode(TimeNodeType value) {
        return new JAXBElement<TimeNodeType>(_TimeNode_QNAME, TimeNodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Curve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurve")
    public JAXBElement<CurveType> createCurve(CurveType value) {
        return new JAXBElement<CurveType>(_Curve_QNAME, CurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "semiMajorAxis")
    public JAXBElement<MeasureType> createSemiMajorAxis(MeasureType value) {
        return new JAXBElement<MeasureType>(_SemiMajorAxis_QNAME, MeasureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurveArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "curveMembers")
    public JAXBElement<CurveArrayPropertyType> createCurveMembers(CurveArrayPropertyType value) {
        return new JAXBElement<CurveArrayPropertyType>(_CurveMembers_QNAME, CurveArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "locationName")
    public JAXBElement<CodeType> createLocationName(CodeType value) {
        return new JAXBElement<CodeType>(_LocationName_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Array", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<ArrayType> createArray(ArrayType value) {
        return new JAXBElement<ArrayType>(_Array_QNAME, ArrayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectPositionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pos")
    public JAXBElement<DirectPositionType> createPos(DirectPositionType value) {
        return new JAXBElement<DirectPositionType>(_Pos_QNAME, DirectPositionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractObject")
    public JAXBElement<Object> createAbstractObject(Object value) {
        return new JAXBElement<Object>(_AbstractObject_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeocentricCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeocentricCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<GeocentricCRSType> createGeocentricCRS(GeocentricCRSType value) {
        return new JAXBElement<GeocentricCRSType>(_GeocentricCRS_QNAME, GeocentricCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGriddedSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGriddedSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractParametricCurveSurface")
    public JAXBElement<AbstractGriddedSurfaceType> createAbstractGriddedSurface(AbstractGriddedSurfaceType value) {
        return new JAXBElement<AbstractGriddedSurfaceType>(_AbstractGriddedSurface_QNAME, AbstractGriddedSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueProperty")
    public JAXBElement<ValuePropertyType> createValueProperty(ValuePropertyType value) {
        return new JAXBElement<ValuePropertyType>(_ValueProperty_QNAME, ValuePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CartesianCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CartesianCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<CartesianCSType> createCartesianCS(CartesianCSType value) {
        return new JAXBElement<CartesianCSType>(_CartesianCS_QNAME, CartesianCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractRingPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "exterior")
    public JAXBElement<AbstractRingPropertyType> createExterior(AbstractRingPropertyType value) {
        return new JAXBElement<AbstractRingPropertyType>(_Exterior_QNAME, AbstractRingPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ellipsoid")
    public JAXBElement<EllipsoidPropertyType> createEllipsoidPropertyElement(EllipsoidPropertyType value) {
        return new JAXBElement<EllipsoidPropertyType>(_EllipsoidPropertyElement_QNAME, EllipsoidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "parameterValue")
    public JAXBElement<AbstractGeneralParameterValuePropertyType> createUsesValue(AbstractGeneralParameterValuePropertyType value) {
        return new JAXBElement<AbstractGeneralParameterValuePropertyType>(_UsesValue_QNAME, AbstractGeneralParameterValuePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericMetaDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GenericMetaData", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractMetaData")
    public JAXBElement<GenericMetaDataType> createGenericMetaData(GenericMetaDataType value) {
        return new JAXBElement<GenericMetaDataType>(_GenericMetaData_QNAME, GenericMetaDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeOrNilReasonListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CategoryList", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValueList")
    public JAXBElement<CodeOrNilReasonListType> createCategoryList(CodeOrNilReasonListType value) {
        return new JAXBElement<CodeOrNilReasonListType>(_CategoryList_QNAME, CodeOrNilReasonListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "includesSingleCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "componentReferenceSystem")
    public JAXBElement<SingleCRSPropertyType> createIncludesSingleCRS(SingleCRSPropertyType value) {
        return new JAXBElement<SingleCRSPropertyType>(_IncludesSingleCRS_QNAME, SingleCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimeTopologyPrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimeTopologyPrimitive", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimePrimitive")
    public JAXBElement<AbstractTimeTopologyPrimitiveType> createAbstractTimeTopologyPrimitive(AbstractTimeTopologyPrimitiveType value) {
        return new JAXBElement<AbstractTimeTopologyPrimitiveType>(_AbstractTimeTopologyPrimitive_QNAME, AbstractTimeTopologyPrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "gridDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<GridDomainType> createGridDomain(GridDomainType value) {
        return new JAXBElement<GridDomainType>(_GridDomain_QNAME, GridDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "doubleOrNilReasonTupleList")
    public JAXBElement<List<String>> createDoubleOrNilReasonTupleList(List<String> value) {
        return new JAXBElement<List<String>>(_DoubleOrNilReasonTupleList_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<MultiSurfaceType> createMultiSurface(MultiSurfaceType value) {
        return new JAXBElement<MultiSurfaceType>(_MultiSurface_QNAME, MultiSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "minimumOccurs")
    public JAXBElement<BigInteger> createMinimumOccurs(BigInteger value) {
        return new JAXBElement<BigInteger>(_MinimumOccurs_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AngleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "angle")
    public JAXBElement<AngleType> createAngle(AngleType value) {
        return new JAXBElement<AngleType>(_Angle_QNAME, AngleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "axisAbbrev")
    public JAXBElement<CodeType> createAxisAbbrev(CodeType value) {
        return new JAXBElement<CodeType>(_AxisAbbrev_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "curveMember")
    public JAXBElement<CurvePropertyType> createCurveMember(CurvePropertyType value) {
        return new JAXBElement<CurvePropertyType>(_CurveMember_QNAME, CurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "decimalMinutes")
    public JAXBElement<BigDecimal> createDecimalMinutes(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DecimalMinutes_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoPrimitiveArrayAssociationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoPrimitiveMembers")
    public JAXBElement<TopoPrimitiveArrayAssociationType> createTopoPrimitiveMembers(TopoPrimitiveArrayAssociationType value) {
        return new JAXBElement<TopoPrimitiveArrayAssociationType>(_TopoPrimitiveMembers_QNAME, TopoPrimitiveArrayAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "includesValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "parameterValue")
    public JAXBElement<AbstractGeneralParameterValuePropertyType> createIncludesValue(AbstractGeneralParameterValuePropertyType value) {
        return new JAXBElement<AbstractGeneralParameterValuePropertyType>(_IncludesValue_QNAME, AbstractGeneralParameterValuePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProjectedCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ProjectedCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralDerivedCRS")
    public JAXBElement<ProjectedCRSType> createProjectedCRS(ProjectedCRSType value) {
        return new JAXBElement<ProjectedCRSType>(_ProjectedCRS_QNAME, ProjectedCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationMethodRef")
    public JAXBElement<OperationMethodPropertyType> createOperationMethodRef(OperationMethodPropertyType value) {
        return new JAXBElement<OperationMethodPropertyType>(_OperationMethodRef_QNAME, OperationMethodPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TinType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Tin", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "TriangulatedSurface")
    public JAXBElement<TinType> createTin(TinType value) {
        return new JAXBElement<TinType>(_Tin_QNAME, TinType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "engineeringCRSRef")
    public JAXBElement<EngineeringCRSPropertyType> createEngineeringCRSRef(EngineeringCRSPropertyType value) {
        return new JAXBElement<EngineeringCRSPropertyType>(_EngineeringCRSRef_QNAME, EngineeringCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoCurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoCurveProperty")
    public JAXBElement<TopoCurvePropertyType> createTopoCurveProperty(TopoCurvePropertyType value) {
        return new JAXBElement<TopoCurvePropertyType>(_TopoCurveProperty_QNAME, TopoCurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Face", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopoPrimitive")
    public JAXBElement<FaceType> createFace(FaceType value) {
        return new JAXBElement<FaceType>(_Face_QNAME, FaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeOrdinalEraType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeOrdinalEra")
    public JAXBElement<TimeOrdinalEraType> createTimeOrdinalEra(TimeOrdinalEraType value) {
        return new JAXBElement<TimeOrdinalEraType>(_TimeOrdinalEra_QNAME, TimeOrdinalEraType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DomainSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "domainSet")
    public JAXBElement<DomainSetType> createDomainSet(DomainSetType value) {
        return new JAXBElement<DomainSetType>(_DomainSet_QNAME, DomainSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "engineeringDatum")
    public JAXBElement<EngineeringDatumPropertyType> createEngineeringDatumPropertyElement(EngineeringDatumPropertyType value) {
        return new JAXBElement<EngineeringDatumPropertyType>(_EngineeringDatumPropertyElement_QNAME, EngineeringDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefinitionProxyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DefinitionProxy", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<DefinitionProxyType> createDefinitionProxy(DefinitionProxyType value) {
        return new JAXBElement<DefinitionProxyType>(_DefinitionProxy_QNAME, DefinitionProxyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "seconds")
    public JAXBElement<BigDecimal> createSeconds(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Seconds_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CubicSplineType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CubicSpline", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<CubicSplineType> createCubicSpline(CubicSplineType value) {
        return new JAXBElement<CubicSplineType>(_CubicSpline_QNAME, CubicSplineType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "resultOf")
    public JAXBElement<ResultType> createResultOf(ResultType value) {
        return new JAXBElement<ResultType>(_ResultOf_QNAME, ResultType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimeSliceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimeSlice", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<AbstractTimeSliceType> createAbstractTimeSlice(AbstractTimeSliceType value) {
        return new JAXBElement<AbstractTimeSliceType>(_AbstractTimeSlice_QNAME, AbstractTimeSliceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DerivedCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DerivedCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralDerivedCRS")
    public JAXBElement<DerivedCRSType> createDerivedCRS(DerivedCRSType value) {
        return new JAXBElement<DerivedCRSType>(_DerivedCRS_QNAME, DerivedCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "conversion")
    public JAXBElement<GeneralConversionPropertyType> createConversionPropertyElement(GeneralConversionPropertyType value) {
        return new JAXBElement<GeneralConversionPropertyType>(_ConversionPropertyElement_QNAME, GeneralConversionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractDiscreteCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractDiscreteCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoverage")
    public JAXBElement<AbstractDiscreteCoverageType> createAbstractDiscreteCoverage(AbstractDiscreteCoverageType value) {
        return new JAXBElement<AbstractDiscreteCoverageType>(_AbstractDiscreteCoverage_QNAME, AbstractDiscreteCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "primeMeridian")
    public JAXBElement<PrimeMeridianPropertyType> createPrimeMeridianPropertyElement(PrimeMeridianPropertyType value) {
        return new JAXBElement<PrimeMeridianPropertyType>(_PrimeMeridianPropertyElement_QNAME, PrimeMeridianPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "centerLineOf")
    public JAXBElement<CurvePropertyType> createCenterLineOf(CurvePropertyType value) {
        return new JAXBElement<CurvePropertyType>(_CenterLineOf_QNAME, CurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AngleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "greenwichLongitude")
    public JAXBElement<AngleType> createGreenwichLongitude(AngleType value) {
        return new JAXBElement<AngleType>(_GreenwichLongitude_QNAME, AngleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "targetCRS")
    public JAXBElement<CRSPropertyType> createTargetCRS(CRSPropertyType value) {
        return new JAXBElement<CRSPropertyType>(_TargetCRS_QNAME, CRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "reversePropertyName")
    public JAXBElement<String> createReversePropertyName(String value) {
        return new JAXBElement<String>(_ReversePropertyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "temporalCRSRef")
    public JAXBElement<TemporalCRSPropertyType> createTemporalCRSRef(TemporalCRSPropertyType value) {
        return new JAXBElement<TemporalCRSPropertyType>(_TemporalCRSRef_QNAME, TemporalCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "componentReferenceSystem")
    public JAXBElement<SingleCRSPropertyType> createComponentReferenceSystem(SingleCRSPropertyType value) {
        return new JAXBElement<SingleCRSPropertyType>(_ComponentReferenceSystem_QNAME, SingleCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<AbstractCRSType> createAbstractCRS(AbstractCRSType value) {
        return new JAXBElement<AbstractCRSType>(_AbstractCRS_QNAME, AbstractCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterGroupType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OperationParameterGroup", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralOperationParameter")
    public JAXBElement<OperationParameterGroupType> createOperationParameterGroup(OperationParameterGroupType value) {
        return new JAXBElement<OperationParameterGroupType>(_OperationParameterGroup_QNAME, OperationParameterGroupType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcStringType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ArcString", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<ArcStringType> createArcString(ArcStringType value) {
        return new JAXBElement<ArcStringType>(_ArcString_QNAME, ArcStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiExtentOf")
    public JAXBElement<MultiSurfacePropertyType> createMultiExtentOf(MultiSurfacePropertyType value) {
        return new JAXBElement<MultiSurfacePropertyType>(_MultiExtentOf_QNAME, MultiSurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObliqueCartesianCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesObliqueCartesianCS")
    public JAXBElement<ObliqueCartesianCSPropertyType> createUsesObliqueCartesianCS(ObliqueCartesianCSPropertyType value) {
        return new JAXBElement<ObliqueCartesianCSPropertyType>(_UsesObliqueCartesianCS_QNAME, ObliqueCartesianCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoPoint")
    public JAXBElement<TopoPointType> createTopoPoint(TopoPointType value) {
        return new JAXBElement<TopoPointType>(_TopoPoint_QNAME, TopoPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationParameter")
    public JAXBElement<OperationParameterPropertyType> createOperationParameterPropertyElement(OperationParameterPropertyType value) {
        return new JAXBElement<OperationParameterPropertyType>(_OperationParameterPropertyElement_QNAME, OperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfaceDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiSurfaceDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<MultiSurfaceDomainType> createMultiSurfaceDomain(MultiSurfaceDomainType value) {
        return new JAXBElement<MultiSurfaceDomainType>(_MultiSurfaceDomain_QNAME, MultiSurfaceDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "methodFormula", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "formula")
    public JAXBElement<CodeType> createMethodFormula(CodeType value) {
        return new JAXBElement<CodeType>(_MethodFormula_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeodeticDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDatum")
    public JAXBElement<GeodeticDatumType> createGeodeticDatum(GeodeticDatumType value) {
        return new JAXBElement<GeodeticDatumType>(_GeodeticDatum_QNAME, GeodeticDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "status")
    public JAXBElement<StringOrRefType> createStatus(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_Status_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinateSystemRef")
    public JAXBElement<CoordinateSystemPropertyType> createCoordinateSystemRef(CoordinateSystemPropertyType value) {
        return new JAXBElement<CoordinateSystemPropertyType>(_CoordinateSystemRef_QNAME, CoordinateSystemPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "quantityTypeReference")
    public JAXBElement<ReferenceType> createQuantityTypeReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_QuantityTypeReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CompositeCurve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurve")
    public JAXBElement<CompositeCurveType> createCompositeCurve(CompositeCurveType value) {
        return new JAXBElement<CompositeCurveType>(_CompositeCurve_QNAME, CompositeCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "imageDatum")
    public JAXBElement<ImageDatumPropertyType> createImageDatumPropertyElement(ImageDatumPropertyType value) {
        return new JAXBElement<ImageDatumPropertyType>(_ImageDatumPropertyElement_QNAME, ImageDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BoundingShapeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "boundedBy")
    public JAXBElement<BoundingShapeType> createBoundedBy(BoundingShapeType value) {
        return new JAXBElement<BoundingShapeType>(_BoundedBy_QNAME, BoundingShapeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesVerticalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "verticalDatum")
    public JAXBElement<VerticalDatumPropertyType> createUsesVerticalDatum(VerticalDatumPropertyType value) {
        return new JAXBElement<VerticalDatumPropertyType>(_UsesVerticalDatum_QNAME, VerticalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "modifiedCoordinate")
    public JAXBElement<BigInteger> createModifiedCoordinate(BigInteger value) {
        return new JAXBElement<BigInteger>(_ModifiedCoordinate_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractSingleCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCRS")
    public JAXBElement<AbstractCRSType> createAbstractSingleCRS(AbstractCRSType value) {
        return new JAXBElement<AbstractCRSType>(_AbstractSingleCRS_QNAME, AbstractCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedObservationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DirectedObservation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Observation")
    public JAXBElement<DirectedObservationType> createDirectedObservation(DirectedObservationType value) {
        return new JAXBElement<DirectedObservationType>(_DirectedObservation_QNAME, DirectedObservationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeWithAuthorityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "rangeMeaning")
    public JAXBElement<CodeWithAuthorityType> createRangeMeaning(CodeWithAuthorityType value) {
        return new JAXBElement<CodeWithAuthorityType>(_RangeMeaning_QNAME, CodeWithAuthorityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPointDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<MultiPointDomainType> createMultiPointDomain(MultiPointDomainType value) {
        return new JAXBElement<MultiPointDomainType>(_MultiPointDomain_QNAME, MultiPointDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConcatenatedOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ConcatenatedOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateOperation")
    public JAXBElement<ConcatenatedOperationType> createConcatenatedOperation(ConcatenatedOperationType value) {
        return new JAXBElement<ConcatenatedOperationType>(_ConcatenatedOperation_QNAME, ConcatenatedOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "generalConversionRef")
    public JAXBElement<GeneralConversionPropertyType> createGeneralConversionRef(GeneralConversionPropertyType value) {
        return new JAXBElement<GeneralConversionPropertyType>(_GeneralConversionRef_QNAME, GeneralConversionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesAffineCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "affineCS")
    public JAXBElement<AffineCSPropertyType> createUsesAffineCS(AffineCSPropertyType value) {
        return new JAXBElement<AffineCSPropertyType>(_UsesAffineCS_QNAME, AffineCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractParametricCurveSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractParametricCurveSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurfacePatch")
    public JAXBElement<AbstractParametricCurveSurfaceType> createAbstractParametricCurveSurface(AbstractParametricCurveSurfaceType value) {
        return new JAXBElement<AbstractParametricCurveSurfaceType>(_AbstractParametricCurveSurface_QNAME, AbstractParametricCurveSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CircleByCenterPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CircleByCenterPoint", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "ArcByCenterPoint")
    public JAXBElement<CircleByCenterPointType> createCircleByCenterPoint(CircleByCenterPointType value) {
        return new JAXBElement<CircleByCenterPointType>(_CircleByCenterPoint_QNAME, CircleByCenterPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SphereType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Sphere", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGriddedSurface")
    public JAXBElement<SphereType> createSphere(SphereType value) {
        return new JAXBElement<SphereType>(_Sphere_QNAME, SphereType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolidArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "solidArrayProperty")
    public JAXBElement<SolidArrayPropertyType> createSolidArrayProperty(SolidArrayPropertyType value) {
        return new JAXBElement<SolidArrayPropertyType>(_SolidArrayProperty_QNAME, SolidArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "cartesianCSRef")
    public JAXBElement<CartesianCSPropertyType> createCartesianCSRef(CartesianCSPropertyType value) {
        return new JAXBElement<CartesianCSPropertyType>(_CartesianCSRef_QNAME, CartesianCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractSurfacePatchType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractSurfacePatch")
    public JAXBElement<AbstractSurfacePatchType> createAbstractSurfacePatch(AbstractSurfacePatchType value) {
        return new JAXBElement<AbstractSurfacePatchType>(_AbstractSurfacePatch_QNAME, AbstractSurfacePatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "imageCRSRef")
    public JAXBElement<ImageCRSPropertyType> createImageCRSRef(ImageCRSPropertyType value) {
        return new JAXBElement<ImageCRSPropertyType>(_ImageCRSRef_QNAME, ImageCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "origin")
    public JAXBElement<XMLGregorianCalendar> createOrigin(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Origin_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointMembers")
    public JAXBElement<PointArrayPropertyType> createPointMembers(PointArrayPropertyType value) {
        return new JAXBElement<PointArrayPropertyType>(_PointMembers_QNAME, PointArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DegreesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "degrees")
    public JAXBElement<DegreesType> createDegrees(DegreesType value) {
        return new JAXBElement<DegreesType>(_Degrees_QNAME, DegreesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Quantity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Quantity", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValue")
    public JAXBElement<Quantity> createQuantity(Quantity value) {
        return new JAXBElement<Quantity>(_Quantity_QNAME, Quantity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "minutes")
    public JAXBElement<Integer> createMinutes(Integer value) {
        return new JAXBElement<Integer>(_Minutes_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "File", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<FileType> createFile(FileType value) {
        return new JAXBElement<FileType>(_File_QNAME, FileType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "imageDatumRef")
    public JAXBElement<ImageDatumPropertyType> createImageDatumRef(ImageDatumPropertyType value) {
        return new JAXBElement<ImageDatumPropertyType>(_ImageDatumRef_QNAME, ImageDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataBlockType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DataBlock", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<DataBlockType> createDataBlock(DataBlockType value) {
        return new JAXBElement<DataBlockType>(_DataBlock_QNAME, DataBlockType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolarCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "PolarCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<PolarCSType> createPolarCS(PolarCSType value) {
        return new JAXBElement<PolarCSType>(_PolarCS_QNAME, PolarCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvelopeWithTimePeriodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "EnvelopeWithTimePeriod", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Envelope")
    public JAXBElement<EnvelopeWithTimePeriodType> createEnvelopeWithTimePeriod(EnvelopeWithTimePeriodType value) {
        return new JAXBElement<EnvelopeWithTimePeriodType>(_EnvelopeWithTimePeriod_QNAME, EnvelopeWithTimePeriodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoVolumeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoVolume")
    public JAXBElement<TopoVolumeType> createTopoVolume(TopoVolumeType value) {
        return new JAXBElement<TopoVolumeType>(_TopoVolume_QNAME, TopoVolumeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcByBulgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ArcByBulge", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "ArcStringByBulge")
    public JAXBElement<ArcByBulgeType> createArcByBulge(ArcByBulgeType value) {
        return new JAXBElement<ArcByBulgeType>(_ArcByBulge_QNAME, ArcByBulgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "target")
    public JAXBElement<TargetPropertyType> createTarget(TargetPropertyType value) {
        return new JAXBElement<TargetPropertyType>(_Target_QNAME, TargetPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralConversionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeneralConversion", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractOperation")
    public JAXBElement<AbstractGeneralConversionType> createAbstractGeneralConversion(AbstractGeneralConversionType value) {
        return new JAXBElement<AbstractGeneralConversionType>(_AbstractGeneralConversion_QNAME, AbstractGeneralConversionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "verticalCSRef")
    public JAXBElement<VerticalCSPropertyType> createVerticalCSRef(VerticalCSPropertyType value) {
        return new JAXBElement<VerticalCSPropertyType>(_VerticalCSRef_QNAME, VerticalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompoundCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "compoundCRSRef")
    public JAXBElement<CompoundCRSPropertyType> createCompoundCRSRef(CompoundCRSPropertyType value) {
        return new JAXBElement<CompoundCRSPropertyType>(_CompoundCRSRef_QNAME, CompoundCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "formula")
    public JAXBElement<CodeType> createFormula(CodeType value) {
        return new JAXBElement<CodeType>(_Formula_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "group")
    public JAXBElement<OperationParameterGroupPropertyType> createGroup(OperationParameterGroupPropertyType value) {
        return new JAXBElement<OperationParameterGroupPropertyType>(_Group_QNAME, OperationParameterGroupPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterGroupPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valuesOfGroup", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "group")
    public JAXBElement<OperationParameterGroupPropertyType> createValuesOfGroup(OperationParameterGroupPropertyType value) {
        return new JAXBElement<OperationParameterGroupPropertyType>(_ValuesOfGroup_QNAME, OperationParameterGroupPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MappingRule")
    public JAXBElement<StringOrRefType> createMappingRule(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_MappingRule_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dataSourceReference")
    public JAXBElement<ReferenceType> createDataSourceReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_DataSourceReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OperationMethod", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<OperationMethodType> createOperationMethod(OperationMethodType value) {
        return new JAXBElement<OperationMethodType>(_OperationMethod_QNAME, OperationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DMSAngleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dmsAngle")
    public JAXBElement<DMSAngleType> createDmsAngle(DMSAngleType value) {
        return new JAXBElement<DMSAngleType>(_DmsAngle_QNAME, DMSAngleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralConversionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "definedByConversion", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "conversion")
    public JAXBElement<GeneralConversionPropertyType> createDefinedByConversion(GeneralConversionPropertyType value) {
        return new JAXBElement<GeneralConversionPropertyType>(_DefinedByConversion_QNAME, GeneralConversionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Count }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Count", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValue")
    public JAXBElement<Count> createCount(Count value) {
        return new JAXBElement<Count>(_Count_QNAME, Count.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "direction")
    public JAXBElement<DirectionPropertyType> createDirection(DirectionPropertyType value) {
        return new JAXBElement<DirectionPropertyType>(_Direction_QNAME, DirectionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "timeCS")
    public JAXBElement<TimeCSPropertyType> createTimeCSPropertyElement(TimeCSPropertyType value) {
        return new JAXBElement<TimeCSPropertyType>(_TimeCSPropertyElement_QNAME, TimeCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedNodePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "directedNode")
    public JAXBElement<DirectedNodePropertyType> createDirectedNode(DirectedNodePropertyType value) {
        return new JAXBElement<DirectedNodePropertyType>(_DirectedNode_QNAME, DirectedNodePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoPrimitiveMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoPrimitiveMember")
    public JAXBElement<TopoPrimitiveMemberType> createTopoPrimitiveMember(TopoPrimitiveMemberType value) {
        return new JAXBElement<TopoPrimitiveMemberType>(_TopoPrimitiveMember_QNAME, TopoPrimitiveMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractScalarValueList", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<Object> createAbstractScalarValueList(Object value) {
        return new JAXBElement<Object>(_AbstractScalarValueList_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompoundCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CompoundCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCRS")
    public JAXBElement<CompoundCRSType> createCompoundCRS(CompoundCRSType value) {
        return new JAXBElement<CompoundCRSType>(_CompoundCRS_QNAME, CompoundCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfaceCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiSurfaceCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<MultiSurfaceCoverageType> createMultiSurfaceCoverage(MultiSurfaceCoverageType value) {
        return new JAXBElement<MultiSurfaceCoverageType>(_MultiSurfaceCoverage_QNAME, MultiSurfaceCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "abstractReference")
    public JAXBElement<ReferenceType> createAbstractReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_AbstractReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiPoint", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<MultiPointType> createMultiPoint(MultiPointType value) {
        return new JAXBElement<MultiPointType>(_MultiPoint_QNAME, MultiPointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "targetElement")
    public JAXBElement<String> createTargetElement(String value) {
        return new JAXBElement<String>(_TargetElement_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geodeticDatumRef")
    public JAXBElement<GeodeticDatumPropertyType> createGeodeticDatumRef(GeodeticDatumPropertyType value) {
        return new JAXBElement<GeodeticDatumPropertyType>(_GeodeticDatumRef_QNAME, GeodeticDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VectorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "vector")
    public JAXBElement<VectorType> createVector(VectorType value) {
        return new JAXBElement<VectorType>(_Vector_QNAME, VectorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeWithAuthorityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "axisDirection")
    public JAXBElement<CodeWithAuthorityType> createAxisDirection(CodeWithAuthorityType value) {
        return new JAXBElement<CodeWithAuthorityType>(_AxisDirection_QNAME, CodeWithAuthorityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MovingObjectStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MovingObjectStatus", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeSlice")
    public JAXBElement<MovingObjectStatusType> createMovingObjectStatus(MovingObjectStatusType value) {
        return new JAXBElement<MovingObjectStatusType>(_MovingObjectStatus_QNAME, MovingObjectStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCenterLineOf")
    public JAXBElement<MultiCurvePropertyType> createMultiCenterLineOf(MultiCurvePropertyType value) {
        return new JAXBElement<MultiCurvePropertyType>(_MultiCenterLineOf_QNAME, MultiCurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Ellipsoid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<EllipsoidType> createEllipsoid(EllipsoidType value) {
        return new JAXBElement<EllipsoidType>(_Ellipsoid_QNAME, EllipsoidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiPointCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<MultiPointCoverageType> createMultiPointCoverage(MultiPointCoverageType value) {
        return new JAXBElement<MultiPointCoverageType>(_MultiPointCoverage_QNAME, MultiPointCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DynamicFeatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DynamicFeature", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<DynamicFeatureType> createDynamicFeature(DynamicFeatureType value) {
        return new JAXBElement<DynamicFeatureType>(_DynamicFeature_QNAME, DynamicFeatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedTopoSolidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "directedTopoSolid")
    public JAXBElement<DirectedTopoSolidPropertyType> createDirectedTopoSolid(DirectedTopoSolidPropertyType value) {
        return new JAXBElement<DirectedTopoSolidPropertyType>(_DirectedTopoSolid_QNAME, DirectedTopoSolidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeographicCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "baseGeographicCRS")
    public JAXBElement<GeographicCRSPropertyType> createBaseGeographicCRS(GeographicCRSPropertyType value) {
        return new JAXBElement<GeographicCRSPropertyType>(_BaseGeographicCRS_QNAME, GeographicCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesTemporalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "temporalDatum")
    public JAXBElement<TemporalDatumPropertyType> createUsesTemporalDatum(TemporalDatumPropertyType value) {
        return new JAXBElement<TemporalDatumPropertyType>(_UsesTemporalDatum_QNAME, TemporalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TemporalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<TemporalCSType> createTemporalCS(TemporalCSType value) {
        return new JAXBElement<TemporalCSType>(_TemporalCS_QNAME, TemporalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiPointProperty")
    public JAXBElement<MultiPointPropertyType> createMultiPointProperty(MultiPointPropertyType value) {
        return new JAXBElement<MultiPointPropertyType>(_MultiPointProperty_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcStringByBulgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ArcStringByBulge", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<ArcStringByBulgeType> createArcStringByBulge(ArcStringByBulgeType value) {
        return new JAXBElement<ArcStringByBulgeType>(_ArcStringByBulge_QNAME, ArcStringByBulgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "transformationRef")
    public JAXBElement<TransformationPropertyType> createTransformationRef(TransformationPropertyType value) {
        return new JAXBElement<TransformationPropertyType>(_TransformationRef_QNAME, TransformationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TemporalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDatum")
    public JAXBElement<TemporalDatumType> createTemporalDatum(TemporalDatumType value) {
        return new JAXBElement<TemporalDatumType>(_TemporalDatum_QNAME, TemporalDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContainerPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "container")
    public JAXBElement<ContainerPropertyType> createContainer(ContainerPropertyType value) {
        return new JAXBElement<ContainerPropertyType>(_Container_QNAME, ContainerPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeWithAuthorityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pixelInCell")
    public JAXBElement<CodeWithAuthorityType> createPixelInCell(CodeWithAuthorityType value) {
        return new JAXBElement<CodeWithAuthorityType>(_PixelInCell_QNAME, CodeWithAuthorityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeEdgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeEdge", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeTopologyPrimitive")
    public JAXBElement<TimeEdgeType> createTimeEdge(TimeEdgeType value) {
        return new JAXBElement<TimeEdgeType>(_TimeEdge_QNAME, TimeEdgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CompositeValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<CompositeValueType> createCompositeValue(CompositeValueType value) {
        return new JAXBElement<CompositeValueType>(_CompositeValue_QNAME, CompositeValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ImageDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDatum")
    public JAXBElement<ImageDatumType> createImageDatum(ImageDatumType value) {
        return new JAXBElement<ImageDatumType>(_ImageDatum_QNAME, ImageDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeReferenceSystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeReferenceSystem", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<TimeReferenceSystemType> createTimeReferenceSystem(TimeReferenceSystemType value) {
        return new JAXBElement<TimeReferenceSystemType>(_TimeReferenceSystem_QNAME, TimeReferenceSystemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "EngineeringDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDatum")
    public JAXBElement<EngineeringDatumType> createEngineeringDatum(EngineeringDatumType value) {
        return new JAXBElement<EngineeringDatumType>(_EngineeringDatum_QNAME, EngineeringDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractMetaDataType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractMetaData", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AbstractMetaDataType> createAbstractMetaData(AbstractMetaDataType value) {
        return new JAXBElement<AbstractMetaDataType>(_AbstractMetaData_QNAME, AbstractMetaDataType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractRingType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractRing", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AbstractRingType> createAbstractRing(AbstractRingType value) {
        return new JAXBElement<AbstractRingType>(_AbstractRing_QNAME, AbstractRingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimePositionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "timePosition")
    public JAXBElement<TimePositionType> createTimePosition(TimePositionType value) {
        return new JAXBElement<TimePositionType>(_TimePosition_QNAME, TimePositionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationVersion")
    public JAXBElement<String> createOperationVersion(String value) {
        return new JAXBElement<String>(_OperationVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "integerValue")
    public JAXBElement<BigInteger> createIntegerValue(BigInteger value) {
        return new JAXBElement<BigInteger>(_IntegerValue_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AffineCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AffineCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<AffineCSType> createAffineCS(AffineCSType value) {
        return new JAXBElement<AffineCSType>(_AffineCS_QNAME, AffineCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiSurfaceProperty")
    public JAXBElement<MultiSurfacePropertyType> createMultiSurfaceProperty(MultiSurfacePropertyType value) {
        return new JAXBElement<MultiSurfacePropertyType>(_MultiSurfaceProperty_QNAME, MultiSurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesEllipsoid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "ellipsoid")
    public JAXBElement<EllipsoidPropertyType> createUsesEllipsoid(EllipsoidPropertyType value) {
        return new JAXBElement<EllipsoidPropertyType>(_UsesEllipsoid_QNAME, EllipsoidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "locationReference")
    public JAXBElement<ReferenceType> createLocationReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_LocationReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryEntryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "definitionMember", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "dictionaryEntry")
    public JAXBElement<DictionaryEntryType> createDefinitionMember(DictionaryEntryType value) {
        return new JAXBElement<DictionaryEntryType>(_DefinitionMember_QNAME, DictionaryEntryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfaceArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "surfaceArrayProperty")
    public JAXBElement<SurfaceArrayPropertyType> createSurfaceArrayProperty(SurfaceArrayPropertyType value) {
        return new JAXBElement<SurfaceArrayPropertyType>(_SurfaceArrayProperty_QNAME, SurfaceArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoSurface")
    public JAXBElement<TopoSurfaceType> createTopoSurface(TopoSurfaceType value) {
        return new JAXBElement<TopoSurfaceType>(_TopoSurface_QNAME, TopoSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ellipsoidalCSRef")
    public JAXBElement<EllipsoidalCSPropertyType> createEllipsoidalCSRef(EllipsoidalCSPropertyType value) {
        return new JAXBElement<EllipsoidalCSPropertyType>(_EllipsoidalCSRef_QNAME, EllipsoidalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesImageDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "imageDatum")
    public JAXBElement<ImageDatumPropertyType> createUsesImageDatum(ImageDatumPropertyType value) {
        return new JAXBElement<ImageDatumPropertyType>(_UsesImageDatum_QNAME, ImageDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Cone", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGriddedSurface")
    public JAXBElement<ConeType> createCone(ConeType value) {
        return new JAXBElement<ConeType>(_Cone_QNAME, ConeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoSurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoSurfaceProperty")
    public JAXBElement<TopoSurfacePropertyType> createTopoSurfaceProperty(TopoSurfacePropertyType value) {
        return new JAXBElement<TopoSurfacePropertyType>(_TopoSurfaceProperty_QNAME, TopoSurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObservationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Observation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<ObservationType> createObservation(ObservationType value) {
        return new JAXBElement<ObservationType>(_Observation_QNAME, ObservationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "temporalDatum")
    public JAXBElement<TemporalDatumPropertyType> createTemporalDatumPropertyElement(TemporalDatumPropertyType value) {
        return new JAXBElement<TemporalDatumPropertyType>(_TemporalDatumPropertyElement_QNAME, TemporalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LineString", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurve")
    public JAXBElement<LineStringType> createLineString(LineStringType value) {
        return new JAXBElement<LineStringType>(_LineString_QNAME, LineStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeOrdinalReferenceSystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeOrdinalReferenceSystem", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "TimeReferenceSystem")
    public JAXBElement<TimeOrdinalReferenceSystemType> createTimeOrdinalReferenceSystem(TimeOrdinalReferenceSystemType value) {
        return new JAXBElement<TimeOrdinalReferenceSystemType>(_TimeOrdinalReferenceSystem_QNAME, TimeOrdinalReferenceSystemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BaseUnitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "BaseUnit", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "UnitDefinition")
    public JAXBElement<BaseUnitType> createBaseUnit(BaseUnitType value) {
        return new JAXBElement<BaseUnitType>(_BaseUnit_QNAME, BaseUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesGeodeticDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "geodeticDatum")
    public JAXBElement<GeodeticDatumPropertyType> createUsesGeodeticDatum(GeodeticDatumPropertyType value) {
        return new JAXBElement<GeodeticDatumPropertyType>(_UsesGeodeticDatum_QNAME, GeodeticDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValuePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "parameterValue")
    public JAXBElement<AbstractGeneralParameterValuePropertyType> createParameterValuePropertyElement(AbstractGeneralParameterValuePropertyType value) {
        return new JAXBElement<AbstractGeneralParameterValuePropertyType>(_ParameterValuePropertyElement_QNAME, AbstractGeneralParameterValuePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeSolidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CompositeSolid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSolid")
    public JAXBElement<CompositeSolidType> createCompositeSolid(CompositeSolidType value) {
        return new JAXBElement<CompositeSolidType>(_CompositeSolid_QNAME, CompositeSolidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnvelopeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Envelope", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<EnvelopeType> createEnvelope(EnvelopeType value) {
        return new JAXBElement<EnvelopeType>(_Envelope_QNAME, EnvelopeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesTemporalCS")
    public JAXBElement<TemporalCSPropertyType> createUsesTemporalCS(TemporalCSPropertyType value) {
        return new JAXBElement<TemporalCSPropertyType>(_UsesTemporalCS_QNAME, TemporalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricPrimitive")
    public JAXBElement<AbstractSurfaceType> createAbstractSurface(AbstractSurfaceType value) {
        return new JAXBElement<AbstractSurfaceType>(_AbstractSurface_QNAME, AbstractSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "featureMembers")
    public JAXBElement<FeatureArrayPropertyType> createFeatureMembers(FeatureArrayPropertyType value) {
        return new JAXBElement<FeatureArrayPropertyType>(_FeatureMembers_QNAME, FeatureArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DerivedCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "derivedCRSRef")
    public JAXBElement<DerivedCRSPropertyType> createDerivedCRSRef(DerivedCRSPropertyType value) {
        return new JAXBElement<DerivedCRSPropertyType>(_DerivedCRSRef_QNAME, DerivedCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "baseSurface")
    public JAXBElement<SurfacePropertyType> createBaseSurface(SurfacePropertyType value) {
        return new JAXBElement<SurfacePropertyType>(_BaseSurface_QNAME, SurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "primeMeridianRef")
    public JAXBElement<PrimeMeridianPropertyType> createPrimeMeridianRef(PrimeMeridianPropertyType value) {
        return new JAXBElement<PrimeMeridianPropertyType>(_PrimeMeridianRef_QNAME, PrimeMeridianPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointMember")
    public JAXBElement<PointPropertyType> createPointMember(PointPropertyType value) {
        return new JAXBElement<PointPropertyType>(_PointMember_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDefinedCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "userDefinedCSRef")
    public JAXBElement<UserDefinedCSPropertyType> createUserDefinedCSRef(UserDefinedCSPropertyType value) {
        return new JAXBElement<UserDefinedCSPropertyType>(_UserDefinedCSRef_QNAME, UserDefinedCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "includesParameter")
    public JAXBElement<AbstractGeneralOperationParameterPropertyType> createIncludesParameter(AbstractGeneralOperationParameterPropertyType value) {
        return new JAXBElement<AbstractGeneralOperationParameterPropertyType>(_IncludesParameter_QNAME, AbstractGeneralOperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "position")
    public JAXBElement<PointPropertyType> createPosition(PointPropertyType value) {
        return new JAXBElement<PointPropertyType>(_Position_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueArrayType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ValueArray", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "CompositeValue")
    public JAXBElement<ValueArrayType> createValueArray(ValueArrayType value) {
        return new JAXBElement<ValueArrayType>(_ValueArray_QNAME, ValueArrayType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDefinedCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "UserDefinedCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<UserDefinedCSType> createUserDefinedCS(UserDefinedCSType value) {
        return new JAXBElement<UserDefinedCSType>(_UserDefinedCS_QNAME, UserDefinedCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CylindricalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "cylindricalCSRef")
    public JAXBElement<CylindricalCSPropertyType> createCylindricalCSRef(CylindricalCSPropertyType value) {
        return new JAXBElement<CylindricalCSPropertyType>(_CylindricalCSRef_QNAME, CylindricalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesSphericalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "sphericalCS")
    public JAXBElement<SphericalCSPropertyType> createUsesSphericalCS(SphericalCSPropertyType value) {
        return new JAXBElement<SphericalCSPropertyType>(_UsesSphericalCS_QNAME, SphericalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "descriptionReference")
    public JAXBElement<ReferenceType> createDescriptionReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_DescriptionReference_QNAME, ReferenceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Dictionary", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<DictionaryType> createDictionary(DictionaryType value) {
        return new JAXBElement<DictionaryType>(_Dictionary_QNAME, DictionaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "baseCurve")
    public JAXBElement<CurvePropertyType> createBaseCurve(CurvePropertyType value) {
        return new JAXBElement<CurvePropertyType>(_BaseCurve_QNAME, CurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "baseGeodeticCRS")
    public JAXBElement<GeodeticCRSPropertyType> createBaseGeodeticCRS(GeodeticCRSPropertyType value) {
        return new JAXBElement<GeodeticCRSPropertyType>(_BaseGeodeticCRS_QNAME, GeodeticCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneralTransformationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "generalTransformationRef")
    public JAXBElement<GeneralTransformationPropertyType> createGeneralTransformationRef(GeneralTransformationPropertyType value) {
        return new JAXBElement<GeneralTransformationPropertyType>(_GeneralTransformationRef_QNAME, GeneralTransformationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geodeticDatum")
    public JAXBElement<GeodeticDatumPropertyType> createGeodeticDatumPropertyElement(GeodeticDatumPropertyType value) {
        return new JAXBElement<GeodeticDatumPropertyType>(_GeodeticDatumPropertyElement_QNAME, GeodeticDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralDerivedCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeneralDerivedCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<AbstractGeneralDerivedCRSType> createAbstractGeneralDerivedCRS(AbstractGeneralDerivedCRSType value) {
        return new JAXBElement<AbstractGeneralDerivedCRSType>(_AbstractGeneralDerivedCRS_QNAME, AbstractGeneralDerivedCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnitDefinitionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "UnitDefinition", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<UnitDefinitionType> createUnitDefinition(UnitDefinitionType value) {
        return new JAXBElement<UnitDefinitionType>(_UnitDefinition_QNAME, UnitDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoverageFunctionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coverageFunction", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<CoverageFunctionType> createCoverageFunction(CoverageFunctionType value) {
        return new JAXBElement<CoverageFunctionType>(_CoverageFunction_QNAME, CoverageFunctionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoComplexMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "superComplex")
    public JAXBElement<TopoComplexMemberType> createSuperComplex(TopoComplexMemberType value) {
        return new JAXBElement<TopoComplexMemberType>(_SuperComplex_QNAME, TopoComplexMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSolidCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiSolidCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<MultiSolidCoverageType> createMultiSolidCoverage(MultiSolidCoverageType value) {
        return new JAXBElement<MultiSolidCoverageType>(_MultiSolidCoverage_QNAME, MultiSolidCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PassThroughOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "passThroughOperationRef")
    public JAXBElement<PassThroughOperationPropertyType> createPassThroughOperationRef(PassThroughOperationPropertyType value) {
        return new JAXBElement<PassThroughOperationPropertyType>(_PassThroughOperationRef_QNAME, PassThroughOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GridCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<GridCoverageType> createGridCoverage(GridCoverageType value) {
        return new JAXBElement<GridCoverageType>(_GridCoverage_QNAME, GridCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimePrimitivePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "validTime")
    public JAXBElement<TimePrimitivePropertyType> createValidTime(TimePrimitivePropertyType value) {
        return new JAXBElement<TimePrimitivePropertyType>(_ValidTime_QNAME, TimePrimitivePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "solidProperty")
    public JAXBElement<SolidPropertyType> createSolidProperty(SolidPropertyType value) {
        return new JAXBElement<SolidPropertyType>(_SolidProperty_QNAME, SolidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinateSystemAxisRef")
    public JAXBElement<CoordinateSystemAxisPropertyType> createCoordinateSystemAxisRef(CoordinateSystemAxisPropertyType value) {
        return new JAXBElement<CoordinateSystemAxisPropertyType>(_CoordinateSystemAxisRef_QNAME, CoordinateSystemAxisPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationRef")
    public JAXBElement<OperationPropertyType> createOperationRef(OperationPropertyType value) {
        return new JAXBElement<OperationPropertyType>(_OperationRef_QNAME, OperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TriangulatedSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TriangulatedSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Surface")
    public JAXBElement<TriangulatedSurfaceType> createTriangulatedSurface(TriangulatedSurfaceType value) {
        return new JAXBElement<TriangulatedSurfaceType>(_TriangulatedSurface_QNAME, TriangulatedSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "stringValue")
    public JAXBElement<String> createStringValue(String value) {
        return new JAXBElement<String>(_StringValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<AbstractDatumType> createAbstractDatum(AbstractDatumType value) {
        return new JAXBElement<AbstractDatumType>(_AbstractDatum_QNAME, AbstractDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuantityExtentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "QuantityExtent", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<QuantityExtentType> createQuantityExtent(QuantityExtentType value) {
        return new JAXBElement<QuantityExtentType>(_QuantityExtent_QNAME, QuantityExtentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LineStringSegmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LineStringSegment", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<LineStringSegmentType> createLineStringSegment(LineStringSegmentType value) {
        return new JAXBElement<LineStringSegmentType>(_LineStringSegment_QNAME, LineStringSegmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeneralOperationParameter", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<AbstractGeneralOperationParameterType> createAbstractGeneralOperationParameter(AbstractGeneralOperationParameterType value) {
        return new JAXBElement<AbstractGeneralOperationParameterType>(_AbstractGeneralOperationParameter_QNAME, AbstractGeneralOperationParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompositeSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CompositeSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurface")
    public JAXBElement<CompositeSurfaceType> createCompositeSurface(CompositeSurfaceType value) {
        return new JAXBElement<CompositeSurfaceType>(_CompositeSurface_QNAME, CompositeSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCoordinateSystemType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCoordinateSystem", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<AbstractCoordinateSystemType> createAbstractCoordinateSystem(AbstractCoordinateSystemType value) {
        return new JAXBElement<AbstractCoordinateSystemType>(_AbstractCoordinateSystem_QNAME, AbstractCoordinateSystemType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "baseCRS")
    public JAXBElement<SingleCRSPropertyType> createBaseCRS(SingleCRSPropertyType value) {
        return new JAXBElement<SingleCRSPropertyType>(_BaseCRS_QNAME, SingleCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimeMeridianType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "PrimeMeridian", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<PrimeMeridianType> createPrimeMeridian(PrimeMeridianType value) {
        return new JAXBElement<PrimeMeridianType>(_PrimeMeridian_QNAME, PrimeMeridianType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolidArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "solidMembers")
    public JAXBElement<SolidArrayPropertyType> createSolidMembers(SolidArrayPropertyType value) {
        return new JAXBElement<SolidArrayPropertyType>(_SolidMembers_QNAME, SolidArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CircleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Circle", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Arc")
    public JAXBElement<CircleType> createCircle(CircleType value) {
        return new JAXBElement<CircleType>(_Circle_QNAME, CircleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "curveProperty")
    public JAXBElement<CurvePropertyType> createCurveProperty(CurvePropertyType value) {
        return new JAXBElement<CurvePropertyType>(_CurveProperty_QNAME, CurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConventionalUnitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ConventionalUnit", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "UnitDefinition")
    public JAXBElement<ConventionalUnitType> createConventionalUnit(ConventionalUnitType value) {
        return new JAXBElement<ConventionalUnitType>(_ConventionalUnit_QNAME, ConventionalUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometricComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeometricComplex", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometry")
    public JAXBElement<GeometricComplexType> createGeometricComplex(GeometricComplexType value) {
        return new JAXBElement<GeometricComplexType>(_GeometricComplex_QNAME, GeometricComplexType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "description")
    public JAXBElement<StringOrRefType> createDescription(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_Description_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesVerticalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "verticalCS")
    public JAXBElement<VerticalCSPropertyType> createUsesVerticalCS(VerticalCSPropertyType value) {
        return new JAXBElement<VerticalCSPropertyType>(_UsesVerticalCS_QNAME, VerticalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfaceArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "surfaceMembers")
    public JAXBElement<SurfaceArrayPropertyType> createSurfaceMembers(SurfaceArrayPropertyType value) {
        return new JAXBElement<SurfaceArrayPropertyType>(_SurfaceMembers_QNAME, SurfaceArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "VerticalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<VerticalCSType> createVerticalCS(VerticalCSType value) {
        return new JAXBElement<VerticalCSType>(_VerticalCS_QNAME, VerticalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonPatchType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "PolygonPatch", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurfacePatch")
    public JAXBElement<PolygonPatchType> createPolygonPatch(PolygonPatchType value) {
        return new JAXBElement<PolygonPatchType>(_PolygonPatch_QNAME, PolygonPatchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCoordinateOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCoordinateOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<AbstractCoordinateOperationType> createAbstractCoordinateOperation(AbstractCoordinateOperationType value) {
        return new JAXBElement<AbstractCoordinateOperationType>(_AbstractCoordinateOperation_QNAME, AbstractCoordinateOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractSolidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractSolid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricPrimitive")
    public JAXBElement<AbstractSolidType> createAbstractSolid(AbstractSolidType value) {
        return new JAXBElement<AbstractSolidType>(_AbstractSolid_QNAME, AbstractSolidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AffinePlacementType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AffinePlacement", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AffinePlacementType> createAffinePlacement(AffinePlacementType value) {
        return new JAXBElement<AffinePlacementType>(_AffinePlacement_QNAME, AffinePlacementType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.geosdi.geoplatform.xml.gml.v320.Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Boolean", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValue")
    public JAXBElement<org.geosdi.geoplatform.xml.gml.v320.Boolean> createBoolean(org.geosdi.geoplatform.xml.gml.v320.Boolean value) {
        return new JAXBElement<org.geosdi.geoplatform.xml.gml.v320.Boolean>(_Boolean_QNAME, org.geosdi.geoplatform.xml.gml.v320.Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "affineCS")
    public JAXBElement<AffineCSPropertyType> createAffineCSPropertyElement(AffineCSPropertyType value) {
        return new JAXBElement<AffineCSPropertyType>(_AffineCSPropertyElement_QNAME, AffineCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCurveSegmentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCurveSegment", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AbstractCurveSegmentType> createAbstractCurveSegment(AbstractCurveSegmentType value) {
        return new JAXBElement<AbstractCurveSegmentType>(_AbstractCurveSegment_QNAME, AbstractCurveSegmentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "axis")
    public JAXBElement<CoordinateSystemAxisPropertyType> createAxis(CoordinateSystemAxisPropertyType value) {
        return new JAXBElement<CoordinateSystemAxisPropertyType>(_Axis_QNAME, CoordinateSystemAxisPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "surfaceProperty")
    public JAXBElement<SurfacePropertyType> createSurfaceProperty(SurfacePropertyType value) {
        return new JAXBElement<SurfacePropertyType>(_SurfaceProperty_QNAME, SurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConcatenatedOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "concatenatedOperationRef")
    public JAXBElement<ConcatenatedOperationPropertyType> createConcatenatedOperationRef(ConcatenatedOperationPropertyType value) {
        return new JAXBElement<ConcatenatedOperationPropertyType>(_ConcatenatedOperationRef_QNAME, ConcatenatedOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "realizationEpoch")
    public JAXBElement<XMLGregorianCalendar> createRealizationEpoch(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_RealizationEpoch_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureOrNilReasonListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "QuantityList", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValueList")
    public JAXBElement<MeasureOrNilReasonListType> createQuantityList(MeasureOrNilReasonListType value) {
        return new JAXBElement<MeasureOrNilReasonListType>(_QuantityList_QNAME, MeasureOrNilReasonListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ellipsoidalCS")
    public JAXBElement<EllipsoidalCSPropertyType> createEllipsoidalCSPropertyElement(EllipsoidalCSPropertyType value) {
        return new JAXBElement<EllipsoidalCSPropertyType>(_EllipsoidalCSPropertyElement_QNAME, EllipsoidalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ParameterValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralParameterValue")
    public JAXBElement<ParameterValueType> createParameterValue(ParameterValueType value) {
        return new JAXBElement<ParameterValueType>(_ParameterValue_QNAME, ParameterValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "temporalDatumRef")
    public JAXBElement<TemporalDatumPropertyType> createTemporalDatumRef(TemporalDatumPropertyType value) {
        return new JAXBElement<TemporalDatumPropertyType>(_TemporalDatumRef_QNAME, TemporalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CountList", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValueList")
    public JAXBElement<List<String>> createCountList(List<String> value) {
        return new JAXBElement<List<String>>(_CountList_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "remarks")
    public JAXBElement<String> createRemarks(String value) {
        return new JAXBElement<String>(_Remarks_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TemporalCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<TemporalCRSType> createTemporalCRS(TemporalCRSType value) {
        return new JAXBElement<TemporalCRSType>(_TemporalCRS_QNAME, TemporalCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryExtentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CategoryExtent", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<CategoryExtentType> createCategoryExtent(CategoryExtentType value) {
        return new JAXBElement<CategoryExtentType>(_CategoryExtent_QNAME, CategoryExtentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RangeParametersType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "rangeParameters")
    public JAXBElement<RangeParametersType> createRangeParameters(RangeParametersType value) {
        return new JAXBElement<RangeParametersType>(_RangeParameters_QNAME, RangeParametersType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "measure")
    public JAXBElement<MeasureType> createMeasure(MeasureType value) {
        return new JAXBElement<MeasureType>(_Measure_QNAME, MeasureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayAssociationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "members")
    public JAXBElement<ArrayAssociationType> createMembers(ArrayAssociationType value) {
        return new JAXBElement<ArrayAssociationType>(_Members_QNAME, ArrayAssociationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RangeSetType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "rangeSet")
    public JAXBElement<RangeSetType> createRangeSet(RangeSetType value) {
        return new JAXBElement<RangeSetType>(_RangeSet_QNAME, RangeSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolyhedralSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "PolyhedralSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Surface")
    public JAXBElement<PolyhedralSurfaceType> createPolyhedralSurface(PolyhedralSurfaceType value) {
        return new JAXBElement<PolyhedralSurfaceType>(_PolyhedralSurface_QNAME, PolyhedralSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RectifiedGridDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "rectifiedGridDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<RectifiedGridDomainType> createRectifiedGridDomain(RectifiedGridDomainType value) {
        return new JAXBElement<RectifiedGridDomainType>(_RectifiedGridDomain_QNAME, RectifiedGridDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "name")
    public JAXBElement<CodeType> createName(CodeType value) {
        return new JAXBElement<CodeType>(_Name_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EdgeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Edge", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopoPrimitive")
    public JAXBElement<EdgeType> createEdge(EdgeType value) {
        return new JAXBElement<EdgeType>(_Edge_QNAME, EdgeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "conversionRef")
    public JAXBElement<ConversionPropertyType> createConversionRef(ConversionPropertyType value) {
        return new JAXBElement<ConversionPropertyType>(_ConversionRef_QNAME, ConversionPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InlinePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "abstractInlineProperty")
    public JAXBElement<InlinePropertyType> createAbstractInlineProperty(InlinePropertyType value) {
        return new JAXBElement<InlinePropertyType>(_AbstractInlineProperty_QNAME, InlinePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "generalOperationParameter")
    public JAXBElement<AbstractGeneralOperationParameterPropertyType> createGeneralOperationParameter(AbstractGeneralOperationParameterPropertyType value) {
        return new JAXBElement<AbstractGeneralOperationParameterPropertyType>(_GeneralOperationParameter_QNAME, AbstractGeneralOperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValuePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueComponent")
    public JAXBElement<ValuePropertyType> createValueComponent(ValuePropertyType value) {
        return new JAXBElement<ValuePropertyType>(_ValueComponent_QNAME, ValuePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsolatedPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "isolated")
    public JAXBElement<IsolatedPropertyType> createIsolated(IsolatedPropertyType value) {
        return new JAXBElement<IsolatedPropertyType>(_Isolated_QNAME, IsolatedPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionToPreferredUnitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "roughConversionToPreferredUnit")
    public JAXBElement<ConversionToPreferredUnitType> createRoughConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        return new JAXBElement<ConversionToPreferredUnitType>(_RoughConversionToPreferredUnit_QNAME, ConversionToPreferredUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "datumRef")
    public JAXBElement<DatumPropertyType> createDatumRef(DatumPropertyType value) {
        return new JAXBElement<DatumPropertyType>(_DatumRef_QNAME, DatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PassThroughOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "PassThroughOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleOperation")
    public JAXBElement<PassThroughOperationType> createPassThroughOperation(PassThroughOperationType value) {
        return new JAXBElement<PassThroughOperationType>(_PassThroughOperation_QNAME, PassThroughOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalDatumType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "VerticalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDatum")
    public JAXBElement<VerticalDatumType> createVerticalDatum(VerticalDatumType value) {
        return new JAXBElement<VerticalDatumType>(_VerticalDatum_QNAME, VerticalDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ellipsoidRef")
    public JAXBElement<EllipsoidPropertyType> createEllipsoidRef(EllipsoidPropertyType value) {
        return new JAXBElement<EllipsoidPropertyType>(_EllipsoidRef_QNAME, EllipsoidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<TimeCSType> createTimeCS(TimeCSType value) {
        return new JAXBElement<TimeCSType>(_TimeCS_QNAME, TimeCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointProperty")
    public JAXBElement<PointPropertyType> createPointProperty(PointPropertyType value) {
        return new JAXBElement<PointPropertyType>(_PointProperty_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueFile")
    public JAXBElement<String> createValueFile(String value) {
        return new JAXBElement<String>(_ValueFile_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralParameterValueType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeneralParameterValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<AbstractGeneralParameterValueType> createAbstractGeneralParameterValue(AbstractGeneralParameterValueType value) {
        return new JAXBElement<AbstractGeneralParameterValueType>(_AbstractGeneralParameterValue_QNAME, AbstractGeneralParameterValueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolarCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "polarCSRef")
    public JAXBElement<PolarCSPropertyType> createPolarCSRef(PolarCSPropertyType value) {
        return new JAXBElement<PolarCSPropertyType>(_PolarCSRef_QNAME, PolarCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Node", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopoPrimitive")
    public JAXBElement<NodeType> createNode(NodeType value) {
        return new JAXBElement<NodeType>(_Node_QNAME, NodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeographicCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeographicCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<GeographicCRSType> createGeographicCRS(GeographicCRSType value) {
        return new JAXBElement<GeographicCRSType>(_GeographicCRS_QNAME, GeographicCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<AbstractCoverageType> createAbstractCoverage(AbstractCoverageType value) {
        return new JAXBElement<AbstractCoverageType>(_AbstractCoverage_QNAME, AbstractCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodeticCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeodeticCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<GeodeticCRSType> createGeodeticCRS(GeodeticCRSType value) {
        return new JAXBElement<GeodeticCRSType>(_GeodeticCRS_QNAME, GeodeticCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcedurePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "using")
    public JAXBElement<ProcedurePropertyType> createUsing(ProcedurePropertyType value) {
        return new JAXBElement<ProcedurePropertyType>(_Using_QNAME, ProcedurePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OperationParameter", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralOperationParameter")
    public JAXBElement<OperationParameterType> createOperationParameter(OperationParameterType value) {
        return new JAXBElement<OperationParameterType>(_OperationParameter_QNAME, OperationParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "verticalDatum")
    public JAXBElement<VerticalDatumPropertyType> createVerticalDatumPropertyElement(VerticalDatumPropertyType value) {
        return new JAXBElement<VerticalDatumPropertyType>(_VerticalDatumPropertyElement_QNAME, VerticalDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DynamicFeatureMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dynamicMembers")
    public JAXBElement<DynamicFeatureMemberType> createDynamicMembers(DynamicFeatureMemberType value) {
        return new JAXBElement<DynamicFeatureMemberType>(_DynamicMembers_QNAME, DynamicFeatureMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArcType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Arc", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "ArcString")
    public JAXBElement<ArcType> createArc(ArcType value) {
        return new JAXBElement<ArcType>(_Arc_QNAME, ArcType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "maximumOccurs")
    public JAXBElement<BigInteger> createMaximumOccurs(BigInteger value) {
        return new JAXBElement<BigInteger>(_MaximumOccurs_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Surface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurface")
    public JAXBElement<SurfaceType> createSurface(SurfaceType value) {
        return new JAXBElement<SurfaceType>(_Surface_QNAME, SurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractImplicitGeometry", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometry")
    public JAXBElement<AbstractGeometryType> createAbstractImplicitGeometry(AbstractGeometryType value) {
        return new JAXBElement<AbstractGeometryType>(_AbstractImplicitGeometry_QNAME, AbstractGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShellType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Shell", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<ShellType> createShell(ShellType value) {
        return new JAXBElement<ShellType>(_Shell_QNAME, ShellType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociationRoleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "abstractStrictAssociationRole")
    public JAXBElement<AssociationRoleType> createAbstractStrictAssociationRole(AssociationRoleType value) {
        return new JAXBElement<AssociationRoleType>(_AbstractStrictAssociationRole_QNAME, AssociationRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Point", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricPrimitive")
    public JAXBElement<PointType> createPoint(PointType value) {
        return new JAXBElement<PointType>(_Point_QNAME, PointType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimeComplexType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimeComplex", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeObject")
    public JAXBElement<AbstractTimeComplexType> createAbstractTimeComplex(AbstractTimeComplexType value) {
        return new JAXBElement<AbstractTimeComplexType>(_AbstractTimeComplex_QNAME, AbstractTimeComplexType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiSurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCoverage")
    public JAXBElement<MultiSurfacePropertyType> createMultiCoverage(MultiSurfacePropertyType value) {
        return new JAXBElement<MultiSurfacePropertyType>(_MultiCoverage_QNAME, MultiSurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolidPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "solidMember")
    public JAXBElement<SolidPropertyType> createSolidMember(SolidPropertyType value) {
        return new JAXBElement<SolidPropertyType>(_SolidMember_QNAME, SolidPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodesicType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Geodesic", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "GeodesicString")
    public JAXBElement<GeodesicType> createGeodesic(GeodesicType value) {
        return new JAXBElement<GeodesicType>(_Geodesic_QNAME, GeodesicType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrientableSurfaceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OrientableSurface", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurface")
    public JAXBElement<OrientableSurfaceType> createOrientableSurface(OrientableSurfaceType value) {
        return new JAXBElement<OrientableSurfaceType>(_OrientableSurface_QNAME, OrientableSurfaceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "BooleanList", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractScalarValueList")
    public JAXBElement<List<String>> createBooleanList(List<String> value) {
        return new JAXBElement<List<String>>(_BooleanList_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordOperation")
    public JAXBElement<CoordinateOperationPropertyType> createCoordOperation(CoordinateOperationPropertyType value) {
        return new JAXBElement<CoordinateOperationPropertyType>(_CoordOperation_QNAME, CoordinateOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociationRoleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "member")
    public JAXBElement<AssociationRoleType> createMember(AssociationRoleType value) {
        return new JAXBElement<AssociationRoleType>(_Member_QNAME, AssociationRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "maximumValue")
    public JAXBElement<Double> createMaximumValue(Double value) {
        return new JAXBElement<Double>(_MaximumValue_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "anchorDefinition")
    public JAXBElement<CodeType> createAnchorDefinition(CodeType value) {
        return new JAXBElement<CodeType>(_AnchorDefinition_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RectangleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Rectangle", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurfacePatch")
    public JAXBElement<RectangleType> createRectangle(RectangleType value) {
        return new JAXBElement<RectangleType>(_Rectangle_QNAME, RectangleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObliqueCartesianCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ObliqueCartesianCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<ObliqueCartesianCSType> createObliqueCartesianCS(ObliqueCartesianCSType value) {
        return new JAXBElement<ObliqueCartesianCSType>(_ObliqueCartesianCS_QNAME, ObliqueCartesianCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DerivationUnitTermType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "derivationUnitTerm")
    public JAXBElement<DerivationUnitTermType> createDerivationUnitTerm(DerivationUnitTermType value) {
        return new JAXBElement<DerivationUnitTermType>(_DerivationUnitTerm_QNAME, DerivationUnitTermType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PolygonType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Polygon", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurface")
    public JAXBElement<PolygonType> createPolygon(PolygonType value) {
        return new JAXBElement<PolygonType>(_Polygon_QNAME, PolygonType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HistoryPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "history")
    public JAXBElement<HistoryPropertyType> createHistory(HistoryPropertyType value) {
        return new JAXBElement<HistoryPropertyType>(_History_QNAME, HistoryPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedEdgePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "directedEdge")
    public JAXBElement<DirectedEdgePropertyType> createDirectedEdge(DirectedEdgePropertyType value) {
        return new JAXBElement<DirectedEdgePropertyType>(_DirectedEdge_QNAME, DirectedEdgePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeWithAuthorityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "derivedCRSType")
    public JAXBElement<CodeWithAuthorityType> createDerivedCRSType(CodeWithAuthorityType value) {
        return new JAXBElement<CodeWithAuthorityType>(_DerivedCRSType_QNAME, CodeWithAuthorityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CoordinateSystemAxis", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Definition")
    public JAXBElement<CoordinateSystemAxisType> createCoordinateSystemAxis(CoordinateSystemAxisType value) {
        return new JAXBElement<CoordinateSystemAxisType>(_CoordinateSystemAxis_QNAME, CoordinateSystemAxisType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoVolumePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoVolumeProperty")
    public JAXBElement<TopoVolumePropertyType> createTopoVolumeProperty(TopoVolumePropertyType value) {
        return new JAXBElement<TopoVolumePropertyType>(_TopoVolumeProperty_QNAME, TopoVolumePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RingType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Ring", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractRing")
    public JAXBElement<RingType> createRing(RingType value) {
        return new JAXBElement<RingType>(_Ring_QNAME, RingType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "catalogSymbol")
    public JAXBElement<CodeType> createCatalogSymbol(CodeType value) {
        return new JAXBElement<CodeType>(_CatalogSymbol_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "extentOf")
    public JAXBElement<SurfacePropertyType> createExtentOf(SurfacePropertyType value) {
        return new JAXBElement<SurfacePropertyType>(_ExtentOf_QNAME, SurfacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DerivedUnitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DerivedUnit", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "UnitDefinition")
    public JAXBElement<DerivedUnitType> createDerivedUnit(DerivedUnitType value) {
        return new JAXBElement<DerivedUnitType>(_DerivedUnit_QNAME, DerivedUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DMSAngleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dmsAngleValue")
    public JAXBElement<DMSAngleType> createDmsAngleValue(DMSAngleType value) {
        return new JAXBElement<DMSAngleType>(_DmsAngleValue_QNAME, DMSAngleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "CountExtent", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<List<String>> createCountExtent(List<String> value) {
        return new JAXBElement<List<String>>(_CountExtent_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "sphericalCS")
    public JAXBElement<SphericalCSPropertyType> createSphericalCSPropertyElement(SphericalCSPropertyType value) {
        return new JAXBElement<SphericalCSPropertyType>(_SphericalCSPropertyElement_QNAME, SphericalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "coordOperation")
    public JAXBElement<CoordinateOperationPropertyType> createUsesOperation(CoordinateOperationPropertyType value) {
        return new JAXBElement<CoordinateOperationPropertyType>(_UsesOperation_QNAME, CoordinateOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueList")
    public JAXBElement<MeasureListType> createValueList(MeasureListType value) {
        return new JAXBElement<MeasureListType>(_ValueList_QNAME, MeasureListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueOfParameter", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "operationParameter")
    public JAXBElement<OperationParameterPropertyType> createValueOfParameter(OperationParameterPropertyType value) {
        return new JAXBElement<OperationParameterPropertyType>(_ValueOfParameter_QNAME, OperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesPrimeMeridian", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "primeMeridian")
    public JAXBElement<PrimeMeridianPropertyType> createUsesPrimeMeridian(PrimeMeridianPropertyType value) {
        return new JAXBElement<PrimeMeridianPropertyType>(_UsesPrimeMeridian_QNAME, PrimeMeridianPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiGeometryPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiGeometryProperty")
    public JAXBElement<MultiGeometryPropertyType> createMultiGeometryProperty(MultiGeometryPropertyType value) {
        return new JAXBElement<MultiGeometryPropertyType>(_MultiGeometryProperty_QNAME, MultiGeometryPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCenterOf")
    public JAXBElement<MultiPointPropertyType> createMultiCenterOf(MultiPointPropertyType value) {
        return new JAXBElement<MultiPointPropertyType>(_MultiCenterOf_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinatesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "tupleList")
    public JAXBElement<CoordinatesType> createTupleList(CoordinatesType value) {
        return new JAXBElement<CoordinatesType>(_TupleList_QNAME, CoordinatesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "abstractGeneralOperationParameterRef")
    public JAXBElement<AbstractGeneralOperationParameterPropertyType> createAbstractGeneralOperationParameterRef(AbstractGeneralOperationParameterPropertyType value) {
        return new JAXBElement<AbstractGeneralOperationParameterPropertyType>(_AbstractGeneralOperationParameterRef_QNAME, AbstractGeneralOperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Conversion", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralConversion")
    public JAXBElement<ConversionType> createConversion(ConversionType value) {
        return new JAXBElement<ConversionType>(_Conversion_QNAME, ConversionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoPointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoPointProperty")
    public JAXBElement<TopoPointPropertyType> createTopoPointProperty(TopoPointPropertyType value) {
        return new JAXBElement<TopoPointPropertyType>(_TopoPointProperty_QNAME, TopoPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "crsRef")
    public JAXBElement<CRSPropertyType> createCrsRef(CRSPropertyType value) {
        return new JAXBElement<CRSPropertyType>(_CrsRef_QNAME, CRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinateOperationRef")
    public JAXBElement<CoordinateOperationPropertyType> createCoordinateOperationRef(CoordinateOperationPropertyType value) {
        return new JAXBElement<CoordinateOperationPropertyType>(_CoordinateOperationRef_QNAME, CoordinateOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimeObjectType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimeObject", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<AbstractTimeObjectType> createAbstractTimeObject(AbstractTimeObjectType value) {
        return new JAXBElement<AbstractTimeObjectType>(_AbstractTimeObject_QNAME, AbstractTimeObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurveDomainType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiCurveDomain", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "domainSet")
    public JAXBElement<MultiCurveDomainType> createMultiCurveDomain(MultiCurveDomainType value) {
        return new JAXBElement<MultiCurveDomainType>(_MultiCurveDomain_QNAME, MultiCurveDomainType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "sphericalCSRef")
    public JAXBElement<SphericalCSPropertyType> createSphericalCSRef(SphericalCSPropertyType value) {
        return new JAXBElement<SphericalCSPropertyType>(_SphericalCSRef_QNAME, SphericalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link java.lang.Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "booleanValue")
    public JAXBElement<java.lang.Boolean> createBooleanValue(java.lang.Boolean value) {
        return new JAXBElement<java.lang.Boolean>(_BooleanValue_QNAME, java.lang.Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoComplexMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "topoComplexProperty")
    public JAXBElement<TopoComplexMemberType> createTopoComplexProperty(TopoComplexMemberType value) {
        return new JAXBElement<TopoComplexMemberType>(_TopoComplexProperty_QNAME, TopoComplexMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RectifiedGridCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "RectifiedGridCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractDiscreteCoverage")
    public JAXBElement<RectifiedGridCoverageType> createRectifiedGridCoverage(RectifiedGridCoverageType value) {
        return new JAXBElement<RectifiedGridCoverageType>(_RectifiedGridCoverage_QNAME, RectifiedGridCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeatureCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "FeatureCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<FeatureCollectionType> createFeatureCollection(FeatureCollectionType value) {
        return new JAXBElement<FeatureCollectionType>(_FeatureCollection_QNAME, FeatureCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "cartesianCS")
    public JAXBElement<CartesianCSPropertyType> createCartesianCSPropertyElement(CartesianCSPropertyType value) {
        return new JAXBElement<CartesianCSPropertyType>(_CartesianCSPropertyElement_QNAME, CartesianCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractRingPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "interior")
    public JAXBElement<AbstractRingPropertyType> createInterior(AbstractRingPropertyType value) {
        return new JAXBElement<AbstractRingPropertyType>(_Interior_QNAME, AbstractRingPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoComplexMemberType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "subComplex")
    public JAXBElement<TopoComplexMemberType> createSubComplex(TopoComplexMemberType value) {
        return new JAXBElement<TopoComplexMemberType>(_SubComplex_QNAME, TopoComplexMemberType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "minimumValue")
    public JAXBElement<Double> createMinimumValue(Double value) {
        return new JAXBElement<Double>(_MinimumValue_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "location")
    public JAXBElement<LocationPropertyType> createLocation(LocationPropertyType value) {
        return new JAXBElement<LocationPropertyType>(_Location_QNAME, LocationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "temporalCSRef")
    public JAXBElement<TemporalCSPropertyType> createTemporalCSRef(TemporalCSPropertyType value) {
        return new JAXBElement<TemporalCSPropertyType>(_TemporalCSRef_QNAME, TemporalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObliqueCartesianCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "obliqueCartesianCSRef")
    public JAXBElement<ObliqueCartesianCSPropertyType> createObliqueCartesianCSRef(ObliqueCartesianCSPropertyType value) {
        return new JAXBElement<ObliqueCartesianCSPropertyType>(_ObliqueCartesianCSRef_QNAME, ObliqueCartesianCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "value")
    public JAXBElement<MeasureType> createValue(MeasureType value) {
        return new JAXBElement<MeasureType>(_Value_QNAME, MeasureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "targetDimensions")
    public JAXBElement<BigInteger> createTargetDimensions(BigInteger value) {
        return new JAXBElement<BigInteger>(_TargetDimensions_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCalendarType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeCalendar", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "TimeReferenceSystem")
    public JAXBElement<TimeCalendarType> createTimeCalendar(TimeCalendarType value) {
        return new JAXBElement<TimeCalendarType>(_TimeCalendar_QNAME, TimeCalendarType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RectifiedGridType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "RectifiedGrid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "Grid")
    public JAXBElement<RectifiedGridType> createRectifiedGrid(RectifiedGridType value) {
        return new JAXBElement<RectifiedGridType>(_RectifiedGrid_QNAME, RectifiedGridType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedFacePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "directedFace")
    public JAXBElement<DirectedFacePropertyType> createDirectedFace(DirectedFacePropertyType value) {
        return new JAXBElement<DirectedFacePropertyType>(_DirectedFace_QNAME, DirectedFacePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PriorityLocationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "priorityLocation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "location")
    public JAXBElement<PriorityLocationPropertyType> createPriorityLocation(PriorityLocationPropertyType value) {
        return new JAXBElement<PriorityLocationPropertyType>(_PriorityLocation_QNAME, PriorityLocationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "EngineeringCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<EngineeringCRSType> createEngineeringCRS(EngineeringCRSType value) {
        return new JAXBElement<EngineeringCRSType>(_EngineeringCRS_QNAME, EngineeringCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidalCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "EllipsoidalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<EllipsoidalCSType> createEllipsoidalCS(EllipsoidalCSType value) {
        return new JAXBElement<EllipsoidalCSType>(_EllipsoidalCS_QNAME, EllipsoidalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurveArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "curveArrayProperty")
    public JAXBElement<CurveArrayPropertyType> createCurveArrayProperty(CurveArrayPropertyType value) {
        return new JAXBElement<CurveArrayPropertyType>(_CurveArrayProperty_QNAME, CurveArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointRep")
    public JAXBElement<PointPropertyType> createPointRep(PointPropertyType value) {
        return new JAXBElement<PointPropertyType>(_PointRep_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractContinuousCoverageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractContinuousCoverage", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<AbstractContinuousCoverageType> createAbstractContinuousCoverage(AbstractContinuousCoverageType value) {
        return new JAXBElement<AbstractContinuousCoverageType>(_AbstractContinuousCoverage_QNAME, AbstractContinuousCoverageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridFunctionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GridFunction", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractObject")
    public JAXBElement<GridFunctionType> createGridFunction(GridFunctionType value) {
        return new JAXBElement<GridFunctionType>(_GridFunction_QNAME, GridFunctionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "centerOf")
    public JAXBElement<PointPropertyType> createCenterOf(PointPropertyType value) {
        return new JAXBElement<PointPropertyType>(_CenterOf_QNAME, PointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PointArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "pointArrayProperty")
    public JAXBElement<PointArrayPropertyType> createPointArrayProperty(PointArrayPropertyType value) {
        return new JAXBElement<PointArrayPropertyType>(_PointArrayProperty_QNAME, PointArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralOperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesParameter", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "generalOperationParameter")
    public JAXBElement<AbstractGeneralOperationParameterPropertyType> createUsesParameter(AbstractGeneralOperationParameterPropertyType value) {
        return new JAXBElement<AbstractGeneralOperationParameterPropertyType>(_UsesParameter_QNAME, AbstractGeneralOperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeographicCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geographicCRSRef")
    public JAXBElement<GeographicCRSPropertyType> createGeographicCRSRef(GeographicCRSPropertyType value) {
        return new JAXBElement<GeographicCRSPropertyType>(_GeographicCRSRef_QNAME, GeographicCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFeatureCollectionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractFeatureCollection", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractFeature")
    public JAXBElement<AbstractFeatureCollectionType> createAbstractFeatureCollection(AbstractFeatureCollectionType value) {
        return new JAXBElement<AbstractFeatureCollectionType>(_AbstractFeatureCollection_QNAME, AbstractFeatureCollectionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "scope")
    public JAXBElement<String> createScope(String value) {
        return new JAXBElement<String>(_Scope_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BagType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Bag", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<BagType> createBag(BagType value) {
        return new JAXBElement<BagType>(_Bag_QNAME, BagType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeCalendarEraType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeCalendarEra")
    public JAXBElement<TimeCalendarEraType> createTimeCalendarEra(TimeCalendarEraType value) {
        return new JAXBElement<TimeCalendarEraType>(_TimeCalendarEra_QNAME, TimeCalendarEraType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionToPreferredUnitType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "conversionToPreferredUnit")
    public JAXBElement<ConversionToPreferredUnitType> createConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        return new JAXBElement<ConversionToPreferredUnitType>(_ConversionToPreferredUnit_QNAME, ConversionToPreferredUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OffsetCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "OffsetCurve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<OffsetCurveType> createOffsetCurve(OffsetCurveType value) {
        return new JAXBElement<OffsetCurveType>(_OffsetCurve_QNAME, OffsetCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractScalarValue", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractValue")
    public JAXBElement<Object> createAbstractScalarValue(Object value) {
        return new JAXBElement<Object>(_AbstractScalarValue_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinatesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "coordinates")
    public JAXBElement<CoordinatesType> createCoordinates(CoordinatesType value) {
        return new JAXBElement<CoordinatesType>(_Coordinates_QNAME, CoordinatesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiCurveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "MultiCurve", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometricAggregate")
    public JAXBElement<MultiCurveType> createMultiCurve(MultiCurveType value) {
        return new JAXBElement<MultiCurveType>(_MultiCurve_QNAME, MultiCurveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesEngineeringDatum", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "engineeringDatum")
    public JAXBElement<EngineeringDatumPropertyType> createUsesEngineeringDatum(EngineeringDatumPropertyType value) {
        return new JAXBElement<EngineeringDatumPropertyType>(_UsesEngineeringDatum_QNAME, EngineeringDatumPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesEllipsoidalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "ellipsoidalCS")
    public JAXBElement<EllipsoidalCSPropertyType> createUsesEllipsoidalCS(EllipsoidalCSPropertyType value) {
        return new JAXBElement<EllipsoidalCSPropertyType>(_UsesEllipsoidalCS_QNAME, EllipsoidalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurvePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "edgeOf")
    public JAXBElement<CurvePropertyType> createEdgeOf(CurvePropertyType value) {
        return new JAXBElement<CurvePropertyType>(_EdgeOf_QNAME, CurvePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "linearCSRef")
    public JAXBElement<LinearCSPropertyType> createLinearCSRef(LinearCSPropertyType value) {
        return new JAXBElement<LinearCSPropertyType>(_LinearCSRef_QNAME, LinearCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeometryArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geometryMembers")
    public JAXBElement<GeometryArrayPropertyType> createGeometryMembers(GeometryArrayPropertyType value) {
        return new JAXBElement<GeometryArrayPropertyType>(_GeometryMembers_QNAME, GeometryArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LocationKeyWord")
    public JAXBElement<CodeType> createLocationKeyWord(CodeType value) {
        return new JAXBElement<CodeType>(_LocationKeyWord_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DictionaryEntryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "dictionaryEntry")
    public JAXBElement<DictionaryEntryType> createDictionaryEntry(DictionaryEntryType value) {
        return new JAXBElement<DictionaryEntryType>(_DictionaryEntry_QNAME, DictionaryEntryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCoordinateOperationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractSingleOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateOperation")
    public JAXBElement<AbstractCoordinateOperationType> createAbstractSingleOperation(AbstractCoordinateOperationType value) {
        return new JAXBElement<AbstractCoordinateOperationType>(_AbstractSingleOperation_QNAME, AbstractCoordinateOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImageCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "ImageCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<ImageCRSType> createImageCRS(ImageCRSType value) {
        return new JAXBElement<ImageCRSType>(_ImageCRS_QNAME, ImageCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "coordinateSystem")
    public JAXBElement<CoordinateSystemPropertyType> createUsesCS(CoordinateSystemPropertyType value) {
        return new JAXBElement<CoordinateSystemPropertyType>(_UsesCS_QNAME, CoordinateSystemPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "singleCRSRef")
    public JAXBElement<SingleCRSPropertyType> createSingleCRSRef(SingleCRSPropertyType value) {
        return new JAXBElement<SingleCRSPropertyType>(_SingleCRSRef_QNAME, SingleCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IndirectEntryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "indirectEntry")
    public JAXBElement<IndirectEntryType> createIndirectEntry(IndirectEntryType value) {
        return new JAXBElement<IndirectEntryType>(_IndirectEntry_QNAME, IndirectEntryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "operationParameterRef")
    public JAXBElement<OperationParameterPropertyType> createOperationParameterRef(OperationParameterPropertyType value) {
        return new JAXBElement<OperationParameterPropertyType>(_OperationParameterRef_QNAME, OperationParameterPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeneralTransformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeneralTransformation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractOperation")
    public JAXBElement<AbstractGeneralTransformationType> createAbstractGeneralTransformation(AbstractGeneralTransformationType value) {
        return new JAXBElement<AbstractGeneralTransformationType>(_AbstractGeneralTransformation_QNAME, AbstractGeneralTransformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BezierType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Bezier", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "BSpline")
    public JAXBElement<BezierType> createBezier(BezierType value) {
        return new JAXBElement<BezierType>(_Bezier_QNAME, BezierType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DirectedObservationAtDistanceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "DirectedObservationAtDistance", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "DirectedObservation")
    public JAXBElement<DirectedObservationAtDistanceType> createDirectedObservationAtDistance(DirectedObservationAtDistanceType value) {
        return new JAXBElement<DirectedObservationAtDistanceType>(_DirectedObservationAtDistance_QNAME, DirectedObservationAtDistanceType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SurfacePatchArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "patches")
    public JAXBElement<SurfacePatchArrayPropertyType> createPatches(SurfacePatchArrayPropertyType value) {
        return new JAXBElement<SurfacePatchArrayPropertyType>(_Patches_QNAME, SurfacePatchArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiPointPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "multiLocation")
    public JAXBElement<MultiPointPropertyType> createMultiLocation(MultiPointPropertyType value) {
        return new JAXBElement<MultiPointPropertyType>(_MultiLocation_QNAME, MultiPointPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractFeatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractFeature", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<AbstractFeatureType> createAbstractFeature(AbstractFeatureType value) {
        return new JAXBElement<AbstractFeatureType>(_AbstractFeature_QNAME, AbstractFeatureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeaturePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "featureProperty")
    public JAXBElement<FeaturePropertyType> createFeatureProperty(FeaturePropertyType value) {
        return new JAXBElement<FeaturePropertyType>(_FeatureProperty_QNAME, FeaturePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Transformation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeneralTransformation")
    public JAXBElement<TransformationType> createTransformation(TransformationType value) {
        return new JAXBElement<TransformationType>(_Transformation_QNAME, TransformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TrianglePatchArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "trianglePatches", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "patches")
    public JAXBElement<TrianglePatchArrayPropertyType> createTrianglePatches(TrianglePatchArrayPropertyType value) {
        return new JAXBElement<TrianglePatchArrayPropertyType>(_TrianglePatches_QNAME, TrianglePatchArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeClockType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeClock", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "TimeReferenceSystem")
    public JAXBElement<TimeClockType> createTimeClock(TimeClockType value) {
        return new JAXBElement<TimeClockType>(_TimeClock_QNAME, TimeClockType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LinearCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LinearCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<LinearCSType> createLinearCS(LinearCSType value) {
        return new JAXBElement<LinearCSType>(_LinearCS_QNAME, LinearCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTopoPrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTopoPrimitive", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopology")
    public JAXBElement<AbstractTopoPrimitiveType> createAbstractTopoPrimitive(AbstractTopoPrimitiveType value) {
        return new JAXBElement<AbstractTopoPrimitiveType>(_AbstractTopoPrimitive_QNAME, AbstractTopoPrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SphericalCSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "SphericalCS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCoordinateSystem")
    public JAXBElement<SphericalCSType> createSphericalCS(SphericalCSType value) {
        return new JAXBElement<SphericalCSType>(_SphericalCS_QNAME, SphericalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesSingleOperation", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "coordOperation")
    public JAXBElement<CoordinateOperationPropertyType> createUsesSingleOperation(CoordinateOperationPropertyType value) {
        return new JAXBElement<CoordinateOperationPropertyType>(_UsesSingleOperation_QNAME, CoordinateOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TopoSolidType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TopoSolid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTopoPrimitive")
    public JAXBElement<TopoSolidType> createTopoSolid(TopoSolidType value) {
        return new JAXBElement<TopoSolidType>(_TopoSolid_QNAME, TopoSolidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "verticalCS")
    public JAXBElement<VerticalCSPropertyType> createVerticalCSPropertyElement(VerticalCSPropertyType value) {
        return new JAXBElement<VerticalCSPropertyType>(_VerticalCSPropertyElement_QNAME, VerticalCSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TriangleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Triangle", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSurfacePatch")
    public JAXBElement<TriangleType> createTriangle(TriangleType value) {
        return new JAXBElement<TriangleType>(_Triangle_QNAME, TriangleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeocentricCRSPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "geocentricCRSRef")
    public JAXBElement<GeocentricCRSPropertyType> createGeocentricCRSRef(GeocentricCRSPropertyType value) {
        return new JAXBElement<GeocentricCRSPropertyType>(_GeocentricCRSRef_QNAME, GeocentricCRSPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociationRoleType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "abstractAssociationRole")
    public JAXBElement<AssociationRoleType> createAbstractAssociationRole(AssociationRoleType value) {
        return new JAXBElement<AssociationRoleType>(_AbstractAssociationRole_QNAME, AssociationRoleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValueArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "valueComponents")
    public JAXBElement<ValueArrayPropertyType> createValueComponents(ValueArrayPropertyType value) {
        return new JAXBElement<ValueArrayPropertyType>(_ValueComponents_QNAME, ValueArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "quantityType")
    public JAXBElement<StringOrRefType> createQuantityType(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_QuantityType_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeaturePropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "featureMember")
    public JAXBElement<FeaturePropertyType> createFeatureMember(FeaturePropertyType value) {
        return new JAXBElement<FeaturePropertyType>(_FeatureMember_QNAME, FeaturePropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StringOrRefType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "LocationString")
    public JAXBElement<StringOrRefType> createLocationString(StringOrRefType value) {
        return new JAXBElement<StringOrRefType>(_LocationString_QNAME, StringOrRefType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesAxis", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "axis")
    public JAXBElement<CoordinateSystemAxisPropertyType> createUsesAxis(CoordinateSystemAxisPropertyType value) {
        return new JAXBElement<CoordinateSystemAxisPropertyType>(_UsesAxis_QNAME, CoordinateSystemAxisPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometricAggregateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeometricAggregate", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGeometry")
    public JAXBElement<AbstractGeometricAggregateType> createAbstractGeometricAggregate(AbstractGeometricAggregateType value) {
        return new JAXBElement<AbstractGeometricAggregateType>(_AbstractGeometricAggregate_QNAME, AbstractGeometricAggregateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "method")
    public JAXBElement<OperationMethodPropertyType> createMethod(OperationMethodPropertyType value) {
        return new JAXBElement<OperationMethodPropertyType>(_Method_QNAME, OperationMethodPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link BigInteger }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "integerValueList")
    public JAXBElement<List<BigInteger>> createIntegerValueList(List<BigInteger> value) {
        return new JAXBElement<List<BigInteger>>(_IntegerValueList_QNAME, ((Class) List.class), null, ((List<BigInteger> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "anchorPoint", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "anchorDefinition")
    public JAXBElement<CodeType> createAnchorPoint(CodeType value) {
        return new JAXBElement<CodeType>(_AnchorPoint_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationMethodPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "usesMethod", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "method")
    public JAXBElement<OperationMethodPropertyType> createUsesMethod(OperationMethodPropertyType value) {
        return new JAXBElement<OperationMethodPropertyType>(_UsesMethod_QNAME, OperationMethodPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DefinitionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Definition", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<DefinitionType> createDefinition(DefinitionType value) {
        return new JAXBElement<DefinitionType>(_Definition_QNAME, DefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeodesicStringType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "GeodesicString", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractCurveSegment")
    public JAXBElement<GeodesicStringType> createGeodesicString(GeodesicStringType value) {
        return new JAXBElement<GeodesicStringType>(_GeodesicString_QNAME, GeodesicStringType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractTimeGeometricPrimitiveType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractTimeGeometricPrimitive", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimePrimitive")
    public JAXBElement<AbstractTimeGeometricPrimitiveType> createAbstractTimeGeometricPrimitive(AbstractTimeGeometricPrimitiveType value) {
        return new JAXBElement<AbstractTimeGeometricPrimitiveType>(_AbstractTimeGeometricPrimitive_QNAME, AbstractTimeGeometricPrimitiveType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CylinderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Cylinder", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGriddedSurface")
    public JAXBElement<CylinderType> createCylinder(CylinderType value) {
        return new JAXBElement<CylinderType>(_Cylinder_QNAME, CylinderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SingleOperationPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "singleOperationRef")
    public JAXBElement<SingleOperationPropertyType> createSingleOperationRef(SingleOperationPropertyType value) {
        return new JAXBElement<SingleOperationPropertyType>(_SingleOperationRef_QNAME, SingleOperationPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "subject", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "target")
    public JAXBElement<TargetPropertyType> createSubject(TargetPropertyType value) {
        return new JAXBElement<TargetPropertyType>(_Subject_QNAME, TargetPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurveSegmentArrayPropertyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "segments")
    public JAXBElement<CurveSegmentArrayPropertyType> createSegments(CurveSegmentArrayPropertyType value) {
        return new JAXBElement<CurveSegmentArrayPropertyType>(_Segments_QNAME, CurveSegmentArrayPropertyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerticalCRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "VerticalCRS", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractSingleCRS")
    public JAXBElement<VerticalCRSType> createVerticalCRS(VerticalCRSType value) {
        return new JAXBElement<VerticalCRSType>(_VerticalCRS_QNAME, VerticalCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "Grid", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractImplicitGeometry")
    public JAXBElement<GridType> createGrid(GridType value) {
        return new JAXBElement<GridType>(_Grid_QNAME, GridType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeInstantType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "TimeInstant", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractTimeGeometricPrimitive")
    public JAXBElement<TimeInstantType> createTimeInstant(TimeInstantType value) {
        return new JAXBElement<TimeInstantType>(_TimeInstant_QNAME, TimeInstantType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "AbstractGeometry", substitutionHeadNamespace = "http://www.opengis.net/gml", substitutionHeadName = "AbstractGML")
    public JAXBElement<AbstractGeometryType> createAbstractGeometry(AbstractGeometryType value) {
        return new JAXBElement<AbstractGeometryType>(_AbstractGeometry_QNAME, AbstractGeometryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GridLimitsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "limits", scope = GridType.class)
    public JAXBElement<GridLimitsType> createGridTypeLimits(GridLimitsType value) {
        return new JAXBElement<GridLimitsType>(_GridTypeLimits_QNAME, GridLimitsType.class, GridType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "axisName", scope = GridType.class)
    public JAXBElement<String> createGridTypeAxisName(String value) {
        return new JAXBElement<String>(_GridTypeAxisName_QNAME, String.class, GridType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.opengis.net/gml", name = "axisLabels", scope = GridType.class)
    public JAXBElement<List<String>> createGridTypeAxisLabels(List<String> value) {
        return new JAXBElement<List<String>>(_GridTypeAxisLabels_QNAME, ((Class) List.class), GridType.class, ((List<String> ) value));
    }

}
