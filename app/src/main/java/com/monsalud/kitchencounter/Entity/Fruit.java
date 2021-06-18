package com.monsalud.kitchencounter.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "fruit_table")
public class Fruit extends Product{

    //Columns (Fields)
    @ColumnInfo(name = "fruitvarietal_name")
    private String fruitvarietal_name;

    //Constructor
    public Fruit(int product_id, String product_name, Product.ProductType product_type, @Nullable String fruitvarietal_name, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.fruitvarietal_name = fruitvarietal_name;
    }

    //Methods
    public String getFruitvarietal_name() {
        return fruitvarietal_name;
    }

    public void setFruitvarietal_name(String fruitvarietal_name) {
        this.fruitvarietal_name = fruitvarietal_name;
    }
}

