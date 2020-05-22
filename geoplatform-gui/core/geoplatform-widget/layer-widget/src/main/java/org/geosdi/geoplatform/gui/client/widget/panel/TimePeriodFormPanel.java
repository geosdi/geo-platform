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
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.puregwt.binding.GPDateBindingHandler;
import org.geosdi.geoplatform.gui.client.resources.LayerWidgetResourcesConfigurator;
import org.geosdi.geoplatform.gui.client.widget.multifield.EndDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.multifield.StartDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.IStrategyPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.TypeValueEnum;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.WIDGET_HEIGHT;
import static org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.TypeValueEnum.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class TimePeriodFormPanel extends FormPanel implements GPDateBindingHandler {

    DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy, HH:mm");

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
    private GPBeanTreeModel itemSelected;
    private List<String> store;
    private LabelField labelRange;
    private LabelField labelPeriod;
    @Inject
    private IStrategyPanel iStrategyPanel;

    @Inject
    public TimePeriodFormPanel(StartDateMultifield theStartDateMultifield, final EndDateMultifield theEndDateMultifield,
                               LayerWidgetResourcesConfigurator layerWidgetResourcesConfigurator) {
        this.startDateMultifield = theStartDateMultifield;
        this.endDateMultifield = theEndDateMultifield;
        layerWidgetResourcesConfigurator.configure();
        this.sliderContainer = new LayoutContainer(new FormLayout());
        this.addComponents();
        WidgetPropertiesHandlerManager.addHandler(GPDateBindingHandler.TYPE, this);
    }

    private void addComponents() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setFrame(Boolean.TRUE);
        super.setBorders(Boolean.FALSE);
        super.setHeight(WIDGET_HEIGHT - 30);
        super.setAutoWidth(Boolean.TRUE);
        this.labelRange = new LabelField();
        this.labelPeriod = new LabelField();
        this.labelRange.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_rangeLabelText());
        this.labelPeriod.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_periodLabelText());
        this.labelPeriod.setLabelSeparator(":");
        this.labelRange.setLabelSeparator(":");
        super.add(labelRange, new FlowData(5));
        super.add(labelPeriod, new FlowData(5));
        super.add(this.startDateMultifield, new FlowData(5));
        this.endDateCheckBox = new CheckBox();
        this.endDateCheckBox.setValue(true);
        this.endDateCheckBox.setBoxLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_enableToDate());
        this.endDateCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                endDateMultifield.setEnabled(endDateCheckBox.getValue());
                endDateMultifield.clearInvalid();
            }
        });
        LayoutContainer container = new LayoutContainer();
        container.add(this.endDateCheckBox);
        super.add(container, new FlowData(5));
        super.add(this.endDateMultifield, new FlowData(5));
        this.labelCurrenteTime = new LabelField();
        this.labelCurrenteTime.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_currentDateTooltipText());
        this.labelCurrenteTime.setLabelSeparator(":");
        super.add(this.labelCurrenteTime, new FlowData(5));
        this.buildTimeTimension();
        Button apply = new Button(ButtonsConstants.INSTANCE.applyText());
        super.addButton(apply);
    }

    private void buildTimeTimension() {
        HorizontalPanel buttonsContainer = new HorizontalPanel();


        buttonsContainer.setSpacing(2);
        this.slider = new Slider() {

//            @Override
//            public void setMinValue(int minValue) {
//                super.setMinValue(1);
//            }

            @Override
            public void setValue(int value) {
                //TODO  && value < store.size()
                if (value >= 0) {
                    super.setValue(value);
                    currentValue = value;
                    GWT.log("@@@" + value);
//                    labelCurrenteTime.setValue(store.get(currentValue ));
//                GWT.log("@@@@@@@@@@@@@@@@@@@@@@"+isValid());
                    enableOnPlaying();
                }
            }
        };
        this.slider.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
//                currentValue = slider.getValue();
////                GWT.log(currentValue);
//                labelCurrenteTime.setValue(store.get(currentValue -1 ));
////                GWT.log("@@@@@@@@@@@@@@@@@@@@@@"+isValid());
//                enableOnPlaying();
            }
        });
        this.slider.setIncrement(1);
//        this.slider.setMinValue(1);
        this.slider.setUseTip(Boolean.TRUE);
        this.playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
        this.reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.playReverseText());
        this.forwardPlayButton.setToolTip(ButtonsConstants.INSTANCE.nextText());
        this.backwardButton.setToolTip(ButtonsConstants.INSTANCE.prevText());
        this.addListener();
        this.playButton.addSelectionListener(this.playSelectioListener);
        buttonsContainer.add((this.backwardButton));
        buttonsContainer.add((this.reversePlayButton));
        buttonsContainer.add((this.playButton));
        buttonsContainer.add((this.forwardPlayButton));
        this.sliderContainer.setLayout(new ColumnLayout());
        this.sliderContainer.add(buttonsContainer, new ColumnData(.3));
        this.sliderContainer.add(this.slider, new ColumnData(.7));
        this.slider.setAutoWidth(Boolean.TRUE);
        super.add(this.sliderContainer);

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
                        slider.enable();
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
                labelCurrenteTime.setValue(slider.getValue());
            }
        });
        this.forwardPlayButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                slider.setValue(slider.getValue() + 1);
                labelCurrenteTime.setValue(slider.getValue());

            }
        });
    }

    private void playTimeFilter() {
        animationTimer = new Timer() {
            @Override
            public void run() {
                if (currentValue < slider.getMaxValue()) {
                    slider.setValue(++currentValue);
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
            this.playSelectioListener.componentSelected(null);
            this.reversePlayButton.enable();
            this.backwardButton.enable();
        }
    }

    public boolean validateForm() {
        if (!isValid()) {
            this.playButton.toggle(Boolean.FALSE);
            return Boolean.FALSE;
        } else if (this.endDateMultifield.isEnabled() && !this.startDateMultifield.getDate().before(this.endDateMultifield.getDate())) {
            GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                            LayerTimeFilterWidget_timeFilterWarningTitleText(),
                    LayerModuleMessages.INSTANCE.
                            LayerTimeFilterWidget_rangeDateErrorMessage());
            this.playButton.toggle(Boolean.FALSE);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private void initComponents() {
        this.backwardButton.disable();
        this.playButton.toggle(Boolean.FALSE);
        this.playButton.disable();
        this.forwardPlayButton.disable();
        this.slider.disable();
        this.reversePlayButton.toggle(Boolean.FALSE);
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

    @Override
    public void bindTreeModel(GPBeanTreeModel gpTreePanel) {
        this.itemSelected = gpTreePanel;
        Map<TypeValueEnum, Object> m = this.iStrategyPanel.getExtentValues();
        labelRange.setValue(fmt.format((Date) m.get(DATE_FROM)).concat(" / ")
                .concat(fmt.format((Date) m.get(DATE_TO))));
        labelPeriod.setValue(m.get(PERIOD));
    }

    private void calculateStep() {

    }
}
