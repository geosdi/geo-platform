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
package org.geosdi.geoplatform.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.jws.WebService;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import it.geosolutions.geoserver.rest.decoder.RESTCoverageStore;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;
import it.geosolutions.geoserver.rest.decoder.RESTStyleList;
import it.geosolutions.geoserver.rest.encoder.GSPostGISDatastoreEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.zip.ZipFile;
import java.util.List;
import java.util.zip.ZipEntry;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.services.geotiff.GeoTiffOverviews;
import org.geosdi.geoplatform.services.geotiff.GeoTiffOverviewsConfiguration;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataSourceException;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPPublisherService")
public class GPPublisherServiceImpl implements GPPublisherService {

    protected Logger logger = LoggerFactory.getLogger(
            GPPublisherServiceImpl.class);

    class LayerInfo {

        String name;
        String origName;
        String epsg;
        String sld;
    }
    private String RESTURL = "";
    private String RESTUSER = "";
    private String RESTPW = "";
    private GeoServerRESTPublisher publisher = null;
    private GeoServerRESTReader reader = null;
    private String geoportalDir = "";
    @Autowired
    private PublisherScheduler scheduler;
    @Autowired
    private GeoTiffOverviewsConfiguration overviewsConfiguration;

    public GPPublisherServiceImpl(String RESTURL, String RESTUSER, String RESTPW,
            String geoportalDir) {
        this.RESTURL = RESTURL;
        this.RESTUSER = RESTUSER;
        this.RESTPW = RESTPW;
        this.geoportalDir = geoportalDir;

        publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        try {
            reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        } catch (MalformedURLException ex) {
            logger.info("Problems for connecting to the REST reader");
        }


        File geoportalDirFile = new File(geoportalDir);
        if (!geoportalDirFile.exists()) {
            logger.error("The geoportaldir: " + geoportalDir + " does not exist!");
        }
        logger.info("GEOSERVER AT: " + RESTURL + ", USER: " + RESTUSER
                + ", PWD: " + RESTPW + ", USING DIR: " + geoportalDir);
    }

    /****************************
     *System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "geoportal"+ System.getProperty("file.separator") + "shp";
     * @param workspace
     * @param dataStoreName
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault
     * @throws FileNotFoundException
     * this service publishes the layer <layerName> we loaded in the previews workspace into the DB datastore identified by the <dataStoreName> and published into the <workspace> workspace
     */
    @Override
    public boolean publish(String sessionID, String workspace,
            String dataStoreName, String layerName) throws ResourceNotFoundFault, FileNotFoundException {
        logger.info("\n Start to publish " + layerName + " in " + workspace + ":" + dataStoreName);
//        String tempUserDirZIP = PublishUtility.createDir(this.zipTempDir + sessionID);
//        String userWorkspace = createPreviewWorkspace(sessionID);
//        reload();
//        boolean publish = true;
//        RESTDataStore dataStore = reader.getDatastore(userWorkspace, layerName);
//
//        if (dataStore != null) {
//            this.removeLayer(layerName);
//            reload();
//            boolean result = publisher.unpublishFeatureType(userWorkspace, layerName, layerName);
//            System.out.println("Result featuretype: " + result);
//            reload();
//            result = publisher.removeDatastore(userWorkspace, layerName);
//            System.out.println("Result datastore: " + result);
//            result = publisher.removeWorkspace(workspace);
//            System.out.println("Result workspace: " + result);
//        } else {
//            logger.info(
//                    "\n The " + userWorkspace + ":" + dataStoreName + " does not exist");
//        }
//        String filename = tempUserDirZIP + layerName.substring(layerName.lastIndexOf(":") + 1) + ".zip";
//        File file = new File(filename);
//        if (file.exists()) {
//            reload();
//            List<LayerInfo> listInfo = getInfoFromCompressedShape(sessionID,
//                    file);
//            String epsg = "EPSG:4326";
//            String sld = null;
//            if (listInfo != null && listInfo.get(0) != null) {
//                epsg = listInfo.get(0).epsg;
//                sld = listInfo.get(0).sld;
//                logger.info("\n PUBLISHING IN THE DB " + epsg + " , " + sld);
//            }
//            try {
//                publish = publisher.publishShp(workspace, dataStoreName,
//                        layerName.substring(layerName.lastIndexOf(":") + 1), file, epsg, sld);
//                if (!publish) {
//                    logger.info(
//                            "\n Cannot publish " + layerName + " into " + workspace + ":" + dataStoreName);
//                    throw new ResourceNotFoundFault(
//                            "Cannot publish " + layerName + " into " + workspace + ":" + dataStoreName);
//                }
//            } catch (FileNotFoundException e) {
//                logger.info("\n ********** File " + layerName + ".zip not found");
//            }
//            file.deleteOnExit();
//            return true;
//        }
        return this.unscheduleJob(layerName);
    }

