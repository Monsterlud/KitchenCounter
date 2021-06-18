package com.monsalud.kitchencounter.UI.Request.PaperGood;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.PaperGoodRecyclerAdapter;


public class PaperGoodsReqListFragment extends Fragment {
    PaperGoodRecyclerAdapter mPaperGoodRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaperGoodRecyclerAdapter = new PaperGoodRecyclerAdapter(getContext(), PaperGoodsReqActivity.mPaperGoods);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paper_goods_req_list, container, false);
        RecyclerView recyclerPaperGoods = view.findViewById(R.id.listPaperGood);
        recyclerPaperGoods.setHasFixedSize(true);
        recyclerPaperGoods.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerPaperGoods.setAdapter(new PaperGoodRecyclerAdapter(getContext(), PaperGoodsReqActivity.mPaperGoods));
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PaperGoodRecyclerAdapter.PaperGoodRecyclerListener) {
            PaperGoodRecyclerAdapter.mListener
                    = (PaperGoodRecyclerAdapter.PaperGoodRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PaperGoodRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        PaperGoodRecyclerAdapter.mListener = null;
    }
}