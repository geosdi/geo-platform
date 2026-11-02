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
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * gml:secondDefiningParameter is a property containing the definition of the second parameter that defines the shape of an ellipsoid. An ellipsoid requires two defining parameters: semi-major axis and inverse flattening or semi-major axis and semi-minor axis. When the reference body is a sphere rather than an ellipsoid, only a single defining parameter is required, namely the radius of the sphere; in that case, the semi-major axis "degenerates" into the radius of the sphere.
 * The inverseFlattening element contains the inverse flattening value of the ellipsoid. This value is a scale factor (or ratio). It uses gml:LengthType with the restriction that the unit of measure referenced by the uom attribute must be suitable for a scale factor, such as percent, permil, or parts-per-million.
 * The semiMinorAxis element contains the length of the semi-minor axis of the ellipsoid. When the isSphere element is included, the ellipsoid is degenerate and is actually a sphere. The sphere is completely defined by the semi-major axis, which is the radius of the sphere.
 * 
 * <p>Classe Java per secondDefiningParameter element declaration.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <element name="secondDefiningParameter">
 *   <complexType>
 *     <complexContent>
 *       <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         <sequence>
 *           <element ref="{http://www.opengis.net/gml}SecondDefiningParameter"/>
 *         </sequence>
 *       </restriction>
 *     </complexContent>
 *   </complexType>
 * </element>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "secondDefiningParameter"
})
@XmlRootElement(name = "secondDefiningParameter")
public class SecondDefiningParameterPropertyElement implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "SecondDefiningParameter", required = true)
    protected SecondDefiningParameterElement secondDefiningParameter;

    /**
     * Recupera il valore della proprietà secondDefiningParameter.
     * 
     * @return
     *     possible object is
     *     {@link SecondDefiningParameterElement }
     *     
     */
    public SecondDefiningParameterElement getSecondDefiningParameter() {
        return secondDefiningParameter;
    }

    /**
     * Imposta il valore della proprietà secondDefiningParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link SecondDefiningParameterElement }
     *     
     */
    public void setSecondDefiningParameter(SecondDefiningParameterElement value) {
        this.secondDefiningParameter = value;
    }

    public boolean isSetSecondDefiningParameter() {
        return (this.secondDefiningParameter!= null);
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            SecondDefiningParameterElement theSecondDefiningParameter;
            theSecondDefiningParameter = this.getSecondDefiningParameter();
            strategy.appendField(locator, this, "secondDefiningParameter", buffer, theSecondDefiningParameter, this.isSetSecondDefiningParameter());
        }
        return buffer;
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SecondDefiningParameterPropertyElement that = ((SecondDefiningParameterPropertyElement) object);
        {
            SecondDefiningParameterElement lhsSecondDefiningParameter;
            lhsSecondDefiningParameter = this.getSecondDefiningParameter();
            SecondDefiningParameterElement rhsSecondDefiningParameter;
            rhsSecondDefiningParameter = that.getSecondDefiningParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "secondDefiningParameter", lhsSecondDefiningParameter), LocatorUtils.property(thatLocator, "secondDefiningParameter", rhsSecondDefiningParameter), lhsSecondDefiningParameter, rhsSecondDefiningParameter, this.isSetSecondDefiningParameter(), that.isSetSecondDefiningParameter())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SecondDefiningParameterElement theSecondDefiningParameter;
            theSecondDefiningParameter = this.getSecondDefiningParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "secondDefiningParameter", theSecondDefiningParameter), currentHashCode, theSecondDefiningParameter, this.isSetSecondDefiningParameter());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof SecondDefiningParameterPropertyElement) {
            final SecondDefiningParameterPropertyElement copy = ((SecondDefiningParameterPropertyElement) draftCopy);
            {
                java.lang.Boolean secondDefiningParameterShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSecondDefiningParameter());
                if (secondDefiningParameterShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SecondDefiningParameterElement sourceSecondDefiningParameter;
                    sourceSecondDefiningParameter = this.getSecondDefiningParameter();
                    SecondDefiningParameterElement copySecondDefiningParameter = ((SecondDefiningParameterElement) strategy.copy(LocatorUtils.property(locator, "secondDefiningParameter", sourceSecondDefiningParameter), sourceSecondDefiningParameter, this.isSetSecondDefiningParameter()));
                    copy.setSecondDefiningParameter(copySecondDefiningParameter);
                } else {
                    if (secondDefiningParameterShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.secondDefiningParameter = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new SecondDefiningParameterPropertyElement();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SecondDefiningParameterPropertyElement) {
            final SecondDefiningParameterPropertyElement target = this;
            final SecondDefiningParameterPropertyElement leftObject = ((SecondDefiningParameterPropertyElement) left);
            final SecondDefiningParameterPropertyElement rightObject = ((SecondDefiningParameterPropertyElement) right);
            {
                java.lang.Boolean secondDefiningParameterShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSecondDefiningParameter(), rightObject.isSetSecondDefiningParameter());
                if (secondDefiningParameterShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SecondDefiningParameterElement lhsSecondDefiningParameter;
                    lhsSecondDefiningParameter = leftObject.getSecondDefiningParameter();
                    SecondDefiningParameterElement rhsSecondDefiningParameter;
                    rhsSecondDefiningParameter = rightObject.getSecondDefiningParameter();
                    SecondDefiningParameterElement mergedSecondDefiningParameter = ((SecondDefiningParameterElement) strategy.merge(LocatorUtils.property(leftLocator, "secondDefiningParameter", lhsSecondDefiningParameter), LocatorUtils.property(rightLocator, "secondDefiningParameter", rhsSecondDefiningParameter), lhsSecondDefiningParameter, rhsSecondDefiningParameter, leftObject.isSetSecondDefiningParameter(), rightObject.isSetSecondDefiningParameter()));
                    target.setSecondDefiningParameter(mergedSecondDefiningParameter);
                } else {
                    if (secondDefiningParameterShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.secondDefiningParameter = null;
                    }
                }
            }
        }
    }

}
