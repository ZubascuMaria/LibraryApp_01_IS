package service.employeeReport;

import model.EmployeeReport;
import repository.employeeReport.EmployeeReportRepository;

import java.util.List;

public class EmployeeReportServiceImpl implements EmployeeReportService{
    private final EmployeeReportRepository employeeReportRepository;

    public EmployeeReportServiceImpl(EmployeeReportRepository employeeReportRepository) {
        this.employeeReportRepository = employeeReportRepository;
    }

    @Override
    public List<EmployeeReport> findAll() {
        return employeeReportRepository.findAll();
    }

    @Override
    public boolean save(EmployeeReport employeeReport) {
        return employeeReportRepository.save(employeeReport);
    }
}
