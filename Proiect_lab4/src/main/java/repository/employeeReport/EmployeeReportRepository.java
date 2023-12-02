package repository.employeeReport;

import model.EmployeeReport;

import java.util.List;

public interface EmployeeReportRepository {
    List<EmployeeReport> findAll();
    boolean save(EmployeeReport employeeReport);
}
