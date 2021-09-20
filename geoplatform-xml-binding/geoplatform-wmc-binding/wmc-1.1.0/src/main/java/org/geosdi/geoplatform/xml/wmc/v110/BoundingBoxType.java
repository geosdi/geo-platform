//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Classe Java per BoundingBoxType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="BoundingBoxType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="SRS" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="minx" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="miny" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="maxx" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *       &lt;attribute name="maxy" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingBoxType")
public class BoundingBoxType implements ToString2 {

    @XmlAttribute(name = "SRS", required = true)
    protected String srs;
    @XmlAttribute(name = "minx", required = true)
    protected BigDecimal minx;
    @XmlAttribute(name = "miny", required = true)
    protected BigDecimal miny;
    @XmlAttribute(name = "maxx", required = true)
    protected BigDecimal maxx;
    @XmlAttribute(name = "maxy", required = true)
    protected BigDecimal maxy;

    /**
     * Recupera il valore della proprietà srs.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSRS() {
        return srs;
    }

    /**
     * Imposta il valore della proprietà srs.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setSRS(String value) {
        this.srs = value;
    }

    public boolean isSetSRS() {
        return (this.srs != null);
    }

    /**
     * Recupera il valore della proprietà minx.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getMinx() {
        return minx;
    }

    /**
     * Imposta il valore della proprietà minx.
     *
     * @param value allowed object is
     * {@link BigDecimal }
     */
    public void setMinx(BigDecimal value) {
        this.minx = value;
    }

    public boolean isSetMinx() {
        return (this.minx != null);
    }

    /**
     * Recupera il valore della proprietà miny.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getMiny() {
        return miny;
    }

    /**
     * Imposta il valore della proprietà miny.
     *
     * @param value allowed object is
     * {@link BigDecimal }
     */
    public void setMiny(BigDecimal value) {
        this.miny = value;
    }

    public boolean isSetMiny() {
        return (this.miny != null);
    }

    /**
     * Recupera il valore della proprietà maxx.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getMaxx() {
        return maxx;
    }

    /**
     * Imposta il valore della proprietà maxx.
     *
     * @param value allowed object is
     * {@link BigDecimal }
     */
    public void setMaxx(BigDecimal value) {
        this.maxx = value;
    }

    public boolean isSetMaxx() {
        return (this.maxx != null);
    }

    /**
     * Recupera il valore della proprietà maxy.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getMaxy() {
        return maxy;
    }

    /**
     * Imposta il valore della proprietà maxy.
     *
     * @param value allowed object is
     * {@link BigDecimal }
     */
    public void setMaxy(BigDecimal value) {
        this.maxy = value;
    }

    public boolean isSetMaxy() {
        return (this.maxy != null);
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
        {
            String theSRS;
            theSRS = this.getSRS();
            strategy.appendField(locator, this, "srs", buffer, theSRS, this.isSetSRS());
        }
        {
            BigDecimal theMinx;
            theMinx = this.getMinx();
            strategy.appendField(locator, this, "minx", buffer, theMinx, this.isSetMinx());
        }
        {
            BigDecimal theMiny;
            theMiny = this.getMiny();
            strategy.appendField(locator, this, "miny", buffer, theMiny, this.isSetMiny());
        }
        {
            BigDecimal theMaxx;
            theMaxx = this.getMaxx();
            strategy.appendField(locator, this, "maxx", buffer, theMaxx, this.isSetMaxx());
        }
        {
            BigDecimal theMaxy;
            theMaxy = this.getMaxy();
            strategy.appendField(locator, this, "maxy", buffer, theMaxy, this.isSetMaxy());
        }
        return buffer;
    }

}
