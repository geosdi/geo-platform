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
package org.geosdi.geoplatform.experimental.el.api.mapper;

import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.support.jackson.function.GPJacksonCheck;
import org.geosdi.geoplatform.support.jackson.mapper.GPJacksonMapper;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @param <D>
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchMapper<D extends Document> extends GPJacksonMapper<D> {

    /**
     * @param url representing {@link URL} as String
     * @return D
     * @throws Exception
     */
    D read(@Nonnull(when = NEVER) URL url) throws Exception;

    /**
     * @param file
     * @return D
     * @throws Exception
     */
    D read(@Nonnull(when = NEVER) File file) throws Exception;

    /**
     * @param in
     * @return D
     * @throws Exception
     */
    D read(@Nonnull(when = NEVER) InputStream in) throws Exception;

    /**
     * @param r
     * @return D
     * @throws Exception
     */
    D read(@Nonnull(when = NEVER) Reader r) throws Exception;

    /**
     * @param s
     * @return D
     * @throws Exception
     */
    D read(@Nonnull(when = NEVER) String s) throws Exception;

    /**
     * @param entityAsString
     * @param classe
     * @param <V>
     * @return {@link V}
     * @throws Exception
     */
    <V extends Object> V read(@Nonnull(when = NEVER) String entityAsString, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param reader
     * @param classe
     * @param <V>
     * @return
     * @throws Exception
     */
    <V extends Object> V read(@Nonnull(when = NEVER) Reader reader, @Nonnull(when = NEVER) Class<V> classe) throws Exception;

    /**
     * @param document
     * @return
     * @throws Exception
     */
    String writeAsString(@Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    String writeAsString(@Nonnull(when = NEVER) GPJacksonCheck<D> theCheck) throws Exception;

    /**
     * @param theValue
     * @param <V>
     * @return {@link String}
     * @throws Exception
     */
    <V extends Object> String writeValue(@Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param file
     * @param theValue
     * @throws Exception
     */
    <V extends Object> void writeValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param writer
     * @param theValue
     * @throws Exception
     */
    <V extends Object> void writeValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @return {@link String} Name of Document Class
     */
    String getDocumentClassName();

    /**
     * @param file
     * @param document
     * @throws Exception
     */
    void write(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) D document) throws Exception;

    /**
     * @param direrctory
     * @return {@link Collection<D>}
     * @throws Exception
     */
    Collection<D> readFromDirectory(Path direrctory) throws Exception;

    /**
     * @param thePath
     * @return {@link D}
     */
    default D read(@Nonnull(when = NEVER) Path thePath) {
        checkArgument((thePath != null) && (thePath.toFile().exists()), "The Parameter Path must not be null and the Associated File must exist.");
        try {
            return this.read(thePath.toFile());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    /**
     * @return {@link String} Mapper Name
     */
    String getMapperName();

    /**
     * @return {@link String}
     */
    String getJsonRootName();
}