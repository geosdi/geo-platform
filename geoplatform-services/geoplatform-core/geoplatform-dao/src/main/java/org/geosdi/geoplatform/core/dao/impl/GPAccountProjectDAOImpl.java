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
package org.geosdi.geoplatform.core.dao.impl;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import java.util.List;
import javax.persistence.Query;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Transactional
public class GPAccountProjectDAOImpl extends BaseDAO<GPAccountProject, Long>
        implements GPAccountProjectDAO {

    @Override
    public List<GPAccountProject> findAll() {
        return super.findAll();
    }

    @Override
    public GPAccountProject find(Long id) {
        return super.find(id);
    }

    @Override
    public GPAccountProject[] find(Long[] ids) {
        return super.find(ids);
    }

    @Override
    public void persist(GPAccountProject... accountsProjects) {
        super.persist(accountsProjects);
    }

    @Override
    public GPAccountProject merge(GPAccountProject accountProject) {
        return super.merge(accountProject);
    }

    @Override
    public GPAccountProject[] merge(GPAccountProject... accountsProjects) {
        return super.merge(accountsProjects);
    }

    @Override
    public boolean remove(GPAccountProject accountProject) {
        return super.remove(accountProject);
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByAccountID(Long accountID) {
        // Hibernate Query Language [HQL]
        StringBuilder str = new StringBuilder();
        str.append("select _it_.account.id");
        str.append(" from ").append(getMetadataUtil().get(GPAccountProject.class).getEntityName()).append(" _it_");
        str.append(" where _it_.account.id = ?");
        // Set query
        Query query = em().createQuery(str.toString());
        query.setParameter(1, accountID);
        // Remove existent entities
        if (!query.getResultList().isEmpty()) {
            em().remove(em().getReference(GPAccountProject.class, accountID));
            return true;
        }

        return false;
    }

    @Override
    public boolean removeByProjectID(Long projectID) {
        // Hibernate Query Language [HQL]
        StringBuilder str = new StringBuilder();
        str.append("select _it_.project.id");
        str.append(" from ").append(getMetadataUtil().get(GPAccountProject.class).getEntityName()).append(" _it_");
        str.append(" where _it_.project.id = ?");
        // Set query
        Query query = em().createQuery(str.toString());
        query.setParameter(1, projectID);
        // Remove existent entities
        if (!query.getResultList().isEmpty()) {
            em().remove(em().getReference(GPAccountProject.class, projectID));
            return true;
        }

        return false;
    }

    @Override
    public List<GPAccountProject> search(ISearch search) {
        return super.search(search);
    }

    @Override
    public int count(ISearch search) {
        return super.count(search);
    }

    @Override
    public GPAccountProject find(Long accountID, Long projectID) {
        Search search = new Search();
        search.addFilterEqual("account.id", accountID);
        search.addFilterEqual("project.id", projectID);
        return searchUnique(search);
    }

    @Override
    public GPAccountProject findDefaultProjectByAccountID(Long accountID) {
        Search search = new Search();
        search.addFilterEqual("account.id", accountID);
        search.addFilterEqual("defaultProject", true);
        return searchUnique(search);
    }

    @Override
    public GPAccountProject forceAsDefaultProject(Long accountID, Long projectID) {
        GPAccountProject oldDefault = this.findDefaultProjectByAccountID(accountID);
        if (oldDefault != null) {
            oldDefault.setDefaultProject(false);
            this.merge(oldDefault);
        }

        Search search = new Search();
        search.addFilterEqual("account.id", accountID);
        search.addFilterEqual("project.id", projectID);
        GPAccountProject newDefault = searchUnique(search);
        if (newDefault == null) {
            return null;
        }

        newDefault.setDefaultProject(true);
        return this.merge(newDefault);
    }

    @Override
    public List<GPAccountProject> findByAccountID(Long accountID) {
        Search search = new Search();
        search.addFilterEqual("account.id", accountID);
        return search(search);
    }

    @Override
    public List<GPAccountProject> findByOwnerAccountID(Long accountID) {
        Search search = new Search();
        search.addFilterEqual("account.id", accountID);
        search.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
        return search(search);
    }

    @Override
    public List<GPAccountProject> findByProjectID(Long projectID) {
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        return search(search);
    }

    @Override
    public GPAccountProject findOwnerByProjectID(Long projectID) {
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
        return searchUnique(search);
    }

    @Override
    public List<GPAccountProject> findNotOwnersByProjectID(Long projectID) {
        Search search = new Search();
        search.addFilterEqual("project.id", projectID);
        search.addFilterNotEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
        return search(search);
    }
}
