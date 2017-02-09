package org.geosdi.geoplatform.core.binding;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPProject;

import java.util.function.Function;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPFolderBinder<TO extends GPFolder, FROM extends GPFolder, B extends IGPFolderBinder>
        extends IBinder<TO, FROM, IGPFolderBinder<TO, FROM, B>> {

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

    class GPFolderBinder extends AbstractBinder<GPFolder, GPFolder, IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder>>
            implements IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> {

        private GPProject projectCloned;
        private GPFolder folderCloned;

        protected GPFolderBinder(){super();}

        public static IGPFolderBinder<GPFolder, GPFolder, IGPFolderBinder> newGPFolderBinder() {
            return new GPFolderBinder();
        }

        protected void checkArguments() {
            super.checkArguments();
            Preconditions.checkNotNull(projectCloned, "The Project Parameter must not be null.");
            // Preconditions.checkNotNull(folderCloned, "The Folder Parameter must not be null.");
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
