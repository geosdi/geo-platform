package org.geosdi.geoplatform.support.async.spring.message.key.builder;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.async.spring.message.key.IGPMessageKeyValue;
import org.geosdi.geoplatform.support.async.spring.message.key.IGPMessageKeyValue.GPMessageKeyValue;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPMessageKeyValueBuilder {

    /**
     * @param key
     * @return {@link IGPMessageKeyValueBuilder}
     */
    IGPMessageKeyValueBuilder withKey(String... key);

    /**
     * @param value
     * @return {@link Object}
     */
    IGPMessageKeyValueBuilder withValue(Object... value);

    /**
     * @return {@link IGPMessageKeyValue}
     * @throws Exception
     */
    IGPMessageKeyValue[] build() throws Exception;

    /**
     *
     */
    class GPMessageKeyValueBuilder implements IGPMessageKeyValueBuilder {

        private String[] key;
        private Object[] value;

        private GPMessageKeyValueBuilder() {
        }

        public static IGPMessageKeyValueBuilder messageKeyValueBuilder() {
            return new GPMessageKeyValueBuilder();
        }

        /**
         * @param key
         * @return {@link IGPMessageKeyValueBuilder}
         */
        @Override
        public IGPMessageKeyValueBuilder withKey(String... key) {
            this.key = key;
            return self();
        }

        /**
         * @param value
         * @return {@link Object}
         */
        @Override
        public IGPMessageKeyValueBuilder withValue(Object... value) {
            this.value = value;
            return self();
        }

        /**
         * @return {@link IGPMessageKeyValue}
         * @throws Exception
         */
        @Override
        public IGPMessageKeyValue[] build() throws Exception {
            this.checkArguments();
            return IntStream.iterate(0, n -> n + 1)
                    .limit(this.key.length)
                    .boxed().map(n -> new GPMessageKeyValue(key[n], value[n]))
                    .toArray(GPMessageKeyValue[]::new);
        }

        /**
         * @throws Exception
         */
        protected void checkArguments() throws Exception {
            Preconditions.checkArgument((this.key != null) && !(this.key.length == 0),
                    "The Parameter Key must not be null or an empty Array");
            Preconditions.checkArgument((this.value != null) && !(this.value.length == 0),
                    "The Parameter Value must not be null.");
            Preconditions.checkArgument(this.value.length == this.key.length, "The Keys and " +
                    "Values lenght must be the same.");
            Arrays.stream(this.key).forEach(key -> Preconditions.checkArgument((key != null)
                    && !(key.isEmpty()), "Every Key parameter must not be null or an Empty String."));
            Arrays.stream(this.value).forEach(value -> Preconditions.checkArgument((value != null),
                    "Every Value parameter must not be null."));
        }

        protected IGPMessageKeyValueBuilder self() {
            return this;
        }
    }
}