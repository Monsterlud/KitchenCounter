package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "salesrep_table",
    foreignKeys = @ForeignKey(
            entity = Vendor.class,
            parentColumns = "vendor_id",
            childColumns = "vendor_id_fk",
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
    )
)
public class SalesRep {

    //Columns (Fields)
    @PrimaryKey
    private int salesrep_id;

    @ColumnInfo(name = "salesrep_firstname")
    private String salesrep_firstname;

    @ColumnInfo(name = "salesrep_lastname")
    private String salesrep_lastname;

    @ColumnInfo(name = "salesrep_phone")
    private String salesrep_phone;

    @ColumnInfo(name = "salesrep_email")
    private String salesrep_email;

    @ColumnInfo(name = "vendor_id_fk")
    private int vendor_id_fk;

    //Constructor

    public SalesRep(int salesrep_id,
                    String salesrep_firstname,
                    String salesrep_lastname,
                    String salesrep_phone,
                    String salesrep_email,
                    int vendor_id_fk) {
        this.salesrep_id = salesrep_id;
        this.salesrep_firstname = salesrep_firstname;
        this.salesrep_lastname = salesrep_lastname;
        this.salesrep_phone = salesrep_phone;
        this.salesrep_email = salesrep_email;
        this.vendor_id_fk = vendor_id_fk;
    }

    //Methods
    public int getSalesrep_id() {
        return salesrep_id;
    }

    public void setSalesrep_id(int salesrep_id) {
        this.salesrep_id = salesrep_id;
    }

    public String getSalesrep_firstname() {
        return salesrep_firstname;
    }

    public void setSalesrep_firstname(String salesrep_firstname) {
        this.salesrep_firstname = salesrep_firstname;
    }

    public String getSalesrep_lastname() {
        return salesrep_lastname;
    }

    public void setSalesrep_lastname(String salesrep_lastname) {
        this.salesrep_lastname = salesrep_lastname;
    }

    public String getSalesrep_phone() {
        return salesrep_phone;
    }

    public void setSalesrep_phone(String salesrep_phone) {
        this.salesrep_phone = salesrep_phone;
    }

    public String getSalesrep_email() {
        return salesrep_email;
    }

    public void setSalesrep_email(String salesrep_email) {
        this.salesrep_email = salesrep_email;
    }

    public int getVendor_id_fk() {
        return vendor_id_fk;
    }

    public void setVendor_id_fk(int vendor_id_fk) {
        this.vendor_id_fk = vendor_id_fk;
    }
}
