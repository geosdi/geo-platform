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
package org.geosdi.geoplatform.persistence.configuration.hibernate.export;

import org.geosdi.geoplatform.persistence.configuration.export.PersistenceSchemaExport;
import org.geosdi.geoplatform.persistence.configuration.export.reflection.GPReflectionsSchemaExport;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "hibSchemaExport")
@Profile("hibernate")
public class GPHibernateSchemaExport extends PersistenceSchemaExport {

    private static final Logger logger = LoggerFactory.getLogger(GPHibernateSchemaExport.class);
    //
    @Autowired
    private GPReflectionsSchemaExport reflectionsSchemaExport;
    @Autowired
    private Properties hibernateProperties;

    @Override
    protected void createSchema() {
        checkArgument(this.reflectionsSchemaExport != null, "The Parameter reflectionsSchemaExport must not be null.");
        checkArgument(this.hibernateProperties != null, "The Parameter hibernateProperties must not be null.");
        if ((this.generateSchema != null) && !(this.generateSchema.trim().isEmpty()) && (this.generateSchema.equalsIgnoreCase("generate"))) {
            Set<Class<?>> annotatedClasses = reflectionsSchemaExport.getAnnotatedClasses();
            checkArgument(!annotatedClasses.isEmpty(), "There are no Classes Annotated with @Entity Annotations.");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(hibernateProperties)
                    .build();
            MetadataSources metadataSource = new MetadataSources(serviceRegistry);
            for (Class<?> classe : annotatedClasses) {
                metadataSource.addAnnotatedClass(classe);
            }
            MetadataImplementor metadata = (MetadataImplementor) metadataSource.buildMetadata();
            schema = new SchemaExport();
            super.exportSchema(metadata);
        }
    }

    @Override
    protected String getSchemaFileName() {
        return "schema-hibernate.sql";
    }
}