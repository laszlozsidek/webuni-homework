package hu.webuni.hr.lzsidek.service;

import hu.webuni.hr.lzsidek.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {
    @Override
    public int getPayRaisePercent(Employee employee) {
        return 5;
    }
}