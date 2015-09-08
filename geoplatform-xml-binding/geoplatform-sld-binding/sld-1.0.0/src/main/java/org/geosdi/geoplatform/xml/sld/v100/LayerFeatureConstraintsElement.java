//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
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
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}FeatureTypeConstraint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "featureTypeConstraint"
})
@XmlRootElement(name = "LayerFeatureConstraints")
public class LayerFeatureConstraintsElement
    implements ToString
{

    @XmlElement(name = "FeatureTypeConstraint", required = true)
    protected List<FeatureTypeConstraintElement> featureTypeConstraint;

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
     * {@link FeatureTypeConstraintElement }
     * 
     * 
     */
    public List<FeatureTypeConstraintElement> getFeatureTypeConstraint() {
        if (featureTypeConstraint == null) {
            featureTypeConstraint = new ArrayList<FeatureTypeConstraintElement>();
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
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            List<FeatureTypeConstraintElement> theFeatureTypeConstraint;
            theFeatureTypeConstraint = (this.isSetFeatureTypeConstraint()?this.getFeatureTypeConstraint():null);
            strategy.appendField(locator, this, "featureTypeConstraint", buffer, theFeatureTypeConstraint);
        }
        return buffer;
    }

    public void setFeatureTypeConstraint(List<FeatureTypeConstraintElement> value) {
        this.featureTypeConstraint = null;
        List<FeatureTypeConstraintElement> draftl = this.getFeatureTypeConstraint();
        draftl.addAll(value);
    }

}
