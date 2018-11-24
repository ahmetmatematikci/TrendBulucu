package net.hardforkcafe.sinyal.trendbulucu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button goldenCross, aroon,ma,cikis,cafe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goldenCross = (Button)findViewById(R.id.button);
        aroon = (Button)findViewById(R.id.button2);
        ma = (Button)findViewById(R.id.button3);
        cikis = (Button)findViewById(R.id.button4);
        cafe = (Button)findViewById(R.id.button8);


        goldenCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, goldencross.class);
                startActivity(intent);
            }
        });


        aroon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, aroon.class);
                startActivity(intent);

            }
        });


        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ma.class);
                startActivity(intent);

            }
        });


        cafe.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Uri urlCafe = Uri.parse("https://hardforkcafe.net/");

                Intent intent = new Intent(Intent.ACTION_VIEW, urlCafe);
                startActivity(intent);



            }
        });


        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finishAffinity();
                System.exit(0);

            }
        });




           }
}
