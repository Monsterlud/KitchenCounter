package com.monsalud.kitchencounter.UI.Request.Dairy;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.Fruit.FruitInvDetailFragment;
import com.monsalud.kitchencounter.UI.LoginActivity;
import com.monsalud.kitchencounter.UI.Request.Beverage.BeverageReqDetailFragment;

import java.time.LocalDate;
import java.util.Calendar;


public class DairyReqDetailFragment extends Fragment {

    static Button mBtnAddToRequests;
    static EditText mEtAmount;
    static String mDate;

    static TextView mTvProductID;
    static TextView mTvDairyName;
    static TextView mTvDairyType;
    static TextView mTvEmployeeName;
    static TextView mTvUnit;

    static TextView mTvDeliveryDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public static InventoryItem.InventoryUnit mUnit;

    private OnAddToRequestsListener mOnAddToRequestsListener;


    public interface OnAddToRequestsListener {
        void onAddToRequests();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dairy_req_detail, container, false);
        mTvDairyName = (TextView) view.findViewById(R.id.tvDairyName_reqdairydetail);
        mTvDairyType = (TextView) view.findViewById(R.id.tvDairyType_reqdairydetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_reqdairydetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_reqdairydetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_reqdairydetail);
        mBtnAddToRequests = (Button) view.findViewById(R.id.btnADDTOREQUESTS_reqdairydetail);
        mBtnAddToRequests.setOnClickListener(buttonAddRequestsListener);

        mEtAmount = (EditText) view.findViewById(R.id.etAmount_reqdairydetail);

        mTvEmployeeName.setText(LoginActivity.mEmployeeName);


        //Set OnClickListener to the start date's textview
//        mTvDeliveryDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(
//                        getContext(),
//                        android.R.style.Widget_Material,
//                        mDateSetListener,
//                        year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
//                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//                params.gravity = Gravity.CENTER;
//                params.width = 900;
//                params.height = 1500;
//                dialog.show();
//            }
//        });
//
//        //Set the course date's textview with the date chosen by the DatePickerDialog
//        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                month++;
//                if (month < 10) {
//                    if(dayOfMonth < 10)
//                        mDate = year + "-0" + month + "-0" + dayOfMonth;
//                    else
//                        mDate = year + "-0" + month + "-" + dayOfMonth;
//                }
//
//                mTvDeliveryDate.setText(mDate);
//            }
//        };
        return view;
    }

    public static void updateFragment(CharSequence dairyName, CharSequence dairyType, int productID, InventoryItem.InventoryUnit unit) {
        mUnit = unit;
        mTvDairyName.setText(String.valueOf(dairyName));
        mTvDairyType.setText(dairyType);
        mTvProductID.setText(String.valueOf(productID));
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvUnit.setText(unit.toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DairyReqDetailFragment.OnAddToRequestsListener) {
            mOnAddToRequestsListener = (DairyReqDetailFragment.OnAddToRequestsListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAddToRequestsListener");
        }
    }
    private View.OnClickListener buttonAddRequestsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOnAddToRequestsListener.onAddToRequests();
        }
    };
}