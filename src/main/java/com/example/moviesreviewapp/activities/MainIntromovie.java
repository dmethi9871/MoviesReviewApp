package com.example.moviesreviewapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviesreviewapp.R;
import com.example.moviesreviewapp.adaptor.FlimListAdaptor;
import com.example.moviesreviewapp.adaptor.sliderAdaptor;
import com.example.moviesreviewapp.domain.ListFilm;
import com.example.moviesreviewapp.domain.Slideritem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainIntromovie extends AppCompatActivity {

    private RecyclerView.Adapter adapterBestMovies, adapterUpComingMovies, adapterCategory;
    private RecyclerView recyclerViewBestMovies, recyclerViewCategory, recyclerViewUpcomingMovies;
    private RequestQueue requestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar1, progressBar2, progressBar3;
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intromovie);
        initView();
        banners();
        sendRequest();
    }

    private void sendRequest() {
        requestQueue = Volley.newRequestQueue(this);
        progressBar1.setVisibility(View.VISIBLE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                progressBar1.setVisibility(View.GONE);
                ListFilm items = gson.fromJson(response, ListFilm.class);
                adapterBestMovies = new FlimListAdaptor(items);
                recyclerViewBestMovies.setAdapter(adapterBestMovies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Error: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Set a tag to the request to later cancel it if needed
        mStringRequest.setTag("GET_REQUEST");

        // Add the request to the RequestQueue
        requestQueue.add(mStringRequest);
    }

    // Override onDestroy() method to cancel the request when the activity or fragment is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (requestQueue != null) {
            requestQueue.cancelAll("GET_REQUEST");
        }
    }

    private void banners() {
        List<Slideritem> slideritem = new ArrayList<>();
        slideritem.add(new Slideritem(R.drawable.wide));
        slideritem.add(new Slideritem(R.drawable.wide));
        slideritem.add(new Slideritem(R.drawable.wide3));
        viewPager2.setAdapter(new sliderAdaptor(slideritem, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                float r = 1 - Math.abs(position);
                viewPager2.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideRunnable);
            }
        });
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideRunnable, 2000);
    }

    private void initView() {
        viewPager2 = findViewById(R.id.viewpagesider);
        recyclerViewBestMovies = findViewById(R.id.recyclerView_bestMovies);
        recyclerViewCategory = findViewById(R.id.recyclerView_category);
        recyclerViewUpcomingMovies = findViewById(R.id.recyclerView_upcomingMovies);

        recyclerViewBestMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewUpcomingMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
    }
}
