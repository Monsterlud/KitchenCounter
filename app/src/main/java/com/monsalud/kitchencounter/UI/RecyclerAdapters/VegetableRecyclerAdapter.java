package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monsalud.kitchencounter.Entity.Vegetable;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvActivity;
import com.monsalud.kitchencounter.UI.Inventory.Vegetable.VegetableInvDetailFragment;

import java.util.List;

public class VegetableRecyclerAdapter extends RecyclerView.Adapter<VegetableRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Vegetable> mVegetables;
    private final LayoutInflater mLayoutInflater;
    public static VegetableRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Vegetable mVegetable;


    public interface VegetableRecyclerListener {
        void onVegRecyclerSent(CharSequence vegName, CharSequence vegVarietal, int productID, InventoryItem.InventoryUnit unit);
    }

    public VegetableRecyclerAdapter(Context context, List<Vegetable> vegetables) {
        mContext = context;
        mVegetables = vegetables;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_vegetable_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableRecyclerAdapter.ViewHolder holder, int position) {
        mVegetable = mVegetables.get(position);
        holder.mTvVegetableName.setText(mVegetable.getProduct_name());
        holder.mTvVegetableVarietalName.setText(mVegetable.getVegvarietal_name());
        holder.mCurrentPosition = position;


        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mVegetable.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#C3ECC3"));
            mListener.onVegRecyclerSent(mVegetable.getProduct_name(), mVegetable.getVegvarietal_name(), mProductID, mVegetable.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#E6FFE6"));
        }
    }

    @Override
    public int getItemCount() {
        return mVegetables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvVegetableName;
        public final TextView mTvVegetableVarietalName;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_vegetableitem);
            mTvVegetableName = (TextView) itemView.findViewById(R.id.tvVegetableName_vegetablelistitem);
            mTvVegetableVarietalName = (TextView) itemView.findViewById(R.id.tvVegetableVarietalName_vegetablelistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onVegRecyclerSent(mTvVegetableName.getText(), mTvVegetableVarietalName.getText(), mProductID, mVegetable.getDefault_unit());
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
