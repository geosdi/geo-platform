package org.geosdi.geoplatform.jaxb.validation.configuration.severity;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.geosdi.geoplatform.jaxb.validation.configuration.ISeverityType.SeverityType;
import org.geosdi.geoplatform.jaxb.validation.configuration.ValidationMessage;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class WarningSeverityMessage implements ValidationMessage.ISeverityMessage<WarningSeverityMessage> {

    private final Integer degree = new Integer(0);
    private final SeverityType type = SeverityType.WARNING;

    public WarningSeverityMessage() {
    }

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
