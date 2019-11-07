package com.example.a1105;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import java.util.List;


public class ListviewAdapter extends ArrayAdapter<Goods> {
    private int resourceId;

    public ListviewAdapter(@NonNull MainActivity context, int resource, List objects) {
        super(context, resource,objects);

        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);


        final TextView name = view.findViewById(R.id.name);
        final ImageView imageView = view.findViewById(R.id.img);
        final TextView price = view.findViewById(R.id.price);
        final Goods good = getItem(position);

        name.setText("商品名称:\n" + good.getName());
        price.setText("商品价格:\n" + good.getPrice());

        Glide.with(parent.getContext()).load(good.getImage()).into(imageView);

        return view;
    }
}
