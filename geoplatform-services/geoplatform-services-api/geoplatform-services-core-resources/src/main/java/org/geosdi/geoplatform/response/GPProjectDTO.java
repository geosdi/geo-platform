package org.geosdi.geoplatform.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPProjectDTO extends Serializable {

    /**
     * @return {@link Long}
     */
    Long getId();

    /**
     * @param theId
     */
    void setId(Long theId);

    /**
     * @return {@link String}
     */
    String getName();

    /**
     * @param theName
     */
    void setName(String theName);

    /**
     * @return {@link Integer}
     */
    Integer getNumberOfElements();

    /**
     * @param numberOfElements
     */
    void setNumberOfElements(Integer numberOfElements);

    /**
     * @return {@link Integer}
     */
    Integer getVersion();

    /**
     * @param theVersion
     */
    void setVersion(Integer theVersion);

    /**
     * @return {@link Boolean}
     */
    boolean isShared();

    /**
     * @param theShared
     */
    void setShared(boolean theShared);

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * @param theDescription
     */
    void setDescription(String theDescription);

    /**
     * @return {@link String}
     */
    String getImagePath();

    /**
     * @param theImagePath
     */
    void setImagePath(String theImagePath);

    /**
     * @return {@link Date}
     */
    Date getCreationDate();

    /**
     * @param theCreationDate
     */
    void setCreationDate(Date theCreationDate);

    /**
     * @return {@link Boolean}
     */
    boolean isDefaultProject();

    /**
     * @param theDefaultProject
     */
    void setDefaultProject(boolean theDefaultProject);

    /**
     * @param <Owner>
     * @return {@link Owner}
     */
    <Owner extends ShortAccountDTO> Owner getOwner();

    /**
     * @param theOwner
     * @param <Owner>
     */
    <Owner extends ShortAccountDTO> void setOwner(Owner theOwner);

    /**
     * @return {@link List<FolderDTO>}
     */
    List<FolderDTO> getRootFolders();

    /**
     * @param theRootFolders
     */
    void setRootFolders(List<FolderDTO> theRootFolders);
}