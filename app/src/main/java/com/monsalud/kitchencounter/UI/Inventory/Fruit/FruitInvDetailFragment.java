package com.monsalud.kitchencounter.UI.Inventory.Fruit;

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
import com.monsalud.kitchencounter.Entity.Vegetable;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvDetailFragment;
import com.monsalud.kitchencounter.UI.LoginActivity;

public class FruitInvDetailFragment extends Fragment {

    static TextView mTvProductID;
    static int mProductID;
    static Button mBtnAddToInventory;

    static TextView mTvFruitName;
    static TextView mTvFruitVarietalName;
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
        View view = inflater.inflate(R.layout.fragment_fruit_inv_detail, container, false);
        mTvFruitName = (TextView) view.findViewById(R.id.tvFruitName_fruitdetail);
        mTvFruitVarietalName = (TextView) view.findViewById(R.id.tvFruitVarietalName_fruitdetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_fruitdetail);
        mEtAmount = (EditText) view.findViewById(R.id.etAmount_fruitdetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_fruitdetail);
        mSpinLocation = (Spinner) view.findViewById(R.id.spinLocation_fruitdetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_fruitdetail);
        mBtnAddToInventory = (Button) view.findViewById(R.id.btnADDTOINVENTORY_fruitdetail);
        mBtnAddToInventory.setOnClickListener(buttonAddInventoryListener);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);



        mSpinLocation.setAdapter(new ArrayAdapter<InventoryItem.InventoryLocation>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        InventoryItem.InventoryLocation.values()));

        return view;
    }

    public static void updateFragment(CharSequence fruitName, CharSequence fruitVarietal, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvFruitName.setText(String.valueOf(fruitName));
        mTvFruitVarietalName.setText(fruitVarietal);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FruitInvDetailFragment.OnAddToInventoryListener) {
            mOnAddToInventoryListener = (FruitInvDetailFragment.OnAddToInventoryListener) context;
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