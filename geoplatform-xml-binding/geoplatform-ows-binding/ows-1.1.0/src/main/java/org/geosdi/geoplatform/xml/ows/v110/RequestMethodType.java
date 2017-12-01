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
 * Connect point URL and any constraints for this HTTP request method for this operation request. In the OnlineResourceType, the xlink:href attribute in the xlink:simpleLink attribute group shall be used to contain this URL. The other attributes in the xlink:simpleLink attribute group should not be used. 
 * 
 * <p>Classe Java per RequestMethodType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RequestMethodType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}OnlineResourceType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Constraint" type="{http://www.opengis.net/ows/1.1}DomainType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestMethodType", propOrder = {
    "constraint"
})
public class RequestMethodType
    extends OnlineResourceType
    implements ToString2
{

    @XmlElement(name = "Constraint")
    protected List<DomainType> constraint;

    /**
     * Gets the value of the constraint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constraint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainType }
     * 
     * 
     */
    public List<DomainType> getConstraint() {
        if (constraint == null) {
            constraint = new ArrayList<DomainType>();
        }
        return this.constraint;
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
            List<DomainType> theConstraint;
            theConstraint = (((this.constraint!= null)&&(!this.constraint.isEmpty()))?this.getConstraint():null);
            strategy.appendField(locator, this, "constraint", buffer, theConstraint, ((this.constraint!= null)&&(!this.constraint.isEmpty())));
        }
        return buffer;
    }

    public void setConstraint(List<DomainType> value) {
        this.constraint = null;
        if (value!= null) {
            List<DomainType> draftl = this.getConstraint();
            draftl.addAll(value);
        }
    }

}
