package org.geosdi.geoplatform.gui.client.model;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPRootTreeNode extends ModelData{

    /**
     *
     * @param projectName
     */
    void setProjectName(String projectName);

    /**
     *
     * @return
     */
    String getProjectName();

    /**
     *
     * @param projectElements
     */
    void setProjectElements(int projectElements);

    /**
     *
     * @return
     */
    int getProjectElements();

    /**
     *
     * @param projectVersion
     */
    void setProjectVersion(int projectVersion);

    /**
     *
     * @return
     */
    int getProjectVersion();

    /**
     *
     * @param projectMessage
     */
    void setProjectMessage(String projectMessage);

    /**
     *
     * @return
     */
    String getProjectMessage();

    /**
     *
     * @param creationDate
     */
    void setCreationDate(String creationDate);

    /**
     *
     * @return
     */
    String getCreationDate();

    /**
     *
     * @param projectsShared
     */
    void setProjectShared(boolean projectsShared);

    /**
     *
     * @return
     */
    boolean isProjectsShared();

}
