package org.geosdi.geoplatform.support.jackson.reader;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonReaderSupport<T extends Object> implements GPJacksonReaderSupport<T> {

    protected final JacksonSupport jacksonSupport;
    protected final Class<T> entityClass;

    /**
     * @param theJacksonSupport
     * @param theEntityClass
     */
    public GPBaseJacksonReaderSupport(JacksonSupport theJacksonSupport, Class<T> theEntityClass) {
        Preconditions.checkArgument(theJacksonSupport != null,
                "The Parameter JacksonSupport must not be null.");
        Preconditions.checkArgument(theEntityClass != null,
                "The Parameter EntityClass must not be null.");
        this.jacksonSupport = theJacksonSupport;
        this.entityClass = theEntityClass;
    }

    /**
     * @param url
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(URL url) throws Exception {
        Preconditions.checkArgument(url != null,
                "The Parameter URL must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(url, entityClass);
    }

    /**
     * @param file
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(File file) throws Exception {
        Preconditions.checkArgument(file != null, "The Parameter File must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(file, this.entityClass);
    }

    /**
     * @param inputStream
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(InputStream inputStream) throws Exception {
        Preconditions.checkArgument(inputStream != null,
                "The Parameter InputStream must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(inputStream, this.entityClass);
    }

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(Reader reader) throws Exception {
        Preconditions.checkArgument(reader != null, "The Parameter Reader must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(reader, this.entityClass);
    }

    /**
     * @param entityAsString
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(String entityAsString) throws Exception {
        Preconditions.checkArgument((entityAsString != null) && !(entityAsString.isEmpty()),
                "The Parameter EntityAsString must not be null or Empty.");
        return this.read(new StringReader(entityAsString));
    }

    /**
     * @param path
     * @return {@link Collection<T>}
     * @throws Exception
     */
    @Override
    public Collection<T> readFromDirectory(Path path) throws Exception {
        Preconditions.checkArgument((path != null && path.toFile().isDirectory()),
                "The Parameter Path must not be null and must be a Directory");
        return Files.list(path)
                .filter(p -> p.toFile().getName().endsWith(".json"))
                .map(p -> this.read(p))
                .filter(d -> d != null)
                .collect(Collectors.toList());
    }
}
