package id.co.bspguard.android.pmmapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import id.co.bspguard.android.pmmapps.ContactUser.Insert;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class Login extends AppCompatActivity {
  EditText _emailText, _passwordText;
  Button _loginButton;
  VolleyObjectResult volleyObjectResult, vor = null;
  VolleyObjectService volleyObjectService, vos;
  Fungsi fungsi = new Fungsi();
  String url = "/dologin";
  JSONObject data = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    _loginButton = (Button) findViewById(R.id.button);
    _emailText = (EditText) findViewById(R.id.email);
    _passwordText = (EditText) findViewById(R.id.password);

    _loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login();
      }
    });
  }

  public void login(){
    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if (!validate()){
      onLoginFailed();
      return;
    }

    _loginButton.setEnabled(false);

    new android.os.Handler().postDelayed(
      new Runnable() {
        @Override
        public void run() {

//          String hasemail = "aku@kamu.com";
//          String haspassword = "demo123";
          final String email = _emailText.getText().toString();
          final String password = _passwordText.getText().toString();
          HashMap<String, String> di = new HashMap<String, String>();
          di.put("email", email);
          di.put("password", password);
          final JSONObject data = new JSONObject(di);
          vor = new VolleyObjectResult() {
            @Override
            public void resSuccess(String requestType, JSONObject response) {
              if(email.equals(email) && password.equals(password) ){
                onLooginSuccess();


                SharedPreferences prefs = PreferenceManager
                        .getDefaultSharedPreferences(Login.this);
                SharedPreferences.Editor lds = prefs.edit();
                lds.putString("email", email);
                lds.putString("password", password);
                lds.commit();


                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
              }else{
                onLoginFailed();
              }
            }

            @Override
            public void resError(String requestType, VolleyError error) {

            }
//            (Exception e) {
//              onLoginFailed();
//              e.printStackTrace();
//            }
//
          };
          vos = new VolleyObjectService(vor, Login.this );
          vos.postJsonObject("POSTCALL", fungsi.url() + url, data);

        }
        }, 3000L);
  }

  public boolean validate(){
    boolean valid = true;
    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
      _emailText.setError("enter a valid email address");
    } else {
      _emailText.setError(null);
    }

    if(password.isEmpty() || password.length() < 4){
      _passwordText.setError("between 4 and 10 alphanumeric characters");
    } else {
      _passwordText.setError(null);
    }

    return valid;
  }

  public void onLoginFailed(){
    _passwordText.setError("please check password");
    _emailText.setError("please check email address");
    _loginButton.setEnabled(true);
  }

  public void onLooginSuccess(){
    _loginButton.setEnabled(true);
    finish();
  }

}

