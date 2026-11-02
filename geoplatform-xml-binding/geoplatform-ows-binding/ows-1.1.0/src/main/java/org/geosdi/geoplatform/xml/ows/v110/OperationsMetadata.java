/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.w3c.dom.Element;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}Operation" maxOccurs="unbounded" minOccurs="2"/>
 *         <element name="Parameter" type="{http://www.opengis.net/ows/1.1}DomainType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="Constraint" type="{http://www.opengis.net/ows/1.1}DomainType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}ExtendedCapabilities" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
public class OperationsMetadata implements ToString
{

    /**
     * Metadata for unordered list of all the (requests for) operations that this server interface implements. The list of required and optional operations implemented shall be specified in the Implementation Specification for this service.
     * 
     */
    @XmlElement(name = "Operation", required = true)
    protected List<Operation> operation;
    /**
     * Optional unordered list of parameter valid domains that each apply to one or more operations which this server interface implements. The list of required and optional parameter domain limitations shall be specified in the Implementation Specification for this service.
     * 
     */
    @XmlElement(name = "Parameter")
    protected List<DomainType> parameter;
    /**
     * Optional unordered list of valid domain constraints on non-parameter quantities that each apply to this server. The list of required and optional constraints shall be specified in the Implementation Specification for this service.
     * 
     */
    @XmlElement(name = "Constraint")
    protected List<DomainType> constraint;
    /**
     * Individual software vendors and servers can use this element to provide metadata about any additional server abilities.
     * 
     */
    @XmlAnyElement
    protected Element extendedCapabilities;

    /**
     * Metadata for unordered list of all the (requests for) operations that this server interface implements. The list of required and optional operations implemented shall be specified in the Implementation Specification for this service.
     * 
     * Gets the value of the operation property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the operation property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operation }
     * </p>
     * 
     * 
     * @return
     *     The value of the operation property.
     */
    public List<Operation> getOperation() {
        if (operation == null) {
            operation = new ArrayList<>();
        }
        return this.operation;
    }

    /**
     * Optional unordered list of parameter valid domains that each apply to one or more operations which this server interface implements. The list of required and optional parameter domain limitations shall be specified in the Implementation Specification for this service.
     * 
     * Gets the value of the parameter property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the parameter property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainType }
     * </p>
     * 
     * 
     * @return
     *     The value of the parameter property.
     */
    public List<DomainType> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<>();
        }
        return this.parameter;
    }

    /**
     * Optional unordered list of valid domain constraints on non-parameter quantities that each apply to this server. The list of required and optional constraints shall be specified in the Implementation Specification for this service.
     * 
     * Gets the value of the constraint property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the constraint property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainType }
     * </p>
     * 
     * 
     * @return
     *     The value of the constraint property.
     */
    public List<DomainType> getConstraint() {
        if (constraint == null) {
            constraint = new ArrayList<>();
        }
        return this.constraint;
    }

    /**
     * Individual software vendors and servers can use this element to provide metadata about any additional server abilities.
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
     * @see #getExtendedCapabilities()
     */
    public void setExtendedCapabilities(Element value) {
        this.extendedCapabilities = value;
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
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
