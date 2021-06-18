package com.monsalud.kitchencounter.UI.Request.JanitorialGood;

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
import com.monsalud.kitchencounter.UI.Request.Dairy.DairyReqDetailFragment;

import java.time.LocalDate;
import java.util.Calendar;


public class JanitorialGoodsReqDetailFragment extends Fragment {

    static TextView mTvProductID;
    static Button mBtnAddToRequests;
    static EditText mEtAmount;
    static String mDate;

    static TextView mTvJanitorialGoodName;
    static TextView mTvJanitorialGoodCategory;
    static TextView mTvEmployeeName;
    static TextView mTvUnit;
    public static InventoryItem.InventoryUnit mUnit;

    static TextView mTvDeliveryDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
        View view = inflater.inflate(R.layout.fragment_janitorial_goods_req_detail, container, false);
        mTvJanitorialGoodName = (TextView) view.findViewById(R.id.tvJanitorialGoodName_reqjanitorialgooddetail);
        mTvJanitorialGoodCategory = (TextView) view.findViewById(R.id.tvJanitorialGoodCategory_reqjanitorialgooddetail);
        mTvEmployeeName = (TextView) view.findViewById(R.id.tvEmployeeName_reqjanitorialgooddetail);
        mTvUnit = (TextView) view.findViewById(R.id.tvUnit_reqjanitorialgooddetail);
        mTvProductID = (TextView) view.findViewById(R.id.tvProductId_reqjanitorialgooddetail);
        mBtnAddToRequests = (Button) view.findViewById(R.id.btnADDTOREQUESTS_reqjanitorialgooddetail);
        mBtnAddToRequests.setOnClickListener(buttonAddRequestsListener);

        mEtAmount = (EditText) view.findViewById(R.id.etAmount_reqjanitorialgooddetail);

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

    public interface insertInventoryItem {
        public void insertProduct(Product product);
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
        if (context instanceof JanitorialGoodsReqDetailFragment.OnAddToRequestsListener) {
            mOnAddToRequestsListener = (JanitorialGoodsReqDetailFragment.OnAddToRequestsListener) context;
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