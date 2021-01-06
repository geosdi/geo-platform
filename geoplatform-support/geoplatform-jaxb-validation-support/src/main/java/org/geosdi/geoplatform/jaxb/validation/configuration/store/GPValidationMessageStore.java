/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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