package org.geosdi.geoplatform.support.jackson.reader;

import com.google.common.base.Preconditions;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJacksonReaderSupport<T extends Object> {

    /**
     * @param url
     * @return {@link T}
     * @throws Exception
     */
    T read(URL url) throws Exception;

    /**
     * @param file
     * @return {@link T}
     * @throws Exception
     */
    T read(File file) throws Exception;

    /**
     * @param inputStream
     * @return {@link T}
     * @throws Exception
     */
    T read(InputStream inputStream) throws Exception;

    /**
     * @param reader
     * @return {@link T+}
     * @throws Exception
     */
    T read(Reader reader) throws Exception;

    /**
     * @param entityAsString
     * @return {@link T}
     * @throws Exception
     */
    T read(String entityAsString) throws Exception;

    /**
     * @param direrctory
     * @return {@link Collection<T>}
     * @throws Exception
     */
    Collection<T> readFromDirectory(Path direrctory) throws Exception;

    /**
     * @return {@link Class<T>}
     */
    Class<T> getEntityClass();

    /**
     * @param thePath
     * @return {@link T}
     */
    default T read(Path thePath) {
        Preconditions.checkArgument((thePath != null) && (thePath.toFile().exists()),
                "The Parameter Path must not be null and the Associated File must exist.");
        try {
            return this.read(thePath.toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
