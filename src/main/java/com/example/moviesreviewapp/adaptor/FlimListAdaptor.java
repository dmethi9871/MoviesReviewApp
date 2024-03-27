package com.example.moviesreviewapp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresOptIn;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviesreviewapp.R;
import com.example.moviesreviewapp.activities.Details;
import com.example.moviesreviewapp.domain.ListFilm;

public class FlimListAdaptor extends RecyclerView.Adapter<FlimListAdaptor.ViewHolder> {
   ListFilm listfilm;
   Context context;

    public FlimListAdaptor(ListFilm listfilm) {
        this.listfilm = listfilm;
    }

    @NonNull
    @Override
    public FlimListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewholder_film,viewGroup,false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull FlimListAdaptor.ViewHolder viewHolder, int i) {
         viewHolder.tittleTxt.setText(listfilm.getData().get(i).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions= requestOptions.transform(new CenterCrop(),new RoundedCorners(30));
        Glide.with(context)
                .load(listfilm.getData().get(i).getPoster())
                .apply(requestOptions)
                .into(viewHolder.pic);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), Details.class);
                intent.putExtra("id",listfilm.getData().get(i).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tittleTxt;
        ImageView pic;
        public ViewHolder(View itemView){
            super(itemView);
            tittleTxt = itemView.findViewById(R.id.textView5);

        }
    }
}
