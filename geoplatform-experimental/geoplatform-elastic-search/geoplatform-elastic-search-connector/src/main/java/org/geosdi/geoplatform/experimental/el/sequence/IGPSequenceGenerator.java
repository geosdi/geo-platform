package org.geosdi.geoplatform.experimental.el.sequence;

import org.geosdi.geoplatform.experimental.el.api.model.sequence.IGPSequence;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPSequenceGenerator extends InitializingBean {

    /**
     * @throws Exception
     */
    void deleteSequence() throws Exception;

    /**
     * @return {@link Long}
     * @throws Exception
     */
    Long nextID() throws Exception;

    /**
     * @return {@link String}
     */
    String getSequenceID();

    /**
     * @return {@link IGPSequence}
     */
    IGPSequence getSequenceModel();

    /**
     * <p>
     * Sequence Settings Interface to define both IndexName and IndexType
     * </p>
     */
    interface GPSequenceSettings {

        /**
         * @return {@link String} Index Name
         */
        String getSequenceName();

        /**
         * @return {@link String} Index Type
         */
        String getSequenceType();

    }
}
