package com.example.a14049472.myfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class FourthActivity extends AppCompatActivity {
    SearchView searchView;
    Button btnSearch;
    TextView tvFound;
    Button btnJoin;
    int gamekey=99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        searchView = (SearchView) findViewById(R.id.SearchView);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvFound = (TextView) findViewById(R.id.tvFound);
        btnJoin = (Button) findViewById(R.id.btnJoin);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.35/pdfyp/searchGame.php?gamekey="+gamekey;
                //String url = "http://192.168.1.3/pdfyp/insertStatement.php";
                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();



            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(FourthActivity.this,FifthAcitivty.class);
                startActivity(i);

            }
        });

    }
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        String someValue  = jsonObj.getString("gamekey");

                        Toast.makeText(getApplicationContext(), someValue, Toast.LENGTH_SHORT).show();
                        tvFound.setText(someValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            };
}

