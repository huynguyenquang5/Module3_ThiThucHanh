package com.example.thithuchanh_module3.Service;
import com.example.thithuchanh_module3.DAO.DepartmentDAO;
import com.example.thithuchanh_module3.DAO.EmployeeDAO;
import com.example.thithuchanh_module3.model.Department;
import com.example.thithuchanh_module3.model.Employee;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
        departmentDAO = new DepartmentDAO();
    }

    public List<Employee> findAll(HttpServletRequest request) {
        return employeeDAO.findAll();
    }

    public List<Department> findDepartment() {
        return departmentDAO.findAll();
    }
    public Employee findById(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        return employeeDAO.findEmployeeById(id);
    }

    public boolean save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("departments"));
        if (id == null) {
            return employeeDAO.addEmployee(new Employee(name, email, address, phoneNumber, salary,
                    departmentDAO.findDepartmentById(departmentId)));
        } else {
            return employeeDAO.editEmployee(
                    new Employee(Long.parseLong(id), name, email, address, phoneNumber, salary,
                            departmentDAO.findDepartmentById(departmentId)));
        }
    }

    public boolean deleteById(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        return employeeDAO.deleteEmployee(id);
    }

    public List<Employee> findByNameContaining(HttpServletRequest request) throws SQLException {
        String name = request.getParameter("search");
        return employeeDAO.findAllByNameContaining(name);
    }
}
