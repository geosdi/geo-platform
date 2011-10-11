//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.core.dao;

import com.googlecode.genericdao.search.ISearch;
import java.util.List;
import org.geosdi.geoplatform.core.model.GPUserProjects;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
public interface GPUserProjectsDAO {

    public List<GPUserProjects> findAll();

    public GPUserProjects find(Long userProjectsId);

    public GPUserProjects[] find(Long[] ids);

    public void persist(GPUserProjects... usersProjects);

    public GPUserProjects merge(GPUserProjects userProjects);

    public GPUserProjects[] merge(GPUserProjects... usersProjects);

    public boolean remove(GPUserProjects userProjects);

    public boolean removeById(Long userProjectsId);

    public boolean removeByUserId(long userId);

    public boolean removeByProjectId(long projectsId);

    public List<GPUserProjects> search(ISearch search);

    public int count(ISearch search);

    public List<GPUserProjects> findByUserId(long userId);

    public List<GPUserProjects> findByOwnerUserId(long userId);

    public List<GPUserProjects> findByProjectId(long projectId);

    public GPUserProjects find(long userId, long projectId);

}
