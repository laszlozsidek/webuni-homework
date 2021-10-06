package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.config.HrConfigProperties;
import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SmartEmployeeService implements EmployeeService {
    private static final int MONTHS = 12;
    
    @Autowired
    HrConfigProperties config;
    
    @Override
    public int getPayRaisePercent(Employee employee) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime past = employee.getStartDateTimeOfWork();

        int monthDiff = (now.getYear() - past.getYear()) * MONTHS + now.getMonthValue() - past.getMonthValue();
        int dayDiff = now.getDayOfMonth() - past.getDayOfMonth();

        if ((monthDiff == 0 && dayDiff < 0) || monthDiff < 0)
            throw new IllegalArgumentException("Invalid StartDateTimeOfWork date");
        if (monthDiff >= 0 && monthDiff < MONTHS * config.getLimit1() || (monthDiff == MONTHS * config.getLimit1() && dayDiff <= 0))
            return 0;
        if ((monthDiff >= MONTHS * config.getLimit1() && monthDiff < MONTHS * config.getLimit2()) || (monthDiff == MONTHS * config.getLimit2() && dayDiff <= 0))
            return 2;
        if ((monthDiff >= MONTHS * config.getLimit2() && monthDiff < MONTHS * config.getLimit3()) || (monthDiff == MONTHS * config.getLimit3() && dayDiff <= 0))
            return 5;
        if (monthDiff >= MONTHS * config.getLimit3())
            return 10;
        return -1;
    }
}
