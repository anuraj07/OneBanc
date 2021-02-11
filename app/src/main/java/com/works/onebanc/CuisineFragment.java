package com.works.onebanc;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class CuisineFragment extends Fragment {

    public CuisineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuisine, container, false);

        ArrayList<SliderModel> items = new ArrayList<>();
        SliderAdapter sliderAdapter = new SliderAdapter(getContext(),items);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        items.add(new SliderModel(R.drawable.north, "North"));
        items.add(new SliderModel(R.drawable.south, "South"));
        items.add(new SliderModel(R.drawable.chinese, "Chinese"));
        items.add(new SliderModel(R.drawable.mexican, "Mexican"));

        recyclerView.setAdapter(sliderAdapter);

        sliderAdapter.notifyDataSetChanged();




        return view;
    }
}