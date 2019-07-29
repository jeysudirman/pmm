package id.co.bspguard.android.pmmapps.Bioskop;

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

import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class Insert extends AppCompatActivity {

    private ListView listBioskop;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "/store";
    JSONObject data = null;

    EditText _namafilm, _noteater, _jammulai, _jamselesai, _harga;
    Button _simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_bioskop);

        _namafilm = (EditText) findViewById(R.id.inamafilm);
        _noteater = (EditText) findViewById(R.id.inoteater);
        _jammulai = (EditText) findViewById(R.id.ijam_mulai);
        _jamselesai = (EditText) findViewById(R.id.ijam_selesai);
        _harga = (EditText) findViewById(R.id.iharga);
        _simpan = (Button) findViewById(R.id.btn_save);

        _simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namafilm = _namafilm.getText().toString();
                String noteater = _noteater.getText().toString();
                String jammulai = _jammulai.getText().toString();
                String jamselesai = _jamselesai.getText().toString();
                String harga = _harga.getText().toString();

                HashMap<String, String> dt = new HashMap<String, String>();
                dt.put("nama_film", namafilm);
                dt.put("nomor_teater", noteater);
                dt.put("jam_mulai", jammulai);
                dt.put("jam_selesai", jamselesai);
                dt.put("harga", harga);
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
