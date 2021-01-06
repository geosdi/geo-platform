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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
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
 * gml:SolidArrayPropertyType is a container for an array of solids. The elements are always contained in the array property, referencing geometry elements or arrays of geometry elements is not supported.
 * 
 * <p>Classe Java per SolidArrayPropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SolidArrayPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractSolid"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}OwnershipAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolidArrayPropertyType", propOrder = {
    "abstractSolid"
})
public class SolidArrayPropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElementRef(name = "AbstractSolid", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends AbstractSolidType>> abstractSolid;
    @XmlAttribute(name = "owns")
    protected java.lang.Boolean owns;

    /**
     * Gets the value of the abstractSolid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractSolid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractSolid().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractSolidType }{@code >}
     * {@link JAXBElement }{@code <}{@link SolidType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompositeSolidType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractSolidType>> getAbstractSolid() {
        if (abstractSolid == null) {
            abstractSolid = new ArrayList<JAXBElement<? extends AbstractSolidType>>();
        }
        return this.abstractSolid;
    }

    public boolean isSetAbstractSolid() {
        return ((this.abstractSolid!= null)&&(!this.abstractSolid.isEmpty()));
    }

    public void unsetAbstractSolid() {
        this.abstractSolid = null;
    }

    /**
     * Recupera il valore della proprietà owns.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public boolean isOwns() {
        if (owns == null) {
            return false;
        } else {
            return owns;
        }
    }

    /**
     * Imposta il valore della proprietà owns.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setOwns(boolean value) {
        this.owns = value;
    }

    public boolean isSetOwns() {
        return (this.owns!= null);
    }

    public void unsetOwns() {
        this.owns = null;
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
        {
            List<JAXBElement<? extends AbstractSolidType>> theAbstractSolid;
            theAbstractSolid = this.getAbstractSolid();
            strategy.appendField(locator, this, "abstractSolid", buffer, theAbstractSolid);
        }
        {
            boolean theOwns;
            theOwns = this.isOwns();
            strategy.appendField(locator, this, "owns", buffer, theOwns);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SolidArrayPropertyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SolidArrayPropertyType that = ((SolidArrayPropertyType) object);
        {
            List<JAXBElement<? extends AbstractSolidType>> lhsAbstractSolid;
            lhsAbstractSolid = this.getAbstractSolid();
            List<JAXBElement<? extends AbstractSolidType>> rhsAbstractSolid;
            rhsAbstractSolid = that.getAbstractSolid();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractSolid", lhsAbstractSolid), LocatorUtils.property(thatLocator, "abstractSolid", rhsAbstractSolid), lhsAbstractSolid, rhsAbstractSolid)) {
                return false;
            }
        }
        {
            boolean lhsOwns;
            lhsOwns = this.isOwns();
            boolean rhsOwns;
            rhsOwns = that.isOwns();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "owns", lhsOwns), LocatorUtils.property(thatLocator, "owns", rhsOwns), lhsOwns, rhsOwns)) {
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
        int currentHashCode = 1;
        {
            List<JAXBElement<? extends AbstractSolidType>> theAbstractSolid;
            theAbstractSolid = this.getAbstractSolid();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractSolid", theAbstractSolid), currentHashCode, theAbstractSolid);
        }
        {
            boolean theOwns;
            theOwns = this.isOwns();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "owns", theOwns), currentHashCode, theOwns);
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
        if (draftCopy instanceof SolidArrayPropertyType) {
            final SolidArrayPropertyType copy = ((SolidArrayPropertyType) draftCopy);
            if (this.isSetAbstractSolid()) {
                List<JAXBElement<? extends AbstractSolidType>> sourceAbstractSolid;
                sourceAbstractSolid = this.getAbstractSolid();
                @SuppressWarnings("unchecked")
                List<JAXBElement<? extends AbstractSolidType>> copyAbstractSolid = ((List<JAXBElement<? extends AbstractSolidType>> ) strategy.copy(LocatorUtils.property(locator, "abstractSolid", sourceAbstractSolid), sourceAbstractSolid));
                copy.unsetAbstractSolid();
                List<JAXBElement<? extends AbstractSolidType>> uniqueAbstractSolidl = copy.getAbstractSolid();
                uniqueAbstractSolidl.addAll(copyAbstractSolid);
            } else {
                copy.unsetAbstractSolid();
            }
            if (this.isSetOwns()) {
                boolean sourceOwns;
                sourceOwns = this.isOwns();
                boolean copyOwns = strategy.copy(LocatorUtils.property(locator, "owns", sourceOwns), sourceOwns);
                copy.setOwns(copyOwns);
            } else {
                copy.unsetOwns();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SolidArrayPropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SolidArrayPropertyType) {
            final SolidArrayPropertyType target = this;
            final SolidArrayPropertyType leftObject = ((SolidArrayPropertyType) left);
            final SolidArrayPropertyType rightObject = ((SolidArrayPropertyType) right);
            {
                List<JAXBElement<? extends AbstractSolidType>> lhsAbstractSolid;
                lhsAbstractSolid = leftObject.getAbstractSolid();
                List<JAXBElement<? extends AbstractSolidType>> rhsAbstractSolid;
                rhsAbstractSolid = rightObject.getAbstractSolid();
                target.unsetAbstractSolid();
                List<JAXBElement<? extends AbstractSolidType>> uniqueAbstractSolidl = target.getAbstractSolid();
                uniqueAbstractSolidl.addAll(((List<JAXBElement<? extends AbstractSolidType>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractSolid", lhsAbstractSolid), LocatorUtils.property(rightLocator, "abstractSolid", rhsAbstractSolid), lhsAbstractSolid, rhsAbstractSolid)));
            }
            {
                boolean lhsOwns;
                lhsOwns = leftObject.isOwns();
                boolean rhsOwns;
                rhsOwns = rightObject.isOwns();
                target.setOwns(((boolean) strategy.merge(LocatorUtils.property(leftLocator, "owns", lhsOwns), LocatorUtils.property(rightLocator, "owns", rhsOwns), lhsOwns, rhsOwns)));
            }
        }
    }

    public void setAbstractSolid(List<JAXBElement<? extends AbstractSolidType>> value) {
        List<JAXBElement<? extends AbstractSolidType>> draftl = this.getAbstractSolid();
        draftl.addAll(value);
    }

    public SolidArrayPropertyType withAbstractSolid(JAXBElement<? extends AbstractSolidType> ... values) {
        if (values!= null) {
            for (JAXBElement<? extends AbstractSolidType> value: values) {
                getAbstractSolid().add(value);
            }
        }
        return this;
    }

    public SolidArrayPropertyType withAbstractSolid(Collection<JAXBElement<? extends AbstractSolidType>> values) {
        if (values!= null) {
            getAbstractSolid().addAll(values);
        }
        return this;
    }

    public SolidArrayPropertyType withOwns(boolean value) {
        setOwns(value);
        return this;
    }

    public SolidArrayPropertyType withAbstractSolid(List<JAXBElement<? extends AbstractSolidType>> value) {
        setAbstractSolid(value);
        return this;
    }

}
