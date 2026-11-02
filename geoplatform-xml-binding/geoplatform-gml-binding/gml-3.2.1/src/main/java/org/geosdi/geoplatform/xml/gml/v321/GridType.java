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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per GridType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GridType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractGeometryType">
 *       <sequence>
 *         <element name="limits" type="{http://www.opengis.net/gml/3.2}GridLimitsType"/>
 *         <choice>
 *           <element name="axisLabels" type="{http://www.opengis.net/gml/3.2}NCNameList"/>
 *           <element name="axisName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         </choice>
 *       </sequence>
 *       <attribute name="dimension" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridType", propOrder = {
    "rest"
})
@XmlSeeAlso({
    RectifiedGridType.class
})
public class GridType
    extends AbstractGeometryType
    implements ToString
{

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * &lt;p&gt;
     * Questa proprietà "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "AxisLabels" è usato da due diverse parti di uno schema. Vedere: 
     * riga 23 di file:/Users/glascaleia/codice/geo-platform/geoplatform-xml-binding/geoplatform-gml-binding/gml-3.2.1/src/main/resources/gml/3.2.1/grids.xsd
     * riga 41 di file:/Users/glascaleia/codice/geo-platform/geoplatform-xml-binding/geoplatform-gml-binding/gml-3.2.1/src/main/resources/gml/3.2.1/geometryBasic0d1d.xsd
     * &lt;p&gt;
     * Per eliminare questa proprietà, applicare una personalizzazione della proprietà a una 
     * delle seguenti due dichiarazioni per modificarne il nome:
     * 
     */
    @XmlElementRefs({
        @XmlElementRef(name = "limits", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "axisLabels", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "axisName", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> rest;
    @XmlAttribute(name = "dimension", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger dimension;

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * &lt;p&gt;
     * Questa proprietà "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "AxisLabels" è usato da due diverse parti di uno schema. Vedere: 
     * riga 23 di file:/Users/glascaleia/codice/geo-platform/geoplatform-xml-binding/geoplatform-gml-binding/gml-3.2.1/src/main/resources/gml/3.2.1/grids.xsd
     * riga 41 di file:/Users/glascaleia/codice/geo-platform/geoplatform-xml-binding/geoplatform-gml-binding/gml-3.2.1/src/main/resources/gml/3.2.1/geometryBasic0d1d.xsd
     * &lt;p&gt;
     * Per eliminare questa proprietà, applicare una personalizzazione della proprietà a una 
     * delle seguenti due dichiarazioni per modificarne il nome:
     * 
     * Gets the value of the rest property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rest property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link GridLimitsType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the rest property.
     */
    public List<JAXBElement<?>> getRest() {
        if (rest == null) {
            rest = new ArrayList<>();
        }
        return this.rest;
    }

    /**
     * Recupera il valore della proprietà dimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDimension() {
        return dimension;
    }

    /**
     * Imposta il valore della proprietà dimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDimension(BigInteger value) {
        this.dimension = value;
    }

    public boolean isSetDimension() {
        return (this.dimension!= null);
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
            List<JAXBElement<?>> theRest;
            theRest = (((this.rest!= null)&&(!this.rest.isEmpty()))?this.getRest():null);
            strategy.appendField(locator, this, "rest", buffer, theRest, ((this.rest!= null)&&(!this.rest.isEmpty())));
        }
        {
            BigInteger theDimension;
            theDimension = this.getDimension();
            strategy.appendField(locator, this, "dimension", buffer, theDimension, this.isSetDimension());
        }
        return buffer;
    }

    public void setRest(List<JAXBElement<?>> value) {
        this.rest = null;
        if (value!= null) {
            List<JAXBElement<?>> draftl = this.getRest();
            draftl.addAll(value);
        }
    }

}
