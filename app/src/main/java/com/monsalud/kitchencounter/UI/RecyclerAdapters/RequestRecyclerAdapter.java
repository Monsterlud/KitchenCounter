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
import com.monsalud.kitchencounter.Tables.RequestItem;
import com.monsalud.kitchencounter.UI.Request.RequestReviewActivity;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestRecyclerAdapter extends RecyclerView.Adapter<RequestRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private List<RequestItem> mRequestItems;
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
    public RequestRecyclerAdapter(Context context, List<RequestItem> requestItems) {
        mContext = context;
        mRequestItems = requestItems;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public RequestRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_requests, parent, false);
        return new RequestRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestRecyclerAdapter.ViewHolder holder, int position) {
        RequestItem requestItem = mRequestItems.get(position);

        //get a list of all products
        mProducts = RequestReviewActivity.mAllProducts;

        //Find the product from the list of all products, then grab the name and description
        for (Product product:mProducts) {
            if(product.getProduct_id() == requestItem.getProduct_id_fk()) {
                mProductName = product.getProduct_name();
                mProductDescription = product.getProduct_description();
                mProductDefaultUnit = product.getDefault_unit();
            }
        }
        //set the holder with all of the current request item's values
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd, yyyy");
        holder.mTvProductName.setText(mProductName);
        holder.mTvProductDescription.setText(mProductDescription);
        holder.mTvAmount.setText(String.valueOf(requestItem.getRequest_amount()));
        holder.mTvUnitType.setText(mProductDefaultUnit.toString());

    }

    @Override
    public int getItemCount() {
        return mRequestItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTvProductName;
        public final TextView mTvProductDescription;
        public final TextView mTvTimestamp;
        public final TextView mTvAmount;
        public final TextView mTvUnitType;
        public final ImageView mIvDelete;

        private final CardView mCard;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.card_requestitem);
            mTvProductName = (TextView) itemView.findViewById(R.id.tvProductName_requestitem);
            mTvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription_requestitem);
            mTvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp_requestitem);
            mTvAmount = (TextView) itemView.findViewById(R.id.tvAmount_requestitem);
            mTvUnitType = (TextView) itemView.findViewById(R.id.tvUnitType_requestitem);
            mIvDelete = (ImageView) itemView.findViewById(R.id.ivDelete_requestitem);

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


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
