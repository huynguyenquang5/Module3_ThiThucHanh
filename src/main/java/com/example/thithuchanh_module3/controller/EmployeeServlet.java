package com.example.thithuchanh_module3.controller;

import com.example.thithuchanh_module3.Service.EmployeeService;
import com.example.thithuchanh_module3.model.Department;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                createForm(request,response);
                break;
            case "edit":
                editForm(request,response);
                break;
            case "delete":
                delete(request, response);
                break;
            default: displayListEmployee(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "search":
                try {
                    displaySearchEmployeeList(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void displayListEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
        request.setAttribute("employee", employeeService.findAll(request));
        requestDispatcher.forward(request,response);
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = employeeService.findDepartment();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("addEmployee.jsp");
        request.setAttribute("departments", departments);
        requestDispatcher.forward(request,response);
    }
    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("editEmployee.jsp");
        request.setAttribute("employee", employeeService.findById(request));
        request.setAttribute("departments", employeeService.findDepartment());
        requestDispatcher.forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (employeeService.save(request)) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Create successfully!");
        }
        response.sendRedirect("http://localhost:8080/EmployeeServlet");
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (employeeService.save(request)) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Edit successfully!");
        }
        response.sendRedirect("http://localhost:8080/EmployeeServlet");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (employeeService.deleteById(request)) {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Delete successfully!");
        }
        response.sendRedirect("http://localhost:8080/EmployeeServlet");
    }

    private void displaySearchEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listEmployee.jsp");
        request.setAttribute("employee", employeeService.findByNameContaining(request));
        requestDispatcher.forward(request, response);
    }
}
