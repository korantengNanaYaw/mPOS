package com.hubtel.mpos.ViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hubtel.mpos.Models.MenuItem;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;

import java.util.Collections;
import java.util.List;

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
    public void onBindViewHolder(final MenuItemsViewHolder holder, int position) {
        typefacer= new Typefacer();
        final MenuItem menuItem =data.get(position);

        holder.textView.setText(""+menuItem.getTitle());
        holder.textView.setTypeface(typefacer.squareLight());


        holder.imageView.post(new Runnable() {
            @Override
            public void run() {

                holder.imageView.setImageResource(menuItem.getImageIcon());
            }
        });
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
        imageView = (ImageView) itemView.findViewById(R.id.icon);
    }
}