package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText namePlainTxt;
    private String Greeting;
    private ImageView pizzaImage;
    private  LanguageManager lang;
    private Button hebrewButton;
    ArrayList<PizzaItem> mPizzaArray= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namePlainTxt= findViewById(R.id.name_plain_txt);
        pizzaImage = findViewById(R.id.imageView);
        TextView greetingTextView = findViewById(R.id.greeting_with_time_txt);
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if (hour >= 12 && hour < 18) {
            Greeting = getString(R.string.goodafternoon);
        } else if (hour >= 18 && hour < 22) {
            Greeting = getString(R.string.goodevening);
        } else if (hour >= 22 || hour <= 4) {
            Greeting = getString(R.string.goodnight);
        } else {
            Greeting = getString(R.string.goodmorning);
        }

        greetingTextView.setText(Greeting);

        final Animation ImageAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        pizzaImage.startAnimation(ImageAnimation);

        lang = new LanguageManager(this);


    }
    public void onClickNameEntered(View view) {
        String name= namePlainTxt.getText().toString();
        if (name.trim().equals("")){
            name = "moni the anoni";
        }
        Intent intent=new Intent(this, Page_choosing_type_of_pizza.class);
        intent.putExtra("name_plain_txt",name);
        intent.putExtra("pizzaArray",mPizzaArray);
        startActivity(intent);
    }




    public void onClickChangeToHebrew(View view)
    {
        lang.updateResource("iw");
        recreate();
        Toast.makeText(this, getResources().getString(R.string.language_has_changed),
                Toast.LENGTH_SHORT).show();
    }


    public void onClickChangeToEnglish(View view) {
        lang.updateResource("en");
        recreate();
        Toast.makeText(this, getResources().getString(R.string.language_has_changed),
                Toast.LENGTH_SHORT).show();
    }
}