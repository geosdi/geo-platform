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
package org.geosdi.geoplatform.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.services.utility.Ds2dsConfiguration;
import org.geosdi.geoplatform.services.utility.FeatureConfiguration;
import org.geosdi.geoplatform.services.utility.FeatureConfigurationUtil;
import org.geosdi.geoplatform.services.utility.PublishUtility;
import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureStore;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.Hints;
import org.geotools.feature.AttributeTypeBuilder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQLCompiler;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.NonIncrementingPrimaryKeyColumn;
import org.geotools.jdbc.PrimaryKey;
import org.geotools.jdbc.PrimaryKeyColumn;
import org.geotools.jdbc.PrimaryKeyFinder;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.filter.Filter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component
public class ShapeAppender {

    private static final Logger logger = LoggerFactory.getLogger(ShapeAppender.class);
    //
    private List<PrimaryKeyColumn> pks;
    private Boolean isPkGenerated;
    private Filter filter;
    @Autowired
    protected Ds2dsConfiguration configuration;

    public static SpelExpressionParser expressionParser = new SpelExpressionParser();

    public static StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

    static {
        evaluationContext.addPropertyAccessor(new PropertyAccessor() {

            @Override
            public void write(EvaluationContext ctx, Object target, String name,
                    Object value) throws AccessException {

            }

            @Override
            public TypedValue read(EvaluationContext ctx, Object target, String name)
                    throws AccessException {
                if (target instanceof SimpleFeature) {
                    SimpleFeature feature = (SimpleFeature) target;
                    return new TypedValue(feature.getAttribute(name));
                }
                return null;
            }

            @Override
            public Class[] getSpecificTargetClasses() {
                return new Class[]{SimpleFeature.class};
            }

            @Override
            public boolean canWrite(EvaluationContext ctx, Object target, String name)
                    throws AccessException {
                return false;
            }

            @Override
            public boolean canRead(EvaluationContext ctx, Object target, String name)
                    throws AccessException {
                return target instanceof SimpleFeature;
            }
        });
    }

    public ShapeAppender() {
    }

    public ShapeAppender(Ds2dsConfiguration actionConfiguration) {
//        this.configuration = actionConfiguration;
//        this.configuration = actionConfiguration.clone();
        this.pks = null;
        this.isPkGenerated = false;
    }

