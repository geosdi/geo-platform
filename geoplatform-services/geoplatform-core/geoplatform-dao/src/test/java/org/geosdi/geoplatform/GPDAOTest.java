//<editor-fold defaultstate="collapsed" desc="License">
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
//</editor-fold>
package org.geosdi.geoplatform;

import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPUser;
import junit.framework.Assert;
import org.geosdi.geoplatform.BaseDAOTest;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
public class GPDAOTest extends BaseDAOTest {

    // User
    private String nameUser = "user_position_test";
    private GPUser userPositionTest = null;
    // Folders
    private String nameUserFolder = "folder of " + nameUser;
    private String nameFolderA = "empty position test A";
    private String nameFolderB = "empty position test B";
    // Layers
    private String nameRasterLayer = null;
    private String nameVectorLayer = null;
    // Positions
    private int beginPosition = 333000;
    private int endPosition = beginPosition;
    private int posUserFolder = 0;
    private int posFolderA = 0;
    private int posFolderB = 0;
    private int posRasterLayer = 0;
    private int posVectorLayer = 0;

    @Before
    public void setUp() {
        logger.info("\n\t@@@ " + getClass().getSimpleName() + ".setUp @@@");
        userPositionTest = super.insertUser(nameUser);

        GPFolder userFolder = super.createUserFolder(nameUserFolder, beginPosition, userPositionTest); // 333000
        posUserFolder = userFolder.getPosition();
        folderDAO.persist(userFolder);

        GPFolder folderA = super.createEmptyFolder(nameFolderA, endPosition += 10, userFolder); // 333010
        GPFolder folderB = super.createEmptyFolder(nameFolderB, endPosition += 10, userFolder); // 333020
        posFolderA = folderA.getPosition();
        posFolderB = folderB.getPosition();
        folderDAO.persist(folderA, folderB);

        GPRasterLayer rasterLayer = super.createRasterLayer1(endPosition += 30, folderB); // 333050
        GPVectorLayer vectorLayer = super.createVectorLayer1(endPosition += 30, folderB); // 333080
        nameRasterLayer = rasterLayer.getName() + "_position_test";
        nameVectorLayer = vectorLayer.getName() + "_position_test";
        rasterLayer.setName(nameRasterLayer);
        vectorLayer.setName(nameVectorLayer);
        posRasterLayer = rasterLayer.getPosition();
        posVectorLayer = vectorLayer.getPosition();
        layerDAO.persist(rasterLayer, vectorLayer);
    }

    @After
    public void tearDown() {
        logger.info("\n\t@@@ " + getClass().getSimpleName() + ".tearDown @@@");
        userDAO.remove(userPositionTest);
    }

    /**
     * Test of updatePositionsRange method for Foders
     */
    @Test
    public void testIncreasePositionsFolders() {
        logger.trace("\n\t@@@ testIncreasePositionsFolders @@@");
        int deltaValue = 1;
        boolean check = folderDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position NOT done", check);

        GPFolder userFolder = folderDAO.findByFolderName(nameUserFolder);
        Assert.assertEquals("Position NOT increased for \"" + nameUserFolder + "\"",
                userFolder.getPosition(), posUserFolder + deltaValue);

        GPFolder folderA = folderDAO.findByFolderName(nameFolderA);
        Assert.assertEquals("Position NOT increased for \"" + nameFolderA + "\"",
                folderA.getPosition(), posFolderA + deltaValue);

        GPFolder folderB = folderDAO.findByFolderName(nameFolderB);
        Assert.assertEquals("Position NOT increased for \"" + nameFolderB + "\"",
                folderB.getPosition(), posFolderB + deltaValue);
    }

