/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.server.request.v110.query.responsibility;

import org.geosdi.geoplatform.connector.server.request.v110.query.repository.QueryRestrictionRepository;
import org.geosdi.geoplatform.connector.server.request.v110.query.repository.WFSQueryRestrictionRepository;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ILogicOperatorHandler {

    WFSQueryRestrictionRepository<ComparisonOpsType> QUERY_RESTRICTION_REPOSITORY = new QueryRestrictionRepository();

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
