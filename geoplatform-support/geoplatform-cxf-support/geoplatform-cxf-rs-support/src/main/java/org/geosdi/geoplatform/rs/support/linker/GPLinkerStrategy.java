package org.geosdi.geoplatform.rs.support.linker;

import org.geosdi.geoplatform.rs.support.request.linker.GPBaseLinkerRequest;
import org.geosdi.geoplatform.rs.support.request.linker.IGPLinkerType;
import org.geosdi.geoplatform.rs.support.response.GPStatusResponse;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPLinkerStrategy<Request extends GPBaseLinkerRequest> extends InitializingBean {

    /**
     * @param theRequest
     * @param <Status>
     * @return {@link Status}
     * @throws Exception
     */
    <Status extends GPStatusResponse> Status link(@Nonnull(when = NEVER) Request theRequest) throws Exception;

    /**
     * @return {@link StrategyKey}
     */
    <StrategyKey extends IGPLinkerStrategyKey> StrategyKey getStrategyKey();

    /**
     * @param <StrategyType>
     * @return {@link StrategyType}
     */
    <StrategyType extends GPLinkerStrategyType> StrategyType getStrategyType();

    /**
     * @return {@link Class<Request>}
     */
    Class<Request> forRequest();

    interface IGPLinkerStrategyKey extends Serializable {

        /**
         * @return {@link IGPLinkerType}
         */
        IGPLinkerType getKey();

        /**
         * @param theValue
         * @return {@link IGPLinkerStrategyKey}
         */
        static IGPLinkerStrategyKey of(@Nonnull(when = NEVER) IGPLinkerType theValue) {
            return new GPLinkerStrategyKey(theValue);
        }

        class GPLinkerStrategyKey implements IGPLinkerStrategyKey {

            private static final long serialVersionUID = 2962343036787785310L;
            //
            private final IGPLinkerType key;

            /**
             * @param theKey
             */
            protected GPLinkerStrategyKey(@Nonnull(when = NEVER) IGPLinkerType theKey) {
                checkArgument(theKey != null, "The Parameter Key must not be null.");
                this.key = theKey;
            }

            /**
             * @return {@link IGPLinkerType}
             */
            @Override
            public IGPLinkerType getKey() {
                return this.key;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof GPLinkerStrategyKey)) return false;
                GPLinkerStrategyKey that = (GPLinkerStrategyKey) o;
                return key != null ? key.equals(that.key) : that.key == null;
            }

            @Override
            public int hashCode() {
                return key != null ? key.hashCode() : 0;
            }

            @Override
            public String toString() {
                return getClass().getSimpleName() + "{"
                        + "key = " + key +
                        '}';
            }
        }
    }

    interface GPLinkerStrategyType {

        /**
         * @return {@link String}
         */
        String getStrategyType();
    }
}