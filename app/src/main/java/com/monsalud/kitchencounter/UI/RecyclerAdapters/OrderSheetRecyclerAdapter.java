package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.Tables.OrderItem;
import com.monsalud.kitchencounter.Tables.OrderView;
import com.monsalud.kitchencounter.UI.Order.OrderFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderSheetRecyclerAdapter extends RecyclerView.Adapter<OrderSheetRecyclerAdapter.ViewHolder> {

    //implements Filterable
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<OrderView> mAllActiveOrderViews;
    private List<OrderView> mAllOrderViewsFull;
    private List<OrderView> mFilteredList;
    public OrderFragment mOrderFragment;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnOrderedClick(int position);
        void OnDeleteClick(int orderID, int position);
    }
    public void SetOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public OrderSheetRecyclerAdapter(Context context, List<OrderView> orderViews) {
        mContext = context;
        mAllActiveOrderViews = orderViews;
        mAllOrderViewsFull = new ArrayList<>(mAllActiveOrderViews);
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public OrderSheetRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_ordersheet, parent, false);
        return new OrderSheetRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderSheetRecyclerAdapter.ViewHolder holder, int position) {
        OrderView orderItem = mAllActiveOrderViews.get(position);

        //set the holder with the current order item's values
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MM-dd");

        String productName = orderItem.getProduct_name();
        String productDescription = orderItem.getProduct_description();
        InventoryItem.InventoryUnit invUnit = orderItem.getDefault_unit();
        double invAmount = orderItem.getInventory_amount();
        double reqAmount = orderItem.getRequest_amount();
        double ordAmount = orderItem.getOrder_amount();
        InventoryItem.InventoryUnit ordUnit = orderItem.getOrder_unit();
        LocalDate deliveryDate = orderItem.getDelivery_date();

        holder.mTvItem.setText(productName);
        holder.mTvDescription.setText(productDescription);
        holder.mTvInvUnit.setText(invUnit.toString());
        holder.mTvInventory.setText("inv -" + invAmount);
        holder.mTvRequest.setText("req -" + reqAmount);
        holder.mTvOrder.setText("ord -" + ordAmount);
        holder.mTvOrdUnit.setText(ordUnit.toString());
        holder.mTvDeliveryDate.setText(deliveryDate.toString());

//        if(!orderItem.isPlaced()) {
//            holder.mIvOrdered.setBackgroundResource(R.drawable.ic_baseline_check_box_dark);
//        }
//        else holder.mIvOrdered.setBackgroundResource(R.drawable.ic_baseline_check_box_light);
    }

    @Override
    public int getItemCount() {
        return mAllActiveOrderViews.size();
    }

//    @Override
//    public Filter getFilter() {
//        return orderFilter;
//    }
//    private Filter orderFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            mFilteredList = new ArrayList<>();
//
//            if(constraint == null || constraint.length() == 0) {
//                mFilteredList.addAll(mAllOrderViewsFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for(OrderView orderView: mAllOrderViewsFull) {
//                    if(orderView.getProduct_name().toLowerCase().contains(filterPattern)) {
//                        mFilteredList.add(orderView);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = mFilteredList;
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mAllActiveOrderViews.clear();
//            mAllActiveOrderViews.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTvItem;
        public final TextView mTvDescription;
        public final TextView mTvInvUnit;
        public final TextView mTvInventory;
        public final TextView mTvRequest;
        public final TextView mTvOrder;
        public final TextView mTvOrdUnit;
        public final TextView mTvDeliveryDate;
//        private final ImageView mIvOrdered;
//        private final ImageView mIvDelete;

        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.tvITEM_ordersheetitem);
            mTvDescription = (TextView) itemView.findViewById(R.id.tvDESCRIPTION_ordersheetitem);
            mTvInvUnit = (TextView) itemView.findViewById(R.id.tvInventoryUnit_ordersheetitem);
            mTvInventory = (TextView) itemView.findViewById(R.id.tvINVENTORY_ordersheetitem);
            mTvRequest = (TextView) itemView.findViewById(R.id.tvREQUEST_ordersheetitem);
            mTvOrder = (TextView) itemView.findViewById(R.id.tvORDER_ordersheetitem);
            mTvOrdUnit = (TextView) itemView.findViewById(R.id.tvOrderUnit_ordersheetitem);
            mTvDeliveryDate = (TextView) itemView.findViewById(R.id.tvDELIVERYDATE_ordersheetitem);
//            mIvOrdered = (ImageView) itemView.findViewById(R.id.ivOrdered_ordersheetitem);
//            mIvDelete = (ImageView) itemView.findViewById(R.id.ivDelete_ordersheetitem);

//            mIvOrdered.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mListener != null) {
//                        int position = getAbsoluteAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION) {
//                            OrderView orderView = mAllActiveOrderViews.get(position);
//                            mListener.OnOrderedClick(orderView.getOrderitem_id());
//                        }
//                    }
//                }
//            });
//            mIvDelete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mListener != null) {
//                        int position = getAbsoluteAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION) {
//                            OrderView orderView = mAllActiveOrderViews.get(position);
//                            mListener.OnDeleteClick(orderView.getOrderitem_id(), position);
//                        }
//                    }
//                }
//            });
        }
    }
}
