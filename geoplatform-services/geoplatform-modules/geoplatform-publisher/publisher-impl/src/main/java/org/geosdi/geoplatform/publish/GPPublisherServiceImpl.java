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
import org.geotools.referencing.CRS;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeatureType;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author Luca Paolino - geoSDI
 *
 */

@WebService(endpointInterface = "org.geosdi.geoplatform.publish.GPPublisherService")
public class GPPublisherServiceImpl implements GPPublisherService {


    protected Logger logger = LoggerFactory.getLogger(GPPublisherServiceImpl.class);

    class InfoShape {
        String name;
        String epsg;
        String sld;
    }

    private String RESTURL  = "";
    private String RESTUSER = "";
    private String RESTPW   = "";


    private GeoServerRESTPublisher publisher= null;
    private GeoServerRESTReader reader = null;
    private String tempDir = "";
    private String tempDirZIP = "";
    private String previewWorkspace="";


    public GPPublisherServiceImpl(String _RESTURL, String _RESTUSER, String _RESTPW, String _previewWorkspace) {
        RESTURL = _RESTURL;
        RESTUSER = _RESTUSER;
        RESTPW = _RESTPW;
        previewWorkspace = _previewWorkspace;

        publisher= new GeoServerRESTPublisher(RESTURL, RESTUSER, RESTPW);
        try {
            reader = new GeoServerRESTReader(RESTURL, RESTUSER, RESTPW);
        } catch (MalformedURLException ex) {
            logger.info("Problems for connecting to the REST reader");
        }

        String tmpDir = System.getProperty("java.io.tmpdir");
        if(!tmpDir.endsWith(System.getProperty("file.separator"))){
            tmpDir += System.getProperty("file.separator");
        }
        //creation of the geoportal root temporary directory
        String geoportalDirName = tmpDir + "geoportal";
        File geoportalDir=new File(geoportalDirName);
        boolean success = true;
         if (!geoportalDir.exists()) success = geoportalDir.mkdir();
        //creation of the shp temporary directory. This will contain all the decompressed shape files
        tempDir = tempDir.concat(geoportalDirName + System.getProperty("file.separator") + "shp");
        File dir=new File(tempDir);
         if (!dir.exists()) success = dir.mkdir();
       //creation of the zip temporary directory. This will contain all the compressed zip files
        tempDir = tempDir.concat(System.getProperty("file.separator"));
        tempDirZIP = tempDirZIP.concat(geoportalDirName+ System.getProperty("file.separator") + "zip");
        File dirZip=new File(tempDirZIP);
        
        if (!dirZip.exists()) success = dirZip.mkdir();
        tempDirZIP = tempDirZIP.concat(System.getProperty("file.separator"));
        logger.info("GEOSERVER AT: "+RESTURL+", USER: "+RESTUSER+", PWD: "+RESTPW);
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
    public boolean publish(String workspace, String dataStoreName, String layerName) throws ResourceNotFoundFault, FileNotFoundException {
        logger.info("\n Start to publish "+layerName+" in "+workspace+":"+dataStoreName);

        reload();
        boolean publish = true;
        RESTDataStore dataStore = reader.getDatastore(previewWorkspace, layerName);
        if (dataStore!=null) {
            this.removeLayer(layerName);
            reload();
            boolean unpublish = publisher.unpublishFeatureType(previewWorkspace, layerName, layerName);
            reload();
            boolean remove = publisher.removeDatastore(previewWorkspace, layerName);
        }
        else logger.info("\n The "+previewWorkspace+":"+dataStoreName+" does not exist");
        String filename = tempDirZIP + layerName + ".zip";
        File file = new File(filename);
        if (file.exists()){
          String result = reload();
          List<InfoShape> listInfo=  getInfoFromCompressedShape(file);
          String epsg ="EPSG:4326";
          if (listInfo!=null && listInfo.get(0)!=null) epsg = listInfo.get(0).epsg;
          try {
               publish = publisher.publishShp(workspace, dataStoreName, layerName, file, epsg);
               if (!publish) {
                   logger.info("\n Cannot publish "+layerName+" into "+workspace+":"+dataStoreName);
                   throw new ResourceNotFoundFault("Cannot publish "+layerName+" into "+workspace+":"+dataStoreName);
               }
           } catch(FileNotFoundException e) {
                logger.info("\n ********** File "+layerName+".zip not found");
           }
           file.deleteOnExit();
           return true;
      }
       return false;
    }

    @Override
    public boolean publishAll(String workspace, String dataStoreName, List<String> layerNames) throws ResourceNotFoundFault, FileNotFoundException {
        for (String name: layerNames)
            publish(workspace, dataStoreName, name);
        return true;
    }




/*************************
 *
 * @param dataStoreName
 * @return
 * @throws ResourceNotFoundFault
 * this service removes a layer from the previews workspace
 */
    @Override
    public boolean removeFromPreview(String dataStoreName) throws ResourceNotFoundFault {
        logger.info("Removing "+dataStoreName+" from "+previewWorkspace);
        this.removeLayer(dataStoreName);
        boolean unpublish = publisher.unpublishFeatureType(previewWorkspace, dataStoreName, dataStoreName);
        reload();
        boolean remove = publisher.removeDatastore(previewWorkspace, dataStoreName);
        return true;
    }
  /*******************************
   *
   * @param dataStoreName
   * @return
   * @throws ResourceNotFoundFault
   * get the URL to the PNG if the layer dataStoreName
   */
    private InfoPreview getURLPreviewByDataStoreName(String dataStoreName) throws ResourceNotFoundFault{
          RESTFeatureType featureType =  reader.getFeatureType(reader.getLayer(dataStoreName));
          InfoPreview info = null;
          try {
            info = new InfoPreview(RESTURL, previewWorkspace, dataStoreName, featureType.getMinX(), featureType.getMinY(), featureType.getMaxX(), featureType.getMaxY(),0,0, "EPSG:4326");
          } catch(Exception e) {
              logger.info("The layer "+dataStoreName+" is published in the "+previewWorkspace+" workspace, but the server cannot provide info");
              info = new InfoPreview(dataStoreName, "The layer "+dataStoreName+" is published in the "+previewWorkspace+" workspace, but the server cannot provide info");
          };
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
    public   List<InfoPreview> getPreviewDataStores() throws ResourceNotFoundFault {
          List<InfoPreview> listPreviews = new ArrayList<InfoPreview>();
          RESTDataStoreList list = reader.getDatastores(previewWorkspace);
          for (NameLinkElem element : list) {
              InfoPreview item = getURLPreviewByDataStoreName(element.getName());
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
        } catch(Exception e) {
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
   private List<InfoShape> getInfoFromCompressedShape(File file) {
        logger.info("Call to getInfoFromCompressedShape");
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
                entryName = entryName.replaceAll("/", "_");
                if (entryName.endsWith(".shp")) {
                    logger.info("found shapefile "+entryName);
                    shpList.add(entryName);
                }
                InputStream zipinputstream = zipSrc.getInputStream(entry);
                entryName = entryName.toLowerCase();
                FileOutputStream fileoutputstream = new FileOutputStream(tempDir + entryName);
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

            for (String shpFileName: shpList) {
                // start analisi degli shape
                logger.info("Extracting info from "+shpFileName);
                InfoShape info = new InfoShape();
                info.name = shpFileName.substring(0, shpFileName.length() - 4);
                File shpFile = new File(tempDir + shpFileName);
                FileDataStore store = FileDataStoreFinder.getDataStore(shpFile);
                SimpleFeatureSource featureSource = store.getFeatureSource();
                String geomType = featureSource.getSchema().getGeometryDescriptor().getType().getName().toString();
                logger.info("\n ************** GEOMETRY TYPE : "+geomType);
                if (geomType.equals("MultiPolygon")) info.sld="default_polygon";
                if (geomType.equals("MultiLineString")) info.sld="default_polyline";
                if (geomType.equals("Point")) info.sld="default_point";
                logger.info("\n ************** GEOMETRY TYPE SLD: "+info.sld);
                Integer code  = null;
                try {
                    code = CRS.lookupEpsgCode(featureSource.getSchema().getCoordinateReferenceSystem(), true);
                } catch(Exception e){};
               // if (code.intValue()!=4326) exportCRS(shpFile, 4326);
                if (code!=null) {
                       info.epsg = "EPSG:" + code.toString();
                 }
                else {
                        info.epsg = "EPSG:4326";
                }
                // fine analisi shape
                infoShapeList.add(info);
                compressFiles(info.name+".zip",info.name); // questo metodo comprime in un file <nomeshape>.zip gli shp file associati: shp, dbf, shx e prj
            }
            // svuota la cartella degli shape <tmp>/geoportal/shp
            File directory = new File(tempDir);
            File[] files = directory.listFiles();
            for (File f : files) f.delete();
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
/*******************
 *
 * @return
 * reload the catalogue of geoserver
 */
   private String reload(){
        String sUrl = RESTURL + "/rest/reload";
        return HttpUtilsLocal.post(sUrl, "", "text/html", RESTUSER, RESTPW);
   }

   /******************************
    *
    * @param file
    * @return returns the URL to the PNG of the layer uploaded in the ZIP file
    * @throws ResourceNotFoundFault this exception may be launched when: the ZIP file does not contain a SHP file
    * this service upload in the previews workspace a shapefile. The ZIP file must contain the sho, the prj, the shx and the dbf files. Otherwise, an exception is raised
    ******************************/
    @Override
    public List<InfoPreview> uploadZIPInPreview(File file) throws ResourceNotFoundFault {
        logger.info("Call to uploadZIPInPreview");
        reload();
        List<InfoPreview> infoPreviewList = new ArrayList<InfoPreview>();
        // decompress the zip file in the <tmp>/shp directory, read info and create <layername>.zip files for each layer in <tmp>/zip
        List<InfoShape> infoShapeList = getInfoFromCompressedShape(file);
        if (infoShapeList.isEmpty()) {
            throw new ResourceNotFoundFault("The ZIP archive does not contain shp files");
        }
        for (InfoShape info: infoShapeList) {
            InfoPreview urlPNGPreview = null;
            // check if the layer already exists in the preview, if not an error message is returned int the InfoPreviewList
            RESTDataStore dataStore = reader.getDatastore(previewWorkspace, info.name);
            if (dataStore == null) {
                try {
                     // check if the previews workspace exist, create it if not
                    List<String> workspaces = reader.getWorkspaceNames();
                    if (!workspaces.contains(previewWorkspace)) publisher.createWorkspace(previewWorkspace);
                    // create the <layername>.zip file
                    File temp = new File(tempDirZIP + info.name + ".zip");
                    //publish the <layername>.zip file in the previews workspace
                    boolean published = publisher.publishShp(previewWorkspace, info.name, info.name, temp, info.epsg);
                    // check if pubblication is ok
                    if (published) {
                        logger.info(info.name+ "correctly published in the "+previewWorkspace+" workspace");
                        urlPNGPreview = getURLPreviewByDataStoreName(info.name);
                        urlPNGPreview.setCrs(info.epsg);
                    } else {
                        logger.info("Some problems occured when publishing "+info.name+" into the "+previewWorkspace+" workspace");
                        urlPNGPreview = new InfoPreview(info.name,"Some problems occured when publishing "+info.name+" into the "+previewWorkspace+" workspace");
                    }
                } catch (Exception ex) {
                     logger.info("Some problems occured when publishing "+info.name+" into the "+previewWorkspace+" workspace");
                     ex.printStackTrace();
                     urlPNGPreview = new InfoPreview(info.name, "Some problems occured when publishing "+info.name+" into the "+previewWorkspace+" workspace");
                }
                //publish the shape in the previews workspace
            }
            else {
               urlPNGPreview = getURLPreviewByDataStoreName(info.name);
               urlPNGPreview.setMessage("The data store "+info.name+" in "+previewWorkspace+" already exists");
            }
                // calculate the PNG URL to return
            urlPNGPreview.setUrl(urlPNGPreview.getUrl() + "//wms");
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
    private ZipOutputStream compressFiles(String zipFileName, String shpFileName){
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(tempDirZIP + zipFileName));
            File shpFile = new File(tempDir + shpFileName + ".shp");
            out = compress(out, shpFile);
            File dbfFile = new File(tempDir + shpFileName + ".dbf");
            out = compress(out, dbfFile);
            File shxFile = new File(tempDir + shpFileName + ".shx");
            out = compress(out, shxFile);
            File prjFile = new File(tempDir + shpFileName + ".prj");
            out = compress(out, prjFile);
            out.close();
         } catch (Exception ex) {
            logger.info("\n Exception compressing "+shpFileName+".zip");
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
    public List<InfoPreview> uploadShapeInPreview(File shpFile, File dbfFile, File shxFile, File prjFile) throws ResourceNotFoundFault {
        InfoPreview infoPreview = null;
        List<InfoPreview> listInfoPreview = new ArrayList<InfoPreview>();
        String name = shpFile.getName().substring(0, shpFile.getName().length() - 4);
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempDirZIP +"temp.zip"));
            out = compress(out, shpFile);
            out = compress(out, dbfFile);
            out = compress(out, shxFile);
            out = compress(out, prjFile);
            out.close();
            File compressedFile = new File(tempDirZIP + "temp.zip");
           // compressedFile.deleteOnExit();
            return uploadZIPInPreview(compressedFile);
        } 
        catch (Exception ex) {
            logger.info("the zip file cannot be created because some files are missing or are malformed");
            infoPreview = new InfoPreview(name, "Some problems occured when publishing "+name+" into the "+previewWorkspace+" workspace. The zip file cannot be created because some files are missing or are malformed. Check whether the shp, dbf, shx, prj files are well-formed");
            listInfoPreview.add(infoPreview);
            return listInfoPreview;
        }
    }
}