package com.example.a14049472.myfyp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    EditText editText2,editText3,editText4,editText5;
    Button btnCont;

    Intent intent;
    private String user, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intent = getIntent();
        editText2 = (EditText) findViewById(R.id.et2);
        editText3 = (EditText) findViewById(R.id.et3);
        editText4 = (EditText) findViewById(R.id.et4);
        editText5 = (EditText) findViewById(R.id.et5);
        btnCont = (Button) findViewById(R.id.btnCont);

        user = intent.getStringExtra("user");

        String url = "http://192.168.43.35/pdfyp/getUserID.php?user="+user;
        //String url = "http://192.168.1.3/pdfyp/insertStatement.php";
        HttpRequest request = new HttpRequest(url);

        request.setOnHttpResponseListener(mHttpResponseListener2);
        request.setMethod("GET");
        request.execute();

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://192.168.43.35/pdfyp/insertStatement.php";
                //String url = "http://192.168.1.3/pdfyp/insertStatement.php";
                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("statement", editText2.getText().toString());
                request.addData("statement2", editText3.getText().toString());
                request.addData("statement3", editText4.getText().toString());
                request.addData("statement4", editText5.getText().toString());
                request.addData("isTrue", "1");
                request.addData("id",id);
                request.execute();


                // Code for step 1 end


                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
               i.putExtra("id",id);
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
                        user = jsonObj.getString("user");
                        id = jsonObj.getString("id");
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            };

    private HttpRequest.OnHttpResponseListener mHttpResponseListener2 =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        user = jsonObj.getString("user");
                        id = jsonObj.getString("id");
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}



















