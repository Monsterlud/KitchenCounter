package com.monsalud.kitchencounter.UI.Request.Beverage;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.BeverageRecyclerAdapter;


public class BeverageReqListFragment extends Fragment {
    BeverageRecyclerAdapter mBeverageRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBeverageRecyclerAdapter = new BeverageRecyclerAdapter(getContext(), BeverageReqActivity.mBeverages);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beverage_req_list, container, false);
        RecyclerView recyclerBeverages = view.findViewById(R.id.listBeverage);
        recyclerBeverages.setHasFixedSize(true);
        recyclerBeverages.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerBeverages.setAdapter(new BeverageRecyclerAdapter(getContext(), BeverageReqActivity.mBeverages));
        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof BeverageRecyclerAdapter.BeverageRecyclerListener) {
            BeverageRecyclerAdapter.mListener
                    = (BeverageRecyclerAdapter.BeverageRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BeverageRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BeverageRecyclerAdapter.mListener = null;
    }
}