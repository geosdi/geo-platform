/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.services.utility;

import java.io.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.shared.publisher.LayerPublishAction;
import org.geosdi.geoplatform.responce.InfoPreview;
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

    public static boolean deleteDir(String directory) {
        return deleteDir(new File(directory));
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

    private static boolean renameZipShp(String userName, InfoPreview infoPreview,
            String tempUserDir) throws ResourceNotFoundFault {
        String tempUserZipDir = PublishUtility.createDir(tempUserDir + PublishUtility.ZIP_DIR_NAME);
        boolean result = false;
        LayerPublishAction layerPublishAction = infoPreview.getLayerPublishAction();
        String newName = userName + "_shp_" + infoPreview.getNewName();
        if (layerPublishAction != null && layerPublishAction.equals(LayerPublishAction.RENAME)
                && newName != null && !newName.equalsIgnoreCase(infoPreview.getDataStoreName())) {
            String fileName = tempUserZipDir + infoPreview.getDataStoreName() + ".zip";
            File previousFile = new File(fileName);
            ZipFile zipSrc = null;
            String renameDirPath = tempUserZipDir + "rename" + System.getProperty("file.separator");
            try {
                PublishUtility.createDir(renameDirPath);
                logger.debug("********* ManageRename renameDirPath: " + renameDirPath);
                //Decomprime il contenuto dello zip nella cartella rename
                zipSrc = new ZipFile(previousFile);
                Enumeration<? extends ZipEntry> entries = zipSrc.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    PublishUtility.extractEntryToFile(entry, zipSrc, renameDirPath);
                }
                logger.debug("********* ManageRename element unzipped");
                //Dopo l'estrazione rinominare e creare zip
                compressFiles(tempUserZipDir, renameDirPath, newName + ".zip",
                        infoPreview.getDataStoreName(), newName);
                logger.debug("********* ManageRename after compress file");
                //Cancellare vecchio zip
                previousFile.delete();
                logger.debug("********* ManageRename after delete previous file");
                result = Boolean.TRUE;
            } catch (Exception e) {
                logger.error("ERRORE : " + e);
                throw new ResourceNotFoundFault(e.getMessage());
            } finally {
                try {
                    zipSrc.close();
                    //Cancella cartella rename
                    PublishUtility.deleteDir(renameDirPath);
                    logger.debug("********* ManageRename succesfully removed rename dir");
                } catch (IOException ex) {
                }
            }
            logger.debug("Shape Zip renamed: " + result);
            if (result) {
                infoPreview.setDataStoreName(newName);
            }
        }
        return result;
    }

    private static boolean renameTif(String userName, InfoPreview infoPreview,
            String tempUserDir) throws ResourceNotFoundFault {
        boolean result = false;
        String tempUserTifDir = PublishUtility.createDir(tempUserDir + PublishUtility.TIF_DIR_NAME);
        LayerPublishAction layerPublishAction = infoPreview.getLayerPublishAction();
        String newName = userName + "_" + infoPreview.getNewName();

        if (layerPublishAction != null && layerPublishAction.equals(LayerPublishAction.RENAME)
                && newName != null && !newName.equalsIgnoreCase(infoPreview.getDataStoreName())) {
            //Rinominare il file nuovo con il nuovo nome

        } else if (layerPublishAction != null && layerPublishAction.equals(LayerPublishAction.OVERRIDE)) {
            //Cancellare il vecchio file e rinominare il file nuovo con il vecchio nome
            logger.debug("renameTif in Override operation");
            String fileName = tempUserTifDir + infoPreview.getDataStoreName() + ".tif";
            File previousFile = new File(fileName);
            previousFile.delete();
            newName = infoPreview.getDataStoreName();
            logger.debug("renameTif after Override operation: " + newName);
        }
        String origName = infoPreview.getFileName();
        String fileName = tempUserTifDir + origName + ".tif";
        File previousFile = new File(fileName);
        previousFile.renameTo(new File(tempUserTifDir + newName + ".tif"));
        //
        String SLDFileName = origName + ".sld";
        File fileSLD = new File(tempUserTifDir, SLDFileName);
        if (fileSLD.exists()) {
            File filePublished = PublishUtility.copyFile(fileSLD,
                    tempUserTifDir, newName + ".sld", true);
            fileSLD.delete();
        }
        //
        String TFWFileName = origName + ".tfw";
        File fileTFW = new File(tempUserTifDir, TFWFileName);
        if (fileTFW.exists()) {
            PublishUtility.copyFile(fileTFW,
                    tempUserTifDir, newName + ".tfw", true);
            fileTFW.delete();
        }
        String PRJFileName = origName + ".prj";
        File filePRJ = new File(tempUserTifDir, PRJFileName);
        if (filePRJ.exists()) {
            PublishUtility.copyFile(filePRJ,
                    tempUserTifDir, newName + ".prj", true);
            filePRJ.delete();
        }
        infoPreview.setDataStoreName(newName);
        result = Boolean.TRUE;
        logger.debug("Tif renamed: " + result);
        return result;
    }

    public static boolean manageRename(String userName, InfoPreview infoPreview,
            String tempUserDir) throws ResourceNotFoundFault {
        boolean result;
        if (infoPreview.isIsShape()) {
            result = renameZipShp(userName, infoPreview, tempUserDir);
        } else {
            result = renameTif(userName, infoPreview, tempUserDir);
        }
        return result;
    }

