package com.monsalud.kitchencounter.UI.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.Entity.Product;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.Tables.InventoryItem;
import com.monsalud.kitchencounter.UI.Inventory.InventoryReviewActivity;

import java.util.List;

public class InventoryRecyclerAdapter extends RecyclerView.Adapter<InventoryRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<InventoryItem> mInventoryItems;
    private List<Product> mProducts;
    private final LayoutInflater mLayoutInflater;
    private String mProductName;
    private String mProductDescription;
    KCRepository mRespository;

    private OnItemClickListener mListener;
    private InventoryItem.InventoryUnit mProductDefaultUnit;

    public interface OnItemClickListener {
        void OnItemClick(int position);
        void OnDeleteClick(int position);
}
    public void SetOnClickListener(OnItemClickListener listener) {
    mListener = listener;
}

    public InventoryRecyclerAdapter(Context context, List<InventoryItem> inventoryItems) {
        mContext = context;
        mInventoryItems = inventoryItems;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public InventoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_inventory, parent, false);
        return new InventoryRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryRecyclerAdapter.ViewHolder holder, int position) {
    InventoryItem inventoryItem = mInventoryItems.get(position);

    //get a list of all products
    mProducts = InventoryReviewActivity.mAllProducts;

    //Find the product from the list of all products, then grab the name and description
        for (Product product:mProducts) {
            if(product.getProduct_id() == inventoryItem.getProduct_id_fk()) {
                mProductName = product.getProduct_name();
                mProductDescription = product.getProduct_description();
                mProductDefaultUnit = product.getDefault_unit();
            }
        }
    //set the holder with all of the current product's values
    holder.mTvProductName.setText(mProductName);
    holder.mTvProductDescription.setText(mProductDescription);
    holder.mTvAmount.setText(String.valueOf(inventoryItem.getInventory_amount()));
    holder.mTvUnitType.setText(mProductDefaultUnit.toString());
    holder.mTvLocation.setText(inventoryItem.getLocation().toString());
    holder.mTvTimestamp.setText(inventoryItem.getInventory_date().toString());
    holder.mTvProductDescription.setText(mProductDescription);
    }

    @Override
    public int getItemCount() {
        return mInventoryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTvProductName;
        public final TextView mTvProductDescription;
        public final TextView mTvTimestamp;
        public final TextView mTvAmount;
        public final TextView mTvUnitType;
        public final TextView mTvLocation;
        public final ImageView mIvDelete;

        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_inventoryitem);
            mTvProductName = (TextView) itemView.findViewById(R.id.tvProductName_inventoryitem);
            mTvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription_inventoryitem);
            mTvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp_inventoryitem);
            mTvAmount = (TextView) itemView.findViewById(R.id.tvAmount_inventoryitem);
            mTvUnitType = (TextView) itemView.findViewById(R.id.tvUnitType_inventoryitem);
            mTvLocation = (TextView) itemView.findViewById(R.id.tvLocation_inventoryitem);
            mIvDelete = (ImageView) itemView.findViewById(R.id.ivDelete_inventoryitem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAbsoluteAdapterPosition();
                            if(position != RecyclerView.NO_POSITION) {
                                mListener.OnItemClick(position);
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
                            mListener.OnDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
