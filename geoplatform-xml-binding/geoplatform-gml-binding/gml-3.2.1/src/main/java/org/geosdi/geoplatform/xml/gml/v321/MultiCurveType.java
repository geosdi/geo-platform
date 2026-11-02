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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per MultiCurveType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MultiCurveType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractGeometricAggregateType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}curveMember" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}curveMembers" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiCurveType", propOrder = {
    "curveMember",
    "curveMembers"
})
public class MultiCurveType
    extends AbstractGeometricAggregateType
    implements ToString
{

    protected List<CurvePropertyType> curveMember;
    /**
     * This property element contains a list of curves. The order of the elements is significant and shall be preserved when processing the array.
     * 
     */
    protected CurveArrayPropertyType curveMembers;

    /**
     * Gets the value of the curveMember property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the curveMember property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCurveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CurvePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the curveMember property.
     */
    public List<CurvePropertyType> getCurveMember() {
        if (curveMember == null) {
            curveMember = new ArrayList<>();
        }
        return this.curveMember;
    }

    public boolean isSetCurveMember() {
        return ((this.curveMember!= null)&&(!this.curveMember.isEmpty()));
    }

    public void unsetCurveMember() {
        this.curveMember = null;
    }

    /**
     * This property element contains a list of curves. The order of the elements is significant and shall be preserved when processing the array.
     * 
     * @return
     *     possible object is
     *     {@link CurveArrayPropertyType }
     *     
     */
    public CurveArrayPropertyType getCurveMembers() {
        return curveMembers;
    }

    /**
     * Imposta il valore della proprietà curveMembers.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveArrayPropertyType }
     *     
     * @see #getCurveMembers()
     */
    public void setCurveMembers(CurveArrayPropertyType value) {
        this.curveMembers = value;
    }

    public boolean isSetCurveMembers() {
        return (this.curveMembers!= null);
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
            List<CurvePropertyType> theCurveMember;
            theCurveMember = (this.isSetCurveMember()?this.getCurveMember():null);
            strategy.appendField(locator, this, "curveMember", buffer, theCurveMember, this.isSetCurveMember());
        }
        {
            CurveArrayPropertyType theCurveMembers;
            theCurveMembers = this.getCurveMembers();
            strategy.appendField(locator, this, "curveMembers", buffer, theCurveMembers, this.isSetCurveMembers());
        }
        return buffer;
    }

    public void setCurveMember(List<CurvePropertyType> value) {
        this.curveMember = null;
        if (value!= null) {
            List<CurvePropertyType> draftl = this.getCurveMember();
            draftl.addAll(value);
        }
    }

}
