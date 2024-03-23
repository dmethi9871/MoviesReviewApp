package com.example.moviesreviewapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moviesreviewapp.domain.Slideritem;

import java.util.List;

public class sliderAdaptor extends RecyclerView.Adapter<sliderAdaptor.SliderviewHolder> {
    private List<Slideritem> slideritemList;
    private ViewPager2 viewPager2;
    private Context context;

    public sliderAdaptor(List<Slideritem> slideritemList, ViewPager2 viewPager2) {
        this.slideritemList = slideritemList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public sliderAdaptor.SliderviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        return new SliderviewHolder(LayoutInflater.from(parent.getContext()).inflate().);
    }

    @Override
    public void onBindViewHolder(@NonNull sliderAdaptor.SliderviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SliderviewHolder {
    }
}
