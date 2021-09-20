//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:29:16 AM CEST 
//


package org.geosdi.geoplatform.xml.filter.v100;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.gml.v212.AbstractGeometryType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per DistanceBufferType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DistanceBufferType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ogc}SpatialOpsType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}PropertyName"/&gt;
 *         &lt;element ref="{http://www.opengis.net/gml}_Geometry"/&gt;
 *         &lt;element name="Distance" type="{http://www.opengis.net/ogc}DistanceType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistanceBufferType", propOrder = {
    "propertyName",
    "geometry",
    "distance"
})
public class DistanceBufferType
    extends SpatialOpsType
    implements ToString2
{

    @XmlElement(name = "PropertyName", required = true)
    protected PropertyNameType propertyName;
    @XmlElementRef(name = "_Geometry", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<AbstractGeometryType> geometry;
    @XmlElement(name = "Distance", required = true)
    protected DistanceType distance;

    /**
     * Recupera il valore della proprietà propertyName.
     * 
     * @return
     *     possible object is
     *     {@link PropertyNameType }
     *     
     */
    public PropertyNameType getPropertyName() {
        return propertyName;
    }

    /**
     * Imposta il valore della proprietà propertyName.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyNameType }
     *     
     */
    public void setPropertyName(PropertyNameType value) {
        this.propertyName = value;
    }

    public boolean isSetPropertyName() {
        return (this.propertyName!= null);
    }

    /**
     * Recupera il valore della proprietà geometry.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     
     */
    public JAXBElement<AbstractGeometryType> getGeometry() {
        return geometry;
    }

    /**
     * Imposta il valore della proprietà geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AbstractGeometryType }{@code >}
     *     
     */
    public void setGeometry(JAXBElement<AbstractGeometryType> value) {
        this.geometry = value;
    }

    public boolean isSetGeometry() {
        return (this.geometry!= null);
    }

    /**
     * Recupera il valore della proprietà distance.
     * 
     * @return
     *     possible object is
     *     {@link DistanceType }
     *     
     */
    public DistanceType getDistance() {
        return distance;
    }

    /**
     * Imposta il valore della proprietà distance.
     * 
     * @param value
     *     allowed object is
     *     {@link DistanceType }
     *     
     */
    public void setDistance(DistanceType value) {
        this.distance = value;
    }

    public boolean isSetDistance() {
        return (this.distance!= null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            PropertyNameType thePropertyName;
            thePropertyName = this.getPropertyName();
            strategy.appendField(locator, this, "propertyName", buffer, thePropertyName, this.isSetPropertyName());
        }
        {
            JAXBElement<AbstractGeometryType> theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry, this.isSetGeometry());
        }
        {
            DistanceType theDistance;
            theDistance = this.getDistance();
            strategy.appendField(locator, this, "distance", buffer, theDistance, this.isSetDistance());
        }
        return buffer;
    }

}
