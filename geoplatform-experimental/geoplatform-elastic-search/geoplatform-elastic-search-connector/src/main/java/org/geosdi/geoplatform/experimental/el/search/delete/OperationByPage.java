/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, operation this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el.search.delete;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.TimeValue;
import org.geosdi.geoplatform.experimental.el.dao.GPElasticSearchUpdateHandler;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface OperationByPage {

    /**
     * @param builder
     * @param <Builder>
     * @return
     * @throws Exception
     */
    <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder)
            throws Exception;

    /**
     * @return {@link Integer}
     */
    Integer getFrom();

    /**
     * @return {@link Integer}
     */
    Integer getSize();

    /**
     *
     * @return {@link IGPOperationAsyncType.OperationAsyncEnum}
     */
    IGPOperationAsyncType.OperationAsyncEnum getOperation();

    /**
     *
     * @return {@link GPElasticSearchUpdateHandler}
     */
    GPElasticSearchUpdateHandler getUpdateHandler();

    /**
     * @param <P>
     * @return {@link P}
     */
    <P extends GPPageableElasticSearchDAO.PageAsync> P getPage();

    /**
     *
     */
    interface IOperationByPageResult {

        /**
         * <p>The total Number of Elements deleted</p>
         *
         * @return {@link Integer}
         */
        Integer getElementsComputed();

        /**
         * @return {@link TimeValue}
         */
        TimeValue getExecutionTime();
    }

    /**
     *
     */
    @Immutable
    class OperationByPageSearch implements OperationByPage {

        private final GPPageableElasticSearchDAO.PageAsync page;

        public OperationByPageSearch(GPPageableElasticSearchDAO.PageAsync thePage) {
            Preconditions.checkNotNull(thePage, "The Parameter Page must not be null.");
            this.page = thePage;
        }

        /**
         * @param builder
         * @return
         * @throws Exception
         */
        @Override
        public <Builder extends SearchRequestBuilder> Builder buildPage(Builder builder) throws Exception {
            return this.page.buildPage(builder);
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getFrom() {
            return this.page.getFrom();
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getSize() {
            return this.page.getSize();
        }

        /**
         *
         * @return {@link GPElasticSearchUpdateHandler}
         */
        @Override
        public GPElasticSearchUpdateHandler getUpdateHandler() {
            return this.page.getGpElasticSearchUpdateHandler();
        }

        /**
         *
         * @return {@link IGPOperationAsyncType.OperationAsyncEnum}
         */
        @Override
        public IGPOperationAsyncType.OperationAsyncEnum getOperation() {
            return this.page.getOperationType();
        }

        /**
         * @return {@link P}
         */
        @Override
        public <P extends GPPageableElasticSearchDAO.PageAsync> P getPage() {
            return (P) this.page;
        }
    }

    /**
     *
     */
    @Immutable
    class OperationByPageSearchDecorator extends OperationByPageSearch {

        private final Long size;

        public OperationByPageSearchDecorator(GPPageableElasticSearchDAO.PageAsync thePage, Long size) {
            super(thePage);
            this.size = size;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getSize() {
            return this.size.intValue();
        }
    }

    /**
     *
     */
    @Immutable
    class OperationByPageResult implements IOperationByPageResult {

        private final Integer elementsComputed;
        private final TimeValue executionTime;

        public OperationByPageResult(Integer theElementsComputed, TimeValue theExecutionTime) {
            this.elementsComputed = theElementsComputed;
            this.executionTime = theExecutionTime;
        }

        /**
         * @return {@link Integer}
         */
        @Override
        public Integer getElementsComputed() {
            return this.elementsComputed;
        }

        /**
         * @return {@link TimeValue}
         */
        @Override
        public TimeValue getExecutionTime() {
            return this.executionTime;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " elementsComputed = " + elementsComputed +
                    ", executionTime = " + executionTime +
                    '}';
        }
    }
}