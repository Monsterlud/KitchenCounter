package com.monsalud.kitchencounter.Entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Tables.InventoryItem;

@Entity(tableName = "product_table",
    foreignKeys = {@ForeignKey(
            entity = Vendor.class,
            parentColumns = "vendor_id",
            childColumns = "default_vendor",
            onUpdate = ForeignKey.CASCADE
    )}
)

public class Product {

    //Columns (Fields)
    @PrimaryKey (autoGenerate = true)
    private int product_id;

    @ColumnInfo(name = "product_name")
    private String product_name;

    @ColumnInfo(name = "product_type")
    @TypeConverters(Converters.class)
    private Product.ProductType product_type;

    @ColumnInfo(name = "product_description")
    @Nullable
    private String product_description;

    @ColumnInfo(name = "default_unit")
    private InventoryItem.InventoryUnit default_unit;

    @ColumnInfo(name = "default_vendor")
    @Nullable
    private int default_vendor;


    //Constructor
    public Product(int product_id, String product_name, Product.ProductType product_type,
                   String product_description, InventoryItem.InventoryUnit default_unit,
                   int default_vendor) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_description = product_description;
        this.default_unit = default_unit;
        this.default_vendor = default_vendor;
    }

    //Methods (inherited by all subclasses)
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public ProductType getProduct_type() {
        return product_type;
    }

    public void setProduct_type(ProductType product_type) {
        this.product_type = product_type;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public InventoryItem.InventoryUnit getDefault_unit() {
        return default_unit;
    }

    public void setDefault_unit(InventoryItem.InventoryUnit default_unit) {
        this.default_unit = default_unit;
    }

    public int getDefault_vendor() {
        return default_vendor;
    }

    public void setDefault_vendor(int default_vendor) {
        this.default_vendor = default_vendor;
    }

    //enum
    public static enum ProductType {VEGETABLE, FRUIT, SEAFOOD, DAIRY, MEAT, DRYGOOD, BEVERAGE, PAPERGOOD, JANITORIALGOOD}
}
