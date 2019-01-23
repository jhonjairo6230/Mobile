package com.nokia.test.mobile.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.Element;

import java.util.List;

public class BatterToppingAdapter extends ArrayAdapter<Element> {
    List<Element> data;

    public BatterToppingAdapter(Context context,List<Element> data) {
        super(context, R.layout.item_bt, data);
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.item_bt, null);

        TextView tv1 = (TextView) item.findViewById(R.id.tv1);
        tv1.setText(data.get(position).getType());

        TextView tv2 = (TextView) item.findViewById(R.id.tv2);
        tv2.setText(data.get(position).getId());

        return (item);
    }
}