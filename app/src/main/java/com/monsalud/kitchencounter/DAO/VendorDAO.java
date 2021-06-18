package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Entity.Vendor;

import java.util.List;

@Dao
public interface VendorDAO {

    @Query("SELECT * FROM vendor_table ")
    List<Vendor> getAllVendors();

    @Query("SELECT * FROM vendor_table WHERE vendor_id = :vendorID")
    Vendor getVendor(int vendorID);

    @Insert
    void insertVendor(Vendor vendor);

    @Insert
    void insertAllVendors(Vendor... vendor);

    @Update
    void updateVendor(Vendor vendor);

    @Query("DELETE FROM vendor_table WHERE vendor_id = :vendorID")
    void deleteVendor(int vendorID);

    @Query("DELETE FROM vendor_table")
    public void deleteAllVendors();
}
