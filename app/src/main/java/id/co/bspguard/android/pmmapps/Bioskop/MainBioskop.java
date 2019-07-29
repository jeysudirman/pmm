package id.co.bspguard.android.pmmapps.Bioskop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class MainBioskop extends AppCompatActivity {

    private ListView listContact;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    private List<BioskopDataSet> list = new ArrayList<BioskopDataSet>();
    private BioskopAdapter bioskopAdapter;
    JSONObject data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bioskop);

        listContact = (ListView) findViewById(R.id.listBioskop);

        vor = new VolleyObjectResult() {
            @Override
            public void resSuccess(String requestType, JSONObject response) {
//                Toast.makeText(MainContact.this, response.toString(), Toast.LENGTH_LONG).show();

                try {
                    JSONArray jsonArray = response.getJSONArray("values");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject object =  jsonArray.getJSONObject(i);
                        BioskopDataSet bds = new BioskopDataSet();
                        bds.setNama_film(object.getString("nama_film"));
                        bds.setNomor_teater(object.getString("nomor_teater"));
                        bds.setJam_mulai(object.getString("jam_mulai"));
                        bds.setJam_selesai(object.getString("jam_selesai"));
                        bds.setHarga(object.getString("harga"));
                        bds.setId(object.getString("id"));
                        list.add(bds);
                    }
                    bioskopAdapter = new BioskopAdapter(MainBioskop.this, list);
                    bioskopAdapter.notifyDataSetChanged();
                    listContact.setAdapter(bioskopAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void resError(String requestType, VolleyError error) {
                View view = findViewById(R.id.coordinator);
                String message = "Maaf data film tidak ditemukan.";
            }
        };
        vos = new VolleyObjectService(vor, MainBioskop.this);
        vos.getJsonObject("GETCALL", fungsi.url());
    }
}