    @Test
    public void testDecreasePositionsFolders() {
        logger.trace("\n\t@@@ testDecreasePositionsFolders @@@");
        int deltaValue = -1;
        boolean check = folderDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPFolder userFolder = folderDAO.findByFolderName(nameUserFolder);
        Assert.assertEquals("Position NOT decreased for \"" + nameUserFolder + "\"",
                userFolder.getPosition(), posUserFolder + deltaValue);

        GPFolder folderA = folderDAO.findByFolderName(nameFolderA);
        Assert.assertEquals("Position NOT decreased for \"" + nameFolderA + "\"",
                folderA.getPosition(), posFolderA + deltaValue);

        GPFolder folderB = folderDAO.findByFolderName(nameFolderB);
        Assert.assertEquals("Position NOT decreased for \"" + nameFolderB + "\"",
                folderB.getPosition(), posFolderB + deltaValue);
    }

    /**
     * Test of updatePositionsLowerBound method for Foders
     */
    @Test
    public void testShiftPositionsFolders() {
        logger.trace("\n\t@@@ testShiftPositionsFolders @@@");
        int deltaValue = 33;
        boolean check = folderDAO.updatePositionsLowerBound(beginPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPFolder userFolder = folderDAO.findByFolderName(nameUserFolder);
        Assert.assertEquals("Position NOT decreased for \"" + nameUserFolder + "\"",
                userFolder.getPosition(), posUserFolder + deltaValue);

        GPFolder folderA = folderDAO.findByFolderName(nameFolderA);
        Assert.assertEquals("Position NOT decreased for \"" + nameFolderA + "\"",
                folderA.getPosition(), posFolderA + deltaValue);

        GPFolder folderB = folderDAO.findByFolderName(nameFolderB);
        Assert.assertEquals("Position NOT decreased for \"" + nameFolderB + "\"",
                folderB.getPosition(), posFolderB + deltaValue);
    }

    /**
     * Test of updatePositionsRange method for Layers
     */
    @Test
    public void testIncreasePositionsLayers() {
        logger.trace("\n\t@@@ testIncreasePositionsLayers @@@");
        int deltaValue = 1;
        boolean check = layerDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Increase Position NOT done", check);

        GPLayer rasterLayer = layerDAO.findByLayerName(nameRasterLayer);
        Assert.assertEquals("Position NOT increased for \"" + nameRasterLayer + "\"",
                rasterLayer.getPosition(), posRasterLayer + deltaValue);

        GPLayer vectorLayer = layerDAO.findByLayerName(nameVectorLayer);
        Assert.assertEquals("Position NOT increased for \"" + nameVectorLayer + "\"",
                vectorLayer.getPosition(), posVectorLayer + deltaValue);
    }

    @Test
    public void testDecreasePositionsLayers() {
        logger.trace("\n\t@@@ testDecreasePositionsLayers @@@");
        int deltaValue = -1;
        boolean check = layerDAO.updatePositionsRange(beginPosition, endPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPLayer rasterLayer = layerDAO.findByLayerName(nameRasterLayer);
        Assert.assertEquals("Position NOT decreased for \"" + nameRasterLayer + "\"",
                rasterLayer.getPosition(), posRasterLayer + deltaValue);

        GPLayer vectorLayer = layerDAO.findByLayerName(nameVectorLayer);
        Assert.assertEquals("Position NOT decreased for \"" + nameVectorLayer + "\"",
                vectorLayer.getPosition(), posVectorLayer + deltaValue);
    }

    /**
     * Test of updatePositionsLowerBound method for Layers
     */
    @Test
    public void testShiftPositionsLayers() {
        logger.trace("\n\t@@@ testShiftPositionsLayers @@@");
        int deltaValue = 99;
        boolean check = layerDAO.updatePositionsLowerBound(beginPosition, deltaValue);
        Assert.assertTrue("Decrease Position NOT done", check);

        GPLayer rasterLayer = layerDAO.findByLayerName(nameRasterLayer);
        Assert.assertEquals("Position NOT decreased for \"" + nameRasterLayer + "\"",
                rasterLayer.getPosition(), posRasterLayer + deltaValue);

        GPLayer vectorLayer = layerDAO.findByLayerName(nameVectorLayer);
        Assert.assertEquals("Position NOT decreased for \"" + nameVectorLayer + "\"",
                vectorLayer.getPosition(), posVectorLayer + deltaValue);
    }
}
