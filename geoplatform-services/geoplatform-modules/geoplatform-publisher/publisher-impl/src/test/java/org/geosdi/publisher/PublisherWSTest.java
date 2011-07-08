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

import org.geosdi.publisher.services.GPPublisherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
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
            File zipFile = new File("c:\\Archivio.zip");
            String image = gppublisherService.uploadZIPInPreview(zipFile);
            logger.info("\n **** Preview image at: "+image);
            } catch (ResourceNotFoundFault ex) {
                logger.error("\n **** Eccezione nella gestione di Archivio.zip: "+ex.getMessage());
            }
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
            try {
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
