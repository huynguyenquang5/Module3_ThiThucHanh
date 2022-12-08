package com.example.thithuchanh_module3.DAO;

import com.example.thithuchanh_module3.connection.MyConnection;
import com.example.thithuchanh_module3.model.Department;
import com.example.thithuchanh_module3.model.Employee;

import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final Connection connection;
    private final DepartmentDAO departmentDAO;
    private final String SELECT_ALL_EMPLOYEE = "select * from employee;";
    private final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id = ?;";
    private final String INSERT_EMPLOYEE =
            "insert into employee(name, email, address, phoneNumber, salary, departmentId) value (?,?,?,?,?,?);";
    private final String UPDATE_EMPLOYEE =
            "update employee set name = ?, email = ?, address = ?, phoneNumber = ?, salary = ?, departmentId = ? where id = ?;";
    private final String DELETE_EMPLOYEE = "delete from employee where id = ?;";
    private final String SELECT_EMPLOYEE_BY_NAME = "select * from employee where name like concat('%',?,'%');";
    public EmployeeDAO() {
        connection = MyConnection.getConnection();
        departmentDAO = new DepartmentDAO();
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_EMPLOYEE)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = Long.parseLong(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Double salary = Double.parseDouble(resultSet.getString("salary"));
                Long departmentId = resultSet.getLong("departmentId");
                Department department = departmentDAO.findDepartmentById(departmentId);
                employees.add(new Employee(id, name, email, address, phoneNumber, salary, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee findEmployeeById(Long id) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Double salary = Double.parseDouble(resultSet.getString("salary"));
                Long departmentId = resultSet.getLong("departmentId");
                Department department = departmentDAO.findDepartmentById(departmentId);
                return new Employee(id, name, email, address, phoneNumber, salary, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setLong(6, employee.getDepartment().getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setLong(6, employee.getDepartment().getId());
            preparedStatement.setLong(7, employee.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmployee(Long id) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Employee> findAllByNameContaining(String nameSearch) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_EMPLOYEE_BY_NAME)) {
            preparedStatement.setString(1, nameSearch);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = Long.parseLong(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                Double salary = Double.parseDouble(resultSet.getString("salary"));
                Long departmentId = resultSet.getLong("departmentId");
                Department department = departmentDAO.findDepartmentById(departmentId);
                employees.add(new Employee(id, name, email, address, phoneNumber, salary, department));
            }
        }
        return employees;
    }
}
