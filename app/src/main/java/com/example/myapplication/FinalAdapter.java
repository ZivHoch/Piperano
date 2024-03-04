package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class FinalAdapter extends ArrayAdapter<String> {

    ArrayList<PizzaItem> mPizzaItem;
    private Context mContext;
    public FinalAdapter(FinalOrderActivity context, ArrayList<PizzaItem> PizzaItem) {
        super(context,R.layout.final_list_design,R.id.final_pizza_name_txt);
        mContext=context;
        mPizzaItem=PizzaItem;
    }

    @Override
    public int getCount() {
        return mPizzaItem.size();
    }

    @Override
    public String getItem(int position){ return mPizzaItem.get(position).getMdata();}

    @NonNull
    @Override
    public View getView(int listViewPosition, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.final_list_design, null);
        }

        ImageView imageView=(ImageView)view.findViewById(R.id.final_image_pizza);
        TextView textNameView=(TextView)view.findViewById(R.id.final_pizza_name_txt);
        TextView textContentView=(TextView)view.findViewById(R.id.final_pizza_content_txt);
        TextView priceView=(TextView)view.findViewById(R.id.final_pizza_price_per_unit);
        TextView amountPizzaView=(TextView)view.findViewById(R.id.final_pizza_amount);
        Button incPizzaView=(Button)view.findViewById(R.id.increase_amount);
        Button decPizzaView=(Button)view.findViewById(R.id.decrease_amount);
        imageView.setImageResource(mPizzaItem.get(listViewPosition).getmImage());
        textNameView.setText(mPizzaItem.get(listViewPosition).getMdata());
        textContentView.setText(mPizzaItem.get(listViewPosition).getmDescription());
        priceView.setText(mPizzaItem.get(listViewPosition).getPrices()+" ₪");
        amountPizzaView.setText("Amount: "+mPizzaItem.get(listViewPosition).getmPizzaAmount());
        incPizzaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPizzaItem.get(listViewPosition).setmPizzaAmount(mPizzaItem.get(listViewPosition).getmPizzaAmount()+1);
                amountPizzaView.setText("Amount: "+mPizzaItem.get(listViewPosition).getmPizzaAmount());
            }
        });
        decPizzaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPizzaItem.get(listViewPosition).setmPizzaAmount(mPizzaItem.get(listViewPosition).getmPizzaAmount()-1);
                int currAmount =mPizzaItem.get(listViewPosition).getmPizzaAmount();
                amountPizzaView.setText("Amount: "+currAmount);
            }
        });
        return view;
    }
}
