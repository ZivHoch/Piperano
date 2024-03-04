package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


/*here we give the user the option to choose if he wants to order a pre made pizza or build one on his own */
public class Page_choosing_type_of_pizza extends AppCompatActivity {
    private String userName;//the name of the user so we can greet him
    private TextView greetings;//tet with a ready sentence that will ask the user what he wants
    ArrayList<PizzaItem> mPizzaArray= new ArrayList<>();//this is the list of the orders tha the user makes he can add multiple items and this array saves them all
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_choosing_type_of_pizza);
        greetings=findViewById(R.id.greetings_txt);
        userName=getIntent().getStringExtra("name_plain_txt");
        greetings.setText(getString(R.string.hello) + "," +userName+ "\n" + getString(R.string.what_would_you_like));
        mPizzaArray=(ArrayList<PizzaItem>)getIntent().getSerializableExtra("pizzaArray");
    }
    //this is the intent that will redirect us to the pre made pizza page
    public void onClickPreMadeChoose(View view) {
        Intent intent=new Intent(this, PreMadePizzaActivity.class);
        intent.putExtra("name_plain_txt",userName);
        intent.putExtra("pizzaArray",mPizzaArray);
        startActivity(intent);
    }

    //this intent will redirect us to the build your own pizza page
    public void onClickCustomMadeChoose(View view) {
        Intent intent=new Intent(this, SelfMadeActivity.class);
        intent.putExtra("name_plain_txt",userName);
        intent.putExtra("pizzaArray",mPizzaArray);
        startActivity(intent);
    }
}