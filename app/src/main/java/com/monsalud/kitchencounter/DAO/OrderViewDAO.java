package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.FtsOptions;
import androidx.room.Query;

import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Tables.OrderView;

import java.util.List;

@Dao
public interface OrderViewDAO {

    @Query("SELECT * FROM OrderView")
    List<OrderView> getAllOrderViewEntries();

//        @Query("SELECT * FROM OrderView")
//        List<OrderView> getAllOrderViewEntries();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 1")
    List<OrderView> getAllActiveFarmerOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 2")
    List<OrderView> getAllActiveOrganicOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 3")
    List<OrderView> getAllActiveBigOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 4")
    List<OrderView> getAllActiveFattedOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 5")
    List<OrderView> getAllActiveReallyOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 6")
    List<OrderView> getAllActiveBetsyOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 7")
    List<OrderView> getAllActivePacificOrderViews();

    @Query("SELECT * FROM OrderView " +
            "WHERE placed = 0 AND active = 1 AND vendor_id_fk = 8")
    List<OrderView> getAllActiveRegalOrderViews();

}
