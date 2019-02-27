package org.geosdi.geoplatform.jaxb.validation.configuration.store;

import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ValidationMessageStore extends ValidationMessageStoreSubJoin {

    /**
     * @param validationMessages
     */
    void addValidationMessage(Iterable<ValidationMessage> validationMessages);

    /**
     * @return {@link List<ValidationMessage>}
     */
    List<ValidationMessage> getValidationMessages();

    /**
     * @param theValidationMessages
     */
    void setValidationMessages(List<ValidationMessage> theValidationMessages);

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean isSetValidationMessages();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean hasValidationMessages();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean hasErrorMessagesXSD();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean hasWarningMessagesXSD();

    /**
     * @return {@link Long}
     */
    Long getErrorMessagesXSD();

    /**
     * @return {@link Long}
     */
    Long getWarningMessagesXSD();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean hasErrorMessagesLogic();

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    Boolean hasWarningMessagesLogic();

    /**
     * @return {@link Long}
     */
    Long getErrorMessagesLogic();

    /**
     * @return {@link Long}
     */
    Long getWarningMessagesLogic();

    /**
     * @return {@link Boolean}
     */
    Boolean isValidXSD();

    /**
     * @return {@link Boolean}
     */
    Boolean isValidLogic();

    /**
     * @return {@link Boolean}
     */
    Boolean isWarningLogic();

    /**
     * <p>Sort {@link ValidationMessage} for {@link ValidationMessage.ISeverityMessage#getDegree()}</p>
     */
    void sortMessages();

    /**
     *
     */
    void resetStore();
}
