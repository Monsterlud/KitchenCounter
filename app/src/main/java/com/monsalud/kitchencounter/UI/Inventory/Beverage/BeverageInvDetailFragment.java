package com.monsalud.kitchencounter.UI.Inventory.Beverage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.Fruit.FruitInvDetailFragment;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvDetailFragment;
import com.monsalud.kitchencounter.UI.LoginActivity;

import java.util.List;


public class BeverageInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static int mProductID;
    static Button mBtnAddToInventory;

    static TextView mTvBeverageName;
    static TextView mTvBeverageType;
    static TextView mTvEmployeeName;
    static Spinner mSpinLocation;
    static TextView mTvUnit;
    static EditText mEtAmount;

    public static InventoryItem.InventoryUnit mUnit;


    private OnAddToInventoryListener mOnAddToInventoryListener;


    public interface OnAddToInventoryListener {
        void onAddToInventory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beverage_inv_detail, container, false);
        mTvBeverageName = (TextView) view.findViewById(R.id.tvBeverageName_beveragedetail);
        mTvBeverageType = (TextView) view.findViewById(R.id.tvBeverageType_beveragedetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_beveragedetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_beveragedetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_beveragedetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_beveragedetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_beveragedetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_beveragedetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);

        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        InventoryItem.InventoryLocation.values()));

        return view;
    }

    public static void updateFragment(CharSequence beverageName, CharSequence beverageType, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvBeverageName.setText(String.valueOf(beverageName));
        mTvBeverageType.setText(beverageType);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BeverageInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (BeverageInvDetailFragment.OnAddToInventoryListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddToInventoryListener");
        }
    }
    private View.OnClickListener buttonAddInventoryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOnAddToInventoryListener.onAddToInventory();
        }
    };
}