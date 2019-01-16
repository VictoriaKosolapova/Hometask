package com.netcracker.edu.db.employee.dao;

import com.netcracker.edu.db.employee.model.Employee;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * TODO: Implement using jdbc
 */

public class EmployeeDaoJdbcImpl implements EmployeeDao {


    public Employee getEmployeeById(BigInteger employeeId) {
        Employee employee = new Employee();
        try (PreparedStatement ps = DabaBaseConnection.CONNECTION.
                prepareStatement("SELECT * from employees WHERE ID = ?")){
            ps.setInt(1,employeeId.intValue());
            ps.execute();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            employee.setId(resultSet.getBigDecimal("id").toBigInteger());
            employee.setName(resultSet.getString("name"));
            employee.setSurname(resultSet.getString("surname"));
            employee.setDepartmentId(resultSet.getLong("departmentId"));
            employee.setPosition(resultSet.getString("position"));
            employee.setSalary(resultSet.getLong("salary"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public boolean addEmployee(Employee employee) {
        boolean result = false;
        try(PreparedStatement ps = DabaBaseConnection.CONNECTION
                .prepareStatement("INSERT INTO employees(name,surname,position,departmentId,salary) " +
                        "values(?,?,?,?,?)")){
            int i = 0;

            ps.setString(++i,employee.getName());
            ps.setString(++i,employee.getSurname());
            ps.setString(++i,employee.getPosition());
            ps.setLong(++i,employee.getDepartmentId());
            ps.setLong(++i,employee.getSalary());

            if(ps.executeUpdate()==1)
                result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public boolean updateEmployee(Employee employee) {
        boolean result = false;
        try(PreparedStatement ps = DabaBaseConnection.CONNECTION
                .prepareStatement("UPDATE employees SET salary = ? WHERE id = ?")){

          ps.setInt(1,400);
          ps.setInt(2,employee.getId().intValue());
          ps.executeUpdate();
          if(ps.executeUpdate()==1)
                result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public boolean deleteEmployee(Employee employee) {
        return false;
    }

    public List<Employee> getEmployeesBySurname(String surname) {

        return null;
    }

    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        return null;
    }

    public List<Employee> getEmployeesWithGreaterSalary(long thresholdSalary) {
        return null;
    }

    public List<Employee> getAllEmployees() {
        return null;
    }
}
