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

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per ClothoidType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ClothoidType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractCurveSegmentType">
 *       <sequence>
 *         <element name="refLocation">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element ref="{http://www.opengis.net/gml/3.2}AffinePlacement"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="scaleFactor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         <element name="startParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="endParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       </sequence>
 *       <attribute name="interpolation" type="{http://www.opengis.net/gml/3.2}CurveInterpolationType" fixed="clothoid" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClothoidType", propOrder = {
    "refLocation",
    "scaleFactor",
    "startParameter",
    "endParameter"
})
public class ClothoidType
    extends AbstractCurveSegmentType
    implements ToString
{

    @XmlElement(required = true)
    protected ClothoidType.RefLocation refLocation;
    @XmlElement(required = true)
    protected BigDecimal scaleFactor;
    protected double startParameter;
    protected double endParameter;
    @XmlAttribute(name = "interpolation")
    public static final CurveInterpolationType INTERPOLATION = CurveInterpolationType.CLOTHOID;

    /**
     * Recupera il valore della proprietà refLocation.
     * 
     * @return
     *     possible object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public ClothoidType.RefLocation getRefLocation() {
        return refLocation;
    }

    /**
     * Imposta il valore della proprietà refLocation.
     * 
     * @param value
     *     allowed object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public void setRefLocation(ClothoidType.RefLocation value) {
        this.refLocation = value;
    }

    public boolean isSetRefLocation() {
        return (this.refLocation!= null);
    }

    /**
     * Recupera il valore della proprietà scaleFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Imposta il valore della proprietà scaleFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setScaleFactor(BigDecimal value) {
        this.scaleFactor = value;
    }

    public boolean isSetScaleFactor() {
        return (this.scaleFactor!= null);
    }

    /**
     * Recupera il valore della proprietà startParameter.
     * 
     */
    public double getStartParameter() {
        return startParameter;
    }

    /**
     * Imposta il valore della proprietà startParameter.
     * 
     */
    public void setStartParameter(double value) {
        this.startParameter = value;
    }

    public boolean isSetStartParameter() {
        return true;
    }

    /**
     * Recupera il valore della proprietà endParameter.
     * 
     */
    public double getEndParameter() {
        return endParameter;
    }

    /**
     * Imposta il valore della proprietà endParameter.
     * 
     */
    public void setEndParameter(double value) {
        this.endParameter = value;
    }

    public boolean isSetEndParameter() {
        return true;
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
            ClothoidType.RefLocation theRefLocation;
            theRefLocation = this.getRefLocation();
            strategy.appendField(locator, this, "refLocation", buffer, theRefLocation, this.isSetRefLocation());
        }
        {
            BigDecimal theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            strategy.appendField(locator, this, "scaleFactor", buffer, theScaleFactor, this.isSetScaleFactor());
        }
        {
            double theStartParameter;
            theStartParameter = this.getStartParameter();
            strategy.appendField(locator, this, "startParameter", buffer, theStartParameter, true);
        }
        {
            double theEndParameter;
            theEndParameter = this.getEndParameter();
            strategy.appendField(locator, this, "endParameter", buffer, theEndParameter, true);
        }
        {
            CurveInterpolationType theINTERPOLATION;
            theINTERPOLATION = ClothoidType.INTERPOLATION;
            strategy.appendField(locator, this, "interpolation", buffer, theINTERPOLATION, true);
        }
        return buffer;
    }


    /**
     * <p>Classe Java per anonymous complex type.</p>
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element ref="{http://www.opengis.net/gml/3.2}AffinePlacement"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "affinePlacement"
    })
    public static class RefLocation implements ToString
    {

        /**
         * location, refDirection, inDimension and outDimension have the same meaning as specified in ISO 19107:2003, 6.4.21.
         * 
         */
        @XmlElement(name = "AffinePlacement", required = true)
        protected AffinePlacementType affinePlacement;

        /**
         * location, refDirection, inDimension and outDimension have the same meaning as specified in ISO 19107:2003, 6.4.21.
         * 
         * @return
         *     possible object is
         *     {@link AffinePlacementType }
         *     
         */
        public AffinePlacementType getAffinePlacement() {
            return affinePlacement;
        }

        /**
         * Imposta il valore della proprietà affinePlacement.
         * 
         * @param value
         *     allowed object is
         *     {@link AffinePlacementType }
         *     
         * @see #getAffinePlacement()
         */
        public void setAffinePlacement(AffinePlacementType value) {
            this.affinePlacement = value;
        }

        public boolean isSetAffinePlacement() {
            return (this.affinePlacement!= null);
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
                AffinePlacementType theAffinePlacement;
                theAffinePlacement = this.getAffinePlacement();
                strategy.appendField(locator, this, "affinePlacement", buffer, theAffinePlacement, this.isSetAffinePlacement());
            }
            return buffer;
        }

    }

}
