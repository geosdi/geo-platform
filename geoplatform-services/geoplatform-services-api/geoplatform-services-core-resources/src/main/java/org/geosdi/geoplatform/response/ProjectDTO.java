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
package org.geosdi.geoplatform.response;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.response.factory.AccountDTOFactory;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@ToString
public class ProjectDTO implements GPProjectDTO {

    private static final long serialVersionUID = -5153092847474200227L;
    //
    private Long id;
    private String name;
    private Integer numberOfElements;
    private Integer version;
    private boolean shared;
    private String description;
    private String imagePath;
    private boolean internalPublic;
    private boolean externalPublic;
    private Date creationDate;
    //
    private boolean defaultProject;
    private ShortAccountDTO owner;
    //
    @XmlElementWrapper(name = "rootFolders")
    @XmlElement(name = "folder")
    private List<FolderDTO> rootFolders = new ArrayList<>();

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
        this.internalPublic = project.isInternalPublic();
        this.externalPublic = project.isExternalPublic();
        this.creationDate = project.getCreationDate();
    }

    /**
     * Convert a Project entity into a Project DTO, and set is default.
     *
     * @param project        the Project entity to convert
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
     * @param owner   the Account owner of the Project
     */
    public ProjectDTO(GPProject project, GPAccount owner) {
        this(project);
        this.owner = AccountDTOFactory.buildAccountDTO(owner);
    }

    /**
     * Convert a Project entity into a Project DTO, with its Account owner.
     *
     * @param project        the Project entity to convert
     * @param defaultProject if the Project is the default Project
     * @param owner          the Account owner of the Project
     */
    public ProjectDTO(GPProject project, Boolean defaultProject, GPAccount owner) {
        this(project, owner);
        this.defaultProject = defaultProject;
    }

    /**
     * Convert an instance of ProjectDTO to {@link GPProject}: will convert all
     * fields unless version and shared fields.
     */
    public static GPProject convertToGPProject(@Nonnull(when = NEVER) ProjectDTO projectDTO) {
        checkArgument(projectDTO != null, "The Parameter ProjectDTO must not be null");
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
        project.setInternalPublic(projectDTO.isInternalPublic());
        project.setExternalPublic(projectDTO.isExternalPublic());
        project.setShared(projectDTO.isShared());
        return project;
    }

    /**
     * @param projects
     * @return {@link List<ProjectDTO>}
     */
    public static List<ProjectDTO> convertToProjectDTOList(List<GPProject> projects) {
        return projects.stream()
                .map(p -> new ProjectDTO(p))
                .collect(toList());
    }
}