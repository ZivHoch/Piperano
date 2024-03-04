package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<String> {
    private ArrayList<PizzaItem> mPizzaItem;
    private ArrayList<String> mSpinnerData;
    private Context mContext;
    public MyAdapter(PreMadePizzaActivity context, ArrayList<PizzaItem> PizzaItem,ArrayList<String> spinnerData) {
        super(context,R.layout.list_view_design,R.id.pizza_name_txt);
        mContext=context;
        mPizzaItem=PizzaItem;
        mSpinnerData=spinnerData;
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
            view = inflater.inflate(R.layout.list_view_design, null);
        }
        ImageView imageView=(ImageView)view.findViewById(R.id.pizza_picture);
        TextView textNameView=(TextView)view.findViewById(R.id.pizza_name_txt);
        TextView textContentView=(TextView)view.findViewById(R.id.pizza_content_txt);
        TextView priceView=(TextView)view.findViewById(R.id.pizza_price);
        imageView.setImageResource(mPizzaItem.get(listViewPosition).getmImage());
        textNameView.setText(mPizzaItem.get(listViewPosition).getMdata());
        textContentView.setText(mPizzaItem.get(listViewPosition).getmDescription());
        priceView.setText(mPizzaItem.get(listViewPosition).getPrices()+" ₪");
        if(mSpinnerData!=null){
            Spinner spinner=(Spinner)view.findViewById(R.id.amount_spinner);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,mSpinnerData);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                    int amount = (spinnerPosition == 0) ? 1 : spinnerPosition;
                    priceView.setText(Integer.parseInt(mPizzaItem.get(listViewPosition).getPrices()) * amount + " ₪");
                    mPizzaItem.get(listViewPosition).setmPizzaAmount(amount);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        return view;
    }
}