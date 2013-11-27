/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.google.common.collect.Lists;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.*;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType.Attribute;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder;
import it.geosolutions.geoserver.rest.encoder.coverage.GSCoverageEncoder;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Resource;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;
import org.geosdi.geoplatform.responce.InfoPreview;
import org.geosdi.geoplatform.responce.LayerAttribute;
import org.geosdi.geoplatform.services.geotiff.GeoTiffOverviews;
import org.geosdi.geoplatform.services.geotiff.GeoTiffOverviewsConfiguration;
import org.geosdi.geoplatform.services.utility.Ds2dsConfiguration;
import org.geosdi.geoplatform.services.utility.FeatureConfiguration;
import org.geosdi.geoplatform.services.utility.PostGISUtility;
import org.geosdi.geoplatform.services.utility.PublishUtility;
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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class GPPublisherBasicServiceImpl implements IGPPublisherService,
        InitializingBean {

    private Logger logger = LoggerFactory.getLogger(
            GPPublisherBasicServiceImpl.class);

    class LayerInfo {

        String name;
        String fileName;
        boolean isShp;
        String epsg = "";
        String sld;
        boolean isPresent;
    }
    private String RESTURL = "";
    private String RESTUSER = "";
    private String RESTPW = "";
    //
    @Resource(name = "sharedRestPublisher")
    private GeoServerRESTPublisher restPublisher;
    //
    @Resource(name = "sharedRestReader")
    private GeoServerRESTReader restReader;
    private String geoportalDir = "";
    //
    @Autowired
    private PublisherScheduler scheduler;
    //
    @Autowired
    private GeoTiffOverviewsConfiguration overviewsConfiguration;
    //
    @Autowired
    private PostGISUtility postGISUtility;
    //
    @Autowired
    private ShapeAppender shapeAppender;
    @Autowired
    private Ds2dsConfiguration ds2dsConfiguration;

    public GPPublisherBasicServiceImpl(String RESTURL, String RESTUSER, String RESTPW,
            String geoportalDir) {
        this.RESTURL = RESTURL;
        this.RESTUSER = RESTUSER;
        this.RESTPW = RESTPW;
        this.geoportalDir = this.manageGeoportalDir(geoportalDir);
        logger.info("GEOPORTAL DIR @@@@@@@@@@@@@@@@@@@@@ " + this.geoportalDir);
        logger.info("GEOSERVER AT: " + RESTURL + ", USER: " + RESTUSER
                + ", PWD: " + RESTPW + ", USING DIR: " + geoportalDir);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("DB_POSTGIS_PARAMETER @@@@@@@@@@@@@@@@@@@@@@@@ " + postGISUtility);
    }

    private String manageGeoportalDir(String geoportalDir) {
        File geoportalDirFile;
        if (geoportalDir != null) {
            geoportalDirFile = new File(geoportalDir);
            if (!geoportalDirFile.exists()) {
                boolean result = false;
                try {
                    result = geoportalDirFile.mkdir();
                } catch (Exception e) {
                    logger.error("Impossible to create the defined dir, "
                            + "trying to create one in user dir");
                }
                if (!result) {
                    geoportalDirFile = PublishUtility.generateGeoPortalDirInUserHome();
                }
            }
        } else {
            geoportalDirFile = PublishUtility.generateGeoPortalDirInUserHome();
        }
        if (!geoportalDirFile.exists()) {
            logger.error(
                    "@@@@@@@@@@@ Impossible to create GeoPortalDir @@@@@@@@@@@@@@@@");
            throw new SecurityException("Can't Create " + geoportalDir);
        }
        String pathGeoPortalDir = geoportalDirFile.getAbsolutePath();
        if (!pathGeoPortalDir.endsWith(System.getProperty("file.separator"))) {
            pathGeoPortalDir = pathGeoPortalDir + System.getProperty(
                    "file.separator");
        }
        return pathGeoPortalDir;
    }

    @Override
    public boolean publishStyle(String styleToPublish) throws ResourceNotFoundFault {
        return restPublisher.publishStyle(styleToPublish);
    }

    @Override
    public boolean putStyle(String styleToPublish, String styleName) throws ResourceNotFoundFault {
        return restPublisher.updateStyle(styleToPublish, styleName);
    }

    @Override
    public String loadStyle(String layerDatasource, String styleName) throws
            ResourceNotFoundFault {
        String dataSource = layerDatasource;
        if (layerDatasource.matches(".*:80/")) {
            dataSource = dataSource.replaceFirst(":80", "");
        }
        System.out.println("Data source: " + dataSource);
        if (!dataSource.startsWith(RESTURL)) {
            //The requested style can't be loaded from the rest url configured.
            throw new ResourceNotFoundFault(
                    "The requested style can't be "
                    + "loaded from the rest url configured on the publisher service.");
        }
        return this.restReader.getSLD(styleName);
    }

    @Override
    public List<LayerAttribute> describeFeatureType(String layerName) throws ResourceNotFoundFault {
        RESTLayer restLayer = this.restReader.getLayer(layerName);
        List<LayerAttribute> result = Lists.<LayerAttribute>newArrayList();
        for (Attribute att : this.restReader.getFeatureType(restLayer).getAttributes()) {
            LayerAttribute layerAttribute = new LayerAttribute(att.getName(),
                    att.getBinding());
            result.add(layerAttribute);
        }
        return result;
    }

    /**
     * **************************
     * System.getProperty("java.io.tmpdir") +
     * System.getProperty("file.separator") + "geoportal"+
     * System.getProperty("file.separator") + "shp";
     *
     * @param workspace
     * @param dataStoreName
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault
     * @throws FileNotFoundException this service publishes the layer
     * <layerName> we loaded in the previews workspace into the DB datastore
     * identified by the <dataStoreName> and published into the <workspace>
     * workspace
     */
    @Override
    public boolean publish(String sessionID, String workspace,
            String dataStoreName, String layerName) throws ResourceNotFoundFault, FileNotFoundException {
        logger.info(
                "\n Start to publish " + layerName + " in " + workspace + ":" + dataStoreName);
        return this.unscheduleJob(layerName);
    }

    @Override
    public boolean publishAll(String sessionID, String workspace, String dataStoreName,
            List<String> layerNames) throws ResourceNotFoundFault, FileNotFoundException {
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

    /**
     * ***********************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault this service removes a layer from the
     * workspace
     */
    public boolean removeSHPFromPreview(String workspace, String layerName) throws ResourceNotFoundFault {
        String userWorkspace = this.getWorkspace(workspace);
        logger.info("Removing shp " + layerName + " from " + userWorkspace);
        this.removeLayer(layerName);
        restPublisher.unpublishFeatureType(userWorkspace, layerName, layerName);
        reload();
        restPublisher.removeDatastore(userWorkspace, layerName, Boolean.TRUE);
        return true;
    }

    /**
     * ***********************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault this service removes a layer from the
     * workspace
     */
    public boolean removeTIFFromPreview(String userName, String layerName) throws ResourceNotFoundFault {
        String userWorkspace = getWorkspace(userName);
        logger.info("Removing tif " + layerName + " from " + userWorkspace);
        this.removeLayer(layerName);
        restPublisher.unpublishCoverage(userWorkspace, layerName, layerName);
        reload();
        restPublisher.removeCoverageStore(userWorkspace, layerName, Boolean.FALSE);
        return true;
    }

    private InfoPreview getTIFURLByLayerName(String userName, String layerName) {
        RESTLayer layer = restReader.getLayer(userName, layerName);
        RESTCoverage featureType = restReader.getCoverage(layer);
        String userWorkspace = getWorkspace(userName);
        InfoPreview info = null;
        try {
            Integer code = this.getEPSGCodeFromString(featureType.getCRS());
            String epsgCode = null;
            if (code != null) {
                epsgCode = "EPSG:" + code.toString();
            }
            info = new InfoPreview(RESTURL, userWorkspace, layerName,
                    featureType.getMinX(), featureType.getMinY(),
                    featureType.getMaxX(), featureType.getMaxY(),
                    epsgCode, layer.getDefaultStyle(), Boolean.FALSE, Boolean.TRUE);
        } catch (Exception e) {
            final String error = "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info. " + e;
            logger.error(error);
            throw new IllegalArgumentException(error, e.getCause());
//            info = new InfoPreview(layerName,
//                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
        }
        return info;
    }

    /**
     * *****************************
     *
     * @param layerName
     * @return
     * @throws ResourceNotFoundFault get the URL to the PNG if the layer
     * dataStoreName
     */
    private InfoPreview getSHPURLByDataStoreName(String userName, String layerName)
            throws ResourceNotFoundFault, IllegalArgumentException {
        RESTLayer layer = restReader.getLayer(userName, layerName);
        RESTFeatureType featureType = restReader.getFeatureType(layer);
        String userWorkspace = getWorkspace(userName);
        InfoPreview infoPreview = null;
        try {
            logger.info("Parameters: userWorkspace: " + userWorkspace + " - layerName: " + layerName
                    + " - featureType: " + featureType + " - layer: " + layer + " - RESTURL: " + RESTURL);
//            Map<String, String> parametersMap = Maps.newHashMap();
//            parametersMap.put("url", layerName);
//            featureType = DataStoreFinder.getDataStore(parametersMap).getFeatureSource(layerName);
//            System.out.println("" + CRS.getGeographicBoundingBox());
            Integer code = this.getEPSGCodeFromString(featureType.getCRS());
            String epsgCode = null;
            if (code != null) {
                epsgCode = "EPSG:" + code.toString();
            }
            infoPreview = new InfoPreview(RESTURL, userWorkspace, layerName,
                    featureType.getMinX(), featureType.getMinY(),
                    featureType.getMaxX(), featureType.getMaxY(),
                    epsgCode, layer.getDefaultStyle(), Boolean.TRUE, Boolean.TRUE);
        } catch (Exception e) {
            final String error = "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info. " + e;
            logger.error(error);
            throw new IllegalArgumentException(error, e.getCause());
//            info = new InfoPreview(layerName,
//                    "The layer " + layerName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
        }
        return infoPreview;
    }

    /**
     * ***********************
     *
     * @param userName
     * @return @throws ResourceNotFoundFault this methods returns the list of
     * the datastores in the user workspace. For each datastore the info to find
     * the PNG is also specified
     */
    @Override
    public List<InfoPreview> getPreviewDataStores(String userName) throws ResourceNotFoundFault {
        reload();
        List<InfoPreview> listPreviews = Lists.<InfoPreview>newArrayList();
        String userWorkspace = getWorkspace(userName);
        RESTDataStoreList list = restReader.getDatastores(userWorkspace);
        for (NameLinkElem element : list) {
            try {
                String name = element.getName();
                InfoPreview item = getSHPURLByDataStoreName(userName, name);
                listPreviews.add(item);
            } catch (IllegalArgumentException ex) {
                throw new ResourceNotFoundFault(ex.getMessage());
            }
        }
        return listPreviews;
    }

    /**
     * ************
     *
     * @param file the ZIP file from where extracting the info
     * @return the information of the shapefile this method extracts from a zip
     * file containing the shape files, the name, the CRS and the geometry types
     */
    private List<LayerInfo> getInfoFromCompressedFile(String userName, File file,
            String tempUserDir, String tempUserZipDir, String tempUserTifDir) {
        logger.debug("Call to getInfoFromCompressedShape");
        System.setProperty("org.geotools.referencing.forceXY", "true");
        List<String> shpEntryNameList = Lists.<String>newArrayList();
        List<String> tifEntryNameList = Lists.<String>newArrayList();
        List<ZipEntry> sldEntryList = Lists.<ZipEntry>newArrayList();
        List<ZipEntry> prjEntryList = Lists.<ZipEntry>newArrayList();
        List<LayerInfo> infoShapeList = Lists.<LayerInfo>newArrayList();
        ZipFile zipSrc = null;
        try {
            // decomprime il contenuto di file nella cartella <tmp>/geoportal/shp
            zipSrc = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipSrc.entries();
            String destinationDir;
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
                entryName = entryName.substring(lastIndex + 1).toLowerCase();
                destinationDir = tempUserDir;
                if (entryName.equals("")) {
                    continue;
                } else if (entryName.endsWith(".tif") || entryName.endsWith(".tiff")) {
                    logger.info("INFO: Found geotiff file " + entryName);
                    tifEntryNameList.add(entryName);
                    destinationDir = tempUserTifDir;
                } else if (entryName.endsWith(".shp")) {
                    logger.info("INFO: Found shape file " + entryName);
                    shpEntryNameList.add(entryName);
                } else if (entryName.endsWith(".sld")) {
                    logger.info("Adding sld to entry list: " + entryName);
                    sldEntryList.add(entry);
                    continue;
                } else if (entryName.endsWith(".prj")) {
                    logger.info("Adding prj to entry list: " + entryName);
                    prjEntryList.add(entry);
                    continue;
                } else if (entryName.endsWith(".tfw")) {
                    destinationDir = tempUserTifDir;
                }
                PublishUtility.extractEntryToFile(entry, zipSrc, destinationDir);
            }
            //Verificare presenza file sld associato a geotiff oppure a shp file
            this.putEntryInTheRightDir(sldEntryList, zipSrc, tempUserTifDir, tempUserDir, tifEntryNameList);
            this.putEntryInTheRightDir(prjEntryList, zipSrc, tempUserTifDir, tempUserDir, tifEntryNameList);
            // fine decompressione
        } catch (Exception e) {
            logger.error("ERROR: " + e);
            throw new IllegalArgumentException("ERROR: " + e);
        } finally {
            try {
                zipSrc.close();
            } catch (IOException ex) {
            }
        }
        infoShapeList.addAll(this.analyzeShpList(shpEntryNameList, userName,
                tempUserDir, tempUserZipDir));
        infoShapeList.addAll(this.analyzeTifList(tifEntryNameList, userName,
                tempUserTifDir));
        // svuota la cartella degli shape <tmp>/geoportal/UserDir
        File directory = new File(tempUserDir);
        File[] files = directory.listFiles();
        for (File f : files) {
            f.delete();
        }
        return infoShapeList;
    }

    private void putEntryInTheRightDir(List<ZipEntry> entryListElement, ZipFile zipSrc,
            String tempUserTifDir, String tempUserDir, List<String> tifEntryNameList)
            throws ResourceNotFoundFault {
        for (ZipEntry elementEntry : entryListElement) {
            int lastIndex = elementEntry.getName().lastIndexOf('/');
            int endNamePos = elementEntry.getName().lastIndexOf('.');
            String prjEntryName = elementEntry.getName().substring(lastIndex + 1, endNamePos).toLowerCase();
            logger.debug("elementEntryName: " + prjEntryName);
            if (this.isDuplicatedName(prjEntryName, tifEntryNameList)) {//geotiff sld
                logger.debug("in geotiff");
                PublishUtility.extractEntryToFile(elementEntry, zipSrc, tempUserTifDir);
            } else {//shp sld
                logger.debug("in shp");
                PublishUtility.extractEntryToFile(elementEntry, zipSrc, tempUserDir);
            }
        }
    }

    private boolean isDuplicatedName(String fileName, List<String> tifEntryNameList) {
        for (String tifEntryName : tifEntryNameList) {
            String tifName = tifEntryName.substring(0, tifEntryName.lastIndexOf(
                    '.'));
            if (tifName.equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    private List<LayerInfo> analyzeTifList(List<String> tifEntryNameList, String userName,
            String tempUserTifDir) {
        List<LayerInfo> infoTifList = Lists.<LayerInfo>newArrayList();
        for (String tifFileName : tifEntryNameList) {
            LayerInfo info = new LayerInfo();
            info.isShp = false;
            String origName = tifFileName.substring(0, tifFileName.lastIndexOf(
                    "."));
            String idName = userName + "_" + origName;
            info.name = new String(idName);
            File oldGeotifFile = new File(tempUserTifDir, tifFileName);
            //
            RESTCoverage coverage = this.restReader.getCoverage(userName, idName, idName);
            File newGeoTifFile;
            if (coverage != null) {
                info.isPresent = Boolean.TRUE;
                idName += System.currentTimeMillis();;
                info.fileName = idName;
            }
            newGeoTifFile = PublishUtility.copyFile(oldGeotifFile,
                    tempUserTifDir, idName + ".tif", true);
            //
            oldGeotifFile.delete();
            info.epsg = "EPSG:" + this.getCRSFromGeotiff(newGeoTifFile);
            String SLDFileName = origName + ".sld";
            File fileSLD = new File(tempUserTifDir, SLDFileName);
            if (fileSLD.exists()) {
                File filePublished = PublishUtility.copyFile(fileSLD,
                        tempUserTifDir, idName + ".sld", true);
                fileSLD.delete();
                info.sld = this.publishSLD(filePublished, idName);
            } else {
                info.sld = "default_raster";
            }
            String TFWFileName = origName + ".tfw";
            File fileTFW = new File(tempUserTifDir, TFWFileName);
            if (fileTFW.exists()) {
                PublishUtility.copyFile(fileTFW,
                        tempUserTifDir, idName + ".tfw", true);
                fileTFW.delete();
            }
            String PRJFileName = origName + ".prj";
            File filePRJ = new File(tempUserTifDir, PRJFileName);
            if (filePRJ.exists()) {
                PublishUtility.copyFile(filePRJ,
                        tempUserTifDir, idName + ".prj", true);
                filePRJ.delete();
            }
            infoTifList.add(info);
        }
        return infoTifList;
    }

    private String publishSLD(File fileSLD, String layerName) {
        reload();
        logger.info(
                "\n INFO: FOUND STYLE FILE. TRYING TO PUBLISH WITH " + layerName + " NAME");
        if (existsStyle(layerName)) {
            restPublisher.removeStyle(layerName);
        }
        boolean returnPS = restPublisher.publishStyle(fileSLD, layerName);
        logger.info("\n INFO: PUBLISH STYLE RESULT " + returnPS);
        return layerName;
    }

    private SimpleFeatureSource getFeatureSource(File file) {
        SimpleFeatureSource featureSource = null;
        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            featureSource = store.getFeatureSource();
        } catch (IOException ex) {
            this.logger.error("Error analyzing file : " + file.getPath() + " - " + ex);
        }
        return featureSource;
    }

    private Integer getEPSGCodeFromString(String crs) {
        Integer codeToReturn = null;
        if (crs.startsWith("GEOGCS")) {
            CoordinateReferenceSystem coordinateReferenceSystem = null;
            try {
                coordinateReferenceSystem = CRS.parseWKT(crs);
            } catch (FactoryException fe) {
                logger.error("Failed to extract CoordinateReferenceSystem from String: " + fe);
            }
            codeToReturn = this.getEPSGCode(coordinateReferenceSystem);
        } else if (crs.startsWith("EPSG")) {
            codeToReturn = Integer.parseInt(crs.substring(crs.indexOf(":") + 1));
        }
        return codeToReturn;
    }

    private Integer getEPSGCode(SimpleFeatureSource featureSource) {
        return this.getEPSGCode(featureSource.getSchema().getCoordinateReferenceSystem());
    }

    private Integer getEPSGCode(CoordinateReferenceSystem coordinateReferenceSystem) {
        Integer code = null;
        try {
            logger.info("Info for EPSG calculation: " + coordinateReferenceSystem);
            code = CRS.lookupEpsgCode(coordinateReferenceSystem, true);
        } catch (FactoryException e) {
            logger.error("Failed to retrieve EPSG code: " + e);
        }
        return code;
    }

    private List<LayerInfo> analyzeShpList(List<String> shpEntryNameList, String userName,
            String tempUserDir, String tempUserZipDir) {
        List<LayerInfo> infoShapeList = Lists.<LayerInfo>newArrayList();
        for (String shpFileName : shpEntryNameList) {
            // start analizing shape
            logger.info("Extracting info from " + shpFileName);
            LayerInfo info = new LayerInfo();
            info.isShp = true;
            String origName = shpFileName.substring(0, shpFileName.length() - 4);
            info.name = userName + "_shp_" + origName;
            //
            RESTLayer layer = this.restReader.getLayer(userName, info.name);
            if (layer != null) {
                info.isPresent = Boolean.TRUE;
            }
            //
            File shpFile = new File(tempUserDir + shpFileName);
            SimpleFeatureSource featureSource = this.getFeatureSource(shpFile);
            String geomType = featureSource.getSchema().getGeometryDescriptor().getType().getName().toString();
            String SLDFileName = origName + ".sld";
            File fileSLD = new File(tempUserDir + SLDFileName);
            if (fileSLD.exists()) {
                info.sld = this.publishSLD(fileSLD, info.name);
            } else if (geomType.equals("MultiPolygon")) {
                info.sld = "default_polygon";
            } else if (geomType.equals("MultiLineString")) {
                info.sld = "default_polyline";
            } else if (geomType.equals("Point")) {
                info.sld = "default_point";
            } else {
                info.sld = info.name;
            }
            logger.info("\n INFO: STYLE " + info.sld + " for " + info.name);
            Integer code = this.getEPSGCode(featureSource);
            if (code != null) {
                info.epsg = "EPSG:" + code.toString();
            }
            logger.info("EPSG Code retrieved: " + info.epsg);
            // End shape analisys
            infoShapeList.add(info);
            PublishUtility.compressFiles(tempUserZipDir, tempUserDir,
                    info.name + ".zip",
                    origName, info.name); // questo metodo comprime in un file <nomeshape>.zip gli shp file associati: shp, dbf, shx e prj
        }
        return infoShapeList;
    }

    /**
     * *************
     *
     * @param layer the layer to remove
     * @return perform a REST call for deleting the layer
     */
    private boolean removeLayer(String layer) {
        return this.restPublisher.removeLayer(null, layer);
//        String sUrl = RESTURL + "/rest/layers/" + layer + "?purge=true";
//        return HttpUtilsLocal.delete(sUrl, RESTUSER, RESTPW);
    }

    /**
     * ***********
     *
     * @param styleName
     * @return check whether the style styleName exists
     */
    @Override
    public boolean existsStyle(String styleName) {
        RESTStyleList styleList = restReader.getStyles();
        for (int i = 0; i < styleList.size(); i++) {
            if (styleList.get(i).getName().equalsIgnoreCase(styleName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ************
     *
     * @param workspace
     * @param dataStoreName
     * @return check whether the layer layerName exists in the workspace
     * workspace
     */
    public boolean existsLayerInDataStore(String workspace, String dataStoreName, String layerName) {
        boolean result = Boolean.FALSE;
        String layerStoreName = null;
        RESTLayer layer = restReader.getLayer(workspace, layerName);
        if (layer != null) {
            RESTFeatureType restft = restReader.getFeatureType(layer);
            if (restft != null) {
                layerStoreName = restft.getName();
            }
        }
        if (layerStoreName != null && dataStoreName.equals(layerStoreName)) {
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * ************
     *
     * @param workspace
     * @param dataStoreName
     * @return check whether the dataStore exists in the workspace
     */
    public boolean existsDataStore(String workspace, String dataStoreName) {
        boolean result = false;
        RESTDataStore restDataStores = restReader.getDatastore(
                workspace, dataStoreName);
        logger.debug("*** existsDataStore restDataStores value: " + restDataStores);
        if (restDataStores != null) {
            result = true;
        }
        return result;
    }

    /**
     * ************
     *
     * @param workspace
     * @param csStoreName
     * @return check whether the coverageStore exists in the workspace
     */
    public boolean existsCoverageStore(String workspace, String csStoreName) {
        boolean result = false;
        RESTCoverageStore restCoverageStores = restReader.getCoverageStore(
                workspace, csStoreName);
        logger.debug("*** existsCoverageStore restCoverageStores value: " + restCoverageStores);
        if (restCoverageStores != null) {
            result = true;
        }
        return result;
    }

    /**
     * *****************
     *
     * @return reload the catalogue of geoserver
     */
    private boolean reload() {
        return this.restPublisher.reload();
//        String sUrl = RESTURL + "/rest/reload";
//        return HttpUtilsLocal.post(sUrl, "", "text/html", RESTUSER, RESTPW);
    }

    /**
     * ******************
     *
     * @param userName
     * @return
     *
     * creates the workspace if not exists or returns the existing one
     */
    private String getWorkspace(String userName) {
        List<String> workspaces = restReader.getWorkspaceNames();
        String userWorkspace = userName;
        if (!workspaces.contains(userWorkspace)) {
            restPublisher.createWorkspace(userWorkspace);
        }
        return userWorkspace;
    }

    private Integer getCRSFromGeotiff(File file) {
        Integer code = null;
        try {
            GeoTiffReader geotiffReader = new GeoTiffReader(file,
                    new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER,
                            Boolean.TRUE));
            GridCoverage2D coverage = (GridCoverage2D) geotiffReader.read(null);
            CoordinateReferenceSystem crs = coverage.getCoordinateReferenceSystem2D();
            code = CRS.lookupEpsgCode(crs, Boolean.TRUE);
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
    public InfoPreview analyzeTIFInPreview(String userName, File file, boolean overwrite) throws ResourceNotFoundFault {
        logger.info("Call to analyzeTIFInPreview");
        String userWorkspace = getWorkspace(userName);
        String epsg = "EPSG:" + this.getCRSFromGeotiff(file);
        String sld = "default_raster";
        String fileName = userName + "_" + file.getName().substring(0,
                file.getName().lastIndexOf("."));

        InfoPreview infoPreview = new InfoPreview(RESTURL, userWorkspace, new String(fileName),
                0d, 0d, 0d, 0d, epsg, sld, Boolean.FALSE, Boolean.FALSE);

        String pathInTifDir = this.geoportalDir + userName + System.getProperty("file.separator")
                + PublishUtility.TIF_DIR_NAME + System.getProperty("file.separator");
        if (existsCoverageStore(userWorkspace, fileName)) {
            logger.debug("********** analyzeTIFInPreview existsCoverageStore(userWorkspace, fileName): "
                    + userWorkspace + " - " + fileName);
            infoPreview.setMessage("The data store " + fileName + " in " + userWorkspace
                    + " already exists");
            fileName = fileName + System.currentTimeMillis();
            infoPreview.setFileName(fileName);
            infoPreview.setIsPresent(Boolean.TRUE);
        }
        File fileInTifDir = PublishUtility.copyFile(file, pathInTifDir,
                fileName + ".tif", overwrite);
        this.addTifCleanerJob(userWorkspace, fileName, fileInTifDir.getAbsolutePath());
        logger.debug("********** analyzeTIFInPreview: Info preview to return: " + infoPreview);
        return infoPreview;
    }

    private boolean unscheduleJob(String completeLayerName) {
        boolean result = false;
        try {
            TriggerKey key = new TriggerKey(completeLayerName,
                    PublisherScheduler.PUBLISHER_GROUP);
            result = this.scheduler.getScheduler().unscheduleJob(key);
            logger.debug("Job unscheduled: " + result);
        } catch (SchedulerException ex) {
            logger.error("Error unscheduling publisher cleaner job: " + ex);
        }
        return result;
    }

    private void addTifCleanerJob(String userWorkspace, String layerName, String filePath) {
        TriggerKey triggerKey = new TriggerKey(userWorkspace + ":" + layerName,
                PublisherScheduler.PUBLISHER_GROUP);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, 30);
        Trigger trigger = TriggerBuilder.newTrigger().forJob(
                this.scheduler.getCleanerJobTifDetail()).
                withIdentity(triggerKey).
                withDescription("Runs after 30 minutes").
                startAt(calendar.getTime()).
                withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().
                        withMisfireHandlingInstructionFireAndProceed()).
                build();
        trigger.getJobDataMap().put(PublishUtility.USER_WORKSPACE,
                userWorkspace);
        trigger.getJobDataMap().put(PublishUtility.FILE_NAME, layerName);
        trigger.getJobDataMap().put(PublishUtility.FILE_PATH, filePath);
        trigger.getJobDataMap().put(PublishUtility.PUBLISHER_SERVICE, this);
        this.scheduleTrigger(triggerKey, trigger);
    }

    private void addShpCleanerJob(String userWorkspace, String layerName, String filePath) {
        TriggerKey triggerKey = new TriggerKey(userWorkspace + ":" + layerName,
                PublisherScheduler.PUBLISHER_GROUP);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MINUTE, 30);
        Trigger trigger = TriggerBuilder.newTrigger().forJob(
                this.scheduler.getCleanerJobShpDetail()).
                withIdentity(triggerKey).
                withDescription("Runs after 30 minutes").
                startAt(calendar.getTime()).
                withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().
                        withMisfireHandlingInstructionFireAndProceed()).
                build();
        trigger.getJobDataMap().put(PublishUtility.USER_WORKSPACE, userWorkspace);
        trigger.getJobDataMap().put(PublisherShpCleanerJob.LAYER_NAME, layerName);
        trigger.getJobDataMap().put(PublishUtility.FILE_PATH, filePath);
        trigger.getJobDataMap().put(PublishUtility.PUBLISHER_SERVICE, this);
        this.scheduleTrigger(triggerKey, trigger);
    }

    private void scheduleTrigger(TriggerKey triggerKey, Trigger trigger) {
        try {
            if (this.scheduler.getScheduler().checkExists(triggerKey)) {
                this.scheduler.getScheduler().rescheduleJob(triggerKey, trigger);
            } else {
                this.scheduler.getScheduler().scheduleJob(trigger);
            }
        } catch (SchedulerException ex) {
            logger.error("Error adding publisher cleaner job: " + ex);
        }
    }

    @Override
    public List<InfoPreview> processEPSGResult(String userName, List<InfoPreview> previewLayerList)
            throws ResourceNotFoundFault {
        String tempUserDir = PublishUtility.createDir(this.geoportalDir + userName);
        String tempUserZipDir = PublishUtility.createDir(tempUserDir + PublishUtility.ZIP_DIR_NAME);
        String tempUserTifDir = PublishUtility.createDir(tempUserDir + PublishUtility.TIF_DIR_NAME);
        String userWorkspace = getWorkspace(userName);
        List<InfoPreview> infoPreviewList = Lists.<InfoPreview>newArrayList();
        for (InfoPreview infoPreview : previewLayerList) {
            if (infoPreview.getLayerPublishAction() != null) {
                if (infoPreview.isIsShape()) {
                    if (infoPreview.getNewName() != null && !infoPreview.getNewName().isEmpty()
                            && this.restReader.getLayer(userWorkspace, userName + "_shp_" + infoPreview.getNewName()) != null) {
                        throw new ResourceNotFoundFault("A layer named: " + infoPreview.getNewName() + " already exists");
                    }
                    if (infoPreview.getLayerPublishAction().equals(LayerPublishAction.RENAME)) {
                        PublishUtility.manageRename(userName, infoPreview, tempUserDir);
                    }
                } else {
                    if (infoPreview.getNewName() != null && !infoPreview.getNewName().isEmpty()
                            && this.restReader.getLayer(userWorkspace, userName + "_" + infoPreview.getNewName()) != null) {
                        throw new ResourceNotFoundFault("A layer named: " + infoPreview.getNewName() + " already exists");
                    }
                    boolean result = PublishUtility.manageRename(userName, infoPreview, tempUserDir);
                    //Pubblicare lo stile se ho duplicato il tif
                    if (result) {
                        String SLDFileName = infoPreview.getDataStoreName() + ".sld";
                        File fileSLD = new File(tempUserTifDir, SLDFileName);
                        if (fileSLD.exists()) {
                            infoPreview.setStyleName(this.publishSLD(fileSLD, infoPreview.getDataStoreName()));
                        }
                        String coverageStoreName = new String(infoPreview.getDataStoreName());
                        logger.debug("********** processEPSGResult Before removing coverage store: " + coverageStoreName);
                        if (restReader.getCoverage(userWorkspace, coverageStoreName, coverageStoreName) != null) {
                            logger.debug("********** processEPSGResult removing coverage store: " + coverageStoreName);
                            restPublisher.removeCoverageStore(userWorkspace, coverageStoreName, Boolean.TRUE);
                        }
                    }
                }
            }
            LayerInfo info = new LayerInfo();
            info.epsg = infoPreview.getCrs();
            info.name = infoPreview.getDataStoreName();
            info.sld = infoPreview.getStyleName();
            if (infoPreview.isIsShape() && infoPreview.getLayerPublishAction() != null
                    && infoPreview.getLayerPublishAction().equals(LayerPublishAction.APPEND)) {
                logger.info("***** processEPSGResult: Executing shape append for zip file: " + infoPreview.getFileName());
                //Settiamo una nuova source feature per evitare di usare quella vecchia
                //che punta al precedente file shp
                ds2dsConfiguration.setSourceFeature(new FeatureConfiguration());
                ds2dsConfiguration.setForcePurgeAllData(Boolean.FALSE);
                ds2dsConfiguration.setPurgeData(Boolean.FALSE);
                this.shapeAppender.importFile(tempUserDir, new File(infoPreview.getFileName()));
                infoPreview = getSHPURLByDataStoreName(userWorkspace, infoPreview.getDataStoreName());
//                infoPreview.setLayerPublishAction(LayerPublishAction.APPEND);
                if (infoPreview.getUrl().indexOf("/wms") == -1) {
                    infoPreview.setUrl(infoPreview.getUrl() + "/wms");
                }
            } else if (infoPreview.isIsShape() && infoPreview.getLayerPublishAction() != null
                    && infoPreview.getLayerPublishAction().equals(LayerPublishAction.OVERRIDE)) {
                //Settiamo una nuova source feature per evitare di usare quella vecchia
                //che punta al precedente file shp
                ds2dsConfiguration.setSourceFeature(new FeatureConfiguration());
                ds2dsConfiguration.setForcePurgeAllData(Boolean.TRUE);
                this.shapeAppender.importFile(tempUserDir, new File(infoPreview.getFileName()));
                infoPreview = getSHPURLByDataStoreName(userWorkspace, infoPreview.getDataStoreName());
//                        infoPreview.setLayerPublishAction(LayerPublishAction.OVERRIDE);
                if (infoPreview.getUrl().indexOf("/wms") == -1) {
                    infoPreview.setUrl(infoPreview.getUrl() + "/wms");
                }
            } else if (infoPreview.isIsShape()) {
                logger.info("***** processEPSGResult: Executing shape publish zip file: " + infoPreview.getFileName());
                info.isShp = Boolean.TRUE;
                infoPreview = this.publishShpInPreview(userWorkspace, info, tempUserZipDir);
            } else if (!infoPreview.isIsShape()) {
                info.isShp = Boolean.FALSE;
                File fileInTifDir = new File(tempUserTifDir, info.name + ".tif");
                infoPreview = this.publishTifInPreview(userName, userWorkspace,
                        fileInTifDir, info.name, info.epsg, info.sld);
            }
            infoPreviewList.add(infoPreview);
        }
        return infoPreviewList;
    }

    /**
     * ****************************
     *
     * @param file
     * @return returns the URL to the PNG of the layer uploaded in the ZIP file
     * @throws ResourceNotFoundFault this exception may be launched when: the
     * ZIP file does not contain a SHP file this service upload in the user
     * workspace a shapefile. The ZIP file must contain the shp, the prj, the
     * shx and the dbf files. Otherwise, an exception is raised
     * ****************************
     */
    @Override
    public List<InfoPreview> analyzeZIPEPSG(String sessionID, String userName, File file) throws ResourceNotFoundFault {
        logger.info("Call to analyzeZIPInPreview");
        reload();
        file = PublishUtility.getFileNameToLowerCase(file);
        String tempUserDir = PublishUtility.createDir(
                this.geoportalDir + userName);
        String tempUserZipDir = PublishUtility.createDir(
                tempUserDir + PublishUtility.ZIP_DIR_NAME);
        String tempUserTifDir = PublishUtility.createDir(
                tempUserDir + PublishUtility.TIF_DIR_NAME);
        // decompress the zip file in the <tmp>/shp directory, read info and create <layername>.zip files for each layer in <tmp>/zip
        // and decompress the geotiff files in user/tiff direcotry
        List<LayerInfo> infoShapeList = getInfoFromCompressedFile(userName, file,
                tempUserDir, tempUserZipDir, tempUserTifDir);
        if (infoShapeList.isEmpty()) {
            throw new ResourceNotFoundFault("The ZIP archive does not contain shp or geotiff files");
        }
        //Fine analisi ed creazione risposta
        List<InfoPreview> infoPreviewList = Lists.<InfoPreview>newArrayList();
        String userWorkspace = getWorkspace(userName);
        InfoPreview infoPreview;
        for (LayerInfo info : infoShapeList) {
            if (info.isShp) {
                infoPreview = new InfoPreview(RESTURL, userWorkspace, info.name,
                        0d, 0d, 0d, 0d, info.epsg, info.sld, Boolean.TRUE, info.isPresent);
                File fileShp = new File(tempUserZipDir, info.name + ".zip");
                infoPreview.setFileName(fileShp.getAbsolutePath());
                this.addShpCleanerJob(userWorkspace, info.name, fileShp.getAbsolutePath());
            } else {
                File fileInTifDir = new File(tempUserTifDir, info.name + ".tif");
                infoPreview = new InfoPreview(RESTURL, userWorkspace, info.name,
                        0d, 0d, 0d, 0d, info.epsg, info.sld, Boolean.FALSE, info.isPresent);
                /**
                 * Solo per i tiff è possibile avere già un nuome nuovo in fase
                 * di preview visto che vengono immediatamente posizionati nella
                 * cartella utente in cui potrebbe già essere presente un tif
                 * con lo stesso nome e per evitare collisioni si assegna un
                 * suffisso al nome del file tif e dei suoi associati sul disco
                 */
                infoPreview.setFileName(info.fileName);
                this.addTifCleanerJob(userWorkspace, info.name, fileInTifDir.getAbsolutePath());
            }
            infoPreviewList.add(infoPreview);
        }
        return infoPreviewList;
    }

    private InfoPreview publishTifInPreview(String userName, String userWorkspace,
            File fileInTifDir, String fileName, String epsg, String sld) {
        InfoPreview infoPreview;
        GeoTiffOverviews.overviewTiff(overviewsConfiguration, fileInTifDir.getAbsolutePath());
//        if (restReader.getCoverage(userWorkspace, fileName, fileName) != null) {
//            restPublisher.removeCoverageStore(userWorkspace, fileName, Boolean.TRUE);
//        }
        try {
//                logger.info(
//                        "\n INFO: STYLE TO PUBLISH " + info.sld + " NAME :" + info.name);
//                logger.info(
//                        "\n INFO: CREATE DATASTORE " + userWorkspace + " NAME :" + info.name);
//            RESTCoverageStore store = restPublisher.publishExternalGeoTIFF(userWorkspace, fileName, fileInTifDir, epsg, sld);
            boolean published = restPublisher.publishExternalGeoTIFF(userWorkspace,
                    fileName, fileInTifDir, fileName, epsg, GSResourceEncoder.ProjectionPolicy.FORCE_DECLARED, sld);
            if (published) {
                GSCoverageEncoder coverageEncoder = new GSCoverageEncoder();
                coverageEncoder.setName(fileName);
                coverageEncoder.setTitle(fileName);
                this.restPublisher.configureCoverage(coverageEncoder, userWorkspace, fileName);
                logger.info(
                        fileInTifDir + " correctly published in the " + userWorkspace + " workspace");
                infoPreview = getTIFURLByLayerName(userName, fileName);
            } else {
                logger.info(
                        "Some problems occured when publishing " + fileInTifDir
                        + " into the " + userWorkspace + " workspace: may be the layer is already published in a db");
                infoPreview = new InfoPreview(fileName,
                        "Some problems occured when publishing " + fileInTifDir
                        + " into the " + userWorkspace + " workspace");
            }
        } catch (Exception ex) {
            logger.error("Some problems occured when publishing " + fileName
                    + " into the " + userWorkspace + " workspace");
            ex.printStackTrace();
            infoPreview = new InfoPreview(fileName,
                    "Some problems occured when publishing "
                    + fileName + " into the " + userWorkspace + " workspace");
        }
        // calculate the PNG URL to return
        infoPreview.setUrl(infoPreview.getUrl() + "/wms");
        return infoPreview;
    }

    private InfoPreview publishShpInPreview(String userWorkspace, LayerInfo info,
            String tempUserZipDir) {
        InfoPreview infoPreview = null;
        String datatStoreName = userWorkspace;
        // check if the dataStore already exists
        if (existsDataStore(userWorkspace, datatStoreName)) {
            boolean result = restPublisher.unpublishFeatureType(userWorkspace,
                    datatStoreName, info.name);
            logger.info("Removing existing FeatureType: " + info.name + " with result: " + result);
        } else {
            postGISUtility.generateEncoder(datatStoreName, userWorkspace);
        }

        // create the <layername>.zip file
        String fileName = tempUserZipDir + info.name + ".zip";
        File tempFile = new File(fileName);
        //publish the <layername>.zip file in the user workspace
        logger.info("\n INFO: STYLE TO PUBLISH " + info.sld + " NAME: " + info.name);
        logger.info("\n INFO: CREATE DATASTORE " + userWorkspace + " NAME: " + info.name);
        try {
            logger.info("INFO EPSG: " + info.epsg);
            boolean published = restPublisher.publishShp(userWorkspace, datatStoreName, info.name, tempFile, info.epsg, info.sld);
            if (published) {
                logger.info(info.name + " correctly published in the " + userWorkspace + " workspace " + info.name);
                infoPreview = getSHPURLByDataStoreName(userWorkspace, info.name);
            } else {
                logger.info(
                        "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace: may be the layer is already published in a db");
                infoPreview = new InfoPreview(info.name,
                        "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
            }
        } catch (Exception ex) {
            logger.error("Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
            ex.printStackTrace();
            infoPreview = new InfoPreview(info.name,
                    "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
        } finally {
            tempFile.delete();
        }
        //This code returns the previous publisher layer without overwriting it
//            } else {
//                infoPreview = getSHPURLByDataStoreName(username, info.name);
//                infoPreview.setMessage(
//                        "The data store " + datatStoreName + " in " + userWorkspace + " already exists");
//            }
        // calculate the PNG URL to return
        infoPreview.setUrl(infoPreview.getUrl() + "/wms");
        return infoPreview;
    }

}
