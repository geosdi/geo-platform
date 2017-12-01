//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Unordered list of one or more groups of references to remote and/or local resources. 
 * 
 * <p>Classe Java per ManifestType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ManifestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}BasicIdentificationType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}ReferenceGroup" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ManifestType", propOrder = {
    "referenceGroup"
})
public class ManifestType
    extends BasicIdentificationType
    implements ToString2
{

    @XmlElement(name = "ReferenceGroup", required = true)
    protected List<ReferenceGroupType> referenceGroup;

    /**
     * Gets the value of the referenceGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceGroupType }
     * 
     * 
     */
    public List<ReferenceGroupType> getReferenceGroup() {
        if (referenceGroup == null) {
            referenceGroup = new ArrayList<ReferenceGroupType>();
        }
        return this.referenceGroup;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
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
            List<ReferenceGroupType> theReferenceGroup;
            theReferenceGroup = (((this.referenceGroup!= null)&&(!this.referenceGroup.isEmpty()))?this.getReferenceGroup():null);
            strategy.appendField(locator, this, "referenceGroup", buffer, theReferenceGroup, ((this.referenceGroup!= null)&&(!this.referenceGroup.isEmpty())));
        }
        return buffer;
    }

    public void setReferenceGroup(List<ReferenceGroupType> value) {
        this.referenceGroup = null;
        if (value!= null) {
            List<ReferenceGroupType> draftl = this.getReferenceGroup();
            draftl.addAll(value);
        }
    }

}
