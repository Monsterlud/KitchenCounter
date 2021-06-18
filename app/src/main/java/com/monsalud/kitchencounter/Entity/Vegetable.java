package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "vegetable_table")
public class Vegetable extends Product{

    //Columns (Fields)
    @ColumnInfo(name = "vegvarietal_name")
    private String vegvarietal_name;

    //Constructor
    public Vegetable(int product_id, String product_name, Product.ProductType product_type, String vegvarietal_name, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.vegvarietal_name = vegvarietal_name;
    }

    //Methods

//    public int getVegetable_id() {
//        return vegetable_id;
//    }
//
//    public void setVegetable_id(int vegetable_id) {
//        this.vegetable_id = vegetable_id;
//    }

    public String getVegvarietal_name() {
        return vegvarietal_name;
    }

    public void setVegvarietal_name(String vegvarietal_name) {
        this.vegvarietal_name = vegvarietal_name;
    }
}


