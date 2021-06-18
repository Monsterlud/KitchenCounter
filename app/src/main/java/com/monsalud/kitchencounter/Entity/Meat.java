package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "meat_table")
public class Meat extends Product{

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    private int meat_id;

    @ColumnInfo(name = "meat_type")
    @TypeConverters(Converters.class)
    public MeatType meat_type;

    //Constructor
    public Meat(int product_id, String product_name, Product.ProductType product_type, MeatType meat_type, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);

        this.meat_type = meat_type;
    }

    //Methods

//    public int getMeat_id() {
//        return meat_id;
//    }
//
//    public void setMeat_id(int meat_id) {
//        this.meat_id = meat_id;
//    }

    public int getMeat_type() {
        if(meat_type == MeatType.BEEF) return 0;
        else if(meat_type == MeatType.PORK) return 1;
        else if(meat_type == MeatType.LAMB) return 2;
        else if(meat_type == MeatType.GOAT) return 3;
        else if(meat_type == MeatType.POULTRY) return 4;
        else if(meat_type == MeatType.WILDGAME) return 5;
        else return 6;
    }

    public void setMeat_type(MeatType meat_type) {
        this.meat_type = meat_type;
    }

    //Enum
    public static enum MeatType {BEEF, PORK, LAMB, GOAT, POULTRY, WILDGAME, OTHER}
}
