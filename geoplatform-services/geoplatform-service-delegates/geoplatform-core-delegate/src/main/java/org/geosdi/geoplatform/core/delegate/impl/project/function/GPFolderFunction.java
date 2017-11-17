package org.geosdi.geoplatform.core.delegate.impl.project.function;

import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPProject;

import java.util.function.Function;

import static org.geosdi.geoplatform.core.binding.IGPFolderBinder.GPFolderBinder.newGPFolderBinder;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPFolderFunction implements Function<GPFolder, GPFolder> {

    private GPProject projectCloned;
    private GPFolderDAO folderDAO;
    private GPFolder folderParent;

    public GPFolderFunction(GPProject projectCloned, GPFolderDAO folderDAO, GPFolder folderParent) {
        this.projectCloned = projectCloned;
        this.folderDAO = folderDAO;
        this.folderParent = folderParent;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param gpFolder the function argument
     * @return the function result
     */
    @Override
    public GPFolder apply(GPFolder gpFolder) {
        try {
            GPFolder folderCloned = newGPFolderBinder()
                    .withFrom(gpFolder)
                    .withParentCloned(folderParent)
                    .withProjectCloned(projectCloned)
                    .bind();
            folderDAO.persist(folderCloned);
            return folderCloned;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
