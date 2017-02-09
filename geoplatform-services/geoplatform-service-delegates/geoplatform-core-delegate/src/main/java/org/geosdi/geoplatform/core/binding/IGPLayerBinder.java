package org.geosdi.geoplatform.core.binding;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.core.model.*;

import java.util.function.Function;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPLayerBinder<TO extends GPLayer, FROM extends GPLayer, B extends IGPLayerBinder>
        extends IBinder<TO, FROM, IGPLayerBinder<TO, FROM, B>> {

    /**
     * @param projectCloned
     * @return
     */
    IGPLayerBinder<TO, FROM, B> withProjectCloned(GPProject projectCloned);

    /**
     * @param folderCloned
     * @return
     */
    IGPLayerBinder<TO, FROM, B> withFolderCloned(GPFolder folderCloned);

    class GPLayerBinder extends AbstractBinder<GPLayer, GPLayer, IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder>>
            implements IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> {

        private GPProject projectCloned;
        private GPFolder folderCloned;

        protected GPLayerBinder(){super();}

        public static IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> newGPFolderBinder() {
            return new GPLayerBinder();
        }

        protected void checkArguments() {
            super.checkArguments();
            Preconditions.checkNotNull(projectCloned, "The Project Parameter must not be null.");
        }

        /**
         * @return
         */
        @Override
        public GPLayer bind() throws Exception {
            this.checkArguments();
            return new GPProjectFuncion().apply(from);
        }

        @Override
        protected IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> self() {
            return this;
        }

        /**
         * @param projectCloned
         * @return
         */
        @Override
        public IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> withProjectCloned(GPProject projectCloned) {
            this.projectCloned = projectCloned;
            return self();
        }

        /**
         * @param folderCloned
         * @return
         */
        @Override
        public IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> withFolderCloned(GPFolder folderCloned) {
            this.folderCloned = folderCloned;
            return self();
        }

        class GPProjectFuncion implements Function<GPLayer, GPLayer> {

            /**
             * Applies this function to the given argument.
             *
             * @param gpLayer the function argument
             * @return the function result
             */
            @Override
            public GPLayer apply(GPLayer gpLayer) {
                GPLayer gpLayerCloned = null;
                if (gpLayer instanceof GPRasterLayer) {
                    gpLayerCloned = new GPRasterLayer();
                    gpLayerCloned.setAbstractText(gpLayer.getAbstractText());
                    gpLayerCloned.setAlias(gpLayer.getAlias());
                    gpLayerCloned.setBbox(gpLayer.getBbox());
                    gpLayerCloned.setCached(gpLayer.isCached());
                    gpLayerCloned.setChecked(gpLayer.isChecked());
                    gpLayerCloned.setCqlFilter(gpLayer.getCqlFilter());
                    gpLayerCloned.setLayerType(gpLayer.getLayerType());
                    gpLayerCloned.setName(gpLayer.getName());
                    gpLayerCloned.setPosition(gpLayer.getPosition());
                    gpLayerCloned.setShared(gpLayer.isShared());
                    gpLayerCloned.setSingleTileRequest(gpLayer.isSingleTileRequest());
                    gpLayerCloned.setSrs(gpLayer.getSrs());
                    gpLayerCloned.setTimeFilter(gpLayer.getTimeFilter());
                    gpLayerCloned.setTitle(gpLayer.getTitle());
                    gpLayerCloned.setUrlServer(gpLayer.getUrlServer());
                    gpLayerCloned.setFolder(folderCloned);
                    ((GPRasterLayer) gpLayerCloned).setLayerInfo(((GPRasterLayer) gpLayer).getLayerInfo());
                    ((GPRasterLayer) gpLayerCloned).setMaxScale(((GPRasterLayer) gpLayer).getMaxScale());
                    ((GPRasterLayer) gpLayerCloned).setMinScale(((GPRasterLayer) gpLayer).getMinScale());
                    ((GPRasterLayer) gpLayerCloned).setOpacity(((GPRasterLayer) gpLayer).getOpacity());
                    gpLayerCloned.setProject(projectCloned);
                    ((GPRasterLayer) gpLayerCloned).setStyles(((GPRasterLayer) gpLayer).getStyles());
                }
                if (gpLayer instanceof GPVectorLayer) {
                    gpLayerCloned = new GPVectorLayer();
                    gpLayerCloned.setAbstractText(gpLayer.getAbstractText());
                    gpLayerCloned.setAlias(gpLayer.getAlias());
                    gpLayerCloned.setBbox(gpLayer.getBbox());
                    gpLayerCloned.setCached(gpLayer.isCached());
                    gpLayerCloned.setChecked(gpLayer.isChecked());
                    gpLayerCloned.setCqlFilter(gpLayer.getCqlFilter());
                    gpLayerCloned.setLayerType(gpLayer.getLayerType());
                    gpLayerCloned.setName(gpLayer.getName());
                    gpLayerCloned.setPosition(gpLayer.getPosition());
                    gpLayerCloned.setShared(gpLayer.isShared());
                    gpLayerCloned.setSingleTileRequest(gpLayer.isSingleTileRequest());
                    gpLayerCloned.setSrs(gpLayer.getSrs());
                    gpLayerCloned.setTimeFilter(gpLayer.getTimeFilter());
                    gpLayerCloned.setTitle(gpLayer.getTitle());
                    gpLayerCloned.setUrlServer(gpLayer.getUrlServer());
                    gpLayerCloned.setFolder(folderCloned);
                    ((GPVectorLayer)gpLayerCloned).setGeometry(((GPVectorLayer) gpLayer).getGeometry());
                    gpLayerCloned.setProject(projectCloned);
                }
                return gpLayerCloned;
            }
        }
    }
}
