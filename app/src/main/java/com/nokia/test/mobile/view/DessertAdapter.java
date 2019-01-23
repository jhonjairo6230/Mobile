package com.nokia.test.mobile.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nokia.test.mobile.R;
import com.nokia.test.mobile.model.DessertResponse;

import java.util.List;

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<DessertResponse> data;
    private Context context;

    public DessertAdapter(Context context, List<DessertResponse> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public DessertAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dessert, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.textView1.setText(data.get(position).getName());
        holder.textView2.setText(data.get(position).getType());
        holder.textView3.setText("Batters: " + data.get(position).getPpu());
        holder.textView4.setText("Toppings: "+data.get(position).getTopping().size());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(DessertResponse Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3, textView4;

        public ViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);

        }


        public void click(final DessertResponse cityListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(cityListData);
                }
            });
        }
    }


}
