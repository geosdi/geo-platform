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
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}FeatureTypeName" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}SemanticTypeIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Rule" maxOccurs="unbounded"/>
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
    "name",
    "title",
    "_abstract",
    "featureTypeName",
    "semanticTypeIdentifier",
    "rule"
})
@XmlRootElement(name = "FeatureTypeStyle")
public class FeatureTypeStyleElement
    implements ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "FeatureTypeName")
    protected String featureTypeName;
    @XmlElement(name = "SemanticTypeIdentifier")
    protected List<String> semanticTypeIdentifier;
    @XmlElement(name = "Rule", required = true)
    protected List<RuleElement> rule;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Recupera il valore della proprietà featureTypeName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureTypeName() {
        return featureTypeName;
    }

    /**
     * Imposta il valore della proprietà featureTypeName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureTypeName(String value) {
        this.featureTypeName = value;
    }

    public boolean isSetFeatureTypeName() {
        return (this.featureTypeName!= null);
    }

    /**
     * Gets the value of the semanticTypeIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the semanticTypeIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSemanticTypeIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSemanticTypeIdentifier() {
        if (semanticTypeIdentifier == null) {
            semanticTypeIdentifier = new ArrayList<String>();
        }
        return this.semanticTypeIdentifier;
    }

    public boolean isSetSemanticTypeIdentifier() {
        return ((this.semanticTypeIdentifier!= null)&&(!this.semanticTypeIdentifier.isEmpty()));
    }

    public void unsetSemanticTypeIdentifier() {
        this.semanticTypeIdentifier = null;
    }

    /**
     * Gets the value of the rule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleElement }
     * 
     * 
     */
    public List<RuleElement> getRule() {
        if (rule == null) {
            rule = new ArrayList<RuleElement>();
        }
        return this.rule;
    }

    public boolean isSetRule() {
        return ((this.rule!= null)&&(!this.rule.isEmpty()));
    }

    public void unsetRule() {
        this.rule = null;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract);
        }
        {
            String theFeatureTypeName;
            theFeatureTypeName = this.getFeatureTypeName();
            strategy.appendField(locator, this, "featureTypeName", buffer, theFeatureTypeName);
        }
        {
            List<String> theSemanticTypeIdentifier;
            theSemanticTypeIdentifier = (this.isSetSemanticTypeIdentifier()?this.getSemanticTypeIdentifier():null);
            strategy.appendField(locator, this, "semanticTypeIdentifier", buffer, theSemanticTypeIdentifier);
        }
        {
            List<RuleElement> theRule;
            theRule = (this.isSetRule()?this.getRule():null);
            strategy.appendField(locator, this, "rule", buffer, theRule);
        }
        return buffer;
    }

    public void setSemanticTypeIdentifier(List<String> value) {
        this.semanticTypeIdentifier = null;
        List<String> draftl = this.getSemanticTypeIdentifier();
        draftl.addAll(value);
    }

    public void setRule(List<RuleElement> value) {
        this.rule = null;
        List<RuleElement> draftl = this.getRule();
        draftl.addAll(value);
    }

}