    @Override
    public boolean publishAll(String sessionID, String workspace,
            String dataStoreName, List<String> layerNames) throws ResourceNotFoundFault, FileNotFoundException {
        for (String name : layerNames) {
            this.unscheduleJob(name);
        }
        return true;
    }

    @Override
    public boolean publishAllofPreview(String sessionID, String workspace,
            String dataStoreName) throws ResourceNotFoundFault, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*************************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault
     * this service removes a layer from the workspace
     */
    @Override
    public boolean removeSHPFromPreview(String userName, String layerName) throws ResourceNotFoundFault {
        String userWorkspace = getWorkspace(userName);
        logger.info("Removing " + layerName + " from " + userWorkspace);
        this.removeLayer(layerName);
        publisher.unpublishFeatureType(userWorkspace,
                layerName, layerName);
        reload();
        publisher.removeDatastore(userWorkspace, layerName);
        return true;
    }

    /*************************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault
     * this service removes a layer from the workspace
     */
    @Override
    public boolean removeTIFFromPreview(String userName, String layerName) throws ResourceNotFoundFault {
        String userWorkspace = getWorkspace(userName);
        logger.info("Removing " + layerName + " from " + userWorkspace);
        this.removeLayer(layerName);
        publisher.unpublishCoverage(userWorkspace, layerName, layerName);
        reload();
        publisher.removeCoverageStore(userWorkspace, layerName);
        return true;
    }

