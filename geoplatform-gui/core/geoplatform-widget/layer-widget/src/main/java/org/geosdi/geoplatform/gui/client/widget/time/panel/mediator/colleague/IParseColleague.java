package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague;

import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseColleagueKey;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;

import javax.inject.Inject;

/**
 * @author Vito Salvia- CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IParseColleague {

    /**
     * @return {@link IParseColleagueKey}
     */
    IParseColleagueKey getKey();

    /**
     * @param period
     * @return
     */
    Long execute(String period);

    abstract class AbstractColleague implements IParseColleague {

        protected IParseMediator mediator;

        public AbstractColleague(IParseMediator parseMediator) {
            parseMediator.registerColleague(this);
            this.mediator = parseMediator;
        }

        @Override
        public Long execute(String period) {
            //3Y1M1W1DT1H1M1S
            Long p = 0l;

            if (period.contains(getKey().getTimeLabel())) {
                String v = period.substring(0, period.indexOf(getKey().getTimeLabel()));
                p += Integer.parseInt(v) * getKey().getValue();
                this.mediator.setPeriodValue(period.substring(period.indexOf(getKey().getTimeLabel()) + 1));
                this.mediator.setParsedPeriod(v + " " + getKey().getLabel());
            }
            return p;
        }


    }


    class YearColleague extends AbstractColleague {

        @Inject
        public YearColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.YEAR;
        }

    }

    class MonthColleague extends AbstractColleague {

        @Inject
        public MonthColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.MONT;
        }

        @Override
        public Long execute(String period) {
            if (!this.mediator.isInitTime())
                return super.execute(period);
            return 0l;
        }
    }

    class WeekColleague extends AbstractColleague {

        @Inject
        public WeekColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.WEEK;
        }

    }

    class DayColleague extends AbstractColleague {

        @Inject
        public DayColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.DAY;
        }

    }

    class HourColleague extends AbstractColleague {


        @Inject
        public HourColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.HOUR;
        }


    }

    class MinuteColleague extends AbstractColleague {

        @Inject
        public MinuteColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.MINUTE;
        }

        @Override
        public Long execute(String period) {
            if (this.mediator.isInitTime())
                return super.execute(period);
            return 0l;
        }


    }

    class SecondColleague extends AbstractColleague {

        @Inject
        public SecondColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.SECOND;
        }


    }

}
