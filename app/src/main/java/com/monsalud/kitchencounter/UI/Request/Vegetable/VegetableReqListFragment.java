package com.monsalud.kitchencounter.UI.Request.Vegetable;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.VegetableRecyclerAdapter;

public class VegetableReqListFragment extends Fragment {
    VegetableRecyclerAdapter mVegetableRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVegetableRecyclerAdapter = new VegetableRecyclerAdapter(getContext(), VegetableReqActivity.mVegetables);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vegetable_req_list, container, false);
        RecyclerView recyclerVeggies = view.findViewById(R.id.listProduce);
        recyclerVeggies.setHasFixedSize(true);
        recyclerVeggies.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerVeggies.setAdapter(new VegetableRecyclerAdapter(getContext(), VegetableReqActivity.mVegetables));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof VegetableRecyclerAdapter.VegetableRecyclerListener) {
            VegetableRecyclerAdapter.mListener
                    = (VegetableRecyclerAdapter.VegetableRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement VegetableRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        VegetableRecyclerAdapter.mListener = null;
    }
}