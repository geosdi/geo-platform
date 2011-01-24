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
package org.geosdi.geoplatform;

import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPStyleDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPStyle;
import org.geosdi.geoplatform.core.model.GPTimerName;
import org.geosdi.geoplatform.core.model.GPUser;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author giuseppe
 * 
 */
public abstract class BaseDAOTest extends TestCase {

	protected final Logger LOGGER;
	protected static GPUserDAO userDAO;
	protected static GPFolderDAO folderDAO;
	protected static GPLayerDAO layerDAO;
	protected static GPStyleDAO styleDAO;

	protected static ClassPathXmlApplicationContext ctx = null;

	public BaseDAOTest() {
		LOGGER = Logger.getLogger(getClass());

		synchronized (BaseDAOTest.class) {
			if (ctx == null) {
				String[] paths = { "applicationContext.xml" };
				ctx = new ClassPathXmlApplicationContext(paths);
				userDAO = (GPUserDAO) ctx.getBean("userDAO");
				folderDAO = (GPFolderDAO) ctx.getBean("folderDAO");
				layerDAO = (GPLayerDAO) ctx.getBean("layerDAO");
				styleDAO = (GPStyleDAO) ctx.getBean("styleDAO");
			}
		}
	}

	@Override
	protected void setUp() throws Exception {
		LOGGER.info("---------- Running " + getClass().getSimpleName() + "::"
				+ getName());
		super.setUp();
	}

	@Test
	public void testCheckDAOs() {
		assertNotNull(userDAO);
		assertNotNull(folderDAO);
		assertNotNull(layerDAO);
		assertNotNull(styleDAO);
	}

	protected void removeAll() {
		removeAllStyle();
		removeAllLayer();
		removeAllFolder();
		removeAllUser();

	}

	private void removeAllFolder() {
		List<GPFolder> folders = folderDAO.findAll();
		for (GPFolder folder : folders) {
			LOGGER.info("Removing " + folder);
			boolean ret = folderDAO.remove(folder);
			assertTrue("Old Folder not removed", ret);
		}
		
	}

	private void removeAllLayer() {
		List<GPLayer> layers = layerDAO.findAll();
		for (GPLayer layer : layers) {
			LOGGER.info("Removing " + layer);
			boolean ret = layerDAO.remove(layer);
			assertTrue("Old Layer not removed", ret);
		}
		
	}

	private void removeAllStyle() {
		List<GPStyle> styles = styleDAO.findAll();
		for (GPStyle style : styles) {
			LOGGER.info("Removing " + style);
			boolean ret = styleDAO.remove(style);
			assertTrue("Old Style not removed", ret);
		}
	}

	private void removeAllUser() {
		List<GPUser> users = userDAO.findAll();
		for (GPUser user : users) {
			LOGGER.info("Removing " + user);
			boolean ret = userDAO.remove(user);
			assertTrue("Old User not removed", ret);
		}

	}

	protected void insertAll() throws ParseException {
		intertMassUser();
	}

	private void intertMassUser() {
		int USER_NUMBER = 50;

		for (int i = 0; i < USER_NUMBER; i++) {
			GPUser user = createUser("user_"+i);
			userDAO.persist(user);
			LOGGER.info("Save user: "+user);
		}

	}
	
	protected GPUser createUser(String name) {
        String username = name;
        GPUser user = new GPUser();
        user.setUsername(username);
        user.setEmailAddress(username + "@test");
        user.setEnabled(true);
        user.setPassword("test");
        user.setSendEmail(true);
        user.setTimerName(GPTimerName.TIMER_A);
        return user;
    }

}
