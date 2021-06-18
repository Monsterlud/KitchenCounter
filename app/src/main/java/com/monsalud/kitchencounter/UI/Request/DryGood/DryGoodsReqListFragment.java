package com.monsalud.kitchencounter.UI.Request.DryGood;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsalud.kitchencounter.Database.KCRepository;
import com.monsalud.kitchencounter.R;
import com.monsalud.kitchencounter.UI.RecyclerAdapters.DryGoodRecyclerAdapter;


public class DryGoodsReqListFragment extends Fragment {
    DryGoodRecyclerAdapter mDryGoodRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDryGoodRecyclerAdapter = new DryGoodRecyclerAdapter(getContext(), DryGoodsReqActivity.mDryGoods);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dry_good_req_list, container, false);
        RecyclerView recyclerDryGoods = view.findViewById(R.id.listDryGood);
        recyclerDryGoods.setHasFixedSize(true);
        recyclerDryGoods.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerDryGoods.setAdapter(new DryGoodRecyclerAdapter(getContext(), DryGoodsReqActivity.mDryGoods));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof DryGoodRecyclerAdapter.DryGoodRecyclerListener) {
            DryGoodRecyclerAdapter.mListener
                    = (DryGoodRecyclerAdapter.DryGoodRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DryGoodRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DryGoodRecyclerAdapter.mListener = null;
    }
}