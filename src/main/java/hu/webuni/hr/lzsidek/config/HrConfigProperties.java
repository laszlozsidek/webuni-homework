package hu.webuni.hr.lzsidek.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr.salary")
@Component
public class HrConfigProperties {

    private Years years = new Years();
    private Percent percent = new Percent();

    public class Years {
        private Double year1;
        private Double year2;
        private Double year3;

        public Double getYear1() {
            return year1;
        }

        public void setYear1(Double year1) {
            this.year1 = year1;
        }

        public Double getYear2() {
            return year2;
        }

        public void setYear2(Double year2) {
            this.year2 = year2;
        }

        public Double getYear3() {
            return year3;
        }

        public void setYear3(Double year3) {
            this.year3 = year3;
        }
    }

    public Years getYears() {
        return years;
    }

    public void setYears(Years years) {
        this.years = years;
    }

    public Percent getPercent() {
        return percent;
    }

    public void setPercent(Percent percent) {
        this.percent = percent;
    }

    public class Percent {
        private int percent1;
        private int percent2;
        private int percent3;
        private int percent4;

        public int getPercent1() {
            return percent1;
        }

        public void setPercent1(int percent1) {
            this.percent1 = percent1;
        }

        public int getPercent2() {
            return percent2;
        }

        public void setPercent2(int percent2) {
            this.percent2 = percent2;
        }

        public int getPercent3() {
            return percent3;
        }

        public void setPercent3(int percent3) {
            this.percent3 = percent3;
        }

        public int getPercent4() {
            return percent4;
        }

        public void setPercent4(int percent4) {
            this.percent4 = percent4;
        }
    }
}
