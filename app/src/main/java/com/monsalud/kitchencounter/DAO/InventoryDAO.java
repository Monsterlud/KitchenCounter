package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface InventoryDAO {

    @Query("SELECT * FROM inventoryitem_table")
    List<InventoryItem> getAllInventoryItems();

    @Query("SELECT * FROM inventoryitem_table WHERE employee_id_fk = :employeeID" +
            " AND submitted = 0")
    List<InventoryItem> getAllUnsubmittedInventory(int employeeID);

    @Query("SELECT * FROM inventoryitem_table WHERE active = 1")
    List<InventoryItem> getAllActiveInventory();

    @Query("UPDATE inventoryitem_table SET submitted = 1 WHERE inventoryitem_id = :inventoryID")
            void updateSubmittedInventoryItem(int inventoryID);

    @Query("SELECT * FROM inventoryitem_table WHERE inventoryitem_id = :inventoryID")
    InventoryItem getInventoryItem(int inventoryID);

    @Insert
    void insertInventoryItem(InventoryItem inventoryItem);

    @Insert
    void insertAllInventoryItems(InventoryItem... inventoryItems);

    @Update
    void updateInventoryItem(InventoryItem inventoryItem);

    @Query("DELETE FROM inventoryitem_table WHERE inventoryitem_id = :inventoryID")
    void deleteInventoryItem(int inventoryID);

    @Query("DELETE FROM inventoryitem_table")
    void deleteAllInventoryItems();

}
