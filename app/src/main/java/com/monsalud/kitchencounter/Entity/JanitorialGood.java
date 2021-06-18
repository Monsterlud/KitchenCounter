package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "janitorialgoods_table")
public class JanitorialGood extends Product{

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    private int janitorialgood_id;

    @ColumnInfo(name = "janitorialgoods_category")
    @TypeConverters(Converters.class)
    public JanitorialGoodsCategory janitorialgoods_category;

    //Constructor
    public JanitorialGood(int product_id, String product_name, Product.ProductType product_type, JanitorialGoodsCategory janitorialgoods_category, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.janitorialgoods_category = janitorialgoods_category;
    }

    //Methods

//    public int getJanitorialgood_id() {
//        return janitorialgood_id;
//    }


    public int getJanitorialGoods_category() {
        if(janitorialgoods_category == JanitorialGoodsCategory.CHEMICALS) return 0;
        else if(janitorialgoods_category == JanitorialGoodsCategory.CLEANINGSUPPLIES) return 1;
        else return 2;
    }

    public void setJanitorialgoods_category(JanitorialGoodsCategory janitorialgoods_category) {
        this.janitorialgoods_category = janitorialgoods_category;
    }

    //Enum
    public static enum JanitorialGoodsCategory {CHEMICALS, CLEANINGSUPPLIES, OTHER}
}
