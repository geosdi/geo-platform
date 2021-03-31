/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.core.binding;

import org.geosdi.geoplatform.core.model.*;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPLayerBinder<TO extends GPLayer, FROM extends GPLayer, B extends IGPLayerBinder> extends IBinder<TO, FROM, IGPLayerBinder<TO, FROM, B>> {

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

    class GPLayerBinder extends AbstractBinder<GPLayer, GPLayer, IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder>> implements IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> {

        private GPProject projectCloned;
        private GPFolder folderCloned;

        protected GPLayerBinder(){super();}

        public static IGPLayerBinder<GPLayer, GPLayer, IGPLayerBinder> newGPFolderBinder() {
            return new GPLayerBinder();
        }

        protected void checkArguments() {
            super.checkArguments();
            checkNotNull(projectCloned, "The Project Parameter must not be null.");
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
