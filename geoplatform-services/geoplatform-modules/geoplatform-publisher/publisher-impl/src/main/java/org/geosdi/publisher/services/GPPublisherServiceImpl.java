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
package org.geosdi.publisher.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.jws.WebService;
import org.springframework.transaction.annotation.Transactional;
import org.geosdi.publisher.exception.ResourceNotFoundFault;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTDataStore;
import it.geosolutions.geoserver.rest.decoder.RESTFeatureType;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.decoder.utils.NameLinkElem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipFile;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.geosdi.publisher.responce.InfoPreview;
import org.geosdi.publisher.responce.PreviewElement;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;

import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.referencing.CRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author Luca Paolino - geoSDI
 *
 */
@Transactional // Give atomicity on WS methods
@WebService(endpointInterface = "org.geosdi.publisher.services.GPPublisherService")
public class GPPublisherServiceImpl implements GPPublisherService {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
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
        tempDir = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator");
   }

    /****************************
     *
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
        String filename = tempDir + layerName + ".zip";
        File file = new File(filename);
        boolean publish = false;
        if (file.exists()){
           try {
               logger.info("start to publish "+layerName+" into "+workspace+":"+dataStoreName);
               publish = publisher.publishShp(workspace, dataStoreName, layerName, file, "EPSG:4326");
               if (!publish) throw new ResourceNotFoundFault("Cannot publish "+layerName+" into "+workspace+":"+dataStoreName);
               logger.info(layerName+" correctly pulished into "+workspace+":"+dataStoreName);
           } catch(FileNotFoundException e) {
                logger.info("\n ********** File "+layerName+".zip not found");
            }
            RESTDataStore dataStore = reader.getDatastore(previewWorkspace, layerName);
            if (dataStore!=null) System.out.println("\n ***************** trovato");
            else System.out.println("\n ***************** NON trovato");
//            if (dataStore!=null) {
//                publisher.unpublishFeatureType(previewWorkspace, layerName, layerName);
//                publisher.removeDatastore(previewWorkspace, layerName);
//            }
           return true;
      }
       return false;
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
        File file = new File(tempDir + dataStoreName + ".zip");
        file.delete();
        publisher.unpublishFeatureType(previewWorkspace, dataStoreName, dataStoreName);
        logger.info(dataStoreName+" correctly removed from "+previewWorkspace);
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
          InfoPreview info = new InfoPreview(RESTURL, previewWorkspace, dataStoreName, featureType.getMinX(), featureType.getMinY(), featureType.getMaxX(), featureType.getMaxY(),0,0, "EPSG:4326");
          return info;

        //  return RESTURL+"/"+previewWorkspace+"/wms?service=WMS&version=1.1.0&request=GetMap&layers=previews:"+dataStoreName+"&styles=&bbox="+minX+","+minY+","+maxX+","+maxY+"&width=512&height=499&srs="+featureType.getCRS()+"&format=image/png";
   }


    /*************************
     *
     * @return
     * @throws ResourceNotFoundFault
     * this methods returns the list of the datastores in the previews workspace. For each datastore the URL to the PNG is also specified
     */

    @Override
    public   List<PreviewElement> getPreviewDataStores() throws ResourceNotFoundFault {
          List<PreviewElement> listPreviews = new ArrayList<PreviewElement>();
          RESTDataStoreList list = reader.getDatastores(previewWorkspace);
          for (NameLinkElem element : list) {
              PreviewElement item = new PreviewElement(element.getName(), getURLPreviewByDataStoreName(element.getName()));
              listPreviews.add(item);
          }
          return listPreviews;
    }
          /**************
 *
 * @param file the ZIP file from where extracting the info
 * @return the information of the shapefile
 * this method extracts from a zip file conntaining the shape files the name, the CRS and the geometry types
 */
   private InfoShape getInfoFromCompressedShape(File file) {
        System.setProperty("org.geotools.referencing.forceXY", "true");
        String name = "";
        try {
            InfoShape info = new InfoShape();
            ZipFile zipSrc = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipSrc.entries();
            byte[] buf = new byte[1024];
            int n;
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".shp")) {
                    info.name = entry.getName().substring(0, entry.getName().length() - 4);
                    name = info.name;
                }
                InputStream zipinputstream = zipSrc.getInputStream(entry);
                entryName = entryName.toLowerCase();
                FileOutputStream fileoutputstream = new FileOutputStream(tempDir + entryName);
                while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
                    fileoutputstream.write(buf, 0, n);
                }
                fileoutputstream.close();
                zipinputstream.close();
            }
            FileDataStore store = FileDataStoreFinder.getDataStore(new File(tempDir + info.name + ".shp"));
            SimpleFeatureSource featureSource = store.getFeatureSource();
            String geomType = featureSource.getSchema().getGeometryDescriptor().getType().getName().toString();
            if (geomType.equals("MultyPolygon")) info.sld="default_polygon";
            if (geomType.equals("PolyLine")) info.sld="default_polyline";
            if (geomType.equals("MultiPoint")) info.sld="default_point";
            Integer code  = CRS.lookupEpsgCode(featureSource.getSchema().getCoordinateReferenceSystem(), true);
            if (code!=null) {
                   info.epsg = "EPSG:" + code.toString();
             }
            else {
                    info.epsg = "EPSG:4326";
                }
            zipSrc.close();
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
   /******************************
    *
    * @param file
    * @return returns the URL to the PNG of the layer uploaded in the ZIP file
    * @throws ResourceNotFoundFault this exception may be launched when: the ZIP file does not contain a SHP file
    * this service upload in the previews workspace a shapefile. The ZIP file must contain the sho, the prj, the shx and the dbf files. Otherwise, an exception is raised
    ******************************/
    @Override
    public InfoPreview uploadZIPInPreview(File file) throws ResourceNotFoundFault {
        InfoShape info = getInfoFromCompressedShape(file);
        if (info == null) {
            throw new ResourceNotFoundFault("The ZIP archive does not contain a shp file");
        }
        try {
            List<String> workspaces = reader.getWorkspaceNames();
            // check if the previews workspace exist, create it if not
            if (!workspaces.contains(previewWorkspace)) publisher.createWorkspace(previewWorkspace);
            // check if the layer already exists, if not the service returns an exception

            //publish the shape in the previews workspace
            // calculate the PNG URL to return
            InfoPreview urlPNGPreview = null;
            // create the <layername>.zip file
            FileInputStream in = new FileInputStream(file);
            System.out.println("*****************PATH: "+tempDir + info.name + ".zip");
            FileOutputStream out = new FileOutputStream(tempDir + info.name + ".zip");
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            // this checks whether the shp is already published. In this case, the service returns the URL to PNG of the stored shapefile
            RESTDataStore dataStore = reader.getDatastore(previewWorkspace, info.name);
            if (dataStore == null) {
                File temp = new File(tempDir + info.name + ".zip");
                //publish the shape in the previews workspace
                boolean published = publisher.publishShp(previewWorkspace, info.name, info.name, temp, info.epsg);
                if (published) logger.info(info.name+ "correctly published in the "+previewWorkspace+" workspace");
                else logger.info("Some problems occured when publishing "+info.name+" into the "+previewWorkspace+" workspace");
            }
            // calculate the PNG URL to return
            urlPNGPreview = getURLPreviewByDataStoreName(info.name);
            urlPNGPreview.setCrs(info.epsg);
            return urlPNGPreview;
        } catch (FileNotFoundException ex) {
            throw new ResourceNotFoundFault("File not found Exception");
        } catch (IOException ex) {
            throw new ResourceNotFoundFault("IO Exception");
        }
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
    * this service upload in the previews workspace a shapefile. The shapefile file must contain the shp, the prj, the shx and the dbf files. Otherwise, an exception is raised
    */
    @Override
    public InfoPreview uploadShapeInPreview(File shpFile, File dbfFile, File shxFile, File prjFile) throws ResourceNotFoundFault {

        String name = shpFile.getName().substring(0, shpFile.getName().length() - 4);
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempDir + "temp.zip"));
            out = compress(out, shpFile);
            out = compress(out, dbfFile);
            out = compress(out, shxFile);
            out = compress(out, prjFile);
            out.close();
            File compressedFile = new File(tempDir + "temp.zip");
            InfoPreview url = uploadZIPInPreview(compressedFile);
            return url;
        } catch (ResourceNotFoundFault e) {
            logger.info("the Layer already exists");
            throw new ResourceNotFoundFault(name+" layer already exists");
        }
        catch (Exception ex) {
            logger.info("the zip file cannot be created because some files are missing or are malformed");
            throw new ResourceNotFoundFault("Cannot create the zip temp file, some files are missing or are malformed"+ex.getMessage());
        }
    }
}