//    public static void copyTifFiles(String origName, String tempUserTifDir,
//            String userName, LayerInfo info) {
//        String SLDFileName = origName + ".sld";
//        File fileSLD = new File(tempUserTifDir, SLDFileName);
//        if (fileSLD.exists()) {
//            File filePublished = PublishUtility.copyFile(fileSLD,
//                    tempUserTifDir, userName + "_" + info.name + ".sld", true);
//            fileSLD.delete();
//            info.sld = this.publishSLD(filePublished, info.name);
//        } else {
//            info.sld = "default_raster";
//        }
//        String TFWFileName = origName + ".tfw";
//        File fileTFW = new File(tempUserTifDir, TFWFileName);
//        if (fileTFW.exists()) {
//            PublishUtility.copyFile(fileTFW,
//                    tempUserTifDir, userName + "_" + info.name + ".tfw", true);
//            fileTFW.delete();
//        }
//        String PRJFileName = origName + ".prj";
//        File filePRJ = new File(tempUserTifDir, PRJFileName);
//        if (filePRJ.exists()) {
//            PublishUtility.copyFile(filePRJ,
//                    tempUserTifDir, userName + "_" + info.name + ".prj", true);
//            filePRJ.delete();
//        }
//    }
    public static File getFileNameToLowerCase(File file) {
        File fileToReturn = new File(FilenameUtils.getFullPath(file.getAbsolutePath())
                + file.getName().toLowerCase());
        try {
            FileUtils.moveFile(file, fileToReturn);
        } catch (IOException ex) {
            logger.error("Error renaming file: " + ex);
        }
        return fileToReturn;
    }

    /**
     * Method usefull to remove special characters from the passed string to
     * clean
     *
     * @param stringToClean
     * @return
     */
    public static String removeSpecialCharactersFromString(String stringToClean) {
        Pattern pt = Pattern.compile("[^a-zA-Z0-9_]");
        Matcher match = pt.matcher(stringToClean);
        while (match.find()) {
            String s = match.group();
            stringToClean = stringToClean.replaceAll("\\" + s, "");
        }
        return stringToClean;
    }

    public static String extractFileExtension(String fileName) {
        return fileName.substring(fileName.length() - 3);
    }

    public static String extractFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(
                    "."));
    }

    public static void extractEntryToFile(ZipEntry entry, ZipFile zipSrc,
            String tempUserDir) throws ResourceNotFoundFault {
        String entryName;
        FileOutputStream fileoutputstream = null;
        InputStream zipinputstream = null;
        try {
            zipinputstream = zipSrc.getInputStream(entry);
            int lastIndex = entry.getName().lastIndexOf('/');
            entryName = entry.getName().substring(lastIndex + 1).toLowerCase();
            String fileName = entryName.toLowerCase();
            String fileExtension = extractFileExtension(fileName);
            fileName = extractFileName(fileName);
            entryName = removeSpecialCharactersFromString(fileName) + "."
                    + fileExtension;
            logger.info("INFO: Found file " + entryName);
            fileoutputstream = new FileOutputStream(tempUserDir + entryName);
            byte[] buf = new byte[1024];
            int n;
            while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
                fileoutputstream.write(buf, 0, n);
            }
        } catch (IOException ioe) {
            logger.error("ERROR on extractEntryToFile(): " + ioe.getMessage());
            throw new ResourceNotFoundFault(ioe.getMessage());
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
            File shxFile = new File(tempUserDir + origName + ".shx");//sbn&&sbx&&CPG
            File prjFile = new File(tempUserDir + origName + ".prj");

            File shpDestFile = shpFile;
            File dbfDestFile = dbfFile;
            File shxDestFile = shxFile;
            File prjDestFile = prjFile;

            File sldFile = new File(tempUserDir + origName + ".sld");
            File sldDestFile = sldFile;
            File cpgFile = new File(tempUserDir + origName + ".cpg");
            File cpgDestFile = cpgFile;

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
                cpgDestFile = new File(tempUserDir + destName + ".cpg");
                cpgFile.renameTo(cpgDestFile);
            }
            out = PublishUtility.compress(out, shpDestFile);
            out = PublishUtility.compress(out, dbfDestFile);
            out = PublishUtility.compress(out, shxDestFile);
            out = PublishUtility.compress(out, prjDestFile);
            if (sldDestFile.exists()) {
                out = PublishUtility.compress(out, sldDestFile);
            }
            if (cpgDestFile.exists()) {
                out = PublishUtility.compress(out, cpgDestFile);
            }
            out.close();
        } catch (Exception ex) {
            logger.error("Exception compressing: " + zipFileName + " - " + ex);
            return null;
        }
        return out;
    }
}
