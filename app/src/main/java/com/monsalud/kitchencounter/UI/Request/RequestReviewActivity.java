package com.monsalud.kitchencounter.UI.Request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.monsalud.kitchencounter.Tables.RequestItem;
import com.monsalud.kitchencounter.UI.Inventory.InventoryReviewActivity;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.MainActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.RequestRecyclerAdapter;
import com.monsalud.kitchencounter.UI.Request.Beverage.BeverageReqActivity;
import com.monsalud.kitchencounter.UI.Request.Dairy.DairyReqActivity;
import com.monsalud.kitchencounter.UI.Request.DryGood.DryGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Fruit.FruitReqActivity;
import com.monsalud.kitchencounter.UI.Request.JanitorialGood.JanitorialGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Meat.MeatReqActivity;
import com.monsalud.kitchencounter.UI.Request.PaperGood.PaperGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Seafood.SeafoodReqActivity;
import com.monsalud.kitchencounter.UI.Request.Vegetable.VegetableReqActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestReviewActivity extends AppCompatActivity {

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
    private TextView mTvSubmitRequests;
    private ImageView mIvBackButton;
    private ImageView mIvShortcut;

    private ConstraintLayout reqreview;

    public static List<RequestItem> mAllUnsubmittedRequests;
    public static List<Product> mAllProducts;
    KCRepository mRepository;
    RequestRecyclerAdapter mRequestRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_review);

        mRepository = new KCRepository(getApplication());
        mAllProducts = mRepository.getAllProducts();

        //find all views
        mTvEmployeeName = findViewById(R.id.tvEmployeeName_requestreview);
        mTvDate = findViewById(R.id.tvDate_requestreview);
        mTvKitchenCounter = findViewById(R.id.tvKITCHENCOUNTER_requestreview);
        mTvVegetable = findViewById(R.id.tvVegetable_requestreview);
        mTvFruit = findViewById(R.id.tvFruit_requestreview);
        mTvSeafood = findViewById(R.id.tvSeafood_requestreview);
        mTvDairy = findViewById(R.id.tvDairy_requestreview);
        mTvMeat = findViewById(R.id.tvMeat_requestreview);
        mTvDryGood = findViewById(R.id.tvDryGood_requestreview);
        mTvBeverage = findViewById(R.id.tvBeverage_requestreview);
        mTvPaperGood = findViewById(R.id.tvPaperGood_requestreview);
        mTvJanitorialGood = findViewById(R.id.tvJanitorialGood_requestreview);
        mTvSubmitRequests = findViewById(R.id.tvSubmit_requestreview);
        mIvBackButton = findViewById(R.id.ivBackButton_requestreview);
        mIvShortcut = findViewById(R.id.ivShortcut_requestreview);

        reqreview = findViewById(R.id.reqreview);

        //set employee and date TextViews
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        LocalDate now = LocalDate.now();
        mTvDate.setText(dtf.format(now));

        //set all onClick listeners
        mTvKitchenCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, VegetableReqActivity.class);
                startActivity(intent);
            }
        });
        mTvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, FruitReqActivity.class);
                startActivity(intent);
            }
        });
        mTvSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, SeafoodReqActivity.class);
                startActivity(intent);
            }
        });
        mTvDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, DairyReqActivity.class);
                startActivity(intent);
            }
        });
        mTvMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, MeatReqActivity.class);
                startActivity(intent);
            }
        });
        mTvDryGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, DryGoodsReqActivity.class);
                startActivity(intent);
            }
        });
        mTvBeverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, BeverageReqActivity.class);
                startActivity(intent);
            }
        });
        mTvPaperGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, PaperGoodsReqActivity.class);
                startActivity(intent);
            }
        });
        mTvJanitorialGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestReviewActivity.this, JanitorialGoodsReqActivity.class);
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
                Intent intent = new Intent(RequestReviewActivity.this, InventoryReviewActivity.class);
                startActivity(intent);
            }
        });

        mTvSubmitRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAllUnsubmittedRequests = mRepository.getAllUnsubmittedRequests();
                if(mAllUnsubmittedRequests.size() != 0) {
                    for (RequestItem reqItem : mAllUnsubmittedRequests) {
                        mRepository.updateSubmittedRequestItem(reqItem.getRequestitem_id());
                    }
                    mRequestRecyclerAdapter.notifyDataSetChanged();
                    initializeDisplayContents();

                    Snackbar confirmSubmit = Snackbar.make(reqreview, "Your requests have been submitted", Snackbar.LENGTH_LONG);
                    confirmSubmit.setBackgroundTint(Color.LTGRAY);
                    TextView submitText = (TextView) (confirmSubmit.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
                    submitText.setTextSize(32);
                    submitText.setTextColor(Color.BLACK);
                    confirmSubmit.show();
                } else {
                    Snackbar nullError = Snackbar.make(reqreview, "There are no Requests to submit", Snackbar.LENGTH_LONG);
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

        //set RecyclerView with unsubmitted Request Items
        mAllUnsubmittedRequests = mRepository.getAllUnsubmittedRequests();
        final RecyclerView recyclerRequests = (RecyclerView) findViewById(R.id.listRequestReview);
        recyclerRequests.setHasFixedSize(true);

        final LinearLayoutManager inventoryLayoutManager = new LinearLayoutManager(this);
        recyclerRequests.setLayoutManager(inventoryLayoutManager);

        mRequestRecyclerAdapter = new RequestRecyclerAdapter(this, mAllUnsubmittedRequests);
        recyclerRequests.setAdapter(mRequestRecyclerAdapter);

        mRequestRecyclerAdapter.SetOnClickListener(new RequestRecyclerAdapter.OnItemClickListener() {
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
        mAllUnsubmittedRequests.remove(position);
        mRequestRecyclerAdapter.notifyItemRemoved(position);
    }
}