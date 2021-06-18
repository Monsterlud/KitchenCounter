package com.monsalud.kitchencounter.UI.Inventory.DryGood;

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


public class DryGoodsInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static int mProductID;
    static Button mBtnAddToInventory;

    static TextView mTvDryGoodName;
    static TextView mTvDryGoodCategory;
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
        View view = inflater.inflate(R.layout.fragment_dry_good_inv_detail, container, false);
        mTvDryGoodName = (TextView) view.findViewById(R.id.tvDryGoodName_drygooddetail);
        mTvDryGoodCategory = (TextView) view.findViewById(R.id.tvDryGoodCategory_drygooddetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_drygooddetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_drygooddetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_drygooddetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_drygooddetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_drygooddetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_drygooddetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);


        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        InventoryItem.InventoryLocation.values()));

        return view;
    }

    public static void updateFragment(CharSequence drygoodName, CharSequence drygoodCategory, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvDryGoodName.setText(String.valueOf(drygoodName));
        mTvDryGoodCategory.setText(drygoodCategory);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DryGoodsInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (DryGoodsInvDetailFragment.OnAddToInventoryListener) context;
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