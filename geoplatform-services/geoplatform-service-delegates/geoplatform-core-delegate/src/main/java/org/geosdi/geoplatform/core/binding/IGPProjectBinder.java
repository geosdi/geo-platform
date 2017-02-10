package org.geosdi.geoplatform.core.binding;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.core.model.GPProject;

import java.util.Date;
import java.util.function.Function;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPProjectBinder<TO extends GPProject, FROM extends GPProject, B extends IGPProjectBinder>
        extends IBinder<TO, FROM, IGPProjectBinder<TO, FROM, B>> {

    /**
     * @param nameProject
     * @return
     */
    IGPProjectBinder<TO, FROM, B> withNameProject(String nameProject);

    class GPProjectBinder extends AbstractBinder<GPProject, GPProject, IGPProjectBinder<GPProject, GPProject, IGPProjectBinder>>
            implements IGPProjectBinder<GPProject, GPProject, IGPProjectBinder> {

        private String projectName;

        protected GPProjectBinder(){super();}

        public static IGPProjectBinder<GPProject, GPProject, IGPProjectBinder> newGProjectBinder() {
            return new GPProjectBinder();
        }

        /**
         * @return
         */
        @Override
        public GPProject bind() throws Exception {
            super.checkArguments();
            Preconditions.checkNotNull(projectName, "The Project Name Parameter must not be null.");
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
                return gpProjectCloned;
            }
        }
    }
}
