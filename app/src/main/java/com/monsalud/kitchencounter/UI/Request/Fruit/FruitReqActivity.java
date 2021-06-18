package com.monsalud.kitchencounter.UI.Request.Fruit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.monsalud.kitchencounter.Database.Converters;
import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Fruit;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.RequestItem;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.FruitRecyclerAdapter;
import com.monsalud.kitchencounter.UI.Request.Beverage.BeverageReqDetailFragment;
import com.monsalud.kitchencounter.UI.Request.RequestReviewActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FruitReqActivity extends AppCompatActivity
        implements FruitRecyclerAdapter.FruitRecyclerListener, FruitReqDetailFragment.OnAddToRequestsListener {

    FruitRecyclerAdapter mFruitRecyclerAdapter;
    KCRepository mRespository;

    ConstraintLayout fruitreq;
    int mId;
    private InventoryItem.InventoryUnit mUnit;
    private int mProdID;
    private LocalDate mDeliveryDate;
    private Double mAmount;

    public static List<Fruit> mFruits;
    private TextView mTvRequestReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_req);

        mRespository = new KCRepository(getApplication());
        mFruits = mRespository.getAllFruits();
        fruitreq = findViewById(R.id.fruitreq);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.detailLayout_reqfruit, new FruitReqDetailFragment());
        ft.replace(R.id.listLayout_reqfruit, new FruitReqListFragment());
        ft.commit();

        mTvRequestReview = findViewById(R.id.tvReviewRequests_reqfruit);
        mTvRequestReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FruitReqActivity.this, RequestReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFruitRecyclerSent(CharSequence fruitName, CharSequence fruitVarietal, int productID, InventoryItem.InventoryUnit unit) {
        FruitReqDetailFragment.updateFragment(fruitName, fruitVarietal, productID, unit);
    }

    @Override
    public void onAddToRequests() {

        closeKeyboard();

        //Get the InventoryUnit value
        mUnit = FruitReqDetailFragment.mUnit;
//        int inventoryUnitPosition = FruitReqDetailFragment.mSpinUnit.getSelectedItemPosition();
//        if(inventoryUnitPosition == 0) mUnit = InventoryItem.InventoryUnit.EACH;
//        else if(inventoryUnitPosition == 1) mUnit = InventoryItem.InventoryUnit.BUNCH;
//        else if(inventoryUnitPosition == 2) mUnit = InventoryItem.InventoryUnit.CASE;
//        else if(inventoryUnitPosition == 3) mUnit = InventoryItem.InventoryUnit.KG;
//        else if(inventoryUnitPosition == 4) mUnit = InventoryItem.InventoryUnit.LB;
//        else if(inventoryUnitPosition == 5) mUnit = InventoryItem.InventoryUnit.OZ;
//        else if(inventoryUnitPosition == 6) mUnit = InventoryItem.InventoryUnit.DOZEN;
//        else mUnit = null;

        //Get the id number of the last RequestItem in the arraylist and increment it
        List<RequestItem> allRequestItems = mRespository.getAllRequestItems();
        if(allRequestItems.size()>0) {
            mId = allRequestItems.get(allRequestItems.size() - 1).getRequestitem_id();
            mId++;
        }
        else mId = 1;

        //Get a LocalDateTime for a timestamp
//        Long nowMillis = System.currentTimeMillis();
//        LocalDate nowLD = Converters.fromEpochMilliToLocalDate(nowMillis);

        //Create an RequestItem object using the user-entered information from the detail fragment and insert it into the Database
        try {
            mProdID = Integer.parseInt(String.valueOf(FruitReqDetailFragment.mTvProductID.getText()));

            mAmount = Double.parseDouble(String.valueOf(FruitReqDetailFragment.mEtAmount.getText()));
        } catch(Exception e) {
            showErrorMsg();
        }

        try {
            RequestItem item = new RequestItem(
                    mId,
                    mProdID,
                    LoginActivity.mEmployeeID,
                    mAmount,
                    false, true);

            mRespository.insertRequestItem(item);

            //Create a snackbar that confirms the transaction
            confirmInventory();

        } catch (Exception e) {
            e.printStackTrace();

            //Snackbar error message
            showErrorMsg();
        }
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) view = new View(this);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void showErrorMsg() {
        Snackbar errorInsertInventory = Snackbar.make(fruitreq, "There was an error submitting this item", Snackbar.LENGTH_LONG);
        errorInsertInventory.setBackgroundTint(Color.WHITE);
        TextView errorText = (TextView) (errorInsertInventory.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        errorText.setTextSize(32);
        errorText.setTextColor(Color.BLACK);
        errorInsertInventory.show();
    }
    private void confirmInventory() {
        Snackbar confirmationInventory = Snackbar.make(fruitreq, "This item has been submitted", Snackbar.LENGTH_LONG);
        confirmationInventory.setBackgroundTint(Color.WHITE);
        TextView confirmText = (TextView) (confirmationInventory.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        confirmText.setTextSize(32);
        confirmText.setTextColor(Color.BLACK);
        confirmationInventory.show();
    }


}