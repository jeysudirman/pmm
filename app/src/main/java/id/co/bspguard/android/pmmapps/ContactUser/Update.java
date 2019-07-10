package id.co.bspguard.android.pmmapps.ContactUser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import id.co.bspguard.android.pmmapps.MainActivity;
import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class Update extends AppCompatActivity {

    private ListView listContact;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "/update";
    JSONObject data = null;

    EditText nama, email, alamat, nohp, uid;
    String _nama, _email, _alamat, _nohp, _uid;
    Button _update, _delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama = (EditText) findViewById(R.id.unama);
        email = (EditText) findViewById(R.id.uemail);
        alamat = (EditText) findViewById(R.id.ualamat);
        nohp = (EditText) findViewById(R.id.uhp);
        uid = (EditText) findViewById(R.id.uid);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            nama.setText(bundle.getString("nama"));
            email.setText(bundle.getString("email"));
            alamat.setText(bundle.getString("alamat"));
            nohp.setText(bundle.getString("nohp"));
            uid.setText(bundle.getString("id"));


        }else{
            /**
             * Apabila Bundle tidak ada, ambil dari Intent
             */
            nama.setText(getIntent().getStringExtra("nama"));
            email.setText(getIntent().getStringExtra("email"));
            alamat.setText(getIntent().getStringExtra("alamat"));
            nohp.setText(getIntent().getStringExtra("nohp"));
            uid.setText(getIntent().getStringExtra("id"));


        }


        _update = (Button)findViewById(R.id.btn_update);
        _update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _nama = nama.getText().toString();
                _email = email.getText().toString();
                _alamat = alamat.getText().toString();
                _nohp = nohp.getText().toString();
                _uid = uid.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("nama", _nama);
                dt.put("email", _email);
                dt.put("alamat", _alamat);
                dt.put("nohp", _nohp);
                final JSONObject data = new JSONObject(dt);

                volleyObjectResult = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {


                        try {
                            String message = response.getString("message");

                                Intent toList = new Intent(Update.this, MainContact.class);
                                toList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Update.this.startActivity(toList);
                            Toast.makeText(Update.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                volleyObjectService = new VolleyObjectService(volleyObjectResult, Update.this);
                volleyObjectService.postJsonObject("POSTCALL", fungsi.url() + url +'/'+ _uid, data);

            }
        });

        _delete = (Button) findViewById(R.id.btn_delete);
        _delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _uid = uid.getText().toString();

                vor = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {


                        try {
                            String message = response.getString("message");

                            Intent toList = new Intent(Update.this, MainContact.class);
                            toList.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            Update.this.startActivity(toList);
                            Toast.makeText(Update.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                vos = new VolleyObjectService(vor, Update.this);
                vos.postJsonObject("GET", fungsi.url() +"/delete/"+ _uid, data);


            }
        });


    }

}
