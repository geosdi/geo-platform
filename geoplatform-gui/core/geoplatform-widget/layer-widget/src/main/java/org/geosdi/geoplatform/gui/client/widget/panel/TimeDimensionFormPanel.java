package org.geosdi.geoplatform.gui.client.widget.panel;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.widget.multifield.EndDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.multifield.StartDateMultifield;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import javax.inject.Inject;

import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.WIDGET_HEIGHT;

public class TimeDimensionFormPanel extends FormPanel {

    private final LayoutContainer sliderContainer;
    private CheckBox endDateCheckBox;
    private StartDateMultifield startDateMultifield;
    private EndDateMultifield endDateMultifield;
    private Slider slider;
    private LabelField labelCurrenteTime;
    private ToggleButton playButton = new ToggleButton(null,
            AbstractImagePrototype.create(LayerResources.ICONS.playTimeDimension()));
    private ToggleButton reversePlayButton = new ToggleButton(null,
            AbstractImagePrototype.create(LayerResources.ICONS.playReverseTimeDimension()));
    private Button backwardButton = new Button(null,
            AbstractImagePrototype.create(LayerResources.ICONS.backwardTimeDimension()));
    private Button forwardPlayButton = new Button(null,
            AbstractImagePrototype.create(LayerResources.ICONS.forwardTimeDimension()));
    private SelectionListener<ButtonEvent> playSelectioListener;

    @Inject
    public TimeDimensionFormPanel(StartDateMultifield startDateMultifield, final EndDateMultifield endDateMultifield) {
        this.startDateMultifield = startDateMultifield;
        this.endDateMultifield = endDateMultifield;
        this.sliderContainer = new LayoutContainer(new FormLayout()) {
            @Override
            protected void onAttach() {
                super.onAttach();
//                playButton.enable();
            }

            @Override
            protected void onHide() {
                super.onHide();
//                stopPlayer();
//                playButton.disable();
            }

            @Override
            protected void onShow() {
                super.onShow();
//                playButton.enable();
            }

            @Override
            protected void onDetach() {
                super.onDetach();
//                stopPlayer();
//                playButton.disable();
            }
        };
        this.addComponents();
    }

    private void addComponents() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setFrame(Boolean.TRUE);
        super.setBorders(Boolean.FALSE);
        super.setHeight(WIDGET_HEIGHT - 60);
        super.setStyleAttribute("background-color", "white");
        super.add(this.startDateMultifield, new FlowData(5));

        this.endDateCheckBox = new CheckBox();
        this.endDateCheckBox.setValue(true);
        this.endDateCheckBox.setBoxLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_enableToDate());
        this.endDateCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                endDateMultifield.setVisible(endDateCheckBox.getValue());
            }
        });
        LayoutContainer container = new LayoutContainer();
        container.add(this.endDateCheckBox);
        super.add(container, new FlowData(5));
        super.add(this.endDateMultifield, new FlowData(5));
        this.buildTimeTimension();
    }

    private void buildTimeTimension() {
        HorizontalPanel container = new HorizontalPanel();
        container.setSpacing(2);
        this.slider = new Slider() {
            @Override
            public void setValue(int value) {
                super.setValue(value);
//                super.setMessage("" + (startStore.getModels().size() - value));
            }
        };
        this.slider.setIncrement(1);
        this.playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
        this.reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.playReverseText());
        this.forwardPlayButton.setToolTip(ButtonsConstants.INSTANCE.nextText());
        this.backwardButton.setToolTip(ButtonsConstants.INSTANCE.prevText());
        this.playSelectioListener = new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
//                GWT.log("Play button status: "+playButton.isPressed());
                if (!playButton.isPressed()) {
                    playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
                    playButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.playTimeDimension()));

                } else {
                    if (isValid() && !validateForm()) {
                        GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                                        LayerTimeFilterWidget_timeFilterWarningTitleText(),
                                LayerModuleMessages.INSTANCE.
                                        LayerTimeFilterWidget_rangeDateErrorMessage());
                    } else if (isValid() && validateForm()) {
                        playButton.setIcon(AbstractImagePrototype.create(
                                LayerResources.ICONS.pauseTimeDimension()));
                    }
                }
            }
        };
        this.playButton.addSelectionListener(playSelectioListener);
        container.add((this.backwardButton));
        container.add((this.reversePlayButton));
        container.add((this.playButton));
        container.add((this.forwardPlayButton));
        this.sliderContainer.setLayout(new ColumnLayout());
        this.sliderContainer.add(container, new ColumnData(.3));
        this.sliderContainer.add(this.slider, new ColumnData(.7));
        this.slider.setAutoWidth(Boolean.TRUE);
        super.add(this.sliderContainer);
        this.labelCurrenteTime = new LabelField();
        this.labelCurrenteTime.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_currentDateTooltipText());
        this.labelCurrenteTime.setLabelSeparator(":");
        this.labelCurrenteTime.setValue("TEST");
        super.add(this.labelCurrenteTime, new FlowData(5));
    }

    public boolean validateForm() {
        return this.startDateMultifield.getDate().before(this.endDateMultifield.getDate());
    }


}
