/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmx;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CodeListValueType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.iso19139.v20070417.gmx package. 
 * <p>An ObjectFactory allows you to programmatically 
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

    private static final QName _MLBaseUnit_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_BaseUnit");
    private static final QName _MLDerivedUnit_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_DerivedUnit");
    private static final QName _MLConventionalUnit_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_ConventionalUnit");
    private static final QName _MLUnitDefinition_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_UnitDefinition");
    private static final QName _UomAlternativeExpression_QNAME = new QName("http://www.isotc211.org/2005/gmx", "UomAlternativeExpression");
    private static final QName _CodeDefinition_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CodeDefinition");
    private static final QName _CodeListDictionary_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CodeListDictionary");
    private static final QName _MLCodeDefinition_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CodeDefinition");
    private static final QName _MLCodeListDictionary_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CodeListDictionary");
    private static final QName _ClAlternativeExpression_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ClAlternativeExpression");
    private static final QName _CodeAlternativeExpression_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CodeAlternativeExpression");
    private static final QName _MLCompoundCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CompoundCRS");
    private static final QName _MLGeodeticCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_GeodeticCRS");
    private static final QName _MLEngineeringCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_EngineeringCRS");
    private static final QName _MLVerticalCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_VerticalCRS");
    private static final QName _MLTemporalCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_TemporalCRS");
    private static final QName _MLImageCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_ImageCRS");
    private static final QName _MLProjectedCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_ProjectedCRS");
    private static final QName _MLDerivedCRS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_DerivedCRS");
    private static final QName _MLCoordinateSystemAxis_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CoordinateSystemAxis");
    private static final QName _MLEllipsoidalCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_EllipsoidalCS");
    private static final QName _MLCartesianCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CartesianCS");
    private static final QName _MLAffineCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_AffineCS");
    private static final QName _MLUserDefinedCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_UserDefinedCS");
    private static final QName _MLVerticalCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_VerticalCS");
    private static final QName _MLTimeCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_TimeCS");
    private static final QName _MLCylindricalCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_CylindricalCS");
    private static final QName _MLSphericalCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_SphericalCS");
    private static final QName _MLPolarCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_PolarCS");
    private static final QName _MLLinearCS_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_LinearCS");
    private static final QName _MLEllipsoid_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_Ellipsoid");
    private static final QName _MLPrimeMeridian_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_PrimeMeridian");
    private static final QName _MLTemporalDatum_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_TemporalDatum");
    private static final QName _MLVerticalDatum_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_VerticalDatum");
    private static final QName _MLImageDatum_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_ImageDatum");
    private static final QName _MLEngineeringDatum_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_EngineeringDatum");
    private static final QName _MLGeodeticDatum_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_GeodeticDatum");
    private static final QName _MLConcatenatedOperation_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_ConcatenatedOperation");
    private static final QName _MLPassThroughOperation_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_PassThroughOperation");
    private static final QName _MLTransformation_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_Transformation");
    private static final QName _MLConversion_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_Conversion");
    private static final QName _MLOperationMethod_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_OperationMethod");
    private static final QName _MLOperationParameterGroup_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_OperationParameterGroup");
    private static final QName _MLOperationParameter_QNAME = new QName("http://www.isotc211.org/2005/gmx", "ML_OperationParameter");
    private static final QName _CrsAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CrsAlt");
    private static final QName _CoordinateSystemAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CoordinateSystemAlt");
    private static final QName _CoordinateSystemAxisAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CoordinateSystemAxisAlt");
    private static final QName _DatumAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "DatumAlt");
    private static final QName _EllipsoidAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "EllipsoidAlt");
    private static final QName _PrimeMeridianAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "PrimeMeridianAlt");
    private static final QName _OperationAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "OperationAlt");
    private static final QName _OperationMethodAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "OperationMethodAlt");
    private static final QName _OperationParameterAlt_QNAME = new QName("http://www.isotc211.org/2005/gmx", "OperationParameterAlt");
    private static final QName _AbstractCTCatalogue_QNAME = new QName("http://www.isotc211.org/2005/gmx", "AbstractCT_Catalogue");
    private static final QName _CTCodelistCatalogue_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CT_CodelistCatalogue");
    private static final QName _CTCrsCatalogue_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CT_CrsCatalogue");
    private static final QName _CTUomCatalogue_QNAME = new QName("http://www.isotc211.org/2005/gmx", "CT_UomCatalogue");
    private static final QName _FileName_QNAME = new QName("http://www.isotc211.org/2005/gmx", "FileName");
    private static final QName _MimeFileType_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MimeFileType");
    private static final QName _Anchor_QNAME = new QName("http://www.isotc211.org/2005/gmx", "Anchor");
    private static final QName _MXAggregate_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MX_Aggregate");
    private static final QName _MXDataSet_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MX_DataSet");
    private static final QName _MXDataFile_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MX_DataFile");
    private static final QName _AbstractMXFile_QNAME = new QName("http://www.isotc211.org/2005/gmx", "AbstractMX_File");
    private static final QName _MXSupportFile_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MX_SupportFile");
    private static final QName _MXScopeCode_QNAME = new QName("http://www.isotc211.org/2005/gmx", "MX_ScopeCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.iso19139.v20070417.gmx
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MLBaseUnitType }
     * 
     * @return
     *     the new instance of {@link MLBaseUnitType }
     */
    public MLBaseUnitType createMLBaseUnitType() {
        return new MLBaseUnitType();
    }

    /**
     * Create an instance of {@link MLDerivedUnitType }
     * 
     * @return
     *     the new instance of {@link MLDerivedUnitType }
     */
    public MLDerivedUnitType createMLDerivedUnitType() {
        return new MLDerivedUnitType();
    }

    /**
     * Create an instance of {@link MLConventionalUnitType }
     * 
     * @return
     *     the new instance of {@link MLConventionalUnitType }
     */
    public MLConventionalUnitType createMLConventionalUnitType() {
        return new MLConventionalUnitType();
    }

    /**
     * Create an instance of {@link MLUnitDefinitionType }
     * 
     * @return
     *     the new instance of {@link MLUnitDefinitionType }
     */
    public MLUnitDefinitionType createMLUnitDefinitionType() {
        return new MLUnitDefinitionType();
    }

    /**
     * Create an instance of {@link UomAlternativeExpressionType }
     * 
     * @return
     *     the new instance of {@link UomAlternativeExpressionType }
     */
    public UomAlternativeExpressionType createUomAlternativeExpressionType() {
        return new UomAlternativeExpressionType();
    }

    /**
     * Create an instance of {@link CodeDefinitionType }
     * 
     * @return
     *     the new instance of {@link CodeDefinitionType }
     */
    public CodeDefinitionType createCodeDefinitionType() {
        return new CodeDefinitionType();
    }

    /**
     * Create an instance of {@link CodeListDictionaryType }
     * 
     * @return
     *     the new instance of {@link CodeListDictionaryType }
     */
    public CodeListDictionaryType createCodeListDictionaryType() {
        return new CodeListDictionaryType();
    }

    /**
     * Create an instance of {@link MLCodeDefinitionType }
     * 
     * @return
     *     the new instance of {@link MLCodeDefinitionType }
     */
    public MLCodeDefinitionType createMLCodeDefinitionType() {
        return new MLCodeDefinitionType();
    }

    /**
     * Create an instance of {@link MLCodeListDictionaryType }
     * 
     * @return
     *     the new instance of {@link MLCodeListDictionaryType }
     */
    public MLCodeListDictionaryType createMLCodeListDictionaryType() {
        return new MLCodeListDictionaryType();
    }

    /**
     * Create an instance of {@link ClAlternativeExpressionType }
     * 
     * @return
     *     the new instance of {@link ClAlternativeExpressionType }
     */
    public ClAlternativeExpressionType createClAlternativeExpressionType() {
        return new ClAlternativeExpressionType();
    }

    /**
     * Create an instance of {@link CodeAlternativeExpressionType }
     * 
     * @return
     *     the new instance of {@link CodeAlternativeExpressionType }
     */
    public CodeAlternativeExpressionType createCodeAlternativeExpressionType() {
        return new CodeAlternativeExpressionType();
    }

    /**
     * Create an instance of {@link MLCompoundCRSType }
     * 
     * @return
     *     the new instance of {@link MLCompoundCRSType }
     */
    public MLCompoundCRSType createMLCompoundCRSType() {
        return new MLCompoundCRSType();
    }

    /**
     * Create an instance of {@link MLGeodeticCRSType }
     * 
     * @return
     *     the new instance of {@link MLGeodeticCRSType }
     */
    public MLGeodeticCRSType createMLGeodeticCRSType() {
        return new MLGeodeticCRSType();
    }

    /**
     * Create an instance of {@link MLEngineeringCRSType }
     * 
     * @return
     *     the new instance of {@link MLEngineeringCRSType }
     */
    public MLEngineeringCRSType createMLEngineeringCRSType() {
        return new MLEngineeringCRSType();
    }

    /**
     * Create an instance of {@link MLVerticalCRSType }
     * 
     * @return
     *     the new instance of {@link MLVerticalCRSType }
     */
    public MLVerticalCRSType createMLVerticalCRSType() {
        return new MLVerticalCRSType();
    }

    /**
     * Create an instance of {@link MLTemporalCRSType }
     * 
     * @return
     *     the new instance of {@link MLTemporalCRSType }
     */
    public MLTemporalCRSType createMLTemporalCRSType() {
        return new MLTemporalCRSType();
    }

    /**
     * Create an instance of {@link MLImageCRSType }
     * 
     * @return
     *     the new instance of {@link MLImageCRSType }
     */
    public MLImageCRSType createMLImageCRSType() {
        return new MLImageCRSType();
    }

    /**
     * Create an instance of {@link MLProjectedCRSType }
     * 
     * @return
     *     the new instance of {@link MLProjectedCRSType }
     */
    public MLProjectedCRSType createMLProjectedCRSType() {
        return new MLProjectedCRSType();
    }

    /**
     * Create an instance of {@link MLDerivedCRSType }
     * 
     * @return
     *     the new instance of {@link MLDerivedCRSType }
     */
    public MLDerivedCRSType createMLDerivedCRSType() {
        return new MLDerivedCRSType();
    }

    /**
     * Create an instance of {@link MLCoordinateSystemAxisType }
     * 
     * @return
     *     the new instance of {@link MLCoordinateSystemAxisType }
     */
    public MLCoordinateSystemAxisType createMLCoordinateSystemAxisType() {
        return new MLCoordinateSystemAxisType();
    }

    /**
     * Create an instance of {@link MLEllipsoidalCSType }
     * 
     * @return
     *     the new instance of {@link MLEllipsoidalCSType }
     */
    public MLEllipsoidalCSType createMLEllipsoidalCSType() {
        return new MLEllipsoidalCSType();
    }

    /**
     * Create an instance of {@link MLCartesianCSType }
     * 
     * @return
     *     the new instance of {@link MLCartesianCSType }
     */
    public MLCartesianCSType createMLCartesianCSType() {
        return new MLCartesianCSType();
    }

    /**
     * Create an instance of {@link MLAffineCSType }
     * 
     * @return
     *     the new instance of {@link MLAffineCSType }
     */
    public MLAffineCSType createMLAffineCSType() {
        return new MLAffineCSType();
    }

    /**
     * Create an instance of {@link MLUserDefinedCSType }
     * 
     * @return
     *     the new instance of {@link MLUserDefinedCSType }
     */
    public MLUserDefinedCSType createMLUserDefinedCSType() {
        return new MLUserDefinedCSType();
    }

    /**
     * Create an instance of {@link MLVerticalCSType }
     * 
     * @return
     *     the new instance of {@link MLVerticalCSType }
     */
    public MLVerticalCSType createMLVerticalCSType() {
        return new MLVerticalCSType();
    }

    /**
     * Create an instance of {@link MLTimeCSType }
     * 
     * @return
     *     the new instance of {@link MLTimeCSType }
     */
    public MLTimeCSType createMLTimeCSType() {
        return new MLTimeCSType();
    }

    /**
     * Create an instance of {@link MLCylindricalCSType }
     * 
     * @return
     *     the new instance of {@link MLCylindricalCSType }
     */
    public MLCylindricalCSType createMLCylindricalCSType() {
        return new MLCylindricalCSType();
    }

    /**
     * Create an instance of {@link MLSphericalCSType }
     * 
     * @return
     *     the new instance of {@link MLSphericalCSType }
     */
    public MLSphericalCSType createMLSphericalCSType() {
        return new MLSphericalCSType();
    }

    /**
     * Create an instance of {@link MLPolarCSType }
     * 
     * @return
     *     the new instance of {@link MLPolarCSType }
     */
    public MLPolarCSType createMLPolarCSType() {
        return new MLPolarCSType();
    }

    /**
     * Create an instance of {@link MLLinearCSType }
     * 
     * @return
     *     the new instance of {@link MLLinearCSType }
     */
    public MLLinearCSType createMLLinearCSType() {
        return new MLLinearCSType();
    }

    /**
     * Create an instance of {@link MLEllipsoidType }
     * 
     * @return
     *     the new instance of {@link MLEllipsoidType }
     */
    public MLEllipsoidType createMLEllipsoidType() {
        return new MLEllipsoidType();
    }

    /**
     * Create an instance of {@link MLPrimeMeridianType }
     * 
     * @return
     *     the new instance of {@link MLPrimeMeridianType }
     */
    public MLPrimeMeridianType createMLPrimeMeridianType() {
        return new MLPrimeMeridianType();
    }

    /**
     * Create an instance of {@link MLTemporalDatumType }
     * 
     * @return
     *     the new instance of {@link MLTemporalDatumType }
     */
    public MLTemporalDatumType createMLTemporalDatumType() {
        return new MLTemporalDatumType();
    }

    /**
     * Create an instance of {@link MLVerticalDatumType }
     * 
     * @return
     *     the new instance of {@link MLVerticalDatumType }
     */
    public MLVerticalDatumType createMLVerticalDatumType() {
        return new MLVerticalDatumType();
    }

    /**
     * Create an instance of {@link MLImageDatumType }
     * 
     * @return
     *     the new instance of {@link MLImageDatumType }
     */
    public MLImageDatumType createMLImageDatumType() {
        return new MLImageDatumType();
    }

    /**
     * Create an instance of {@link MLEngineeringDatumType }
     * 
     * @return
     *     the new instance of {@link MLEngineeringDatumType }
     */
    public MLEngineeringDatumType createMLEngineeringDatumType() {
        return new MLEngineeringDatumType();
    }

    /**
     * Create an instance of {@link MLGeodeticDatumType }
     * 
     * @return
     *     the new instance of {@link MLGeodeticDatumType }
     */
    public MLGeodeticDatumType createMLGeodeticDatumType() {
        return new MLGeodeticDatumType();
    }

    /**
     * Create an instance of {@link MLConcatenatedOperationType }
     * 
     * @return
     *     the new instance of {@link MLConcatenatedOperationType }
     */
    public MLConcatenatedOperationType createMLConcatenatedOperationType() {
        return new MLConcatenatedOperationType();
    }

    /**
     * Create an instance of {@link MLPassThroughOperationType }
     * 
     * @return
     *     the new instance of {@link MLPassThroughOperationType }
     */
    public MLPassThroughOperationType createMLPassThroughOperationType() {
        return new MLPassThroughOperationType();
    }

    /**
     * Create an instance of {@link MLTransformationType }
     * 
     * @return
     *     the new instance of {@link MLTransformationType }
     */
    public MLTransformationType createMLTransformationType() {
        return new MLTransformationType();
    }

    /**
     * Create an instance of {@link MLConversionType }
     * 
     * @return
     *     the new instance of {@link MLConversionType }
     */
    public MLConversionType createMLConversionType() {
        return new MLConversionType();
    }

    /**
     * Create an instance of {@link MLOperationMethodType }
     * 
     * @return
     *     the new instance of {@link MLOperationMethodType }
     */
    public MLOperationMethodType createMLOperationMethodType() {
        return new MLOperationMethodType();
    }

    /**
     * Create an instance of {@link MLOperationParameterGroupType }
     * 
     * @return
     *     the new instance of {@link MLOperationParameterGroupType }
     */
    public MLOperationParameterGroupType createMLOperationParameterGroupType() {
        return new MLOperationParameterGroupType();
    }

    /**
     * Create an instance of {@link MLOperationParameterType }
     * 
     * @return
     *     the new instance of {@link MLOperationParameterType }
     */
    public MLOperationParameterType createMLOperationParameterType() {
        return new MLOperationParameterType();
    }

    /**
     * Create an instance of {@link CrsAltType }
     * 
     * @return
     *     the new instance of {@link CrsAltType }
     */
    public CrsAltType createCrsAltType() {
        return new CrsAltType();
    }

    /**
     * Create an instance of {@link CoordinateSystemAltType }
     * 
     * @return
     *     the new instance of {@link CoordinateSystemAltType }
     */
    public CoordinateSystemAltType createCoordinateSystemAltType() {
        return new CoordinateSystemAltType();
    }

    /**
     * Create an instance of {@link CoordinateSystemAxisAltType }
     * 
     * @return
     *     the new instance of {@link CoordinateSystemAxisAltType }
     */
    public CoordinateSystemAxisAltType createCoordinateSystemAxisAltType() {
        return new CoordinateSystemAxisAltType();
    }

    /**
     * Create an instance of {@link DatumAltType }
     * 
     * @return
     *     the new instance of {@link DatumAltType }
     */
    public DatumAltType createDatumAltType() {
        return new DatumAltType();
    }

    /**
     * Create an instance of {@link EllipsoidAltType }
     * 
     * @return
     *     the new instance of {@link EllipsoidAltType }
     */
    public EllipsoidAltType createEllipsoidAltType() {
        return new EllipsoidAltType();
    }

    /**
     * Create an instance of {@link PrimeMeridianAltType }
     * 
     * @return
     *     the new instance of {@link PrimeMeridianAltType }
     */
    public PrimeMeridianAltType createPrimeMeridianAltType() {
        return new PrimeMeridianAltType();
    }

    /**
     * Create an instance of {@link OperationAltType }
     * 
     * @return
     *     the new instance of {@link OperationAltType }
     */
    public OperationAltType createOperationAltType() {
        return new OperationAltType();
    }

    /**
     * Create an instance of {@link OperationMethodAltType }
     * 
     * @return
     *     the new instance of {@link OperationMethodAltType }
     */
    public OperationMethodAltType createOperationMethodAltType() {
        return new OperationMethodAltType();
    }

    /**
     * Create an instance of {@link OperationParameterAltType }
     * 
     * @return
     *     the new instance of {@link OperationParameterAltType }
     */
    public OperationParameterAltType createOperationParameterAltType() {
        return new OperationParameterAltType();
    }

    /**
     * Create an instance of {@link CTCodelistCatalogueType }
     * 
     * @return
     *     the new instance of {@link CTCodelistCatalogueType }
     */
    public CTCodelistCatalogueType createCTCodelistCatalogueType() {
        return new CTCodelistCatalogueType();
    }

    /**
     * Create an instance of {@link CTCrsCatalogueType }
     * 
     * @return
     *     the new instance of {@link CTCrsCatalogueType }
     */
    public CTCrsCatalogueType createCTCrsCatalogueType() {
        return new CTCrsCatalogueType();
    }

    /**
     * Create an instance of {@link CTUomCatalogueType }
     * 
     * @return
     *     the new instance of {@link CTUomCatalogueType }
     */
    public CTUomCatalogueType createCTUomCatalogueType() {
        return new CTUomCatalogueType();
    }

    /**
     * Create an instance of {@link FileNameType }
     * 
     * @return
     *     the new instance of {@link FileNameType }
     */
    public FileNameType createFileNameType() {
        return new FileNameType();
    }

    /**
     * Create an instance of {@link MimeFileTypeType }
     * 
     * @return
     *     the new instance of {@link MimeFileTypeType }
     */
    public MimeFileTypeType createMimeFileTypeType() {
        return new MimeFileTypeType();
    }

    /**
     * Create an instance of {@link AnchorType }
     * 
     * @return
     *     the new instance of {@link AnchorType }
     */
    public AnchorType createAnchorType() {
        return new AnchorType();
    }

    /**
     * Create an instance of {@link MXAggregateType }
     * 
     * @return
     *     the new instance of {@link MXAggregateType }
     */
    public MXAggregateType createMXAggregateType() {
        return new MXAggregateType();
    }

    /**
     * Create an instance of {@link MXDataSetType }
     * 
     * @return
     *     the new instance of {@link MXDataSetType }
     */
    public MXDataSetType createMXDataSetType() {
        return new MXDataSetType();
    }

    /**
     * Create an instance of {@link MXDataFileType }
     * 
     * @return
     *     the new instance of {@link MXDataFileType }
     */
    public MXDataFileType createMXDataFileType() {
        return new MXDataFileType();
    }

    /**
     * Create an instance of {@link MXSupportFileType }
     * 
     * @return
     *     the new instance of {@link MXSupportFileType }
     */
    public MXSupportFileType createMXSupportFileType() {
        return new MXSupportFileType();
    }

    /**
     * Create an instance of {@link UnitDefinitionPropertyType }
     * 
     * @return
     *     the new instance of {@link UnitDefinitionPropertyType }
     */
    public UnitDefinitionPropertyType createUnitDefinitionPropertyType() {
        return new UnitDefinitionPropertyType();
    }

    /**
     * Create an instance of {@link BaseUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link BaseUnitPropertyType }
     */
    public BaseUnitPropertyType createBaseUnitPropertyType() {
        return new BaseUnitPropertyType();
    }

    /**
     * Create an instance of {@link DerivedUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link DerivedUnitPropertyType }
     */
    public DerivedUnitPropertyType createDerivedUnitPropertyType() {
        return new DerivedUnitPropertyType();
    }

    /**
     * Create an instance of {@link ConventionalUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link ConventionalUnitPropertyType }
     */
    public ConventionalUnitPropertyType createConventionalUnitPropertyType() {
        return new ConventionalUnitPropertyType();
    }

    /**
     * Create an instance of {@link MLBaseUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link MLBaseUnitPropertyType }
     */
    public MLBaseUnitPropertyType createMLBaseUnitPropertyType() {
        return new MLBaseUnitPropertyType();
    }

    /**
     * Create an instance of {@link MLDerivedUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link MLDerivedUnitPropertyType }
     */
    public MLDerivedUnitPropertyType createMLDerivedUnitPropertyType() {
        return new MLDerivedUnitPropertyType();
    }

    /**
     * Create an instance of {@link MLConventionalUnitPropertyType }
     * 
     * @return
     *     the new instance of {@link MLConventionalUnitPropertyType }
     */
    public MLConventionalUnitPropertyType createMLConventionalUnitPropertyType() {
        return new MLConventionalUnitPropertyType();
    }

    /**
     * Create an instance of {@link MLUnitDefinitionPropertyType }
     * 
     * @return
     *     the new instance of {@link MLUnitDefinitionPropertyType }
     */
    public MLUnitDefinitionPropertyType createMLUnitDefinitionPropertyType() {
        return new MLUnitDefinitionPropertyType();
    }

    /**
     * Create an instance of {@link UomAlternativeExpressionPropertyType }
     * 
     * @return
     *     the new instance of {@link UomAlternativeExpressionPropertyType }
     */
    public UomAlternativeExpressionPropertyType createUomAlternativeExpressionPropertyType() {
        return new UomAlternativeExpressionPropertyType();
    }

    /**
     * Create an instance of {@link CTCodelistValuePropertyType }
     * 
     * @return
     *     the new instance of {@link CTCodelistValuePropertyType }
     */
    public CTCodelistValuePropertyType createCTCodelistValuePropertyType() {
        return new CTCodelistValuePropertyType();
    }

    /**
     * Create an instance of {@link CTCodelistPropertyType }
     * 
     * @return
     *     the new instance of {@link CTCodelistPropertyType }
     */
    public CTCodelistPropertyType createCTCodelistPropertyType() {
        return new CTCodelistPropertyType();
    }

    /**
     * Create an instance of {@link CodeDefinitionPropertyType }
     * 
     * @return
     *     the new instance of {@link CodeDefinitionPropertyType }
     */
    public CodeDefinitionPropertyType createCodeDefinitionPropertyType() {
        return new CodeDefinitionPropertyType();
    }

    /**
     * Create an instance of {@link CodeListDictionaryPropertyType }
     * 
     * @return
     *     the new instance of {@link CodeListDictionaryPropertyType }
     */
    public CodeListDictionaryPropertyType createCodeListDictionaryPropertyType() {
        return new CodeListDictionaryPropertyType();
    }

    /**
     * Create an instance of {@link MLCodeDefinitionPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCodeDefinitionPropertyType }
     */
    public MLCodeDefinitionPropertyType createMLCodeDefinitionPropertyType() {
        return new MLCodeDefinitionPropertyType();
    }

    /**
     * Create an instance of {@link MLCodeListDictionaryPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCodeListDictionaryPropertyType }
     */
    public MLCodeListDictionaryPropertyType createMLCodeListDictionaryPropertyType() {
        return new MLCodeListDictionaryPropertyType();
    }

    /**
     * Create an instance of {@link ClAlternativeExpressionPropertyType }
     * 
     * @return
     *     the new instance of {@link ClAlternativeExpressionPropertyType }
     */
    public ClAlternativeExpressionPropertyType createClAlternativeExpressionPropertyType() {
        return new ClAlternativeExpressionPropertyType();
    }

    /**
     * Create an instance of {@link CodeAlternativeExpressionPropertyType }
     * 
     * @return
     *     the new instance of {@link CodeAlternativeExpressionPropertyType }
     */
    public CodeAlternativeExpressionPropertyType createCodeAlternativeExpressionPropertyType() {
        return new CodeAlternativeExpressionPropertyType();
    }

    /**
     * Create an instance of {@link CTCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link CTCRSPropertyType }
     */
    public CTCRSPropertyType createCTCRSPropertyType() {
        return new CTCRSPropertyType();
    }

    /**
     * Create an instance of {@link CTCoordinateSystemPropertyType }
     * 
     * @return
     *     the new instance of {@link CTCoordinateSystemPropertyType }
     */
    public CTCoordinateSystemPropertyType createCTCoordinateSystemPropertyType() {
        return new CTCoordinateSystemPropertyType();
    }

    /**
     * Create an instance of {@link CTCoordinateSystemAxisPropertyType }
     * 
     * @return
     *     the new instance of {@link CTCoordinateSystemAxisPropertyType }
     */
    public CTCoordinateSystemAxisPropertyType createCTCoordinateSystemAxisPropertyType() {
        return new CTCoordinateSystemAxisPropertyType();
    }

    /**
     * Create an instance of {@link CTDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link CTDatumPropertyType }
     */
    public CTDatumPropertyType createCTDatumPropertyType() {
        return new CTDatumPropertyType();
    }

    /**
     * Create an instance of {@link CTEllipsoidPropertyType }
     * 
     * @return
     *     the new instance of {@link CTEllipsoidPropertyType }
     */
    public CTEllipsoidPropertyType createCTEllipsoidPropertyType() {
        return new CTEllipsoidPropertyType();
    }

    /**
     * Create an instance of {@link CTPrimeMeridianPropertyType }
     * 
     * @return
     *     the new instance of {@link CTPrimeMeridianPropertyType }
     */
    public CTPrimeMeridianPropertyType createCTPrimeMeridianPropertyType() {
        return new CTPrimeMeridianPropertyType();
    }

    /**
     * Create an instance of {@link CTOperationPropertyType }
     * 
     * @return
     *     the new instance of {@link CTOperationPropertyType }
     */
    public CTOperationPropertyType createCTOperationPropertyType() {
        return new CTOperationPropertyType();
    }

    /**
     * Create an instance of {@link CTOperationMethodPropertyType }
     * 
     * @return
     *     the new instance of {@link CTOperationMethodPropertyType }
     */
    public CTOperationMethodPropertyType createCTOperationMethodPropertyType() {
        return new CTOperationMethodPropertyType();
    }

    /**
     * Create an instance of {@link CTOperationParametersPropertyType }
     * 
     * @return
     *     the new instance of {@link CTOperationParametersPropertyType }
     */
    public CTOperationParametersPropertyType createCTOperationParametersPropertyType() {
        return new CTOperationParametersPropertyType();
    }

    /**
     * Create an instance of {@link MLCompoundCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCompoundCRSPropertyType }
     */
    public MLCompoundCRSPropertyType createMLCompoundCRSPropertyType() {
        return new MLCompoundCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLGeodeticCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLGeodeticCRSPropertyType }
     */
    public MLGeodeticCRSPropertyType createMLGeodeticCRSPropertyType() {
        return new MLGeodeticCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLEngineeringCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLEngineeringCRSPropertyType }
     */
    public MLEngineeringCRSPropertyType createMLEngineeringCRSPropertyType() {
        return new MLEngineeringCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLVerticalCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLVerticalCRSPropertyType }
     */
    public MLVerticalCRSPropertyType createMLVerticalCRSPropertyType() {
        return new MLVerticalCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLTemporalCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLTemporalCRSPropertyType }
     */
    public MLTemporalCRSPropertyType createMLTemporalCRSPropertyType() {
        return new MLTemporalCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLImageCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLImageCRSPropertyType }
     */
    public MLImageCRSPropertyType createMLImageCRSPropertyType() {
        return new MLImageCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLProjectedCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLProjectedCRSPropertyType }
     */
    public MLProjectedCRSPropertyType createMLProjectedCRSPropertyType() {
        return new MLProjectedCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLDerivedCRSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLDerivedCRSPropertyType }
     */
    public MLDerivedCRSPropertyType createMLDerivedCRSPropertyType() {
        return new MLDerivedCRSPropertyType();
    }

    /**
     * Create an instance of {@link MLCoordinateSystemAxisPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCoordinateSystemAxisPropertyType }
     */
    public MLCoordinateSystemAxisPropertyType createMLCoordinateSystemAxisPropertyType() {
        return new MLCoordinateSystemAxisPropertyType();
    }

    /**
     * Create an instance of {@link MLEllipsoidalCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLEllipsoidalCSPropertyType }
     */
    public MLEllipsoidalCSPropertyType createMLEllipsoidalCSPropertyType() {
        return new MLEllipsoidalCSPropertyType();
    }

    /**
     * Create an instance of {@link MLCartesianCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCartesianCSPropertyType }
     */
    public MLCartesianCSPropertyType createMLCartesianCSPropertyType() {
        return new MLCartesianCSPropertyType();
    }

    /**
     * Create an instance of {@link MLAffineCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLAffineCSPropertyType }
     */
    public MLAffineCSPropertyType createMLAffineCSPropertyType() {
        return new MLAffineCSPropertyType();
    }

    /**
     * Create an instance of {@link MLUserDefinedCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLUserDefinedCSPropertyType }
     */
    public MLUserDefinedCSPropertyType createMLUserDefinedCSPropertyType() {
        return new MLUserDefinedCSPropertyType();
    }

    /**
     * Create an instance of {@link MLVerticalCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLVerticalCSPropertyType }
     */
    public MLVerticalCSPropertyType createMLVerticalCSPropertyType() {
        return new MLVerticalCSPropertyType();
    }

    /**
     * Create an instance of {@link MLTimeCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLTimeCSPropertyType }
     */
    public MLTimeCSPropertyType createMLTimeCSPropertyType() {
        return new MLTimeCSPropertyType();
    }

    /**
     * Create an instance of {@link MLCylindricalCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLCylindricalCSPropertyType }
     */
    public MLCylindricalCSPropertyType createMLCylindricalCSPropertyType() {
        return new MLCylindricalCSPropertyType();
    }

    /**
     * Create an instance of {@link MLSphericalCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLSphericalCSPropertyType }
     */
    public MLSphericalCSPropertyType createMLSphericalCSPropertyType() {
        return new MLSphericalCSPropertyType();
    }

    /**
     * Create an instance of {@link MLPolarCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLPolarCSPropertyType }
     */
    public MLPolarCSPropertyType createMLPolarCSPropertyType() {
        return new MLPolarCSPropertyType();
    }

    /**
     * Create an instance of {@link MLLinearCSPropertyType }
     * 
     * @return
     *     the new instance of {@link MLLinearCSPropertyType }
     */
    public MLLinearCSPropertyType createMLLinearCSPropertyType() {
        return new MLLinearCSPropertyType();
    }

    /**
     * Create an instance of {@link MLEllipsoidPropertyType }
     * 
     * @return
     *     the new instance of {@link MLEllipsoidPropertyType }
     */
    public MLEllipsoidPropertyType createMLEllipsoidPropertyType() {
        return new MLEllipsoidPropertyType();
    }

    /**
     * Create an instance of {@link MLPrimeMeridianPropertyType }
     * 
     * @return
     *     the new instance of {@link MLPrimeMeridianPropertyType }
     */
    public MLPrimeMeridianPropertyType createMLPrimeMeridianPropertyType() {
        return new MLPrimeMeridianPropertyType();
    }

    /**
     * Create an instance of {@link MLTemporalDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link MLTemporalDatumPropertyType }
     */
    public MLTemporalDatumPropertyType createMLTemporalDatumPropertyType() {
        return new MLTemporalDatumPropertyType();
    }

    /**
     * Create an instance of {@link MLVerticalDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link MLVerticalDatumPropertyType }
     */
    public MLVerticalDatumPropertyType createMLVerticalDatumPropertyType() {
        return new MLVerticalDatumPropertyType();
    }

    /**
     * Create an instance of {@link MLImageDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link MLImageDatumPropertyType }
     */
    public MLImageDatumPropertyType createMLImageDatumPropertyType() {
        return new MLImageDatumPropertyType();
    }

    /**
     * Create an instance of {@link MLEngineeringDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link MLEngineeringDatumPropertyType }
     */
    public MLEngineeringDatumPropertyType createMLEngineeringDatumPropertyType() {
        return new MLEngineeringDatumPropertyType();
    }

    /**
     * Create an instance of {@link MLGeodeticDatumPropertyType }
     * 
     * @return
     *     the new instance of {@link MLGeodeticDatumPropertyType }
     */
    public MLGeodeticDatumPropertyType createMLGeodeticDatumPropertyType() {
        return new MLGeodeticDatumPropertyType();
    }

    /**
     * Create an instance of {@link MLConcatenatedOperationPropertyType }
     * 
     * @return
     *     the new instance of {@link MLConcatenatedOperationPropertyType }
     */
    public MLConcatenatedOperationPropertyType createMLConcatenatedOperationPropertyType() {
        return new MLConcatenatedOperationPropertyType();
    }

    /**
     * Create an instance of {@link MLPassThroughOperationPropertyType }
     * 
     * @return
     *     the new instance of {@link MLPassThroughOperationPropertyType }
     */
    public MLPassThroughOperationPropertyType createMLPassThroughOperationPropertyType() {
        return new MLPassThroughOperationPropertyType();
    }

    /**
     * Create an instance of {@link MLTransformationPropertyType }
     * 
     * @return
     *     the new instance of {@link MLTransformationPropertyType }
     */
    public MLTransformationPropertyType createMLTransformationPropertyType() {
        return new MLTransformationPropertyType();
    }

    /**
     * Create an instance of {@link MLConversionPropertyType }
     * 
     * @return
     *     the new instance of {@link MLConversionPropertyType }
     */
    public MLConversionPropertyType createMLConversionPropertyType() {
        return new MLConversionPropertyType();
    }

    /**
     * Create an instance of {@link MLOperationMethodPropertyType }
     * 
     * @return
     *     the new instance of {@link MLOperationMethodPropertyType }
     */
    public MLOperationMethodPropertyType createMLOperationMethodPropertyType() {
        return new MLOperationMethodPropertyType();
    }

    /**
     * Create an instance of {@link MLOperationParameterGroupPropertyType }
     * 
     * @return
     *     the new instance of {@link MLOperationParameterGroupPropertyType }
     */
    public MLOperationParameterGroupPropertyType createMLOperationParameterGroupPropertyType() {
        return new MLOperationParameterGroupPropertyType();
    }

    /**
     * Create an instance of {@link MLOperationParameterPropertyType }
     * 
     * @return
     *     the new instance of {@link MLOperationParameterPropertyType }
     */
    public MLOperationParameterPropertyType createMLOperationParameterPropertyType() {
        return new MLOperationParameterPropertyType();
    }

    /**
     * Create an instance of {@link CrsAltPropertyType }
     * 
     * @return
     *     the new instance of {@link CrsAltPropertyType }
     */
    public CrsAltPropertyType createCrsAltPropertyType() {
        return new CrsAltPropertyType();
    }

    /**
     * Create an instance of {@link CoordinateSystemAltPropertyType }
     * 
     * @return
     *     the new instance of {@link CoordinateSystemAltPropertyType }
     */
    public CoordinateSystemAltPropertyType createCoordinateSystemAltPropertyType() {
        return new CoordinateSystemAltPropertyType();
    }

    /**
     * Create an instance of {@link CoordinateSystemAxisAltPropertyType }
     * 
     * @return
     *     the new instance of {@link CoordinateSystemAxisAltPropertyType }
     */
    public CoordinateSystemAxisAltPropertyType createCoordinateSystemAxisAltPropertyType() {
        return new CoordinateSystemAxisAltPropertyType();
    }

    /**
     * Create an instance of {@link DatumAltPropertyType }
     * 
     * @return
     *     the new instance of {@link DatumAltPropertyType }
     */
    public DatumAltPropertyType createDatumAltPropertyType() {
        return new DatumAltPropertyType();
    }

    /**
     * Create an instance of {@link EllipsoidAltPropertyType }
     * 
     * @return
     *     the new instance of {@link EllipsoidAltPropertyType }
     */
    public EllipsoidAltPropertyType createEllipsoidAltPropertyType() {
        return new EllipsoidAltPropertyType();
    }

    /**
     * Create an instance of {@link PrimeMeridianAltPropertyType }
     * 
     * @return
     *     the new instance of {@link PrimeMeridianAltPropertyType }
     */
    public PrimeMeridianAltPropertyType createPrimeMeridianAltPropertyType() {
        return new PrimeMeridianAltPropertyType();
    }

    /**
     * Create an instance of {@link OperationAltPropertyType }
     * 
     * @return
     *     the new instance of {@link OperationAltPropertyType }
     */
    public OperationAltPropertyType createOperationAltPropertyType() {
        return new OperationAltPropertyType();
    }

    /**
     * Create an instance of {@link OperationMethodAltPropertyType }
     * 
     * @return
     *     the new instance of {@link OperationMethodAltPropertyType }
     */
    public OperationMethodAltPropertyType createOperationMethodAltPropertyType() {
        return new OperationMethodAltPropertyType();
    }

    /**
     * Create an instance of {@link OperationParameterAltPropertyType }
     * 
     * @return
     *     the new instance of {@link OperationParameterAltPropertyType }
     */
    public OperationParameterAltPropertyType createOperationParameterAltPropertyType() {
        return new OperationParameterAltPropertyType();
    }

    /**
     * Create an instance of {@link CTCataloguePropertyType }
     * 
     * @return
     *     the new instance of {@link CTCataloguePropertyType }
     */
    public CTCataloguePropertyType createCTCataloguePropertyType() {
        return new CTCataloguePropertyType();
    }

    /**
     * Create an instance of {@link CTCodelistCataloguePropertyType }
     * 
     * @return
     *     the new instance of {@link CTCodelistCataloguePropertyType }
     */
    public CTCodelistCataloguePropertyType createCTCodelistCataloguePropertyType() {
        return new CTCodelistCataloguePropertyType();
    }

    /**
     * Create an instance of {@link CTCrsCataloguePropertyType }
     * 
     * @return
     *     the new instance of {@link CTCrsCataloguePropertyType }
     */
    public CTCrsCataloguePropertyType createCTCrsCataloguePropertyType() {
        return new CTCrsCataloguePropertyType();
    }

    /**
     * Create an instance of {@link CTUomCataloguePropertyType }
     * 
     * @return
     *     the new instance of {@link CTUomCataloguePropertyType }
     */
    public CTUomCataloguePropertyType createCTUomCataloguePropertyType() {
        return new CTUomCataloguePropertyType();
    }

    /**
     * Create an instance of {@link FileNamePropertyType }
     * 
     * @return
     *     the new instance of {@link FileNamePropertyType }
     */
    public FileNamePropertyType createFileNamePropertyType() {
        return new FileNamePropertyType();
    }

    /**
     * Create an instance of {@link MimeFileTypePropertyType }
     * 
     * @return
     *     the new instance of {@link MimeFileTypePropertyType }
     */
    public MimeFileTypePropertyType createMimeFileTypePropertyType() {
        return new MimeFileTypePropertyType();
    }

    /**
     * Create an instance of {@link AnchorPropertyType }
     * 
     * @return
     *     the new instance of {@link AnchorPropertyType }
     */
    public AnchorPropertyType createAnchorPropertyType() {
        return new AnchorPropertyType();
    }

    /**
     * Create an instance of {@link MXAggregatePropertyType }
     * 
     * @return
     *     the new instance of {@link MXAggregatePropertyType }
     */
    public MXAggregatePropertyType createMXAggregatePropertyType() {
        return new MXAggregatePropertyType();
    }

    /**
     * Create an instance of {@link MXDataSetPropertyType }
     * 
     * @return
     *     the new instance of {@link MXDataSetPropertyType }
     */
    public MXDataSetPropertyType createMXDataSetPropertyType() {
        return new MXDataSetPropertyType();
    }

    /**
     * Create an instance of {@link MXDataFilePropertyType }
     * 
     * @return
     *     the new instance of {@link MXDataFilePropertyType }
     */
    public MXDataFilePropertyType createMXDataFilePropertyType() {
        return new MXDataFilePropertyType();
    }

    /**
     * Create an instance of {@link MXSupportFilePropertyType }
     * 
     * @return
     *     the new instance of {@link MXSupportFilePropertyType }
     */
    public MXSupportFilePropertyType createMXSupportFilePropertyType() {
        return new MXSupportFilePropertyType();
    }

    /**
     * Create an instance of {@link MXFilePropertyType }
     * 
     * @return
     *     the new instance of {@link MXFilePropertyType }
     */
    public MXFilePropertyType createMXFilePropertyType() {
        return new MXFilePropertyType();
    }

    /**
     * Create an instance of {@link MXScopeCodePropertyType }
     * 
     * @return
     *     the new instance of {@link MXScopeCodePropertyType }
     */
    public MXScopeCodePropertyType createMXScopeCodePropertyType() {
        return new MXScopeCodePropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLBaseUnitType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLBaseUnitType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_BaseUnit", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "BaseUnit")
    public JAXBElement<MLBaseUnitType> createMLBaseUnit(MLBaseUnitType value) {
        return new JAXBElement<>(_MLBaseUnit_QNAME, MLBaseUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLDerivedUnitType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLDerivedUnitType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_DerivedUnit", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "DerivedUnit")
    public JAXBElement<MLDerivedUnitType> createMLDerivedUnit(MLDerivedUnitType value) {
        return new JAXBElement<>(_MLDerivedUnit_QNAME, MLDerivedUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLConventionalUnitType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLConventionalUnitType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_ConventionalUnit", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "ConventionalUnit")
    public JAXBElement<MLConventionalUnitType> createMLConventionalUnit(MLConventionalUnitType value) {
        return new JAXBElement<>(_MLConventionalUnit_QNAME, MLConventionalUnitType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLUnitDefinitionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLUnitDefinitionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_UnitDefinition", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "UnitDefinition")
    public JAXBElement<MLUnitDefinitionType> createMLUnitDefinition(MLUnitDefinitionType value) {
        return new JAXBElement<>(_MLUnitDefinition_QNAME, MLUnitDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UomAlternativeExpressionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UomAlternativeExpressionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "UomAlternativeExpression")
    public JAXBElement<UomAlternativeExpressionType> createUomAlternativeExpression(UomAlternativeExpressionType value) {
        return new JAXBElement<>(_UomAlternativeExpression_QNAME, UomAlternativeExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeDefinitionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeDefinitionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CodeDefinition", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "Definition")
    public JAXBElement<CodeDefinitionType> createCodeDefinition(CodeDefinitionType value) {
        return new JAXBElement<>(_CodeDefinition_QNAME, CodeDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListDictionaryType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeListDictionaryType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CodeListDictionary", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "Dictionary")
    public JAXBElement<CodeListDictionaryType> createCodeListDictionary(CodeListDictionaryType value) {
        return new JAXBElement<>(_CodeListDictionary_QNAME, CodeListDictionaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCodeDefinitionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCodeDefinitionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CodeDefinition", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "CodeDefinition")
    public JAXBElement<MLCodeDefinitionType> createMLCodeDefinition(MLCodeDefinitionType value) {
        return new JAXBElement<>(_MLCodeDefinition_QNAME, MLCodeDefinitionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCodeListDictionaryType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCodeListDictionaryType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CodeListDictionary", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "CodeListDictionary")
    public JAXBElement<MLCodeListDictionaryType> createMLCodeListDictionary(MLCodeListDictionaryType value) {
        return new JAXBElement<>(_MLCodeListDictionary_QNAME, MLCodeListDictionaryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClAlternativeExpressionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ClAlternativeExpressionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ClAlternativeExpression")
    public JAXBElement<ClAlternativeExpressionType> createClAlternativeExpression(ClAlternativeExpressionType value) {
        return new JAXBElement<>(_ClAlternativeExpression_QNAME, ClAlternativeExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeAlternativeExpressionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeAlternativeExpressionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CodeAlternativeExpression")
    public JAXBElement<CodeAlternativeExpressionType> createCodeAlternativeExpression(CodeAlternativeExpressionType value) {
        return new JAXBElement<>(_CodeAlternativeExpression_QNAME, CodeAlternativeExpressionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCompoundCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCompoundCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CompoundCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "CompoundCRS")
    public JAXBElement<MLCompoundCRSType> createMLCompoundCRS(MLCompoundCRSType value) {
        return new JAXBElement<>(_MLCompoundCRS_QNAME, MLCompoundCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLGeodeticCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLGeodeticCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_GeodeticCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "GeodeticCRS")
    public JAXBElement<MLGeodeticCRSType> createMLGeodeticCRS(MLGeodeticCRSType value) {
        return new JAXBElement<>(_MLGeodeticCRS_QNAME, MLGeodeticCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLEngineeringCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLEngineeringCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_EngineeringCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "EngineeringCRS")
    public JAXBElement<MLEngineeringCRSType> createMLEngineeringCRS(MLEngineeringCRSType value) {
        return new JAXBElement<>(_MLEngineeringCRS_QNAME, MLEngineeringCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLVerticalCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLVerticalCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_VerticalCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "VerticalCRS")
    public JAXBElement<MLVerticalCRSType> createMLVerticalCRS(MLVerticalCRSType value) {
        return new JAXBElement<>(_MLVerticalCRS_QNAME, MLVerticalCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLTemporalCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLTemporalCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_TemporalCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "TemporalCRS")
    public JAXBElement<MLTemporalCRSType> createMLTemporalCRS(MLTemporalCRSType value) {
        return new JAXBElement<>(_MLTemporalCRS_QNAME, MLTemporalCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLImageCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLImageCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_ImageCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "ImageCRS")
    public JAXBElement<MLImageCRSType> createMLImageCRS(MLImageCRSType value) {
        return new JAXBElement<>(_MLImageCRS_QNAME, MLImageCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLProjectedCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLProjectedCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_ProjectedCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "ProjectedCRS")
    public JAXBElement<MLProjectedCRSType> createMLProjectedCRS(MLProjectedCRSType value) {
        return new JAXBElement<>(_MLProjectedCRS_QNAME, MLProjectedCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLDerivedCRSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLDerivedCRSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_DerivedCRS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "DerivedCRS")
    public JAXBElement<MLDerivedCRSType> createMLDerivedCRS(MLDerivedCRSType value) {
        return new JAXBElement<>(_MLDerivedCRS_QNAME, MLDerivedCRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCoordinateSystemAxisType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCoordinateSystemAxisType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CoordinateSystemAxis", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "CoordinateSystemAxis")
    public JAXBElement<MLCoordinateSystemAxisType> createMLCoordinateSystemAxis(MLCoordinateSystemAxisType value) {
        return new JAXBElement<>(_MLCoordinateSystemAxis_QNAME, MLCoordinateSystemAxisType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLEllipsoidalCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLEllipsoidalCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_EllipsoidalCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "EllipsoidalCS")
    public JAXBElement<MLEllipsoidalCSType> createMLEllipsoidalCS(MLEllipsoidalCSType value) {
        return new JAXBElement<>(_MLEllipsoidalCS_QNAME, MLEllipsoidalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCartesianCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCartesianCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CartesianCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "CartesianCS")
    public JAXBElement<MLCartesianCSType> createMLCartesianCS(MLCartesianCSType value) {
        return new JAXBElement<>(_MLCartesianCS_QNAME, MLCartesianCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLAffineCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLAffineCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_AffineCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "AffineCS")
    public JAXBElement<MLAffineCSType> createMLAffineCS(MLAffineCSType value) {
        return new JAXBElement<>(_MLAffineCS_QNAME, MLAffineCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLUserDefinedCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLUserDefinedCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_UserDefinedCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "UserDefinedCS")
    public JAXBElement<MLUserDefinedCSType> createMLUserDefinedCS(MLUserDefinedCSType value) {
        return new JAXBElement<>(_MLUserDefinedCS_QNAME, MLUserDefinedCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLVerticalCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLVerticalCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_VerticalCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "VerticalCS")
    public JAXBElement<MLVerticalCSType> createMLVerticalCS(MLVerticalCSType value) {
        return new JAXBElement<>(_MLVerticalCS_QNAME, MLVerticalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLTimeCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLTimeCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_TimeCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "TimeCS")
    public JAXBElement<MLTimeCSType> createMLTimeCS(MLTimeCSType value) {
        return new JAXBElement<>(_MLTimeCS_QNAME, MLTimeCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLCylindricalCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLCylindricalCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_CylindricalCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "CylindricalCS")
    public JAXBElement<MLCylindricalCSType> createMLCylindricalCS(MLCylindricalCSType value) {
        return new JAXBElement<>(_MLCylindricalCS_QNAME, MLCylindricalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLSphericalCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLSphericalCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_SphericalCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "SphericalCS")
    public JAXBElement<MLSphericalCSType> createMLSphericalCS(MLSphericalCSType value) {
        return new JAXBElement<>(_MLSphericalCS_QNAME, MLSphericalCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLPolarCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLPolarCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_PolarCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "PolarCS")
    public JAXBElement<MLPolarCSType> createMLPolarCS(MLPolarCSType value) {
        return new JAXBElement<>(_MLPolarCS_QNAME, MLPolarCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLLinearCSType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLLinearCSType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_LinearCS", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "LinearCS")
    public JAXBElement<MLLinearCSType> createMLLinearCS(MLLinearCSType value) {
        return new JAXBElement<>(_MLLinearCS_QNAME, MLLinearCSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLEllipsoidType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLEllipsoidType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_Ellipsoid", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "Ellipsoid")
    public JAXBElement<MLEllipsoidType> createMLEllipsoid(MLEllipsoidType value) {
        return new JAXBElement<>(_MLEllipsoid_QNAME, MLEllipsoidType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLPrimeMeridianType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLPrimeMeridianType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_PrimeMeridian", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "PrimeMeridian")
    public JAXBElement<MLPrimeMeridianType> createMLPrimeMeridian(MLPrimeMeridianType value) {
        return new JAXBElement<>(_MLPrimeMeridian_QNAME, MLPrimeMeridianType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLTemporalDatumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLTemporalDatumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_TemporalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "TemporalDatum")
    public JAXBElement<MLTemporalDatumType> createMLTemporalDatum(MLTemporalDatumType value) {
        return new JAXBElement<>(_MLTemporalDatum_QNAME, MLTemporalDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLVerticalDatumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLVerticalDatumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_VerticalDatum", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "VerticalDatum")
    public JAXBElement<MLVerticalDatumType> createMLVerticalDatum(MLVerticalDatumType value) {
        return new JAXBElement<>(_MLVerticalDatum_QNAME, MLVerticalDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLImageDatumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLImageDatumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_ImageDatum", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "ImageDatum")
    public JAXBElement<MLImageDatumType> createMLImageDatum(MLImageDatumType value) {
        return new JAXBElement<>(_MLImageDatum_QNAME, MLImageDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLEngineeringDatumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLEngineeringDatumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_EngineeringDatum", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "EngineeringDatum")
    public JAXBElement<MLEngineeringDatumType> createMLEngineeringDatum(MLEngineeringDatumType value) {
        return new JAXBElement<>(_MLEngineeringDatum_QNAME, MLEngineeringDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLGeodeticDatumType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLGeodeticDatumType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_GeodeticDatum", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "GeodeticDatum")
    public JAXBElement<MLGeodeticDatumType> createMLGeodeticDatum(MLGeodeticDatumType value) {
        return new JAXBElement<>(_MLGeodeticDatum_QNAME, MLGeodeticDatumType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLConcatenatedOperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLConcatenatedOperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_ConcatenatedOperation", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "ConcatenatedOperation")
    public JAXBElement<MLConcatenatedOperationType> createMLConcatenatedOperation(MLConcatenatedOperationType value) {
        return new JAXBElement<>(_MLConcatenatedOperation_QNAME, MLConcatenatedOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLPassThroughOperationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLPassThroughOperationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_PassThroughOperation", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "PassThroughOperation")
    public JAXBElement<MLPassThroughOperationType> createMLPassThroughOperation(MLPassThroughOperationType value) {
        return new JAXBElement<>(_MLPassThroughOperation_QNAME, MLPassThroughOperationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLTransformationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLTransformationType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_Transformation", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "Transformation")
    public JAXBElement<MLTransformationType> createMLTransformation(MLTransformationType value) {
        return new JAXBElement<>(_MLTransformation_QNAME, MLTransformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLConversionType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLConversionType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_Conversion", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "Conversion")
    public JAXBElement<MLConversionType> createMLConversion(MLConversionType value) {
        return new JAXBElement<>(_MLConversion_QNAME, MLConversionType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLOperationMethodType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLOperationMethodType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_OperationMethod", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "OperationMethod")
    public JAXBElement<MLOperationMethodType> createMLOperationMethod(MLOperationMethodType value) {
        return new JAXBElement<>(_MLOperationMethod_QNAME, MLOperationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLOperationParameterGroupType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLOperationParameterGroupType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_OperationParameterGroup", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "OperationParameterGroup")
    public JAXBElement<MLOperationParameterGroupType> createMLOperationParameterGroup(MLOperationParameterGroupType value) {
        return new JAXBElement<>(_MLOperationParameterGroup_QNAME, MLOperationParameterGroupType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MLOperationParameterType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MLOperationParameterType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "ML_OperationParameter", substitutionHeadNamespace = "http://www.opengis.net/gml/3.2", substitutionHeadName = "OperationParameter")
    public JAXBElement<MLOperationParameterType> createMLOperationParameter(MLOperationParameterType value) {
        return new JAXBElement<>(_MLOperationParameter_QNAME, MLOperationParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrsAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CrsAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CrsAlt")
    public JAXBElement<CrsAltType> createCrsAlt(CrsAltType value) {
        return new JAXBElement<>(_CrsAlt_QNAME, CrsAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CoordinateSystemAlt")
    public JAXBElement<CoordinateSystemAltType> createCoordinateSystemAlt(CoordinateSystemAltType value) {
        return new JAXBElement<>(_CoordinateSystemAlt_QNAME, CoordinateSystemAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CoordinateSystemAxisAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CoordinateSystemAxisAlt")
    public JAXBElement<CoordinateSystemAxisAltType> createCoordinateSystemAxisAlt(CoordinateSystemAxisAltType value) {
        return new JAXBElement<>(_CoordinateSystemAxisAlt_QNAME, CoordinateSystemAxisAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatumAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatumAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "DatumAlt")
    public JAXBElement<DatumAltType> createDatumAlt(DatumAltType value) {
        return new JAXBElement<>(_DatumAlt_QNAME, DatumAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EllipsoidAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EllipsoidAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "EllipsoidAlt")
    public JAXBElement<EllipsoidAltType> createEllipsoidAlt(EllipsoidAltType value) {
        return new JAXBElement<>(_EllipsoidAlt_QNAME, EllipsoidAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrimeMeridianAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PrimeMeridianAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "PrimeMeridianAlt")
    public JAXBElement<PrimeMeridianAltType> createPrimeMeridianAlt(PrimeMeridianAltType value) {
        return new JAXBElement<>(_PrimeMeridianAlt_QNAME, PrimeMeridianAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "OperationAlt")
    public JAXBElement<OperationAltType> createOperationAlt(OperationAltType value) {
        return new JAXBElement<>(_OperationAlt_QNAME, OperationAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationMethodAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationMethodAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "OperationMethodAlt")
    public JAXBElement<OperationMethodAltType> createOperationMethodAlt(OperationMethodAltType value) {
        return new JAXBElement<>(_OperationMethodAlt_QNAME, OperationMethodAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OperationParameterAltType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OperationParameterAltType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "OperationParameterAlt")
    public JAXBElement<OperationParameterAltType> createOperationParameterAlt(OperationParameterAltType value) {
        return new JAXBElement<>(_OperationParameterAlt_QNAME, OperationParameterAltType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractCTCatalogueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractCTCatalogueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "AbstractCT_Catalogue")
    public JAXBElement<AbstractCTCatalogueType> createAbstractCTCatalogue(AbstractCTCatalogueType value) {
        return new JAXBElement<>(_AbstractCTCatalogue_QNAME, AbstractCTCatalogueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CTCodelistCatalogueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CTCodelistCatalogueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CT_CodelistCatalogue", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractCT_Catalogue")
    public JAXBElement<CTCodelistCatalogueType> createCTCodelistCatalogue(CTCodelistCatalogueType value) {
        return new JAXBElement<>(_CTCodelistCatalogue_QNAME, CTCodelistCatalogueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CTCrsCatalogueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CTCrsCatalogueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CT_CrsCatalogue", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractCT_Catalogue")
    public JAXBElement<CTCrsCatalogueType> createCTCrsCatalogue(CTCrsCatalogueType value) {
        return new JAXBElement<>(_CTCrsCatalogue_QNAME, CTCrsCatalogueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CTUomCatalogueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CTUomCatalogueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "CT_UomCatalogue", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractCT_Catalogue")
    public JAXBElement<CTUomCatalogueType> createCTUomCatalogue(CTUomCatalogueType value) {
        return new JAXBElement<>(_CTUomCatalogue_QNAME, CTUomCatalogueType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileNameType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FileNameType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "FileName", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<FileNameType> createFileName(FileNameType value) {
        return new JAXBElement<>(_FileName_QNAME, FileNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MimeFileTypeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MimeFileTypeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MimeFileType", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<MimeFileTypeType> createMimeFileType(MimeFileTypeType value) {
        return new JAXBElement<>(_MimeFileType_QNAME, MimeFileTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnchorType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AnchorType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "Anchor", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "CharacterString")
    public JAXBElement<AnchorType> createAnchor(AnchorType value) {
        return new JAXBElement<>(_Anchor_QNAME, AnchorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MXAggregateType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MXAggregateType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MX_Aggregate", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmd", substitutionHeadName = "AbstractDS_Aggregate")
    public JAXBElement<MXAggregateType> createMXAggregate(MXAggregateType value) {
        return new JAXBElement<>(_MXAggregate_QNAME, MXAggregateType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MXDataSetType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MXDataSetType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MX_DataSet", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmd", substitutionHeadName = "DS_DataSet")
    public JAXBElement<MXDataSetType> createMXDataSet(MXDataSetType value) {
        return new JAXBElement<>(_MXDataSet_QNAME, MXDataSetType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MXDataFileType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MXDataFileType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MX_DataFile", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractMX_File")
    public JAXBElement<MXDataFileType> createMXDataFile(MXDataFileType value) {
        return new JAXBElement<>(_MXDataFile_QNAME, MXDataFileType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractMXFileType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractMXFileType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "AbstractMX_File")
    public JAXBElement<AbstractMXFileType> createAbstractMXFile(AbstractMXFileType value) {
        return new JAXBElement<>(_AbstractMXFile_QNAME, AbstractMXFileType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MXSupportFileType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MXSupportFileType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MX_SupportFile", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmx", substitutionHeadName = "AbstractMX_File")
    public JAXBElement<MXSupportFileType> createMXSupportFile(MXSupportFileType value) {
        return new JAXBElement<>(_MXSupportFile_QNAME, MXSupportFileType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeListValueType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gmx", name = "MX_ScopeCode", substitutionHeadNamespace = "http://www.isotc211.org/2005/gmd", substitutionHeadName = "MD_ScopeCode")
    public JAXBElement<CodeListValueType> createMXScopeCode(CodeListValueType value) {
        return new JAXBElement<>(_MXScopeCode_QNAME, CodeListValueType.class, null, value);
    }

}
