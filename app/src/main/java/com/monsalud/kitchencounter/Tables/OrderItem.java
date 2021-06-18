package com.monsalud.kitchencounter.Tables;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Entity.Employee;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Vendor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "orderitem_table",
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
            @ForeignKey(
                    entity = Vendor.class,
                    parentColumns = "vendor_id",
                    childColumns = "vendor_id_fk",
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.SET_NULL),
    }
)

public class OrderItem {
    @PrimaryKey(autoGenerate = true)
    private int orderitem_id;

    @ColumnInfo(name = "product_id_fk")
    @NonNull
    private int product_id_fk;

    @ColumnInfo(name = "employee_id_fk")
    private int employee_id_fk;

    @ColumnInfo(name = "vendor_id_fk")
    private int vendor_id_fk;

    @ColumnInfo(name = "vendor_name")
    private String vendor_name;

    @ColumnInfo(name = "delivery_date")
    @TypeConverters(Converters.class)
    @Nullable
    private LocalDate delivery_date;

    @ColumnInfo(name = "order_unit")
    @TypeConverters(Converters.class)
    private InventoryItem.InventoryUnit order_unit;

    @ColumnInfo(name = "order_amount")
    private double order_amount;

    @ColumnInfo(name = "active")
    private boolean active;

    @ColumnInfo(name = "placed")
    private boolean placed;


    //Constructor
    public OrderItem(int orderitem_id,
                     int product_id_fk,
                     int employee_id_fk,
                     int vendor_id_fk,
                     String vendor_name,
                     LocalDate delivery_date,
                     InventoryItem.InventoryUnit order_unit,
                     double order_amount,
                     boolean active,
                     boolean placed) {
        this.orderitem_id = orderitem_id;
        this.product_id_fk = product_id_fk;
        this.employee_id_fk = employee_id_fk;
        this.vendor_id_fk = vendor_id_fk;
        this.vendor_name = vendor_name;
        this.delivery_date = delivery_date;
        this.order_unit = order_unit;
        this.order_amount = order_amount;
        this.active = active;
        this.placed = placed;
    }

    //Methods

    public int getOrderitem_id() {
        return orderitem_id;
    }

    public void setOrderitem_id(int orderitem_id) {
        this.orderitem_id = orderitem_id;
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

    public int getVendor_id_fk() {
        return vendor_id_fk;
    }

    public void setVendor_id_fk(int vendor_id_fk) {
        this.vendor_id_fk = vendor_id_fk;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }

    public InventoryItem.InventoryUnit getOrder_unit() {
        return order_unit;
    }

    public void setOrder_unit(InventoryItem.InventoryUnit order_unit) {
        this.order_unit = order_unit;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

