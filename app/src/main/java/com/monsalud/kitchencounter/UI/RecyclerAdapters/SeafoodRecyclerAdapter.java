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

import com.monsalud.kitchencounter.Entity.Seafood;
import com.monsalud.kitchencounter.Entity.Vegetable;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class SeafoodRecyclerAdapter extends RecyclerView.Adapter<SeafoodRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Seafood> mSeafoods;
    private final LayoutInflater mLayoutInflater;
    public static SeafoodRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Seafood.SeafoodType mSeafoodType;
    private Seafood mSeafood;


    public interface SeafoodRecyclerListener {
        void onSeafoodRecyclerSent(CharSequence seafoodName, CharSequence seafoodType, int productID, InventoryItem.InventoryUnit unit);
    }

    public SeafoodRecyclerAdapter(Context context, List<Seafood> seafoods) {
        mContext = context;
        mSeafoods = seafoods;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_seafood_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeafoodRecyclerAdapter.ViewHolder holder, int position) {
        mSeafood = mSeafoods.get(position);

        //Get the Seafood Type from the ordinal
        if(mSeafood.getSeafood_type() == 0) mSeafoodType = Seafood.SeafoodType.FISH;
        else if(mSeafood.getSeafood_type() == 1) mSeafoodType = Seafood.SeafoodType.SHELLFISH;
        else if(mSeafood.getSeafood_type() == 2) mSeafoodType = Seafood.SeafoodType.BIVALVE;
        else mSeafoodType = Seafood.SeafoodType.OTHER;

        //Bind the data with the view holder
        holder.mTvSeafoodName.setText(mSeafood.getProduct_name());
        holder.mTvSeafoodType.setText(mSeafoodType.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductID = mSeafood.getProduct_id();
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#B1EFFB"));
            mListener.onSeafoodRecyclerSent(mSeafood.getProduct_name(), String.valueOf(mSeafoodType), mProductID, mSeafood.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#E9F9FF"));
        }
    }

    @Override
    public int getItemCount() {
        return mSeafoods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvSeafoodName;
        public final TextView mTvSeafoodType;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_seafooditem);
            mTvSeafoodName = (TextView) itemView.findViewById(R.id.tvSeafoodName_seafoodlistitem);
            mTvSeafoodType = (TextView) itemView.findViewById(R.id.tvSeafoodType_seafoodlistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onSeafoodRecyclerSent(mTvSeafoodName.getText(), mTvSeafoodType.getText(), mProductID, mSeafood.getDefault_unit());
                }
            });
        }
    }
    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
