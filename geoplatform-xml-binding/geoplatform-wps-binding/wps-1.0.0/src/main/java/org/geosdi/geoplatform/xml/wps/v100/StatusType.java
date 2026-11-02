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

package org.geosdi.geoplatform.xml.wps.v100;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Description of the status of process execution.
 * 
 * <p>Classe Java per StatusType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="StatusType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="ProcessAccepted" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ProcessStarted" type="{http://www.opengis.net/wps/1.0.0}ProcessStartedType"/>
 *         <element name="ProcessPaused" type="{http://www.opengis.net/wps/1.0.0}ProcessStartedType"/>
 *         <element name="ProcessSucceeded" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="ProcessFailed" type="{http://www.opengis.net/wps/1.0.0}ProcessFailedType"/>
 *       </choice>
 *       <attribute name="creationTime" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusType", propOrder = {
    "processAccepted",
    "processStarted",
    "processPaused",
    "processSucceeded",
    "processFailed"
})
public class StatusType implements ToString
{

    /**
     * Indicates that this process has been accepted by the server, but is in a queue and has not yet started to execute. The contents of this human-readable text string is left open to definition by each server implementation, but is expected to include any messages the server may wish to let the clients know. Such information could include how long the queue is, or any warning conditions that may have been encountered. The client may display this text to a human user.
     * 
     */
    @XmlElement(name = "ProcessAccepted")
    protected String processAccepted;
    /**
     * Indicates that this process has been accepted by the server, and processing has begun.
     * 
     */
    @XmlElement(name = "ProcessStarted")
    protected ProcessStartedType processStarted;
    /**
     * Indicates that this process has been  accepted by the server, and processing has started but subsequently been paused by the server.
     * 
     */
    @XmlElement(name = "ProcessPaused")
    protected ProcessStartedType processPaused;
    /**
     * Indicates that this process has successfully completed execution. The contents of this human-readable text string is left open to definition by each server, but is expected to include any messages the server may wish to let the clients know, such as how long the process took to execute, or any warning conditions that may have been encountered. The client may display this text string to a human user. The client should make use of the presence of this element to trigger automated or manual access to the results of the process. If manual access is intended, the client should use the presence of this element to present the results as downloadable links to the user.
     * 
     */
    @XmlElement(name = "ProcessSucceeded")
    protected String processSucceeded;
    /**
     * Indicates that execution of this process has failed, and includes error information.
     * 
     */
    @XmlElement(name = "ProcessFailed")
    protected ProcessFailedType processFailed;
    /**
     * The time (UTC) that the process finished.  If the process is still executing or awaiting execution, this element shall contain the creation time of this document.
     * 
     */
    @XmlAttribute(name = "creationTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;

    /**
     * Indicates that this process has been accepted by the server, but is in a queue and has not yet started to execute. The contents of this human-readable text string is left open to definition by each server implementation, but is expected to include any messages the server may wish to let the clients know. Such information could include how long the queue is, or any warning conditions that may have been encountered. The client may display this text to a human user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessAccepted() {
        return processAccepted;
    }

    /**
     * Imposta il valore della proprietà processAccepted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getProcessAccepted()
     */
    public void setProcessAccepted(String value) {
        this.processAccepted = value;
    }

    public boolean isSetProcessAccepted() {
        return (this.processAccepted!= null);
    }

    /**
     * Indicates that this process has been accepted by the server, and processing has begun.
     * 
     * @return
     *     possible object is
     *     {@link ProcessStartedType }
     *     
     */
    public ProcessStartedType getProcessStarted() {
        return processStarted;
    }

    /**
     * Imposta il valore della proprietà processStarted.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessStartedType }
     *     
     * @see #getProcessStarted()
     */
    public void setProcessStarted(ProcessStartedType value) {
        this.processStarted = value;
    }

    public boolean isSetProcessStarted() {
        return (this.processStarted!= null);
    }

    /**
     * Indicates that this process has been  accepted by the server, and processing has started but subsequently been paused by the server.
     * 
     * @return
     *     possible object is
     *     {@link ProcessStartedType }
     *     
     */
    public ProcessStartedType getProcessPaused() {
        return processPaused;
    }

    /**
     * Imposta il valore della proprietà processPaused.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessStartedType }
     *     
     * @see #getProcessPaused()
     */
    public void setProcessPaused(ProcessStartedType value) {
        this.processPaused = value;
    }

    public boolean isSetProcessPaused() {
        return (this.processPaused!= null);
    }

    /**
     * Indicates that this process has successfully completed execution. The contents of this human-readable text string is left open to definition by each server, but is expected to include any messages the server may wish to let the clients know, such as how long the process took to execute, or any warning conditions that may have been encountered. The client may display this text string to a human user. The client should make use of the presence of this element to trigger automated or manual access to the results of the process. If manual access is intended, the client should use the presence of this element to present the results as downloadable links to the user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessSucceeded() {
        return processSucceeded;
    }

    /**
     * Imposta il valore della proprietà processSucceeded.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getProcessSucceeded()
     */
    public void setProcessSucceeded(String value) {
        this.processSucceeded = value;
    }

    public boolean isSetProcessSucceeded() {
        return (this.processSucceeded!= null);
    }

    /**
     * Indicates that execution of this process has failed, and includes error information.
     * 
     * @return
     *     possible object is
     *     {@link ProcessFailedType }
     *     
     */
    public ProcessFailedType getProcessFailed() {
        return processFailed;
    }

    /**
     * Imposta il valore della proprietà processFailed.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessFailedType }
     *     
     * @see #getProcessFailed()
     */
    public void setProcessFailed(ProcessFailedType value) {
        this.processFailed = value;
    }

    public boolean isSetProcessFailed() {
        return (this.processFailed!= null);
    }

    /**
     * The time (UTC) that the process finished.  If the process is still executing or awaiting execution, this element shall contain the creation time of this document.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationTime() {
        return creationTime;
    }

    /**
     * Imposta il valore della proprietà creationTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     * @see #getCreationTime()
     */
    public void setCreationTime(XMLGregorianCalendar value) {
        this.creationTime = value;
    }

    public boolean isSetCreationTime() {
        return (this.creationTime!= null);
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
            String theProcessAccepted;
            theProcessAccepted = this.getProcessAccepted();
            strategy.appendField(locator, this, "processAccepted", buffer, theProcessAccepted, this.isSetProcessAccepted());
        }
        {
            ProcessStartedType theProcessStarted;
            theProcessStarted = this.getProcessStarted();
            strategy.appendField(locator, this, "processStarted", buffer, theProcessStarted, this.isSetProcessStarted());
        }
        {
            ProcessStartedType theProcessPaused;
            theProcessPaused = this.getProcessPaused();
            strategy.appendField(locator, this, "processPaused", buffer, theProcessPaused, this.isSetProcessPaused());
        }
        {
            String theProcessSucceeded;
            theProcessSucceeded = this.getProcessSucceeded();
            strategy.appendField(locator, this, "processSucceeded", buffer, theProcessSucceeded, this.isSetProcessSucceeded());
        }
        {
            ProcessFailedType theProcessFailed;
            theProcessFailed = this.getProcessFailed();
            strategy.appendField(locator, this, "processFailed", buffer, theProcessFailed, this.isSetProcessFailed());
        }
        {
            XMLGregorianCalendar theCreationTime;
            theCreationTime = this.getCreationTime();
            strategy.appendField(locator, this, "creationTime", buffer, theCreationTime, this.isSetCreationTime());
        }
        return buffer;
    }

}
