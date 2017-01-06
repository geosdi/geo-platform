/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
