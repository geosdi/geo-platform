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
package org.geosdi.geoplatform.core.dao.impl;

import org.geosdi.geoplatform.core.dao.GPUserProjectsDAO;
import org.geosdi.geoplatform.core.model.GPUserProjects;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import java.util.List;
import javax.persistence.Query;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
@Transactional
public class GPUserProjectsDAOImpl extends BaseDAO<GPUserProjects, Long>
        implements GPUserProjectsDAO {

    @Override
    public List<GPUserProjects> findAll() {
        return super.findAll();
    }

    @Override
    public GPUserProjects find(Long userProjectsId) {
        return super.find(userProjectsId);
    }

    @Override
    public GPUserProjects[] find(Long[] ids) {
        return super.find(ids);
    }

    @Override
    public void persist(GPUserProjects... usersProjects) {
        super.persist(usersProjects);
    }

    @Override
    public GPUserProjects merge(GPUserProjects userProjects) {
        return super.merge(userProjects);
    }

    @Override
    public GPUserProjects[] merge(GPUserProjects... usersProjects) {
        return super.merge(usersProjects);
    }

    @Override
    public boolean remove(GPUserProjects userProjects) {
        return super.remove(userProjects);
    }

    @Override
    public boolean removeById(Long userProjectsId) {
        return super.removeById(userProjectsId);
    }

    @Override
    public boolean removeByUserId(long userId) {
        // Hibernate Query Language [HQL]
        StringBuilder str = new StringBuilder();
        str.append("select _it_.user.id");
        str.append(" from ").append(getMetadataUtil().get(GPUserProjects.class).getEntityName()).append(" _it_");
        str.append(" where _it_.user.id = ?");
        // Set query
        Query query = em().createQuery(str.toString());
        query.setParameter(1, userId);
        // Remove existent entities
        if (!query.getResultList().isEmpty()) {
            em().remove(em().getReference(GPUserProjects.class, userId));
            return true;
        }

        return false;
    }

    @Override
    public boolean removeByProjectId(long projectId) {
        // Hibernate Query Language [HQL]
        StringBuilder str = new StringBuilder();
        str.append("select _it_.project.id");
        str.append(" from ").append(getMetadataUtil().get(GPUserProjects.class).getEntityName()).append(" _it_");
        str.append(" where _it_.project.id = ?");
        // Set query
        Query query = em().createQuery(str.toString());
        query.setParameter(1, projectId);
        // Remove existent entities
        if (!query.getResultList().isEmpty()) {
            em().remove(em().getReference(GPUserProjects.class, projectId));
            return true;
        }

        return false;
    }

    @Override
    public List<GPUserProjects> search(ISearch search) {
        return super.search(search);
    }

    @Override
    public int count(ISearch search) {
        return super.count(search);
    }

    @Override
    public List<GPUserProjects> findByUserId(long userId) {
        Search search = new Search();
        search.addFilterEqual("user.id", userId);
        return search(search);
    }

    @Override
    public List<GPUserProjects> findByOwnerUserId(long userId) {
        Search search = new Search();
        search.addFilterEqual("user.id", userId);
        search.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
        return search(search);
    }

    @Override
    public List<GPUserProjects> findByProjectId(long projectId) {
        Search search = new Search();
        search.addFilterEqual("project.id", projectId);
        return search(search);
    }

    @Override
    public GPUserProjects find(long userId, long projectId) {
        Search search = new Search();
        search.addFilterEqual("user.id", userId);
        search.addFilterEqual("project.id", projectId);
        return searchUnique(search);
    }
}
