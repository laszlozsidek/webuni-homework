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
        if (monthDiff >= 0 && monthDiff < MONTHS * config.getYears().getYear1() || (monthDiff == MONTHS * config.getYears().getYear1() && dayDiff <= 0))
            return config.getPercent().getPercent1();
        if ((monthDiff >= MONTHS * config.getYears().getYear1() && monthDiff < MONTHS * config.getYears().getYear2()) || (monthDiff == MONTHS * config.getYears().getYear2() && dayDiff <= 0))
            return config.getPercent().getPercent2();
        if ((monthDiff >= MONTHS * config.getYears().getYear2() && monthDiff < MONTHS * config.getYears().getYear3()) || (monthDiff == MONTHS * config.getYears().getYear3() && dayDiff <= 0))
            return config.getPercent().getPercent3();
        if (monthDiff >= MONTHS * config.getYears().getYear3())
            return config.getPercent().getPercent4();
        return -1;
    }
}
