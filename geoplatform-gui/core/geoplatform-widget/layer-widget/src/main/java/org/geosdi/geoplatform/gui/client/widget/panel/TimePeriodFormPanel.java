package org.geosdi.geoplatform.gui.client.widget.panel;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Slider;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.common.collect.Lists;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.puregwt.action.GPActionHandler;
import org.geosdi.geoplatform.gui.client.puregwt.binding.GPDateBindingHandler;
import org.geosdi.geoplatform.gui.client.puregwt.filter.event.GPHideFilterWidgetEvent;
import org.geosdi.geoplatform.gui.client.puregwt.reset.GPResetComponentHandler;
import org.geosdi.geoplatform.gui.client.puregwt.reset.event.GPResetComponentsEvent;
import org.geosdi.geoplatform.gui.client.resources.LayerWidgetResourcesConfigurator;
import org.geosdi.geoplatform.gui.client.widget.multifield.EndDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.multifield.StartDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation.IStrategyOperation;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view.IStrategyView;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view.TypeValueEnum;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.TimeFilterLayerMapEvent;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.LAYER_TIME_DELIMITER;
import static org.geosdi.geoplatform.gui.client.widget.time.LayerTimeFilterWidget.WIDGET_HEIGHT;
import static org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view.TypeValueEnum.*;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class TimePeriodFormPanel extends FormPanel implements GPDateBindingHandler, GPResetComponentHandler, GPActionHandler {

    protected SpinnerField timerAnimation;

    private final LayoutContainer periodSliderContainer;
    private final StartDateMultifield startDateMultifield;
    private final EndDateMultifield endDateMultifield;
    private CheckBox endDateCheckBox;
    private Slider periodSlider;
    private LabelField labelCurrenteTime;
    private LabelField labelStep;
    private ToggleButton playButton = new ToggleButton(null,
            AbstractImagePrototype.create(LayerResources.ICONS.playTimeDimension()));
    private ToggleButton reversePlayButton = new ToggleButton(null,
            AbstractImagePrototype.create(LayerResources.ICONS.playReverseTimeDimension()));
    private Button backwardButton = new Button(null,
            AbstractImagePrototype.create(LayerResources.ICONS.backwardTimeDimension()));
    private Button forwardPlayButton = new Button(null,
            AbstractImagePrototype.create(LayerResources.ICONS.forwardTimeDimension()));
    private SelectionListener<ButtonEvent> periodPlaySelectioListener;
    private SelectionListener<ButtonEvent> periodReversePlaySelectioListener;
    private Timer animationTimer;
    private int currentValue;
    private GPTreePanel treePanel;
    private List<Date> store = Lists.newArrayList();
    private List<Date> partialStore = Lists.newArrayList();
    private LabelField labelRange;
    private LabelField labelPeriod;
    @Inject
    private IStrategyView iStrategyView;
    @Inject
    private IStrategyOperation iStrategyOperation;
    @Inject
    private IParseMediator.ParseMediator parseMediator;
    private Long period = null;
    private Button apply;
    private final static TimeFilterLayerMapEvent TIME_FILTER_LAYER_MAP_EVENT = new TimeFilterLayerMapEvent();
    private final DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss");
    DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy, HH:mm:ss");


    @Inject
    public TimePeriodFormPanel(StartDateMultifield theStartDateMultifield, final EndDateMultifield theEndDateMultifield,
                               LayerWidgetResourcesConfigurator layerWidgetResourcesConfigurator) {
        this.startDateMultifield = theStartDateMultifield;
        this.endDateMultifield = theEndDateMultifield;
        layerWidgetResourcesConfigurator.configure();
        this.periodSliderContainer = new LayoutContainer(new FormLayout());
        this.addComponents();
        WidgetPropertiesHandlerManager.addHandler(GPDateBindingHandler.TYPE, this);
        WidgetPropertiesHandlerManager.addHandler(GPResetComponentHandler.TYPE, this);
        WidgetPropertiesHandlerManager.addHandler(GPActionHandler.TYPE, this);
    }

    private void addComponents() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setFrame(Boolean.TRUE);
        super.setBorders(Boolean.FALSE);
        super.setHeight(WIDGET_HEIGHT);
        super.setAutoWidth(Boolean.TRUE);
        this.labelRange = new LabelField();
        this.labelPeriod = new LabelField();
        this.labelPeriod.addStyleName("label_period");
        this.labelRange.addStyleName("label_period");
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
                endDateMultifield.reset();
                endDateMultifield.clearInvalid();
            }
        });


        LayoutContainer container = new LayoutContainer();
        container.add(this.endDateCheckBox);
        super.add(container, new FlowData(5));
        super.add(this.endDateMultifield, new FlowData(5));
