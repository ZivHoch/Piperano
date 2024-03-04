package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FinalOrderActivity extends AppCompatActivity {
    private String userName;
    private ListView lvSpinner;
    private TextView finalPrice;
    private int totalPrice,deliveryPrice;
    private ArrayList<PizzaItem> mPizzaArray = new ArrayList<>();
    private TextView phoneNum;
    private TextView addressDelivey;
    private SwitchCompat switchDelivery;
    private String regexStr;
    private String regexAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);
        mPizzaArray = (ArrayList<PizzaItem>) getIntent().getSerializableExtra("pizzaArray");
        userName = getIntent().getStringExtra("name_plain_txt");
        lvSpinner = (ListView) findViewById(R.id.final_pizza_list_view);
        finalPrice = (TextView) findViewById(R.id.total_price_for_all_pizzas);
        switchDelivery= findViewById(R.id.switch_button);
        phoneNum= findViewById(R.id.phoneNumber);
        addressDelivey=findViewById(R.id.AdressForDelivery);
        updatePrice();
        FinalAdapter adapter = new FinalAdapter(this, mPizzaArray);
        lvSpinner.setAdapter(adapter);
        regexStr = "^[0-9]{10}$";
        regexAddress = "^[a-z]{20}";
        switchDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchDelivery.isChecked()){
                    addressDelivey.setVisibility(View.VISIBLE);
                    deliveryPrice=15;
                }else{
                    addressDelivey.setVisibility(View.GONE);
                    deliveryPrice=0;
                }
                updatePrice();
            }
        });
    }

    private void updatePrice() {
        totalPrice = 0;
        int curPrice, curAmount;
        for (int i = 0; i < mPizzaArray.size(); i++) {
            curAmount = mPizzaArray.get(i).getmPizzaAmount();
            if (curAmount <= 0) {
                mPizzaArray.remove(i);
            } else {
                curPrice = Integer.parseInt(mPizzaArray.get(i).getPrices());
                totalPrice += curPrice * curAmount;
            }
        }
        totalPrice+=deliveryPrice;
        finalPrice.setText("Total price: " + totalPrice + " ₪");
        FinalAdapter adapter = new FinalAdapter(this, mPizzaArray);
        lvSpinner.setAdapter(adapter);
    }

    public void onClickUpdatePrice(View view) {
        updatePrice();
    }

    public void onClickAddMoreItem(View view) {
        Intent intent = new Intent(this, Page_choosing_type_of_pizza.class);
        intent.putExtra("pizzaArray", mPizzaArray);
        intent.putExtra("name_plain_txt", userName);
        startActivity(intent);
    }

    public void onClickFinish(View v) {

        String address="";
        if (totalPrice == 0) {
            Toast.makeText(FinalOrderActivity.this, R.string.Error_no_pizza_in_order_hint, Toast.LENGTH_SHORT).show();
        }
        else if(!(phoneNum.getText().toString().matches(regexStr)))
        {
            Toast.makeText(FinalOrderActivity.this, getString(R.string.invalid_phone_number), Toast.LENGTH_SHORT).show();
        }
        else if(switchDelivery.isChecked() && addressDelivey.getText().toString().isEmpty()){
            Toast.makeText(FinalOrderActivity.this, getString(R.string.invalid_address), Toast.LENGTH_SHORT).show();
        }
        else{
            address+=addressDelivey.getText();
            AlertDialog.Builder alert = new AlertDialog.Builder(FinalOrderActivity.this);
            alert.setTitle("Your Order is recieved :)");
            if(!switchDelivery.isChecked()){
                alert.setMessage(getString(R.string.thank_you) + userName + getString(R.string.for_purchasing) +"\n"+ getString(R.string.we_will_contact)
                        +phoneNum.getText()+"\n" + getString(R.string.order_is_done) +"\n"+ getString(R.string.total_price) +totalPrice+ "₪");
            }
            else{
                alert.setMessage(getString(R.string.thank_you) + userName + getString(R.string.for_purchasing) + "\n" +
                        getString(R.string.delivery)+phoneNum.getText()+"\n" +  getString(R.string.delivery_guy_near_the_Adress)  +address+"\n" +  getString(R.string.total_price) +totalPrice +" ₪");
            }
            alert.setPositiveButton(R.string.RestartAppHint, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(FinalOrderActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            alert.show();
        }
    }
}