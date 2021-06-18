package com.monsalud.kitchencounter.UI.Request.Seafood;

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
import com.monsalud.kitchencounter.UI.RecyclerAdapters.SeafoodRecyclerAdapter;

public class SeafoodReqListFragment extends Fragment {
    SeafoodRecyclerAdapter mSeafoodRecyclerAdapter;
    KCRepository mRespository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeafoodRecyclerAdapter = new SeafoodRecyclerAdapter(getContext(), SeafoodReqActivity.mSeafoods);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seafood_req_list, container, false);
        RecyclerView recyclerSeafood = view.findViewById(R.id.listSeafood);
        recyclerSeafood.setHasFixedSize(true);
        recyclerSeafood.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerSeafood.setAdapter(new SeafoodRecyclerAdapter(getContext(), SeafoodReqActivity.mSeafoods));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof SeafoodRecyclerAdapter.SeafoodRecyclerListener) {
            SeafoodRecyclerAdapter.mListener
                    = (SeafoodRecyclerAdapter.SeafoodRecyclerListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SeafoodRecyclerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SeafoodRecyclerAdapter.mListener = null;
    }
}