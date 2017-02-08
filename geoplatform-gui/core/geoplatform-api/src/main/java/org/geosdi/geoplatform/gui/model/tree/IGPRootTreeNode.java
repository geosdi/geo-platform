package org.geosdi.geoplatform.gui.model.tree;

import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPRootTreeNode extends GPCompositeTreeModelConverter<GPFolderClientInfo>, IGPNode {

    /**
     *
     * @param projectID
     */
    void setProjectID(long projectID);

    /**
     *
     * @return
     */
    long getProjectID();

    /**
     * @param projectName
     */
    void setProjectName(String projectName);

    /**
     * @return
     */
    String getProjectName();

    /**
     * @param projectElements
     */
    void setProjectElements(int projectElements);

    /**
     * @return
     */
    int getProjectElements();

    /**
     * @param projectVersion
     */
    void setProjectVersion(int projectVersion);

    /**
     * @return
     */
    int getProjectVersion();

    /**
     * @param projectMessage
     */
    void setProjectMessage(String projectMessage);

    /**
     * @return
     */
    String getProjectMessage();

    /**
     * @param creationDate
     */
    void setCreationDate(String creationDate);

    /**
     * @return
     */
    String getCreationDate();

    /**
     * @param projectsShared
     */
    void setProjectShared(boolean projectsShared);

    /**
     * @return {@link Boolean}
     */
    Boolean isProjectsShared();
}
