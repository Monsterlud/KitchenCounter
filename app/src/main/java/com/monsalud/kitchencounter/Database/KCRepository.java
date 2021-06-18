package com.monsalud.kitchencounter.Database;

import android.app.Application;

import androidx.room.Transaction;

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
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.Tables.RequestItem;
import com.monsalud.kitchencounter.UI.LoginActivity;

import java.util.List;

public class KCRepository {

    //Fields
    private ProductDAO mProductDAO;
    private VendorDAO mVendorDAO;
    private SalesRepDAO mSalesRepDAO;
    private EmployeeDAO mEmployeeDAO;
    private InventoryDAO mInventoryDAO;
    private RequestDAO mRequestDAO;
    private OrderDAO mOrderDAO;
    private OrderViewDAO mOrderViewDAO;

    private List<Product> mAllProducts;
    private List<Vegetable> mAllVegetables;
    private List<Fruit> mAllFruits;
    private List<Seafood> mAllSeafoods;
    private List<Dairy> mAllDairy;
    private List<Meat> mAllMeats;
    private List<DryGood> mAllDryGoods;
    private List<Beverage> mAllBeverages;
    private List<PaperGood> mAllPaperGoods;
    private List<JanitorialGood> mAllJanitorialGoods;
    private List<Vendor> mAllVendors;
    private List<SalesRep> mAllSalesReps;
    private List<Employee> mAllEmployees;
    private List<InventoryItem> mAllInventoryItems;
    private List<RequestItem> mAllRequestItems;
    private List<OrderItem> mAllOrderItems;
    private List<InventoryItem> mAllUnsubmittedInventory;
    private List<RequestItem> mAllUnsubmittedRequests;
    private List<InventoryItem> mAllActiveInventory;
    private List<RequestItem> mAllActiveRequests;
    private List<OrderItem> mAllUnplacedOrders;
    private List<OrderView> mAllOrderViewEntries;

    private List<OrderView> mAllFarmerOrderViews;
    private List<OrderView> mAllOrganicOrderViews;
    private List<OrderView> mAllBigOrderViews;
    private List<OrderView> mAllFattedOrderViews;
    private List<OrderView> mAllReallyOrderViews;
    private List<OrderView> mAllBetsyOrderViews;
    private List<OrderView> mAllPacificOrderViews;
    private List<OrderView> mAllRegalOrderViews;


    private OrderItem mOrderItem;


