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

public class ThirdActivity extends AppCompatActivity {

    TextView tv3;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    EditText editText9;
    Button btnCont2;
    private String user,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tv3=(TextView)findViewById(R.id.tv3);
        editText6=(EditText)findViewById(R.id.et6);
        editText7=(EditText)findViewById(R.id.et7);
        editText8=(EditText)findViewById(R.id.et8);
        editText9=(EditText)findViewById(R.id.et9);
        btnCont2=(Button)findViewById(R.id.btnCont2);
        Intent i=getIntent();
      id = i.getStringExtra("id");

        String url = "http://192.168.43.35/pdfyp/getUserID.php?user="+user;
        //String url = "http://192.168.1.3/pdfyp/insertStatement.php";
        HttpRequest request = new HttpRequest(url);

        request.setOnHttpResponseListener(mHttpResponseListener2);
        request.setMethod("GET");
        request.execute();


        btnCont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =  "http://192.168.43.35/pdfyp/insertFalse.php";
               // String url = "http://192.168.1.3/pdfyp/insertFalse.php";
                HttpRequest request = new HttpRequest(url);

                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("POST");
                request.addData("statement5", editText6.getText().toString());
                request.addData("statement6", editText7.getText().toString());
                request.addData("statement7", editText8.getText().toString());
                request.addData("statement8", editText9.getText().toString());
                request.addData("isTrue", "0");
                request.addData("id",id);
                request.execute();

                Intent intent=new Intent(ThirdActivity.this,FourthActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
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


