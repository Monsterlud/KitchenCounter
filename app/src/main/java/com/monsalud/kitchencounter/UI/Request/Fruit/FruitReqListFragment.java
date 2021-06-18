package com.monsalud.kitchencounter.UI.Request.Fruit;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.FruitRecyclerAdapter;

public class FruitReqListFragment extends Fragment {
    FruitRecyclerAdapter mFruitRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFruitRecyclerAdapter = new FruitRecyclerAdapter(getContext(), FruitReqActivity.mFruits);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fruit_req_list, container, false);
        RecyclerView recyclerFruits = view.findViewById(R.id.listFruit);
        recyclerFruits.setHasFixedSize(true);
        recyclerFruits.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFruits.setAdapter(new FruitRecyclerAdapter(getContext(), FruitReqActivity.mFruits));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FruitRecyclerAdapter.FruitRecyclerListener) {
            FruitRecyclerAdapter.mListener
                    = (FruitRecyclerAdapter.FruitRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FruitRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FruitRecyclerAdapter.mListener = null;
    }
}