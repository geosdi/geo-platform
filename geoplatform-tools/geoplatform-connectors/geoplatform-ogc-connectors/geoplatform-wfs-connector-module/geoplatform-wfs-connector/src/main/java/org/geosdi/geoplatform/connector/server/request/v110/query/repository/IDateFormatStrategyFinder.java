package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Arrays.asList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IDateFormatStrategyFinder extends Serializable {

    /**
     * @param attributeDTO
     * @return {@link List<Object>}
     */
    List<Object> findDateFormat(@Nonnull(when = NEVER) AttributeDTO attributeDTO);

    class DateFormatStrategyPatter implements IDateFormatStrategyFinder {

        protected DateFormatStrategyPatter() {
        }

        /**
         * @param attributeDTO
         * @return {@link List<Object>}
         */
        @Override
        public List<Object> findDateFormat(@Nonnull(when = NEVER) AttributeDTO attributeDTO) {
            checkArgument(attributeDTO != null, "The Parameter attributeDTO must not be null.");
            return (attributeDTO.getType().equalsIgnoreCase("date") ? asList("yyyy-MM-dd") : asList("yyyy-MM-dd HH:mm"));
        }
    }
}