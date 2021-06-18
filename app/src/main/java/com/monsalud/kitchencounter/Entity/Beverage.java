package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "beverage_table")
public class Beverage extends Product{

    //Columns (Fields)
//    @ColumnInfo(name = "beverage_id")
//    public int beverage_id;

    @ColumnInfo(name = "beverage_type")
    @TypeConverters(Converters.class)
    public BeverageType beverage_type;

    //Constructor
    public Beverage(int product_id, String product_name, Product.ProductType product_type, BeverageType beverage_type, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.beverage_type = beverage_type;
    }

    //Methods
    public int getBeverage_type() {
        if(beverage_type == BeverageType.COFFEE) return 0;
        else if(beverage_type == BeverageType.TEA) return 1;
        else if(beverage_type == BeverageType.SOFTDRINKS) return 2;
        else if(beverage_type == BeverageType.WATER) return 3;
        else if(beverage_type == BeverageType.JUICE) return 4;
        else return 5;
    }

    public void setBeverage_type(BeverageType beverage_type) {
        this.beverage_type = beverage_type;
    }

    //Enum
    public static enum BeverageType {COFFEE, TEA, SOFTDRINKS, WATER, JUICE, OTHER}
}
