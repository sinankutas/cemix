package com.arneca.evyap.ui.adapter;/*
 * Created by Sinan KUTAS on 27.01.2021.
 */


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arneca.evyap.R;
import com.arneca.evyap.api.DataModel;
import com.arneca.evyap.api.DataModelFactory;
import com.arneca.evyap.api.response.GetFactories;
import com.arneca.evyap.helper.PreferencesHelper;
import com.arneca.evyap.ui.activity.ProductLineActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecAdapterFactory extends RecyclerView.Adapter<RecAdapterFactory.RecViewHolder>  implements RecyclerViewAdapter.ItemListener{

private GetFactories factories;
private Context context;


public RecAdapterFactory(GetFactories factories, Context context) {
        this.factories = factories;
        this.context = context;
        }

@Override
public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.product_line_factory_item, parent, false);

        return new RecViewHolder(view,parent.getContext());
        }

@Override
public void onBindViewHolder(RecViewHolder holder, int position) {

        holder.bind(factories, position);

        holder.itemView.setOnClickListener(v -> {
            PreferencesHelper.setSelectedFactory(factories.getData().get(position));
            if (context instanceof ProductLineActivity) {
                ((ProductLineActivity)context).getAllLineInfo();
                ((ProductLineActivity)context).showFactoryList(false);
            }
         notifyItemChanged(position);
        });
        }

    @Override
    public int getItemCount() {
            return factories.getData() == null ? 0 : factories.getData().size();
            }

public class RecViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private View subItem;
    RecyclerView gridView;
    ArrayList<DataModelFactory> arrayList;
    private Context context;
    public RecViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;
        title = itemView.findViewById(R.id.item_title);
        subItem = itemView.findViewById(R.id.sub_item);



    }

    private void bind(GetFactories factories, int position) {
        boolean expanded = factories.isExpanded();

        subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
        title.setText(factories.getData().get(position).getFactoryName());



    /*    gridView = (RecyclerView) itemView.findViewById(R.id.gridView);
        arrayList = new ArrayList<>();
        for (GetFactories.data factory: factories.getData()) {
            arrayList.add(new DataModelFactory(factory.getFactoryName() ,""+factory.getFactoryCode()));

        }

        RecyclerViewFactoryAdapter adapterGrid = new RecyclerViewFactoryAdapter(context, arrayList);
        gridView.setAdapter(adapterGrid);


        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(context, 500);
        gridView.setLayoutManager(layoutManager);
        gridView.setBackgroundColor(this.context.getResources().getColor(R.color.red));


        GridLayoutManager manager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        gridView.setLayoutManager(manager);
*/
    }
}

    @Override
    public void onItemClick(DataModel item) {


    }
}