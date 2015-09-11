package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ILogicOperatorHandler {

    /**
     * @param queryDTO
     * @param filter
     * @throws IllegalStateException
     */
    void buildLogicFilterOperator(QueryDTO queryDTO, FilterType filter) throws IllegalStateException;

    /**
     * @return {@link String}
     */
    String getFilterName();

    /**
     *
     */
    interface IWFSQueryRestricrionsBuilder {

        /**
         * @param queryDTO
         * @return the Builder
         */
        IWFSQueryRestricrionsBuilder withQueryDTO(QueryDTO queryDTO);

        /**
         * @param filterType
         * @return the Builder
         */
        IWFSQueryRestricrionsBuilder withFilterType(FilterType filterType);

        /**
         * @return {@link FilterType}
         */
        FilterType build() throws Exception;
    }

    /**
     *
     */
    class WFSQueryRestrictionsBuilder implements IWFSQueryRestricrionsBuilder {

        static {
            jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
            orOperator = new OrOperatorHandler();
        }

        //
        private static final GPJAXBContextBuilder jaxbContextBuilder;
        private static final ILogicOperatorHandler orOperator;
        //
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        private FilterType filterType;
        private QueryDTO queryDTO;

        private WFSQueryRestrictionsBuilder() {
        }

        public static IWFSQueryRestricrionsBuilder builder() {
            return new WFSQueryRestrictionsBuilder();
        }

        /**
         * @param queryDTO
         * @return the Builder
         */
        @Override
        public IWFSQueryRestricrionsBuilder withQueryDTO(QueryDTO queryDTO) {
            this.queryDTO = queryDTO;
            return this;
        }

        /**
         * @param filterType
         * @return the Builder
         */
        @Override
        public IWFSQueryRestricrionsBuilder withFilterType(FilterType filterType) {
            this.filterType = filterType;
            return this;
        }

        /**
         * @return {@link FilterType}
         */
        @Override
        public FilterType build() throws Exception {
            if (this.queryDTO == null) {
                throw new IllegalArgumentException("The queryDTO must not be null.");
            }

            orOperator.buildLogicFilterOperator(this.queryDTO,
                    (this.filterType = (this.filterType == null ? new FilterType() : this.filterType)));

            if (logger.isTraceEnabled()) {
                StringWriter writer = new StringWriter();
                jaxbContextBuilder.marshal(this.filterType, writer);
                logger.trace("########################FILTER_TYPE_XML : \n{}\n", writer);
            }

            return this.filterType;
        }
    }
}
