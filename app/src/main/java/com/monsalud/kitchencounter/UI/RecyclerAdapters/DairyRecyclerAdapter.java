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

import com.monsalud.kitchencounter.Entity.Dairy;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;

import java.util.List;

public class DairyRecyclerAdapter extends RecyclerView.Adapter<DairyRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<Dairy> mDairies;
    private final LayoutInflater mLayoutInflater;
    public static DairyRecyclerListener mListener;

    private int mProductID;
    private int row_index = -1;
    private Dairy.DairyType mDairyType;
    private Dairy mDairy;


    public interface DairyRecyclerListener {
        void onDairyRecyclerSent(CharSequence dairyName, CharSequence dairyType, int productID, InventoryItem.InventoryUnit unit);
    }

    public DairyRecyclerAdapter(Context context, List<Dairy> dairies) {
        mContext = context;
        mDairies = dairies;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public DairyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_dairy_list, parent, false);
        return new DairyRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DairyRecyclerAdapter.ViewHolder holder, int position) {
        mDairy = mDairies.get(position);

        //Get the Dairy Type from the ordinal
        if(mDairy.getDairy_type() == 0) mDairyType = Dairy.DairyType.MILKANDCREAM;
        else if(mDairy.getDairy_type() == 1) mDairyType = Dairy.DairyType.CHEESE;
        else if (mDairy.getDairy_type() == 2) mDairyType = Dairy.DairyType.CULTURED;
        else if (mDairy.getDairy_type() == 3) mDairyType = Dairy.DairyType.EGGS;
        else mDairyType = Dairy.DairyType.OTHER;

        holder.mTvDairyName.setText(mDairy.getProduct_name());
        holder.mTvDairyType.setText(mDairyType.toString());
        holder.mCurrentPosition = position;

        holder.mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index == position) {
            mProductID = mDairy.getProduct_id();
            holder.mCard.setCardBackgroundColor(Color.parseColor("#F8F8DC"));
            mListener.onDairyRecyclerSent(mDairy.getProduct_name(), String.valueOf(mDairyType), mProductID, mDairy.getDefault_unit());
        }
        else  {
            holder.mCard.setCardBackgroundColor(Color.parseColor("#FDFDF1"));
        }
    }

    @Override
    public int getItemCount() {
        return mDairies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTvDairyName;
        public final TextView mTvDairyType;
        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_dairyitem);
            mTvDairyName = (TextView) itemView.findViewById(R.id.tvDairyName_dairylistitem);
            mTvDairyType = (TextView) itemView.findViewById(R.id.tvDairyType_dairylistitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onDairyRecyclerSent(mTvDairyName.getText(), mTvDairyType.getText(), mProductID, mDairy.getDefault_unit());
                }
            });
        }
    }
}
