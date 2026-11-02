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

package org.geosdi.geoplatform.xml.iso19139.v20070417.gco;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import org.geosdi.geoplatform.xml.gml.v321.AngleType;
import org.geosdi.geoplatform.xml.gml.v321.CodeType;
import org.geosdi.geoplatform.xml.gml.v321.LengthType;
import org.geosdi.geoplatform.xml.gml.v321.MeasureType;
import org.geosdi.geoplatform.xml.gml.v321.ScaleType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.geosdi.geoplatform.xml.iso19139.v20070417.gco package. 
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

    private static final QName _AbstractObject_QNAME = new QName("http://www.isotc211.org/2005/gco", "AbstractObject");
    private static final QName _TypeName_QNAME = new QName("http://www.isotc211.org/2005/gco", "TypeName");
    private static final QName _MemberName_QNAME = new QName("http://www.isotc211.org/2005/gco", "MemberName");
    private static final QName _Multiplicity_QNAME = new QName("http://www.isotc211.org/2005/gco", "Multiplicity");
    private static final QName _MultiplicityRange_QNAME = new QName("http://www.isotc211.org/2005/gco", "MultiplicityRange");
    private static final QName _Measure_QNAME = new QName("http://www.isotc211.org/2005/gco", "Measure");
    private static final QName _Length_QNAME = new QName("http://www.isotc211.org/2005/gco", "Length");
    private static final QName _Angle_QNAME = new QName("http://www.isotc211.org/2005/gco", "Angle");
    private static final QName _Scale_QNAME = new QName("http://www.isotc211.org/2005/gco", "Scale");
    private static final QName _Distance_QNAME = new QName("http://www.isotc211.org/2005/gco", "Distance");
    private static final QName _CharacterString_QNAME = new QName("http://www.isotc211.org/2005/gco", "CharacterString");
    private static final QName _Boolean_QNAME = new QName("http://www.isotc211.org/2005/gco", "Boolean");
    private static final QName _AbstractGenericName_QNAME = new QName("http://www.isotc211.org/2005/gco", "AbstractGenericName");
    private static final QName _LocalName_QNAME = new QName("http://www.isotc211.org/2005/gco", "LocalName");
    private static final QName _ScopedName_QNAME = new QName("http://www.isotc211.org/2005/gco", "ScopedName");
    private static final QName _DateTime_QNAME = new QName("http://www.isotc211.org/2005/gco", "DateTime");
    private static final QName _Date_QNAME = new QName("http://www.isotc211.org/2005/gco", "Date");
    private static final QName _Decimal_QNAME = new QName("http://www.isotc211.org/2005/gco", "Decimal");
    private static final QName _Real_QNAME = new QName("http://www.isotc211.org/2005/gco", "Real");
    private static final QName _Integer_QNAME = new QName("http://www.isotc211.org/2005/gco", "Integer");
    private static final QName _UnlimitedInteger_QNAME = new QName("http://www.isotc211.org/2005/gco", "UnlimitedInteger");
    private static final QName _Record_QNAME = new QName("http://www.isotc211.org/2005/gco", "Record");
    private static final QName _RecordType_QNAME = new QName("http://www.isotc211.org/2005/gco", "RecordType");
    private static final QName _Binary_QNAME = new QName("http://www.isotc211.org/2005/gco", "Binary");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.geosdi.geoplatform.xml.iso19139.v20070417.gco
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TypeNameType }
     * 
     * @return
     *     the new instance of {@link TypeNameType }
     */
    public TypeNameType createTypeNameType() {
        return new TypeNameType();
    }

    /**
     * Create an instance of {@link MemberNameType }
     * 
     * @return
     *     the new instance of {@link MemberNameType }
     */
    public MemberNameType createMemberNameType() {
        return new MemberNameType();
    }

    /**
     * Create an instance of {@link MultiplicityType }
     * 
     * @return
     *     the new instance of {@link MultiplicityType }
     */
    public MultiplicityType createMultiplicityType() {
        return new MultiplicityType();
    }

    /**
     * Create an instance of {@link MultiplicityRangeType }
     * 
     * @return
     *     the new instance of {@link MultiplicityRangeType }
     */
    public MultiplicityRangeType createMultiplicityRangeType() {
        return new MultiplicityRangeType();
    }

    /**
     * Create an instance of {@link UnlimitedIntegerType }
     * 
     * @return
     *     the new instance of {@link UnlimitedIntegerType }
     */
    public UnlimitedIntegerType createUnlimitedIntegerType() {
        return new UnlimitedIntegerType();
    }

    /**
     * Create an instance of {@link RecordTypeType }
     * 
     * @return
     *     the new instance of {@link RecordTypeType }
     */
    public RecordTypeType createRecordTypeType() {
        return new RecordTypeType();
    }

    /**
     * Create an instance of {@link BinaryType }
     * 
     * @return
     *     the new instance of {@link BinaryType }
     */
    public BinaryType createBinaryType() {
        return new BinaryType();
    }

    /**
     * Create an instance of {@link ObjectReferencePropertyType }
     * 
     * @return
     *     the new instance of {@link ObjectReferencePropertyType }
     */
    public ObjectReferencePropertyType createObjectReferencePropertyType() {
        return new ObjectReferencePropertyType();
    }

    /**
     * Create an instance of {@link CodeListValueType }
     * 
     * @return
     *     the new instance of {@link CodeListValueType }
     */
    public CodeListValueType createCodeListValueType() {
        return new CodeListValueType();
    }

    /**
     * Create an instance of {@link TypeNamePropertyType }
     * 
     * @return
     *     the new instance of {@link TypeNamePropertyType }
     */
    public TypeNamePropertyType createTypeNamePropertyType() {
        return new TypeNamePropertyType();
    }

    /**
     * Create an instance of {@link MemberNamePropertyType }
     * 
     * @return
     *     the new instance of {@link MemberNamePropertyType }
     */
    public MemberNamePropertyType createMemberNamePropertyType() {
        return new MemberNamePropertyType();
    }

    /**
     * Create an instance of {@link MultiplicityPropertyType }
     * 
     * @return
     *     the new instance of {@link MultiplicityPropertyType }
     */
    public MultiplicityPropertyType createMultiplicityPropertyType() {
        return new MultiplicityPropertyType();
    }

    /**
     * Create an instance of {@link MultiplicityRangePropertyType }
     * 
     * @return
     *     the new instance of {@link MultiplicityRangePropertyType }
     */
    public MultiplicityRangePropertyType createMultiplicityRangePropertyType() {
        return new MultiplicityRangePropertyType();
    }

    /**
     * Create an instance of {@link MeasurePropertyType }
     * 
     * @return
     *     the new instance of {@link MeasurePropertyType }
     */
    public MeasurePropertyType createMeasurePropertyType() {
        return new MeasurePropertyType();
    }

    /**
     * Create an instance of {@link LengthPropertyType }
     * 
     * @return
     *     the new instance of {@link LengthPropertyType }
     */
    public LengthPropertyType createLengthPropertyType() {
        return new LengthPropertyType();
    }

    /**
     * Create an instance of {@link AnglePropertyType }
     * 
     * @return
     *     the new instance of {@link AnglePropertyType }
     */
    public AnglePropertyType createAnglePropertyType() {
        return new AnglePropertyType();
    }

    /**
     * Create an instance of {@link ScalePropertyType }
     * 
     * @return
     *     the new instance of {@link ScalePropertyType }
     */
    public ScalePropertyType createScalePropertyType() {
        return new ScalePropertyType();
    }

    /**
     * Create an instance of {@link DistancePropertyType }
     * 
     * @return
     *     the new instance of {@link DistancePropertyType }
     */
    public DistancePropertyType createDistancePropertyType() {
        return new DistancePropertyType();
    }

    /**
     * Create an instance of {@link CharacterStringPropertyType }
     * 
     * @return
     *     the new instance of {@link CharacterStringPropertyType }
     */
    public CharacterStringPropertyType createCharacterStringPropertyType() {
        return new CharacterStringPropertyType();
    }

    /**
     * Create an instance of {@link BooleanPropertyType }
     * 
     * @return
     *     the new instance of {@link BooleanPropertyType }
     */
    public BooleanPropertyType createBooleanPropertyType() {
        return new BooleanPropertyType();
    }

    /**
     * Create an instance of {@link GenericNamePropertyType }
     * 
     * @return
     *     the new instance of {@link GenericNamePropertyType }
     */
    public GenericNamePropertyType createGenericNamePropertyType() {
        return new GenericNamePropertyType();
    }

    /**
     * Create an instance of {@link LocalNamePropertyType }
     * 
     * @return
     *     the new instance of {@link LocalNamePropertyType }
     */
    public LocalNamePropertyType createLocalNamePropertyType() {
        return new LocalNamePropertyType();
    }

    /**
     * Create an instance of {@link ScopedNamePropertyType }
     * 
     * @return
     *     the new instance of {@link ScopedNamePropertyType }
     */
    public ScopedNamePropertyType createScopedNamePropertyType() {
        return new ScopedNamePropertyType();
    }

    /**
     * Create an instance of {@link UomAnglePropertyType }
     * 
     * @return
     *     the new instance of {@link UomAnglePropertyType }
     */
    public UomAnglePropertyType createUomAnglePropertyType() {
        return new UomAnglePropertyType();
    }

    /**
     * Create an instance of {@link UomLengthPropertyType }
     * 
     * @return
     *     the new instance of {@link UomLengthPropertyType }
     */
    public UomLengthPropertyType createUomLengthPropertyType() {
        return new UomLengthPropertyType();
    }

    /**
     * Create an instance of {@link UomScalePropertyType }
     * 
     * @return
     *     the new instance of {@link UomScalePropertyType }
     */
    public UomScalePropertyType createUomScalePropertyType() {
        return new UomScalePropertyType();
    }

    /**
     * Create an instance of {@link UnitOfMeasurePropertyType }
     * 
     * @return
     *     the new instance of {@link UnitOfMeasurePropertyType }
     */
    public UnitOfMeasurePropertyType createUnitOfMeasurePropertyType() {
        return new UnitOfMeasurePropertyType();
    }

    /**
     * Create an instance of {@link UomAreaPropertyType }
     * 
     * @return
     *     the new instance of {@link UomAreaPropertyType }
     */
    public UomAreaPropertyType createUomAreaPropertyType() {
        return new UomAreaPropertyType();
    }

    /**
     * Create an instance of {@link UomVelocityPropertyType }
     * 
     * @return
     *     the new instance of {@link UomVelocityPropertyType }
     */
    public UomVelocityPropertyType createUomVelocityPropertyType() {
        return new UomVelocityPropertyType();
    }

    /**
     * Create an instance of {@link UomTimePropertyType }
     * 
     * @return
     *     the new instance of {@link UomTimePropertyType }
     */
    public UomTimePropertyType createUomTimePropertyType() {
        return new UomTimePropertyType();
    }

    /**
     * Create an instance of {@link UomVolumePropertyType }
     * 
     * @return
     *     the new instance of {@link UomVolumePropertyType }
     */
    public UomVolumePropertyType createUomVolumePropertyType() {
        return new UomVolumePropertyType();
    }

    /**
     * Create an instance of {@link DateTimePropertyType }
     * 
     * @return
     *     the new instance of {@link DateTimePropertyType }
     */
    public DateTimePropertyType createDateTimePropertyType() {
        return new DateTimePropertyType();
    }

    /**
     * Create an instance of {@link DatePropertyType }
     * 
     * @return
     *     the new instance of {@link DatePropertyType }
     */
    public DatePropertyType createDatePropertyType() {
        return new DatePropertyType();
    }

    /**
     * Create an instance of {@link NumberPropertyType }
     * 
     * @return
     *     the new instance of {@link NumberPropertyType }
     */
    public NumberPropertyType createNumberPropertyType() {
        return new NumberPropertyType();
    }

    /**
     * Create an instance of {@link DecimalPropertyType }
     * 
     * @return
     *     the new instance of {@link DecimalPropertyType }
     */
    public DecimalPropertyType createDecimalPropertyType() {
        return new DecimalPropertyType();
    }

    /**
     * Create an instance of {@link RealPropertyType }
     * 
     * @return
     *     the new instance of {@link RealPropertyType }
     */
    public RealPropertyType createRealPropertyType() {
        return new RealPropertyType();
    }

    /**
     * Create an instance of {@link IntegerPropertyType }
     * 
     * @return
     *     the new instance of {@link IntegerPropertyType }
     */
    public IntegerPropertyType createIntegerPropertyType() {
        return new IntegerPropertyType();
    }

    /**
     * Create an instance of {@link UnlimitedIntegerPropertyType }
     * 
     * @return
     *     the new instance of {@link UnlimitedIntegerPropertyType }
     */
    public UnlimitedIntegerPropertyType createUnlimitedIntegerPropertyType() {
        return new UnlimitedIntegerPropertyType();
    }

    /**
     * Create an instance of {@link RecordPropertyType }
     * 
     * @return
     *     the new instance of {@link RecordPropertyType }
     */
    public RecordPropertyType createRecordPropertyType() {
        return new RecordPropertyType();
    }

    /**
     * Create an instance of {@link RecordTypePropertyType }
     * 
     * @return
     *     the new instance of {@link RecordTypePropertyType }
     */
    public RecordTypePropertyType createRecordTypePropertyType() {
        return new RecordTypePropertyType();
    }

    /**
     * Create an instance of {@link BinaryPropertyType }
     * 
     * @return
     *     the new instance of {@link BinaryPropertyType }
     */
    public BinaryPropertyType createBinaryPropertyType() {
        return new BinaryPropertyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AbstractObjectType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "AbstractObject")
    public JAXBElement<AbstractObjectType> createAbstractObject(AbstractObjectType value) {
        return new JAXBElement<>(_AbstractObject_QNAME, AbstractObjectType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TypeNameType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TypeNameType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "TypeName")
    public JAXBElement<TypeNameType> createTypeName(TypeNameType value) {
        return new JAXBElement<>(_TypeName_QNAME, TypeNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MemberNameType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MemberNameType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "MemberName")
    public JAXBElement<MemberNameType> createMemberName(MemberNameType value) {
        return new JAXBElement<>(_MemberName_QNAME, MemberNameType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiplicityType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiplicityType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Multiplicity")
    public JAXBElement<MultiplicityType> createMultiplicity(MultiplicityType value) {
        return new JAXBElement<>(_Multiplicity_QNAME, MultiplicityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiplicityRangeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MultiplicityRangeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "MultiplicityRange")
    public JAXBElement<MultiplicityRangeType> createMultiplicityRange(MultiplicityRangeType value) {
        return new JAXBElement<>(_MultiplicityRange_QNAME, MultiplicityRangeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MeasureType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Measure")
    public JAXBElement<MeasureType> createMeasure(MeasureType value) {
        return new JAXBElement<>(_Measure_QNAME, MeasureType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LengthType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LengthType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Length", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "Measure")
    public JAXBElement<LengthType> createLength(LengthType value) {
        return new JAXBElement<>(_Length_QNAME, LengthType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AngleType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AngleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Angle", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "Measure")
    public JAXBElement<AngleType> createAngle(AngleType value) {
        return new JAXBElement<>(_Angle_QNAME, AngleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ScaleType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ScaleType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Scale", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "Measure")
    public JAXBElement<ScaleType> createScale(ScaleType value) {
        return new JAXBElement<>(_Scale_QNAME, ScaleType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LengthType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LengthType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Distance", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "Length")
    public JAXBElement<LengthType> createDistance(LengthType value) {
        return new JAXBElement<>(_Distance_QNAME, LengthType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "CharacterString")
    public JAXBElement<String> createCharacterString(String value) {
        return new JAXBElement<>(_CharacterString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "AbstractGenericName")
    public JAXBElement<CodeType> createAbstractGenericName(CodeType value) {
        return new JAXBElement<>(_AbstractGenericName_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "LocalName", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "AbstractGenericName")
    public JAXBElement<CodeType> createLocalName(CodeType value) {
        return new JAXBElement<>(_LocalName_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CodeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "ScopedName", substitutionHeadNamespace = "http://www.isotc211.org/2005/gco", substitutionHeadName = "AbstractGenericName")
    public JAXBElement<CodeType> createScopedName(CodeType value) {
        return new JAXBElement<>(_ScopedName_QNAME, CodeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "DateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Date")
    public JAXBElement<String> createDate(String value) {
        return new JAXBElement<>(_Date_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Real")
    public JAXBElement<Double> createReal(Double value) {
        return new JAXBElement<>(_Real_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Integer")
    public JAXBElement<BigInteger> createInteger(BigInteger value) {
        return new JAXBElement<>(_Integer_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UnlimitedIntegerType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UnlimitedIntegerType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "UnlimitedInteger")
    public JAXBElement<UnlimitedIntegerType> createUnlimitedInteger(UnlimitedIntegerType value) {
        return new JAXBElement<>(_UnlimitedInteger_QNAME, UnlimitedIntegerType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Record")
    public JAXBElement<Object> createRecord(Object value) {
        return new JAXBElement<>(_Record_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecordTypeType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RecordTypeType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "RecordType")
    public JAXBElement<RecordTypeType> createRecordType(RecordTypeType value) {
        return new JAXBElement<>(_RecordType_QNAME, RecordTypeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BinaryType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BinaryType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.isotc211.org/2005/gco", name = "Binary")
    public JAXBElement<BinaryType> createBinary(BinaryType value) {
        return new JAXBElement<>(_Binary_QNAME, BinaryType.class, null, value);
    }

}
