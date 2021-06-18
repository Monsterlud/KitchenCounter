package com.monsalud.kitchencounter.UI.Request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.UI.Inventory.Beverage.BeverageInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Dairy.DairyInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.DryGood.DryGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Fruit.FruitInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.InventoryMainActivity;
import com.monsalud.kitchencounter.UI.Inventory.JanitorialGood.JanitorialGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Meat.MeatInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.PaperGood.PaperGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Seafood.SeafoodInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvActivity;
import com.monsalud.kitchencounter.UI.MainActivity;
import com.monsalud.kitchencounter.UI.Request.Beverage.BeverageReqActivity;
import com.monsalud.kitchencounter.UI.Request.Dairy.DairyReqActivity;
import com.monsalud.kitchencounter.UI.Request.DryGood.DryGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Fruit.FruitReqActivity;
import com.monsalud.kitchencounter.UI.Request.JanitorialGood.JanitorialGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Meat.MeatReqActivity;
import com.monsalud.kitchencounter.UI.Request.PaperGood.PaperGoodsReqActivity;
import com.monsalud.kitchencounter.UI.Request.Seafood.SeafoodReqActivity;
import com.monsalud.kitchencounter.UI.Request.Vegetable.VegetableReqActivity;

public class RequestMainActivity extends AppCompatActivity {

    TextView mTvKitchenCounter;
    TextView mTvRequest;
    TextView mTvVegetable;
    TextView mTvFruit;
    TextView mTvSeafood;
    TextView mTvDairy;
    TextView mTvMeat;
    TextView mTvDryGoods;
    TextView mTvBeverages;
    TextView mTvPaperGoods;
    TextView mTvJanitorialGoods;
    ImageView mIvBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_main);

        mTvKitchenCounter = findViewById(R.id.tvKITCHENCOUNTER_requestmain);
        mTvRequest = findViewById(R.id.tvREQUEST_requestmain);
        mTvVegetable = findViewById(R.id.tvProduce_requestmain);
        mTvFruit = findViewById(R.id.tvFruit_requestmain);
        mTvSeafood = findViewById(R.id.tvSeafood_requestmain);
        mTvDairy = findViewById(R.id.tvDairy_requestmain);
        mTvMeat = findViewById(R.id.tvMeat_requestmain);
        mTvDryGoods = findViewById(R.id.tvDryGoods_requestmain);
        mTvBeverages = findViewById(R.id.tvBeverages_requestmain);
        mTvPaperGoods = findViewById(R.id.tvPaperGoods_requestmain);
        mTvJanitorialGoods = findViewById(R.id.tvJanitorialGoods_requestmain);
        mIvBackButton = findViewById(R.id.ivBackButton_requestmain);

        mTvKitchenCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, VegetableReqActivity.class);
                startActivity(intent);
            }
        });

        mTvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, FruitReqActivity.class);
                startActivity(intent);
            }
        });

        mTvSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, SeafoodReqActivity.class);
                startActivity(intent);
            }
        });

        mTvDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, DairyReqActivity.class);
                startActivity(intent);
            }
        });

        mTvMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, MeatReqActivity.class);
                startActivity(intent);
            }
        });

        mTvDryGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, DryGoodsReqActivity.class);
                startActivity(intent);
            }
        });

        mTvBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, BeverageReqActivity.class);
                startActivity(intent);
            }
        });

        mTvPaperGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, PaperGoodsReqActivity.class);
                startActivity(intent);
            }
        });

        mTvJanitorialGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, JanitorialGoodsReqActivity.class);
                startActivity(intent);
            }
        });
        mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestMainActivity.this, RequestReviewActivity.class);
                startActivity(intent);
            }
        });
    }
}