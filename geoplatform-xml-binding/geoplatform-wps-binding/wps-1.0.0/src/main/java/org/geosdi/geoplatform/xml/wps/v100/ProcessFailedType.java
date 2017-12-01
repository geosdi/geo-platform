//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.ExceptionReport;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Indicator that the process has failed to execute successfully. The reason for failure is given in the exception report.
 * <p>
 * <p>Classe Java per ProcessFailedType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ProcessFailedType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}ExceptionReport"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessFailedType", propOrder = {
        "exceptionReport"
})
public class ProcessFailedType implements ToString2 {

    @XmlElement(name = "ExceptionReport", namespace = "http://www.opengis.net/ows/1.1", required = true)
    protected ExceptionReport exceptionReport;

    /**
     * Recupera il valore della proprietà exceptionReport.
     *
     * @return possible object is
     * {@link ExceptionReport }
     */
    public ExceptionReport getExceptionReport() {
        return exceptionReport;
    }

    /**
     * Imposta il valore della proprietà exceptionReport.
     *
     * @param value allowed object is
     *              {@link ExceptionReport }
     */
    public void setExceptionReport(ExceptionReport value) {
        this.exceptionReport = value;
    }

    public boolean isSetExceptionReport() {
        return (this.exceptionReport != null);
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
            ExceptionReport theExceptionReport;
            theExceptionReport = this.getExceptionReport();
            strategy.appendField(locator, this, "exceptionReport", buffer, theExceptionReport, this.isSetExceptionReport());
        }
        return buffer;
    }

}
