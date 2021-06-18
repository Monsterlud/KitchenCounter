package com.monsalud.kitchencounter.UI.Request.JanitorialGood;

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
import com.monsalud.kitchencounter.Entity.JanitorialGood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.RequestItem;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.JanitorialGoodRecyclerAdapter;
import com.monsalud.kitchencounter.UI.Request.Beverage.BeverageReqDetailFragment;
import com.monsalud.kitchencounter.UI.Request.RequestReviewActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JanitorialGoodsReqActivity extends AppCompatActivity
        implements JanitorialGoodRecyclerAdapter.JanitorialGoodRecyclerListener, JanitorialGoodsReqDetailFragment.OnAddToRequestsListener {

    JanitorialGoodRecyclerAdapter mJanitorialGoodRecyclerAdapter;
    KCRepository mRespository;

    ConstraintLayout janitorialgoodreq;
    int mId;
    private InventoryItem.InventoryUnit mUnit;
    private int mProdID;
    private LocalDate mDeliveryDate;
    private Double mAmount;

    public static List<JanitorialGood> mJanitorialGoods;
    private TextView mTvRequestReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_janitorial_good_req);

        mRespository = new KCRepository(getApplication());
        mJanitorialGoods = mRespository.getAllJanitorialGoods();
        janitorialgoodreq = findViewById(R.id.janitorialgoodreq);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.detailLayout_reqjanitorialgood, new JanitorialGoodsReqDetailFragment());
        ft.replace(R.id.listLayout_reqjanitorialgood, new JanitorialGoodsReqListFragment());
        ft.commit();

        mTvRequestReview = findViewById(R.id.tvReviewRequests_reqjanitorialgood);
        mTvRequestReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JanitorialGoodsReqActivity.this, RequestReviewActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onJanitorialGoodRecyclerSent(CharSequence janitorialgoodName, CharSequence janitorialgoodCategory, int productID, InventoryItem.InventoryUnit unit) {
        JanitorialGoodsReqDetailFragment.updateFragment(janitorialgoodName, janitorialgoodCategory, productID, unit);
    }

    @Override
    public void onAddToRequests() {

        closeKeyboard();

        //Get the InventoryUnit value
        mUnit = JanitorialGoodsReqDetailFragment.mUnit;
//        int inventoryUnitPosition = JanitorialGoodsReqDetailFragment.mSpinUnit.getSelectedItemPosition();
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
            mProdID = Integer.parseInt(String.valueOf(JanitorialGoodsReqDetailFragment.mTvProductID.getText()));
            mAmount = Double.parseDouble(String.valueOf(JanitorialGoodsReqDetailFragment.mEtAmount.getText()));
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
        Snackbar errorInsertInventory = Snackbar.make(janitorialgoodreq, "There was an error submitting this item", Snackbar.LENGTH_LONG);
        errorInsertInventory.setBackgroundTint(Color.WHITE);
        TextView errorText = (TextView) (errorInsertInventory.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        errorText.setTextSize(32);
        errorText.setTextColor(Color.BLACK);
        errorInsertInventory.show();
    }
    private void confirmInventory() {
        Snackbar confirmationInventory = Snackbar.make(janitorialgoodreq, "This item has been submitted", Snackbar.LENGTH_LONG);
        confirmationInventory.setBackgroundTint(Color.WHITE);
        TextView confirmText = (TextView) (confirmationInventory.getView()).findViewById(com.google.android.material.R.id.snackbar_text);
        confirmText.setTextSize(32);
        confirmText.setTextColor(Color.BLACK);
        confirmationInventory.show();
    }
    }
