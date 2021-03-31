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

import org.geosdi.geoplatform.core.model.GPProject;

import java.util.Date;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPProjectBinder<TO extends GPProject, FROM extends GPProject, B extends IGPProjectBinder> extends IBinder<TO, FROM, IGPProjectBinder<TO, FROM, B>> {

    /**
     * @param nameProject
     * @return
     */
    IGPProjectBinder<TO, FROM, B> withNameProject(String nameProject);

    class GPProjectBinder extends AbstractBinder<GPProject, GPProject, IGPProjectBinder<GPProject, GPProject, IGPProjectBinder>> implements IGPProjectBinder<GPProject, GPProject, IGPProjectBinder> {

        private String projectName;

        protected GPProjectBinder(){super();}

        /**
         * @return {@link IGPProjectBinder<GPProject, GPProject, IGPProjectBinder>}
         */
        public static IGPProjectBinder<GPProject, GPProject, IGPProjectBinder> newGProjectBinder() {
            return new GPProjectBinder();
        }

        /**
         * @return
         */
        @Override
        public GPProject bind() throws Exception {
            super.checkArguments();
            checkNotNull(projectName, "The Project Name Parameter must not be null.");
            return new GPProjectFunction().apply(from);
        }

        /**
         * @param nameProject
         * @return
         */
        @Override
        public IGPProjectBinder<GPProject, GPProject, IGPProjectBinder> withNameProject(String nameProject) {
            this.projectName = nameProject;
            return self();
        }

        class GPProjectFunction implements Function<GPProject, GPProject> {

            /**
             * Applies this function to the given argument.
             *
             * @param gpProject the function argument
             * @return the function result
             */
            @Override
            public GPProject apply(GPProject gpProject) {
                GPProject gpProjectCloned = new GPProject();
                gpProjectCloned.setCreationDate(new Date());
                gpProjectCloned.setDescription(gpProject.getDescription());
                gpProjectCloned.setImagePath(gpProject.getImagePath());
                gpProjectCloned.setName(projectName);
                gpProjectCloned.setNumberOfElements(gpProject.getNumberOfElements());
                gpProjectCloned.setShared(gpProject.isShared());
                gpProjectCloned.setVersion(gpProject.getVersion());
                gpProjectCloned.setInternalPublic(gpProject.isInternalPublic());
                gpProjectCloned.setExternalPublic(gpProject.isExternalPublic());
                return gpProjectCloned;
            }
        }
    }
}
