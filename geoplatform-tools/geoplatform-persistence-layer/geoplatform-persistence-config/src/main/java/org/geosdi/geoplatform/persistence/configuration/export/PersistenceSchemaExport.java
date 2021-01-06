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
package org.geosdi.geoplatform.persistence.configuration.export;

import org.hibernate.boot.Metadata;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.Target;
import org.hibernate.tool.schema.TargetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.io.File;
import java.util.EnumSet;

import static java.io.File.separator;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PersistenceSchemaExport implements InitializingBean {

    protected static final Logger logger = LoggerFactory.getLogger(PersistenceSchemaExport.class);
    //
    private static final String SCHEMA_EXPORT_DIR_NAME = "GPPersistenceLayer";
    //
    @Value(value = "#{systemProperties['user.home']}")
    private String userHome;
    @Value(value = "#{systemProperties['gpSchemaExport']}")
    protected String generateSchema;
    @Resource(name = "gpTargetType")
    private TargetType gpTargetType;
    protected SchemaExport schema;

    @Override
    public final void afterPropertiesSet() throws Exception {
        this.createSchema();
    }

    protected final void exportSchema(Metadata metadata) {
        switch (this.gpTargetType) {
            case SCRIPT:
                String schemaExportDirPath = this.userHome + separator + SCHEMA_EXPORT_DIR_NAME;
                File dirPath = new File(schemaExportDirPath);
                if (!dirPath.exists()) {
                    boolean success = dirPath.mkdirs();
                    if (!success) {
                        throw new SecurityException("It was not possible to create the schema.sql in the User Home Directory.");
                    }
                }
                String schemaExportFilePath = schemaExportDirPath + separator
                        + ((getSchemaFileName() != null) && !(getSchemaFileName().isEmpty()) ? getSchemaFileName() : "schema.sql");
                File schemaFile = new File(schemaExportFilePath);
                if (schemaFile.exists()) {
                    schemaFile.delete();
                }
                schema.setOutputFile(schemaExportFilePath);
                logger.info("@@@@@@@@@@@@@@@@@@@@@@GeoPlatform-PersistenceLayer: schema database generated at path {}\n",
                        schemaExportFilePath);
        }
        schema.setFormat(true);
        schema.setDelimiter(";");
        schema.create(EnumSet.of(this.gpTargetType), metadata);
    }

    protected abstract void createSchema();

    protected abstract String getSchemaFileName();
}
