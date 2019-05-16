package org.geosdi.geoplatform.gui.client.widget.guide;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GuideResource extends ClientBundle {
    public static final GuideResource INSTANCE = GWT.create(GuideResource.class);

    @Source("cql_guide.html")
    public TextResource getCqlGuide();

}
