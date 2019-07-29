package id.co.bspguard.android.pmmapps.Bioskop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import id.co.bspguard.android.pmmapps.ContactUser.MainContact;
import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class Update extends AppCompatActivity {

    private ListView listBioskop;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "/update";
    JSONObject data = null;

    EditText namafilm, noteater, jammulai, jamselesai, harga, uid;
    String _namafilm, _noteater, _jammulai, _jamselesai, _harga, _uid;
    Button _update, _delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bioskop);

        namafilm = (EditText) findViewById(R.id.unamafilm);
        noteater = (EditText) findViewById(R.id.unoteater);
        jammulai = (EditText) findViewById(R.id.ujam_mulai);
        jamselesai = (EditText) findViewById(R.id.ujam_selesai);
        harga = (EditText) findViewById(R.id.uharga);
        uid = (EditText) findViewById(R.id.uid);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            namafilm.setText(bundle.getString("nama_film"));
            noteater.setText(bundle.getString("nomor_teater"));
            jammulai.setText(bundle.getString("jam_mulai"));
            jamselesai.setText(bundle.getString("jam_selesai"));
            harga.setText(bundle.getString("harga"));
            uid.setText(bundle.getString("id"));


        }else{
            /**
             * Apabila Bundle tidak ada, ambil dari Intent
             */
            namafilm.setText(getIntent().getStringExtra("nama_film"));
            noteater.setText(getIntent().getStringExtra("nomor_teater"));
            jammulai.setText(getIntent().getStringExtra("jam_mulai"));
            jamselesai.setText(getIntent().getStringExtra("jam_selesai"));
            harga.setText(getIntent().getStringExtra("harga"));
            uid.setText(getIntent().getStringExtra("id"));


        }


        _update = (Button)findViewById(R.id.btn_update);
        _update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _namafilm = namafilm.getText().toString();
                _noteater = noteater.getText().toString();
                _jammulai = jammulai.getText().toString();
                _jamselesai = jamselesai.getText().toString();
                _harga = harga.getText().toString();
                _uid = uid.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("nama_film", _namafilm);
                dt.put("nomor_teater", _noteater);
                dt.put("jam_mulai", _jammulai);
                dt.put("jam_selesai", _jamselesai);
                dt.put("harga", _harga);
                final JSONObject data = new JSONObject(dt);

                volleyObjectResult = new VolleyObjectResult() {
                    @Override
                    public void resSuccess(String requestType, JSONObject response) {


                        try {
                            String message = response.getString("message");

                                Intent toList = new Intent(Update.this, MainBioskop.class);
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

                            Intent toList = new Intent(Update.this, MainBioskop.class);
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
