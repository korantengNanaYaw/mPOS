package com.hubtel.mpos;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 22/06/16.
 */
public class MenuItemsRecycleViewAdapter extends RecyclerView.Adapter<MenuItemsViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<MenuItem> data=Collections.emptyList();
    Typefacer typefacer;


    public MenuItemsRecycleViewAdapter(Context context, List<MenuItem> data) {
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.context=context;
        typefacer=new Typefacer();
    }



    @Override
    public MenuItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.inflate(R.layout.wallet_card_layout,parent,false);

        MenuItemsViewHolder holder = new MenuItemsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MenuItemsViewHolder holder, int position) {
        typefacer= new Typefacer();
        MenuItem menuItem =data.get(position);

        holder.textView.setText(""+menuItem.getTitle());
        holder.textView.setTypeface(typefacer.squareLight());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}



class MenuItemsViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public MenuItemsViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.title);
        //imageView = (ImageView) itemView.findViewById(R.id.image);
    }
}