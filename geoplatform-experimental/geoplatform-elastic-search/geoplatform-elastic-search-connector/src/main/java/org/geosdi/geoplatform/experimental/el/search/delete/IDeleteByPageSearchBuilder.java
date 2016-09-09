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