    //Constructor
    public KCRepository(Application application) {
        KitchenCounterDatabase db = KitchenCounterDatabase.getDatabaseInstance(application);
                mProductDAO = db.productDAO();
                mVendorDAO = db.vendorDAO();
                mSalesRepDAO = db.salesRepDAO();
                mEmployeeDAO = db.employeeDAO();
                mInventoryDAO = db.inventoryDAO();
                mRequestDAO = db.requestDAO();
                mOrderDAO = db.orderDAO();
                mOrderViewDAO = db.orderViewDAO();

                try {
                    Thread.sleep(1000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
    }

    //Methods
    //SELECT
    public OrderItem getOrderItem(int orderID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mOrderItem = mOrderDAO.getOrderItem(orderID);
        });
        return mOrderItem;
    }
    public List<Product> getAllProducts() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllProducts = mProductDAO.getAllProducts();
        });
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllProducts;
    }
    public List<Vendor> getAllVendors() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllVendors = mVendorDAO.getAllVendors();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllVendors;
    }
    @Transaction
    public List<Vegetable> getAllVegetables() {
    KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
        mAllVegetables = mProductDAO.getAllVegetables();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    return mAllVegetables;
    }
    public List<Fruit> getAllFruits() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllFruits = mProductDAO.getAllFruits();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllFruits;
    }
    public List<Seafood> getAllSeafoods() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllSeafoods = mProductDAO.getAllSeafood();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllSeafoods;
    }
    public List<Dairy> getAllDairy() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllDairy = mProductDAO.getAllDairy();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllDairy;
    }
    public List<Meat> getAllMeats() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllMeats = mProductDAO.getAllMeats();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllMeats;
    }
    public List<DryGood> getAllDryGoods() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllDryGoods = mProductDAO.getAllDryGoods();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllDryGoods;
    }
    public List<Beverage> getAllBeverages() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllBeverages = mProductDAO.getAllBeverages();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllBeverages;
    }
    public List<PaperGood> getAllPaperGoods() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllPaperGoods = mProductDAO.getAllPaperGoods();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllPaperGoods;
    }
    public List<JanitorialGood> getAllJanitorialGoods() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllJanitorialGoods = mProductDAO.getAllJanitorialGoods();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllJanitorialGoods;
    }

    public List<InventoryItem> getAllInventoryItems() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllInventoryItems = mInventoryDAO.getAllInventoryItems();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllInventoryItems;
    }

    public List<InventoryItem> getAllUnsubmittedInventory() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllUnsubmittedInventory = mInventoryDAO.getAllUnsubmittedInventory(LoginActivity.mEmployeeID);
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUnsubmittedInventory;
    }
    public List<InventoryItem> getAllActiveInventory() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllActiveInventory = mInventoryDAO.getAllActiveInventory();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllActiveInventory;
    }

    public List<RequestItem> getAllUnsubmittedRequests() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllUnsubmittedRequests = mRequestDAO.getAllUnsubmittedRequests(LoginActivity.mEmployeeID);
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUnsubmittedRequests;
    }
    public List<RequestItem> getAllActiveRequests() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllActiveRequests = mRequestDAO.getAllActiveRequests();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllActiveRequests;
    }
    public List<RequestItem> getAllRequestItems() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllRequestItems = mRequestDAO.getAllRequestItems();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllRequestItems;
    }

    public List<OrderItem> getAllUnplacedOrders() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllUnplacedOrders = mOrderDAO.getAllUnplacedOrders();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUnplacedOrders;
    }

    public List<OrderItem> getAllOrderItems() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllOrderItems = mOrderDAO.getAllOrderItems();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllOrderItems;
    }

    public List<OrderView> getAllOrderViewEntries() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllOrderViewEntries = mOrderViewDAO.getAllOrderViewEntries();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllOrderViewEntries;
    }
    public List<OrderView> getAllActiveFarmerOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllFarmerOrderViews = mOrderViewDAO.getAllActiveFarmerOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllFarmerOrderViews;
    }
    public List<OrderView> getAllActiveOrganicOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllOrganicOrderViews = mOrderViewDAO.getAllActiveOrganicOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllOrganicOrderViews;
    }
    public List<OrderView> getAllActiveBigOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllBigOrderViews = mOrderViewDAO.getAllActiveBigOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllBigOrderViews;
    }
    public List<OrderView> getAllActiveFattedOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllFattedOrderViews = mOrderViewDAO.getAllActiveFattedOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllFattedOrderViews;
    }
    public List<OrderView> getAllActiveReallyOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllReallyOrderViews = mOrderViewDAO.getAllActiveReallyOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllReallyOrderViews;
    }
    public List<OrderView> getAllActiveBetsyOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllBetsyOrderViews = mOrderViewDAO.getAllActiveBetsyOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllBetsyOrderViews;
    }
    public List<OrderView> getAllActivePacificOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllPacificOrderViews = mOrderViewDAO.getAllActivePacificOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllPacificOrderViews;
    }
    public List<OrderView> getAllActiveRegalOrderViews() {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mAllRegalOrderViews = mOrderViewDAO.getAllActiveRegalOrderViews();
        });
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllRegalOrderViews;
    }



    //INSERT
    public void insertVegetable(Vegetable vegetable) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertVegetable(vegetable);
