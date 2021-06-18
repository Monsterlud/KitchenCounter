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

import com.monsalud.kitchencounter.Entity.DryGood;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class DryGoodRecyclerAdapter extends RecyclerView.Adapter<DryGoodRecyclerAdapter.ViewHolder> {


    private final Context mContext;
    private List<DryGood> mDryGoods;
    private final LayoutInflater mLayoutInflater;
    public static DryGoodRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private DryGood.DryGoodsCategory mDryGoodsCategory;
    private DryGood mDryGood;


    public interface DryGoodRecyclerListener {
        void onDryGoodRecyclerSent(CharSequence drygoodName, CharSequence drygoodCategory, int productID, InventoryItem.InventoryUnit unit);
    }

    public DryGoodRecyclerAdapter(Context context, List<DryGood> dryGoods) {
        mContext = context;
        mDryGoods = dryGoods;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public DryGoodRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_drygood_list, parent, false);
        return new DryGoodRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DryGoodRecyclerAdapter.ViewHolder holder, int position) {
        mDryGood = mDryGoods.get(position);

        //Get the DryGood Type from the ordinal
        if(mDryGood.getDryGoods_category() == 0) mDryGoodsCategory = DryGood.DryGoodsCategory.GRAINS;
        else if(mDryGood.getDryGoods_category() == 1) mDryGoodsCategory = DryGood.DryGoodsCategory.BEANS;
        else if(mDryGood.getDryGoods_category() == 2) mDryGoodsCategory = DryGood.DryGoodsCategory.PASTA;
        else if(mDryGood.getDryGoods_category() == 3) mDryGoodsCategory = DryGood.DryGoodsCategory.ASIAN;
        else if(mDryGood.getDryGoods_category() == 4) mDryGoodsCategory = DryGood.DryGoodsCategory.NUTS;
        else if(mDryGood.getDryGoods_category() == 5) mDryGoodsCategory = DryGood.DryGoodsCategory.BAKING;
        else if(mDryGood.getDryGoods_category() == 6) mDryGoodsCategory = DryGood.DryGoodsCategory.SPICES;
        else if(mDryGood.getDryGoods_category() == 7) mDryGoodsCategory = DryGood.DryGoodsCategory.CANNED;
        else mDryGoodsCategory = DryGood.DryGoodsCategory.OTHER;

        //Bind the data with the view holder
        holder.mTvDryGoodName.setText(mDryGood.getProduct_name());
        holder.mTvDryGoodCategory.setText(mDryGoodsCategory.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mDryGood.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#E4E9FF"));
            mListener.onDryGoodRecyclerSent(mDryGood.getProduct_name(), String.valueOf(mDryGoodsCategory), mProductID, mDryGood.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#F6F8FF"));
        }
    }

    @Override
    public int getItemCount() {
        return mDryGoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvDryGoodName;
        public final TextView mTvDryGoodCategory;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_drygoodsitem);
            mTvDryGoodName = (TextView) itemView.findViewById(R.id.tvDryGoodsName_drygoodslistitem);
            mTvDryGoodCategory = (TextView) itemView.findViewById(R.id.tvDryGoodsType_drygoodslistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDryGoodRecyclerSent(mTvDryGoodName.getText(), mTvDryGoodCategory.getText(), mProductID, mDryGood.getDefault_unit());
                }
            });
        }
    }
}
