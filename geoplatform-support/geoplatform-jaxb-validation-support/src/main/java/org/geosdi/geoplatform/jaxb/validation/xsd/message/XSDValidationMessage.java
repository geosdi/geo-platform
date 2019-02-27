package org.geosdi.geoplatform.jaxb.validation.xsd.message;


import org.geosdi.geoplatform.jaxb.validation.configuration.GPValidationMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XSDValidationMessage extends GPValidationMessage implements IXSDValidationMessage {

    private static final long serialVersionUID = -7106030484216463993L;
    //
    private Integer columnNumber;
    private Integer lineNumber;

    public XSDValidationMessage() {
    }

    public XSDValidationMessage(String theMessage, ISeverityMessage theSeverityMessage,
            Integer theColumnNumber, Integer theLineNumber) {
        super(theMessage, theSeverityMessage);
        this.columnNumber = theColumnNumber;
        this.lineNumber = theLineNumber;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getColumnNumber() {
        return this.columnNumber;
    }

    /**
     * @param theColumnNumber
     */
    @Override
    public void setColumnNumber(Integer theColumnNumber) {
        this.columnNumber = theColumnNumber;
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer getLineNumber() {
        return this.lineNumber;
    }

    /**
     * @param theLineNumber
     */
    @Override
    public void setLineNumber(Integer theLineNumber) {
        this.lineNumber = theLineNumber;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  message = '" + getMessage() + '\'' +
                ", severityMessage = " + getSeverityMessage() +
                ", columnNumber = " + columnNumber +
                ", lineNumber = " + lineNumber +
                '}';
    }
}
