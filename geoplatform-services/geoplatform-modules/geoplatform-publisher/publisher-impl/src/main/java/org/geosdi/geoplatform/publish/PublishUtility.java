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
package org.geosdi.geoplatform.publish;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PublishUtility {

    public final static String GEOPORTAL = "geoportal";
    public static String TMPDIR;

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

    /************************
     *
     * @param out the archive stream where compressing the inFile
     * @param inFile the file to compress
     * @return
     * @throws IOException this exception is raised when inFile does not exist
     * this method at the first opens and compresses  the fileinFile, then it inserts it in the out stream
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
}
