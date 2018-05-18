package org.geosdi.geoplatform.gui.client.config.provider.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTreeStore;
import org.geosdi.geoplatform.gui.client.model.tree.WFSRootLayerTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Provider;

import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class LayerTreePanelProvider implements Provider<GPTreePanel> {

    private final GPTreeStore store;

    @Inject
    public LayerTreePanelProvider(@WFSLayerTreeStore GPTreeStore store) {
        this.store = store;
    }

    @Override
    public GPTreePanel get() {
        return new GPTreePanel(store) {

            {
                super.setIconProvider(new ModelIconProvider<GPBeanTreeModel>() {

                    @Override
                    public AbstractImagePrototype getIcon(
                            GPBeanTreeModel model) {
                        return model.getIcon();
                    }

                });

                super.setLabelProvider(new ModelStringProvider<GPBeanTreeModel>() {

                    @Override
                    public String getStringValue(GPBeanTreeModel model, String property) {
                        return model.getLabel();
                    }

                });
            }

            @Override
            protected boolean hasChildren(ModelData model) {
                return model instanceof WFSRootLayerTreeNode;
            }

        };
    }
}
