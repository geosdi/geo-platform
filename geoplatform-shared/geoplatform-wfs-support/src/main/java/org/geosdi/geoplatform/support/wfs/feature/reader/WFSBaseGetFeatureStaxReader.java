package org.geosdi.geoplatform.support.wfs.feature.reader;

import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;

import javax.annotation.Nonnull;
import javax.xml.stream.XMLStreamReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WFSBaseGetFeatureStaxReader<F, G, R> extends AbstractStaxStreamReader<R> implements GPWFSGetFeatureStaxReader {

    protected final LayerSchemaDTO layerSchema;

    protected WFSBaseGetFeatureStaxReader(@Nonnull(when = NEVER) LayerSchemaDTO theLayerSchema) {
        checkArgument(theLayerSchema != null, "The LayerSchema must not be null.");
        this.layerSchema = theLayerSchema;
    }

    /**
     * @param object
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public final R read(@Nonnull(when = NEVER) Object object) throws Exception {
        XMLStreamReader reader = super.acquireReader(object);
        GeometryAttributeDTO geometryAtt = layerSchema.getGeometry();
        String geometryName = geometryAtt.getName();
        if ((geometryAtt == null) || (geometryName == null) || (geometryName.trim().isEmpty())) {
            throw new IllegalStateException("The Geometry Attribute must not be null or " +
                    "Geometry Name must not be null or an Empty String.");
        }
        return this.internalRead(reader, geometryName, layerSchema.getAttributeNames());
    }

    /**
     * @param reader
     * @param geometryName
     * @param attributeNames
     * @return {@link R}
     * @throws Exception
     */
    protected abstract R internalRead(XMLStreamReader reader, String geometryName, List<String> attributeNames) throws Exception;

    /**
     * @return {@link F}
     * @throws Exception
     */
    protected abstract F readFID() throws Exception;

    /**
     * @return {@link G}
     * @throws Exception
     */
    protected abstract G readGeometry() throws Exception;

    /**
     * @param feature
     * @param featureName
     * @param attributeNames
     * @param nextTag
     * @param prefix
     * @param geometryName
     * @throws Exception
     */
    protected abstract void readAttributes(F feature, String featureName, List<String> attributeNames, Boolean nextTag,
            String prefix, String geometryName) throws Exception;
}