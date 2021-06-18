package com.monsalud.kitchencounter.UI.Request.JanitorialGood;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.JanitorialGoodRecyclerAdapter;


public class JanitorialGoodsReqListFragment extends Fragment {
    JanitorialGoodRecyclerAdapter mJanitorialGoodRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJanitorialGoodRecyclerAdapter = new JanitorialGoodRecyclerAdapter(getContext(), JanitorialGoodsReqActivity.mJanitorialGoods);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_janitorial_goods_req_list, container, false);
        RecyclerView recyclerJanitorialGoods = view.findViewById(R.id.listJanitorialGood);
        recyclerJanitorialGoods.setHasFixedSize(true);
        recyclerJanitorialGoods.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerJanitorialGoods.setAdapter(new JanitorialGoodRecyclerAdapter(getContext(), JanitorialGoodsReqActivity.mJanitorialGoods));
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof JanitorialGoodRecyclerAdapter.JanitorialGoodRecyclerListener) {
            JanitorialGoodRecyclerAdapter.mListener
                    = (JanitorialGoodRecyclerAdapter.JanitorialGoodRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement JanitorialGoodRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        JanitorialGoodRecyclerAdapter.mListener = null;
    }
}