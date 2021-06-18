package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Entity.SalesRep;

import java.util.List;

@Dao
public interface SalesRepDAO {

    @Query("SELECT * FROM salesrep_table")
    List<SalesRep> getAllSalesReps();

    @Query("SELECT * FROM salesrep_table WHERE salesrep_id = :salesRepID")
    SalesRep getSalesRep(int salesRepID);

    @Insert
    void insertSalesRep(SalesRep salesRep);

    @Insert
    void insertAllSalesReps(SalesRep... salesRep);

    @Update
    void updateSalesRep(SalesRep salesRep);

    @Query("DELETE FROM salesrep_table WHERE salesrep_id = :salesRepID")
    void deleteSalesRep(int salesRepID);

    @Query("DELETE FROM salesrep_table")
    void deleteAllSalesReps();
}
