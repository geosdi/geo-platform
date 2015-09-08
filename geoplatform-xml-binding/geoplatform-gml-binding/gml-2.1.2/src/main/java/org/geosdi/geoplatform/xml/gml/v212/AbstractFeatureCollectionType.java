//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 10:30:08 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v212;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 *         A feature collection contains zero or more featureMember elements.
 *       
 * 
 * <p>Classe Java per AbstractFeatureCollectionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractFeatureCollectionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractFeatureCollectionBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}featureMember" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractFeatureCollectionType", propOrder = {
    "featureMember"
})
public abstract class AbstractFeatureCollectionType
    extends AbstractFeatureCollectionBaseType
    implements ToString
{

    protected List<FeatureAssociationType> featureMember;

    /**
     * Gets the value of the featureMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeatureAssociationType }
     * 
     * 
     */
    public List<FeatureAssociationType> getFeatureMember() {
        if (featureMember == null) {
            featureMember = new ArrayList<FeatureAssociationType>();
        }
        return this.featureMember;
    }

    public boolean isSetFeatureMember() {
        return ((this.featureMember!= null)&&(!this.featureMember.isEmpty()));
    }

    public void unsetFeatureMember() {
        this.featureMember = null;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<FeatureAssociationType> theFeatureMember;
            theFeatureMember = (this.isSetFeatureMember()?this.getFeatureMember():null);
            strategy.appendField(locator, this, "featureMember", buffer, theFeatureMember);
        }
        return buffer;
    }

    public void setFeatureMember(List<FeatureAssociationType> value) {
        this.featureMember = null;
        List<FeatureAssociationType> draftl = this.getFeatureMember();
        draftl.addAll(value);
    }

}
