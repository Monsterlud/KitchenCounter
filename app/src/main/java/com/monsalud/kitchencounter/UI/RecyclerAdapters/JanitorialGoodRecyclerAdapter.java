package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monsalud.kitchencounter.Entity.JanitorialGood;
import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class JanitorialGoodRecyclerAdapter extends RecyclerView.Adapter<JanitorialGoodRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<JanitorialGood> mJanitorialGoods;
    private final LayoutInflater mLayoutInflater;
    public static JanitorialGoodRecyclerAdapter.JanitorialGoodRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private JanitorialGood.JanitorialGoodsCategory mJanitorialGoodsCategory;
    private JanitorialGood mJanitorialGood;


    public interface JanitorialGoodRecyclerListener {
        void onJanitorialGoodRecyclerSent(CharSequence janitorialgoodName, CharSequence janitorialgoodCategory, int productID, InventoryItem.InventoryUnit unit);
    }

    public JanitorialGoodRecyclerAdapter(Context context, List<JanitorialGood> janitorialGoods) {
        mContext = context;
        mJanitorialGoods = janitorialGoods;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public JanitorialGoodRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_janitorialgood_list, parent, false);
        return new JanitorialGoodRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JanitorialGoodRecyclerAdapter.ViewHolder holder, int position) {
        mJanitorialGood = mJanitorialGoods.get(position);

        //Get the JanitorialGood Type from the ordinal
        if(mJanitorialGood.getJanitorialGoods_category() == 0) mJanitorialGoodsCategory = JanitorialGood.JanitorialGoodsCategory.CHEMICALS;
        else if(mJanitorialGood.getJanitorialGoods_category() == 1) mJanitorialGoodsCategory = JanitorialGood.JanitorialGoodsCategory.CLEANINGSUPPLIES;
        else mJanitorialGoodsCategory = JanitorialGood.JanitorialGoodsCategory.OTHER;

        //Bind the data with the view holder
        holder.mTvJanitorialGoodName.setText(mJanitorialGood.getProduct_name());
        holder.mTvJanitorialGoodCategory.setText(mJanitorialGoodsCategory.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mJanitorialGood.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#CDD4CD"));
            mListener.onJanitorialGoodRecyclerSent(mJanitorialGood.getProduct_name(), String.valueOf(mJanitorialGoodsCategory), mProductID, mJanitorialGood.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#E8EBE8"));
        }
    }

    @Override
    public int getItemCount() {
        return mJanitorialGoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvJanitorialGoodName;
        public final TextView mTvJanitorialGoodCategory;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_janitorialgoodsitem);
            mTvJanitorialGoodName = (TextView) itemView.findViewById(R.id.tvJanitorialGoodsName_janitorialgoodslistitem);
            mTvJanitorialGoodCategory = (TextView) itemView.findViewById(R.id.tvJanitorialGoodsCategory_janitorialgoodslistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onJanitorialGoodRecyclerSent(mTvJanitorialGoodName.getText(), mTvJanitorialGoodCategory.getText(), mProductID, mJanitorialGood.getDefault_unit());
                }
            });
        }
    }
}
