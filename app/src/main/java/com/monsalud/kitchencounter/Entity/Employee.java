package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;

@Entity(tableName = "employee_table")
public class Employee {

    //Columns (Fields)
    @PrimaryKey
    private int employee_id;

    @ColumnInfo(name = "employee_firstname")
    private String employee_firstname;

    @ColumnInfo(name = "employee_lastname")
    private String employee_lastname;

    @ColumnInfo(name = "employee_phone_mobile")
    private String employee_phone_mobile;

    @ColumnInfo(name = "employee_phone_home")
    private String employee_phone_home;

    @ColumnInfo(name = "employee_email")
    private String employee_email;

    @ColumnInfo(name = "employee_role")
    @TypeConverters(Converters.class)
    public EmployeeRole employee_role;

    //Constructor


    public Employee(int employee_id,
                    String employee_firstname,
                    String employee_lastname,
                    String employee_phone_mobile,
                    String employee_phone_home,
                    String employee_email,
                    EmployeeRole employee_role) {
        this.employee_id = employee_id;
        this.employee_firstname = employee_firstname;
        this.employee_lastname = employee_lastname;
        this.employee_phone_mobile = employee_phone_mobile;
        this.employee_phone_home = employee_phone_home;
        this.employee_email = employee_email;
        this.employee_role = employee_role;
    }

    //Methods
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_firstname() {
        return employee_firstname;
    }

    public void setEmployee_firstname(String employee_name) {
        this.employee_firstname = employee_name;
    }

    public String getEmployee_lastname() {
        return employee_lastname;
    }

    public void setEmployee_lastname(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }

    public String getEmployee_phone_mobile() {
        return employee_phone_mobile;
    }

    public void setEmployee_phone_mobile(String employee_phone_mobile) {
        this.employee_phone_mobile = employee_phone_mobile;
    }

    public String getEmployee_phone_home() {
        return employee_phone_home;
    }

    public void setEmployee_phone_home(String employee_phone_home) {
        this.employee_phone_home = employee_phone_home;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public EmployeeRole getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(EmployeeRole employee_role) {
        this.employee_role = employee_role;
    }

    //Enum
    public static enum EmployeeRole {CULINARY, CASHIER, CHEF}

}
