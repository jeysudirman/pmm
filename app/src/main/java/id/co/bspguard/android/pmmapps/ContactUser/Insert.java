package id.co.bspguard.android.pmmapps.ContactUser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class Insert extends AppCompatActivity {

    private ListView listContact;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "/store";
    JSONObject data = null;

    EditText _nama, _email, _alamat, _nohp;
    Button _simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        _nama = (EditText) findViewById(R.id.inama);
        _email = (EditText) findViewById(R.id.iemail);
        _alamat = (EditText) findViewById(R.id.ialamat);
        _nohp = (EditText) findViewById(R.id.ihp);
        _simpan = (Button) findViewById(R.id.btn_save);

        _simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = _nama.getText().toString();
                String email = _email.getText().toString();
                String alamat = _alamat.getText().toString();
                String nohp = _nohp.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("nama", nama);
                dt.put("email", email);
                dt.put("alamat", alamat);
                dt.put("nohp", nohp);
                final JSONObject data = new JSONObject(dt);


                vor = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {


                        try {
                            String message = response.getString("message");
                            Toast.makeText(Insert.this, message, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {

                            e.printStackTrace();
                            Toast.makeText(Insert.this, "Terjadi kesalahan !!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void resError(String requestType, VolleyError error) {

                    }
                };
                vos = new VolleyObjectService(vor, Insert.this);
                vos.postJsonObject("POSTCALL", fungsi.url() + url, data);
            }
        });


    }
}
