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
package org.geosdi.geoplatform.support.jackson.reader.toon;

import dev.toonformat.jtoon.DecodeOptions;
import dev.toonformat.jtoon.JToon;
import org.apache.commons.io.IOUtils;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.jackson.reader.GPBaseJacksonReaderSupport;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;
import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonToonReaderSupport<T extends Object> extends GPBaseJacksonReaderSupport<T, JsonMapper> implements GPJacksonToonReaderSupport<T> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param theJacksonSupport
     * @param theEntityClass
     */
    public GPBaseJacksonToonReaderSupport(@Nonnull(when = NEVER) JacksonSupport<JsonMapper> theJacksonSupport, @NonNull Class<T> theEntityClass) {
        super(theJacksonSupport, theEntityClass);
    }

    /**
     * @param entityAsToonString
     * @param theDecodeOptions
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) String entityAsToonString, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument((entityAsToonString != null) && !(entityAsToonString.trim().isEmpty()), "The Parameter EntityAsToonString must not be null or Empty.");
        checkArgument(theDecodeOptions != null, "The Parameter theDecodeOptions must not be null.");
        String entityAsJsonString = JToon.decodeToJson(entityAsToonString, theDecodeOptions);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@ENTITY_AS_JSON_STRING : \n{}\n", entityAsJsonString);
        return this.read(entityAsJsonString);
    }

    /**
     * @param entityAsToonString
     * @param classe
     * @param theDecodeOptions
     * @return {@link V}
     * @throws Exception
     */
    @Override
    public <V> V read(@Nonnull(when = NEVER) String entityAsToonString, @NonNull Class<V> classe, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument((entityAsToonString != null) && !(entityAsToonString.trim().isEmpty()), "The Parameter EntityAsToonString must not be null or Empty.");
        checkArgument(classe != null, "The Parameter classe must not be null.");
        checkArgument(theDecodeOptions != null, "The Parameter theDecodeOptions must not be null.");
        String entityAsJsonString = JToon.decodeToJson(entityAsToonString, theDecodeOptions);
        logger.trace("@@@@@@@@@@@@@@@@@@@@@ENTITY_AS_JSON_STRING : \n{}\n", entityAsJsonString);
        return this.read(entityAsJsonString, classe);
    }

    /**
     * @param entityAsToonString
     * @param theDecodeOptions
     * @return {@link Object}
     * @throws Exception
     */
    @Override
    public Object readAsToon(@Nonnull(when = NEVER) String entityAsToonString, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument((entityAsToonString != null) && !(entityAsToonString.trim().isEmpty()), "The Parameter EntityAsToonString must not be null or Empty.");
        checkArgument(theDecodeOptions != null, "The Parameter theDecodeOptions must not be null.");
        return JToon.decode(entityAsToonString, theDecodeOptions);
    }

    /**
     * @param fileAsToon
     * @param theDecodeOptions
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) File fileAsToon, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument(fileAsToon != null, "The Parameter File must not be null.");
        checkArgument(theDecodeOptions != null, "The Parameter DecodeOptions must not be null.");
        return this.read(IOUtils.toString(new FileInputStream(fileAsToon), UTF_8), theDecodeOptions);
    }

    /**
     * @param inputStreamAsToon
     * @param theDecodeOptions
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) InputStream inputStreamAsToon, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument(inputStreamAsToon != null, "The Parameter InputStream must not be null.");
        checkArgument(theDecodeOptions != null, "The Parameter DecodeOptions must not be null.");
        return this.read(IOUtils.toString(inputStreamAsToon, UTF_8), theDecodeOptions);
    }

    /**
     * @param readerAsToon
     * @param theDecodeOptions
     * @return {@link T}
     * @throws Exception
     */
    @Override
    public T read(@Nonnull(when = NEVER) Reader readerAsToon, @Nonnull(when = NEVER) DecodeOptions theDecodeOptions) throws Exception {
        checkArgument(readerAsToon != null, "The Parameter Reader must not be null.");
        checkArgument(theDecodeOptions != null, "The Parameter DecodeOptions must not be null.");
        return this.read(IOUtils.toString(readerAsToon), theDecodeOptions);
    }
}