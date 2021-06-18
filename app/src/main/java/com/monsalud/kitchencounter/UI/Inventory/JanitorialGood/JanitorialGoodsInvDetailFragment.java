package com.monsalud.kitchencounter.UI.Inventory.JanitorialGood;

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


public class JanitorialGoodsInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static int mProductID;
    static Button mBtnAddToInventory;

    static TextView mTvJanitorialGoodName;
    static TextView mTvJanitorialGoodCategory;
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
        View view = inflater.inflate(R.layout.fragment_janitorial_good_inv_detail, container, false);
        mTvJanitorialGoodName = (TextView) view.findViewById(R.id.tvJanitorialGoodName_janitorialgooddetail);
        mTvJanitorialGoodCategory = (TextView) view.findViewById(R.id.tvJanitorialGoodCategory_janitorialgooddetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_janitorialgooddetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_janitorialgooddetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_janitorialgooddetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_janitorialgooddetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_janitorialgooddetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_janitorialgooddetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);


        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        InventoryItem.InventoryLocation.values()));

        return view;
    }

    public static void updateFragment(CharSequence janitorialName, CharSequence janitorialCategory, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvJanitorialGoodName.setText(String.valueOf(janitorialName));
        mTvJanitorialGoodCategory.setText(janitorialCategory);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof JanitorialGoodsInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (JanitorialGoodsInvDetailFragment.OnAddToInventoryListener) context;
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