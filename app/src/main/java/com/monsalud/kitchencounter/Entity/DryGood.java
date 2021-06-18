package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "drygoods_table")
public class DryGood extends Product{

    //Columns (Fields)
//    @PrimaryKey(autoGenerate = true)
//    private int drygood_id;

    @ColumnInfo(name = "drygoods_category")
    @TypeConverters(Converters.class)
    public DryGoodsCategory drygoods_category;

    //Constructor
    public DryGood(int product_id, String product_name, Product.ProductType product_type, DryGoodsCategory drygoods_category, String product_description, InventoryItem.InventoryUnit default_unit, int default_vendor) {
        super(product_id, product_name, product_type, product_description, default_unit, default_vendor);
        this.drygoods_category = drygoods_category;
    }

    //Methods

//    public int getDrygood_id() {
//        return drygood_id;
//    }
//
//    public void setDrygood_id(int drygood_id) {
//        this.drygood_id = drygood_id;
//    }

    public int getDryGoods_category() {
        if(drygoods_category == DryGoodsCategory.GRAINS) return 0;
        else if(drygoods_category == DryGoodsCategory.BEANS) return 1;
        else if(drygoods_category == DryGoodsCategory.PASTA) return 2;
        else if(drygoods_category == DryGoodsCategory.ASIAN) return 3;
        else if(drygoods_category == DryGoodsCategory.NUTS) return 4;
        else if(drygoods_category == DryGoodsCategory.BAKING) return 5;
        else if(drygoods_category == DryGoodsCategory.SPICES) return 6;
        else if(drygoods_category == DryGoodsCategory.CANNED) return 7;
        else return 8;
    }

    public void setDrygoods_category(DryGoodsCategory drygoods_category) {
        this.drygoods_category = drygoods_category;
    }

    //Enum
    public static enum DryGoodsCategory {GRAINS, BEANS, PASTA, ASIAN,
        NUTS, BAKING, SPICES, CANNED, OTHER}
}
