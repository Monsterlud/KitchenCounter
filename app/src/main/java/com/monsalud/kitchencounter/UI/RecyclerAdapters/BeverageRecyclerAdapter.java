package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monsalud.kitchencounter.Entity.Beverage;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class BeverageRecyclerAdapter extends RecyclerView.Adapter<BeverageRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Beverage> mBeverages;
    private final LayoutInflater mLayoutInflater;
    public static BeverageRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Beverage.BeverageType mBeverageType;
    private Beverage mBeverage;

    public interface BeverageRecyclerListener {
        void onBeverageRecyclerSent(CharSequence beverageName, CharSequence beverageType, int productID, InventoryItem.InventoryUnit unit);
    }

    public BeverageRecyclerAdapter(Context context, List<Beverage> beverages) {
        mContext = context;
        mBeverages = beverages;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public BeverageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_beverage_list, parent, false);
        return new BeverageRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageRecyclerAdapter.ViewHolder holder, int position) {
        mBeverage = mBeverages.get(position);

        //Get the Beverage Type from the ordinal
        if(mBeverage.getBeverage_type() == 0) mBeverageType = Beverage.BeverageType.COFFEE;
        else if(mBeverage.getBeverage_type() == 1) mBeverageType = Beverage.BeverageType.TEA;
        else if(mBeverage.getBeverage_type() == 2) mBeverageType = Beverage.BeverageType.SOFTDRINKS;
        else if(mBeverage.getBeverage_type() == 3) mBeverageType = Beverage.BeverageType.WATER;
        else if(mBeverage.getBeverage_type() == 4) mBeverageType = Beverage.BeverageType.JUICE;
        else mBeverageType = Beverage.BeverageType.OTHER;

        //Bind the data with the view holder
        holder.mTvBeverageName.setText(mBeverage.getProduct_name());
        holder.mTvBeverageType.setText(mBeverageType.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mBeverage.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#C5B9B2"));
            mListener.onBeverageRecyclerSent(mBeverage.getProduct_name(), String.valueOf(mBeverageType), mProductID, mBeverage.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#EAE1DC"));
        }
    }

    @Override
    public int getItemCount() {
        return mBeverages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvBeverageName;
        public final TextView mTvBeverageType;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_beverageitem);
            mTvBeverageName = (TextView) itemView.findViewById(R.id.tvBeverageName_beveragelistitem);
            mTvBeverageType = (TextView) itemView.findViewById(R.id.tvBeverageType_beveragelistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onBeverageRecyclerSent(mTvBeverageName.getText(), mTvBeverageType.getText(), mProductID, mBeverage.getDefault_unit());
                }
            });
        }
    }
}
