package org.geosdi.geoplatform.gui.client.widget.form.projects.binding;

import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.form.Field;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.widget.form.binding.GPFieldBinding;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ProjectDescriptionFieldBinding extends GPFieldBinding {

    /**
     * @param field
     * @param property
     */
    public ProjectDescriptionFieldBinding(Field field, String property) {
        super(field, property);
    }

    @Override
    public void setModelProperty(Object val) {
        ((GPClientProject) model).setDescription(val.toString());
    }

    @Override
    public void setRecordProperty(Record r, Object val) {
        r.set(property, val);
    }
}