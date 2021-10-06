package hu.webuni.hr.lzsidek.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr.salary.years")
@Component
public class HrConfigProperties {

    private Double limit1;
    private Double limit2;
    private Double limit3;

    public Double getLimit1() {
        return limit1;
    }

    public void setLimit1(Double limit1) {
        this.limit1 = limit1;
    }

    public Double getLimit2() {
        return limit2;
    }

    public void setLimit2(Double limit2) {
        this.limit2 = limit2;
    }

    public Double getLimit3() {
        return limit3;
    }

    public void setLimit3(Double limit3) {
        this.limit3 = limit3;
    }
}
