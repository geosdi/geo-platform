/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.response.factory.AccountDTOFactory;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDTO {
    
    private Long id;
    private String name;
    private Integer numberOfElements;
    private Integer version;
    private Boolean shared;
    private String description;
    private String imagePath;
    private Date creationDate;
    //
    private Boolean defaultProject;
    private ShortAccountDTO owner;
    //
    @XmlElementWrapper(name = "rootFolders")
    @XmlElement(name = "folder")
    private List<FolderDTO> rootFolders;

    /**
     * Default constructor.
     */
    public ProjectDTO() {
    }

    /**
     * Convert a Project entity into a Project DTO.
     *
     * @param project the Project entity to convert
     */
    public ProjectDTO(GPProject project) {
        this.id = project.getId();
        this.name = project.getName();
        this.numberOfElements = project.getNumberOfElements();
        this.version = project.getVersion();
        this.shared = project.isShared();
        this.description = project.getDescription();
        this.imagePath = project.getImagePath();
        this.creationDate = project.getCreationDate();
    }

    /**
     * Convert a Project entity into a Project DTO, and set is default.
     *
     * @param project the Project entity to convert
     * @param defaultProject if the Project is the default Project
     */
    public ProjectDTO(GPProject project, Boolean defaultProject) {
        this(project);
        this.defaultProject = defaultProject;
    }

    /**
     * Convert a Project entity into a Project DTO, with its Account owner.
     *
     * @param project the Project entity to convert
     * @param owner the Account owner of the Project
     */
    public ProjectDTO(GPProject project, GPAccount owner) {
        this(project);
        this.owner = AccountDTOFactory.buildAccountDTO(owner);
    }

    /**
     * Convert a Project entity into a Project DTO, with its Account owner.
     *
     * @param project the Project entity to convert
     * @param defaultProject if the Project is the default Project
     * @param owner the Account owner of the Project
     */
    public ProjectDTO(GPProject project, Boolean defaultProject, GPAccount owner) {
        this(project, owner);
        this.defaultProject = defaultProject;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numberOfElements
     */
    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * @param numberOfElements to set
     */
    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @return the version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return the shared
     */
    public Boolean isShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     *
     * @return
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the default
     */
    public Boolean isDefault() {
        return defaultProject;
    }

    /**
     * @param default the defaultProject to set
     */
    public void setDefault(Boolean defaultProject) {
        this.defaultProject = defaultProject;
    }

    /**
     * @return the owner
     */
    public ShortAccountDTO getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(ShortAccountDTO owner) {
        this.owner = owner;
    }

    /**
     * @param rootFolders to set
     */
    public List<FolderDTO> getRootFolders() {
        return rootFolders;
    }

    /**
     * @param rootFolders the rootFolders to set
     */
    public void setRootFolders(List<FolderDTO> rootFolders) {
        this.rootFolders = rootFolders;
    }
    
    @Override
    public String toString() {
        return "ProjectDTO{"
                + "id=" + id
                + ", name=" + name
                + ", numberOfElements=" + numberOfElements
                + ", version=" + version
                + ", shared=" + shared
                + ", description=" + description
                + ", imagePath=" + imagePath
                + ", default=" + defaultProject
                + ", owner=" + owner
                + ", rootFolders=" + rootFolders + '}';
    }

    /**
     * Convert an instance of ProjectDTO to {@link GPProject}: will convert all
     * fields unless version and shared fields.
     */
    public static GPProject convertToGPProject(ProjectDTO projectDTO) {
        GPProject project = new GPProject();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        if (projectDTO.getNumberOfElements() != null) {
            project.setNumberOfElements(projectDTO.getNumberOfElements());
        }
        if (projectDTO.getDescription() != null) {
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getImagePath() != null) {
            project.setImagePath(projectDTO.getImagePath());
        }
        
        return project;
    }
    
    public static List<ProjectDTO> convertToProjectDTOList(
            List<GPProject> projects) {
        List<ProjectDTO> projectsDTO = Lists.<ProjectDTO>newArrayListWithExpectedSize(
                projects.size());
        
        for (GPProject project : projects) {
            projectsDTO.add(new ProjectDTO(project));
        }
        
        return projectsDTO;
    }
}