//        buildDatesAvailables();
        this.labelCurrenteTime = new LabelField();
        this.labelCurrenteTime.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_currentDateTooltipText());
        this.labelCurrenteTime.setLabelSeparator(":");


        this.timerAnimation = new SpinnerField();
        this.timerAnimation.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_refreshTooltipText());
//        this.timerAnimation.setWidth("76px");
        this.timerAnimation.addStyleName("label_animation");
        this.timerAnimation.setMinValue(1);
        this.timerAnimation.setValue(1);
        this.timerAnimation.setAllowBlank(Boolean.FALSE);
        super.add(this.timerAnimation, new FlowData(5));


        this.buildTimeTimension();
        super.add(this.labelCurrenteTime, new FlowData(5));


        this.labelStep = new LabelField();
        this.labelStep.setFieldLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_stepTooltipText());
        this.labelStep.setLabelSeparator(":");
        super.add(this.labelStep, new FlowData(5));


        this.apply = new Button(ButtonsConstants.INSTANCE.applyText());
        this.apply.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                validateForm();
            }
        });
        super.addButton(this.apply);

        Button close = new Button(ButtonsConstants.INSTANCE.closeText(),
                new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        stopPlayer();
                        WidgetPropertiesHandlerManager.fireEvent(new GPHideFilterWidgetEvent());
                    }
                });
        this.addButton(close);
        initComponents();
    }

    private void buildTimeTimension() {
        HorizontalPanel buttonsContainer = new HorizontalPanel();
        buttonsContainer.setSpacing(2);
        this.periodSlider = new Slider() {

            @Override
            public void setValue(int value) {
                if (value >= 0 && !partialStore.isEmpty()) {
                    super.setValue(value);
                    currentValue = value;
                    labelCurrenteTime.setValue(fmt.format(partialStore.get(currentValue)));
                    super.setMessage("" + partialStore.get(currentValue));
                    GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
//                    GWT.log(layerSelected.getLabel()+layerSelected.getLabel() + LAYER_TIME_DELIMITER + timeFilter+ "]");
//                    GeoPlatformMessage.infoMessage(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_timeFilterMessageTitleText(),
//                            LayerModuleMessages.INSTANCE.LayerTimeFilterWidget_layerStatusShowedMessage(timeFilter));
                    //1985-01-01T23:00:00.000Z
//                    GWT.log("@@@@@@@@@@@"+partialStore.get(currentValue));
                    String f = sdf.format(partialStore.get(currentValue)).concat(".000Z");
                    layerSelected.setTimeFilter(f);
                    layerSelected.setAlias(null);
                    layerSelected.setAlias(layerSelected.getLabel() + LAYER_TIME_DELIMITER + f + "]");
                    TIME_FILTER_LAYER_MAP_EVENT.setLayerBean(layerSelected);
                    GPHandlerManager.fireEvent(TIME_FILTER_LAYER_MAP_EVENT);
                    treePanel.refresh(layerSelected);
                    enableOnPlaying();
                }
            }
        };
        this.periodSlider.setIncrement(1);
        this.periodSlider.setUseTip(Boolean.TRUE);
        this.playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
        this.reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.playReverseText());
        this.forwardPlayButton.setToolTip(ButtonsConstants.INSTANCE.nextText());
        this.backwardButton.setToolTip(ButtonsConstants.INSTANCE.prevText());
        this.addListener();
        this.playButton.addSelectionListener(this.periodPlaySelectioListener);
        this.reversePlayButton.addSelectionListener(this.periodReversePlaySelectioListener);
        buttonsContainer.add((this.backwardButton));
        buttonsContainer.add((this.reversePlayButton));
        buttonsContainer.add((this.playButton));
        buttonsContainer.add((this.forwardPlayButton));
        this.periodSliderContainer.setLayout(new ColumnLayout());
        this.periodSliderContainer.add(buttonsContainer, new ColumnData(.3));
        this.periodSliderContainer.add(this.periodSlider, new ColumnData(.7));
        this.periodSlider.setAutoWidth(Boolean.TRUE);
        super.add(this.periodSliderContainer);

    }

    private void enableOnPlaying() {
        this.backwardButton.setEnabled(!this.playButton.isPressed() && !this.reversePlayButton.isPressed() && this.currentValue > this.periodSlider.getMinValue());
        this.reversePlayButton.setEnabled(!this.playButton.isPressed() && this.currentValue > this.periodSlider.getMinValue());
        this.forwardPlayButton.setEnabled(!this.playButton.isPressed() && !this.reversePlayButton.isPressed() && this.currentValue < this.periodSlider.getMaxValue());
        this.playButton.setEnabled(!this.reversePlayButton.isPressed() && this.currentValue < this.periodSlider.getMaxValue());
    }

    private void addListener() {
        this.periodPlaySelectioListener = new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
//                GWT.log("Play button status: "+playButton.isPressed());
                if (!playButton.isPressed()) {
                    apply.enable();
                    playButton.setToolTip(ButtonsConstants.INSTANCE.playText());
                    playButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.playTimeDimension()));
                    animationTimer.cancel();
                    enableOnPlaying();
                } else {
                    apply.disable();
                    playButton.setToolTip(ButtonsConstants.INSTANCE.pauseText());
                    playButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.pauseTimeDimension()));
                    playTimeFilter();
                }
            }
        };
        this.periodReversePlaySelectioListener = new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
