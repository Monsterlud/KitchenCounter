package com.monsalud.kitchencounter.UI.Order;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Vendor;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.LoginActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class OrderFragment extends Fragment {

    Button mBtnOrder;
    Button mBtnCancelOrder;

    private OnOrderListener mOnOrderListener;
    private OnCancelOrderListener mOnCancelOrderListener;

    public static TextView mTvEmployeeName;
    public static TextView mTvTodaysDate;
    public static TextView mTvProductName;
    public static TextView mTvProductDescription;
    public static TextView mTvInventoryAmount;
    public static TextView mTvInvUnit;
    public static TextView mTvRequestAmount;
    public static EditText mTvOrderAmount;
    public static Spinner mSpinOrderUnit;
    public static TextView mTvDeliveryDate;
    public static Spinner mSpinVendor;

    public static String mProduct_name;
    public static String mProduct_description;
    public static String mInventory_unit;
    public static Double mInventory_amount;
    public static Double mRequest_amount;
    public static Double mOrder_amount;
    public static String mOrder_unit;
    public static String mDelivery_date;
    public static String mVendor_name;

    public static LocalDate mLdDeliveryDate;

    private List<Vendor> mAllVendors;
    private KCRepository mRepository;
    private DatePickerDialog.OnDateSetListener mOnDateSetListenerOrder;
    public static String mFormattedDate;



    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        mRepository = new KCRepository(getActivity().getApplication());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E MMM dd, yyyy");
        DateTimeFormatter dtfLD = DateTimeFormatter.ofPattern("E, MM-dd");

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            //get all bundle values that were passed from the item
            mProduct_name = bundle.get("product_name").toString();
            mProduct_description = bundle.get("product_description").toString();
            mInventory_unit = bundle.get("inventory_unit").toString();
            mInventory_amount = Double.parseDouble(bundle.get("inventory_amount").toString());
            mRequest_amount = Double.parseDouble(bundle.get("request_amount").toString());
            mOrder_amount = Double.parseDouble(bundle.get("order_amount").toString());
            mOrder_unit = bundle.get("order_unit").toString();
            mVendor_name = bundle.get("vendor_name").toString();
            mDelivery_date = bundle.get("delivery_date").toString();
        }
        //attach all local views to the layout resource
        mTvEmployeeName = view.findViewById(R.id.tvEmployeeName_orderfragment);
        mTvTodaysDate = view.findViewById(R.id.tvTodayDate_orderfragment);
        mTvProductName = view.findViewById(R.id.tvProductName_orderfragment);
        mTvProductDescription = view.findViewById(R.id.tvProductDescription_orderfragment);
        mTvInvUnit = view.findViewById(R.id.tvInvUnit_orderfragment);
        mTvInventoryAmount = view.findViewById(R.id.tvInventoryAmount_orderfragment);
        mTvRequestAmount = view.findViewById(R.id.tvRequestAmount_orderfragment);
        mTvOrderAmount = view.findViewById(R.id.etOrderAmount_orderfragment);
        mSpinOrderUnit = view.findViewById(R.id.spinUnit_orderfragment);
        mTvDeliveryDate = view.findViewById(R.id.tvDeliveryDate_orderfragment);
        mSpinVendor = view.findViewById(R.id.spinVendor_orderfragment);

        mBtnOrder = view.findViewById(R.id.btnOrder_orderfragment);
        mBtnCancelOrder = view.findViewById(R.id.btnCancel_orderfragment);


        //set all local views with values retrieved from the bundle
        mTvEmployeeName.setText(LoginActivity.mEmployeeName);
        mTvTodaysDate.setText(LocalDate.now().format(dtf));
        mTvProductName.setText(mProduct_name);
        mTvProductDescription.setText(mProduct_description);
        mTvInvUnit.setText(mInventory_unit);
        mTvInventoryAmount.setText(String.valueOf(mInventory_amount));
        mTvRequestAmount.setText(String.valueOf(mRequest_amount));
        mTvOrderAmount.setText(String.valueOf(mOrder_amount));
        mTvDeliveryDate.setText(String.valueOf(mDelivery_date));

        mSpinOrderUnit.setAdapter(new ArrayAdapter<InventoryItem.InventoryUnit>(getContext(), android.R.layout.simple_spinner_item, InventoryItem.InventoryUnit.values()));

        mLdDeliveryDate = LocalDate.parse(mDelivery_date);

        if(mInventory_unit.equals("EACH")) mSpinOrderUnit.setSelection(0);
        else if(mInventory_unit.equals("BUNCH")) mSpinOrderUnit.setSelection(1);
        else if(mInventory_unit.equals("CASE")) mSpinOrderUnit.setSelection(2);
        else if(mInventory_unit.equals("KG")) mSpinOrderUnit.setSelection(3);
        else if(mInventory_unit.equals("LB")) mSpinOrderUnit.setSelection(4);
        else if(mInventory_unit.equals("OZ")) mSpinOrderUnit.setSelection(5);
        else if(mInventory_unit.equals("DOZEN")) mSpinOrderUnit.setSelection(6);
        else mSpinOrderUnit.setSelection(0);


        mAllVendors = mRepository.getAllVendors();
        ArrayAdapter<Vendor> vAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mAllVendors);
        vAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinVendor.setAdapter(vAdapter);

        if(mVendor_name.contains("Farmer")) mSpinVendor.setSelection(0);
        else if(mVendor_name.contains("Organic")) mSpinVendor.setSelection(1);
        else if(mVendor_name.contains("Ranch")) mSpinVendor.setSelection(2);
        else if(mVendor_name.contains("Fatted")) mSpinVendor.setSelection(3);
        else if(mVendor_name.contains("Really")) mSpinVendor.setSelection(4);
        else if(mVendor_name.contains("Betsy")) mSpinVendor.setSelection(5);
        else if(mVendor_name.contains("Pacific")) mSpinVendor.setSelection(6);
        else if(mVendor_name.contains("Regal")) mSpinVendor.setSelection(7);
        else mSpinVendor.setSelection(0);

        mBtnOrder.setOnClickListener(buttonOrderListener);
        mBtnCancelOrder.setOnClickListener(buttonCanceOrderListener);

        mTvOrderAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvOrderAmount.setText("");
            }
        });

        mTvDeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Widget_Material,
                        mOnDateSetListenerOrder,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.gravity = Gravity.CENTER;
                params.width = 900;
                params.height = 1500;
                dialog.show();
            }
        });

        mOnDateSetListenerOrder = new DatePickerDialog.OnDateSetListener() {
            String orderDate;

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                if (month < 10) {
                    if (dayOfMonth < 10)
                        orderDate = year + "-0" + month + "-0" + dayOfMonth;
                    else
                        orderDate = year + "-0" + month + "-" + dayOfMonth;
                }

                updateLabelOrderDate(orderDate);
            }
        };
        return view;
    }
    private void updateLabelOrderDate(String date) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM-dd");
//        LocalDate ld = LocalDate.parse(date);
//        mFormattedDate = dtf.format(ld);
//        mLdDeliveryDate = ld;
        mTvDeliveryDate.setText(date);
    }
    public interface OnOrderListener {
        void onOrder();
    }
    public interface OnCancelOrderListener {
        void onCancelOrder();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCancelOrderListener) {
            mOnCancelOrderListener = (OnCancelOrderListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + "must implement OnCancelOrderListener");
        }
        if(context instanceof OnOrderListener) {
            mOnOrderListener = (OnOrderListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + " must implement OnOrderListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnOrderListener = null;
        mOnCancelOrderListener = null;
    }
    private View.OnClickListener buttonCanceOrderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOnCancelOrderListener.onCancelOrder();
        }
    };
    private View.OnClickListener buttonOrderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mOnOrderListener.onOrder();
        }
    };
}