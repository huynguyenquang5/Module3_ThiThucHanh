package com.example.thithuchanh_module3.DAO;
import com.example.thithuchanh_module3.connection.MyConnection;
import com.example.thithuchanh_module3.model.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final Connection connection;
    private final String SELECT_ALL_DEPARTMENTS = "select * from department;";
    private final String SELECT_DEPARTMENT_BY_ID = "select * from department where id = ?;";

    public DepartmentDAO() {
        connection = MyConnection.getConnection();
    }

    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_ALL_DEPARTMENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public Department findDepartmentById(Long id) {
        try (PreparedStatement preparedStatement =
                connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long idDepartment = resultSet.getLong("id");
                String name = resultSet.getString("name");
                return new Department(idDepartment, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
