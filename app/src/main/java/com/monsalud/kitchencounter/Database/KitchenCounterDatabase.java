package com.monsalud.kitchencounter.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Transaction;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.monsalud.kitchencounter.DAO.EmployeeDAO;
import com.monsalud.kitchencounter.DAO.InventoryDAO;
import com.monsalud.kitchencounter.DAO.OrderDAO;
import com.monsalud.kitchencounter.DAO.OrderViewDAO;
import com.monsalud.kitchencounter.DAO.ProductDAO;
import com.monsalud.kitchencounter.DAO.RequestDAO;
import com.monsalud.kitchencounter.DAO.SalesRepDAO;
import com.monsalud.kitchencounter.DAO.VendorDAO;
import com.monsalud.kitchencounter.Entity.Beverage;
import com.monsalud.kitchencounter.Entity.Dairy;
import com.monsalud.kitchencounter.Entity.DryGood;
import com.monsalud.kitchencounter.Entity.Employee;
import com.monsalud.kitchencounter.Entity.Fruit;
import com.monsalud.kitchencounter.Entity.JanitorialGood;
import com.monsalud.kitchencounter.Entity.Meat;
import com.monsalud.kitchencounter.Entity.PaperGood;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.SalesRep;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.Entity.Vegetable;
import com.monsalud.kitchencounter.Entity.Vendor;

import com.monsalud.kitchencounter.Tables.InvProdView;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.Tables.ReqProdView;
import com.monsalud.kitchencounter.Tables.RequestItem;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class, Vegetable.class, Fruit.class, Seafood.class, Dairy.class,
        Meat.class, DryGood.class, Beverage.class, PaperGood.class, JanitorialGood.class,
        Vendor.class, SalesRep.class, Employee.class, InventoryItem.class,
        RequestItem.class, OrderItem.class}, views = {OrderView.class, InvProdView.class, ReqProdView.class}, version = 1)

public abstract class KitchenCounterDatabase extends RoomDatabase {

    public abstract ProductDAO productDAO();
    public abstract VendorDAO vendorDAO();
    public abstract SalesRepDAO salesRepDAO();
    public abstract EmployeeDAO employeeDAO();
    public abstract InventoryDAO inventoryDAO();
    public abstract RequestDAO requestDAO();
    public abstract OrderDAO orderDAO();
    public abstract OrderViewDAO orderViewDAO();
    private static final int NUMBER_OF_THREADS = 4;
    private static final String DATABASE_NAME = "kitchencounterDB.db";

    //The Database Executor
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //The Database Instance (Singleton)
    private static volatile KitchenCounterDatabase INSTANCE;

