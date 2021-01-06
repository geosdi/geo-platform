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

import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * gml:RelatedTimeType provides a content model for indicating the relative position of an arbitrary member of the substitution group whose head is gml:AbstractTimePrimitive. It extends the generic gml:TimePrimitivePropertyType with an XML attribute relativePosition, whose value is selected from the set of 13 temporal relationships identified by Allen (1983)
 * 
 * <p>Classe Java per RelatedTimeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RelatedTimeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TimePrimitivePropertyType">
 *       &lt;attribute name="relativePosition">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Before"/>
 *             &lt;enumeration value="After"/>
 *             &lt;enumeration value="Begins"/>
 *             &lt;enumeration value="Ends"/>
 *             &lt;enumeration value="During"/>
 *             &lt;enumeration value="Equals"/>
 *             &lt;enumeration value="Contains"/>
 *             &lt;enumeration value="Overlaps"/>
 *             &lt;enumeration value="Meets"/>
 *             &lt;enumeration value="OverlappedBy"/>
 *             &lt;enumeration value="MetBy"/>
 *             &lt;enumeration value="BegunBy"/>
 *             &lt;enumeration value="EndedBy"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedTimeType")
public class RelatedTimeType
    extends TimePrimitivePropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlAttribute(name = "relativePosition")
    protected String relativePosition;

    /**
     * Recupera il valore della proprietà relativePosition.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelativePosition() {
        return relativePosition;
    }

    /**
     * Imposta il valore della proprietà relativePosition.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelativePosition(String value) {
        this.relativePosition = value;
    }

    public boolean isSetRelativePosition() {
        return (this.relativePosition!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            String theRelativePosition;
            theRelativePosition = this.getRelativePosition();
            strategy.appendField(locator, this, "relativePosition", buffer, theRelativePosition);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RelatedTimeType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final RelatedTimeType that = ((RelatedTimeType) object);
        {
            String lhsRelativePosition;
            lhsRelativePosition = this.getRelativePosition();
            String rhsRelativePosition;
            rhsRelativePosition = that.getRelativePosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relativePosition", lhsRelativePosition), LocatorUtils.property(thatLocator, "relativePosition", rhsRelativePosition), lhsRelativePosition, rhsRelativePosition)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = super.hashCode(locator, strategy);
        {
            String theRelativePosition;
            theRelativePosition = this.getRelativePosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "relativePosition", theRelativePosition), currentHashCode, theRelativePosition);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof RelatedTimeType) {
            final RelatedTimeType copy = ((RelatedTimeType) draftCopy);
            if (this.isSetRelativePosition()) {
                String sourceRelativePosition;
                sourceRelativePosition = this.getRelativePosition();
                String copyRelativePosition = ((String) strategy.copy(LocatorUtils.property(locator, "relativePosition", sourceRelativePosition), sourceRelativePosition));
                copy.setRelativePosition(copyRelativePosition);
            } else {
                copy.relativePosition = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new RelatedTimeType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof RelatedTimeType) {
            final RelatedTimeType target = this;
            final RelatedTimeType leftObject = ((RelatedTimeType) left);
            final RelatedTimeType rightObject = ((RelatedTimeType) right);
            {
                String lhsRelativePosition;
                lhsRelativePosition = leftObject.getRelativePosition();
                String rhsRelativePosition;
                rhsRelativePosition = rightObject.getRelativePosition();
                target.setRelativePosition(((String) strategy.merge(LocatorUtils.property(leftLocator, "relativePosition", lhsRelativePosition), LocatorUtils.property(rightLocator, "relativePosition", rhsRelativePosition), lhsRelativePosition, rhsRelativePosition)));
            }
        }
    }

    public RelatedTimeType withRelativePosition(String value) {
        setRelativePosition(value);
        return this;
    }

    @Override
    public RelatedTimeType withAbstractTimePrimitive(JAXBElement<? extends AbstractTimePrimitiveType> value) {
        setAbstractTimePrimitive(value);
        return this;
    }

    @Override
    public RelatedTimeType withNilReason(String... values) {
        if (values!= null) {
            for (String value: values) {
                getNilReason().add(value);
            }
        }
        return this;
    }

    @Override
    public RelatedTimeType withNilReason(Collection<String> values) {
        if (values!= null) {
            getNilReason().addAll(values);
        }
        return this;
    }

    @Override
    public RelatedTimeType withRemoteSchema(String value) {
        setRemoteSchema(value);
        return this;
    }

    @Override
    public RelatedTimeType withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public RelatedTimeType withRole(String value) {
        setRole(value);
        return this;
    }

    @Override
    public RelatedTimeType withArcrole(String value) {
        setArcrole(value);
        return this;
    }

    @Override
    public RelatedTimeType withTitle(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public RelatedTimeType withShow(String value) {
        setShow(value);
        return this;
    }

    @Override
    public RelatedTimeType withActuate(String value) {
        setActuate(value);
        return this;
    }

    @Override
    public RelatedTimeType withOwns(boolean value) {
        setOwns(value);
        return this;
    }

    @Override
    public RelatedTimeType withNilReason(List<String> value) {
        setNilReason(value);
        return this;
    }

}
