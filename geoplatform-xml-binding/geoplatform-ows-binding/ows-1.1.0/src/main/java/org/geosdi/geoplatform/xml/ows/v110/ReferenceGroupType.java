//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Logical group of one or more references to remote and/or local resources, allowing including metadata about that group. A Group can be used instead of a Manifest that can only contain one group. 
 * 
 * <p>Classe Java per ReferenceGroupType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ReferenceGroupType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}BasicIdentificationType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}AbstractReferenceBase" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceGroupType", propOrder = {
    "abstractReferenceBase"
})
public class ReferenceGroupType
    extends BasicIdentificationType
    implements ToString2
{

    @XmlElementRef(name = "AbstractReferenceBase", namespace = "http://www.opengis.net/ows/1.1", type = JAXBElement.class)
    protected List<JAXBElement<? extends AbstractReferenceBaseType>> abstractReferenceBase;

    /**
     * Gets the value of the abstractReferenceBase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractReferenceBase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractReferenceBase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractReferenceBaseType }{@code >}
     * {@link JAXBElement }{@code <}{@link ServiceReferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractReferenceBaseType>> getAbstractReferenceBase() {
        if (abstractReferenceBase == null) {
            abstractReferenceBase = new ArrayList<JAXBElement<? extends AbstractReferenceBaseType>>();
        }
        return this.abstractReferenceBase;
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
            List<JAXBElement<? extends AbstractReferenceBaseType>> theAbstractReferenceBase;
            theAbstractReferenceBase = (((this.abstractReferenceBase!= null)&&(!this.abstractReferenceBase.isEmpty()))?this.getAbstractReferenceBase():null);
            strategy.appendField(locator, this, "abstractReferenceBase", buffer, theAbstractReferenceBase, ((this.abstractReferenceBase!= null)&&(!this.abstractReferenceBase.isEmpty())));
        }
        return buffer;
    }

    public void setAbstractReferenceBase(List<JAXBElement<? extends AbstractReferenceBaseType>> value) {
        this.abstractReferenceBase = null;
        if (value!= null) {
            List<JAXBElement<? extends AbstractReferenceBaseType>> draftl = this.getAbstractReferenceBase();
            draftl.addAll(value);
        }
    }

}
