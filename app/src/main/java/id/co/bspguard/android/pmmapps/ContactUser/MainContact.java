package id.co.bspguard.android.pmmapps.ContactUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.co.bspguard.android.pmmapps.R;
import id.co.bspguard.android.pmmapps.functions.Fungsi;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectResult;
import id.co.bspguard.android.pmmapps.functions.VolleyObjectService;

public class MainContact extends AppCompatActivity {

    private ListView listContact;
    VolleyObjectResult volleyObjectResult, vor = null;
    VolleyObjectService volleyObjectService, vos;
    Fungsi fungsi = new Fungsi();
    String url = "http://7f9a387a.ngrok.io/kontak";
    private List<ContactDataSet> list = new ArrayList<ContactDataSet>();
    private ContactAdapter contactAdapter;
    JSONObject data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact);

        listContact = (ListView) findViewById(R.id.listContact);

        vor = new VolleyObjectResult() {
            @Override
            public void resSuccess(String requestType, JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("values");
                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject object =  jsonArray.getJSONObject(i);
                        ContactDataSet nds = new ContactDataSet();
                        nds.setNama(object.getString("nama"));
                        nds.setEmail(object.getString("email"));
                        nds.setAlamat(object.getString("alamat"));
                        nds.setNohp(object.getString("nohp"));
                        nds.setId(object.getString("id"));
                        list.add(nds);
                    }
                    contactAdapter = new ContactAdapter(MainContact.this, list);
                    contactAdapter.notifyDataSetChanged();
                    listContact.setAdapter(contactAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void resError(String requestType, VolleyError error) {
                View view = findViewById(R.id.coordinator);
                String message = "Maaf data kontak tidak ditemukan.";
            }
        };
        vos = new VolleyObjectService(vor, MainContact.this);
        vos.getJsonObject("GETCALL", url);
    }
}
