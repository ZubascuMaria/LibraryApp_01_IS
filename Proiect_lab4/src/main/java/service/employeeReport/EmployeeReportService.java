package service.employeeReport;

import model.EmployeeReport;

import java.util.List;

public interface EmployeeReportService {
    List<EmployeeReport> findAll();
    boolean save(EmployeeReport employeeReport);
}
