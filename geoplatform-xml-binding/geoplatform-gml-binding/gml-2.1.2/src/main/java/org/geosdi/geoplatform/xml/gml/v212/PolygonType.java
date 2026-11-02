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
// Generato il: 2026.11.02 alle 02:49:13 PM CET
//

package org.geosdi.geoplatform.xml.gml.v212;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * A Polygon is defined by an outer boundary and zero or more inner 
 *         boundaries which are in turn defined by LinearRings.
 * 
 * <p>Classe Java per PolygonType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="PolygonType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeometryType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}outerBoundaryIs"/>
 *         <element ref="{http://www.opengis.net/gml}innerBoundaryIs" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonType", propOrder = {
    "outerBoundaryIs",
    "innerBoundaryIs"
})
@XmlRootElement(name = "Polygon")
public class PolygonType
    extends AbstractGeometryType
    implements ToString
{

    @XmlElement(required = true)
    protected LinearRingMemberType outerBoundaryIs;
    protected List<LinearRingMemberType> innerBoundaryIs;

    /**
     * Recupera il valore della proprietà outerBoundaryIs.
     * 
     * @return
     *     possible object is
     *     {@link LinearRingMemberType }
     *     
     */
    public LinearRingMemberType getOuterBoundaryIs() {
        return outerBoundaryIs;
    }

    /**
     * Imposta il valore della proprietà outerBoundaryIs.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearRingMemberType }
     *     
     */
    public void setOuterBoundaryIs(LinearRingMemberType value) {
        this.outerBoundaryIs = value;
    }

    public boolean isSetOuterBoundaryIs() {
        return (this.outerBoundaryIs!= null);
    }

    /**
     * Gets the value of the innerBoundaryIs property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the innerBoundaryIs property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getInnerBoundaryIs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LinearRingMemberType }
     * </p>
     * 
     * 
     * @return
     *     The value of the innerBoundaryIs property.
     */
    public List<LinearRingMemberType> getInnerBoundaryIs() {
        if (innerBoundaryIs == null) {
            innerBoundaryIs = new ArrayList<>();
        }
        return this.innerBoundaryIs;
    }

    public boolean isSetInnerBoundaryIs() {
        return ((this.innerBoundaryIs!= null)&&(!this.innerBoundaryIs.isEmpty()));
    }

    public void unsetInnerBoundaryIs() {
        this.innerBoundaryIs = null;
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
        super.appendFields(locator, buffer, strategy);
        {
            LinearRingMemberType theOuterBoundaryIs;
            theOuterBoundaryIs = this.getOuterBoundaryIs();
            strategy.appendField(locator, this, "outerBoundaryIs", buffer, theOuterBoundaryIs, this.isSetOuterBoundaryIs());
        }
        {
            List<LinearRingMemberType> theInnerBoundaryIs;
            theInnerBoundaryIs = (this.isSetInnerBoundaryIs()?this.getInnerBoundaryIs():null);
            strategy.appendField(locator, this, "innerBoundaryIs", buffer, theInnerBoundaryIs, this.isSetInnerBoundaryIs());
        }
        return buffer;
    }

    public void setInnerBoundaryIs(List<LinearRingMemberType> value) {
        this.innerBoundaryIs = null;
        if (value!= null) {
            List<LinearRingMemberType> draftl = this.getInnerBoundaryIs();
            draftl.addAll(value);
        }
    }

}