    private InfoPreview getTIFURLByLayerName(String userName, String layerName) {
        RESTCoverage featureType = reader.getCoverage(reader.getLayer(
                layerName));
        String userWorkspace = getWorkspace(userName);
        InfoPreview info = null;
        try {
            info = new InfoPreview(RESTURL, userWorkspace, layerName,
                    featureType.getMinX(), featureType.getMinY(),
                    featureType.getMaxX(), featureType.getMaxY(), "EPSG:4326");
        } catch (Exception e) {
            logger.info(
                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
            info = new InfoPreview(layerName,
                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
        }
        return info;
    }

    /*******************************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault
     * get the URL to the PNG if the layer dataStoreName
     */
    private InfoPreview getSHPURLByDataStoreName(String userName, String layerName) throws ResourceNotFoundFault {
        RESTFeatureType featureType = reader.getFeatureType(reader.getLayer(layerName));
        String userWorkspace = getWorkspace(userName);
        InfoPreview info = null;
        try {
            info = new InfoPreview(RESTURL, userWorkspace, layerName,
                    featureType.getMinX(), featureType.getMinY(),
                    featureType.getMaxX(), featureType.getMaxY(), "EPSG:4326");
        } catch (Exception e) {
            logger.info(
                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
            info = new InfoPreview(layerName,
                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
        }
        return info;
        //  return RESTURL+"/"+previewWorkspace+"/wms?service=WMS&version=1.1.0&request=GetMap&layers=previews:"+dataStoreName+"&styles=&bbox="+minX+","+minY+","+maxX+","+maxY+"&width=512&height=499&srs="+featureType.getCRS()+"&format=image/png";
    }

    /*************************
     *
     * @return
     * @throws ResourceNotFoundFault
     * this methods returns the list of the datastores in the user workspace. For each datastore the info to find the PNG is also specified
     */
    @Override
    public List<InfoPreview> getPreviewDataStores(String userName) throws ResourceNotFoundFault {
        reload();
        List<InfoPreview> listPreviews = new ArrayList<InfoPreview>();
        String userWorkspace = getWorkspace(userName);
        RESTDataStoreList list = reader.getDatastores(userWorkspace);
        for (NameLinkElem element : list) {
            String name = element.getName();
            InfoPreview item = getSHPURLByDataStoreName(userName, name);
            listPreviews.add(item);
        }
        return listPreviews;
    }

    /**************
     *
     * @param file the ZIP file from where extracting the info
     * @return the information of the shapefile
     * this method extracts from a zip file containing the shape files, the name, the CRS and the geometry types
     */
    private List<LayerInfo> getInfoFromCompressedShape(String userName, File file) {
        logger.info("Call to getInfoFromCompressedShape");
        String tempUserDir = PublishUtility.createDir(this.geoportalDir + userName);
        String tempUserZipDir = PublishUtility.createDir(tempUserDir + PublishUtility.zipDirName);
//        String userWorkspace = getWorkspace(userName);
        System.setProperty("org.geotools.referencing.forceXY", "true");
        List<String> shpList = new ArrayList<String>();
        List<LayerInfo> infoShapeList = new ArrayList<LayerInfo>();
        try {
            // decomprime il contenuto di file nella cartella <tmp>/geoportal/shp
            ZipFile zipSrc = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipSrc.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();
                File newFile = new File(entryName);
                if (newFile.isDirectory()) {
                    continue;
                }
                //   entryName = entryName.replaceAll("/", "_");
                //    logger.info("\n ********** INFO:"+entryName);

                int lastIndex = entryName.lastIndexOf('/');
                entryName = entryName.substring(lastIndex + 1);
                //   logger.info("\n ********** INFO2:"+entryName);
                if (entryName.equals("")) {
                    continue;
                }
                if (entryName.endsWith(".shp")) {
                    logger.info("INFO: Found shapefile " + entryName);
                    shpList.add(entryName);
                }
                InputStream zipinputstream = zipSrc.getInputStream(entry);
                entryName = entryName.toLowerCase();
                FileOutputStream fileoutputstream = null;
                logger.info("INFO: Found file " + entryName);
//                if (entryName.endsWith(".sld")) {
//                    fileoutputstream = new FileOutputStream(tempDirZIP + userName + "_" + entryName);
//                }
//                else
                fileoutputstream = new FileOutputStream(tempUserDir + entryName);
                byte[] buf = new byte[1024];
                int n;
                while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
                    fileoutputstream.write(buf, 0, n);
                }
                fileoutputstream.close();
                zipinputstream.close();
            }
            zipSrc.close();
            // fine decompressione

            for (String shpFileName : shpList) {
                // start analisi degli shape
                logger.info("Extracting info from " + shpFileName);
//                RESTDataStore dataStore = null;
                LayerInfo info = new LayerInfo();
                info.name = shpFileName.substring(0, shpFileName.length() - 4);
//                info.origName = info.name;
//                String origName = info.name;
//                dataStore = reader.getDatastore(userWorkspace, info.name);
//                int i = 0;
//                while(dataStore!=null) {
//                    info.name = origName+""+(i++);
//                    dataStore = reader.getDatastore(userWorkspace, info.name);
//                }

                File shpFile = new File(tempUserDir + shpFileName);
                FileDataStore store = FileDataStoreFinder.getDataStore(shpFile);
                SimpleFeatureSource featureSource = store.getFeatureSource();
                String geomType = featureSource.getSchema().getGeometryDescriptor().getType().getName().toString();
                String SLDFileName = info.name + ".sld";
                info.sld = info.name;
                File fileSLD = new File(tempUserDir + SLDFileName);
                if (geomType.equals("MultiPolygon")) {
                    info.sld = "default_polygon";
                }
                if (geomType.equals("MultiLineString")) {
                    info.sld = "default_polyline";
                }
                if (geomType.equals("Point")) {
                    info.sld = "default_point";
                }
                if (fileSLD.exists()) {
                    reload();
                    info.sld = info.name;
                    logger.info(
                            "\n INFO: FOUND STYLE FILE " + tempUserDir + SLDFileName + ". TRYING TO PUBLISH WITH " + info.name + " NAME");
                    boolean returnPS = false;
                    if (!existsStyle(info.name)) {
                        returnPS = publisher.publishStyle(fileSLD, info.name);
                    }
                    logger.info("\n INFO: PUBLISH STYLE RESULT " + returnPS);
                }
                logger.info("\n INFO: STYLE " + info.sld + " for " + info.name);
                Integer code = null;
                try {
//                    System.out.println("Info sull'epsg: " + featureSource.getSchema().getCoordinateReferenceSystem());
                    code = CRS.lookupEpsgCode(
                            featureSource.getSchema().getCoordinateReferenceSystem(),
                            true);
                } catch (Exception e) {
                }
                // if (code.intValue()!=4326) exportCRS(shpFile, 4326);
                if (code != null) {
                    info.epsg = "EPSG:" + code.toString();
                } else {
                    info.epsg = "EPSG:4326";
                }
                // fine analisi shape
                infoShapeList.add(info);
                PublishUtility.compressFiles(tempUserZipDir, tempUserDir, info.name + ".zip",
                        info.name, info.name); // questo metodo comprime in un file <nomeshape>.zip gli shp file associati: shp, dbf, shx e prj
            }
            // svuota la cartella degli shape <tmp>/geoportal/shp
            File directory = new File(tempUserDir);
            File[] files = directory.listFiles();
            for (File f : files) {
                f.delete();
            }
        } catch (Exception e) {
            logger.error("ERRORE : " + e);
        }
        return infoShapeList;
    }

    /***************
     *
     * @param layer the layer to remove
     * @return
     *  perform a REST call for deleting the layer
     */
    private boolean removeLayer(String layer) {
        String sUrl = RESTURL + "/rest/layers/" + layer + "?purge=true";
        return HttpUtilsLocal.delete(sUrl, RESTUSER, RESTPW);
    }

    /*************
     *
     * @param styleName
     * @return
     * check whether the style styleName exists
     */
    public boolean existsStyle(String styleName) {
        RESTStyleList styleList = reader.getStyles();
        for (int i = 0; i < styleList.size(); i++) {
            if (styleList.get(i).getName().equals(styleName)) {
                return true;
            }
        }
        return false;
    }

    /**************
     *
     * @param workspace
     * @param dataStoreName
     * @return
     * check whether the layer layerName exists in the workspace workspace
     */
    public boolean existsDataStore(String workspace, String dataStoreName) {
        RESTDataStoreList workspaceDataStores = reader.getDatastores(workspace);
        for (int i = 0; i < workspaceDataStores.size(); i++) {
            if (workspaceDataStores.get(i).getName().equals(dataStoreName)) {
                return true;
            }
        }
        return false;
    }

    /*******************
     *
     * @return
     * reload the catalogue of geoserver
     */
    private String reload() {
        String sUrl = RESTURL + "/rest/reload";
        return HttpUtilsLocal.post(sUrl, "", "text/html", RESTUSER, RESTPW);
    }

    /********************
     *
     * @param userName
     * @return
     *
     * creates the workspace if not exists or returns the existing one
     */
    private String getWorkspace(String nomeUtente) {
        List<String> workspaces = reader.getWorkspaceNames();
        String userWorkspace = nomeUtente;
        if (!workspaces.contains(userWorkspace)) {
            publisher.createWorkspace(userWorkspace);
        }
        return userWorkspace;
    }

    private Integer getCRSFromGeotiff(File file) {
        Integer code = null;
        try {
            GeoTiffReader geotiffReader = new GeoTiffReader(file, new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE));
            GridCoverage2D coverage = (GridCoverage2D) geotiffReader.read(null);
            CoordinateReferenceSystem crs = coverage.getCoordinateReferenceSystem2D();
            code = CRS.lookupEpsgCode(crs, false);
        } catch (DataSourceException ex) {
            logger.error("Errore retrieving the crs: " + ex);
        } catch (IOException ioe) {
            logger.error("Errore retrieving the crs: " + ioe);
        } catch (FactoryException ioe) {
            logger.error("Errore retrieving the crs: " + ioe);
        }
        return code;
    }

    //The layerName must be unique otherwise the Geoserver can't publish the same layer
    //in two different workspaces (verify!)
    //It is not possible to publish different layers on the same "storeName"
    @Override
    public InfoPreview uploadTIFInPreview(String username, File file, boolean overwrite) throws ResourceNotFoundFault {
        logger.info("Call to uploadTIFInPreview");
        InfoPreview infoPreview = null;
        String userWorkspace = getWorkspace(username);
        String epsg = "EPSG:" + this.getCRSFromGeotiff(file);
        String sld = "default_raster";
//        String dataStoreName = username + "_" + PublishUtility.tifDirName;
        String fileName = username + "_" + file.getName().substring(0, file.getName().lastIndexOf("."));
        File fileInTifDir = null;
        //TODO: Se il layer esiste ma è richiesto di fare overwrite bisogna sovrascriverlo: cancellare ed aggiungere il file
        if (!existsDataStore(userWorkspace, fileName)) {
            String pathInTifDir = this.geoportalDir + username + System.getProperty("file.separator")
                    + PublishUtility.tifDirName + System.getProperty("file.separator");
            fileInTifDir = PublishUtility.copyFile(file, pathInTifDir, fileName + ".tif", overwrite);
            if (fileInTifDir == null) {
                infoPreview = new InfoPreview(fileName,
                        "The file " + fileInTifDir + " already exists, you must overwrite it");
                return infoPreview;
            }
            GeoTiffOverviews.overviewTiff(overviewsConfiguration, fileInTifDir.getAbsolutePath());
            try {
//                logger.info(
//                        "\n INFO: STYLE TO PUBLISH " + info.sld + " NAME :" + info.name);
//                logger.info(
//                        "\n INFO: CREATE DATASTORE " + userWorkspace + " NAME :" + info.name);
                RESTCoverageStore store = publisher.publishExternalGeoTIFF(userWorkspace,
                        fileName, fileInTifDir, epsg, sld);
                if (store != null) {
                    logger.info(
                            fileInTifDir + " correctly published in the " + userWorkspace + " workspace");
                    infoPreview = getTIFURLByLayerName(username, fileName);
                    infoPreview.setCrs(epsg);
                } else {
                    logger.info(
                            "Some problems occured when publishing " + fileInTifDir
                            + " into the " + userWorkspace + " workspace: may be the layer is already published in a db");
                    infoPreview = new InfoPreview(fileName,
                            "Some problems occured when publishing " + fileInTifDir
                            + " into the " + userWorkspace + " workspace");
                }
            } catch (Exception ex) {
                logger.info("Some problems occured when publishing " + fileName
                        + " into the " + userWorkspace + " workspace");
                ex.printStackTrace();
                infoPreview = new InfoPreview(fileName, "Some problems occured when publishing "
                        + fileName + " into the " + userWorkspace + " workspace");
            }
            //publish the tiff in the workspace
        } else {
            infoPreview = getTIFURLByLayerName(username, fileName);
            infoPreview.setMessage(
                    "The data store " + fileName + " in " + userWorkspace + " already exists");
        }
        // calculate the PNG URL to return
        infoPreview.setUrl(infoPreview.getUrl() + "/wms");
        this.addCleanerJob(userWorkspace, fileName, fileInTifDir.getAbsolutePath());
        return infoPreview;
    }

    private boolean unscheduleJob(String layerName) {
        boolean result = false;
        try {
            TriggerKey key = new TriggerKey(layerName, PublisherScheduler.PUBLISHER_GROUP);
            System.out.println("Trigger key: " + key.toString());
            result = this.scheduler.getScheduler().unscheduleJob(key);
            logger.debug("Job unscheduled: " + result);
        } catch (SchedulerException ex) {
            logger.error("Error unscheduling publisher cleaner job: " + ex);
        }
        return result;
    }

    private void addCleanerJob(String userWorkspace, String layerName, String filePath) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, 30);
        Trigger trigger = TriggerBuilder.newTrigger().forJob(this.scheduler.getCleanerJobDetail()).
                //                withIdentity(PublisherCleanerJob.PUBLISHER_CLEANER_JOB + "Trigger", PublisherScheduler.PUBLISHER_GROUP).
                withIdentity(new TriggerKey(userWorkspace + ":" + layerName, PublisherScheduler.PUBLISHER_GROUP)).
                withDescription("Runs after 30 minutes").
                startAt(calendar.getTime()).
                withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().
                withMisfireHandlingInstructionFireAndProceed()).
                build();
        trigger.getJobDataMap().put(PublisherCleanerJob.USER_WORKSPACE, userWorkspace);
        trigger.getJobDataMap().put(PublisherCleanerJob.FILE_NAME, layerName);
        trigger.getJobDataMap().put(PublisherCleanerJob.FILE_PATH, filePath);
        trigger.getJobDataMap().put(PublisherCleanerJob.PUBLISHER_SERVICE, this);
        try {
            this.scheduler.getScheduler().scheduleJob(trigger);
            System.out.println("Trigger key: " + trigger.getKey());
        } catch (SchedulerException ex) {
            logger.error("Error adding publisher cleaner job: " + ex);
        }
    }

    /******************************
     *
     * @param file
     * @return returns the URL to the PNG of the layer uploaded in the ZIP file
     * @throws ResourceNotFoundFault this exception may be launched when: the ZIP file does not contain a SHP file
     * this service upload in the user workspace a shapefile. The ZIP file must contain the shp, the prj, the shx and the dbf files. 
     * Otherwise, an exception is raised
     ******************************/
    @Override
    public List<InfoPreview> uploadZIPInPreview(String sessionID, String username, File file) throws ResourceNotFoundFault {
        logger.info("Call to uploadZIPInPreview");
        reload();
        String datatStoreName = username + "_shp_" + file.getName().substring(0, file.getName().lastIndexOf("."));
        String tempUserZipDir = this.geoportalDir + username + System.getProperty("file.separator")
                + PublishUtility.zipDirName + System.getProperty("file.separator");
        String userWorkspace = getWorkspace(username);
        List<InfoPreview> infoPreviewList = new ArrayList<InfoPreview>();
        // decompress the zip file in the <tmp>/shp directory, read info and create <layername>.zip files for each layer in <tmp>/zip
        List<LayerInfo> infoShapeList = getInfoFromCompressedShape(username, file);
        if (infoShapeList.isEmpty()) {
            throw new ResourceNotFoundFault("The ZIP archive does not contain shp files");
        }
        for (LayerInfo info : infoShapeList) {
            InfoPreview infoPreview = null;
            // check if the dataStore already exists
            if (!existsDataStore(userWorkspace, datatStoreName)) {
                GSPostGISDatastoreEncoder encoder = PublishUtility.generateEncoder(datatStoreName);
                publisher.createPostGISDatastore(username, encoder);
                // create the <layername>.zip file
                String fileName = tempUserZipDir + info.name + ".zip";
                File tempFile = new File(fileName);
                //publish the <layername>.zip file in the user workspace
                logger.info(
                        "\n INFO: STYLE TO PUBLISH " + info.sld + " NAME :" + info.name);
                logger.info(
                        "\n INFO: CREATE DATASTORE " + userWorkspace + " NAME :" + info.name);
                try {
                    //LO store per il publish deve essere quello di default, vedere il publish
                    boolean published = publisher.publishShp(userWorkspace, datatStoreName, info.name, tempFile, info.epsg, info.sld);
//                    boolean published = publisher.publishShp(userWorkspace, info.name, info.name, temp, info.epsg, info.sld);
                    if (published) {
                        logger.info(
                                info.name + " correctly published in the " + userWorkspace + " workspace");
                        infoPreview = getSHPURLByDataStoreName(userWorkspace, info.name);
//                        infoPreview = getSHPURLByDataStoreName(username, info.name);
                        infoPreview.setCrs(info.epsg);
                    } else {
                        logger.info(
                                "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace: may be the layer is already published in a db");
                        infoPreview = new InfoPreview(info.name,
                                "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                    }
                } catch (Exception ex) {
                    logger.info(
                            "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                    ex.printStackTrace();
                    infoPreview = new InfoPreview(info.name,
                            "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                } finally {
                    tempFile.delete();
                }
                //publish the shape in the previews workspace
            } else {
                infoPreview = getSHPURLByDataStoreName(username, info.name);
                infoPreview.setMessage(
                        "The data store " + datatStoreName + " in " + userWorkspace + " already exists");
            }
            // calculate the PNG URL to return
            infoPreview.setUrl(infoPreview.getUrl() + "/wms");
            infoPreviewList.add(infoPreview);
        }
        return infoPreviewList;
    }

    /***********************
     *
     * @param shpFile
     * @param dbfFile
     * @param shxFile
     * @param prjFile
     * @return
     * @throws ResourceNotFoundFault
     * this service uploads in the previews workspace a shapefile. The shapefile file must contain the shp, the prj, the shx and the dbf files. Otherwise, an exception is raised
     */
    @Override
    public List<InfoPreview> uploadShapeInPreview(String sessionID, String username, File shpFile,
            File dbfFile, File shxFile, File prjFile, File sldFile) throws ResourceNotFoundFault {
//        InfoPreview infoPreview = null;
//        List<InfoPreview> listInfoPreview = new ArrayList<InfoPreview>();
//        String name = shpFile.getName().substring(0,
//                shpFile.getName().length() - 4);
//        String tempUserDirZIP = PublishUtility.createDir(this.zipTempDir + sessionID);
//        try {
//            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
//                    tempUserDirZIP + "temp.zip"));
//            out = PublishUtility.compress(out, shpFile);
//            out = PublishUtility.compress(out, dbfFile);
//            out = PublishUtility.compress(out, shxFile);
//            out = PublishUtility.compress(out, prjFile);
//            if (sldFile != null) {
//                out = PublishUtility.compress(out, sldFile);
//            }
//            out.close();
//            File compressedFile = new File(tempUserDirZIP + "temp.zip");
//            // compressedFile.deleteOnExit();
//            return uploadZIPInPreview(sessionID, username, compressedFile);
//        } catch (Exception ex) {
//            logger.info(
//                    "the zip file cannot be created because some files are missing or are malformed");
//            infoPreview = new InfoPreview(name,
//                    "Some problems occured when publishing " + name + " into the " + previewWorkspace + " workspace. The zip file cannot be created because some files are missing or are malformed. Check whether the shp, dbf, shx, prj files are well-formed");
//            listInfoPreview.add(infoPreview);
//            return listInfoPreview;
//        }
        return null;
    }
//
//    /**********************
//     *
//     * @param userName this is the user name who
//     * @param list the list of
//     * @param shpFileName
//     * @return
//     * @throws ResourceNotFoundFault
//     * @throws Exception
//     */
//    @Override
//    public byte[] createSHP(String sessionID, List<Feature> list,
//            String shpFileName) throws ResourceNotFoundFault, Exception {
//        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
//        logger.info("\n INFO: Parsing feature schema");
//        if (list.size() > 0) {
//            GeometryFactory geometryFactory = new GeometryFactory();
//
//            WKTReader reader = new WKTReader(geometryFactory);
//            try {
//                Geometry geometry = reader.read(list.get(0).getWkt());
//                typeBuilder.setDefaultGeometry("the_geom");
//                typeBuilder.add("the_geom", geometry.getClass());
//            } catch (ParseException e) {
//                throw new ResourceNotFoundFault(
//                        "Cannot identify the geometry class");
//            }
//            List<Attribute> attributes = list.get(0).getAttributes();
//            for (Attribute attribute : attributes) {
//                Class clazz = null;
//                if (attribute.getType().equals("int")) {
//                    clazz = Integer.class;
//                }
//                if (attribute.getType().equals("double")) {
//                    clazz = Double.class;
//                }
//                if (attribute.getType().equals("float")) {
//                    clazz = Float.class;
//                }
//                if (attribute.getType().equals("String")) {
//                    clazz = String.class;
//                }
//                if (attribute.getType().equals("char")) {
//                    clazz = Character.class;
//                }
//                typeBuilder.add(attribute.getName(), clazz);
//            }
//
//        }
//
//        typeBuilder.setName("SHP_CREATION");
//        logger.info("\n  INFO: Parsing feature data ");
//        SimpleFeatureBuilder fb = new SimpleFeatureBuilder(
//                typeBuilder.buildFeatureType());
//        SimpleFeatureCollection result = FeatureCollections.newCollection();
//        GeometryFactory geometryFactory = new GeometryFactory();
//        WKTReader reader = new WKTReader(geometryFactory);
//        Geometry geometry = null;
//        int featureID = 0;
//        for (Feature feature : list) {
//            List<Attribute> attributes = feature.getAttributes();
//            try {
//                geometry = reader.read(feature.getWkt());
//            } catch (ParseException e) {
//                continue;
//            }
//            fb.set("the_geom", geometry);
//            for (Attribute attribute : attributes) {
//                Object value = null;
//                logger.info(
//                        "Name: " + attribute.getName() + " type:" + attribute.getType() + " value: " + attribute.getValue());
//                if (attribute.getType().equals("int")) {
//                    value = new Integer(Integer.parseInt(attribute.getValue()));
//                }
//                if (attribute.getType().equals("double")) {
//                    value = new Double(Double.parseDouble(attribute.getValue()));
//                }
//                if (attribute.getType().equals("float")) {
//                    value = new Float(Float.parseFloat(attribute.getValue()));
//                }
//                if (attribute.getType().equals("String")) {
//                    value = attribute.getValue();
//                }
//                fb.set(attribute.getName(), value);
//                result.add(fb.buildFeature("feature_" + (featureID++)));
//            }
//
//
//        }
//        logger.info("\n  INFO: Creating SHP files ");
//        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = null;
//        SimpleFeatureIterator iterator = null;
//        Transaction transaction = null;
//        try {
//            String tempUserDir = PublishUtility.createDir(
//                    this.shpTempDir + sessionID);
//            ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
//            Map<String, Serializable> params = new HashMap<String, Serializable>();
//            String shpFullPathName = tempUserDir + shpFileName;
//            File newFile = new File(shpFullPathName);
//            params.put("url", newFile.toURI().toURL());
//            params.put("create spatial index", Boolean.TRUE);
//            ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(
//                    params);
//            newDataStore.forceSchemaCRS(DefaultGeographicCRS.WGS84);
//            newDataStore.createSchema(result.getSchema()); // DA ECCEZIONE
//            writer = newDataStore.getFeatureWriter(Transaction.AUTO_COMMIT);
//            iterator = result.features();
//            transaction = new DefaultTransaction("Reproject");
//            while (iterator.hasNext()) {
//                // copy the contents of each feature and transform the geometry
//                SimpleFeature feature = iterator.next();
//                SimpleFeature copy = writer.next();
//                copy.setAttributes(feature.getAttributes());
//                Geometry geometrySource = (Geometry) feature.getDefaultGeometry();
//                copy.setDefaultGeometry(geometrySource);
//                writer.write();
//            }
//            transaction.commit();
//            logger.info("\n  INFO: Compressing SHP Files ");
//            String tempUserZipDir = PublishUtility.createDir(this.zipTempDir + sessionID);
//            String name = shpFileName.substring(0, shpFileName.length() - 4);
//            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
//                    tempUserZipDir + name + ".zip"));
//            out = PublishUtility.compress(out, new File(
//                    tempUserDir + name + ".shp"));
//            out = PublishUtility.compress(out, new File(
//                    tempUserDir + name + ".dbf"));
//            out = PublishUtility.compress(out, new File(
//                    tempUserDir + name + ".shx"));
//            out = PublishUtility.compress(out, new File(
//                    tempUserDir + name + ".prj"));
//            out.close();
//            return getStreamOfByteFromFile(tempUserZipDir + name + ".zip");
//        } catch (MalformedURLException ex) {
//            throw new ResourceNotFoundFault("Malformed URL Exception");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            throw new ResourceNotFoundFault("IO Exception");
//        } catch (Exception ex) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            ex.printStackTrace();
//            throw new ResourceNotFoundFault("Exception");
//        } finally {
//            writer.close();
//            iterator.close();
//            transaction.close();
//        }
//    }
//
//    private byte[] getStreamOfByteFromFile(String fileName) throws FileNotFoundException, IOException {
//        File file = new File(fileName);
//        InputStream is = new FileInputStream(file);
//
//        // Get the size of the file
//        long length = file.length();
//
//        if (length > Integer.MAX_VALUE) {
//            // File is too large
//        }
//
//        // Create the byte array to hold the data
//        byte[] bytes = new byte[(int) length];
//
//        // Read in the bytes
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length && (numRead = is.read(bytes, offset,
//                bytes.length - offset)) >= 0) {
//            offset += numRead;
//        }
//
//        // Ensure all the bytes have been read in
//        if (offset < bytes.length) {
//            throw new IOException(
//                    "Could not completely read file " + file.getName());
//        }
//
//        // Close the input stream and return bytes
//        is.close();
//        return bytes;
//    }
//
//    private boolean exportCRS(File shpFile, int code) {
//        SimpleFeatureSource featureSource = null;
//        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = null;
//        Transaction transaction = null;
//        MathTransform transform = null;
//        try {
//            FileDataStore store = FileDataStoreFinder.getDataStore(shpFile);
//            featureSource = store.getFeatureSource();
//            SimpleFeatureType schema = featureSource.getSchema();
//            CoordinateReferenceSystem dataCRS = schema.getCoordinateReferenceSystem();
//            CoordinateReferenceSystem toCRS = CRS.decode("EPSG:" + code);
//            boolean lenient = true; // allow for some error due to different datums
//            transform = CRS.findMathTransform(dataCRS, toCRS, lenient);
//            DataStoreFactorySpi factory = new ShapefileDataStoreFactory();
//            Map<String, Serializable> create = new HashMap<String, Serializable>();
//            create.put("url", shpFile.toURI().toURL());
//            create.put("create spatial index", Boolean.TRUE);
//            DataStore dataStore = factory.createNewDataStore(create);
//            SimpleFeatureType featureType = SimpleFeatureTypeBuilder.retype(
//                    schema, toCRS);
//            dataStore.createSchema(featureType);
//            transaction = new DefaultTransaction("Reproject");
//            writer = dataStore.getFeatureWriterAppend(featureType.getTypeName(),
//                    transaction);
//        } catch (Exception e) {
//            return false;
//        }
//
//        SimpleFeatureCollection featureCollection = null;
//        SimpleFeatureIterator iterator = null;
//        try {
//            featureCollection = featureSource.getFeatures();
//            iterator = featureCollection.features();
//            while (iterator.hasNext()) {
//                // copy the contents of each feature and transform the geometry
//                SimpleFeature feature = iterator.next();
//                SimpleFeature copy = writer.next();
//                copy.setAttributes(feature.getAttributes());
//
//                Geometry geometry = (Geometry) feature.getDefaultGeometry();
//                Geometry geometry2 = JTS.transform(geometry, transform);
//
//                copy.setDefaultGeometry(geometry2);
//                writer.write();
//            }
//            transaction.commit();
//            return true;
//        } catch (Exception problem) {
//            try {
//                problem.printStackTrace();
//                transaction.rollback();
//                return false;
//            } catch (IOException ex) {
//                return false;
//            }
//        } finally {
//            try {
//                writer.close();
//                iterator.close();
//                transaction.close();
//                return false;
//            } catch (IOException ex) {
//                return false;
//            }
//
//        }
//    }
}
