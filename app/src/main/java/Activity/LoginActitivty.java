package Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Others.POJO;
import employee.guardian.psak.vsolv.R;

public class LoginActitivty extends AppCompatActivity {
    Button loginButton;
    EditText loginUserName, loginPassword;
    //TextView registerTextView;
    private static String URL  ="http://vga.ramstertech.com/freebieslearning/login.php";
    private Snackbar snackbar;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUserName = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        pd = new ProgressDialog(LoginActitivty.this);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });


    }

    private void loginRequest(){
        pd.setMessage("Signing In . . .");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(LoginActitivty.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Login",""+response);
                        pd.hide();
                        //shofghgfwSnackbar(response);

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String data=jsonObject.getString("MESSAGE");
                            POJO.setName(data);
                            if (POJO.getName().equals("SUCCESS")){
                                startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                            }else{
                                Log.v("Login","Faild");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pd.hide();
                      Log.d("ErrorResponse", String.valueOf(error));

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", loginUserName.getText().toString());
                params.put("password", loginPassword.getText().toString());
                params.put("Token", "7111797114100105971106449505132");
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);



    }
//    public void showSnackbar(String stringSnackbar){
//        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
//                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
//                .show();
//    }
}