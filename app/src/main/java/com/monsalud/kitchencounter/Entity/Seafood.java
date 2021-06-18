package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "seafood_table")
public class Seafood extends Product{

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    private int seafood_id;

    @ColumnInfo(name = "seafood_type")
    @TypeConverters(Converters.class)
    public SeafoodType seafood_type;

    //Constructor
    public Seafood(int product_id, String product_name, Product.ProductType product_type, SeafoodType seafood_type, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.seafood_type = seafood_type;
    }

    //Methods

//    public int getSeafood_id() {
//        return seafood_id;
//    }
//
//    public void setSeafood_id(int seafood_id) {
//        this.seafood_id = seafood_id;
//    }

    public int getSeafood_type() {
        if(seafood_type == SeafoodType.FISH) return 0;
        else if(seafood_type == SeafoodType.SHELLFISH) return 1;
        else if(seafood_type == SeafoodType.BIVALVE) return 2;
        else return 3;
    }

    public void setSeafood_type(SeafoodType seafood_type) {
        this.seafood_type = seafood_type;
    }

    //Enum
    public static enum SeafoodType {FISH, SHELLFISH, BIVALVE, OTHER}
}
