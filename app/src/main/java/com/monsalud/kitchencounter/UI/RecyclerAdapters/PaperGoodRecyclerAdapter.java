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

import com.monsalud.kitchencounter.Entity.PaperGood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class PaperGoodRecyclerAdapter extends RecyclerView.Adapter<PaperGoodRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<PaperGood> mPaperGoods;
    private final LayoutInflater mLayoutInflater;
    public static PaperGoodRecyclerAdapter.PaperGoodRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private PaperGood.PaperGoodsCategory mPaperGoodsCategory;
    private PaperGood mPaperGood;


    public interface PaperGoodRecyclerListener {
        void onPaperGoodRecyclerSent(CharSequence papergoodName, CharSequence papergoodCategory, int productID, InventoryItem.InventoryUnit unit);
    }

    public PaperGoodRecyclerAdapter(Context context, List<PaperGood> paperGoods) {
        mContext = context;
        mPaperGoods = paperGoods;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public PaperGoodRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_papergood_list, parent, false);
        return new PaperGoodRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaperGoodRecyclerAdapter.ViewHolder holder, int position) {
        mPaperGood = mPaperGoods.get(position);

        //Get the PaperGood Type from the ordinal
        if(mPaperGood.getPaperGood_category() == 0) mPaperGoodsCategory = PaperGood.PaperGoodsCategory.TOGOPACKAGING;
        else if(mPaperGood.getPaperGood_category() == 1) mPaperGoodsCategory = PaperGood.PaperGoodsCategory.DISPOSABLES;
        else mPaperGoodsCategory = PaperGood.PaperGoodsCategory.OTHER;

        //Bind the data with the view holder
        holder.mTvPaperGoodName.setText(mPaperGood.getProduct_name());
        holder.mTvPaperGoodCategory.setText(mPaperGoodsCategory.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mPaperGood.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#EDD0A1"));
            mListener.onPaperGoodRecyclerSent(mPaperGood.getProduct_name(), String.valueOf(mPaperGoodsCategory), mProductID, mPaperGood.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#FFEDDE"));
        }
    }

    @Override
    public int getItemCount() {
        return mPaperGoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvPaperGoodName;
        public final TextView mTvPaperGoodCategory;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_papergoodsitem);
            mTvPaperGoodName = (TextView) itemView.findViewById(R.id.tvPaperGoodsName_papergoodslistitem);
            mTvPaperGoodCategory = (TextView) itemView.findViewById(R.id.tvPaperGoodsCategory_papergoodslistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPaperGoodRecyclerSent(mTvPaperGoodName.getText(), mTvPaperGoodCategory.getText(), mProductID, mPaperGood.getDefault_unit());
                }
            });
        }
    }
}
