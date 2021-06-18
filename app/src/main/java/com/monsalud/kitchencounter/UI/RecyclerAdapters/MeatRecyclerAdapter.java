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

import com.monsalud.kitchencounter.Entity.Meat;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class MeatRecyclerAdapter extends RecyclerView.Adapter<MeatRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Meat> mMeats;
    private final LayoutInflater mLayoutInflater;
    public static MeatRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Meat.MeatType mMeatType;
    private Meat mMeat;


    public interface MeatRecyclerListener {
        void onMeatRecyclerSent(CharSequence meatName, CharSequence meatType, int productID, InventoryItem.InventoryUnit unit);
    }

    public MeatRecyclerAdapter(Context context, List<Meat> meats) {
        mContext = context;
        mMeats = meats;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MeatRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_meat_list, parent, false);
        return new MeatRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeatRecyclerAdapter.ViewHolder holder, int position) {
        mMeat = mMeats.get(position);

        //Get the Meat Type from the ordinal
        if(mMeat.getMeat_type() == 0) mMeatType = Meat.MeatType.BEEF;
        else if(mMeat.getMeat_type() == 1) mMeatType = Meat.MeatType.PORK;
        else if(mMeat.getMeat_type() == 2) mMeatType = Meat.MeatType.LAMB;
        else if(mMeat.getMeat_type() == 3) mMeatType = Meat.MeatType.GOAT;
        else if(mMeat.getMeat_type() == 4) mMeatType = Meat.MeatType.POULTRY;
        else if(mMeat.getMeat_type() == 5) mMeatType = Meat.MeatType.WILDGAME;
        else mMeatType = Meat.MeatType.OTHER;

        //Bind the data with the view holder
        holder.mTvMeatName.setText(mMeat.getProduct_name());
        holder.mTvMeatType.setText(mMeatType.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mMeat.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#F7AAAA"));
            mListener.onMeatRecyclerSent(mMeat.getProduct_name(), String.valueOf(mMeatType), mProductID, mMeat.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#F6DEDE"));
        }
    }

    @Override
    public int getItemCount() {
        return mMeats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvMeatName;
        public final TextView mTvMeatType;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_meatitem);
            mTvMeatName = (TextView) itemView.findViewById(R.id.tvMeatName_meatlistitem);
            mTvMeatType = (TextView) itemView.findViewById(R.id.tvMeatType_meatlistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onMeatRecyclerSent(mTvMeatName.getText(), mTvMeatType.getText(), mProductID, mMeat.getDefault_unit());
                }
            });
        }
    }
}
