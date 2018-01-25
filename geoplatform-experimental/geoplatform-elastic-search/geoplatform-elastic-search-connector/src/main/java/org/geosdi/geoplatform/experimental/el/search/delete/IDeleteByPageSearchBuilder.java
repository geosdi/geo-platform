/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.search.delete;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.experimental.el.dao.GPPageableElasticSearchDAO;

import static org.geosdi.geoplatform.experimental.el.search.delete.DeleteByPage.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IDeleteByPageSearchBuilder {

    /**
     * @param thePage
     * @param <P>
     * @return {@link IDeleteByPageSearchBuilder}
     */
    <P extends GPPageableElasticSearchDAO.Page> IDeleteByPageSearchBuilder withPage(P thePage);

    /**
     * @return {@link DeleteByPage}
     * @throws Exception
     */
    DeleteByPage build() throws Exception;

    /**
     *
     */
    class DeleteByPageSearchBuilder implements IDeleteByPageSearchBuilder {

        private GPPageableElasticSearchDAO.Page page;

        private DeleteByPageSearchBuilder() {
        }

        public static IDeleteByPageSearchBuilder newInstance() {
            return new DeleteByPageSearchBuilder();
        }

        /**
         * @param thePage
         * @return {@link IDeleteByPageSearchBuilder}
         */
        @Override
        public <P extends GPPageableElasticSearchDAO.Page> IDeleteByPageSearchBuilder withPage(P thePage) {
            this.page = thePage;
            return self();
        }

        /**
         * @return {@link DeleteByPage}
         * @throws Exception
         */
        @Override
        public DeleteByPage build() throws Exception {
            Preconditions.checkNotNull(this.page, "The Parameter Page must not be null.");
            return new DeleteByPageSearch(this.page);
        }

        /**
         * @return {@link IDeleteByPageSearchBuilder}
         */
        protected IDeleteByPageSearchBuilder self() {
            return this;
        }
    }
}
