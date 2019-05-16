package org.geosdi.geoplatform.gui.client.widget.cql;

import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.guide.GuideResource;


/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GuideFilterWidget extends GeoPlatformWindow {

    public final static short WIDGET_HEIGHT = 600;
    public final static short WIDGET_WIDTH = 700;

    public GuideFilterWidget(boolean lazy) {
        super(lazy);
    }

    @Override
    public void addComponent() {
        HTML htmlPanel = new HTML();
        String html = GuideResource.INSTANCE.getCqlGuide().getText();
        htmlPanel.setHTML(html);
        ScrollPanel container = new ScrollPanel(htmlPanel);
        container.setHeight("580px");
        super.add(container);
    }

    @Override
    public void initSize() {
        super.setSize(WIDGET_WIDTH, WIDGET_HEIGHT);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(LayerFiltersModuleConstants.INSTANCE.CQLFilterAdvancedTab_cqlGuideHeaderText());
        super.setLayout(new BorderLayout());
        super.setModal(Boolean.TRUE);
        super.setResizable(Boolean.FALSE);
    }
}
