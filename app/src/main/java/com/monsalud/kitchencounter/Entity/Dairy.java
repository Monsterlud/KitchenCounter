package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;


@Entity(tableName = "dairy_table")
public class Dairy extends Product {

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    public int dairy_id;

    @ColumnInfo(name = "dairy_type")
    @TypeConverters(Converters.class)
    public DairyType dairy_type;

    //Constructor
    public Dairy(int product_id, String product_name, Product.ProductType product_type, DairyType dairy_type, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.dairy_type = dairy_type;
    }

    //Methods

//    public int getDairy_id() {
//        return dairy_id;
//    }
//
//    public void setDairy_id(int dairy_id) {
//        this.dairy_id = dairy_id;
//    }

    public int getDairy_type() {
        if(dairy_type == DairyType.MILKANDCREAM) return 0;
        else if(dairy_type == DairyType.CHEESE) return 1;
        else if(dairy_type == DairyType.CULTURED) return 2;
        else if(dairy_type == DairyType.EGGS) return 3;
        else return 4;
    }

    public void setDairy_type(DairyType dairy_type) {
        this.dairy_type = dairy_type;
    }

    //Enum
    public static enum DairyType {MILKANDCREAM, CHEESE, CULTURED, EGGS, OTHER}
}
