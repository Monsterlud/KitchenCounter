package com.monsalud.kitchencounter.UI.Inventory.Meat;

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

import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.Fruit.FruitInvDetailFragment;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvDetailFragment;
import com.monsalud.kitchencounter.UI.LoginActivity;


public class MeatInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static int mProductID;
    static Button mBtnAddToInventory;

    static TextView mTvMeatName;
    static TextView mTvMeatType;
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
        View view = inflater.inflate(R.layout.fragment_meat_inv_detail, container, false);
        mTvMeatName = (TextView) view.findViewById(R.id.tvMeatName_meatdetail);
        mTvMeatType = (TextView) view.findViewById(R.id.tvMeatType_meatdetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_meatdetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_meatdetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_meatdetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_meatdetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_meatdetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_meatdetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);



        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        InventoryItem.InventoryLocation.values()));

        return view;
    }


    public static void updateFragment(CharSequence meatName, CharSequence meatType, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvMeatName.setText(String.valueOf(meatName));
        mTvMeatType.setText(meatType);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MeatInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (MeatInvDetailFragment.OnAddToInventoryListener) context;
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