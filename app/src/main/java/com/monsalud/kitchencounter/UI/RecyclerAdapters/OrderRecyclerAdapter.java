package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.FtsOptions;

import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.Entity.Vendor;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.UI.Order.OrderFragment;
import com.monsalud.kitchencounter.UI.Order.OrderMainActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder>
     implements Filterable{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<OrderView> mAllActiveOrderViews;
    private List<OrderView> mAllOrderViewsFull;
    private List<OrderView> mFilteredList;
    public OrderFragment mOrderFragment;

    private int mOrderID;
    private InventoryItem.InventoryUnit mOrdUnit;
    private String mVendorName;

    public OrderRecyclerAdapter(Context context, List<OrderView> orderEntries) {
        mContext = context;
        mAllActiveOrderViews = orderEntries;
        mAllOrderViewsFull = new ArrayList<>(mAllActiveOrderViews);
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public OrderRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_orders, parent, false);
        return new OrderRecyclerAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderRecyclerAdapter.ViewHolder holder, int position) {

        //extract the order item from the orderview, then get the order item's order unit and vendor name
        OrderView orderEntry = mAllActiveOrderViews.get(position);
        for(OrderItem orderItem : OrderMainActivity.mAllOrderItems) {
            if(orderItem.getProduct_id_fk() == orderEntry.getProduct_id()) {
                mOrdUnit = orderItem.getOrder_unit();
                //mVendorName = orderItem.getVendor_name();
//                int vendorID = orderItem.getVendor_id_fk();
//                for(Vendor vendor : OrderMainActivity.mAllVendors) {
//                    if(vendor.getVendor_id() == vendorID) {
//                        mVendorName = vendor.getVendor_name();
//                    }
//                }
            }
        };
        //get the remaining values from the orderview
        mOrderID = orderEntry.getOrderitem_id();
        String productName = orderEntry.getProduct_name();
        String productDescription = orderEntry.getProduct_description();
        double invAmount = orderEntry.getInventory_amount();
        InventoryItem.InventoryUnit invUnit = orderEntry.getDefault_unit();
        double reqAmount = orderEntry.getRequest_amount();
        double ordAmount = orderEntry.getOrder_amount();
        String vendorName = orderEntry.getVendor_name();
        LocalDate deliveryDate = orderEntry.getDelivery_date();

        //set the holder with the current order item's values
        holder.mTvItem.setText(productName);
        holder.mTvDescription.setText(productDescription);
        holder.mTvInvUnit.setText(invUnit.toString());
        holder.mTvInventory.setText(String.valueOf(invAmount));
        holder.mTvRequest.setText(String.valueOf(reqAmount));
        holder.mTvOrder.setText(String.valueOf(ordAmount));
        holder.mTvVendor.setText(vendorName);

        if(mOrdUnit != null)
        holder.mTvOrdUnit.setText(mOrdUnit.toString());
        else {mOrdUnit = InventoryItem.InventoryUnit.CASE;
        holder.mTvOrdUnit.setText(mOrdUnit.toString());
        }

        if(deliveryDate != null)
        holder.mTvDeliveryDate.setText(deliveryDate.toString());
        else {deliveryDate = LocalDate.now().plusDays(1);
        holder.mTvDeliveryDate.setText(deliveryDate.toString());
        }
    }

    @Override
    public int getItemCount() {
        return mAllActiveOrderViews.size();
    }

    @Override
    public Filter getFilter() {
        return orderFilter;
    }
    private Filter orderFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            mFilteredList = new ArrayList<>();
            
            if(constraint == null || constraint.length() == 0) {
                mFilteredList.addAll(mAllOrderViewsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(OrderView orderView: mAllOrderViewsFull) {
                    if(orderView.getProduct_name().toLowerCase().contains(filterPattern)) {
                        mFilteredList.add(orderView);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = mFilteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mAllActiveOrderViews.clear();
            mAllActiveOrderViews.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTvItem;
        public final TextView mTvDescription;
        public final TextView mTvInventory;
        public final TextView mTvInvUnit;
        public final TextView mTvRequest;
        public final TextView mTvOrder;
        public final TextView mTvOrdUnit;
        public final TextView mTvVendor;
        public final TextView mTvDeliveryDate;
        public final ImageView mIvAdd;

        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.tvITEM_orderitem);
            mTvDescription = (TextView) itemView.findViewById(R.id.tvDESCRIPTION_orderitem);
            mTvInventory = (TextView) itemView.findViewById(R.id.tvINVENTORY_orderitem);
            mTvInvUnit = (TextView) itemView.findViewById(R.id.tvInventoryUnit_orderitem);
            mTvRequest = (TextView) itemView.findViewById(R.id.tvREQUEST_orderitem);
            mTvOrder = (TextView) itemView.findViewById(R.id.tvORDER_orderitem);
            mTvOrdUnit = (TextView) itemView.findViewById(R.id.tvOrderUnit_orderitem);
            mTvVendor = (TextView) itemView.findViewById(R.id.tvVENDOR_orderitem);
            mTvDeliveryDate = (TextView) itemView.findViewById(R.id.tvDELIVERYDATE_orderitem);
            mIvAdd = (ImageView) itemView.findViewById(R.id.ivADDTOORDERS_orderitem);

            mIvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM-dd");
                    DateTimeFormatter dtfLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String productName = mTvItem.getText().toString();
                    String productDescription = mTvDescription.getText().toString();
                    double inventoryAmount = Double.parseDouble(String.valueOf(mTvInventory.getText()));
                    double requestAmount = Double.parseDouble(String.valueOf(mTvRequest.getText()));
                    String invUnit = mTvInvUnit.getText().toString();
                    double orderAmount = Double.parseDouble(String.valueOf(mTvOrder.getText()));
                    String ordUnit = mTvInvUnit.getText().toString();
                    String vendorName = mTvVendor.getText().toString();
                    String deliveryDate = mTvDeliveryDate.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("product_name", productName);
                    bundle.putString("product_description", productDescription);
                    bundle.putDouble("inventory_amount", inventoryAmount);
                    bundle.putString("inventory_unit", invUnit);
                    bundle.putDouble("request_amount", requestAmount);
                    bundle.putDouble("order_amount", orderAmount);
                    bundle.putString("order_unit", ordUnit);
                    bundle.putString("vendor_name", vendorName);
                    bundle.putString("delivery_date", deliveryDate);

                    mOrderFragment = new OrderFragment();
                    mOrderFragment.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.constraintLayout, mOrderFragment)
                            .addToBackStack(null)
                            .commit();

                    };
            });
        }
    }
}
