package com.monsalud.kitchencounter.UI.Order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Vendor;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.OrderRecyclerAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderMainActivity extends AppCompatActivity
    implements OrderFragment.OnCancelOrderListener, OrderFragment.OnOrderListener {

    static TextView mTvEmployeeName;
    static TextView mTvDate;
    static TextView mTvGenerateOrders;
    static AutoCompleteTextView actv;
    private OrderRecyclerAdapter mOrderRecyclerAdapter;
    private KCRepository mRepository;
    private List<OrderView> mFilteredOrderViewList;
    private Fragment mFragment;
    private int mId;
    public static List<OrderItem> mAllOrderItems;
    private List<Product> mAllProducts;
    private int mProductID;
    public static List<Vendor> mAllVendors;
    private int mVendorID;
    private InventoryItem.InventoryUnit mUnit;
    ConstraintLayout ordermain;
    private LocalDate mRequestedDate;
    private LocalDate mDeliveryDate;
    private double mOrderAmount;
    private Product.ProductType mProductType;
    private List<OrderView> mFullOrderViewList;
    private String mVendorName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main);

        mRepository = new KCRepository(getApplication());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM-dd");
        mFilteredOrderViewList = mRepository.getAllOrderViewEntries();
        mFullOrderViewList = new ArrayList<>(mFilteredOrderViewList);
        mAllOrderItems = mRepository.getAllOrderItems();

        String[] dropDownItems = getResources().getStringArray(R.array.search_type_dropdown);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, R.layout.item_search_dropdown, dropDownItems);

        mTvEmployeeName = findViewById(R.id.tvEmployeeName_order);
        mTvDate = findViewById(R.id.tvDate_order);
        mTvGenerateOrders = findViewById(R.id.btnGenerateOrders_order);
        ordermain = findViewById(R.id.ordermain);
        actv = findViewById(R.id.ACTV_itemsearchdropdown);
        actv.setAdapter(arrayAdapter);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFullOrderViewList = mRepository.getAllOrderViewEntries();
                switch (position) {
                    case 0:
                        mFilteredOrderViewList = mFullOrderViewList;
                        mOrderRecyclerAdapter.notifyDataSetChanged();
                        initializeOrderingTable();
                        break;
                    case 1:
                        filterByProduct(Product.ProductType.VEGETABLE);
                        break;
                    case 2:
                        //mProductType = Product.ProductType.FRUIT;
                        filterByProduct(Product.ProductType.FRUIT);
                        break;
                    case 3:
                        //mProductType = Product.ProductType.SEAFOOD;
                        filterByProduct(Product.ProductType.SEAFOOD);
                        break;
                    case 4:
                        //mProductType = Product.ProductType.DAIRY;
                        filterByProduct(Product.ProductType.DAIRY);
                        break;
                    case 5:
                        //mProductType = Product.ProductType.MEAT;
                        filterByProduct(Product.ProductType.MEAT);
                        break;
                    case 6:
                        //mProductType = Product.ProductType.DRYGOOD;
                        filterByProduct(Product.ProductType.DRYGOOD);
                        break;
                    case 7:
                        //mProductType = Product.ProductType.BEVERAGE;
                        filterByProduct(Product.ProductType.BEVERAGE);
                        break;
                    case 8:
                        //mProductType = Product.ProductType.PAPERGOOD;
                        filterByProduct(Product.ProductType.PAPERGOOD);
                        break;
                    case 9:
                        //mProductType = Product.ProductType.JANITORIALGOOD;
                        filterByProduct(Product.ProductType.JANITORIALGOOD);
                        break;
                    case 10:
                        //setupOrderingView();
                        break;
                }
            }
    });

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvDate.setText(dtf.format(LocalDate.now()));
        mAllVendors = mRepository.getAllVendors();

        initializeOrderingTable();

        mTvGenerateOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderMainActivity.this, OrderSheetsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void filterByProduct(Product.ProductType productType) {
        mFilteredOrderViewList.clear();
            for (OrderView orderView : mFullOrderViewList) {
                if (orderView.getProduct_type() == productType) {
                    mFilteredOrderViewList.add(orderView);
                }
            }
        mOrderRecyclerAdapter.notifyDataSetChanged();
    }

    private void initializeOrderingTable() {
        //set RecyclerView with OrderView items (possibly filtered)
        final RecyclerView recyclerOrderTable = (RecyclerView) findViewById(R.id.recyclerview_order);
        recyclerOrderTable.setHasFixedSize(true);

        final LinearLayoutManager orderLayoutManager = new LinearLayoutManager(this);
        recyclerOrderTable.setLayoutManager(orderLayoutManager);

        mOrderRecyclerAdapter = new OrderRecyclerAdapter(this, mFilteredOrderViewList);
        recyclerOrderTable.setAdapter(mOrderRecyclerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //make sure that the RecyclerAdapter knows that the data set has changed
        mOrderRecyclerAdapter.notifyDataSetChanged();
        //initializeOrderingTable();
    }

    @Override
    public void onOrder() {
        //get the id number of the last OrderItem in the arraylist and increment it
        mAllOrderItems = mRepository.getAllOrderItems();
        if(mAllOrderItems.size()>0) {
            mId = mAllOrderItems.get(mAllOrderItems.size() - 1).getOrderitem_id();
            mId++;
        }
        else mId = 1;
        //get the product ID
        mAllProducts = mRepository.getAllProducts();
        for(Product product: mAllProducts) {
            if(product.getProduct_name().equals(OrderFragment.mProduct_name)
            && product.getProduct_description().equals(OrderFragment.mProduct_description)) {
                mProductID = product.getProduct_id();
            }
        }
        //get the vendor ID and vendor name
        for(Vendor vendor: mAllVendors) {
            if(vendor.getVendor_name().equals(String.valueOf(OrderFragment.mSpinVendor.getSelectedItem()))) {
                mVendorID = vendor.getVendor_id();
                mVendorName = vendor.getVendor_name();
            }
        }
        //get the InventoryUnit value of mOrder_Unit
        mUnit = (InventoryItem.InventoryUnit) OrderFragment.mSpinOrderUnit.getSelectedItem();

        //get the delivery date and the order amount from the fragment
        mDeliveryDate = LocalDate.parse(OrderFragment.mTvDeliveryDate.getText().toString());
        mOrderAmount = Double.parseDouble(OrderFragment.mTvOrderAmount.getText().toString());

        //create OrderItem object and insert into database
        OrderItem orderItem = new OrderItem(
            mId, mProductID, LoginActivity.mEmployeeID, mVendorID,
                mVendorName,
                mDeliveryDate,
                mUnit,
                mOrderAmount,
                true, false);
        mRepository.insertOrderItem(orderItem);

        onBackPressed();
        hideKeyboard();
        showSnackbarConfirmationMsg();
        mOrderRecyclerAdapter.notifyDataSetChanged();
        mFilteredOrderViewList = mRepository.getAllOrderViewEntries();
        initializeOrderingTable();
    }

    private void showSnackbarConfirmationMsg() {
        Snackbar confirmationOrder = Snackbar.make(ordermain, "This item has been added to the order list", Snackbar.LENGTH_LONG);
        confirmationOrder.setBackgroundTint(Color.WHITE);
        TextView confirmText = (TextView) (confirmationOrder.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        confirmText.setTextSize(32);
        confirmText.setTextColor(Color.BLACK);
        confirmationOrder.show();
    }

    @Override
    public void onCancelOrder() {
        mOrderRecyclerAdapter.mOrderFragment.getActivity().onBackPressed();
    }

private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
    View view = this.getCurrentFocus();
    if (view == null) view = new View(this);
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.product_search_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mOrderRecyclerAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }
}