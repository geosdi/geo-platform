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
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.model.GPProject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * 
 */
@Transactional
public class GPProjectDAOImpl extends BaseDAO<GPProject, Long> implements GPProjectDAO {

    @Override
    public List<GPProject> findAll() {
        return super.findAll();
    }

    @Override
    public GPProject find(Long id) {
        return super.find(id);
    }

    @Override
    public void persist(GPProject... projects) {
        super.persist(projects);
    }

    @Override
    public GPProject merge(GPProject project) {
        return super.merge(project);
    }

    @Override
    public GPProject[] merge(GPProject... projects) {
        return super.merge(projects);
    }

    @Override
    public boolean remove(GPProject project) {
        return super.remove(project);
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List search(ISearch search) {
        return super.search(search);
    }

    @Override
    public int count(ISearch search) {
        return super.count(search);
    }

    @Override
    public GPProject findByProjectName(String projectName) {
        Search search = new Search();
        search.addFilterEqual("name", projectName);
        return searchUnique(search);
    }

    @Override
    public List<GPProject> findInternalPublic(int num, int page) {
        Search search = new Search();
        search.addFilterEqual("internalPublic", TRUE);
        search.setMaxResults(num);
        search.setPage(page);
        return search(search);
    }

    @Override
    public List<GPProject> findExternalPublic(int num, int page) {
        Search search = new Search();
        search.addFilterEqual("externalPublic", TRUE);
        search.setMaxResults(num);
        search.setPage(page);
        return search(search);
    }

    @Override
    public int getTotalInternalPublic() {
        Search search = new Search();
        search.addFilterEqual("internalPublic", TRUE);
        return super.count(search);
    }

    @Override
    public int getTotalExternalPublic() {
        Search search = new Search();
        search.addFilterEqual("externalPublic", TRUE);
        return super.count(search);
    }
}