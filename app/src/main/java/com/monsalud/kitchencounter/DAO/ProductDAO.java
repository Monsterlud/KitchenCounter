package com.monsalud.kitchencounter.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.monsalud.kitchencounter.Entity.Beverage;
import com.monsalud.kitchencounter.Entity.Dairy;
import com.monsalud.kitchencounter.Entity.DryGood;
import com.monsalud.kitchencounter.Entity.Fruit;
import com.monsalud.kitchencounter.Entity.JanitorialGood;
import com.monsalud.kitchencounter.Entity.Meat;
import com.monsalud.kitchencounter.Entity.PaperGood;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.Entity.Vegetable;

import java.util.List;

@Dao
public interface ProductDAO {

    //SELECT (Get)
    @Query("SELECT * FROM product_table")
    List<Product> getAllProducts();

    @Query("SELECT * FROM product_table WHERE product_id = :productID")
    Product getProduct(int productID);

    @Query("SELECT * FROM vegetable_table")
    List<Vegetable> getAllVegetables();

    @Query("SELECT * FROM vegetable_table WHERE product_id = :productID")
    Vegetable getVegetable(int productID);

    @Query("SELECT * FROM fruit_table")
    List<Fruit> getAllFruits();

    @Query("SELECT * FROM fruit_table WHERE product_id = :productID")
    Fruit getFruit(int productID);

    @Query("SELECT * FROM seafood_table")
    List<Seafood> getAllSeafood();

    @Query("SELECT * FROM seafood_table WHERE product_id = :productID")
    Seafood getSeafood(int productID);

    @Query("SELECT * FROM dairy_table")
    List<Dairy> getAllDairy();

    @Query("SELECT * FROM dairy_table WHERE product_id = :productID")
    Dairy getDairy(int productID);

    @Query("SELECT * FROM meat_table")
    List<Meat> getAllMeats();

    @Query("SELECT * FROM meat_table WHERE product_id = :productID")
    Meat getMeat(int productID);

    @Query("SELECT * FROM drygoods_table")
    List<DryGood> getAllDryGoods();

    @Query("SELECT * FROM drygoods_table WHERE product_id = :productID")
    DryGood getDryGood(int productID);

    @Query("SELECT * FROM beverage_table")
    List<Beverage> getAllBeverages();

    @Query("SELECT * FROM beverage_table WHERE product_id = :productID")
    Beverage getBeverage(int productID);

    @Query("SELECT * FROM papergoods_table")
    List<PaperGood> getAllPaperGoods();

    @Query("SELECT * FROM papergoods_table WHERE product_id = :productID")
    PaperGood getPaperGood(int productID);

    @Query("SELECT * FROM janitorialgoods_table")
    List<JanitorialGood> getAllJanitorialGoods();

    @Query("SELECT * FROM janitorialgoods_table WHERE product_id = :productID")
    JanitorialGood getJanitorialGood(int productID);

    //INSERT
//    @Query("INSERT INTO product_table (product_ID, product_name)" +
//            "VALUES (:productID, :productName)")
//    void insertProduct(int productID, String productName);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertProduct(Product product);

    @Insert
    void insertVegetable(Vegetable vegetable);

    @Insert
    void insertAllVegetables(Vegetable... vegetables);

    @Insert
    void insertFruit(Fruit fruit);

    @Insert
    void insertAllFruits(Fruit... fruits);

    @Insert
    void insertSeafood(Seafood seafood);

    @Insert
    void insertAllSeafoods(Seafood... seafoods);

    @Insert
    void insertDairy(Dairy dairy);

    @Insert
    void insertAllDairies(Dairy... dairies);

    @Insert
    void insertMeat(Meat meat);

    @Insert
    void insertAllMeats(Meat... meats);

    @Insert
    void insertDryGood(DryGood dryGood);

    @Insert
    void insertAllDryGoods(DryGood... dryGoods);

    @Insert
    void insertBeverage(Beverage beverage);

    @Insert
    void insertAllBeverages(Beverage... beverages);

    @Insert
    void insertPaperGood(PaperGood paperGood);

    @Insert
    void insertAllPaperGoods(PaperGood... paperGoods);

    @Insert
    void insertJanitorialGood(JanitorialGood janitorialGood);

    @Insert
    void insertAllJanitorialGoods(JanitorialGood... janitorialGoods);

    //UPDATE
//    @Update
//    void updateProduct(Product product);

    @Update
    void updateVegetable(Vegetable vegetable);

    @Update
    void updateFruit(Fruit fruit);

    @Update
    void updateSeafood(Seafood seafood);

    @Update
    void updateDairy(Dairy dairy);

    @Update
    void updateMeat(Meat meat);

    @Update
    void updateDryGood(DryGood dryGood);

    @Update
    void updateBeverage(Beverage beverage);

    @Update
    void updatePaperGood(PaperGood paperGood);

    @Update
    void updateJanitorialGood(JanitorialGood janitorialGood);

    //DELETE
    @Query("DELETE FROM vegetable_table")
    void deleteAllVegetables();

    @Query("DELETE FROM vegetable_table WHERE product_id = :productID")
    void deleteVegetable(int productID);

    @Query("DELETE FROM fruit_table")
    void deleteAllFruits();

    @Query("DELETE FROM fruit_table WHERE product_id = :productID")
    void deleteFruit(int productID);

    @Query("DELETE FROM seafood_table")
    void deleteAllSeafood();

    @Query("DELETE FROM seafood_table WHERE product_id = :productID")
    void deleteSeafood(int productID);

    @Query("DELETE FROM dairy_table")
    void deleteAllDairy();

    @Query("DELETE FROM dairy_table WHERE product_id = :productID")
    void deleteDairy(int productID);

    @Query("DELETE FROM meat_table")
    void deleteAllMeats();

    @Query("DELETE FROM meat_table WHERE product_id = :productID")
    void deleteMeat(int productID);

    @Query("DELETE FROM drygoods_table")
    void deleteAllDryGoods();

    @Query("DELETE FROM drygoods_table WHERE product_id = :productID")
    void deleteDryGood(int productID);

    @Query("DELETE FROM beverage_table")
    void deleteAllBeverages();

    @Query("DELETE FROM beverage_table WHERE product_id = :productID")
    void deleteBeverage(int productID);

    @Query("DELETE FROM papergoods_table")
    void deleteAllPaperGoods();

    @Query("DELETE FROM papergoods_table WHERE product_id = :productID")
    void deletePaperGood(int productID);

    @Query("DELETE FROM janitorialgoods_table")
    void deleteAllJanitorialGoods();

    @Query("DELETE FROM janitorialgoods_table WHERE product_id = :productID")
    void deleteJanitorialGood(int productID);

    @Query("DELETE FROM product_table")
    void deleteAllProducts();

}
