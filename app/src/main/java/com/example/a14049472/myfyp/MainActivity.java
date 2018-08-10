package com.example.a14049472.myfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUser = et1.getText().toString();
                if (newUser.length() != 0) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("user", et1.getText().toString());
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Enter a name", Toast.LENGTH_SHORT).show();
                }

                String url = "http://192.168.43.35/pdfyp/createUser.php";
                //String url = "http://192.168.1.3/pdfyp/createUser.php";

                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("user", et1.getText().toString());

                request.execute();


                // Code for step 1 end

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
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}

