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
import com.google.gwt.user.client.Timer;
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
    private final StartDateMultifield startDateMultifield;
    private final EndDateMultifield endDateMultifield;
    private CheckBox endDateCheckBox;
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
    private SelectionListener<ButtonEvent> playReverseSelectioListener;
    private Timer animationTimer;
    private int currentValue;

    @Inject
    public TimeDimensionFormPanel(StartDateMultifield theStartDateMultifield, final EndDateMultifield theEndDateMultifield) {
        this.startDateMultifield = theStartDateMultifield;
        this.endDateMultifield = theEndDateMultifield;
        this.sliderContainer = new LayoutContainer(new FormLayout());
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
        HorizontalPanel buttonsContainer = new HorizontalPanel();
        buttonsContainer.setSpacing(2);
        this.slider = new Slider() {
            @Override
            public void setValue(int value) {
                super.setValue(value);
                currentValue = value;
                enableOnPlaying();
            }
        };
        this.slider.setIncrement(1);
        this.playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
        this.reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.playReverseText());
        this.forwardPlayButton.setToolTip(ButtonsConstants.INSTANCE.nextText());
        this.backwardButton.setToolTip(ButtonsConstants.INSTANCE.prevText());
        this.addListener();
        this.playButton.addSelectionListener(playSelectioListener);
        buttonsContainer.add((this.backwardButton));
        buttonsContainer.add((this.reversePlayButton));
        buttonsContainer.add((this.playButton));
        buttonsContainer.add((this.forwardPlayButton));
        this.sliderContainer.setLayout(new ColumnLayout());
        this.sliderContainer.add(buttonsContainer, new ColumnData(.3));
        this.sliderContainer.add(this.slider, new ColumnData(.7));
        this.slider.setAutoWidth(Boolean.TRUE);
        super.add(this.sliderContainer);
        this.labelCurrenteTime = new LabelField();
        this.labelCurrenteTime.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_currentDateTooltipText());
        this.labelCurrenteTime.setLabelSeparator(":");
        super.add(this.labelCurrenteTime, new FlowData(5));
    }

    private void enableOnPlaying() {
        backwardButton.setEnabled(!playButton.isPressed() && this.currentValue > slider.getMinValue());
        reversePlayButton.setEnabled(!playButton.isPressed() && this.currentValue > slider.getMinValue());
        forwardPlayButton.setEnabled(!playButton.isPressed() && this.currentValue < slider.getMaxValue());
        playButton.setEnabled(this.currentValue < slider.getMaxValue());
    }

    private void addListener() {
        this.playSelectioListener = new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
//                GWT.log("Play button status: "+playButton.isPressed());
                if (!playButton.isPressed()) {
                    playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
                    playButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.playTimeDimension()));
                    animationTimer.cancel();
                    enableOnPlaying();
                } else {
                    if (validateForm()) {
                        playButton.setToolTip(ButtonsConstants.INSTANCE.pauseText());
                        playButton.setIcon(AbstractImagePrototype.create(
                                LayerResources.ICONS.pauseTimeDimension()));
                        playTimeFilter();
                    }

                }
            }
        };
        this.backwardButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                slider.setValue(slider.getValue() - 1);
            }
        });
        this.forwardPlayButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                slider.setValue(slider.getValue() + 1);
            }
        });
    }

    private void playTimeFilter() {
        animationTimer = new Timer() {
            @Override
            public void run() {
                if (currentValue < slider.getMaxValue()) {
                    slider.setValue(++currentValue);
                    labelCurrenteTime.setValue(currentValue);
                } else {
                    stopPlayer();
                }
            }
        };
        animationTimer.scheduleRepeating(500);
    }

    private void stopPlayer() {
        if (this.playButton.isPressed()) {
            this.playButton.toggle(Boolean.FALSE);
            playSelectioListener.componentSelected(null);
            this.reversePlayButton.enable();
            this.backwardButton.enable();
        }
    }

    public boolean validateForm() {
        boolean check = isValid();
        if (!check) {
            this.playButton.toggle(Boolean.FALSE);
            return check;
        } else if (!this.startDateMultifield.getDate().before(this.endDateMultifield.getDate())) {
            GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                            LayerTimeFilterWidget_timeFilterWarningTitleText(),
                    LayerModuleMessages.INSTANCE.
                            LayerTimeFilterWidget_rangeDateErrorMessage());
            this.playButton.toggle(Boolean.FALSE);
            return false;
        }
        return true;
    }

    private void initComponents() {
        this.backwardButton.disable();
        this.forwardPlayButton.enable();
        this.playButton.toggle(Boolean.FALSE);
        this.reversePlayButton.toggle(Boolean.FALSE);
        this.startDateMultifield.initComponents();
        this.endDateMultifield.initComponents();
        this.slider.setValue(0);
        this.endDateCheckBox.setValue(true);
        this.endDateMultifield.setVisible(Boolean.TRUE);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        initComponents();
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.stopPlayer();
    }
}
