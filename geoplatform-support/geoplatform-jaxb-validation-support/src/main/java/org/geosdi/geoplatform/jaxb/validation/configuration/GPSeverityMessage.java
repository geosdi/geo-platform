package org.geosdi.geoplatform.jaxb.validation.configuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType;

import static org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GPSeverityMessage implements ValidationMessage.ISeverityMessage<GPSeverityMessage> {

    WARNING_MESSAGE(0, WARNING),
    ERROR_MESSAGE(1, ERROR),
    FATAL_ERROR_MESSAGE(2, FATAL_ERROR);

    private final Integer degree;
    private final SeverityType type;

    /**
     * @param theDegree
     * @param theType
     */
    GPSeverityMessage(Integer theDegree, SeverityType theType) {
        this.degree = theDegree;
        this.type = theType;
    }


    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getDegree() {
        return this.degree;
    }

    /**
     * @return {@link SeverityType}
     */
    @Override
    public SeverityType getType() {
        return this.type;
    }

    /**
     * @param value
     * @return {@link GPSeverityMessage}
     * @throws IllegalArgumentException
     */
    public static GPSeverityMessage fromValue(Integer value) throws IllegalArgumentException {
        switch (value) {
            case 0:
                return WARNING_MESSAGE;
            case 1:
                return ERROR_MESSAGE;
            case 2:
                return FATAL_ERROR_MESSAGE;
            default:
                throw new IllegalArgumentException("No SeverityMessage found for value : " + value);
        }
    }

    /**
     * @param value
     * @return {@link GPSeverityMessage}
     * @throws IllegalArgumentException
     */
    public static GPSeverityMessage fromValue(SeverityType value) throws IllegalArgumentException {
        switch ((value != null) ? value : ERROR) {
            case WARNING:
                return WARNING_MESSAGE;
            case ERROR:
                return ERROR_MESSAGE;
            case FATAL_ERROR:
                return FATAL_ERROR_MESSAGE;
            default:
                throw new IllegalArgumentException("No SeverityMessage found for value : " + value);
        }
    }
}