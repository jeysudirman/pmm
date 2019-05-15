package id.co.bspguard.android.pmmapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
  EditText _emailText, _passwordText;
  Button _loginButton;

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
          String hasemail = "aku@kamu.com";
          String haspassword = "demo123";
          String email = _emailText.getText().toString();
          String password = _passwordText.getText().toString();
          Log.d("debug", "hasemail:" + hasemail + "email" + email);
          Log.d("debug", "haspassword:" + haspassword + "password" + password);

          try{
            if(email.equals(hasemail) && password.equals(haspassword) ){
              onLooginSuccess();
              Intent intent = new Intent(Login.this, MainActivity.class);
              startActivity(intent);
            }else{
              onLoginFailed();
            }
          } catch (Exception e) {
            onLoginFailed();
            e.printStackTrace();
          }
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
