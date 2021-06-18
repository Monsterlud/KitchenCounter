package com.monsalud.kitchencounter.UI.Inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.UI.Inventory.Beverage.BeverageInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Dairy.DairyInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.DryGood.DryGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Fruit.FruitInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.JanitorialGood.JanitorialGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Meat.MeatInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.PaperGood.PaperGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Seafood.SeafoodInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvActivity;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.MainActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.InventoryRecyclerAdapter;
import com.monsalud.kitchencounter.UI.Request.RequestReviewActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InventoryReviewActivity extends AppCompatActivity {

    private TextView mTvEmployeeName;
    private TextView mTvDate;
    private TextView mTvKitchenCounter;
    private TextView mTvVegetable;
    private TextView mTvFruit;
    private TextView mTvSeafood;
    private TextView mTvDairy;
    private TextView mTvMeat;
    private TextView mTvDryGood;
    private TextView mTvBeverage;
    private TextView mTvPaperGood;
    private TextView mTvJanitorialGood;
    private TextView mTvSubmitInventory;
    private ImageView mIvBackButton;
    private ImageView mIvShortcut;

    private ConstraintLayout invreview;

    public static List<InventoryItem> mAllUnsubmittedInventory;
    public static List<Product> mAllProducts;

    KCRepository mRepository;
    InventoryRecyclerAdapter mInventoryRecyclerAdapter;
    private int mVendorID;
    private static List<OrderItem> mAllOrderItems;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_review);

        mRepository = new KCRepository(getApplication());
        mAllProducts = mRepository.getAllProducts();
        mAllOrderItems = mRepository.getAllOrderItems();

        //find all views
        mTvEmployeeName = findViewById(R.id.tvEmployeeName_inventoryreview);
        mTvDate = findViewById(R.id.tvDate_inventoryreview);
        mTvKitchenCounter = findViewById(R.id.tvKITCHENCOUNTER_inventoryreview);
        mTvVegetable = findViewById(R.id.tvVegetable_inventoryreview);
        mTvFruit = findViewById(R.id.tvFruit_inventoryreview);
        mTvSeafood = findViewById(R.id.tvSeafood_inventoryreview);
        mTvDairy = findViewById(R.id.tvDairy_inventoryreview);
        mTvMeat = findViewById(R.id.tvMeat_inventoryreview);
        mTvDryGood = findViewById(R.id.tvDryGood_inventoryreview);
        mTvBeverage = findViewById(R.id.tvBeverage_inventoryreview);
        mTvPaperGood = findViewById(R.id.tvPaperGood_inventoryreview);
        mTvJanitorialGood = findViewById(R.id.tvJanitorialGood_inventoryreview);
        mTvSubmitInventory = findViewById(R.id.tvSubmit_inventoryreview);
        mIvBackButton = findViewById(R.id.ivBackButton_inventoryreview);
        mIvShortcut = findViewById(R.id.ivShortcut_inventoryreview);

        invreview = findViewById(R.id.invreview);

        //set employee and date TextViews
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        LocalDate now = LocalDate.now();
        mTvDate.setText(dtf.format(now));

        //All onClick listeners
        mTvKitchenCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, VegetableInvActivity.class);
                startActivity(intent);
            }
        });
        mTvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, FruitInvActivity.class);
                startActivity(intent);
            }
        });
        mTvSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, SeafoodInvActivity.class);
                startActivity(intent);
            }
        });
        mTvDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, DairyInvActivity.class);
                startActivity(intent);
            }
        });
        mTvMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, MeatInvActivity.class);
                startActivity(intent);
            }
        });
        mTvDryGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, DryGoodsInvActivity.class);
                startActivity(intent);
            }
        });
        mTvBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, BeverageInvActivity.class);
                startActivity(intent);
            }
        });
        mTvPaperGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, PaperGoodsInvActivity.class);
                startActivity(intent);
            }
        });
        mTvJanitorialGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, JanitorialGoodsInvActivity.class);
                startActivity(intent);
            }
        });
        mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mIvShortcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryReviewActivity.this, RequestReviewActivity.class);
                startActivity(intent);
            }
        });

        mTvSubmitInventory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mAllUnsubmittedInventory = mRepository.getAllUnsubmittedInventory();
                if(mAllUnsubmittedInventory.size() != 0) {
                    for (InventoryItem invItem : mAllUnsubmittedInventory) {
                        //change the column 'submitted' to true
                        mRepository.updateSubmittedInventoryItem(invItem.getInventoryitem_id());
                    }
                    mInventoryRecyclerAdapter.notifyDataSetChanged();
                    initializeDisplayContents();

                    Snackbar confirmSubmit = Snackbar.make(invreview, "Your inventory has been submitted", Snackbar.LENGTH_LONG);
                    confirmSubmit.setBackgroundTint(Color.WHITE);
                    TextView submitText = (TextView) (confirmSubmit.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
                    submitText.setTextSize(32);
                    submitText.setTextColor(Color.BLACK);
                    confirmSubmit.show();
                } else {
                    Snackbar nullError = Snackbar.make(invreview, "There are no Inventory Items to submit", Snackbar.LENGTH_LONG);
                    nullError.setBackgroundTint(Color.WHITE);
                    TextView submitText = (TextView) (nullError.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
                    submitText.setTextSize(32);
                    submitText.setTextColor(Color.BLACK);
                    nullError.show();
                }
            }
        });
        initializeDisplayContents();
    }

        private void initializeDisplayContents() {
            //set RecyclerView with unsubmitted Inventory Items
            mAllUnsubmittedInventory = mRepository.getAllUnsubmittedInventory();
            final RecyclerView recyclerInventory = (RecyclerView) findViewById(R.id.listInventoryReview);
            recyclerInventory.setHasFixedSize(true);

            final LinearLayoutManager inventoryLayoutManager = new LinearLayoutManager(this);
            recyclerInventory.setLayoutManager(inventoryLayoutManager);

            mInventoryRecyclerAdapter = new InventoryRecyclerAdapter(this, mAllUnsubmittedInventory);
            recyclerInventory.setAdapter(mInventoryRecyclerAdapter);

            mInventoryRecyclerAdapter.SetOnClickListener(new InventoryRecyclerAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                }
                @Override
                public void OnDeleteClick(int position) {
                    removeItem(position);
                }
            });
        }

        private void removeItem(int position) {
            mAllUnsubmittedInventory.remove(position);
            mInventoryRecyclerAdapter.notifyItemRemoved(position);
        }
    }


