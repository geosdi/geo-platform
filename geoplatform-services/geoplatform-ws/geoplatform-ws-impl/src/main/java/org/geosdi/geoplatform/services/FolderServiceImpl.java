/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestById;
import org.geosdi.geoplatform.request.RequestByUserFolder;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.FolderList;
import org.geosdi.geoplatform.responce.ShortFolder;

import com.trg.search.Filter;
import com.trg.search.Search;

/**
 * @author giuseppe
 * 
 */
class FolderServiceImpl {

	final private static Logger LOGGER = Logger
			.getLogger(FolderServiceImpl.class);

	private GPFolderDAO folderDao;
	private GPUserDAO userDao;

	public long insertFolder(GPFolder folder) {
		folderDao.persist(folder);
		return folder.getId();
	}

	public long updateFolder(GPFolder folder) throws ResourceNotFoundFault,
			IllegalParameterFault {
		GPFolder orig = folderDao.find(folder.getId());
		if (orig == null) {
			throw new ResourceNotFoundFault("Folder not found", folder.getId());
		}

		orig.setName(folder.getName());
		orig.setParent(folder.getParent());
		orig.setPosition(folder.getPosition());
		orig.setChildern(folder.getChildern());

		folderDao.merge(orig);
		return orig.getId();
	}

	public GPFolder getFolder(RequestById request) throws ResourceNotFoundFault {
		GPFolder folder = folderDao.find(request.getId());

		if (folder == null) {
			throw new ResourceNotFoundFault("Folder not found", request.getId());
		}

		return folder;
	}

	public boolean deleteFolder(RequestById request)
			throws ResourceNotFoundFault, IllegalParameterFault {
		GPFolder folder = folderDao.find(request.getId());

		if (folder == null) {
			throw new ResourceNotFoundFault("Folder not found", request.getId());
		}

		// data on ancillary tables should be deleted by cascading
		return folderDao.remove(folder);

	}

	public FolderList getFolders() {
		List<GPFolder> found = folderDao.findAll();
		return convertToShortList(found);
	}

	public FolderList searchFolders(PaginatedSearchRequest searchRequest) {
		Search searchCriteria = new Search(GPFolder.class);
		searchCriteria.setMaxResults(searchRequest.getNum());
		searchCriteria.setPage(searchRequest.getPage());
		searchCriteria.addSortAsc("name");

		String like = searchRequest.getNameLike();
		if (like != null) {
			searchCriteria.addFilterILike("name", like);
		}

		List<GPFolder> foundFolder = folderDao.search(searchCriteria);
		FolderList list = convertToShortList(foundFolder);
		return list;
	}

	public long getFoldersCount(SearchRequest searchRequest) {
		Search searchCriteria = new Search(GPFolder.class);

		if (searchRequest != null && searchRequest.getNameLike() != null) {
			searchCriteria.addFilterILike("name", searchRequest.getNameLike());
		}

		return folderDao.count(searchCriteria);
	}

	public long getUserFoldersCount(RequestById request) {
		Search searchCriteria = new Search(GPFolder.class);
		searchCriteria.addFilterEqual("owner.id", request.getId());
		return folderDao.count(searchCriteria);
	}

	public List<GPFolder> getUserFolders(RequestById request) {
		Search searchCriteria = new Search(GPFolder.class);
		searchCriteria.setMaxResults(request.getNum());
		searchCriteria.setPage(request.getPage());
		searchCriteria.addSortAsc("position");

		searchCriteria.addFilterEqual("owner.id", request.getId());

		List<GPFolder> foundFolder = folderDao.search(searchCriteria);
		
		return foundFolder;
	}

	public FolderList getAllUserFolders(long userId, int num, int page) {
		Search searchCriteria = new Search(GPFolder.class);
		searchCriteria.setMaxResults(num);
		searchCriteria.setPage(page);
		searchCriteria.addSortAsc("name");

		Filter owner = Filter.equal("owner.id", userId);
		Filter shared = Filter.equal("shared", true);
		searchCriteria.addFilterOr(owner, shared);

		List<GPFolder> foundFolder = folderDao.search(searchCriteria);

		FolderList list = convertToShortList(foundFolder);
		return list;
	}

	public int getAllUserFoldersCount(long userId) {
		Search searchCriteria = new Search(GPFolder.class);

		Filter owner = Filter.equal("owner.id", userId);
		Filter shared = Filter.equal("shared", true);
		searchCriteria.addFilterOr(owner, shared);

		return folderDao.count(searchCriteria);
	}

	public void setFolderShared(RequestById request)
			throws ResourceNotFoundFault {
		GPFolder folder = folderDao.find(request.getId());

		if (folder == null) {
			throw new ResourceNotFoundFault("Folder not found", request.getId());
		}

		folder.setShared(true);
		folder.setOwner(null);
		folderDao.merge(folder);

	}

	public boolean setFolderOwner(RequestByUserFolder request, boolean force)
			throws ResourceNotFoundFault {
		GPFolder folder = folderDao.find(request.getFolderId());

		if (folder == null) {
			throw new ResourceNotFoundFault("Folder not found",
					request.getFolderId());
		}

		GPUser user = userDao.find(request.getUserId());

		if (user == null) {
			throw new ResourceNotFoundFault("User not found",
					request.getUserId());
		}

		// TODO: implement the logic described in this method's javadoc

		folder.setShared(false);
		folder.setOwner(user);
		folderDao.merge(folder);

		return true;
	}

	private FolderList convertToShortList(List<GPFolder> folderList) {
		List<ShortFolder> shortFolders = new ArrayList<ShortFolder>(
				folderList.size());
		for (GPFolder folder : folderList) {
			shortFolders.add(new ShortFolder(folder));
		}

		FolderList folders = new FolderList();
		folders.setList(shortFolders);
		return folders;
	}

	/**
	 * @param folderDao
	 *            the folderDao to set
	 */
	public void setFolderDao(GPFolderDAO folderDao) {
		this.folderDao = folderDao;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(GPUserDAO userDao) {
		this.userDao = userDao;
	}

}
