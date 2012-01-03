/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.responce;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPProject;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectDTO {

    private Long id;
    private String name;
    private Integer numberOfElements;
    //
    @XmlElementWrapper(name = "rootFolders")
    @XmlElement(name = "folder")
    private List<FolderDTO> rootFolders;

    /**
     * Default constructor
     */
    public ProjectDTO() {
    }

    /**
     * Constructor with GPProject as arg
     * @param project
     */
    public ProjectDTO(GPProject project) {
        this.id = project.getId();
        this.name = project.getName();
        this.numberOfElements = project.getNumberOfElements();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
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
    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    /**
     * @param rootFolders to set
     */
    public List<FolderDTO> getRootFolders() {
        return rootFolders;
    }

    /**
     * @param rootFolders to set
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
                + ", rootFolders=" + rootFolders + '}';
    }

    public static GPProject convertToGPProject(ProjectDTO projectDTO) {
        GPProject project = new GPProject();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        if (projectDTO.getNumberOfElements() != null) {
            project.setNumberOfElements(projectDTO.getNumberOfElements());
        }
        return project;
    }

    public static List<ProjectDTO> convertToProjectDTOList(List<GPProject> projects) {
        List<ProjectDTO> projectsDTO = new ArrayList<ProjectDTO>(projects.size());

        for (GPProject project : projects) {            
            projectsDTO.add(new ProjectDTO(project));
        }

        return projectsDTO;
    }
}
