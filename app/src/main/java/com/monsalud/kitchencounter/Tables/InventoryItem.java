package com.monsalud.kitchencounter.Tables;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Entity.Employee;
import com.monsalud.kitchencounter.Entity.Fruit;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Vegetable;
import com.monsalud.kitchencounter.Entity.Vendor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "inventoryitem_table",
    foreignKeys = {
            @ForeignKey(
                    entity = Product.class,
                    parentColumns = "product_id",
                    childColumns = "product_id_fk",
                    onUpdate = ForeignKey.CASCADE),
            @ForeignKey(
                    entity = Employee.class,
                    parentColumns = "employee_id",
                    childColumns = "employee_id_fk",
                    onUpdate = ForeignKey.CASCADE)
    })

public class InventoryItem {

    //Columns (Fields)
    @PrimaryKey(autoGenerate = true)
    private int inventoryitem_id;

    @ColumnInfo(name = "product_id_fk")
    @Nullable
    private int product_id_fk;

    @ColumnInfo(name = "employee_id_fk")
    @Nullable
    private int employee_id_fk;

    @ColumnInfo(name = "inventory_date")
    @TypeConverters(Converters.class)
    private LocalDate inventory_date;

    @ColumnInfo(name = "inventory_amount")
    private double inventory_amount;

    @ColumnInfo(name = "location")
    @TypeConverters(Converters.class)
    @Nullable
    private InventoryLocation location;

    @ColumnInfo(name = "submitted")
    private boolean submitted;

    @ColumnInfo(name = "active")
    private boolean active;

    //Constructor
    public InventoryItem(int inventoryitem_id,
                         int product_id_fk,
                         int employee_id_fk,
                         LocalDate inventory_date,
                         double inventory_amount,
                         InventoryLocation location,
                         boolean submitted,
                         boolean active) {
        this.inventoryitem_id = inventoryitem_id;
        this.product_id_fk = product_id_fk;
        this.employee_id_fk = employee_id_fk;
        this.inventory_date = inventory_date;
        this.inventory_amount = inventory_amount;
        this.location = location;
        this.submitted = submitted;
        this.active = active;
    }

    //Methods
    public int getInventoryitem_id() {
        return inventoryitem_id;
    }

    public void setInventoryitem_id(int inventory_id) {
        this.inventoryitem_id = inventory_id;
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

    public void setInventory_date(LocalDate inventory_date) {
        this.inventory_date = inventory_date;
    }

    public LocalDate getInventory_date() {
        return inventory_date;
    }

    public double getInventory_amount() {
        return inventory_amount;
    }

    public void setInventory_amount(double inventory_amount) {
        this.inventory_amount = inventory_amount;
    }

    public InventoryLocation getLocation() {
        return location;
    }

    public void setLocation(InventoryLocation location) {
        this.location = location;
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

    public void setActive(boolean submitted) {
        this.active = active;
    }

    //Enums
    public static enum InventoryUnit {EACH, BUNCH, CASE, KG, LB, OZ, DOZEN}

    public static enum InventoryLocation {CENTRALWALKIN_SHELF_A, CENTRALWALKIN_SHELF_B, CENTRALWALKIN_SHELF_C,
        CENTRALWALKIN_SHELF_D, CENTRALWALKIN_SHELF_E, CENTRALWALKIN_SHELF_F, CENTRALDRYGOODS, CENTRALHOTLINE,
        CENTRALCASHIER, PLAZAWALKIN, PLAZADRYGOODS, PLAZAFREEZER, BROWNBAGMAIN,
        BROWNBAGSATELLITE, UNDERTHESTAIRS, LOADINGDOCK, OTHER}
}
