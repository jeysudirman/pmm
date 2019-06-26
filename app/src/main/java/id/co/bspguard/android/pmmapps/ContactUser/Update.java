package id.co.bspguard.android.pmmapps.ContactUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import id.co.bspguard.android.pmmapps.R;

public class Update extends AppCompatActivity {

    EditText nama, email, alamat, nohp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama = (EditText) findViewById(R.id.unama);
        email = (EditText) findViewById(R.id.uemail);
        alamat = (EditText) findViewById(R.id.ualamat);
        nohp = (EditText) findViewById(R.id.uhp);

        if(getIntent().getExtras()!=null){
            /**
             * Jika Bundle ada, ambil data dari Bundle
             */
            Bundle bundle = getIntent().getExtras();
            nama.setText(bundle.getString("nama"));
            email.setText(bundle.getString("email"));
            alamat.setText(bundle.getString("alamat"));
            nohp.setText(bundle.getString("nohp"));

        }else{
            /**
             * Apabila Bundle tidak ada, ambil dari Intent
             */
            nama.setText(getIntent().getStringExtra("nama"));
            email.setText(getIntent().getStringExtra("email"));
            alamat.setText(getIntent().getStringExtra("alamat"));
            nohp.setText(getIntent().getStringExtra("nohp"));

        }
    }
}
