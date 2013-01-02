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
package org.geosdi.geoplatform.services.utility;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PublishUtility {

    private static Logger logger = LoggerFactory.getLogger(
            PublishUtility.class);
//    public final static String GEOPORTAL = "geoportal";
    public static String TMPDIR;
    public final static String SHP_DIR_NAME = "shp";
    public final static String TIF_DIR_NAME = "tif";
    public final static String ZIP_DIR_NAME = "zip";
    public static final String FILE_NAME = "fileName";
    public static final String FILE_PATH = "filePath";
    public static final String USER_WORKSPACE = "userWorkSpace";
    public static final String PUBLISHER_SERVICE = "publisher_service";

    static {
        TMPDIR = System.getProperty("java.io.tmpdir");
        if (!TMPDIR.endsWith(System.getProperty("file.separator"))) {
            TMPDIR += System.getProperty("file.separator");
        }
    }

    public static String createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return path + System.getProperty("file.separator");
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    public static boolean deleteDir(File directory) {
        if (directory.isDirectory()) {
            String[] children = directory.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(directory, children[i]));
                if (!success) {
//                    logger.error("Failed deleting operation");
                    return false;
                }
            }
        }
        return directory.delete();
    }

    /**
     * **********************
     *
     * @param out the archive stream where compressing the inFile
     * @param inFile the file to compress
     * @return
     * @throws IOException this exception is raised when inFile does not exist
     * this method at the first opens and compresses the fileinFile, then it
     * inserts it in the out stream
     */
    public static ZipOutputStream compress(ZipOutputStream out, File inFile) throws IOException {
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

    public static File generateGeoPortalDirInUserHome() {
        String geoportalDir = System.getProperty("user.home") + System.getProperty("file.separator")
                + "Geoportal" + System.getProperty("file.separator");
        logger.info("Geoportal dir: " + geoportalDir);
        File geoportalDirFile = new File(geoportalDir);
        geoportalDirFile.mkdir();
        return geoportalDirFile;
    }

    public static File copyFile(File origin, String destinationPathDir, String destinationFileName, boolean overwrite) {
        File destination = new File(destinationPathDir);
        destination.mkdirs();
        destination = new File(destination, destinationFileName);
        if (!destination.exists() || overwrite) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
//            } catch (FileNotFoundException ex) {
//                logger.error(ex.getMessage() + " in the specified directory.");
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return destination;
    }

    public static File getFileNameToLowerCase(File file) {
        File fileToReturn = new File(FilenameUtils.getFullPath(file.getAbsolutePath())
                + file.getName().toLowerCase());
        file.renameTo(fileToReturn);
        return file;
    }

    public static void extractEntryToFile(ZipEntry entry, ZipFile zipSrc, String tempUserDir) {
        String entryName;
        FileOutputStream fileoutputstream = null;
        InputStream zipinputstream = null;
        try {
            zipinputstream = zipSrc.getInputStream(entry);
            int lastIndex = entry.getName().lastIndexOf('/');
            entryName = entry.getName().substring(lastIndex + 1).toLowerCase();
            logger.info("INFO: Found file " + entryName);
            fileoutputstream = new FileOutputStream(tempUserDir + entryName.toLowerCase());
            byte[] buf = new byte[1024];
            int n;
            while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
                fileoutputstream.write(buf, 0, n);
            }
        } catch (IOException ioe) {
        } finally {
            try {
                fileoutputstream.close();
                zipinputstream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * ***************
     *
     * @param zipFileName the name of the resulting zip file
     * @param shpFileName the name of the shp file to compress
     * @return
     *
     */
    public static ZipOutputStream compressFiles(String tempUserZipDir,
            String tempUserDir, String zipFileName, String origName,
            String destName) {
        ZipOutputStream out;
        try {
            out = new ZipOutputStream(new FileOutputStream(
                    tempUserZipDir + zipFileName));
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
            out = PublishUtility.compress(out, shpDestFile);
            out = PublishUtility.compress(out, dbfDestFile);
            out = PublishUtility.compress(out, shxDestFile);
            out = PublishUtility.compress(out, prjDestFile);
            if (sldDestFile.exists()) {
                out = PublishUtility.compress(out, sldDestFile);
            }
            out.close();
        } catch (Exception ex) {
            logger.info("Exception compressing: " + zipFileName + " - " + ex);
            return null;
        }
        return out;
    }
}
