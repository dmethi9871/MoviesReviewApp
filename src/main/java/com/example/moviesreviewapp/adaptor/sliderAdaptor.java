package com.example.moviesreviewapp.adaptor;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;




import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesreviewapp.R;
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

        return new SliderviewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull sliderAdaptor.SliderviewHolder holder, int position) {
           holder.setImageView(slideritemList.get(position));
           if(position==slideritemList.size()-2){
               viewPager2.post(runnable);
           }
    }

    @Override
    public int getItemCount() {
        return slideritemList.size();
    }

    public class SliderviewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public SliderviewHolder(View itemview){
            super(itemview);
            imageView= itemview.findViewById(R.id.imageslider);


        }

        void setImageView(Slideritem slideritem) {
            RequestOptions requestOption = new RequestOptions();
            requestOption = requestOption.transform(new CenterCrop(),new RoundedCorners(60));

            Glide.with(context)
                .load(slideritem.getImage())
                        .apply(requestOption)
                    .into(imageView);


        }
    }
    private  Runnable runnable=new Runnable() {
        @Override
        public void run() {
            slideritemList.addAll(slideritemList);
            notifyDataSetChanged();
        }
    };
}
