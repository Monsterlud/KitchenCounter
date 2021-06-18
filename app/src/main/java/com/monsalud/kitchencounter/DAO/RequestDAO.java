package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.RequestItem;

import java.util.List;

@Dao
public interface RequestDAO {

    @Query("SELECT * FROM requestitem_table")
    List<RequestItem> getAllRequestItems();

    @Query("SELECT * FROM requestitem_table WHERE requestitem_id = :requestItemID")
    RequestItem getRequestItem(int requestItemID);

    @Query("SELECT * FROM requestitem_table WHERE active = 1")
    List<RequestItem> getAllActiveRequests();

    @Insert
    void insertRequestItem(RequestItem requestItem);

    @Update
    void updateRequestItem(RequestItem requestItem);

    @Query("DELETE FROM requestitem_table WHERE requestitem_id = :requestItemID")
    void deleteRequestItem(int requestItemID);

    @Query("DELETE FROM requestitem_table")
    void deleteAllRequestItems();

    @Query("SELECT * FROM requestitem_table WHERE employee_id_fk = :employeeID" +
            " AND submitted = 0")
    List<RequestItem> getAllUnsubmittedRequests(int employeeID);

    @Query("UPDATE requestitem_table SET submitted = 1 WHERE requestitem_id = :requestID")
    void updateSubmittedRequestItem(int requestID);
}
