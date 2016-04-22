package org.geosdi.geoplatform.support.jackson.mapper;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;

import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJacksonMapper<T extends Object> {

    JacksonSupport DEFAULT_MAPPER = new GPJacksonSupport().configure(NON_NULL);

    /**
     * @param url representing {@link URL} as String
     * @return T
     * @throws Exception
     */
    T read(URL url) throws Exception;

    /**
     * @param file
     * @return T
     * @throws Exception
     */
    T read(File file) throws Exception;

    /**
     * @param in
     * @return T
     * @throws Exception
     */
    T read(InputStream in) throws Exception;

    /**
     * @param r
     * @return T
     * @throws Exception
     */
    T read(Reader r) throws Exception;

    /**
     * @param s
     * @return T
     * @throws Exception
     */
    T read(String s) throws Exception;

    /**
     * @param entity
     * @return
     * @throws Exception
     */
    String writeAsString(T entity) throws Exception;

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    void write(File file, T entity) throws Exception;

    /**
     * @param direrctory
     * @return {@link Collection <T>}
     * @throws Exception
     */
    Collection<T> readFromDirectory(Path direrctory) throws Exception;

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
