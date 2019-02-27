package org.geosdi.geoplatform.jaxb.validation.configuration;

import org.geosdi.geoplatform.jaxb.validation.xsd.message.XSDValidationMessage;

import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlSeeAlso(value = XSDValidationMessage.class)
public class GPValidationMessage implements ValidationMessage {

    private static final long serialVersionUID = 8168211492131764019L;
    //
    private String message;
    private ISeverityMessage severityMessage;

    public GPValidationMessage() {
    }

    public GPValidationMessage(String theMessage, ISeverityMessage theSeverityMessage) {
        this.message = theMessage;
        this.severityMessage = theSeverityMessage;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * @param theMessage
     */
    @Override
    public void setMessage(String theMessage) {
        this.message = theMessage;
    }

    /**
     * @return {@link S}
     */
    @Override
    public <S extends ISeverityMessage> S getSeverityMessage() {
        return (S) this.severityMessage;
    }

    /**
     * @param theSeverityMessage
     */
    @Override
    public <S extends ISeverityMessage> void setSeverityMessage(S theSeverityMessage) {
        this.severityMessage = theSeverityMessage;
    }


    @Override
    public int compareTo(ValidationMessage o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  message = '" + message + '\'' +
                ", severityMessage = " + severityMessage +
                '}';
    }
}
