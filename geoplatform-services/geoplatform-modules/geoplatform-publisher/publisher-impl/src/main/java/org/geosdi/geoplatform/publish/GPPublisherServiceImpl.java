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
package org.geosdi.geoplatform.publish;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.jws.WebService;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import it.geosolutions.geoserver.rest.decoder.RESTStyleList;
import org.geosdi.geoplatform.request.Feature;
import org.geotools.geometry.jts.JTS;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.zip.ZipFile;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.Attribute;
import org.geosdi.geoplatform.responce.InfoPreview;

import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.FeatureWriter;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.DataStore;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.referencing.CRS;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Paolino - geoSDI
 *
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.publish.GPPublisherService")
public class GPPublisherServiceImpl implements GPPublisherService {

    protected Logger logger = LoggerFactory.getLogger(GPPublisherServiceImpl.class);

    class InfoShape {

        String name;
        String origName;
        String epsg;
        String sld;
    }
    private final String GEOPORTAL = "geoportal";
    private String RESTURL = "";
    private String RESTUSER = "";
    private String RESTPW = "";
    private GeoServerRESTPublisher publisher = null;
    private GeoServerRESTReader reader = null;
    private String tempDir = "";
    private String tempDirZIP = "";
    private String previewWorkspace = "";
    private List<String> managedSessions = new ArrayList<String>();

    @Override
    public boolean verifyAndDeleteSessionDir(String idSessionDestroyed) {
        for (String session : this.managedSessions) {
            System.out.println("Managed sessions: " + session);
            if (idSessionDestroyed.equals(session)) {
                String tmpDir = System.getProperty("java.io.tmpdir");
                if (!tmpDir.endsWith(System.getProperty("file.separator"))) {
                    tmpDir += System.getProperty("file.separator");
                }
                String shpDir = tmpDir + GEOPORTAL + System.getProperty("file.separator") + "shp";
                String zipDir = tmpDir + GEOPORTAL + System.getProperty("file.separator") + "zip";
                this.deleteDir(new File(shpDir + System.getProperty("file.separator") + session));
                this.deleteDir(new File(zipDir + System.getProperty("file.separator") + session));
                logger.info("Deleted user folder: " + session);
                this.managedSessions.remove(session);
                return true;
            }
        }
        return false;
    }