    /**
     * Does the actual import on the given file event.
     *
     * @param tempUserDir
     * @param zipShpFile
     * @return ouput boolean
     * @throws ResourceNotFoundFault
     */
    public boolean importFile(String tempUserDir, File zipShpFile) throws ResourceNotFoundFault {
        String appendTemporaryDirPath = tempUserDir + "append" + System.getProperty("file.separator");
        PublishUtility.createDir(appendTemporaryDirPath);
        File shpFile = this.extractShpFile(appendTemporaryDirPath, zipShpFile);

        DataStore sourceDataStore = null;
        DataStore destDataStore = null;

        final Transaction transaction = new DefaultTransaction("create");
        try {
            // source
            sourceDataStore = createSourceDataStore(shpFile);
            Query query = buildSourceQuery(sourceDataStore);
            FeatureStore<SimpleFeatureType, SimpleFeature> featureReader = createSourceReader(sourceDataStore, transaction, query);

            // output
            destDataStore = createOutputDataStore();
            SimpleFeatureType schema = buildDestinationSchema(featureReader.getSchema());

            FeatureStore<SimpleFeatureType, SimpleFeature> featureWriter = createOutputWriter(destDataStore, schema, transaction);
            SimpleFeatureType destSchema = featureWriter.getSchema();

            // check for schema case differences from input to output
            Map<String, String> schemaDiffs = compareSchemas(destSchema, schema);
            SimpleFeatureBuilder builder = new SimpleFeatureBuilder(destSchema);

            purgeData(featureWriter);
            logger.debug("Reading data");
            int total = featureReader.getCount(query);
            FeatureIterator<SimpleFeature> iterator = createSourceIterator(query, featureReader);
            int count = 0;
            try {
                while (iterator.hasNext()) {
                    SimpleFeature feature = buildFeature(builder,
                            iterator.next(), schemaDiffs, sourceDataStore);
                    logger.info("Reading data: 4.1");
                    featureWriter.addFeatures(DataUtilities
                            .collection(feature));
                    logger.info("Reading data: 4.2");
                    count++;
                    if (count % 100 == 0) {
                        updateImportProgress(count, total, "Importing data");
                    }
                    logger.info("Reading data: 4.3");
                }
                logger.info("Reading data: 5");
                logger.info("Data imported");
            } finally {
                iterator.close();
            }
            logger.info("Data imported (" + count + " features)");
            transaction.commit();
//            return buildOutputEvent();
            return true;
        } catch (Exception ioe) {
            try {
                transaction.rollback();
            } catch (IOException e1) {
                final String message = "Transaction rollback unsuccessful: "
                        + e1.getLocalizedMessage();
                if (logger.isErrorEnabled()) {
                    logger.error(message);
                }
                throw new ResourceNotFoundFault(message);
            }
            String cause = ioe.getCause() == null ? null : ioe.getCause().getMessage();
            String msg = "MESSAGE: " + Arrays.toString(ioe.getStackTrace()) + " - CAUSE: " + cause;
            throw new ResourceNotFoundFault(msg);

        } finally {
            updateTask("Closing connections");
            closeResource(sourceDataStore);
            closeResource(destDataStore);
            closeResource(transaction);
            PublishUtility.deleteDir(appendTemporaryDirPath);
        }
    }

    /**
     * Creates an iterator on the source features
     *
     * @param query Query used to filter the source
     * @param featureReader store for the source
     * @return
     * @throws IOException
     */
    private FeatureIterator<SimpleFeature> createSourceIterator(Query query,
            FeatureStore<SimpleFeatureType, SimpleFeature> featureReader)
            throws IOException {
        FeatureCollection<SimpleFeatureType, SimpleFeature> features = featureReader
                .getFeatures(query);
        FeatureIterator<SimpleFeature> iterator = features.features();
        return iterator;
    }

    /**
     * Purge data on output feature, if requested.
     *
     * @param featureWriter
     * @throws IOException
     */
    public void purgeData(FeatureStore<SimpleFeatureType, SimpleFeature> featureWriter) throws Exception {
        if (configuration.isForcePurgeAllData()) {
            updateTask("Purging ALL existing data");
            featureWriter.removeFeatures(Filter.INCLUDE);
            updateTask("Data purged");
        } else if (configuration.isPurgeData()) {
            updateTask("Purging existing data");
            featureWriter.removeFeatures(buildFilter());
            updateTask("Data purged");
        }
    }

