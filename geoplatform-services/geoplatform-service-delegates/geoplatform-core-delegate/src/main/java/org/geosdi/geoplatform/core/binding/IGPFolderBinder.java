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

import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPProject;

import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPFolderBinder<TO extends GPFolder, FROM extends GPFolder, B extends IGPFolderBinder> extends IBinder<TO, FROM, IGPFolderBinder<TO, FROM, B>> {

    /**
     * @param projectCloned
     * @return
     */
    IGPFolderBinder<TO, FROM, B> withProjectCloned(GPProject projectCloned);

    /**
     * @param folderCloned
     * @return
     */
    IGPFolderBinder<TO, FROM, B> withParentCloned(GPFolder folderCloned);

    class GPFolderBinder extends AbstractBinder<GPFolder, GPFolder, IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder>> implements IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> {

        private GPProject projectCloned;
        private GPFolder folderCloned;

        protected GPFolderBinder(){super();}

        public static IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> newGPFolderBinder() {
            return new GPFolderBinder();
        }

        protected void checkArguments() {
            super.checkArguments();
            checkNotNull(projectCloned, "The Project Parameter must not be null.");
        }

        /**
         * @return
         */
        @Override
        public GPFolder bind() throws Exception {
            this.checkArguments();
            return new GPProjectFuncion().apply(from);
        }

        @Override
        protected IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> self() {
            return this;
        }

        /**
         * @param projectCloned
         * @return
         */
        @Override
        public IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> withProjectCloned(GPProject projectCloned) {
            this.projectCloned = projectCloned;
            return self();
        }

        /**
         * @param folderCloned
         * @return
         */
        @Override
        public IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> withParentCloned(GPFolder folderCloned) {
            this.folderCloned = folderCloned;
            return self();
        }

        class GPProjectFuncion implements Function<GPFolder, GPFolder> {

            /**
             * Applies this function to the given argument.
             *
             * @param gpFolder the function argument
             * @return the function result
             */
            @Override
            public GPFolder apply(GPFolder gpFolder) {
                GPFolder gpFolderCloned = new GPFolder();
                gpFolderCloned.setChecked(gpFolder.isChecked());
                gpFolderCloned.setExpanded(gpFolder.isExpanded());
                gpFolderCloned.setName(gpFolder.getName());
                gpFolderCloned.setNumberOfDescendants(gpFolder.getNumberOfDescendants());
                gpFolderCloned.setParent(gpFolder.getParent());
                gpFolderCloned.setPosition(gpFolder.getPosition());
                gpFolderCloned.setProject(projectCloned);
                gpFolderCloned.setParent(folderCloned);
                return gpFolderCloned;
            }
        }
    }
}