    private boolean deleteDir(File directory) {
        if (directory.isDirectory()) {
            String[] children = directory.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(directory, children[i]));
                if (!success) {
                    logger.error("Failed deleting operation");
                    return false;
                }
            }
        }
        return directory.delete();
    }

    public GPPublisherServiceImpl(String RESTURL, String RESTUSER, String RESTPW, String _previewWorkspace) {
        this.RESTURL = RESTURL;
        this.RESTUSER = RESTUSER;
        this.RESTPW = RESTPW;
        previewWorkspace = _previewWorkspace;

        publisher = new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        try {
            reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        } catch (MalformedURLException ex) {
            logger.info("Problems for connecting to the REST reader");
        }

        String tmpDir = System.getProperty("java.io.tmpdir");
        if (!tmpDir.endsWith(System.getProperty("file.separator"))) {
            tmpDir += System.getProperty("file.separator");
        }
        //creation of the geoportal root temporary directory
        String geoportalDirName = tmpDir + "geoportal";
        File geoportalDir = new File(geoportalDirName);
        boolean success = true;
        if (!geoportalDir.exists()) {
            success = geoportalDir.mkdir();
        }
        //creation of the shp temporary directory. This will contain all the decompressed shape files
        tempDir = tempDir.concat(geoportalDirName + System.getProperty("file.separator") + "shp");
        File dir = new File(tempDir);
        if (!dir.exists()) {
            success = dir.mkdir();
        }
        //creation of the zip temporary directory. This will contain all the compressed zip files
        tempDir = tempDir.concat(System.getProperty("file.separator"));
        tempDirZIP = tempDirZIP.concat(geoportalDirName + System.getProperty("file.separator") + "zip");
        File dirZip = new File(tempDirZIP);

        if (!dirZip.exists()) {
            success = dirZip.mkdir();
        }
        tempDirZIP = tempDirZIP.concat(System.getProperty("file.separator"));
        logger.info("GEOSERVER AT: " + RESTURL + ", USER: " + RESTUSER + ", PWD: " + RESTPW);
    }

    private String createDir(String user) {
        File dir = new File(tempDir + user);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return tempDir + user + System.getProperty("file.separator");
    }

    private String createZIPDir(String user) {
        File dir = new File(tempDirZIP + user);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return tempDirZIP + user + System.getProperty("file.separator");
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
    public boolean publish(String userName, String workspace, String dataStoreName, String layerName) throws ResourceNotFoundFault, FileNotFoundException {
        logger.info("\n Start to publish " + layerName + " in " + workspace + ":" + dataStoreName);
        String tempUserDirZIP = createZIPDir(userName);
        String userWorkspace = createWorkspace(userName);
        reload();
        boolean publish = true;
        RESTDataStore dataStore = reader.getDatastore(userWorkspace, layerName);

        if (dataStore != null) {
            this.removeLayer(layerName);
            reload();
            boolean unpublish = publisher.unpublishFeatureType(userWorkspace, layerName, layerName);
            reload();
            boolean remove = publisher.removeDatastore(userWorkspace, layerName);
        } else {
            logger.info("\n The " + userWorkspace + ":" + dataStoreName + " does not exist");
        }
        String filename = tempUserDirZIP + layerName + ".zip";
        File file = new File(filename);
        if (file.exists()) {
            String result = reload();
            List<InfoShape> listInfo = getInfoFromCompressedShape(userName, file);
            String epsg = "EPSG:4326";
            String sld = null;
            String name = null;
            if (listInfo != null && listInfo.get(0) != null) {
                epsg = listInfo.get(0).epsg;
                sld = listInfo.get(0).sld;
                name = listInfo.get(0).name;
                logger.info("\n PUBLISHING IN THE DB " + epsg + " , " + sld);
            }
            try {
                publish = publisher.publishShp(workspace, dataStoreName, layerName, file, epsg, sld);
                if (!publish) {
                    logger.info("\n Cannot publish " + layerName + " into " + workspace + ":" + dataStoreName);
                    throw new ResourceNotFoundFault("Cannot publish " + layerName + " into " + workspace + ":" + dataStoreName);
                }
            } catch (FileNotFoundException e) {
                logger.info("\n ********** File " + layerName + ".zip not found");
            }
            file.deleteOnExit();
            return true;
        }
        return false;
    }

    @Override
    public boolean publishAll(String userName, String workspace, String dataStoreName, List<String> layerNames) throws ResourceNotFoundFault, FileNotFoundException {
        for (String name : layerNames) {
            publish(userName, workspace, dataStoreName, name);
        }
        return true;
    }

    @Override
    public boolean publishAllofPreview(String userName, String workspace, String dataStoreName) throws ResourceNotFoundFault, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   /**********************
    *
    * @param userName this is the user name who
    * @param list the list of
    * @param shpFileName
    * @return
    * @throws ResourceNotFoundFault
    * @throws Exception
    */
    public byte[] createSHP(String userName, List<Feature> list, String shpFileName) throws ResourceNotFoundFault, Exception {
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        logger.info("\n INFO: Parsing feature schema");
        if (list.size() > 0) {
            GeometryFactory geometryFactory = new GeometryFactory();

            WKTReader reader = new WKTReader(geometryFactory);
            try {
                Geometry geometry = reader.read(list.get(0).getWkt());
                typeBuilder.setDefaultGeometry("the_geom");
                typeBuilder.add("the_geom", geometry.getClass());
            } catch (ParseException e) {
                throw new ResourceNotFoundFault("Cannot identify the geometry class");
            }
            List<Attribute> attributes = list.get(0).getAttributes();
            for (Attribute attribute : attributes) {
                Class clazz = null;
                if (attribute.getType().equals("int")) {
                    clazz = Integer.class;
                }
                if (attribute.getType().equals("double")) {
                    clazz = Double.class;
                }
                if (attribute.getType().equals("float")) {
                    clazz = Float.class;
                }
                if (attribute.getType().equals("String")) {
                    clazz = String.class;
                }
                if (attribute.getType().equals("char")) {
                    clazz = Character.class;
                }
                typeBuilder.add(attribute.getName(), clazz);
            }

        }

        typeBuilder.setName("SHP_CREATION");
        logger.info("\n  INFO: Parsing feature data ");
        SimpleFeatureBuilder fb = new SimpleFeatureBuilder(typeBuilder.buildFeatureType());
        SimpleFeatureCollection result = FeatureCollections.newCollection();
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        Geometry geometry = null;
        int featureID = 0;
        for (Feature feature : list) {
            List<Attribute> attributes = feature.getAttributes();
            try {
                geometry = reader.read(feature.getWkt());
            } catch (ParseException e) {
                continue;
            }
            fb.set("the_geom", geometry);
            for (Attribute attribute : attributes) {
                Object value = null;
                logger.info("Name: " + attribute.getName() + " type:" + attribute.getType() + " value: " + attribute.getValue());
                if (attribute.getType().equals("int")) {
                    value = new Integer(Integer.parseInt(attribute.getValue()));
                }
                if (attribute.getType().equals("double")) {
                    value = new Double(Double.parseDouble(attribute.getValue()));
                }
                if (attribute.getType().equals("float")) {
                    value = new Float(Float.parseFloat(attribute.getValue()));
                }
                if (attribute.getType().equals("String")) {
                    value = attribute.getValue();
                }
                fb.set(attribute.getName(), value);
                result.add(fb.buildFeature("feature_" + (featureID++)));
            }


        }
        logger.info("\n  INFO: Creating SHP files ");
        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = null;
        SimpleFeatureIterator iterator = null;
        Transaction transaction = null;
        try {
            String tempUserDir = createDir(userName);
            ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
            Map<String, Serializable> params = new HashMap<String, Serializable>();
            String shpFullPathName = tempUserDir + shpFileName;
            File newFile = new File(shpFullPathName);
            params.put("url", newFile.toURI().toURL());
            params.put("create spatial index", Boolean.TRUE);
            ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
            newDataStore.forceSchemaCRS(DefaultGeographicCRS.WGS84);
            newDataStore.createSchema(result.getSchema()); // DA ECCEZIONE
            writer = newDataStore.getFeatureWriter(Transaction.AUTO_COMMIT);
            iterator = result.features();
            transaction = new DefaultTransaction("Reproject");
            while (iterator.hasNext()) {
                // copy the contents of each feature and transform the geometry
                SimpleFeature feature = iterator.next();
                SimpleFeature copy = writer.next();
                copy.setAttributes(feature.getAttributes());
                Geometry geometrySource = (Geometry) feature.getDefaultGeometry();
                copy.setDefaultGeometry(geometrySource);
                writer.write();
            }
            transaction.commit();
            logger.info("\n  INFO: Compressing SHP Files ");
            String tempUserZipDir = createZIPDir(userName);
            String name = shpFileName.substring(0, shpFileName.length() - 4);
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempUserZipDir + name + ".zip"));
            out = compress(out, new File(tempUserDir + name + ".shp"));
            out = compress(out, new File(tempUserDir + name + ".dbf"));
            out = compress(out, new File(tempUserDir + name + ".shx"));
            out = compress(out, new File(tempUserDir + name + ".prj"));
            out.close();
            return getStreamOfByteFromFile(tempUserZipDir + name+".zip");
        } catch (MalformedURLException ex) {
            throw new ResourceNotFoundFault("Malformed URL Exception");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ResourceNotFoundFault("IO Exception");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            throw new ResourceNotFoundFault("Exception");
        } finally {
            writer.close();
            iterator.close();
            transaction.close();
        }
    }

    private byte[] getStreamOfByteFromFile(String fileName) throws FileNotFoundException, IOException {
        File file = new File(fileName);
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }


    /*************************
     *
     * @param dataStoreName
     * @return
     * @throws ResourceNotFoundFault
     * this service removes a layer from the previews workspace
     */
    @Override
    public boolean removeFromPreview(String userName, String dataStoreName) throws ResourceNotFoundFault {
        String userWorkspace = createWorkspace(userName);
        logger.info("Removing " + dataStoreName + " from " + userWorkspace);
        this.removeLayer(dataStoreName);
        boolean unpublish = publisher.unpublishFeatureType(userWorkspace, dataStoreName, dataStoreName);
        reload();
        boolean remove = publisher.removeDatastore(userWorkspace, dataStoreName);
        return true;
    }

    /*******************************
     *
     * @param dataStoreName
     * @return
     * @throws ResourceNotFoundFault
     * get the URL to the PNG if the layer dataStoreName
     */
    private InfoPreview getURLPreviewByDataStoreName(String userName, String dataStoreName) throws ResourceNotFoundFault {
        RESTFeatureType featureType = reader.getFeatureType(reader.getLayer(dataStoreName));
        logger.info("\n *********************" + dataStoreName + "  *********************** " + featureType.getMaxX());
        String userWorkspace = createWorkspace(userName);
        InfoPreview info = null;
        try {
            info = new InfoPreview(RESTURL, userWorkspace, dataStoreName, featureType.getMinX(), featureType.getMinY(), featureType.getMaxX(), featureType.getMaxY(), "EPSG:4326");
        } catch (Exception e) {
            logger.info("The layer " + dataStoreName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
            info = new InfoPreview(dataStoreName, "The layer " + dataStoreName + " is published in the " + userWorkspace + " workspace, but the server cannot provide info");
        }
        return info;

        //  return RESTURL+"/"+previewWorkspace+"/wms?service=WMS&version=1.1.0&request=GetMap&layers=previews:"+dataStoreName+"&styles=&bbox="+minX+","+minY+","+maxX+","+maxY+"&width=512&height=499&srs="+featureType.getCRS()+"&format=image/png";
    }

    /*************************
     *
     * @return
     * @throws ResourceNotFoundFault
     * this methods returns the list of the datastores in the previews workspace. For each datastore the info to find the PNG is also specified
     */
    @Override
    public List<InfoPreview> getPreviewDataStores(String userName) throws ResourceNotFoundFault {
        reload();
        List<InfoPreview> listPreviews = new ArrayList<InfoPreview>();
        String tempUserZipDir = createZIPDir(userName);
        String userWorkspace = createWorkspace(userName);
        File dir = new File(tempUserZipDir);
        RESTDataStoreList list = reader.getDatastores(userWorkspace);
        for (NameLinkElem element : list) {
            String name = element.getName();
            InfoPreview item = getURLPreviewByDataStoreName(userName, name);
            listPreviews.add(item);
        }
        return listPreviews;
    }

    private boolean exportCRS(File shpFile, int code) {
        SimpleFeatureSource featureSource = null;
        FeatureWriter<SimpleFeatureType, SimpleFeature> writer = null;
        Transaction transaction = null;
        MathTransform transform = null;
        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(shpFile);
            featureSource = store.getFeatureSource();
            SimpleFeatureType schema = featureSource.getSchema();
            CoordinateReferenceSystem dataCRS = schema.getCoordinateReferenceSystem();
            CoordinateReferenceSystem toCRS = CRS.decode("EPSG:" + code);
            boolean lenient = true; // allow for some error due to different datums
            transform = CRS.findMathTransform(dataCRS, toCRS, lenient);
            DataStoreFactorySpi factory = new ShapefileDataStoreFactory();
            Map<String, Serializable> create = new HashMap<String, Serializable>();
            create.put("url", shpFile.toURI().toURL());
            create.put("create spatial index", Boolean.TRUE);
            DataStore dataStore = factory.createNewDataStore(create);
            SimpleFeatureType featureType = SimpleFeatureTypeBuilder.retype(schema, toCRS);
            dataStore.createSchema(featureType);
            transaction = new DefaultTransaction("Reproject");
            writer = dataStore.getFeatureWriterAppend(featureType.getTypeName(), transaction);
        } catch (Exception e) {
            return false;
        }

        SimpleFeatureCollection featureCollection = null;
        SimpleFeatureIterator iterator = null;
        try {
            featureCollection = featureSource.getFeatures();
            iterator = featureCollection.features();
            while (iterator.hasNext()) {
                // copy the contents of each feature and transform the geometry
                SimpleFeature feature = iterator.next();
                SimpleFeature copy = writer.next();
                copy.setAttributes(feature.getAttributes());

                Geometry geometry = (Geometry) feature.getDefaultGeometry();
                Geometry geometry2 = JTS.transform(geometry, transform);

                copy.setDefaultGeometry(geometry2);
                writer.write();
            }
            transaction.commit();
            return true;
        } catch (Exception problem) {
            try {
                problem.printStackTrace();
                transaction.rollback();
                return false;
            } catch (IOException ex) {
                return false;
            }
        } finally {
            try {
                writer.close();
                iterator.close();
                transaction.close();
                return false;
            } catch (IOException ex) {
                return false;
            }

        }
    }

    /**************
     *
     * @param file the ZIP file from where extracting the info
     * @return the information of the shapefile
     * this method extracts from a zip file conntaining the shape files the name, the CRS and the geometry types
     */
    private List<InfoShape> getInfoFromCompressedShape(String userName, File file) {
        logger.info("Call to getInfoFromCompressedShape");
        String tempUserDir = createDir(userName);
        String tempUserZipDir = createZIPDir(userName);
        String userWorkspace = createWorkspace(userName);
        System.setProperty("org.geotools.referencing.forceXY", "true");
        List<String> shpList = new ArrayList<String>();
        List<InfoShape> infoShapeList = new ArrayList<InfoShape>();
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
                RESTDataStore dataStore = null;
                InfoShape info = new InfoShape();
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
                    logger.info("\n INFO: FOUND STYLE FILE " + tempUserDir + SLDFileName + ". TRYING TO PUBLISH WITH " + info.name + " NAME");
                    boolean returnPS = false;
                    if (!existsStyle(info.name))
                        returnPS = publisher.publishStyle(fileSLD, info.name);
                    logger.info("\n INFO: PUBLISH STYLE RESULT " + returnPS);
                }
                logger.info("\n INFO: STYLE " + info.sld + " for " + info.name);
                Integer code = null;
                try {
                    code = CRS.lookupEpsgCode(featureSource.getSchema().getCoordinateReferenceSystem(), true);
                } catch (Exception e) {
                };
                // if (code.intValue()!=4326) exportCRS(shpFile, 4326);
                if (code != null) {
                    info.epsg = "EPSG:" + code.toString();
                } else {
                    info.epsg = "EPSG:4326";
                }
                // fine analisi shape
                infoShapeList.add(info);
                compressFiles(tempUserZipDir, tempUserDir, info.name + ".zip", info.name, info.name); // questo metodo comprime in un file <nomeshape>.zip gli shp file associati: shp, dbf, shx e prj
            }
            // svuota la cartella degli shape <tmp>/geoportal/shp
            File directory = new File(tempUserDir);
            File[] files = directory.listFiles();
            for (File f : files) {
                f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        for (int i=0;i < styleList.size();i++){
            if (styleList.get(i).getName().equals(styleName)) return true;
        }
        return false;
    }

    /**************
     *
     * @param workspace
     * @param layerName
     * @return
     * check whether the layer layerName exists in the workspace workspace
     */
    public boolean existsLayer(String workspace, String layerName) {
        RESTDataStoreList workspaceDataStores = reader.getDatastores(workspace);
        for (int i=0;i < workspaceDataStores.size();i++){
            if (workspaceDataStores.get(i).getName().equals(layerName)) return true;
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
    * create the workspace "previewWorkspace+"_"+userName" if not exists
    */
   private String createWorkspace(String userName){
         List<String> workspaces = reader.getWorkspaceNames();
         String userWorkspace = previewWorkspace+"_"+userName;
         if (!workspaces.contains(userWorkspace)) publisher.createWorkspace(userWorkspace);
         return userWorkspace;


    }

    /******************************
     *
     * @param file
     * @return returns the URL to the PNG of the layer uploaded in the ZIP file
     * @throws ResourceNotFoundFault this exception may be launched when: the ZIP file does not contain a SHP file
     * this service upload in the previews workspace a shapefile. The ZIP file must contain the sho, the prj, the shx and the dbf files. Otherwise, an exception is raised
     ******************************/
    @Override
    public List<InfoPreview> uploadZIPInPreview(String userName, File file) throws ResourceNotFoundFault {
        logger.info("Call to uploadZIPInPreview");
        this.managedSessions.add(userName);
        reload();
        String tempUserDir = createDir(userName);
        String tempUserZipDir = createZIPDir(userName);
        String userWorkspace = createWorkspace(userName);
        List<InfoPreview> infoPreviewList = new ArrayList<InfoPreview>();
        // decompress the zip file in the <tmp>/shp directory, read info and create <layername>.zip files for each layer in <tmp>/zip
        List<InfoShape> infoShapeList = getInfoFromCompressedShape(userName, file);
        if (infoShapeList.isEmpty()) {
            throw new ResourceNotFoundFault("The ZIP archive does not contain shp files");
        }
        for (InfoShape info : infoShapeList) {
            InfoPreview urlPNGPreview = null;
            // check if the layer already exists in the preview, if not an error message is returned int the InfoPreviewList
            

            if (!existsLayer(userWorkspace, info.name) ) {
                try {
                    // check if the previews workspace exist, create it if not
                    // create the <layername>.zip file
                    String fileName = tempUserZipDir + info.name + ".zip";
                    File temp = new File(fileName);
                    //publish the <layername>.zip file in the previews workspace
                    logger.info("\n INFO: STYLE TO PUBLISH " + info.sld + " NAME :" + info.name);
                    boolean published = false;
                    logger.info("\n INFO: CREATE DATASTORE "+userWorkspace+" NAME :"+info.name);
                    published = publisher.publishShp(userWorkspace, info.name, info.name, temp, info.epsg, info.sld);
                    // check if publish is ok otherwise returns an error message
                    if (published) {
                        logger.info(info.name + "correctly published in the " + userWorkspace + " workspace");
                        urlPNGPreview = getURLPreviewByDataStoreName(userName, info.name);
                        urlPNGPreview.setCrs(info.epsg);
                    } else {
                        logger.info("Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace: may be the layer is already published in a db");
                        urlPNGPreview = new InfoPreview(info.name, "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                    }
                } catch (Exception ex) {

                    logger.info("Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                    ex.printStackTrace();
                    urlPNGPreview = new InfoPreview(info.name, "Some problems occured when publishing " + info.name + " into the " + userWorkspace + " workspace");
                }
                //publish the shape in the previews workspace
           } else {
                urlPNGPreview = getURLPreviewByDataStoreName(userName, info.name);
                urlPNGPreview.setMessage("The data store " + info.name + " in " + userWorkspace + " already exists");
            }
            // calculate the PNG URL to return
            urlPNGPreview.setUrl(urlPNGPreview.getUrl() + "/wms");
            infoPreviewList.add(urlPNGPreview);
        }
        return infoPreviewList;

    }

    /*****************
     *
     * @param zipFileName the name of the resulting zip file
     * @param shpFileName the name of the shp file to compress
     * @return
     *
     */
    private ZipOutputStream compressFiles(String tempUserZipDir, String tempUserDir, String zipFileName, String origName, String destName) {
        ZipOutputStream out = null;

        try {
            out = new ZipOutputStream(new FileOutputStream(tempUserZipDir + zipFileName));
            File shpFile = new File(tempUserDir + origName + ".shp");
            File dbfFile = new File(tempUserDir + origName + ".dbf");
            File shxFile = new File(tempUserDir + origName + ".shx");
            File prjFile = new File(tempUserDir + origName + ".prj");

            File shpDestFile = shpFile;
            File dbfDestFile = dbfFile;
            File shxDestFile = shxFile;
            File prjDestFile = prjFile;

            File sldFile = new File(tempUserDir + origName + ".sld");
            File sldDestFile = sldFile;

            if (destName != null) {
                shpDestFile = new File(tempUserDir + destName + ".shp");
                shpFile.renameTo(shpDestFile);
                dbfDestFile = new File(tempUserDir + destName + ".dbf");
                dbfFile.renameTo(dbfDestFile);
                shxDestFile = new File(tempUserDir + destName + ".shx");
                shxFile.renameTo(shxDestFile);
                prjDestFile = new File(tempUserDir + destName + ".prj");
                prjFile.renameTo(prjDestFile);
                sldDestFile = new File(tempUserDir + destName + ".sld");
                sldFile.renameTo(sldDestFile);
            }
            out = compress(out, shpDestFile);
            out = compress(out, dbfDestFile);
            out = compress(out, shxDestFile);
            out = compress(out, prjDestFile);
            if (sldDestFile.exists()) {
                out = compress(out, sldDestFile);
            }
            out.close();
        } catch (Exception ex) {
            logger.info("\n Exception compressing " + zipFileName + ".zip");
            return null;
        }
        return out;
    }

    /************************
     *
     * @param out the archive stream where compressing the inFile
     * @param inFile the file to compress
     * @return
     * @throws IOException this exception is raised when inFile does not exist
     * this method at the first opens and compresses  the fileinFile, then it inserts it in the out stream
     */
    private ZipOutputStream compress(ZipOutputStream out, File inFile) throws IOException {
        FileInputStream inShpFil = new FileInputStream(inFile); // Stream to read file
        ZipEntry entryShp = new ZipEntry(inFile.getName()); // Make a ZipEntry
        out.putNextEntry(entryShp); // Store entry
        byte[] buffer = new byte[4096]; // Create a buffer for copying
        int bytesRead;
        while ((bytesRead = inShpFil.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        inShpFil.close();
        return out;
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
    public List<InfoPreview> uploadShapeInPreview(String userName, File shpFile, File dbfFile, File shxFile, File prjFile, File sldFile) throws ResourceNotFoundFault {
        InfoPreview infoPreview = null;
        List<InfoPreview> listInfoPreview = new ArrayList<InfoPreview>();
        String name = shpFile.getName().substring(0, shpFile.getName().length() - 4);
        String tempUserDirZIP = createZIPDir(userName);
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempUserDirZIP + "temp.zip"));
            out = compress(out, shpFile);
            out = compress(out, dbfFile);
            out = compress(out, shxFile);
            out = compress(out, prjFile);
            if (sldFile != null) {
                out = compress(out, sldFile);
            }
            out.close();
            File compressedFile = new File(tempUserDirZIP + "temp.zip");
            // compressedFile.deleteOnExit();
            return uploadZIPInPreview(userName, compressedFile);
        } catch (Exception ex) {
            logger.info("the zip file cannot be created because some files are missing or are malformed");
            infoPreview = new InfoPreview(name, "Some problems occured when publishing " + name + " into the " + previewWorkspace + " workspace. The zip file cannot be created because some files are missing or are malformed. Check whether the shp, dbf, shx, prj files are well-formed");
            listInfoPreview.add(infoPreview);
            return listInfoPreview;
        }
    }
}