    /**
     * Builds a Feature instance to be written on output.
     *
     * @param builder
     * @param sourceFeature
     * @return
     */
    protected SimpleFeature buildFeature(SimpleFeatureBuilder builder, SimpleFeature sourceFeature, Map<String, String> mappings, DataStore srcDataStore) {
        for (AttributeDescriptor ad : builder.getFeatureType().getAttributeDescriptors()) {
            String attribute = ad.getLocalName();
            builder.set(attribute, getAttributeValue(sourceFeature, attribute, mappings));
        }
        SimpleFeature smf = null;
        if (srcDataStore != null && srcDataStore instanceof JDBCDataStore && isPkGenerated == false) {
            if (pks == null) {
                SimpleFeatureType schema = builder.getFeatureType();
                JDBCDataStore jdbcDS = ((JDBCDataStore) srcDataStore);
                Connection cx = null;
                PrimaryKeyFinder pkFinder = jdbcDS.getPrimaryKeyFinder();
                try {
                    cx = jdbcDS.getDataSource().getConnection();
                    PrimaryKey pk = pkFinder.getPrimaryKey(jdbcDS, jdbcDS.getDatabaseSchema(), schema.getTypeName(), cx);
                    List<PrimaryKeyColumn> pkc = Lists.<PrimaryKeyColumn>newArrayList();
                    pks = pk.getColumns();
                    for (PrimaryKeyColumn el : pks) {
                        if (el instanceof NonIncrementingPrimaryKeyColumn) {
                            isPkGenerated = false;
                        } else {
                            isPkGenerated = true;
                            break;
                        }
                    }

                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                    throw new IllegalStateException("Error Occurred while search for the PK");
                } finally {
                    if (cx != null) {
                        try {
                            cx.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage(), e);
                        }
                    }
                }
            }
            if (!isPkGenerated) {
                StringBuilder sb = new StringBuilder();
                boolean first = true;
                for (PrimaryKeyColumn el : pks) {
                    if (!first) {
                        sb.append(".");
                    }
                    first = false;
                    sb.append(getAttributeValue(sourceFeature, el.getName(), mappings));
                }
                String fid = sb.toString();
                smf = builder.buildFeature(fid);
                Map map = smf.getUserData();
                map.put(Hints.USE_PROVIDED_FID, true);
                return smf;
            }
        }
        return builder.buildFeature(null);
    }

    /**
     * Builds an attribute value to be written on output.
     *
     * @param sourceFeature source used to get values to write
     * @param attributeName name of the attribute in the output feature
     * @return
     */
    protected Object getAttributeValue(SimpleFeature sourceFeature, String attributeName, Map<String, String> mappings) {
        // gets mapping for renamed attributes
        if (configuration.getAttributeMappings().containsKey(attributeName)) {
            attributeName = configuration.getAttributeMappings().get(attributeName).toString();
        } else if (mappings.containsKey(attributeName)) {
            attributeName = mappings.get(attributeName);
        }

        if (isExpression(attributeName)) {
            String expression = attributeName.trim().substring(2, attributeName.length() - 1);
            org.springframework.expression.Expression spelExpression = expressionParser
                    .parseExpression(expression);

            return spelExpression.getValue(evaluationContext, sourceFeature);
        } else {
            return sourceFeature.getAttribute(attributeName);
        }
    }

    /**
     * @param attributeName
     * @return
     */
    protected boolean isExpression(String attributeName) {
        return attributeName.trim().startsWith("#{") && attributeName.trim().endsWith("}");
    }

    /**
     * Compare input and output schemas for different case mapping in attribute
     * names.
     *
     * @param destSchema
     * @param schema
     * @return
     */
    protected Map<String, String> compareSchemas(SimpleFeatureType destSchema, SimpleFeatureType schema) {
        Map<String, String> diffs = Maps.<String, String>newHashMap();
        for (AttributeDescriptor ad : destSchema.getAttributeDescriptors()) {
            String attribute = ad.getLocalName();
            if (schema.getDescriptor(attribute) == null) {
                for (String variant : getNameVariants(attribute)) {
                    if (schema.getDescriptor(variant) != null) {
                        diffs.put(attribute, variant);
                        break;
                    }
                }
            }
        }
        return diffs;
    }

    /**
     * Returns case variants for the given name.
     *
     * @param name
     * @return
     */
    protected String[] getNameVariants(String name) {
        return new String[]{name.toLowerCase(), name.toUpperCase()};
    }

    /**
     * Builds a FeatureStore for the output Feature.
     *
     * @param store
     * @param schema
     * @return
     * @throws IOException
     */
    protected FeatureStore<SimpleFeatureType, SimpleFeature> createOutputWriter(DataStore store, SimpleFeatureType schema, Transaction transaction) throws IOException {
        String destTypeName = schema.getTypeName();
        boolean createSchema = true;
        for (String typeName : store.getTypeNames()) {
            if (typeName.equalsIgnoreCase(destTypeName)) {
                createSchema = false;
                destTypeName = typeName;
            }
        }
        // check for case changing in typeName
        if (createSchema) {
            store.createSchema(schema);
            for (String typeName : store.getTypeNames()) {
                if (!typeName.equals(destTypeName) && typeName.equalsIgnoreCase(destTypeName)) {
                    destTypeName = typeName;
                }
            }
        }
        FeatureStore<SimpleFeatureType, SimpleFeature> result = (FeatureStore<SimpleFeatureType, SimpleFeature>) store.getFeatureSource(destTypeName);
        result.setTransaction(transaction);
        return result;
    }

    private SimpleFeatureType buildDestinationSchema(
            SimpleFeatureType sourceSchema) {
        String typeName = configuration.getOutputFeature().getTypeName();
        if (typeName == null) {
            typeName = sourceSchema.getTypeName();
            configuration.getOutputFeature().setTypeName(typeName);
        }
        CoordinateReferenceSystem crs = configuration.getOutputFeature()
                .getCoordinateReferenceSystem();
        if (crs == null) {
            String reprojCrs = configuration.getReprojectedCrs();
            if (reprojCrs != null && !reprojCrs.isEmpty()) {
                try {
                    crs = CRS.decode(reprojCrs);
                } catch (Exception e) {
                    logger.error("Failed to decode reprojCrs, use src CRS for now but please fix the configuration. The exception occurred is " + e.getClass());
                    crs = sourceSchema.getCoordinateReferenceSystem();
                }
            } else {
                crs = sourceSchema.getCoordinateReferenceSystem();
            }
            configuration.getOutputFeature().setCoordinateReferenceSystem(crs);
        }
        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
        builder.setCRS(crs);
        builder.setName(typeName);

        for (String attributeName : buildOutputAttributes(sourceSchema)) {
            builder.add(buildSchemaAttribute(attributeName, sourceSchema, crs));
        }
        return builder.buildFeatureType();
    }

    /**
     * Builds a single attribute for the output Feature schema. By default it
     * uses the original source attribute definition, if not overridden by
     * configuration.
     *
     * @param attr
     * @param crs crs to use for geometric attributes
     * @return
     */
    private AttributeDescriptor buildSchemaAttribute(String attributeName,
            SimpleFeatureType schema, CoordinateReferenceSystem crs) {
        AttributeDescriptor attr;
        if (configuration.getAttributeMappings().containsKey(attributeName) && !isExpression(configuration.getAttributeMappings()
                .get(attributeName).toString())) {
            attr = schema.getDescriptor(configuration.getAttributeMappings()
                    .get(attributeName).toString());
        } else {
            attr = schema.getDescriptor(attributeName);
        }
        AttributeTypeBuilder builder = new AttributeTypeBuilder();
        builder.setName(attr.getLocalName());
        builder.setBinding(attr.getType().getBinding());
        if (attr instanceof GeometryDescriptor) {
            if (crs == null) {
                crs = ((GeometryDescriptor) attr).getCoordinateReferenceSystem();
            }
            builder.setCRS(crs);
        }

        // set descriptor information
        builder.setMinOccurs(attr.getMinOccurs());
        builder.setMaxOccurs(attr.getMaxOccurs());
        builder.setNillable(attr.isNillable());

        return builder.buildDescriptor(attributeName);
    }

    /**
     * Builds the list of output attributes, looking at mappings configuration
     * and source schema.
     *
     * @param sourceSchema
     * @return
     */
    private Collection<String> buildOutputAttributes(SimpleFeatureType sourceSchema) {

        if (configuration.isProjectOnMappings()) {
            return configuration.getAttributeMappings().keySet();
        } else {
            List<String> attributes = Lists.<String>newArrayList();
            for (AttributeDescriptor attr : sourceSchema.getAttributeDescriptors()) {
                attributes.add(getAttributeMapping(attr.getLocalName()));
            }
            return attributes;
        }
    }

    /**
     * Gets the eventual output mapping for the given source attribute name.
     *
     * @param localName
     * @return
     */
    private String getAttributeMapping(String localName) {
        for (String outputName : configuration.getAttributeMappings().keySet()) {
            if (configuration.getAttributeMappings().get(outputName).toString().equals(localName)) {
                return outputName;
            }
        }
        return localName;
    }

    /**
     * Creates the source datastore reader.
     *
     * @param sourceDataStore
     * @param transaction
     * @param query
     * @return
     * @throws IOException
     */
    protected FeatureStore<SimpleFeatureType, SimpleFeature> createSourceReader(
            DataStore sourceDataStore, final Transaction transaction,
            Query query) throws IOException {
        FeatureStore<SimpleFeatureType, SimpleFeature> featureReader
                = (FeatureStore<SimpleFeatureType, SimpleFeature>) sourceDataStore
                .getFeatureSource(query.getTypeName());
        featureReader.setTransaction(transaction);
        return featureReader;
    }

    protected Query buildSourceQuery(DataStore sourceStore) throws Exception {
        Query query = new Query();
        query.setTypeName(configuration.getSourceFeature().getTypeName());

        // Used to force the CRS of the source feature: it doesn't perform a reprojection, just force the CRS
        // if the configuration doesn't specify it the Crs will be read from the source feature
        query.setCoordinateSystem(configuration.getSourceFeature().getCoordinateReferenceSystem());
        // Used to reproject the source feature: if the CRS has been forced (see before) the reprojection
        // is performed between the forced CRS (query.getCoordinateSystem()) and the CRS specified for reprojection (query.getCoordinateSystemReproject)
        // otherwise the origin CRS is read from src feature
        CoordinateReferenceSystem coordinateReferenceSystemTarget = null;
        String epsgCode = configuration.getReprojectedCrs();
        if (epsgCode != null) {
            // In case this property is still not set we have to set it before start with reprojection
            // to avoid wrong axis ordering 
            if (System.getProperty("org.geotools.referencing.forceXY") == null) {
                System.setProperty("org.geotools.referencing.forceXY", "true");
            }
            try {
                coordinateReferenceSystemTarget = CRS.decode(epsgCode);
            } catch (NoSuchAuthorityCodeException e) {
                throw new IllegalArgumentException("Invalid crs: " + epsgCode);
            } catch (FactoryException e) {
                throw new IllegalArgumentException("Invalid crs: " + epsgCode);
            }
        }
        query.setCoordinateSystemReproject(coordinateReferenceSystemTarget);
        query.setFilter(buildFilter());
        return query;
    }

    protected Filter buildFilter() throws Exception {
        if (filter != null) {
            return filter;
        }
        String cqlFilter = configuration.getEcqlFilter();
        if (cqlFilter == null || cqlFilter.isEmpty()) {
            if (logger.isDebugEnabled()) {
                logger.debug("No cql source store filter set...");
            }
            return Filter.INCLUDE;
        }
        ECQLCompiler compiler = new ECQLCompiler(cqlFilter, CommonFactoryFinder.getFilterFactory2());
        try {
            compiler.compileFilter();
            return compiler.getFilter();
        } catch (CQLException e) {
            logger.error(e.getMessage(), e);
            throw new Exception("Error while cql filter compilation. please check and be sure that the cql filter specified in configuration is correct, see the log for more infos about the error.");
        }
    }

    protected DataStore createSourceDataStore(File shpFile) throws IOException, ResourceNotFoundFault {
        updateTask("Connecting to source DataStore");
//        String fileType = getFileType(fileEvent); == shp
        FeatureConfiguration sourceFeature = configuration.getSourceFeature();
        sourceFeature.getDataStore()
                .put("url", DataUtilities.fileToURL(shpFile));
        DataStore source = createDataStore(sourceFeature);
        // if no typeName is configured, takes the first one registered in store
        if (sourceFeature.getTypeName() == null) {
            sourceFeature.setTypeName(source.getTypeNames()[0]);
        }
        // if no CRS is configured, takes if from the feature
        logger.info("!!!!!!!!!!!!!!!!!!! CRS: " + sourceFeature.getCrs());
        if (sourceFeature.getCrs() == null) {
            sourceFeature.setCoordinateReferenceSystem(source.getSchema(
                    sourceFeature.getTypeName())
                    .getCoordinateReferenceSystem());
        }
        configuration.setSourceFeature(sourceFeature);
        return source;
    }

    protected DataStore createDataStore(FeatureConfiguration config) throws IOException, ResourceNotFoundFault {
        DataStore dataStore = FeatureConfigurationUtil.createDataStore(config);
        if (dataStore == null) {
            logger.debug("Cannot connect to DataStore: wrong parameters");
        }
        return dataStore;
    }

    /**
     * Creates the destination DataStore, from the configuration connection
     * parameters.
     *
     * @return
     * @throws IOException
     * @throws ResourceNotFoundFault
     */
    protected DataStore createOutputDataStore() throws IOException, ResourceNotFoundFault {
        logger.debug("Connecting to output DataStore");
        return createDataStore(configuration.getOutputFeature());
    }

    protected void closeResource(DataStore dataStore) {
        if (dataStore != null) {
            try {
                dataStore.dispose();
            } catch (Throwable t) {
                if (logger.isErrorEnabled()) {
                    logger.error("Error closing datastore connection");
                }
            }
        }
    }

    protected void closeResource(Transaction transaction) {
        if (transaction != null) {
            try {
                transaction.close();
            } catch (Throwable t) {
                if (logger.isErrorEnabled()) {
                    logger.error("Error closing transaction");
                }
            }
        }
    }

    /**
     * Updates the import progress ( progress / total ) for the listeners.
     *
     * @param progress
     * @param total
     * @param message
     */
    protected void updateImportProgress(int progress, int total, String message) {
        float f = total == 0 ? 0 : (float) progress / total;
        logger.debug(message);
        if (logger.isInfoEnabled()) {
            logger.info("Importing data: " + progress + "/" + total);
        }
    }

    protected void updateTask(String task) {
        logger.debug(task);
        if (logger.isInfoEnabled()) {
            logger.info(task);
        }
    }

    private File extractShpFile(String tempUserDir, File zipShpFile) {
        File shpToReturn = null;
        ZipFile zipSrc = null;
        try {
            // decomprime il contenuto di file nella cartella <tmp>/geoportal/shp
            zipSrc = new ZipFile(zipShpFile);
            Enumeration<? extends ZipEntry> entries = zipSrc.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();
                File newFile = new File(entryName);
                if (newFile.isDirectory()) {
                    continue;
                }
                //   entryName = entryName.replaceAll("/", "_");
                //    logger.info("\n ********** INFO:"+entryName);

                int lastIndex = entryName.lastIndexOf('/');
                entryName = entryName.substring(lastIndex + 1).toLowerCase();
                if (entryName.equals("")) {
                    continue;
                } else if (entryName.endsWith(".shp")) {
                    logger.info("*** extractShpFile INFO: Found shape file " + entryName);
                    PublishUtility.extractEntryToFile(entry, zipSrc, tempUserDir);
                    shpToReturn = new File(tempUserDir + entryName.toLowerCase());
                }
                PublishUtility.extractEntryToFile(entry, zipSrc, tempUserDir);
            }
            if (shpToReturn == null) {
                throw new IllegalArgumentException("The passed zip file doe not contains shp file");
            }
        } catch (Exception e) {
            logger.error("ERROR: " + e);
            throw new IllegalArgumentException("ERROR: " + e);
        } finally {
            try {
                zipSrc.close();
            } catch (IOException ex) {
            }
        }
        return shpToReturn;
    }
}
