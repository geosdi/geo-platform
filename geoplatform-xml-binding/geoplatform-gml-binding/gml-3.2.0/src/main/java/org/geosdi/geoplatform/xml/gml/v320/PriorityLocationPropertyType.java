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
 * <p>Classe Java per PriorityLocationPropertyType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PriorityLocationPropertyType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}LocationPropertyType">
 *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PriorityLocationPropertyType")
public class PriorityLocationPropertyType
    extends LocationPropertyType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlAttribute(name = "priority")
    protected String priority;

    /**
     * Recupera il valore della proprietà priority.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Imposta il valore della proprietà priority.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    public boolean isSetPriority() {
        return (this.priority!= null);
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
            String thePriority;
            thePriority = this.getPriority();
            strategy.appendField(locator, this, "priority", buffer, thePriority);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PriorityLocationPropertyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PriorityLocationPropertyType that = ((PriorityLocationPropertyType) object);
        {
            String lhsPriority;
            lhsPriority = this.getPriority();
            String rhsPriority;
            rhsPriority = that.getPriority();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "priority", lhsPriority), LocatorUtils.property(thatLocator, "priority", rhsPriority), lhsPriority, rhsPriority)) {
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
            String thePriority;
            thePriority = this.getPriority();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "priority", thePriority), currentHashCode, thePriority);
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
        if (draftCopy instanceof PriorityLocationPropertyType) {
            final PriorityLocationPropertyType copy = ((PriorityLocationPropertyType) draftCopy);
            if (this.isSetPriority()) {
                String sourcePriority;
                sourcePriority = this.getPriority();
                String copyPriority = ((String) strategy.copy(LocatorUtils.property(locator, "priority", sourcePriority), sourcePriority));
                copy.setPriority(copyPriority);
            } else {
                copy.priority = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new PriorityLocationPropertyType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof PriorityLocationPropertyType) {
            final PriorityLocationPropertyType target = this;
            final PriorityLocationPropertyType leftObject = ((PriorityLocationPropertyType) left);
            final PriorityLocationPropertyType rightObject = ((PriorityLocationPropertyType) right);
            {
                String lhsPriority;
                lhsPriority = leftObject.getPriority();
                String rhsPriority;
                rhsPriority = rightObject.getPriority();
                target.setPriority(((String) strategy.merge(LocatorUtils.property(leftLocator, "priority", lhsPriority), LocatorUtils.property(rightLocator, "priority", rhsPriority), lhsPriority, rhsPriority)));
            }
        }
    }

    public PriorityLocationPropertyType withPriority(String value) {
        setPriority(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withAbstractGeometry(JAXBElement<? extends AbstractGeometryType> value) {
        setAbstractGeometry(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withLocationKeyWord(CodeType value) {
        setLocationKeyWord(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withLocationString(StringOrRefType value) {
        setLocationString(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNull(String... values) {
        if (values!= null) {
            for (String value: values) {
                getNull().add(value);
            }
        }
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNull(Collection<String> values) {
        if (values!= null) {
            getNull().addAll(values);
        }
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNilReason(String... values) {
        if (values!= null) {
            for (String value: values) {
                getNilReason().add(value);
            }
        }
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNilReason(Collection<String> values) {
        if (values!= null) {
            getNilReason().addAll(values);
        }
        return this;
    }

    @Override
    public PriorityLocationPropertyType withRemoteSchema(String value) {
        setRemoteSchema(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withHref(String value) {
        setHref(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withRole(String value) {
        setRole(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withArcrole(String value) {
        setArcrole(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withTitle(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withShow(String value) {
        setShow(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withActuate(String value) {
        setActuate(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNull(List<String> value) {
        setNull(value);
        return this;
    }

    @Override
    public PriorityLocationPropertyType withNilReason(List<String> value) {
        setNilReason(value);
        return this;
    }

}
