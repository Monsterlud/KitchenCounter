package com.monsalud.kitchencounter.UI.Request.Dairy;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.DairyRecyclerAdapter;


public class DairyReqListFragment extends Fragment {
    DairyRecyclerAdapter mDairyRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDairyRecyclerAdapter = new DairyRecyclerAdapter(getContext(), DairyReqActivity.mDairies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dairy_req_list, container, false);
        RecyclerView recyclerDairy = view.findViewById(R.id.listDairy);
        recyclerDairy.setHasFixedSize(true);
        recyclerDairy.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerDairy.setAdapter(new DairyRecyclerAdapter(getContext(), DairyReqActivity.mDairies));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof DairyRecyclerAdapter.DairyRecyclerListener) {
            DairyRecyclerAdapter.mListener
                    = (DairyRecyclerAdapter.DairyRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DairyRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        DairyRecyclerAdapter.mListener = null;
    }
}