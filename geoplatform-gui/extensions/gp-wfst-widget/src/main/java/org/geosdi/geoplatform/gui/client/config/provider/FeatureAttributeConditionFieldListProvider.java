package org.geosdi.geoplatform.gui.client.config.provider;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureAttributeConditionField;

import javax.inject.Provider;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureAttributeConditionFieldListProvider implements Provider<List<FeatureAttributeConditionField>> {

    @Override
    public List<FeatureAttributeConditionField> get() {
        return Lists.<FeatureAttributeConditionField>newArrayList();
    }
}
