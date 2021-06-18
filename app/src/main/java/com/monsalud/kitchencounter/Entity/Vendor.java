package com.monsalud.kitchencounter.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.monsalud.kitchencounter.Database.Converters;

@Entity(tableName = "vendor_table")
public class Vendor {

    //Columns (Fields)
    @PrimaryKey(autoGenerate = true)
    private int vendor_id;

    @ColumnInfo(name = "vendor_name")
    private String vendor_name;

    @ColumnInfo(name = "vendor_address")
    private String vendor_address;

    @ColumnInfo(name = "vendor_city")
    private String vendor_city;

    @ColumnInfo(name = "vendor_state")
    @TypeConverters(Converters.class)
    public State vendor_state;

    @ColumnInfo(name = "vendor_zip")
    private String vendor_zip;

    @ColumnInfo(name = "vendor_phone")
    private String vendor_phone;

    @ColumnInfo(name = "vendor_email")
    private String vendor_email;

    @ColumnInfo(name = "vendor url")
    private String vendor_url;

    //Constructor
    public Vendor(int vendor_id,
                  String vendor_name,
                  String vendor_address,
                  String vendor_city,
                  State vendor_state,
                  String vendor_zip,
                  String vendor_phone,
                  String vendor_email,
                  String vendor_url) {
        this.vendor_id = vendor_id;
        this.vendor_name = vendor_name;
        this.vendor_address = vendor_address;
        this.vendor_city = vendor_city;
        this.vendor_state = vendor_state;
        this.vendor_zip = vendor_zip;
        this.vendor_phone = vendor_phone;
        this.vendor_email = vendor_email;
        this.vendor_url = vendor_url;
    }

    //Methods
    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }
    public String getVendor_address() {
        return vendor_address;
    }

    public void setVendor_address(String vendor_address) {
        this.vendor_address = vendor_address;
    }

    public String getVendor_city() {
        return vendor_city;
    }

    public void setVendor_city(String vendor_city) {
        this.vendor_city = vendor_city;
    }

    public State getVendor_state() {
        return vendor_state;
    }

    public void setVendor_state(State vendor_state) {
        this.vendor_state = vendor_state;
    }

    public String getVendor_zip() {
        return vendor_zip;
    }

    public void setVendor_zip(String vendor_zip) {
        this.vendor_zip = vendor_zip;
    }

    public String getVendor_phone() {
        return vendor_phone;
    }

    public void setVendor_phone(String vendor_phone) {
        this.vendor_phone = vendor_phone;
    }

    public String getVendor_email() {
        return vendor_email;
    }

    public void setVendor_email(String vendor_email) {
        this.vendor_email = vendor_email;
    }

    public String getVendor_url() {
        return vendor_url;
    }

    public void setVendor_url(String vendor_url) {
        this.vendor_url = vendor_url;
    }

    @Override
    public String toString() {
        return vendor_name;
    }
    //Enum
    public static enum State {AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL, GA,
        HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, MS, MO, MT, NE,
        NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT,
        VT, VA, WA, WV, WI, WY}
}
