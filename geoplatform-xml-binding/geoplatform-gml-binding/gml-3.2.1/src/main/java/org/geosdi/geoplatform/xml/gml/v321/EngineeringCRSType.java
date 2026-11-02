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
 * <p>Classe Java per EngineeringCRSType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EngineeringCRSType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractCRSType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml/3.2}affineCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}cartesianCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}cylindricalCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}linearCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}polarCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}sphericalCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}userDefinedCS"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}coordinateSystem"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml/3.2}engineeringDatum"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EngineeringCRSType", propOrder = {
    "affineCS",
    "cartesianCS",
    "cylindricalCS",
    "linearCS",
    "polarCS",
    "sphericalCS",
    "userDefinedCS",
    "coordinateSystem",
    "engineeringDatum"
})
public class EngineeringCRSType
    extends AbstractCRSType
    implements ToString
{

    /**
     * gml:affineCS is an association role to the affine coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "affineCS", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<AffineCSPropertyType> affineCS;
    /**
     * gml:cartesianCS is an association role to the Cartesian coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "cartesianCS", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<CartesianCSPropertyType> cartesianCS;
    /**
     * gml:cylindricalCS is an association role to the cylindrical coordinate system used by this CRS.
     * 
     */
    protected CylindricalCSPropertyType cylindricalCS;
    /**
     * gml:linearCS is an association role to the linear coordinate system used by this CRS.
     * 
     */
    protected LinearCSPropertyType linearCS;
    /**
     * gml:polarCS is an association role to the polar coordinate system used by this CRS.
     * 
     */
    protected PolarCSPropertyType polarCS;
    /**
     * gml:sphericalCS is an association role to the spherical coordinate system used by this CRS.
     * 
     */
    @XmlElementRef(name = "sphericalCS", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<SphericalCSPropertyType> sphericalCS;
    /**
     * gml:userDefinedCS is an association role to the user defined coordinate system used by this CRS.
     * 
     */
    protected UserDefinedCSPropertyType userDefinedCS;
    @XmlElementRef(name = "coordinateSystem", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    protected JAXBElement<CoordinateSystemPropertyType> coordinateSystem;
    /**
     * gml:engineeringDatum is an association role to the engineering datum used by this CRS.
     * 
     */
    @XmlElementRef(name = "engineeringDatum", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
    protected JAXBElement<EngineeringDatumPropertyType> engineeringDatum;

    /**
     * gml:affineCS is an association role to the affine coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<AffineCSPropertyType> getAffineCS() {
        return affineCS;
    }

    /**
     * Imposta il valore della proprietà affineCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AffineCSPropertyType }{@code >}
     *     
     * @see #getAffineCS()
     */
    public void setAffineCS(JAXBElement<AffineCSPropertyType> value) {
        this.affineCS = value;
    }

    public boolean isSetAffineCS() {
        return (this.affineCS!= null);
    }

    /**
     * gml:cartesianCS is an association role to the Cartesian coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<CartesianCSPropertyType> getCartesianCS() {
        return cartesianCS;
    }

    /**
     * Imposta il valore della proprietà cartesianCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CartesianCSPropertyType }{@code >}
     *     
     * @see #getCartesianCS()
     */
    public void setCartesianCS(JAXBElement<CartesianCSPropertyType> value) {
        this.cartesianCS = value;
    }

    public boolean isSetCartesianCS() {
        return (this.cartesianCS!= null);
    }

    /**
     * gml:cylindricalCS is an association role to the cylindrical coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link CylindricalCSPropertyType }
     *     
     */
    public CylindricalCSPropertyType getCylindricalCS() {
        return cylindricalCS;
    }

    /**
     * Imposta il valore della proprietà cylindricalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link CylindricalCSPropertyType }
     *     
     * @see #getCylindricalCS()
     */
    public void setCylindricalCS(CylindricalCSPropertyType value) {
        this.cylindricalCS = value;
    }

    public boolean isSetCylindricalCS() {
        return (this.cylindricalCS!= null);
    }

    /**
     * gml:linearCS is an association role to the linear coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link LinearCSPropertyType }
     *     
     */
    public LinearCSPropertyType getLinearCS() {
        return linearCS;
    }

    /**
     * Imposta il valore della proprietà linearCS.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearCSPropertyType }
     *     
     * @see #getLinearCS()
     */
    public void setLinearCS(LinearCSPropertyType value) {
        this.linearCS = value;
    }

    public boolean isSetLinearCS() {
        return (this.linearCS!= null);
    }

    /**
     * gml:polarCS is an association role to the polar coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link PolarCSPropertyType }
     *     
     */
    public PolarCSPropertyType getPolarCS() {
        return polarCS;
    }

    /**
     * Imposta il valore della proprietà polarCS.
     * 
     * @param value
     *     allowed object is
     *     {@link PolarCSPropertyType }
     *     
     * @see #getPolarCS()
     */
    public void setPolarCS(PolarCSPropertyType value) {
        this.polarCS = value;
    }

    public boolean isSetPolarCS() {
        return (this.polarCS!= null);
    }

    /**
     * gml:sphericalCS is an association role to the spherical coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     */
    public JAXBElement<SphericalCSPropertyType> getSphericalCS() {
        return sphericalCS;
    }

    /**
     * Imposta il valore della proprietà sphericalCS.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link SphericalCSPropertyType }{@code >}
     *     
     * @see #getSphericalCS()
     */
    public void setSphericalCS(JAXBElement<SphericalCSPropertyType> value) {
        this.sphericalCS = value;
    }

    public boolean isSetSphericalCS() {
        return (this.sphericalCS!= null);
    }

    /**
     * gml:userDefinedCS is an association role to the user defined coordinate system used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link UserDefinedCSPropertyType }
     *     
     */
    public UserDefinedCSPropertyType getUserDefinedCS() {
        return userDefinedCS;
    }

    /**
     * Imposta il valore della proprietà userDefinedCS.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDefinedCSPropertyType }
     *     
     * @see #getUserDefinedCS()
     */
    public void setUserDefinedCS(UserDefinedCSPropertyType value) {
        this.userDefinedCS = value;
    }

    public boolean isSetUserDefinedCS() {
        return (this.userDefinedCS!= null);
    }

    /**
     * Recupera il valore della proprietà coordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public JAXBElement<CoordinateSystemPropertyType> getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Imposta il valore della proprietà coordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link CoordinateSystemPropertyType }{@code >}
     *     
     */
    public void setCoordinateSystem(JAXBElement<CoordinateSystemPropertyType> value) {
        this.coordinateSystem = value;
    }

    public boolean isSetCoordinateSystem() {
        return (this.coordinateSystem!= null);
    }

    /**
     * gml:engineeringDatum is an association role to the engineering datum used by this CRS.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     
     */
    public JAXBElement<EngineeringDatumPropertyType> getEngineeringDatum() {
        return engineeringDatum;
    }

    /**
     * Imposta il valore della proprietà engineeringDatum.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EngineeringDatumPropertyType }{@code >}
     *     
     * @see #getEngineeringDatum()
     */
    public void setEngineeringDatum(JAXBElement<EngineeringDatumPropertyType> value) {
        this.engineeringDatum = value;
    }

    public boolean isSetEngineeringDatum() {
        return (this.engineeringDatum!= null);
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
            JAXBElement<AffineCSPropertyType> theAffineCS;
            theAffineCS = this.getAffineCS();
            strategy.appendField(locator, this, "affineCS", buffer, theAffineCS, this.isSetAffineCS());
        }
        {
            JAXBElement<CartesianCSPropertyType> theCartesianCS;
            theCartesianCS = this.getCartesianCS();
            strategy.appendField(locator, this, "cartesianCS", buffer, theCartesianCS, this.isSetCartesianCS());
        }
        {
            CylindricalCSPropertyType theCylindricalCS;
            theCylindricalCS = this.getCylindricalCS();
            strategy.appendField(locator, this, "cylindricalCS", buffer, theCylindricalCS, this.isSetCylindricalCS());
        }
        {
            LinearCSPropertyType theLinearCS;
            theLinearCS = this.getLinearCS();
            strategy.appendField(locator, this, "linearCS", buffer, theLinearCS, this.isSetLinearCS());
        }
        {
            PolarCSPropertyType thePolarCS;
            thePolarCS = this.getPolarCS();
            strategy.appendField(locator, this, "polarCS", buffer, thePolarCS, this.isSetPolarCS());
        }
        {
            JAXBElement<SphericalCSPropertyType> theSphericalCS;
            theSphericalCS = this.getSphericalCS();
            strategy.appendField(locator, this, "sphericalCS", buffer, theSphericalCS, this.isSetSphericalCS());
        }
        {
            UserDefinedCSPropertyType theUserDefinedCS;
            theUserDefinedCS = this.getUserDefinedCS();
            strategy.appendField(locator, this, "userDefinedCS", buffer, theUserDefinedCS, this.isSetUserDefinedCS());
        }
        {
            JAXBElement<CoordinateSystemPropertyType> theCoordinateSystem;
            theCoordinateSystem = this.getCoordinateSystem();
            strategy.appendField(locator, this, "coordinateSystem", buffer, theCoordinateSystem, this.isSetCoordinateSystem());
        }
        {
            JAXBElement<EngineeringDatumPropertyType> theEngineeringDatum;
            theEngineeringDatum = this.getEngineeringDatum();
            strategy.appendField(locator, this, "engineeringDatum", buffer, theEngineeringDatum, this.isSetEngineeringDatum());
        }
        return buffer;
    }

}
