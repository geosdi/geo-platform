package org.geosdi.geoplatform.support.jackson.mapper;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseJacksonMapper<T extends Object> implements GPJacksonMapper<T> {

    protected final Class<T> entityClass;
    protected final JacksonSupport reader;

    public GPBaseJacksonMapper(Class<T> theEntityClass, JacksonSupport theReader) {
        Preconditions.checkNotNull(theEntityClass,
                "The Entity Class must not be null");
        this.entityClass = theEntityClass;
        this.reader = ((theReader != null) ? theReader : DEFAULT_MAPPER);
    }

    @Override
    public T read(URL url) throws Exception {
        return reader.getDefaultMapper().readValue(url, entityClass);
    }

    @Override
    public T read(File file) throws Exception {
        return this.reader.getDefaultMapper().readValue(file, entityClass);
    }

    @Override
    public T read(InputStream in) throws Exception {
        return reader.getDefaultMapper().readValue(in, entityClass);
    }

    @Override
    public T read(Reader r) throws Exception {
        return this.reader.getDefaultMapper().readValue(r, entityClass);
    }

    @Override
    public T read(String s) throws Exception {
        return this.reader.getDefaultMapper().readValue(s, entityClass);
    }

    @Override
    public String writeAsString(T entity) throws Exception {
        return this.reader.getDefaultMapper().writeValueAsString(entity);
    }

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    @Override
    public void write(File file, T entity) throws Exception {
        this.reader.getDefaultMapper().writeValue(file, entity);
    }

    /**
     * @param direrctory
     * @return {@link Collection <T>}
     * @throws Exception
     */
    @Override
    public Collection<T> readFromDirectory(Path direrctory) throws Exception {
        return Files.list(direrctory)
                .filter(path -> path.toFile().getName().endsWith(".json"))
                .map(path -> this.read(path))
                .filter(d -> d != null)
                .collect(Collectors.toList());
    }
}