//            mProductDAO.insertProduct(vegetable.getProduct_id(), vegetable.getProduct_name());
        });
    }
    public void insertFruit(Fruit fruit) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertFruit(fruit);
        });
    }
    public void insertSeafood(Seafood seafood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertSeafood(seafood);
        });
    }
    public void insertDairy(Dairy dairy) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertDairy(dairy);
        });
    }
    public void insertMeat(Meat meat) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertMeat(meat);
        });
    }
    public void insertDryGood(DryGood dryGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertDryGood(dryGood);
        });
    }
    public void insertBeverage(Beverage beverage) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertBeverage(beverage);
        });
    }
    public void insertPaperGood(PaperGood paperGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertPaperGood(paperGood);
        });
    }
    public void insertJanitorialGood(JanitorialGood janitorialGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertJanitorialGood(janitorialGood);
        });
    }
    public void insertVendor(Vendor vendor) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mVendorDAO.insertVendor(vendor);
        });
    }
    public void insertSalesRep(SalesRep salesRep) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mSalesRepDAO.insertSalesRep(salesRep);
        });
    }
    public void insertEmployee(Employee employee) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mEmployeeDAO.insertEmployee(employee);
        });
    }
    public void insertInventoryItem(InventoryItem inventoryItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mInventoryDAO.insertInventoryItem(inventoryItem);
        });
    }
    public void insertRequestItem(RequestItem requestItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mRequestDAO.insertRequestItem(requestItem);
        });
    }
    public void insertOrderItem(OrderItem orderItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mOrderDAO.insertOrderItem(orderItem);
        });
    }


    //INSERT all
    public void insertAllVegetables(Vegetable... vegetables) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllVegetables(vegetables);
        });
    }
    public void insertAllFruits(Fruit... fruits) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllFruits(fruits);
        });
    }
    public void insertAllSeafoods(Seafood... seafoods) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllSeafoods(seafoods);
        });
    }
    public void insertAllDairies(Dairy... dairies) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllDairies(dairies);
        });
    }
    public void insertAllMeats(Meat... meats) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllMeats(meats);
        });
    }
    public void insertAllDryGoods(DryGood... dryGoods) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllDryGoods(dryGoods);
        });
    }
    public void insertAllBeverages(Beverage... beverages) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllBeverages(beverages);
        });
    }
    public void insertAllPaperGoods(PaperGood... paperGoods) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllPaperGoods(paperGoods);
        });
    }
    public void insertAllJanitorialGoods(JanitorialGood... janitorialGoods) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.insertAllJanitorialGoods(janitorialGoods);
        });
    }


    //UPDATE
    public void updateVendor(Vendor vendor) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mVendorDAO.updateVendor(vendor);
        });
    }
    public void updateSalesRep(SalesRep salesRep) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mSalesRepDAO.updateSalesRep(salesRep);
        });
    }
    public void updateEmployee(Employee employee) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mEmployeeDAO.updateEmployee(employee);
        });
    }
    public void updateInventoryItem(InventoryItem inventoryItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mInventoryDAO.updateInventoryItem(inventoryItem);
        });
    }
    public void updateRequestItem(RequestItem requestItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mRequestDAO.updateRequestItem(requestItem);
        });
    }
    public void updateOrderItem(OrderItem orderItem) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mOrderDAO.updateOrderItem(orderItem);
        });
    }
    public void updateVegetable(Vegetable vegetable) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateVegetable(vegetable);
        });
    }
    public void updateFruit(Fruit fruit) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateFruit(fruit);
        });
    }
    public void updateSeafood(Seafood seafood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateSeafood(seafood);
        });
    }
    public void updateDairy(Dairy dairy) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateDairy(dairy);
        });
    }
    public void updateMeat(Meat meat) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateMeat(meat);
        });
    }
    public void updateDryGood(DryGood dryGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateDryGood(dryGood);
        });
    }
    public void updateBeverage(Beverage beverage) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateBeverage(beverage);
        });
    }
    public void updatePaperGood(PaperGood paperGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updatePaperGood(paperGood);
        });
    }
    public void updateJanitorialGood(JanitorialGood janitorialGood) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.updateJanitorialGood(janitorialGood);
        });
    }
    public void updateSubmittedInventoryItem(int inventoryID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mInventoryDAO.updateSubmittedInventoryItem(inventoryID);
        });
        try {
            Thread.sleep(50);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void updateSubmittedRequestItem(int requestID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mRequestDAO.updateSubmittedRequestItem(requestID);
        });
        try {
            Thread.sleep(50);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }




    //DELETE
    public void deleteVendor(int vendorID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mVendorDAO.deleteVendor(vendorID);
        });
    }
    public void deleteSalesRep(int salesRepID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mSalesRepDAO.deleteSalesRep(salesRepID);
        });
    }
    public void deleteEmployee(int employeeID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mEmployeeDAO.deleteEmployee(employeeID);
        });
    }
    public void deleteInventoryItem(int inventoryID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mInventoryDAO.deleteInventoryItem(inventoryID);
        });
    }
    public void deleteRequestItem(int requestID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mRequestDAO.deleteRequestItem(requestID);
        });
    }
    public void deleteOrderItem(int orderID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mOrderDAO.deleteOrderItem(orderID);
        });
    }
    public void deleteVegetable(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteVegetable(productID);
        });
    }
    public void deleteFruit(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteFruit(productID);
        });
    }
    public void deleteSeafood(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteSeafood(productID);
        });
    }
    public void deleteDairy(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteDairy(productID);
        });
    }
    public void deleteMeat(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteMeat(productID);
        });
    }
    public void deleteDryGood(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteDryGood(productID);
        });
    }
    public void deleteBeverage(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteBeverage(productID);
        });
    }
    public void deletePaperGood(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deletePaperGood(productID);
        });
    }
    public void deleteJanitorialGood(int productID) {
        KitchenCounterDatabase.databaseWriteExecutor.execute(() -> {
            mProductDAO.deleteJanitorialGood(productID);
        });
    }

}