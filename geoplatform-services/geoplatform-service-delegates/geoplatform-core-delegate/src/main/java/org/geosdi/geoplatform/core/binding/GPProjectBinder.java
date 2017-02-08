package org.geosdi.geoplatform.core.binding;

import org.geosdi.geoplatform.core.model.GPProject;

import java.util.Date;
import java.util.function.Function;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPProjectBinder extends IBinder.AbstractBinder<GPProject, GPProject, IBinder<GPProject, GPProject, GPProjectBinder>> {

    public static GPProjectBinder newGProjectBinder() {
        return new GPProjectBinder();
    }

    /**
     * @return
     */
    @Override
    public GPProject bind() throws Exception {
        super.checkArguments();
        return new GPProjectFunction().apply(from);
    }

    @Override
    protected IBinder self() {
        return this;
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
            gpProjectCloned.setName(gpProject.getName().concat("-copy"));
            gpProjectCloned.setNumberOfElements(gpProject.getNumberOfElements());
            gpProjectCloned.setShared(gpProject.isShared());
            gpProjectCloned.setVersion(gpProject.getVersion());
            return gpProjectCloned;
        }
    }
}
