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
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.w3c.dom.Element;


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
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Operation" maxOccurs="unbounded" minOccurs="2"/&gt;
 *         &lt;element name="Parameter" type="{http://www.opengis.net/ows/1.1}DomainType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Constraint" type="{http://www.opengis.net/ows/1.1}DomainType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}ExtendedCapabilities" minOccurs="0"/&gt;
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
    "operation",
    "parameter",
    "constraint",
    "extendedCapabilities"
})
@XmlRootElement(name = "OperationsMetadata")
public class OperationsMetadata implements ToString2
{

    @XmlElement(name = "Operation", required = true)
    protected List<Operation> operation;
    @XmlElement(name = "Parameter")
    protected List<DomainType> parameter;
    @XmlElement(name = "Constraint")
    protected List<DomainType> constraint;
    @XmlAnyElement
    protected Element extendedCapabilities;

    /**
     * Metadata for unordered list of all the (requests for) operations that this server interface implements. The list of required and optional operations implemented shall be specified in the Implementation Specification for this service. Gets the value of the operation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operation }
     * 
     * 
     */
    public List<Operation> getOperation() {
        if (operation == null) {
            operation = new ArrayList<Operation>();
        }
        return this.operation;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainType }
     * 
     * 
     */
    public List<DomainType> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<DomainType>();
        }
        return this.parameter;
    }

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

    /**
     * Recupera il valore della proprietà extendedCapabilities.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getExtendedCapabilities() {
        return extendedCapabilities;
    }

    /**
     * Imposta il valore della proprietà extendedCapabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setExtendedCapabilities(Element value) {
        this.extendedCapabilities = value;
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
        {
            List<Operation> theOperation;
            theOperation = (((this.operation!= null)&&(!this.operation.isEmpty()))?this.getOperation():null);
            strategy.appendField(locator, this, "operation", buffer, theOperation, ((this.operation!= null)&&(!this.operation.isEmpty())));
        }
        {
            List<DomainType> theParameter;
            theParameter = (((this.parameter!= null)&&(!this.parameter.isEmpty()))?this.getParameter():null);
            strategy.appendField(locator, this, "parameter", buffer, theParameter, ((this.parameter!= null)&&(!this.parameter.isEmpty())));
        }
        {
            List<DomainType> theConstraint;
            theConstraint = (((this.constraint!= null)&&(!this.constraint.isEmpty()))?this.getConstraint():null);
            strategy.appendField(locator, this, "constraint", buffer, theConstraint, ((this.constraint!= null)&&(!this.constraint.isEmpty())));
        }
        {
            Element theExtendedCapabilities;
            theExtendedCapabilities = this.getExtendedCapabilities();
            strategy.appendField(locator, this, "extendedCapabilities", buffer, theExtendedCapabilities, (this.extendedCapabilities!= null));
        }
        return buffer;
    }

    public void setOperation(List<Operation> value) {
        this.operation = null;
        if (value!= null) {
            List<Operation> draftl = this.getOperation();
            draftl.addAll(value);
        }
    }

    public void setParameter(List<DomainType> value) {
        this.parameter = null;
        if (value!= null) {
            List<DomainType> draftl = this.getParameter();
            draftl.addAll(value);
        }
    }

    public void setConstraint(List<DomainType> value) {
        this.constraint = null;
        if (value!= null) {
            List<DomainType> draftl = this.getConstraint();
            draftl.addAll(value);
        }
    }

}
