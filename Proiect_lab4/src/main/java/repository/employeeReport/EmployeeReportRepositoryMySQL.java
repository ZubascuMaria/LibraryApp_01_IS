package repository.employeeReport;

import model.EmployeeReport;
import model.builder.EmployeeReportBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReportRepositoryMySQL implements EmployeeReportRepository{
    private final Connection connection;

    public EmployeeReportRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<EmployeeReport> findAll() {
        String sql = "SELECT * FROM employee_report;";

        List<EmployeeReport>employeeReports = new ArrayList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                employeeReports.add(getEmployeeReportFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeReports;
    }

    @Override
    public boolean save(EmployeeReport employeeReport) {
        String sql = "INSERT INTO employee_report VALUES(null,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeReport.getDescription());

            int rowsInserted = preparedStatement.executeUpdate();

            return (rowsInserted != 1) ? false : true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private EmployeeReport getEmployeeReportFromResultSet(ResultSet resultSet) throws SQLException
    {
        return new EmployeeReportBuilder()
                .setId(resultSet.getLong("id"))
                .setDescription(resultSet.getString("description"))
                .build();
    }
}
