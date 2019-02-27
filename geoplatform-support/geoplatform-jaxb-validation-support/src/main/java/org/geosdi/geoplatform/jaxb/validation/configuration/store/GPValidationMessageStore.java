package org.geosdi.geoplatform.jaxb.validation.configuration.store;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

import javax.xml.bind.annotation.*;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.util.Collections.sort;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.StreamSupport.stream;
import static org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.logic.ErrorWarningPredicate.toLogicErrorPredicate;
import static org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.logic.LogicWarningPredicate.toLogicWarningPredicate;
import static org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.xsd.XSDErrorPredicate.toXSDErrorPredicate;
import static org.geosdi.geoplatform.jaxb.validation.configuration.store.predicate.xsd.XSDWarningPredicate.toXSDWarningPredicate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPValidationMessageStore")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder = {"valid", "validationMessages"})
public class GPValidationMessageStore implements ValidationMessageStore {

    private static final long serialVersionUID = -199144004797302689L;
    //
    private List<ValidationMessage> validationMessages;
    private Long errorMessagesXSD;
    private Long warningMessagesXSD;
    private Long errorMessagesLogic;
    private Long warningMessagesLogic;

    public GPValidationMessageStore() {
    }

    public GPValidationMessageStore(List<ValidationMessage> theValidationMessages) {
        this.validationMessages = theValidationMessages;
    }

    /**
     * @return {@link List < ValidationMessage >}
     */
    @Override
    public List<ValidationMessage> getValidationMessages() {
        return this.validationMessages;
    }

    /**
     * @param theValidationMessages
     */
    @Override
    public void setValidationMessages(List<ValidationMessage> theValidationMessages) {
        this.validationMessages = theValidationMessages;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetValidationMessages() {
        return (this.validationMessages != null);
    }

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    @Override
    public Boolean hasValidationMessages() {
        return ((isSetValidationMessages()) && !(this.validationMessages.isEmpty()));
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean hasErrorMessagesXSD() {
        return getErrorMessagesXSD() > 0;
    }

    @Override
    public Boolean hasWarningMessagesXSD() {
        return getWarningMessagesXSD() > 0;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getErrorMessagesXSD() {
        return this.errorMessagesXSD = (this.errorMessagesXSD == null ? (isSetValidationMessages()
                ? this.validationMessages.stream()
                .filter(toXSDErrorPredicate())
                .count() : new Long(0)) : this.errorMessagesXSD);
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getWarningMessagesXSD() {
        return this.warningMessagesXSD = (this.warningMessagesXSD == null ? (isSetValidationMessages()
                ? this.validationMessages.stream()
                .filter(toXSDWarningPredicate())
                .count() : new Long(0)) : this.warningMessagesXSD);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean hasErrorMessagesLogic() {
        return getErrorMessagesLogic() > 0;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean hasWarningMessagesLogic() {
        return getWarningMessagesLogic() > 0;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getErrorMessagesLogic() {
        return this.errorMessagesLogic = (this.errorMessagesLogic == null ? (isSetValidationMessages()
                ? this.validationMessages.stream()
                .filter(toLogicErrorPredicate())
                .count() : new Long(0)) : this.errorMessagesLogic);
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getWarningMessagesLogic() {
        return this.warningMessagesLogic = (this.warningMessagesLogic == null ? (isSetValidationMessages()
                ? this.validationMessages.stream()
                .filter(toLogicWarningPredicate())
                .count() : new Long(0)) : this.warningMessagesLogic);
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValidXSD() {
        return !hasErrorMessagesXSD();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValidLogic() {
        return !hasErrorMessagesLogic();
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isWarningLogic() {
        return hasWarningMessagesLogic();
    }

    /**
     * @param validationMessage
     */
    @Override
    public void addValidationMessage(ValidationMessage validationMessage) {
        if (this.validationMessages == null)
            this.validationMessages = Lists.newArrayList();
        this.validationMessages.add(validationMessage);
    }

    /**
     * @param validationMessages
     */
    @Override
    public void addValidationMessage(Iterable<ValidationMessage> validationMessages) {
        stream(validationMessages.spliterator(), FALSE).forEach(this::addValidationMessage);
    }

    /**
     * <p>Sort {@link ValidationMessage} for {@link ValidationMessage.ISeverityMessage#getDegree()}</p>
     */
    @Override
    public void sortMessages() {
        sort(this.validationMessages, naturalOrder());
    }

    /**
     *
     */
    @Override
    public void resetStore() {
        if (this.validationMessages != null)
            this.validationMessages.clear();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  validationMessages = " + validationMessages +
                '}';
    }
}