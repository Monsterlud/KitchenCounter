package com.monsalud.kitchencounter.Tables;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Entity.Employee;
import com.monsalud.kitchencounter.Entity.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "requestitem_table",
    foreignKeys = {
            @ForeignKey(
                    entity = Product.class,
                    parentColumns = "product_id",
                    childColumns = "product_id_fk",
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.SET_NULL),
            @ForeignKey(
                    entity = Employee.class,
                    parentColumns = "employee_id",
                    childColumns = "employee_id_fk",
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.SET_NULL),
    }
)

public class RequestItem {

    //Columns (Fields)
    @PrimaryKey(autoGenerate = true)
    private int requestitem_id;

    @ColumnInfo(name = "product_id_fk")
    @Nullable
    private int product_id_fk;

    @ColumnInfo(name = "employee_id_fk")
    @Nullable
    private int employee_id_fk;

    @ColumnInfo(name = "request_amount")
    private double request_amount;

    @ColumnInfo(name = "submitted")
    private boolean submitted;

    @ColumnInfo(name = "active")
    private boolean active;

    //Constructor
    public RequestItem(int requestitem_id,
                       int product_id_fk,
                       int employee_id_fk,
                       double request_amount,
                       boolean submitted,
                       boolean active) {
        this.requestitem_id = requestitem_id;
        this.product_id_fk = product_id_fk;
        this.employee_id_fk = employee_id_fk;
        this.request_amount = request_amount;
        this.submitted = submitted;
        this.active = active;
    }

    //Methods

    public int getRequestitem_id() {
        return requestitem_id;
    }

    public void setRequestitem_id(int requestitem_id) {
        this.requestitem_id = requestitem_id;
    }

    public int getProduct_id_fk() {
        return product_id_fk;
    }

    public void setProduct_id_fk(int product_id_fk) {
        this.product_id_fk = product_id_fk;
    }

    public int getEmployee_id_fk() {
        return employee_id_fk;
    }

    public void setEmployee_id_fk(int employee_id_fk) {
        this.employee_id_fk = employee_id_fk;
    }

    public double getRequest_amount() {
        return request_amount;
    }

    public void setRequest_amount(double request_amount) {
        this.request_amount = request_amount;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

