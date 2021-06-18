package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Query("SELECT * FROM employee_table")
    List<Employee> getAllEmployees();

    @Query("SELECT * FROM employee_table WHERE employee_id = :employeeID")
    Employee getEmployee(int employeeID);

    @Insert
    void insertEmployee(Employee employee);

    @Insert
    void insertAllEmployees(Employee... employee);

    @Update
    void updateEmployee(Employee employee);

    @Query("DELETE FROM employee_table WHERE employee_id = :employeeID")
    void deleteEmployee(int employeeID);

    @Query("DELETE FROM employee_table")
    public void deleteAllEmployees();


}
