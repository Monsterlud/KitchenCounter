package com.monsalud.kitchencounter.UI.Request.Meat;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.MeatRecyclerAdapter;


public class MeatReqListFragment extends Fragment {
    MeatRecyclerAdapter mMeatRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMeatRecyclerAdapter = new MeatRecyclerAdapter(getContext(), MeatReqActivity.mMeats);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meat_req_list, container, false);
        RecyclerView recyclerMeats = view.findViewById(R.id.listMeat);
        recyclerMeats.setHasFixedSize(true);
        recyclerMeats.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerMeats.setAdapter(new MeatRecyclerAdapter(getContext(), MeatReqActivity.mMeats));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MeatRecyclerAdapter.MeatRecyclerListener) {
            MeatRecyclerAdapter.mListener
                    = (MeatRecyclerAdapter.MeatRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MeatRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        MeatRecyclerAdapter.mListener = null;
    }
}