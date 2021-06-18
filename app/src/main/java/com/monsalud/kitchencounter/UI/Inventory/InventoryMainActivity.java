package com.monsalud.kitchencounter.UI.Inventory;

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
import com.monsalud.kitchencounter.UI.Inventory.JanitorialGood.JanitorialGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Meat.MeatInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.PaperGood.PaperGoodsInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Seafood.SeafoodInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvActivity;
import com.monsalud.kitchencounter.UI.MainActivity;
import com.monsalud.kitchencounter.UI.Request.RequestMainActivity;

public class InventoryMainActivity extends AppCompatActivity {

    TextView mTvKitchenCounter;
    TextView mTvInventory;
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
        setContentView(R.layout.activity_inventory_main);

        mTvKitchenCounter = findViewById(R.id.tvKITCHENCOUNTER_inventorymain);
        mTvInventory = findViewById(R.id.tvINVENTORY_inventorymain);
        mTvVegetable = findViewById(R.id.tvProduce_inventorymain);
        mTvFruit = findViewById(R.id.tvFruit_inventorymain);
        mTvSeafood = findViewById(R.id.tvSeafood_inventorymain);
        mTvDairy = findViewById(R.id.tvDairy_inventorymain);
        mTvMeat = findViewById(R.id.tvMeat_inventorymain);
        mTvDryGoods = findViewById(R.id.tvDryGoods_inventorymain);
        mTvBeverages = findViewById(R.id.tvBeverages_inventorymain);
        mTvPaperGoods = findViewById(R.id.tvPaperGoods_inventorymain);
        mTvJanitorialGoods = findViewById(R.id.tvJanitorialGoods_inventorymain);
        mIvBackButton = findViewById(R.id.ivBackButton_inventorymain);

        mTvKitchenCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvVegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, VegetableInvActivity.class);
                startActivity(intent);
            }
        });

        mTvFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, FruitInvActivity.class);
                startActivity(intent);
            }
        });

        mTvSeafood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, SeafoodInvActivity.class);
                startActivity(intent);
            }
        });

        mTvDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, DairyInvActivity.class);
                startActivity(intent);
            }
        });

        mTvMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, MeatInvActivity.class);
                startActivity(intent);
            }
        });

        mTvDryGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, DryGoodsInvActivity.class);
                startActivity(intent);
            }
        });

        mTvBeverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, BeverageInvActivity.class);
                startActivity(intent);
            }
        });

        mTvPaperGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, PaperGoodsInvActivity.class);
                startActivity(intent);
            }
        });

        mTvJanitorialGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, JanitorialGoodsInvActivity.class);
                startActivity(intent);
            }
        });
        mIvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mTvInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryMainActivity.this, InventoryReviewActivity.class);
                startActivity(intent);
            }
        });
    }

}