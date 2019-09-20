package org.geosdi.geoplatform.jaxb.validation.configuration.severity;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class ErrorSeverityMessage implements ValidationMessage.ISeverityMessage<ErrorSeverityMessage> {

    private final Integer degree = new Integer(1);
    private final SeverityType type = SeverityType.ERROR;

    /**
     * @return {@link Integer}
     */
    @Override
    public final Integer getDegree() {
        return this.degree;
    }

    /**
     * @return {@link SeverityType}
     */
    @Override
    public final SeverityType getType() {
        return this.type;
    }
}
