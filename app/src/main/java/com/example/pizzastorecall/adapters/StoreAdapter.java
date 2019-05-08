package com.example.pizzastorecall.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pizzastorecall.R;
import com.example.pizzastorecall.datas.Store;

import java.util.List;

public class StoreAdapter extends ArrayAdapter<Store> {

    Context mcontext;
    List<Store> mstoreList;

    LayoutInflater inf;


    public StoreAdapter(Context context, List<Store> storeList){
        super(context, R.layout.stroe_list_item, storeList);

        this.mcontext = context;
        this.mstoreList = storeList;

        inf = LayoutInflater.from(context);

    }

    @Override
    public View getView(final int position, View row, ViewGroup parent) {

        if(row == null){
            row = inf.inflate(R.layout.stroe_list_item, null);
        }

        Store storeData = mstoreList.get(position);

        ImageView pizzaStoreImg = row.findViewById(R.id.pizzaStoreImg);
        TextView storeNmTxt = row.findViewById(R.id.storeNmTxt);
        TextView openAndCloseTxt = row.findViewById(R.id.openAndCloseTxt);

        Glide.with(mcontext).load(storeData.getLogoImgUrl()).into(pizzaStoreImg);

        storeNmTxt.setText(storeData.getName());
        openAndCloseTxt.setText(String.format("%s ~ %s" ,storeData.getOpen(), storeData.getClose()));

        return row;
        //return super.getView(position, convertView, parent);
    }
}