//                GWT.log("Play button status: "+playButton.isPressed());
                if (!reversePlayButton.isPressed()) {
                    apply.enable();
                    reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.playReverseText());
                    reversePlayButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.playReverseTimeDimension()));
                    animationTimer.cancel();
                    enableOnPlaying();
                } else {
                    apply.disable();
                    reversePlayButton.setToolTip(ButtonsConstants.INSTANCE.pauseText());
                    reversePlayButton.setIcon(AbstractImagePrototype.create(
                            LayerResources.ICONS.pauseTimeDimension()));
                    reversePlayTimeFilter();
                }
            }
        };
        this.backwardButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                periodSlider.setValue(periodSlider.getValue() - 1);

            }
        });
        this.forwardPlayButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                periodSlider.setValue(periodSlider.getValue() + 1);
            }
        });
    }

    private void playTimeFilter() {
        animationTimer = new Timer() {
            @Override
            public void run() {
                if (currentValue < periodSlider.getMaxValue()) {
                    periodSlider.setValue(++currentValue);
                } else {
                    stopPlayer();
                }
            }
        };
        animationTimer.scheduleRepeating(this.timerAnimation.getValue().intValue() * 1000);
    }

    private void reversePlayTimeFilter() {
        animationTimer = new Timer() {
            @Override
            public void run() {
                if (currentValue > periodSlider.getMinValue()) {
                    periodSlider.setValue(--currentValue);
                } else {
                    stopPlayer();
                }
            }
        };
        animationTimer.scheduleRepeating(this.timerAnimation.getValue().intValue() * 1000);
    }

    private void stopPlayer() {
        if (this.playButton.isPressed()) {
            this.playButton.toggle(Boolean.FALSE);
            this.periodPlaySelectioListener.componentSelected(null);
        }
        if (this.reversePlayButton.isPressed()) {
            this.reversePlayButton.toggle(Boolean.FALSE);
            this.periodReversePlaySelectioListener.componentSelected(null);
        }
        enableOnPlaying();
    }

    private boolean validateForm() {
        if (this.period == null)
            this.period = this.parseMediator.calculatePeriod(this.iStrategyView.getExtentValues().get(PERIOD).toString());
        if (!isValid()) {
            this.playButton.toggle(Boolean.FALSE);
            return Boolean.FALSE;
        } else if (this.endDateMultifield.isEnabled() && !this.startDateMultifield.getDate().before(this.endDateMultifield.getDate())) {
            GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                            LayerTimeFilterWidget_timeFilterWarningTitleText(),
                    LayerModuleMessages.INSTANCE.
                            LayerTimeFilterWidget_rangeDateErrorMessage());
//            this.playButton.toggle(Boolean.FALSE);
            return Boolean.FALSE;
        } else if (this.endDateMultifield.isEnabled() &&
                this.endDateMultifield.getDate().getTime() - this.startDateMultifield.getDate().getTime() < this.period) {
            GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                            LayerTimeFilterWidget_timeFilterWarningTitleText(),
                    LayerModuleMessages.INSTANCE.
                            LayerTimeFilterWidget_periodDateErrorMessage());
            return Boolean.FALSE;
        }
        this.calculateStep();
        this.labelPeriod.setValue(this.parseMediator.getParsedPeriod());
        return Boolean.TRUE;
    }

    private void initComponents() {
        this.backwardButton.disable();
        this.forwardPlayButton.disable();
        this.playButton.toggle(Boolean.FALSE);
        this.reversePlayButton.toggle(Boolean.FALSE);
        this.playButton.disable();
        this.reversePlayButton.disable();
        this.periodSlider.disable();
        this.endDateCheckBox.setValue(true);
        this.endDateMultifield.setVisible(Boolean.TRUE);
        this.store.clear();
        this.partialStore.clear();
        this.periodSlider.setValue(0);
        this.labelCurrenteTime.setValue(null);
        this.store.clear();
        this.partialStore.clear();
        this.labelStep.setValue(null);
        this.timerAnimation.setValue(1);
    }

    @Override
    protected void onAttach() {
        super.onAttach();
    }

    @Override
    protected void onDetach() {
        WidgetPropertiesHandlerManager.fireEvent(new GPResetComponentsEvent());
        this.stopPlayer();
        super.onDetach();

    }

    @Override
    public void bindTreeModel(GPTreePanel gpTreePanel) {
        this.treePanel = gpTreePanel;
        Map<TypeValueEnum, Object> m = this.iStrategyView.getExtentValues();
        this.labelRange.setValue(fmt.format((Date) m.get(DATE_FROM)).concat(" / ")
                .concat(fmt.format((Date) m.get(DATE_TO))));
        this.endDateMultifield.bindDate((Date) m.get(DATE_FROM), (Date) m.get(DATE_TO));
        this.startDateMultifield.bindDate((Date) m.get(DATE_FROM), (Date) m.get(DATE_TO));
    }

    private void calculateStep() {
        Date dateTo = (Date) this.iStrategyView.getExtentValues().get(DATE_TO);
        Date dateFrom = (Date) this.iStrategyView.getExtentValues().get(DATE_FROM);
        if (this.store.isEmpty()) {
            this.store.add(dateFrom);
            while (dateFrom.equals(dateTo) || dateFrom.before(dateTo)) {
                dateFrom = new Date(dateFrom.getTime() + this.period);
                this.store.add(dateFrom);
            }
        }
        this.iStrategyOperation.getStrategy(this.endDateCheckBox.getValue()).getApplyOperation(this.store, this.startDateMultifield.getDate(),
                this.endDateMultifield.getDate());
        this.labelStep.setValue(this.partialStore.size());
    }

    @Override
    public void removeFilterTime() {
        initComponents();
    }

    @Override
    public void periodWithRangeOperation(List<Date> partialStore) {
        this.partialStore = partialStore;
        this.periodSlider.setValue(0);
        this.periodSlider.setMaxValue(this.partialStore.size() - 1);
    }

    @Override
    public void noDate(Date date1, Date date2) {
        GeoPlatformMessage.errorMessage(
                LayerModuleConstants.INSTANCE.
                        LayerTimeFilterWidget_timeFilterWarningTitleText(),
                LayerModuleMessages.INSTANCE.
                        LayerTimeFilterWidget_noDateFound(date1.toString(), date2.toString()));
    }

    @Override
    public void periodWithSingleDate(int index) {
        this.partialStore.clear();
        this.partialStore.addAll(this.store);
        this.periodSlider.setMaxValue(this.partialStore.size() - 1);
        this.periodSlider.setValue(index);
    }
}
