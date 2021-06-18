package com.monsalud.kitchencounter.Tables;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Ignore;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Database.KitchenCounterDatabase;
import com.monsalud.kitchencounter.Entity.Product;

import java.time.LocalDate;
import java.util.List;

@DatabaseView("SELECT product_table.product_id, product_table.product_name, product_table.product_description, product_table.product_type, product_table.default_unit, " +
        "invprodview.inventory_amount, reqprodview.request_amount, " +
        "orderitem_table.orderitem_id, orderitem_table.order_amount, orderitem_table.order_unit, orderitem_table.delivery_date, " +
        "orderitem_table.active, orderitem_table.placed, orderitem_table.vendor_id_fk, orderitem_table.vendor_name " +
        "FROM product_table " +
        "LEFT JOIN invprodview ON product_table.product_id = invprodview.product_id " +
        "LEFT JOIN reqprodview ON product_table.product_id = reqprodview.product_id " +
        "LEFT JOIN orderitem_table ON product_table.product_id = orderitem_table.product_id_fk " +
        "GROUP BY product_table.product_id;")


public class OrderView {

    @ColumnInfo(name = "product_id")
    public int product_id;

    @ColumnInfo(name = "product_name")
    public String product_name;

    @ColumnInfo(name = "product_description")
    public String product_description;

    @ColumnInfo(name = "product_type")
    @TypeConverters(Converters.class)
    public Product.ProductType product_type;

    @ColumnInfo(name = "inventory_amount")
    public double inventory_amount;

    @ColumnInfo(name = "default_unit")
    @TypeConverters(Converters.class)
    public InventoryItem.InventoryUnit default_unit;

    @ColumnInfo(name = "request_amount")
    public double request_amount;

    @ColumnInfo(name = "order_amount")
    public double order_amount;

    @ColumnInfo(name = "order_unit")
    @TypeConverters(Converters.class)
    public InventoryItem.InventoryUnit order_unit;

    @ColumnInfo(name = "vendor_id_fk")
    public int vendor_id_fk;

    @ColumnInfo(name = "orderitem_id")
    public int orderitem_id;

    @ColumnInfo(name = "vendor_name")
    public String vendor_name;

    @Nullable
    @ColumnInfo(name = "delivery_date")
    @TypeConverters(Converters.class)
    public LocalDate delivery_date;

    @ColumnInfo(name = "active")
    @Nullable
    public boolean active;

    @ColumnInfo(name = "placed")
    @Nullable
    public boolean placed;

    @ColumnInfo(name = "vendor_phone")
    public String vendor_phone;

    @ColumnInfo(name = "vendor_email")
    public String vendor_email;

    //Methods

    public int getProduct_id() {
        return product_id;
    }

    public int getVendor_id_fk() {
        return vendor_id_fk;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Product.ProductType getProduct_type() {
        return product_type;
    }

    public double getInventory_amount() {
        return inventory_amount;
    }

    public InventoryItem.InventoryUnit getDefault_unit() {
        return default_unit;
    }

    public double getRequest_amount() {
        return request_amount;
    }

    public double getOrder_amount() {
        return order_amount;
    }
    public InventoryItem.InventoryUnit getOrder_unit() {
        if(order_unit != null)
            return order_unit;
        else return InventoryItem.InventoryUnit.EACH;
    }
    public String getVendor_name() {
        return vendor_name;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPlaced() {
        return placed;
    }

    public String getVendor_phone() {
        return vendor_phone;
    }

    public String getVendor_email() {
        return vendor_email;
    }

    public int getOrderitem_id() {
        return orderitem_id;
    }

    @Nullable
    public LocalDate getDelivery_date() {
        return delivery_date;
    }
}
