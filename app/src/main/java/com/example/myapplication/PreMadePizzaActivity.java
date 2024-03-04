package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import java.util.ArrayList;

//this is the
public class PreMadePizzaActivity extends AppCompatActivity {
    private String userName;
    private ListView lvSpinner;
    private ArrayList<PizzaItem> mPreMadePizzaSelected= new ArrayList<>();
    private LinearLayout LayoutList;
    private TextView pizzaFinalPrice;
    private ArrayList<PizzaItem> mPreMadePizza= new ArrayList<>();
    private ArrayList<String> mSpinnerData=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_made_pizza);
        lvSpinner=(ListView)findViewById(R.id.pizza_list_view);
        LayoutList=(LinearLayout)findViewById(R.id.Layout_List);
        pizzaFinalPrice=findViewById(R.id.pizza_price);
        mPreMadePizzaSelected=(ArrayList<PizzaItem>)getIntent().getSerializableExtra("pizzaArray");
        userName=getIntent().getStringExtra("name_plain_txt");
        mPreMadePizza.add(new PizzaItem(getString(R.string.peperoni_de_papa),R.drawable.pre_pizza1,"110",1,getString(R.string.peperoni_de_papa_description)));
        mPreMadePizza.add(new PizzaItem(getString(R.string.shimpy_the_shrimpty),R.drawable.pre_pizza2,"104",1,getString(R.string.shimpy_the_shrimpty_description)));
        mPreMadePizza.add(new PizzaItem(getString(R.string.super_spicy),R.drawable.pre_pizza3,"80",1,getString(R.string.super_spicy_description)));
        mPreMadePizza.add(new PizzaItem(getString(R.string.the_pineapple),R.drawable.pre_pizza4,"90",1,getString(R.string.the_pineapple_description)));
        mPreMadePizza.add(new PizzaItem(getString(R.string.colorful_pepper),R.drawable.pre_pizza5,"75",1,getString(R.string.colorful_pepper_description)));
        mPreMadePizza.add(new PizzaItem(getString(R.string.tomato_with_ato),R.drawable.pre_pizza6,"89",1,getString(R.string.tomato_with_ato_description)));
        for (int i=0;i<8;i++)
            mSpinnerData.add(i+"");
        MyAdapter adapter = new MyAdapter(this,mPreMadePizza,mSpinnerData);
        lvSpinner.setAdapter(adapter);
    }

    public void onClickFinishOrder(View view) {
        for (int i = 0; i< lvSpinner.getChildCount();i++){
            View currPizza = lvSpinner.getChildAt(i);
            PizzaItem curPizzaItem;
            AppCompatSpinner curSpinner= currPizza.findViewById(R.id.amount_spinner);
            int t=curSpinner.getSelectedItemPosition();
            if (t!=0){
                curPizzaItem=mPreMadePizza.get(i);
                curPizzaItem.setmPizzaAmount(t);
                mPreMadePizzaSelected.add(curPizzaItem);
            }
        }
        Intent intent=new Intent(this, FinalOrderActivity.class);
        intent.putExtra("pizzaArray",mPreMadePizzaSelected);
        intent.putExtra("name_plain_txt",userName);
        startActivity(intent);
    }
}