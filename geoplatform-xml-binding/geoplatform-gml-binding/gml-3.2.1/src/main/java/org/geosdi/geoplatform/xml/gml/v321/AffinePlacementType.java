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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per AffinePlacementType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AffinePlacementType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="location" type="{http://www.opengis.net/gml/3.2}DirectPositionType"/>
 *         <element name="refDirection" type="{http://www.opengis.net/gml/3.2}VectorType" maxOccurs="unbounded"/>
 *         <element name="inDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         <element name="outDimension" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffinePlacementType", propOrder = {
    "location",
    "refDirection",
    "inDimension",
    "outDimension"
})
public class AffinePlacementType implements ToString
{

    @XmlElement(required = true)
    protected DirectPositionType location;
    @XmlElement(required = true)
    protected List<VectorType> refDirection;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger inDimension;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger outDimension;

    /**
     * Recupera il valore della proprietà location.
     * 
     * @return
     *     possible object is
     *     {@link DirectPositionType }
     *     
     */
    public DirectPositionType getLocation() {
        return location;
    }

    /**
     * Imposta il valore della proprietà location.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectPositionType }
     *     
     */
    public void setLocation(DirectPositionType value) {
        this.location = value;
    }

    public boolean isSetLocation() {
        return (this.location!= null);
    }

    /**
     * Gets the value of the refDirection property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the refDirection property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRefDirection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VectorType }
     * </p>
     * 
     * 
     * @return
     *     The value of the refDirection property.
     */
    public List<VectorType> getRefDirection() {
        if (refDirection == null) {
            refDirection = new ArrayList<>();
        }
        return this.refDirection;
    }

    public boolean isSetRefDirection() {
        return ((this.refDirection!= null)&&(!this.refDirection.isEmpty()));
    }

    public void unsetRefDirection() {
        this.refDirection = null;
    }

    /**
     * Recupera il valore della proprietà inDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInDimension() {
        return inDimension;
    }

    /**
     * Imposta il valore della proprietà inDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInDimension(BigInteger value) {
        this.inDimension = value;
    }

    public boolean isSetInDimension() {
        return (this.inDimension!= null);
    }

    /**
     * Recupera il valore della proprietà outDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOutDimension() {
        return outDimension;
    }

    /**
     * Imposta il valore della proprietà outDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOutDimension(BigInteger value) {
        this.outDimension = value;
    }

    public boolean isSetOutDimension() {
        return (this.outDimension!= null);
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
            DirectPositionType theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation, this.isSetLocation());
        }
        {
            List<VectorType> theRefDirection;
            theRefDirection = (this.isSetRefDirection()?this.getRefDirection():null);
            strategy.appendField(locator, this, "refDirection", buffer, theRefDirection, this.isSetRefDirection());
        }
        {
            BigInteger theInDimension;
            theInDimension = this.getInDimension();
            strategy.appendField(locator, this, "inDimension", buffer, theInDimension, this.isSetInDimension());
        }
        {
            BigInteger theOutDimension;
            theOutDimension = this.getOutDimension();
            strategy.appendField(locator, this, "outDimension", buffer, theOutDimension, this.isSetOutDimension());
        }
        return buffer;
    }

    public void setRefDirection(List<VectorType> value) {
        this.refDirection = null;
        if (value!= null) {
            List<VectorType> draftl = this.getRefDirection();
            draftl.addAll(value);
        }
    }

}
