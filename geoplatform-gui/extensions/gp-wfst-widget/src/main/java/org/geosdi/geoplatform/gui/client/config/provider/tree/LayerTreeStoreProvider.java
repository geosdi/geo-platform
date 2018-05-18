package org.geosdi.geoplatform.gui.client.config.provider.tree;

import org.geosdi.geoplatform.gui.client.model.GPModelKeyProvider;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;

import javax.inject.Provider;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class LayerTreeStoreProvider implements Provider<GPTreeStore> {

    @Override
    public GPTreeStore get() {
        return new GPTreeStore() {
            {
                super.setKeyProvider(new GPModelKeyProvider());
            }
        };
    }
}
