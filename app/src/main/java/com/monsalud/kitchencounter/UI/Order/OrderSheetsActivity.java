package com.monsalud.kitchencounter.UI.Order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.TextView;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Vendor;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.MainActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.OrderRecyclerAdapter;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.OrderSheetRecyclerAdapter;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.SheetFarmerRecyclerAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderSheetsActivity extends AppCompatActivity {

    static TextView mTvDate;
    static TextView mTvEmployee;

    private List<OrderView> mFilterFarmer;
    private List<OrderView> mFilterOrganic;
    private List<OrderView> mFilterBig;
    private List<OrderView> mFilterFatted;
    private List<OrderView> mFilterReally;
    private List<OrderView> mFilterBetsy;
    private List<OrderView> mFilterPacific;
    private List<OrderView> mFilterRegal;

    private List<Vendor> mAllVendors;
    private List<OrderView> mFilteredOrderViewList;
    private ArrayList<OrderView> mFullOrderViewList;

    KCRepository mRepository;
    OrderSheetRecyclerAdapter mOrderSheetRecyclerAdapter;
    private  TextView mTvKitchenCounter;
    private TextView mTvFarmerPhone;
    private TextView mTvFarmerEmail;
    private TextView mTvOrganicPhone;
    private TextView mTvOrganizEmail;
    private TextView mTvBigPhone;
    private TextView mTvBigEmail;
    private TextView mTvFattedPhone;
    private TextView mTvFattedEmail;
    private TextView mTvReallyPhone;
    private TextView mTvReallyEmail;
    private TextView mTvBestsyPhone;
    private TextView mTvBetsyEmail;
    private TextView mTvPacificPhone;
    private TextView mTvPacificEmail;
    private TextView mTvRegalPhone;
    private TextView mTvRegalEmail;
    private SheetFarmerRecyclerAdapter mSheetFarmerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sheet);

        //get all orderview lists filtered to vendor, get all order views, get all vendors
        mRepository = new KCRepository(getApplication());
        mAllVendors = mRepository.getAllVendors();
        mFilteredOrderViewList = mRepository.getAllOrderViewEntries();
        //mFullOrderViewList = new ArrayList<>(mFilteredOrderViewList);

        mFilterFarmer = mRepository.getAllActiveFarmerOrderViews();
        mFilterOrganic = mRepository.getAllActiveOrganicOrderViews();
        mFilterBig = mRepository.getAllActiveBigOrderViews();
        mFilterFatted = mRepository.getAllActiveFattedOrderViews();
        mFilterReally = mRepository.getAllActiveReallyOrderViews();
        mFilterBetsy = mRepository.getAllActiveBetsyOrderViews();
        mFilterPacific = mRepository.getAllActivePacificOrderViews();
        mFilterRegal = mRepository.getAllActiveRegalOrderViews();

        //bind local views to layout views
        mTvDate = findViewById(R.id.tvDate_ordersheet);
        mTvEmployee = findViewById(R.id.tvEmployee_ordersheet);

        mTvKitchenCounter = findViewById(R.id.tvKITCHENCOUNTER_ordersheet);
        mTvFarmerPhone = findViewById(R.id.tvFARMERGREENphone_ordersheet);
        mTvFarmerEmail = findViewById(R.id.tvFARMERGREENemail_ordersheet);
        mTvOrganicPhone = findViewById(R.id.tvORGANICCITYphone_ordersheet);
        mTvOrganizEmail = findViewById(R.id.tvORGANICCITYemail_ordersheet);
        mTvBigPhone = findViewById(R.id.tvBIGRANCHphone_ordersheet);
        mTvBigEmail = findViewById(R.id.tvBIGRANCHemail_ordersheet);
        mTvFattedPhone = findViewById(R.id.tvFATTEDCALFphone_ordersheet);
        mTvFattedEmail = findViewById(R.id.tvFATTEDCALFemail_ordersheet);
        mTvReallyPhone = findViewById(R.id.tvREALLYBIGphone_ordersheet);
        mTvReallyEmail = findViewById(R.id.tvREALLYBIGemail_ordersheet);
        mTvBestsyPhone = findViewById(R.id.tvBETSYCOWGIRLphone_ordersheet);
        mTvBetsyEmail = findViewById(R.id.tvBETSYCOWGIRLemail_ordersheet);
        mTvPacificPhone = findViewById(R.id.tvPACIFICSEAFOODphone_ordersheet);
        mTvPacificEmail = findViewById(R.id.tvPACIFICSEAFOODemail_ordersheet);
        mTvRegalPhone = findViewById(R.id.tvREGALBEVERAGESphone_ordersheet);
        mTvRegalEmail = findViewById(R.id.tvREGALBEVERAGESemail_ordersheet);

        //set vendor phone numbers and emails
        String FarmerPhone = null;
        String FarmerEmail = null;
        String OrganicPhone = null;
        String OrganicEmail = null;
        String BigPhone = null;
        String BigEmail = null;
        String FattedPhone = null;
        String FattedEmail = null;
        String ReallyPhone = null;
        String ReallyEmail = null;
        String BetsyPhone = null;
        String BetsyEmail = null;
        String PacificPhone = null;
        String PacificEmail = null;
        String RegalPhone = null;
        String RegalEmail = null;

        for (Vendor vendor : mAllVendors) {
            if (vendor.getVendor_name().equals("Farmer Green Produce")) {
                FarmerPhone = vendor.getVendor_phone();
                FarmerEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Organic City Produce")) {
                OrganicPhone = vendor.getVendor_phone();
                OrganicEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Big Ranch Meats")) {
                BigPhone = vendor.getVendor_phone();
                BigEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Fatted Calf Charcuterie")) {
                FattedPhone = vendor.getVendor_phone();
                FattedEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Really Big Food Company")) {
                ReallyPhone = vendor.getVendor_phone();
                ReallyEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Betsy the Cowgirl Dairy")) {
                BetsyPhone = vendor.getVendor_phone();
                BetsyEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Pacific Seafood")) {
                PacificPhone = vendor.getVendor_phone();
                PacificEmail = vendor.getVendor_email();
            } else if (vendor.getVendor_name().equals("Regal Beverages")) {
                RegalPhone = vendor.getVendor_phone();
                RegalEmail = vendor.getVendor_email();
            }
            else;
        }

        mTvFarmerPhone.setText(FarmerPhone);
        mTvFarmerEmail.setText(FarmerEmail);
        mTvOrganicPhone.setText(OrganicPhone);
        mTvOrganizEmail.setText(OrganicEmail);
        mTvBigPhone.setText(BigPhone);
        mTvBigEmail.setText(BigEmail);
        mTvFattedPhone.setText(FattedPhone);
        mTvFattedEmail.setText(FattedEmail);
        mTvReallyPhone.setText(ReallyPhone);
        mTvReallyEmail.setText(ReallyEmail);
        mTvBestsyPhone.setText(BetsyPhone);
        mTvBetsyEmail.setText(BetsyEmail);
        mTvPacificPhone.setText(PacificPhone);
        mTvPacificEmail.setText(PacificEmail);
        mTvRegalPhone.setText(RegalPhone);
        mTvRegalEmail.setText(RegalEmail);

        //set the employee name and today's date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM-dd");
        mTvDate.setText(dtf.format(LocalDate.now()));
        mTvEmployee.setText(LoginActivity.mEmployeeName);

        //initialize all recyclerviews
        initializeOrderSheets();

        mTvKitchenCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderSheetsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initializeOrderSheets() {
        //farmer green produce
        final RecyclerView recyclerfarmer = (RecyclerView) findViewById(R.id.recyclerfarmergreen);
        recyclerfarmer.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerFarmer = new LinearLayoutManager(this);
        recyclerfarmer.setLayoutManager(orderLayoutManagerFarmer);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterFarmer);
        recyclerfarmer.setAdapter(mOrderSheetRecyclerAdapter);

        //organic city produce
        final RecyclerView recyclerorganic = (RecyclerView) findViewById(R.id.recyclerorganiccity);
        recyclerorganic.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerOrganic = new LinearLayoutManager(this);
        recyclerorganic.setLayoutManager(orderLayoutManagerOrganic);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterOrganic);
        recyclerorganic.setAdapter(mOrderSheetRecyclerAdapter);

        //big ranch meats
        final RecyclerView recyclerbig = (RecyclerView) findViewById(R.id.recyclerbigranch);
        recyclerbig.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerBig = new LinearLayoutManager(this);
        recyclerbig.setLayoutManager(orderLayoutManagerBig);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterBig);
        recyclerbig.setAdapter(mOrderSheetRecyclerAdapter);

        //fatted calf charcuterie
        final RecyclerView recyclerfatted = (RecyclerView) findViewById(R.id.recyclerfattedcalf);
        recyclerfatted.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerFatted = new LinearLayoutManager(this);
        recyclerfatted.setLayoutManager(orderLayoutManagerFatted);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterFatted);
        recyclerfatted.setAdapter(mOrderSheetRecyclerAdapter);

        //really big food company
        final RecyclerView recyclerreally = (RecyclerView) findViewById(R.id.recyclerreallybig);
        recyclerreally.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerReally = new LinearLayoutManager(this);
        recyclerreally.setLayoutManager(orderLayoutManagerReally);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterReally);
        recyclerreally.setAdapter(mOrderSheetRecyclerAdapter);

        //betsy the cowgirl dairy
        final RecyclerView recyclerbetsy = (RecyclerView) findViewById(R.id.recyclerbetsycowgirl);
        recyclerbetsy.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerBetsy = new LinearLayoutManager(this);
        recyclerbetsy.setLayoutManager(orderLayoutManagerBetsy);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterBetsy);
        recyclerbetsy.setAdapter(mOrderSheetRecyclerAdapter);

        //pacific seafood
        final RecyclerView recyclerpacific = (RecyclerView) findViewById(R.id.recyclerpacificseafood);
        recyclerpacific.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerPacific = new LinearLayoutManager(this);
        recyclerpacific.setLayoutManager(orderLayoutManagerPacific);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterPacific);
        recyclerpacific.setAdapter(mOrderSheetRecyclerAdapter);

        //regal beverages
        final RecyclerView recyclerregal = (RecyclerView) findViewById(R.id.recyclerregalbeverages);
        recyclerregal.setHasFixedSize(true);
        final LinearLayoutManager orderLayoutManagerRegal = new LinearLayoutManager(this);
        recyclerregal.setLayoutManager(orderLayoutManagerRegal);
        mOrderSheetRecyclerAdapter = new OrderSheetRecyclerAdapter(this, mFilterRegal);
        recyclerregal.setAdapter(mOrderSheetRecyclerAdapter);

        mOrderSheetRecyclerAdapter.SetOnClickListener(new OrderSheetRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnOrderedClick(int orderID) {
                orderPlaced(orderID);
            }

            @Override
            public void OnDeleteClick(int orderID, int position) {
                deleteOrder(orderID, position);
            }
        });
    }
    private void orderPlaced(int orderID) {
        //placed = 1, active = 0
        OrderItem orderItem = mRepository.getOrderItem(orderID);
        orderItem.setPlaced(true);
    }
    private void deleteOrder(int orderID, int position) {
        mRepository.deleteOrderItem(orderID);
        mFilteredOrderViewList.remove(position);
        mOrderSheetRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //make sure that the RecyclerAdapter knows that the data set has changed
        mOrderSheetRecyclerAdapter.notifyDataSetChanged();
        initializeOrderSheets();
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) view = new View(this);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//        @Override
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
//                mOrderSheetRecyclerAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sharing:
                /*launches the chooser sheet and puts the Extras from the
                order sheet to be displayed by the chosen app (messages, gmail, etc)
                 */
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM/dd: h:mm a");
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "The order has been placed!");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "The food orders have been placed for tomorrow delivery on " + dtf.format(LocalDateTime.now()));
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, "Alert the team...");
                startActivity(shareIntent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}