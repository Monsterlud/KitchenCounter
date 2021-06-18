package com.monsalud.kitchencounter.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.UI.Inventory.InventoryMainActivity;
import com.monsalud.kitchencounter.UI.Order.OrderMainActivity;
import com.monsalud.kitchencounter.UI.Request.RequestMainActivity;

public class MainActivity extends AppCompatActivity {

    TextView mTvInventory;
    TextView mTvRequest;
    TextView mTvOrder;

    KCRepository mRespository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDatabase();

        mTvInventory = findViewById(R.id.tvInventory_main);
        mTvRequest = findViewById(R.id.tvRequest_main);
        mTvOrder = findViewById(R.id.tvOrder_main);

        mTvInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InventoryMainActivity.class);
                startActivity(intent);
            }
        });
        mTvRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestMainActivity.class);
                startActivity(intent);
            }
        });
        mTvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderMainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initializeDatabase() {
        mRespository = new KCRepository(getApplication());
        mRespository.getAllProducts();
    }
}