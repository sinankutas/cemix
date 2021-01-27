package com.arneca.evyap.ui.adapter;/*
 * Created by Sinan KUTAS on 25.01.2021.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> values) {

        mValues = values;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,textViewValue;
        public RelativeLayout relativeLayout;
        DataModel item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.txtInfo);

            CardView card = (CardView) v.findViewById(R.id.cardView);
            card.setCardBackgroundColor(Color.parseColor("#E6E6E6"));

            textViewValue = (TextView) v.findViewById(R.id.txtValue);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);


        }

        public void setData(DataModel item) {
            this.item = item;
            if (item.isRedActive){
                textViewValue.setTextColor(Color.parseColor("#FA4C4C"));
                textView.setTextColor(Color.parseColor("#FA4C4C"));
            }
            textViewValue.setText(item.value);
            textView.setText(item.title);
         // relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
        /*    if (mListener != null) {
                mListener.onItemClick(item);
            }*/
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}