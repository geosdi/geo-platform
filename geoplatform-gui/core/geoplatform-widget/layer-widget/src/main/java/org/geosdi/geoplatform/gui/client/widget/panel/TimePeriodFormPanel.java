/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.widget.panel;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.Label;
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
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.puregwt.binding.GPDateBindingHandler;
import org.geosdi.geoplatform.gui.client.puregwt.filter.event.GPHideFilterWidgetEvent;
import org.geosdi.geoplatform.gui.client.puregwt.layer.GPLayerHandler;
import org.geosdi.geoplatform.gui.client.puregwt.period.GPPeriodHandler;
import org.geosdi.geoplatform.gui.client.puregwt.reset.GPResetComponentHandler;
import org.geosdi.geoplatform.gui.client.puregwt.reset.event.GPResetComponentsEvent;
import org.geosdi.geoplatform.gui.client.resources.LayerWidgetResourcesConfigurator;
import org.geosdi.geoplatform.gui.client.widget.multifield.EndDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.multifield.StartDateMultifield;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation.IStrategyDateOperation;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.time.IStrategyLayerOperation;
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
public class TimePeriodFormPanel extends FormPanel implements GPDateBindingHandler, GPResetComponentHandler, GPPeriodHandler,
        GPLayerHandler {

    private final static TimeFilterLayerMapEvent TIME_FILTER_LAYER_MAP_EVENT = new TimeFilterLayerMapEvent();
    private final static String DATE_SEPARATOR = " / ";
    private final LayoutContainer periodSliderContainer;
    private final StartDateMultifield startDateMultifield;
    private final EndDateMultifield endDateMultifield;
    private final DateTimeFormat sdf = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss");
    protected SpinnerField timerAnimation;
    private DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy, HH:mm:ss");
    private DateTimeFormat fmt_noseconds = DateTimeFormat.getFormat("dd-MM-yyyy, HH:mm");
    private CheckBox endDateCheckBox;
    private CheckBox showAllCheckBox;
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
    private IStrategyDateOperation iStrategyDateOperation;
    @Inject
    private IStrategyLayerOperation iStrategyLayerOperation;
    @Inject
    private IParseMediator.ParseMediator parseMediator;
    private Long period = null;
    private Button apply;
    private Label labelDates;

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
        WidgetPropertiesHandlerManager.addHandler(GPPeriodHandler.TYPE, this);
        WidgetPropertiesHandlerManager.addHandler(GPLayerHandler.TYPE, this);
    }


    /**
     * @param gpTreePanel
     */
    @Override
    public void bindTreeModel(GPTreePanel gpTreePanel) {
        this.treePanel = gpTreePanel;
    }


    /**
     *
     */
    @Override
    public void removeFilterTime() {
        initComponents();
    }

    /**
     * @param partialStore
     */
    @Override
    public void periodWithRangeOperation(List<Date> partialStore) {
        this.partialStore = partialStore;
        this.periodSlider.setValue(0);
        this.periodSlider.setMaxValue(this.partialStore.size() - 1);
    }

    /**
     * @param date1
     * @param date2
     */
    @Override
    public void noDate(Date date1, Date date2) {
        GeoPlatformMessage.errorMessage(
                LayerModuleConstants.INSTANCE.
                        LayerTimeFilterWidget_timeFilterWarningTitleText(),
                LayerModuleMessages.INSTANCE.
                        LayerTimeFilterWidget_noDateFound(date1.toString(), date2.toString()));
    }

    /**
     * @param index
     */
    @Override
    public void periodWithSingleDate(int index) {
        this.partialStore.clear();
        this.partialStore.addAll(this.store);
        this.periodSlider.setMaxValue(this.partialStore.size() - 1);
        this.periodSlider.setValue(index);
    }

    /**
     * @param from
     */
    @Override
    public void refreshDateFrom(Date from) {
        String[] dates = this.labelDates.getHtml().split(DATE_SEPARATOR);
        String s = fmt_noseconds.format(from).concat(DATE_SEPARATOR).concat(dates.length == 2 ? this.labelDates.getHtml()
                .substring(this.labelDates.getHtml().indexOf(DATE_SEPARATOR) + 3) : "");
        this.labelDates.setHtml(s);
    }

    /**
     * @param to
     */
    @Override
    public void refreshDateTo(Date to) {
        this.labelDates.setHtml(this.labelDates.getHtml().substring(0, this.labelDates.getHtml().indexOf(DATE_SEPARATOR) + 3)
                .concat(to != null ? fmt_noseconds.format(to) : ""));

    }

    /**
     *
     */
    @Override
    public void singleLayer() {
        this.calculateStep();
    }

    /**
     *
     */
    @Override
    public void multipleLayer() {
        this.periodSlider.setValue(0);
        this.labelStep.setValue(null);
        this.labelCurrenteTime.setValue(null);
        this.playButton.toggle(Boolean.FALSE);
        this.playButton.disable();
        this.reversePlayButton.toggle(Boolean.FALSE);
        this.reversePlayButton.disable();
        this.backwardButton.disable();
        this.forwardPlayButton.disable();
        Date to = this.endDateCheckBox.getValue() ? this.endDateMultifield.getDate() : (Date) this.iStrategyView.getExtentValues().get(DATE_TO);
        String f = sdf.format(this.startDateMultifield.getDate(), TimeZone.createTimeZone(0)).concat(".000Z").concat("/")
                .concat(sdf.format(to
                        , TimeZone.createTimeZone(0)).concat(".000Z"));
        this.saveLayer(f);

    }

    /**
     * @param timeFilter
     */
    private void saveLayer(String timeFilter) {
        GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(layerSelected);
        layerSelected.setTimeFilter(timeFilter);
        layerSelected.setAlias(null);
        layerSelected.setAlias(layerSelected.getLabel() + LAYER_TIME_DELIMITER + timeFilter + "]");
        TIME_FILTER_LAYER_MAP_EVENT.setLayerBean(layerSelected);
        GPHandlerManager.fireEvent(TIME_FILTER_LAYER_MAP_EVENT);
        mementoSave.putOriginalPropertiesInCache(memento);
        treePanel.refresh(layerSelected);
    }


    /**
     *
     */
    private void calculateStep() {
        if (this.store.isEmpty()) {
            Date dateTo = (Date) this.iStrategyView.getExtentValues().get(DATE_TO);
            Date dateFrom = (Date) this.iStrategyView.getExtentValues().get(DATE_FROM);
            Date d = new Date(dateFrom.getTime());
            while (d.getTime() >= dateFrom.getTime() && d.getTime() <= dateTo.getTime()) {
                this.store.add(d);
                d = this.parseMediator.getNextDate(d);
            }
        }
        this.iStrategyDateOperation.getStrategy(this.endDateCheckBox.getValue()).getApplyOperation(this.store, this.startDateMultifield.getDate(),
                this.endDateMultifield.getDate());
        this.labelStep.setValue(this.partialStore.size());
    }

    /**
     *
     */
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
                refreshDateTo(!endDateCheckBox.getValue() ? ((Date) iStrategyView.getExtentValues().get(DATE_TO)) : null);
            }
        });

        LayoutContainer container = new LayoutContainer();

        container.setLayout(new ColumnLayout());

        container.add(this.endDateCheckBox);
        super.add(container, new FlowData(5));
        super.add(this.endDateMultifield, new FlowData(5));

        this.showAllCheckBox = new CheckBox();
        this.showAllCheckBox.setValue(false);
        this.showAllCheckBox.setBoxLabel(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_showAllTimeLayers());
        this.showAllCheckBox.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                timerAnimation.setEnabled(!showAllCheckBox.getValue());
                periodSlider.setEnabled(!showAllCheckBox.getValue());
            }
        });

        LayoutContainer containerShow = new LayoutContainer();
        containerShow.setLayout(new ColumnLayout());
        containerShow.add(this.showAllCheckBox, new ColumnData(.3));
        this.labelDates = new Label(DATE_SEPARATOR);
        this.labelDates.setStyleAttribute("margin-top", "5px");

        containerShow.add(this.labelDates, new ColumnData(.7));
        super.add(containerShow, new FlowData(5));

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
    }

    /**
     *
     */
    private void buildTimeTimension() {
        HorizontalPanel buttonsContainer = new HorizontalPanel();
        buttonsContainer.setSpacing(2);
        this.periodSlider = new Slider() {

            @Override
            public void setValue(int value) {
                if (value >= 0 && !partialStore.isEmpty()) {
                    super.setValue(value);
                    currentValue = value;
                    super.setMessage("" + fmt.format(partialStore.get(currentValue)));
                    labelCurrenteTime.setValue(fmt.format(partialStore.get(currentValue)));
                    saveLayer(sdf.format(partialStore.get(currentValue), TimeZone.createTimeZone(0)).concat(".000Z"));
                    enableOnPlaying();
                }

            }
        };
        this.periodSlider.setIncrement(1);
        this.periodSlider.setUseTip(Boolean.TRUE);
        this.periodSlider.setDraggable(Boolean.FALSE);
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

    /**
     *
     */
    private void enableOnPlaying() {
        this.backwardButton.setEnabled(!this.playButton.isPressed() && !this.reversePlayButton.isPressed() && this.currentValue > this.periodSlider.getMinValue());
        this.reversePlayButton.setEnabled(!this.playButton.isPressed() && this.currentValue > this.periodSlider.getMinValue());
        this.forwardPlayButton.setEnabled(!this.playButton.isPressed() && !this.reversePlayButton.isPressed() && this.currentValue < this.periodSlider.getMaxValue());
        this.playButton.setEnabled(!this.reversePlayButton.isPressed() && this.currentValue < this.periodSlider.getMaxValue());
    }

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     *
     */
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

    /**
     * @return
     */
    private boolean validateForm() {
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
        this.iStrategyLayerOperation.getStrategy(this.showAllCheckBox.getValue()).getApplyOperation();
        return Boolean.TRUE;
    }

    /**
     *
     */
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
        this.periodSlider.setValue(0, Boolean.TRUE);
        this.labelCurrenteTime.setValue(null);
        this.labelStep.setValue(null);
        this.timerAnimation.setValue(1);
        this.showAllCheckBox.setValue(false);
    }


    @Override
    protected void onAttach() {
        super.onAttach();
        this.period = this.parseMediator.calculatePeriod(this.iStrategyView.getExtentValues().get(PERIOD).toString());
        this.labelPeriod.setValue(this.parseMediator.getParsedPeriod());
        Map<TypeValueEnum, Object> m = this.iStrategyView.getExtentValues();
        this.labelRange.setValue(fmt.format((Date) m.get(DATE_FROM)).concat(DATE_SEPARATOR)
                .concat(fmt.format((Date) m.get(DATE_TO))));
        this.endDateMultifield.bindDate((Date) m.get(DATE_FROM), this.parseMediator.getNextDate((Date) m.get(DATE_TO)));
        this.startDateMultifield.bindDate((Date) m.get(DATE_FROM), this.parseMediator.getNextDate((Date) m.get(DATE_TO)));
    }

    @Override
    protected void onDetach() {
        WidgetPropertiesHandlerManager.fireEvent(new GPResetComponentsEvent());
        this.stopPlayer();
        super.onDetach();
        this.parseMediator.reset();
    }

}
