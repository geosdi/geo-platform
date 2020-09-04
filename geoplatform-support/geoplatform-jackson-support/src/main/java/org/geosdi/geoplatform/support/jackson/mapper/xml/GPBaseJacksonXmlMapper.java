/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.jackson.mapper.xml;

import org.geosdi.geoplatform.support.jackson.function.GPJacksonCheck;
import org.geosdi.geoplatform.support.jackson.reader.GPBaseJacksonReaderSupport;
import org.geosdi.geoplatform.support.jackson.xml.JacksonXmlSupport;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.Writer;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseJacksonXmlMapper<T extends Object> extends GPBaseJacksonReaderSupport<T> implements GPJacksonXmlMapper<T> {

    /**
     * @param theEntityClass
     * @param theJacksonXmlSupport
     */
    public GPBaseJacksonXmlMapper(@Nonnull(when = NEVER) Class<T> theEntityClass, JacksonXmlSupport theJacksonXmlSupport) {
        super(((theJacksonXmlSupport != null) ? theJacksonXmlSupport : DEFAULT_XML_MAPPER), theEntityClass);
    }

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
     * @param file
     * @param theCheck
     * @throws Exception
     */
    @Override
    public void write(@Nonnull(when = NEVER) File file, @Nonnull(when = NEVER) GPJacksonCheck<T> theCheck) throws Exception {
        checkArgument(theCheck != null, "The Parameter checkFunction must not be null.");
        this.write(file, theCheck.apply());
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