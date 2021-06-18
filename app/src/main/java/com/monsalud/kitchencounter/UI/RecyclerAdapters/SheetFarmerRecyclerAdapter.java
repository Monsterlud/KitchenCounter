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

public class SheetFarmerRecyclerAdapter extends RecyclerView.Adapter<SheetFarmerRecyclerAdapter.ViewHolder> {

    //implements Filterable
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<OrderView> mAllActiveOrderViews;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnOrderedClick(int position);
        void OnDeleteClick(int position);
    }
    public void SetOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public SheetFarmerRecyclerAdapter(Context context, List<OrderView> orderViews) {
        mContext = context;
        mAllActiveOrderViews = orderViews;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public SheetFarmerRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_ordersheet_farmer, parent, false);
        return new SheetFarmerRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SheetFarmerRecyclerAdapter.ViewHolder holder, int position) {
        OrderView orderView = mAllActiveOrderViews.get(position);

        //set the holder with the current order item's values
        String productName = orderView.getProduct_name();
        String productDescription = orderView.getProduct_description();
        InventoryItem.InventoryUnit invUnit = orderView.getDefault_unit();
        double invAmount = orderView.getInventory_amount();
        double reqAmount = orderView.getRequest_amount();
        double ordAmount = orderView.getOrder_amount();
        InventoryItem.InventoryUnit ordUnit = orderView.getOrder_unit();
        LocalDate deliveryDate = orderView.getDelivery_date();

        holder.mTvItem.setText(productName);
        holder.mTvDescription.setText(productDescription);
        holder.mTvInvUnit.setText(invUnit.toString());
        holder.mTvInventory.setText("inv -" + invAmount);
        holder.mTvRequest.setText("req -" + reqAmount);
        holder.mTvOrder.setText("ord -" + ordAmount);
        holder.mTvOrdUnit.setText(ordUnit.toString());
        holder.mTvDeliveryDate.setText(deliveryDate.toString());

        if(!orderView.isPlaced()) {
            holder.mIvOrdered.setBackgroundResource(R.drawable.ic_baseline_check_box_dark);
        }
        else holder.mIvOrdered.setBackgroundResource(R.drawable.ic_baseline_check_box_light);
    }

    @Override
    public int getItemCount() {
        return mAllActiveOrderViews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTvItem;
        public final TextView mTvDescription;
        public final TextView mTvInvUnit;
        public final TextView mTvInventory;
        public final TextView mTvRequest;
        public final TextView mTvOrder;
        public final TextView mTvOrdUnit;
        public final TextView mTvDeliveryDate;
        private final ImageView mIvOrdered;
        private final ImageView mIvDelete;

        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(R.id.tvITEM_itemfarmer);
            mTvDescription = (TextView) itemView.findViewById(R.id.tvDESCRIPTION_itemfarmer);
            mTvInvUnit = (TextView) itemView.findViewById(R.id.tvInventoryUnit_itemfarmer);
            mTvInventory = (TextView) itemView.findViewById(R.id.tvINVENTORY_itemfarmer);
            mTvRequest = (TextView) itemView.findViewById(R.id.tvREQUEST_itemfarmer);
            mTvOrder = (TextView) itemView.findViewById(R.id.tvORDER_itemfarmer);
            mTvOrdUnit = (TextView) itemView.findViewById(R.id.tvOrderUnit_itemfarmer);
            mTvDeliveryDate = (TextView) itemView.findViewById(R.id.tvDELIVERYDATE_itemfarmer);
            mIvOrdered = (ImageView) itemView.findViewById(R.id.ivOrdered_itemfarmer);
            mIvDelete = (ImageView) itemView.findViewById(R.id.ivDelete_itemfarmer);

            mIvOrdered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            OrderView orderView = mAllActiveOrderViews.get(position);
                            mListener.OnOrderedClick(orderView.getOrderitem_id());
                        }
                    }
                }
            });
            mIvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            OrderView orderView = mAllActiveOrderViews.get(position);
                            mListener.OnDeleteClick(orderView.getOrderitem_id());
                        }
                    }
                }
            });
        }
    }
}
