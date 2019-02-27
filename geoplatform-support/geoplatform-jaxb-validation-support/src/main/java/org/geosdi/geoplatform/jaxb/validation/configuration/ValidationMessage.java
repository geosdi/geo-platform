package org.geosdi.geoplatform.jaxb.validation.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType;
import org.geosdi.geoplatform.jaxb.validation.configuration.adapter.ValidationMessageAdapter;
import org.geosdi.geoplatform.jaxb.validation.configuration.severity.ErrorSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.severity.FatalErrorSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.configuration.severity.WarningSeverityMessage;
import org.geosdi.geoplatform.jaxb.validation.xsd.message.IXSDValidationMessage;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlJavaTypeAdapter(value = ValidationMessageAdapter.class)
@XmlSeeAlso(value = {GPValidationMessage.class, IXSDValidationMessage.class})
public interface ValidationMessage extends Serializable, Comparable<ValidationMessage> {

    /**
     * @return {@link String}
     */
    String getMessage();

    /**
     * @param theMessage
     */
    void setMessage(String theMessage);

    /**
     * @param <S>
     * @return {@link S}
     */
    <S extends ISeverityMessage> S getSeverityMessage();

    /**
     * @param theSeverityMessage
     * @param <S>
     */
    <S extends ISeverityMessage> void setSeverityMessage(S theSeverityMessage);

    /**
     * @return {@link ValidationType}
     */
    default ValidationType getValidationType() {
        return ValidationType.LOGIC;
    }

    /**
     *
     */
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXISTING_PROPERTY,
            property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = WarningSeverityMessage.class, name = "WARNING"),
            @JsonSubTypes.Type(value = ErrorSeverityMessage.class, name = "ERROR"),
            @JsonSubTypes.Type(value = FatalErrorSeverityMessage.class, name = "FATAL_ERROR")})
    interface ISeverityMessage<S extends ISeverityMessage> extends Comparable<S> {

        /**
         * @return {@link Integer}
         */
        Integer getDegree();

        /**
         * @return {@link SeverityType}
         */
        SeverityType getType();

        /**
         * @param o
         * @return
         */
        default int compareTo(S o) {
            return o.getDegree().compareTo(getDegree());
        }
    }

    /**
     *
     */
    enum ValidationType {
        XSD, LOGIC;
    }
}
