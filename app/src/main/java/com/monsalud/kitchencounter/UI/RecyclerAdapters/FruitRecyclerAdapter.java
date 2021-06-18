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

import com.monsalud.kitchencounter.Entity.Fruit;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Fruit> mFruits;
    private final LayoutInflater mLayoutInflater;
    public static FruitRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Fruit mFruit;


    public interface FruitRecyclerListener {
        void onFruitRecyclerSent(CharSequence fruitName, CharSequence fruitVarietal, int productID, InventoryItem.InventoryUnit unit);
    }

    public FruitRecyclerAdapter(Context context, List<Fruit> fruits) {
        mContext = context;
        mFruits = fruits;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public FruitRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_fruit_list, parent, false);
        return new FruitRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitRecyclerAdapter.ViewHolder holder, int position) {
        mFruit = mFruits.get(position);
        holder.mTvFruitName.setText(this.mFruit.getProduct_name());
        holder.mTvFruitVarietalName.setText(this.mFruit.getFruitvarietal_name());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = this.mFruit.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#FAD5E9"));
            mListener.onFruitRecyclerSent(this.mFruit.getProduct_name(), this.mFruit.getFruitvarietal_name(), mProductID, mFruit.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#FEF2F8"));
        }
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvFruitName;
        public final TextView mTvFruitVarietalName;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_fruititem);
            mTvFruitName = (TextView) itemView.findViewById(R.id.tvFruitName_fruitlistitem);
            mTvFruitVarietalName = (TextView) itemView.findViewById(R.id.tvFruitVarietalName_fruitlistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFruitRecyclerSent(mTvFruitName.getText(), mTvFruitVarietalName.getText(), mProductID, mFruit.getDefault_unit());
                }
            });
        }
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
