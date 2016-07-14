package org.geosdi.geoplatform.experimental.openam.api.model.error;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class OpenAMErrorResponse implements IOpenAMErrorResponse {

    private static final long serialVersionUID = -3256451063274550309L;
    //
    private String code;
    private String reason;
    private String message;

    public OpenAMErrorResponse() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * @param theCode
     */
    @Override
    public void setCode(String theCode) {
        this.code = theCode;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getReason() {
        return this.reason;
    }

    /**
     * @param theReason
     */
    @Override
    public void setReason(String theReason) {
        this.reason = theReason;
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  code = '" + code + '\'' +
                ", reason = '" + reason + '\'' +
                ", message = '" + message + '\'' +
                '}';
    }
}
