package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "papergoods_table")
public class PaperGood extends Product{

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    private int papergood_id;

    @ColumnInfo(name = "papergoods_category")
    @TypeConverters(Converters.class)
    public PaperGoodsCategory papergoods_category;

    //Constructor
    public PaperGood(int product_id, String product_name, Product.ProductType product_type, PaperGoodsCategory papergoods_category, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.papergoods_category = papergoods_category;
    }

    //Method
//
//    public int getPapergood_id() {
//        return papergood_id;
//    }
//
//    public void setPapergood_id(int papergood_id) {
//        this.papergood_id = papergood_id;
//    }

    public int getPaperGood_category() {
        if(papergoods_category == PaperGoodsCategory.TOGOPACKAGING) return 0;
        else if(papergoods_category == PaperGoodsCategory.DISPOSABLES) return 1;
        else return 3;
    }

    public void setPapergoods_category(PaperGoodsCategory papergoods_category) {
        this.papergoods_category = papergoods_category;
    }

    //Enum
    public static enum PaperGoodsCategory {TOGOPACKAGING, DISPOSABLES, OTHER}
}
