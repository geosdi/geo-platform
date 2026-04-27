/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.mapper.toon;

import dev.toonformat.jtoon.EncodeOptions;
import org.geosdi.geoplatform.support.jackson.function.GPJacksonCheck;
import org.geosdi.geoplatform.support.jackson.reader.toon.GPJacksonToonReaderSupport;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.Writer;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPJacksonToonMapper<T extends Object> extends GPJacksonToonReaderSupport<T> {

    /**
     * @param entity
     * @return
     * @throws Exception
     */
    String writeAsToonString(@Nonnull(when = NEVER) T entity) throws Exception;

    /**
     * @param entity
     * @param theEncodeOptions
     * @return
     * @throws Exception
     */
    String writeAsToonString(@Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    String writeAsToonString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception;

    /**
     * @param theCheck
     * @param theEncodeOptions
     * @return {@link String}
     * @throws Exception
     */
    String writeAsToonString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param theFile
     * @param theCheck
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception;

    /**
     * @param theFile
     * @param theCheck
     * @param theEncodeOptions
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param theValue
     * @param <V>
     * @return {@link String}
     * @throws Exception
     */
    <V extends Object> String writeValueAsToon(@Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param theValue
     * @param theEncodeOptions
     * @param <V>
     * @return {@link String}
     * @throws Exception
     */
    <V extends Object> String writeValueAsToon(@Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity) throws Exception;

    /**
     * @param file
     * @param entity
     * @param theEncodeOptions
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param writer
     * @param entity
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity) throws Exception;

    /**
     * @param writer
     * @param entity
     * @param theEncodeOptions
     * @throws Exception
     */
    void writeAsToon(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param file
     * @param theValue
     * @throws Exception
     */
    <V extends Object> void writeAsToonValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param file
     * @param theValue
     * @param theEncodeOptions
     * @throws Exception
     */
    <V extends Object> void writeAsToonValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param writer
     * @param theValue
     * @throws Exception
     */
    <V extends Object> void writeAsToonValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param writer
     * @param theValue
     * @param theEncodeOptions
     * @throws Exception
     */
    <V extends Object> void writeAsToonValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception;

    /**
     * @param entity
     * @return
     * @throws Exception
     */
    String writeAsString(@Nonnull(when = NEVER) T entity) throws Exception;

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    String writeAsString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception;

    /**
     * @param theFile
     * @param theCheck
     * @throws Exception
     */
    void write(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception;

    /**
     * @param theValue
     * @param <V>
     * @return {@link String}
     * @throws Exception
     */
    <V extends Object> String writeValue(@Nonnull(when = NEVER) V theValue) throws Exception;

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    void write(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity) throws Exception;

    /**
     * @param writer
     * @param entity
     * @throws Exception
     */
    void write(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity) throws Exception;

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
}