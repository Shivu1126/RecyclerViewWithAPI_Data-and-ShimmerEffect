package com.androidwithshiv.mobiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidwithshiv.mobiles.R;
import com.androidwithshiv.mobiles.model.MobileDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MobileDetailsAdapter extends RecyclerView.Adapter<MobileDetailsAdapter.MobileDetailsHolder>{

    private List<MobileDetails> mobileDetailsList;
    private Context context;

    public MobileDetailsAdapter(List<MobileDetails> mobileDetailsList, Context context) {
        this.mobileDetailsList = mobileDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MobileDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mobile_ui, parent, false);
        return new MobileDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileDetailsHolder holder, int position) {
        holder.mobileName.setText(mobileDetailsList.get(position).getName());
        holder.mobilePrice.setText(mobileDetailsList.get(position).getPrice());
        holder.mobileRating.setText(mobileDetailsList.get(position).getRating());
        holder.mobileDescription.setText(mobileDetailsList.get(position).getDescription());
        Picasso.get().load(mobileDetailsList.get(position).getPic()).into(holder.mobilePic);
    }

    @Override
    public int getItemCount() {
        return mobileDetailsList.size();
    }

    public class MobileDetailsHolder extends RecyclerView.ViewHolder{

        private TextView mobileName, mobilePrice, mobileRating, mobileDescription;
        private ImageView mobilePic;
        public MobileDetailsHolder(@NonNull View itemView) {
            super(itemView);

            mobileName = itemView.findViewById(R.id.name);
            mobileDescription = itemView.findViewById(R.id.description);
            mobilePic = itemView.findViewById(R.id.img);
            mobilePrice = itemView.findViewById(R.id.price);
            mobileRating = itemView.findViewById(R.id.rating);
        }
    }
}
