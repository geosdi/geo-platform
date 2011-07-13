/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.geosdi.publisher;

/**
 *
 * @author Luca
 */


import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.geosdi.publisher.exception.ResourceNotFoundFault;
import org.geosdi.publisher.responce.InfoPreview;

import org.geosdi.publisher.services.GPPublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Luca Paolino - geoSDI Group
 * @email luca.paolino@geosdi.org
 * this test try to publish two shapefiles. the first only in the preview workspace, the second firstly in the preview workspace and then into the data datastore of the preview2 workspace
 * In order to execute this test you should provide two shapefiles. The first should be in one ZIP compressed file while the second should be provided provifing its shp, prj, shx and dbf uncrompressed files.
 * You should also modify the paths to access them.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PublisherWSTest extends TestCase{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GPPublisherService gppublisherService;

    @Test
    public void testWS() {
        Assert.assertNotNull(gppublisherService);
        try {
            logger.info("\n **** Start to upload Archivio.zip");
            File zipFile = new File("c:\\shape.zip");
            InfoPreview info = gppublisherService.uploadZIPInPreview(zipFile);
            logger.info("\n **** Preview image at: "+info.getDataStoreName());
            logger.info("\n **** Preview image at: "+info.getWorkspace());
            System.out.println("\n **** Preview url at: "+info.getUrl());
            System.out.println("\n **** Preview data store name at: "+info.getDataStoreName());
            System.out.println("\n **** Preview workspace at: "+info.getWorkspace());
            logger.info("\n **** Publishing SHAPE "+gppublisherService.publish("preview2", "data", "it_aeropo" ));
            File shpFile = new File("c:\\limiti_adb_4326.shp");
            File dbfFile = new File("c:\\limiti_adb_4326.dbf");
            File shxFile = new File("c:\\limiti_adb_4326.shx");
            File prjFile = new File("c:\\limiti_adb_4326.prj");
//            try {
//                logger.info("\n **** Start to upload Limiti_AdB_4326 files");
//                logger.info("\n **** Preview image at: "+gppublisherService.uploadShapeInPreview(shpFile, dbfFile, shxFile, null));
//            } catch (Exception ex) {
//                logger.error("\n **** Eccezione nella gestione di Limiti_AdB_4326 files: "+ex.getMessage());
//            }

                logger.info("\n **** Start again to upload Limiti_AdB_4326 files");
                logger.info("\n **** Preview image at: "+gppublisherService.uploadShapeInPreview(shpFile, dbfFile, shxFile, prjFile));
                logger.info("\n **** Successfully published in preview");
                logger.info("\n **** Start to publish Limiti_AdB_4326 files");
                logger.info("\n **** Publishing Limiti_AdB_4326 "+gppublisherService.publish("preview2", "data", "limiti_adb_4326"));
                logger.info("\n **** Process successfully terminated");
            } catch (ResourceNotFoundFault ex) {
                logger.error("\n **** Eccezione nella pubblicazione: "+ex.getMessage());
                
            }
            catch (FileNotFoundException ex) {
                logger.error("\n **** File zip non trovato");
            }
    }
}
