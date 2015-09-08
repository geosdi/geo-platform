package org.geosdi.geoplatform.gui.client.config.provider;

import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;

import javax.inject.Provider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MatchComboFieldProvider implements Provider<SimpleComboBox<String>> {

    @Override
    public SimpleComboBox<String> get() {
        SimpleComboBox<String> matchComboField = new SimpleComboBox<String>() {

            @Override
            protected void onSelect(SimpleComboValue<String> model, int index) {
                super.onSelect(model, index);
            }

        };
        matchComboField.setToolTip(new ToolTipConfig("Match selection", "Change feature selection"));
        matchComboField.setEditable(Boolean.FALSE);
        matchComboField.setTypeAhead(Boolean.TRUE);
        matchComboField.setTriggerAction(ComboBox.TriggerAction.ALL);
        matchComboField.add(MatchType.ALL.name());
        matchComboField.add(MatchType.ANY.name());
        matchComboField.add(MatchType.NONE.name());
        matchComboField.setSimpleValue(MatchType.ALL.name());

        return matchComboField;
    }

    private enum MatchType {

        ALL, ANY, NONE;
    }
}
