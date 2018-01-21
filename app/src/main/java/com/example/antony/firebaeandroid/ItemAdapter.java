package com.example.antony.firebaeandroid;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Antony on 1/21/2018.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    private Activity context;
    private List<Item> itemList;

    public ItemAdapter(Activity context, List<Item> itemList){
        super(context, R.layout.list_layout, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.itemName);
        TextView textViewDesc = (TextView) listViewItem.findViewById(R.id.itemDesc);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.itemPrice);

        Item item = itemList.get(position);

        textViewName.setText(item.Name);
        textViewDesc.setText(item.Description);
        textViewPrice.setText(item.Price);

        return listViewItem;
    }

}
