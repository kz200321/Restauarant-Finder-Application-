package com.example.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ViewHolder>{
    private Context context;
    private List<Business> restaurants;
    public BusinessAdapter(Context context, List<Business> restaurants){
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public BusinessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessAdapter.ViewHolder holder, int position) {
        Business restaurant = restaurants.get(position);
        holder.tvName.setText(restaurant.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rvRestaurant);
        }




    }
}
