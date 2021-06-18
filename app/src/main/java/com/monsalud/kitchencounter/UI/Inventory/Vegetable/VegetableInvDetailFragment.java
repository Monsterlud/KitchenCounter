package com.monsalud.kitchencounter.UI.Inventory.Vegetable;

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

import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.LoginActivity;


public class VegetableInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static Button mBtnAddToInventory;

    static TextView mTvVegetableName;
    static TextView mTvVegetableVarietalName;
    static TextView mTvEmployeeName;
    static Spinner mSpinLocation;
    static TextView mTvUnit;
    static EditText mEtAmount;

    public static InventoryItem.InventoryUnit mUnit;

    static int mProductID;
    static String mProductName;
    static String mProductDescription;
    static double mAmount;

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

        // Inflate the layout for this fragment...add Employee Name
        View view = inflater.inflate(R.layout.fragment_vegetable_inv_detail, container, false);
        mTvVegetableName = (TextView) view.findViewById(R.id.tvVegetableName_producedetail);
        mTvVegetableVarietalName = (TextView) view.findViewById(R.id.tvVegetableVarietalName_producedetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_producedetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_producedetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_producedetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_producedetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_producedetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_producedetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);



        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                    android.R.layout.simple_spinner_item,
                    InventoryItem.InventoryLocation.values()));

        return view;
    }

    public static void updateFragment(CharSequence vegName, CharSequence vegVarietal, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvVegetableName.setText(String.valueOf(vegName));
        mTvVegetableVarietalName.setText(vegVarietal);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof VegetableInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (VegetableInvDetailFragment.OnAddToInventoryListener) context;
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