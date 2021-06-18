package com.monsalud.kitchencounter.Tables;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

@DatabaseView("SELECT product_table.product_id, product_table.product_name, product_table.product_description, " +
        "SUM(inventoryitem_table.inventory_amount) AS inventory_amount " +
        "FROM product_table LEFT JOIN inventoryitem_table " +
        "ON product_table.product_id = inventoryitem_table.product_id_fk " +
        "AND (inventoryitem_table.active = 1 AND inventoryitem_table.submitted = 1)" +
        "GROUP BY product_table.product_id;")

public class InvProdView {

    @ColumnInfo(name = "product_id")
    public int product_id;

    @ColumnInfo(name = "product_name")
    public String product_name;

    @ColumnInfo(name = "product_description")
    public String product_description;

    @ColumnInfo(name = "inventory_amount")
    public double inventory_amount;

    //getters
    public int getProduct_id() {
        return product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public String getProduct_description() {
        return product_description;
    }
    public double getInventory_amount() {
        return inventory_amount;
    }
}
