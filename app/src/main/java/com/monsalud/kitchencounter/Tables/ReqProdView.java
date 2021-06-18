package com.monsalud.kitchencounter.Tables;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

@DatabaseView("SELECT product_table.product_id, product_table.product_name, product_table.product_description, " +
        "SUM(requestitem_table.request_amount) AS request_amount " +
        "FROM product_table LEFT JOIN requestitem_table " +
        "ON product_table.product_id = requestitem_table.product_id_fk " +
        "AND (requestitem_table.active = 1 AND requestitem_table.submitted = 1) " +
                "GROUP BY product_table.product_id;")

public class ReqProdView {

    @ColumnInfo(name = "product_id")
    public int product_id;

    @ColumnInfo(name = "product_name")
    public String product_name;

    @ColumnInfo(name = "product_description")
    public String product_description;

    @ColumnInfo(name = "request_amount")
    public double request_amount;

    //getters
    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public double getRequest_amount() {
        return request_amount;
    }
}
