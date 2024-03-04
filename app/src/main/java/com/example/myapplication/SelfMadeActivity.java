package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class SelfMadeActivity extends AppCompatActivity {
    private String userName;//the name of the user
    private ArrayList<ExtraOnPizzaItem> ExtraOnPizzaList;//the list of the extras
    private ArrayList<String> DescriptionList= new ArrayList<>();//the list of the description of each pizza we built
    private String CurrentPizzaSize="";//variable that tells us which size we chose
    private String CurrentPizzaCrust="";//varaible that tells us which crust we chose
    private String CurrentPizzaCheese="";//variable that tells us which cheese we chose
    private Button okbtn;//this button onclick checks how many extras we want to add , if nothing was select we will present a toast message
    private TextView finalPrice;//the price of the order
    private static int numOfExtraForThePizza;//number of extras we want to tadd on the pizza
    private LinearLayout ExtraOnPizzaLayout;
    private EditText numOfExtraOnPizza;//here we insert the number of extras we want
    private int pizzaSizePrice,pizzaCrustTypePrice,cheeseTypePrice,totalPrice,deliveryPrice;//prices
    private ArrayList<PizzaItem> mPizzaArray= new ArrayList<>();//here we save the items we user chose

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_made);
        okbtn = findViewById(R.id.okBtn);
        mPizzaArray=(ArrayList<PizzaItem>)getIntent().getSerializableExtra("pizzaArray");
        userName=getIntent().getStringExtra("name_plain_txt");
        numOfExtraForThePizza=0;
        pizzaSizePrice=0;
        pizzaCrustTypePrice=0;
        deliveryPrice=0;
        cheeseTypePrice = 0;
        initExtrasItems();
        ExtraOnPizzaLayout = findViewById(R.id.spinnerLayout);
        numOfExtraOnPizza = findViewById(R.id.numOfExtraOnPizza);
        finalPrice = findViewById(R.id.price_txt);

        //once lciked we will check if a number was inserted , if not we will present a message otherwise we build N number of spinners
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numOfExtrasOnPizza = numOfExtraOnPizza.getText().toString();//here we save how many extras the user asked in string
                if(numOfExtrasOnPizza.isEmpty()){//this tells us the nothing was chosen
                    Toast.makeText(SelfMadeActivity.this, getString(R.string.no_one_extra_was_Chosen) ,Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                    }

                    numOfExtraForThePizza = Integer.parseInt(numOfExtrasOnPizza);//convert the numer of extras to int
                    updateFinalPrice();
                    ExtraOnPizzaLayout.removeAllViews();
                    if(numOfExtraForThePizza>=10)//we only allow 10 extras
                    {
                        Toast.makeText(SelfMadeActivity.this,R.string.too_much_Extra_On_Pizza_hint,Toast.LENGTH_SHORT).show();
                    } else{
                        //we build the spinner according to the settings we gave it
                        for (int i = 0; i < numOfExtraForThePizza; i++) {
                            Spinner spinner = new Spinner(SelfMadeActivity.this);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            params.setMargins(20, 10, 20, 0);
                            spinner.setLayoutParams(params);
                            spinner.setPadding(10, 10, 10, 10);
                            ExtraOnPizzaAdapter extraOnPizzaAdapter = new ExtraOnPizzaAdapter(SelfMadeActivity.this, ExtraOnPizzaList);
                            extraOnPizzaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(extraOnPizzaAdapter);
                            ExtraOnPizzaLayout.addView(spinner);
                        }
                    }
                }

            }
        });

    }
    //add extras to the spiner
    private void initExtrasItems(){
        ExtraOnPizzaList = new ArrayList<>();
        ExtraOnPizzaList.add(new ExtraOnPizzaItem(getString(R.string.pickle_extra),R.drawable.pickle));
        ExtraOnPizzaList.add(new ExtraOnPizzaItem(getString(R.string.b_olives_extra),R.drawable.b_olives));
        ExtraOnPizzaList.add(new ExtraOnPizzaItem(getString(R.string.g_olives_extra),R.drawable.g_olives));
        ExtraOnPizzaList.add(new ExtraOnPizzaItem(getString(R.string.tomato_extra),R.drawable.tomato));
        ExtraOnPizzaList.add(new ExtraOnPizzaItem(getString(R.string.pizza_cheese_name_blue),R.drawable.cheese_type3));
    }

    /*Here we handle all the lcikcs of which setting was chosen for the pizza and it's price*/
    public void onClickFamilySizeChosen(View view) {
        CurrentPizzaSize= getString(R.string.family_size);
        pizzaSizePrice = 40;
        updateFinalPrice();
    }

    public void onClickRegularSizeChosen(View view) {
        CurrentPizzaSize=getString(R.string.regular_size);
        pizzaSizePrice = 30;
        updateFinalPrice();
    }

    public void onClickSmallSizeChosen(View view) {
        CurrentPizzaSize=getString(R.string.small_size);
        pizzaSizePrice = 15;
        updateFinalPrice();
    }

    public void onClickNormalCrustWasChosen(View view) {
        CurrentPizzaCrust=getString(R.string.regular_crust);
        pizzaCrustTypePrice = 5;
        updateFinalPrice();
    }

    public void onClickFullWeatlCrustWasChosen(View view) {
        CurrentPizzaCrust=getString(R.string.full_weat_crust);

        pizzaCrustTypePrice = 7;
        updateFinalPrice();
    }

    public void onClickSpicyCrustWasChosen(View view) {
        CurrentPizzaCrust=getString(R.string.spicey_crust);
        pizzaCrustTypePrice = 10;
        updateFinalPrice();
    }

    public void onClickRegularCheeseWasChosen(View view) {
        CurrentPizzaCheese=getString(R.string.regular_cheese);
        cheeseTypePrice = 5;
        updateFinalPrice();
    }

    public void onClickChederCheeseWasChosen(View view) {
        CurrentPizzaCheese=getString(R.string.cheder_cheese);
        cheeseTypePrice = 10;
        updateFinalPrice();
    }

    public void onClickBlueCheeseWasChosen(View view) {
        CurrentPizzaCheese=getString(R.string.blue_cheese);
        cheeseTypePrice = 20;
        updateFinalPrice();
    }

    //update the total amount
    private void updateFinalPrice() {
        totalPrice=(pizzaSizePrice + pizzaCrustTypePrice + cheeseTypePrice + numOfExtraForThePizza*5+deliveryPrice);
        finalPrice.setText("Total amount: " + totalPrice);
    }

    public void onClickFinishOrder(View view) {

        if (CurrentPizzaSize.equals("")||CurrentPizzaCrust.equals("")||CurrentPizzaCheese.equals("")){
            Toast.makeText(SelfMadeActivity.this,getString(R.string.no_pizza_combo_was_Chosen),Toast.LENGTH_SHORT).show();
        }
        else{
            DescriptionList.add(CurrentPizzaSize);
            DescriptionList.add(CurrentPizzaCrust);
            DescriptionList.add(CurrentPizzaCheese);
            for (int i = 0; i< numOfExtraForThePizza;i++) {
                View currPizzaExtra = ExtraOnPizzaLayout.getChildAt(i);
                TextView curSpinner = currPizzaExtra.findViewById(R.id.extra_type_for_pizza_name);
                DescriptionList.add((String) curSpinner.getText());
            }
            StringBuilder sb = new StringBuilder();
            for (String s : DescriptionList)
            {
                sb.append(s);
                sb.append(", ");
            }
            PizzaItem curPizza = new PizzaItem(getString(R.string.special_pizza),R.drawable.family_pizza,""+totalPrice,1,sb.toString());
            mPizzaArray.add(curPizza);
            Intent intent=new Intent(this, FinalOrderActivity.class);
            intent.putExtra("pizzaArray",mPizzaArray);
            intent.putExtra("name_plain_txt",userName);
            startActivity(intent);
        }
    }
}