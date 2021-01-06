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
package org.geosdi.geoplatform.support.jackson.reader;

import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.nio.file.Files.list;
import static java.util.stream.Collectors.toList;
import static javax.annotation.meta.When.NEVER;

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
    public GPBaseJacksonReaderSupport(@Nonnull(when = NEVER) JacksonSupport theJacksonSupport, @Nonnull(when = NEVER) Class<T> theEntityClass) {
        checkArgument(theJacksonSupport != null, "The Parameter JacksonSupport must not be null.");
        checkArgument(theEntityClass != null, "The Parameter EntityClass must not be null.");
        this.jacksonSupport = theJacksonSupport;
        this.entityClass = theEntityClass;
    }

    /**
     * @param url
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) URL url) throws Exception {
        checkArgument(url != null, "The Parameter URL must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(url, entityClass);
    }

    /**
     * @param file
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) File file) throws Exception {
        checkArgument(file != null, "The Parameter File must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(file, this.entityClass);
    }

    /**
     * @param inputStream
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) InputStream inputStream) throws Exception {
        checkArgument(inputStream != null, "The Parameter InputStream must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(inputStream, this.entityClass);
    }

    /**
     * @param reader
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) Reader reader) throws Exception {
        checkArgument(reader != null, "The Parameter Reader must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(reader, this.entityClass);
    }

    /**
     * @param entityAsString
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) String entityAsString) throws Exception {
        checkArgument((entityAsString != null) && !(entityAsString.trim().isEmpty()), "The Parameter EntityAsString must not be null or Empty.");
        return this.read(new StringReader(entityAsString));
    }

    /**
     * @param entityAsString
     * @param classe
     * @return {@link Class<V>}
     * @throws Exception
     */
    @Override
    public <V extends Object> V read(@Nonnull(when = NEVER) String entityAsString, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument((entityAsString != null) && !(entityAsString.trim().isEmpty()), "The Parameter EntityAsString must not be null or Empty.");
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return this.read(new StringReader(entityAsString), classe);
    }

    /**
     * @param reader
     * @param classe
     * @return
     * @throws Exception
     */
    @Override
    public <V extends Object> V read(@Nonnull(when = NEVER) Reader reader, @Nonnull(when = NEVER) Class<V> classe) throws Exception {
        checkArgument(reader != null, "The Parameter Reader must not be null.");
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return this.jacksonSupport.getDefaultMapper().readValue(reader, classe);
    }

    /**
     * @param path
     * @return {@link Collection<T>}
     * @throws Exception
     */
    @Override
    public Collection<T> readFromDirectory(@Nonnull(when = NEVER) Path path) throws Exception {
        checkArgument((path != null && path.toFile().isDirectory()), "The Parameter Path must not be null and must be a Directory");
        return list(path)
                .filter(p -> !(p.toFile().isDirectory()) && (p.toFile().getName().endsWith(".json")))
                .map(this::read)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    /**
     * @return {@link Class<T>}
     */
    @Override
    public Class<T> getEntityClass() {
        return this.entityClass;
    }
}