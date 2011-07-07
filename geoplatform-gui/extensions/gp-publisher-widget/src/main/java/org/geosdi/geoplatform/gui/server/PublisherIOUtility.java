/**
 * 
 */
package org.geosdi.geoplatform.gui.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PublisherIOUtility {

    /**
     * 
     * @param tempFile
     * @return
     * @throws IOException
     */
    public static File decompress(final String prefix, final File inputFile,
            final File tempFile) throws IOException {
        final File tmpDestDir = createTodayPrefixedDirectory(prefix, new File(
                tempFile.getParent()));

        ZipFile zipFile = new ZipFile(inputFile);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);

            if (entry.isDirectory()) {
                // Assume directories are stored parents first then
                // children.
                (new File(tmpDestDir, entry.getName())).mkdir();
                continue;
            }

            File newFile = new File(tmpDestDir, entry.getName());
            FileOutputStream fos = new FileOutputStream(newFile);
            try {
                byte[] buf = new byte[1024];
                int len;

                while ((len = stream.read(buf)) >= 0) {
                    saveCompressedStream(buf, fos, len);
                }

            } catch (IOException e) {
                zipFile.close();
                IOException ioe = new IOException(
                        "Not valid ZIP archive file type.");
                ioe.initCause(e);
                throw ioe;
            } finally {
                fos.flush();
                fos.close();

                stream.close();
            }
        }
        zipFile.close();

        File[] files = tmpDestDir.listFiles(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return FilenameUtils.getExtension(name).equalsIgnoreCase("shp");
            }
        });

        return files.length > 0 ? files[0] : null;
    }

    /**
     * @param len
     * @param stream
     * @param fos
     * @return
     * @throws IOException
     */
    public static void saveCompressedStream(final byte[] buffer,
            final OutputStream out, final int len) throws IOException {
        try {
            out.write(buffer, 0, len);

        } catch (Exception e) {
            out.flush();
            out.close();
            IOException ioe = new IOException("Not valid archive file type.");
            ioe.initCause(e);
            throw ioe;
        }
    }

    /**
     * Create a subDirectory having the actual date as name, within a specified
     * destination directory.
     * 
     * @param destDir
     *            the destination directory where to build the "today"
     *            directory.
     * @param inputFileName
     * @return the created directory.
     */
    public final static File createTodayDirectory(File destDir,
            String inputFileName) {
        return createTodayDirectory(destDir, inputFileName, false);
    }

    /**
     * Create a subDirectory having the actual date as name, within a specified
     * destination directory.
     * 
     * @param destDir
     *            the destination directory where to build the "today"
     *            directory.
     * @param inputFileName
     * @return the created directory.
     */
    public final static File createTodayDirectory(File destDir,
            String inputFileName, final boolean withTime) {
        final SimpleDateFormat SDF = withTime ? new SimpleDateFormat(
                "yyyy_MM_dd_hhmmsss") : new SimpleDateFormat("yyyy_MM_dd");
        final String newPath = (new StringBuffer(destDir.getAbsolutePath().trim()).append(File.separatorChar).append(
                SDF.format(new Date())).append("_").append(inputFileName)).toString();
        File dir = new File(newPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    /**
     * Create a subDirectory having the actual date as name, within a specified
     * destination directory.
     * 
     * @param prefix
     * @param parent
     *            the destination directory where to build the "today"
     *            directory.
     * @return the created directory.
     */
    public static File createTodayPrefixedDirectory(final String prefix,
            final File parent) {
        final SimpleDateFormat SDF_HMS = new SimpleDateFormat(
                "yyyy_MM_dd_hhmmsss");
        final String newPath = (new StringBuffer(parent.getAbsolutePath().trim()).append(File.separatorChar).append(prefix).append(
                File.separatorChar).append(SDF_HMS.format(new Date()))).toString();
        File dir = new File(newPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
}
