package com.example.virtual_wardrobe.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtual_wardrobe.R;
import com.example.virtual_wardrobe.model.Clothe;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter {
    List<Clothe> list;
    class MyHolderView extends RecyclerView.ViewHolder {
        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            descriptionText = itemView.findViewById(R.id.text_descrp);
            photo = itemView.findViewById(R.id.picture);
            type = itemView.findViewById(R.id.type);
        }
        TextView name;
        TextView type;
        TextView descriptionText;
        ImageView photo;
    }
    public ClothesAdapter(  List<Clothe> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_card, parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolderView view = (MyHolderView) holder;
        // view.photo(list.get(position).);
        view.name.setText(list.get(position).name);
        view.descriptionText.setText(list.get(position).description);
        view.type.setText(list.get(position).category);
        view.photo.setImageURI(Uri.parse(list.get(position).image));

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
}
