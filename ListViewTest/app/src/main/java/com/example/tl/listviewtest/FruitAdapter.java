package com.example.tl.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit>{
    private int resourceId;

    /*
    public ArrayAdapter (Context context, int resource, List<T> objects)
        context 	The current context.
        resource 	The resource ID for a layout file containing a TextView to use when instantiating views.
        objects 	The objects to represent in the ListView.
    */
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    /*
    Abstract
        called when an item was scolled into the screen,returen a view to contain the item. one item corresponding one view.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("mylog", "getView()");
        Fruit fruit = getItem(position);//获取当前项的 Fruit 实例

        /*
        LayoutInflater.from(Context context);
            Obtains the LayoutInflater from the given context.

        View 	inflate(int resource, ViewGroup root, boolean attachToRoot)
            Inflate a new view hierarchy from the specified xml resource.
        */
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView fruitImage = view.findViewById(R.id.fruit_image);
        TextView fruitName = view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImgId());
        fruitName.setText(fruit.getName());
        return view;
    }
}