    @Transaction
    public static KitchenCounterDatabase getDatabaseInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (KitchenCounterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KitchenCounterDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        public void onOpen(SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                ProductDAO mProductDAO = INSTANCE.productDAO();
                VendorDAO mVendorDAO = INSTANCE.vendorDAO();
                SalesRepDAO mSalesRepDAO = INSTANCE.salesRepDAO();
                EmployeeDAO mEmployeeDAO = INSTANCE.employeeDAO();
                InventoryDAO mInventoryDAO = INSTANCE.inventoryDAO();
                RequestDAO mRequestDAO = INSTANCE.requestDAO();
                OrderDAO mOrderDAO = INSTANCE.orderDAO();

                /*Delete Products, Vendors, SalesReps, Employees, InventoryItems, RequestItems,
                and OrderItems for a clean Database every restart.
                TO DO: Comment out these statements to create persistent data*/
                mInventoryDAO.deleteAllInventoryItems();
                mRequestDAO.deleteAllRequestItems();
                mOrderDAO.deleteAllOrderItems();
                mProductDAO.deleteAllVegetables();
                mProductDAO.deleteAllFruits();
                mProductDAO.deleteAllSeafood();
                mProductDAO.deleteAllDairy();
                mProductDAO.deleteAllMeats();
                mProductDAO.deleteAllDryGoods();
                mProductDAO.deleteAllBeverages();
                mProductDAO.deleteAllPaperGoods();
                mProductDAO.deleteAllJanitorialGoods();
                mProductDAO.deleteAllProducts();
                mSalesRepDAO.deleteAllSalesReps();
                mVendorDAO.deleteAllVendors();
                mEmployeeDAO.deleteAllEmployees();


                //Populate Vendors
                Vendor vendor = new Vendor(1, "Farmer Green Produce", "1200 Park Avenue", "Industrial City",
                        Vendor.State.CA, "94115", "415-555-1212", "sales@farmergreen.com", "www.farmergreen.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(2, "Organic City Produce", "141 Street Road", "San Francisco",
                        Vendor.State.CA, "94123", "415-555-3333", "elise@organiccityproduce.com", "www.organiccityproduce.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(3, "Big Ranch Meats", "15634 County Line Road", "Springfield",
                        Vendor.State.CA, "97843", "925-555-1965", "sales@bigranchmeats.com", "www.bigranch.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(4, "Fatted Calf Charcuterie", "644 C First Street", "Napa",
                        Vendor.State.CA, "94559", "707-256-3684", "napashop@fattedcalf.com", "www.fattedcalf.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(5, "Really Big Food Company", "2000 Great Highway", "South San Francisco",
                        Vendor.State.CA, "95555", "650-555-5555", "sales@reallybigfood.com", "www.reallybigfood.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(6, "Betsy the Cowgirl Dairy", "15534 Country Road", "Pt. Reyes Station",
                        Vendor.State.CA, "94999", "707-555-0990", "sales@betsythecowgirl.com", "www.betsythecowgirl.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(7, "Pacific Seafood", "3000 Great Highway", "Pacifica",
                        Vendor.State.CA, "99999", "650-555-1111", "sales@pacificseafood.com", "www.pacificseafood.com");
                mVendorDAO.insertVendor(vendor);
                vendor = new Vendor(8, "Regal Beverages", "100 California Turnpike", "Berkeley",
                        Vendor.State.CA, "94999", "510-555-2021", "sales@regalbeverages.com", "www.regalbeverages.com");
                mVendorDAO.insertVendor(vendor);

                //Populate SalesReps
                SalesRep salesRep = new SalesRep(1, "Jim", "Johnson",
                        "555-5555", "jim@company.com", 1);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(2, "Nancy", "Smith",
                        "555-5555", "nancy@company.com", 1);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(3, "Mary", "Young",
                        "555-5555", "mary@company.com", 2);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(4, "Doug", "Monsalud",
                        "555-5555", "doug@company.com", 3);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(5, "Taylor", "Boetticher",
                        "555-5555", "taylor@fattedcalf.com", 4);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(6, "Tommy", "Little",
                        "555-5555", "tommy@company.com", 5);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(7, "Brittany", "Smith",
                        "555-5555", "brittany@company.com", 6);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(8, "Margaret", "Garcia",
                        "555-5555", "margaret@company.com", 7);
                mSalesRepDAO.insertSalesRep(salesRep);
                salesRep = new SalesRep(9, "Sofia", "Milano",
                        "555-5555", "sofia@company.com", 8);
                mSalesRepDAO.insertSalesRep(salesRep);

                //Populate Employees
                Employee employee = new Employee(1, "Darrell", "Johnson",
                        "510-555-5555", "510-111-1111",
                        "darrell@techcompany.com", Employee.EmployeeRole.CULINARY);
                mEmployeeDAO.insertEmployee(employee);
                employee = new Employee(2, "Alyssa", "Smith",
                        "510-555-4444", "510-222-2222",
                        "alyssa@techcompany.com", Employee.EmployeeRole.CASHIER);
                mEmployeeDAO.insertEmployee(employee);
                employee = new Employee(3, "Sara", "Mitchell",
                        "510-555-3333", "510-333-3333",
                        "sara@techcompany.com", Employee.EmployeeRole.CHEF);
                mEmployeeDAO.insertEmployee(employee);

                //Populate produce
                Vegetable vegetable = new Vegetable(1, "Carrot", Product.ProductType.VEGETABLE,
                        "Bunched", "Bunched", InventoryItem.InventoryUnit.BUNCH, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(2, "Onion", Product.ProductType.VEGETABLE,
                        "Red", "Red", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(3, "Eggplant", Product.ProductType.VEGETABLE,
                        "Japanese", "Japanese", InventoryItem.InventoryUnit.LB,  2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(4, "Broccoli", Product.ProductType.VEGETABLE,
                        "Crowns", "Crowns", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(5, "Broccoli", Product.ProductType.VEGETABLE,
                        "Di Ciccio", "Di Ciccio", InventoryItem.InventoryUnit.LB,  1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(6, "Broccoli", Product.ProductType.VEGETABLE,
                        "Romanesco", "Romanesco", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(7, "Kale", Product.ProductType.VEGETABLE,
                        "Lacinato", "Lacinato", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(8, "Kale", Product.ProductType.VEGETABLE,
                        "Red Russian", "Red Russian", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(9, "Carrot", Product.ProductType.VEGETABLE,
                        "Nantes", "Nantes", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(10, "Mushroom", Product.ProductType.VEGETABLE,
                        "Chanterelle", "Chanterelle", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(11, "Mushroom", Product.ProductType.VEGETABLE,
                        "Morel", "Morel", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(12, "Onion", Product.ProductType.VEGETABLE,
                        "Green", "Green", InventoryItem.InventoryUnit.BUNCH, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(13, "Onion", Product.ProductType.VEGETABLE,
                        "Cippolini", "Cippolini", InventoryItem.InventoryUnit.LB, 2);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);
                vegetable = new Vegetable(14, "Tomato", Product.ProductType.VEGETABLE,
                        "Marvel Stripe", "Marvel Stripe", InventoryItem.InventoryUnit.CASE, 1);
                mProductDAO.insertVegetable(vegetable);
                mProductDAO.insertProduct(vegetable);

                //Populate fruit
                Fruit fruit = new Fruit(15, "Apple", Product.ProductType.FRUIT,
                        "Fuji", "Fuji", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(16, "Apple", Product.ProductType.FRUIT,
                        "Gravenstein", "Gravenstein", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(17, "Blueberries", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(18, "Raspberries", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(19, "Strawberries (Organic)", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE, 1);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(20, "Strawberries", Product.ProductType.FRUIT,
                        "Long Stem", "Long Stem", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(21, "Pear", Product.ProductType.FRUIT,
                        "Bosc", "Bosc", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(22, "Lemon", Product.ProductType.FRUIT,
                        "Meyer", "Meyer", InventoryItem.InventoryUnit.LB, 1);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(23, "Limes", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(24, "Limes", Product.ProductType.FRUIT,
                        "Key", "Key", InventoryItem.InventoryUnit.LB,  2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(25, "Oranges", Product.ProductType.FRUIT,
                        "Blood", "Blood", InventoryItem.InventoryUnit.CASE, 1);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(26, "Oranges", Product.ProductType.FRUIT,
                        "Cara Cara", "Cara Cara", InventoryItem.InventoryUnit.CASE, 1);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(27, "Bananas", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE, 2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);
                fruit = new Fruit(28, "Passionfruit", Product.ProductType.FRUIT,
                        "", "", InventoryItem.InventoryUnit.CASE,  2);
                mProductDAO.insertFruit(fruit);
                mProductDAO.insertProduct(fruit);

                //Populate seafoods
                Seafood seafood = new Seafood(29, "Salmon, Coho", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.FISH, "Northern California", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(30, "Salmon, King Filet", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.FISH, "Wild, Fresh", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(31, "Tuna, Bigeye", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.FISH, "Loin", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(32, "Crab, Dungeness (Whole)", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.SHELLFISH, "Live", InventoryItem.InventoryUnit.EACH, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(33, "Mussels, Greenlip", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.BIVALVE, "New Zealand", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(34, "Seaweed, Wakame (Fresh)", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.OTHER, "", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(35, "Caviar, Beluga", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.OTHER, "Domestic", InventoryItem.InventoryUnit.OZ, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(36, "Caviar, Salmon", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.OTHER, "Domestic", InventoryItem.InventoryUnit.OZ, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(37, "Lobster, Whole", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.SHELLFISH, "Live, Maine", InventoryItem.InventoryUnit.EACH, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(38, "Langoustines, Whole", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.SHELLFISH, "Frozen", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(39, "Mackerel, Filet", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.FISH, "S&P", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(40, "Crabmeat, Rock", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.SHELLFISH, "Maine", InventoryItem.InventoryUnit.LB, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(41, "Oysters, Kumamoto", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.BIVALVE, "Washington State", InventoryItem.InventoryUnit.DOZEN, 7);
                mProductDAO.insertSeafood(seafood);
                mProductDAO.insertProduct(seafood);
                seafood = new Seafood(42, "Oysters, Miyagi", Product.ProductType.SEAFOOD,
                        Seafood.SeafoodType.BIVALVE, "Hog Island", InventoryItem.InventoryUnit.DOZEN, 7);
                mProductDAO.insertSeafood(seafood);

                //Populate dairy
                Dairy dairy = new Dairy(43, "Milk, Skim", Product.ProductType.DAIRY,
                        Dairy.DairyType.MILKANDCREAM, "", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(44, "Milk, 2%", Product.ProductType.DAIRY,
                        Dairy.DairyType.MILKANDCREAM, "", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(45, "Milk, Whole", Product.ProductType.DAIRY,
                        Dairy.DairyType.MILKANDCREAM, "", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(46, "Half n Half", Product.ProductType.DAIRY,
                        Dairy.DairyType.MILKANDCREAM, "", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(47, "Cream", Product.ProductType.DAIRY,
                        Dairy.DairyType.MILKANDCREAM, "", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(48, "Eggs, Large", Product.ProductType.DAIRY,
                        Dairy.DairyType.EGGS, "Brown", InventoryItem.InventoryUnit.DOZEN, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(49, "Maytag Blue", Product.ProductType.DAIRY,
                        Dairy.DairyType.CHEESE, "Crumbles", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(50, "Fresh Goat Cheese", Product.ProductType.DAIRY,
                        Dairy.DairyType.CHEESE, "Laura Chenel", InventoryItem.InventoryUnit.LB, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(51, "Yogurt, Ind. Vanilla", Product.ProductType.DAIRY,
                        Dairy.DairyType.CULTURED, "Clover", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(52, "Yogurt, Greek", Product.ProductType.DAIRY,
                        Dairy.DairyType.CULTURED, "Fage", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(53, "Feta, French", Product.ProductType.DAIRY,
                        Dairy.DairyType.CHEESE, "", InventoryItem.InventoryUnit.LB, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(54, "Eggs, Whites", Product.ProductType.DAIRY,
                        Dairy.DairyType.EGGS, "Quarts", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(55, "Cowgirl Mt. Tam", Product.ProductType.DAIRY,
                        Dairy.DairyType.CHEESE, "Wheels", InventoryItem.InventoryUnit.EACH, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);
                dairy = new Dairy(56, "Butter, Sweet", Product.ProductType.DAIRY,
                        Dairy.DairyType.OTHER, "Organic", InventoryItem.InventoryUnit.CASE, 6);
                mProductDAO.insertDairy(dairy);
                mProductDAO.insertProduct(dairy);

                //Populate Meats
                Meat meat = new Meat(57, "Ground Beef", Product.ProductType.MEAT,
                        Meat.MeatType.BEEF, "Marin Sun Farms", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(58, "Beef Navel", Product.ProductType.MEAT,
                        Meat.MeatType.BEEF, "Marin Sun Farms", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(59, "Ribeye Roasts", Product.ProductType.MEAT,
                        Meat.MeatType.BEEF, "Bone-In", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(60, "Ground Lamb", Product.ProductType.MEAT,
                        Meat.MeatType.LAMB, "Colorado", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(61, "Leg of Lamb - Boneless", Product.ProductType.MEAT,
                        Meat.MeatType.LAMB, "Sonoma", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(62, "Chicken WOGS", Product.ProductType.MEAT,
                        Meat.MeatType.POULTRY, "2.5#", InventoryItem.InventoryUnit.EACH, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(63, "Chicken Breasts, B&S", Product.ProductType.MEAT,
                        Meat.MeatType.POULTRY, "10oz average", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(64, "Duck Legs", Product.ProductType.MEAT,
                        Meat.MeatType.POULTRY, "Liberty", InventoryItem.InventoryUnit.EACH, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(65, "Pork Shoulder, Cubed", Product.ProductType.MEAT,
                        Meat.MeatType.PORK, "", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(66, "Ground Pork", Product.ProductType.MEAT,
                        Meat.MeatType.PORK, "", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(67, "Whole Goat", Product.ProductType.MEAT,
                        Meat.MeatType.GOAT, "", InventoryItem.InventoryUnit.EACH, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(68, "Pork Chops, Bone-In", Product.ProductType.MEAT,
                        Meat.MeatType.PORK, "Center Cut", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(69, "Wild Boar, Shoulder", Product.ProductType.MEAT,
                        Meat.MeatType.WILDGAME, "Bob Canard", InventoryItem.InventoryUnit.LB, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);
                meat = new Meat(70, "Poussin, Whole", Product.ProductType.MEAT,
                        Meat.MeatType.POULTRY, "Frozen", InventoryItem.InventoryUnit.EACH, 3);
                mProductDAO.insertMeat(meat);
                mProductDAO.insertProduct(meat);

                //Populate Dry Goods
                DryGood dryGood = new DryGood(71, "AP Flour", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.BAKING, "Guisto's", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(72, "Oats, Steel-Cut", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.GRAINS, "Guisto's", InventoryItem.InventoryUnit.LB, 5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(73, "Tamari Soy Sauce", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.ASIAN, "Cube", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(74, "Gemelli", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.PASTA, "De Cecco", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(75, "San Marzano Tomatoes, Whole", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.CANNED, "", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(76, "Freekeh, Green", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.GRAINS, "Bulk", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(77, "Pinto Beans", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.BEANS, "Organic", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(78, "Almonds, Whole", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.NUTS, "Natural", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(79, "Walnuts, Pieces", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.NUTS, "", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(80, "Tumeric, Ground", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.SPICES, "", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(81, "Cumin, Whole", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.SPICES, "", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(82, "Pimente d'Esplette", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.SPICES, "Spanish", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(83, "Sugar, Organic", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.BAKING, "", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);
                dryGood = new DryGood(84, "Ditilini", Product.ProductType.DRYGOOD,
                        DryGood.DryGoodsCategory.PASTA, "Barilla", InventoryItem.InventoryUnit.LB,5);
                mProductDAO.insertDryGood(dryGood);
                mProductDAO.insertProduct(dryGood);

                //Populate Beverages
                Beverage beverage = new Beverage(85, "Coconut Water", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.JUICE, "12oz", InventoryItem.InventoryUnit.CASE,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);
                beverage = new Beverage(86, "Dr. Pepper", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.SOFTDRINKS, "Cans", InventoryItem.InventoryUnit.CASE,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);
                beverage = new Beverage(87, "Evian", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.WATER, "24oz Bottles", InventoryItem.InventoryUnit.CASE,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);
                beverage = new Beverage(88, "Red Bay Decaf, Ground", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.COFFEE, "Lb", InventoryItem.InventoryUnit.LB,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);
                beverage = new Beverage(89, "Ginger-Tumeric Tea", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.TEA, "16oz", InventoryItem.InventoryUnit.CASE,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);
                beverage = new Beverage(90, "Gooseberry Kombucha", Product.ProductType.BEVERAGE,
                        Beverage.BeverageType.OTHER, "16oz", InventoryItem.InventoryUnit.CASE,8);
                mProductDAO.insertBeverage(beverage);
                mProductDAO.insertProduct(beverage);

                //Populate Paper Goods
                PaperGood paperGood = new PaperGood(91, "Pizza Boxes (storage)", Product.ProductType.PAPERGOOD,
                        PaperGood.PaperGoodsCategory.OTHER, "", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertPaperGood(paperGood);
                mProductDAO.insertProduct(paperGood);
                paperGood = new PaperGood(92, "Paper Towels", Product.ProductType.PAPERGOOD,
                        PaperGood.PaperGoodsCategory.DISPOSABLES, "", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertPaperGood(paperGood);
                mProductDAO.insertProduct(paperGood);
                paperGood = new PaperGood(93, "Compostable Forks", Product.ProductType.PAPERGOOD,
                        PaperGood.PaperGoodsCategory.TOGOPACKAGING, "Potato", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertPaperGood(paperGood);
                mProductDAO.insertProduct(paperGood);
                paperGood = new PaperGood(94, "Napkins", Product.ProductType.PAPERGOOD,
                        PaperGood.PaperGoodsCategory.TOGOPACKAGING, "Natural", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertPaperGood(paperGood);
                mProductDAO.insertProduct(paperGood);
                paperGood = new PaperGood(95, "Paper Straws", Product.ProductType.PAPERGOOD,
                        PaperGood.PaperGoodsCategory.DISPOSABLES, "", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertPaperGood(paperGood);
                mProductDAO.insertProduct(paperGood);

                //Populate Janitorial Goods
                JanitorialGood janitorialGood = new JanitorialGood(96, "Mach 1", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CHEMICALS, "Ecolab", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood(97, "Rinse Helper", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CHEMICALS, "Ecolab", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood(98, "Bleach", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CHEMICALS, "", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood(99, "Rubber Gloves - Large", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CLEANINGSUPPLIES, "", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood(100, "Rubber Gloves - Medium", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CLEANINGSUPPLIES, "", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood( 101, "Green Scrubbies", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CLEANINGSUPPLIES, "", InventoryItem.InventoryUnit.EACH,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);
                janitorialGood = new JanitorialGood(102, "AP Cleaner", Product.ProductType.JANITORIALGOOD,
                        JanitorialGood.JanitorialGoodsCategory.CHEMICALS, "Seven Generations", InventoryItem.InventoryUnit.CASE,5);
                mProductDAO.insertJanitorialGood(janitorialGood);
                mProductDAO.insertProduct(janitorialGood);

                //Start with some inventory items
                InventoryItem invItem = new InventoryItem(1, 98, 3, LocalDate.now(),
                        2, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(2, 60, 3, LocalDate.now(),
                         10, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(3, 72, 3, LocalDate.now(),
                         50, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(4, 6, 3, LocalDate.now(),
                         2, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(5, 10, 3, LocalDate.now(),
                         8, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(6, 45, 3, LocalDate.now(),
                         27, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(7, 47, 3, LocalDate.now(),
                         79, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(8, 40, 3, LocalDate.now(),
                         5, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(9, 41, 3, LocalDate.now(),
                         10,  InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(10, 87, 3, LocalDate.now(),
                         7, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(11, 88, 3, LocalDate.now(),
                         20, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(12, 92, 3, LocalDate.now(),
                         2.5, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);
                invItem = new InventoryItem(13, 93, 3, LocalDate.now(),
                         4.5, InventoryItem.InventoryLocation.CENTRALDRYGOODS, false, true);
                mInventoryDAO.insertInventoryItem(invItem);

                //Start with some request items
                RequestItem reqItem = new RequestItem( 1,98, 3,2, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 2,4, 3, 5, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 3,10, 3,12, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 4,14, 3,2, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 5,21, 3,1, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 6,27, 3,60, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem(7, 38, 3,23, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 8,39, 3,5, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem(9, 47, 3,3, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem(10,52, 3,4, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 11,59, 3,7, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 12,69, 3,10, false, true);
                mRequestDAO.insertRequestItem(reqItem);
                reqItem = new RequestItem( 13,70, 3,2, false, true);
                mRequestDAO.insertRequestItem(reqItem);

                //start with some orderitems
                OrderItem orderItem = new OrderItem(1, 2,3, 1, "Farmer Green Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 2, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(2, 5,3, 2, "Organic City Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 4, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(3, 9,3, 2, "Organic City Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 1, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(4, 17,3, 1, "Farmer Green Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 1, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(5, 23,3, 1, "Farmer Green Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 2, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(6, 25,3, 2, "Organic City Produce",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 10, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(7, 30,3, 7, "Pacific Seafood",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 10, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(8, 32,3, 7,"Pacific Seafood",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.EACH, 12, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(9, 35,3, 7,"Pacific Seafood",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.OZ, 2, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(10, 43,3, 6, "Betsy the Cowgirl Dairy",
                         LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 8, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(11, 45,3, 6,"Betsy the Cowgirl Dairy",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 5, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(12, 46,3, 6,"Betsy the Cowgirl Dairy",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 12, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(13, 52,3, 6,"Betsy the Cowgirl Dairy",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.EACH, 60, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(14, 60,3, 3, "Big Ranch Meats",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 5, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(15, 62,3, 3,"Big Ranch Meats",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.EACH, 24, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(16, 65,3, 3,"Big Ranch Meats",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 40, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(17, 75,3, 5,"Really Big Food Company",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 6, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(18, 82,3, 5, "Really Big Food Company",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 1, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(19, 86,3, 8, "Regal Beverages",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 6, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(20, 88,3, 8,"Regal Beverages",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.LB, 15, true, false);
                mOrderDAO.insertOrderItem(orderItem);
                orderItem = new OrderItem(21, 101,3, 5,"Really Big Food Company",
                        LocalDate.parse("2021-06-16"), InventoryItem.InventoryUnit.CASE, 2, true, false);
                mOrderDAO.insertOrderItem(orderItem);
            });
        }
    };
}



