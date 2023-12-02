package model.builder;

import model.EmployeeReport;

public class EmployeeReportBuilder {
    public EmployeeReport employeeReport;
    public EmployeeReportBuilder(){
        employeeReport = new EmployeeReport();
    }
    public EmployeeReportBuilder setId(Long id){
        employeeReport.setId(id);
        return this;
    }
    public EmployeeReportBuilder setDescription(String description){
        employeeReport.setDescription(description);
        return this;
    }
    public EmployeeReport build(){
        return employeeReport;
    }
}
