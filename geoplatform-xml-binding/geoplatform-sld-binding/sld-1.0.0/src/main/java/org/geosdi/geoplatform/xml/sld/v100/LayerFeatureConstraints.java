//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}FeatureTypeConstraint" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "featureTypeConstraint"
})
@XmlRootElement(name = "LayerFeatureConstraints")
public class LayerFeatureConstraints implements ToString2
{

    @XmlElement(name = "FeatureTypeConstraint", required = true)
    protected List<FeatureTypeConstraint> featureTypeConstraint;

    /**
     * Gets the value of the featureTypeConstraint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureTypeConstraint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureTypeConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeatureTypeConstraint }
     * 
     * 
     */
    public List<FeatureTypeConstraint> getFeatureTypeConstraint() {
        if (featureTypeConstraint == null) {
            featureTypeConstraint = new ArrayList<FeatureTypeConstraint>();
        }
        return this.featureTypeConstraint;
    }

    public boolean isSetFeatureTypeConstraint() {
        return ((this.featureTypeConstraint!= null)&&(!this.featureTypeConstraint.isEmpty()));
    }

    public void unsetFeatureTypeConstraint() {
        this.featureTypeConstraint = null;
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
            List<FeatureTypeConstraint> theFeatureTypeConstraint;
            theFeatureTypeConstraint = (this.isSetFeatureTypeConstraint()?this.getFeatureTypeConstraint():null);
            strategy.appendField(locator, this, "featureTypeConstraint", buffer, theFeatureTypeConstraint, this.isSetFeatureTypeConstraint());
        }
        return buffer;
    }

    public void setFeatureTypeConstraint(List<FeatureTypeConstraint> value) {
        this.featureTypeConstraint = null;
        if (value!= null) {
            List<FeatureTypeConstraint> draftl = this.getFeatureTypeConstraint();
            draftl.addAll(value);
        }
    }

}
