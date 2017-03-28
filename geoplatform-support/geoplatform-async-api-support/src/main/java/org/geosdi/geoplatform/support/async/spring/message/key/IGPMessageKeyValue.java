package org.geosdi.geoplatform.support.async.spring.message.key;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPMessageKeyValue {

    /**
     * @return {@link String}
     */
    String getMessageKey();

    /**
     * @return {@link String}
     */
    Object getMessageValue();

    /**
     *
     */
    @Immutable
    class GPMessageKeyValue implements IGPMessageKeyValue {

        private final String messageKey;
        private final Object messageValue;

        public GPMessageKeyValue(String theMessageKey, Object theMessageValue) {
            Preconditions.checkArgument((theMessageKey != null) && !(theMessageKey.isEmpty()),
                    "The Parameter MessageKey must not be null or an empty String");
            Preconditions.checkArgument((theMessageValue != null),
                    "The Parameter MessageValue must not be null.");
            this.messageKey = theMessageKey;
            this.messageValue = theMessageValue;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getMessageKey() {
            return this.messageKey;
        }

        /**
         * @return {@link String}
         */
        @Override
        public Object getMessageValue() {
            return this.messageValue;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " messageKey = '" + messageKey + '\'' +
                    ", messageValue = '" + messageValue + '\'' +
                    '}';
        }
    }
}
