package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Tables.OrderItem;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM orderitem_table")
    List<OrderItem> getAllOrderItems();

    @Query("SELECT * FROM orderitem_table WHERE orderitem_id = :orderID")
    OrderItem getOrderItem(int orderID);

    @Query("SELECT * FROM orderitem_table WHERE placed = 0")
    List<OrderItem> getAllUnplacedOrders();

    @Insert
    void insertOrderItem(OrderItem orderItem);

    @Update
    void updateOrderItem(OrderItem orderItem);

    @Query("DELETE FROM orderitem_table WHERE orderitem_id = :orderID")
    void deleteOrderItem(int orderID);

    @Query("DELETE FROM orderitem_table")
    void deleteAllOrderItems();
}
