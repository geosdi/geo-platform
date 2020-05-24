package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague;

import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseColleagueKey;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;

import javax.inject.Inject;

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

        private IParseMediator mediator;

        public AbstractColleague(IParseMediator parseMediator) {
            parseMediator.registerColleague(this);
            this.mediator = parseMediator;
        }

        @Override
        public Long execute(String period) {
            //3Y1M1W1DT1H1M1S
            Long p = 0l;
            if (period.contains(getKey().getTimeLabel())) {
                p += Integer.parseInt(period.substring(getStartIndex(), period.indexOf(getKey().getTimeLabel()))) * getKey().getValue();
                this.mediator.setPeriodValue(period.substring(period.indexOf(getKey().getTimeLabel()) + getStartIndex() + 1));
            }
            return p;
        }

        protected int getStartIndex() {
            return 0;
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
            if (!period.contains("T")) {
                return super.execute(period);
            } else if (period.contains("T") && period.indexOf("T") > period.indexOf("M")) {
                return super.execute(period);
            }
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

        protected int getStartIndex() {
            return 1;
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
            if (period.contains("T") && period.indexOf("T") < period.lastIndexOf("M")) {
                return super.execute(period);
            }
            return 0l;
        }

        protected int getStartIndex() {
            return 1;
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

        protected int getStartIndex() {
            return 1;
        }

    }

}
