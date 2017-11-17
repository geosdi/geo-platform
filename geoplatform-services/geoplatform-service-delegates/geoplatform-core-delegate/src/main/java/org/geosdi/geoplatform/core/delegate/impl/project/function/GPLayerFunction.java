package org.geosdi.geoplatform.core.delegate.impl.project.function;

import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;

import java.util.function.Function;

import static org.geosdi.geoplatform.core.binding.IGPLayerBinder.GPLayerBinder.newGPFolderBinder;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPLayerFunction implements Function<GPLayer, GPLayer> {

    private GPProject projectCloned;
    private GPLayerDAO layerDAO;
    private GPFolder folder;

    public GPLayerFunction(GPProject projectCloned, GPLayerDAO layerDAO, GPFolder folder) {
        this.projectCloned = projectCloned;
        this.layerDAO = layerDAO;
        this.folder = folder;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param gpLayer the function argument
     * @return the function result
     */
    @Override
    public GPLayer apply(GPLayer gpLayer) {
        try {
            GPLayer gpLayerCloned = newGPFolderBinder()
                    .withFrom(gpLayer)
                    .withFolderCloned(folder)
                    .withProjectCloned(projectCloned)
                    .bind();
            layerDAO.persist(gpLayerCloned);
            return gpLayerCloned;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}
