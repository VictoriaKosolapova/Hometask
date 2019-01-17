package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Implement using jdbc
 */

public class EmployeeDaoJdbcImpl implements EmployeeDao {


    public Employee getEmployeeById(BigInteger employeeId) {
        Employee employee = null;
        try (ResultSet employeeResultSet = DabaBaseConnection.CONNECTION.createStatement().
                executeQuery("SELECT * FROM employees WHERE id =" +employeeId)) {
            while (employeeResultSet.next()){
                employee = setEmployee(employeeResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public boolean addEmployee(Employee employee) {
        boolean result = false;
        try (PreparedStatement ps = DabaBaseConnection.CONNECTION
                .prepareStatement("INSERT INTO employees(name,surname,position,departmentId,salary) " +
                        "values(?,?,?,?,?)")) {
            int i = 0;

            ps.setString(++i, employee.getName());
            ps.setString(++i, employee.getSurname());
            ps.setString(++i, employee.getPosition());
            ps.setLong(++i, employee.getDepartmentId());
            ps.setLong(++i, employee.getSalary());

            if (ps.executeUpdate() == 1)
                result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        try (PreparedStatement ps = DabaBaseConnection.CONNECTION
                .prepareStatement("UPDATE employees SET salary = ? WHERE id = ?")) {

            ps.setInt(1, 400);
            ps.setInt(2, employee.getId().intValue());
            ps.executeUpdate();
            if (ps.executeUpdate() == 1)
                result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean deleteEmployee(Employee employee) {
        boolean result = false;
        try (PreparedStatement ps = DabaBaseConnection.CONNECTION
                .prepareStatement("DELETE FROM employees WHERE id = ?")) {
            ps.setInt(1, employee.getId().intValue());
            ps.executeUpdate();
            if (ps.executeUpdate() == 1)
                result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<Employee> getEmployeesBySurname(String surname) {
        List<Employee> surnameList = new ArrayList<>();
        try (ResultSet surnameResultSet = DabaBaseConnection.CONNECTION.createStatement().
                executeQuery("SELECT * FROM employees WHERE surname =" + "'"+surname+"'")) {
            while (surnameResultSet.next()) {
                surnameList.add(setEmployee(surnameResultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return surnameList;
    }

    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        List<Employee> departmentIdeList = new ArrayList<>();
        try (ResultSet surnameResultSet = DabaBaseConnection.CONNECTION.createStatement().
                executeQuery("SELECT * FROM employees WHERE departmentId =" + departmentId)) {
            while (surnameResultSet.next()) {
                departmentIdeList.add(setEmployee(surnameResultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return departmentIdeList;

    }

    public List<Employee> getEmployeesWithGreaterSalary(long thresholdSalary) {
        List<Employee> greaterSalaryList = new ArrayList<>();
        try (ResultSet salaryResultSet = DabaBaseConnection.CONNECTION.createStatement().
                executeQuery("SELECT * FROM employees WHERE salary >" + thresholdSalary)) {
            while (salaryResultSet.next()) {
                greaterSalaryList.add(setEmployee(salaryResultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return greaterSalaryList;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> allEmployeesList = new ArrayList<>();
        try (ResultSet resultSet = DabaBaseConnection.CONNECTION.createStatement().
                executeQuery("SELECT * FROM employees")) {
            while (resultSet.next()) {
                allEmployeesList.add(setEmployee(resultSet));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allEmployeesList;
    }

    public Employee setEmployee(ResultSet resultSet) {
        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getBigDecimal("id").toBigInteger());
            employee.setName(resultSet.getString("name"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setDepartmentId(resultSet.getLong("departmentId"));
            employee.setPosition(resultSet.getString("position"));
            employee.setSalary(resultSet.getLong("salary"));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return employee;
    }
}
