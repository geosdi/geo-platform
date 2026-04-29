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
import dev.toonformat.jtoon.JToon;
import org.apache.commons.io.IOUtils;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.function.GPJacksonCheck;
import org.geosdi.geoplatform.support.jackson.reader.toon.GPBaseJacksonToonReaderSupport;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;
import static dev.toonformat.jtoon.EncodeOptions.DEFAULT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.support.jackson.mapper.GPJacksonMapper.DEFAULT_MAPPER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonToonMapper<T extends Object> extends GPBaseJacksonToonReaderSupport<T> implements GPJacksonToonMapper<T> {

    /**
     * @param theEntityClass
     * @param theJacksonSupport
     */
    public GPBaseJacksonToonMapper(@Nonnull(when = NEVER) Class<T> theEntityClass, @Nullable JacksonSupport<JsonMapper> theJacksonSupport) {
        super(((theJacksonSupport != null) ? theJacksonSupport : DEFAULT_MAPPER), theEntityClass);
    }

    /**
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public String writeAsToonString(@Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(entity != null, "The Parameter Entity must not be null.");
        return JToon.encode(entity);
    }

    /**
     * @param entity
     * @param theEncodeOptions
     * @return
     * @throws Exception
     */
    @Override
    public String writeAsToonString(@Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(entity != null, "The Parameter Entity must not be null.");
        return JToon.encode(entity, (theEncodeOptions != null) ? theEncodeOptions : DEFAULT);
    }

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeAsToonString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.writeAsToonString(theCheck.apply());
    }

    /**
     * @param theCheck
     * @param theEncodeOptions
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeAsToonString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.writeAsToonString(theCheck.apply(), theEncodeOptions);
    }

    /**
     * @param theFile
     * @param theCheck
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        this.writeAsToon(theFile, theCheck.apply());
    }

    /**
     * @param theFile
     * @param theCheck
     * @param theEncodeOptions
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        this.writeAsToon(theFile, theCheck.apply(), theEncodeOptions);
    }

    /**
     * @param theValue
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public <V> String writeValueAsToon(@Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        return JToon.encode(theValue);
    }

    /**
     * @param theValue
     * @param theEncodeOptions
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public <V> String writeValueAsToon(@Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        return JToon.encode(theValue, (theEncodeOptions != null) ? theEncodeOptions : DEFAULT);
    }

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(file != null, "The Parameter File must not be null.");
        IOUtils.write(this.writeAsToonString(entity), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8)));
    }

    /**
     * @param file
     * @param entity
     * @param theEncodeOptions
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(file != null, "The Parameter File must not be null.");
        IOUtils.write(this.writeAsToonString(entity, theEncodeOptions), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8)));
    }

    /**
     * @param writer
     * @param entity
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(writer != null, "The Parameter writer must not be null.");
        IOUtils.write(writeAsToonString(entity), writer);
    }

    /**
     * @param writer
     * @param entity
     * @param theEncodeOptions
     * @throws Exception
     */
    @Override
    public void writeAsToon(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(writer != null, "The Parameter writer must not be null.");
        IOUtils.write(writeAsToonString(entity, theEncodeOptions), writer);
    }

    /**
     * @param file
     * @param theValue
     * @throws Exception
     */
    @Override
    public <V> void writeAsToonValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(file != null, "The Parameter File must not be null.");
        checkArgument(theValue != null, "The Parameter value must not be null.");
        IOUtils.write(this.writeValueAsToon(entityClass), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8)));
    }

    /**
     * @param file
     * @param theValue
     * @param theEncodeOptions
     * @throws Exception
     */
    @Override
    public <V> void writeAsToonValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(file != null, "The Parameter File must not be null.");
        checkArgument(theValue != null, "The Parameter value must not be null.");
        IOUtils.write(this.writeValueAsToon(entityClass, theEncodeOptions), new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8)));
    }

    /**
     * @param writer
     * @param theValue
     * @throws Exception
     */
    @Override
    public <V> void writeAsToonValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(writer != null, "The Parameter writer must not be null.");
        IOUtils.write(this.writeValueAsToon(theValue), writer);
    }

    /**
     * @param writer
     * @param theValue
     * @param theEncodeOptions
     * @throws Exception
     */
    @Override
    public <V> void writeAsToonValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue, @Nullable EncodeOptions theEncodeOptions) throws Exception {
        checkArgument(writer != null, "The Parameter writer must not be null.");
        IOUtils.write(this.writeValueAsToon(theValue, theEncodeOptions), writer);
    }

    /**
     * @param entity
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeAsString(@Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(entity != null, "The Parameter Entity must not be null.");
        return this.jacksonSupport.getDefaultMapper().writeValueAsString(entity);
    }

    /**
     * @param theCheck
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String writeAsString(@Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        return this.writeAsString(theCheck.apply());
    }

    /**
     * @param theFile
     * @param theCheck
     * @throws Exception
     */
    @Override
    public void write(@Nonnull(when = NEVER) File theFile, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception {
        checkArgument(theFile != null, "The Parameter file must not be null.");
        checkArgument(theCheck != null, "The Parameter theCheck must not be null.");
        this.write(theFile, theCheck.apply());
    }

    /**
     * @param file
     * @param entity
     * @throws Exception
     */
    @Override
    public void write(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(entity != null, "The Parameter Entity must not be null.");
        checkArgument(file != null, "The Parameter File must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(file, entity);
    }

    /**
     * @param writer
     * @param entity
     * @throws Exception
     */
    @Override
    public void write(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) T entity) throws Exception {
        checkArgument(entity != null, "The Parameter Entity must not be null.");
        checkArgument(writer != null, "The Parameter Writer must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(writer, entity);
    }

    /**
     * @param theValue
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public <V> String writeValue(@Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        return this.jacksonSupport.getDefaultMapper().writeValueAsString(theValue);
    }

    /**
     * @param file
     * @param theValue
     * @throws Exception
     */
    @Override
    public <V> void writeValue(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(file != null, "The Parameter File must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(file, theValue);
    }

    /**
     * @param writer
     * @param theValue
     * @throws Exception
     */
    @Override
    public <V> void writeValue(@Nonnull(when = NEVER) Writer writer, @Nonnull(when = NEVER) V theValue) throws Exception {
        checkArgument(theValue != null, "The Parameter value must not be null.");
        checkArgument(writer != null, "The Parameter Writer must not be null.");
        this.jacksonSupport.getDefaultMapper().writeValue(writer, theValue);
    }
}