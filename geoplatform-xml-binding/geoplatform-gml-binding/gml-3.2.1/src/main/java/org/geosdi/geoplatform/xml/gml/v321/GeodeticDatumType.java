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

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per GeodeticDatumType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GeodeticDatumType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractDatumType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml/3.2}primeMeridian"/>
 *         <element ref="{http://www.opengis.net/gml/3.2}ellipsoid"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticDatumType", propOrder = {
    "primeMeridian",
    "ellipsoid"
})
public class GeodeticDatumType
    extends AbstractDatumType
    implements ToString
{

    /**
     * gml:primeMeridian is an association role to the prime meridian used by this geodetic datum.
     * 
     */
    @XmlElementRef(name = "primeMeridian", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
    protected JAXBElement<PrimeMeridianPropertyType> primeMeridian;
    /**
     * gml:ellipsoid is an association role to the ellipsoid used by this geodetic datum.
     * 
     */
    @XmlElementRef(name = "ellipsoid", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
    protected JAXBElement<EllipsoidPropertyType> ellipsoid;

    /**
     * gml:primeMeridian is an association role to the prime meridian used by this geodetic datum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     */
    public JAXBElement<PrimeMeridianPropertyType> getPrimeMeridian() {
        return primeMeridian;
    }

    /**
     * Imposta il valore della proprietà primeMeridian.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link PrimeMeridianPropertyType }{@code >}
     *     
     * @see #getPrimeMeridian()
     */
    public void setPrimeMeridian(JAXBElement<PrimeMeridianPropertyType> value) {
        this.primeMeridian = value;
    }

    public boolean isSetPrimeMeridian() {
        return (this.primeMeridian!= null);
    }

    /**
     * gml:ellipsoid is an association role to the ellipsoid used by this geodetic datum.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     */
    public JAXBElement<EllipsoidPropertyType> getEllipsoid() {
        return ellipsoid;
    }

    /**
     * Imposta il valore della proprietà ellipsoid.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EllipsoidPropertyType }{@code >}
     *     
     * @see #getEllipsoid()
     */
    public void setEllipsoid(JAXBElement<EllipsoidPropertyType> value) {
        this.ellipsoid = value;
    }

    public boolean isSetEllipsoid() {
        return (this.ellipsoid!= null);
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
            JAXBElement<PrimeMeridianPropertyType> thePrimeMeridian;
            thePrimeMeridian = this.getPrimeMeridian();
            strategy.appendField(locator, this, "primeMeridian", buffer, thePrimeMeridian, this.isSetPrimeMeridian());
        }
        {
            JAXBElement<EllipsoidPropertyType> theEllipsoid;
            theEllipsoid = this.getEllipsoid();
            strategy.appendField(locator, this, "ellipsoid", buffer, theEllipsoid, this.isSetEllipsoid());
        }
        return buffer;
    }

}
