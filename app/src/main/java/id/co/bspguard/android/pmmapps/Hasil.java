package id.co.bspguard.android.pmmapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Hasil extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hasil);

    TextView alas = (TextView) findViewById(R.id.valuealas);
    TextView tinggi = (TextView) findViewById(R.id.valuetinggi);
    TextView hasil = (TextView) findViewById(R.id.valuehasil);

    if(getIntent().getExtras()!=null){
      /**
       * Jika Bundle ada, ambil data dari Bundle
       */
      Bundle bundle = getIntent().getExtras();
      alas.setText(bundle.getString("alas"));
      tinggi.setText(bundle.getString("tinggi"));
      hasil.setText(bundle.getString("hasil"));

    }else{
      /**
       * Apabila Bundle tidak ada, ambil dari Intent
       */
      alas.setText(getIntent().getStringExtra("alas"));
      tinggi.setText(getIntent().getStringExtra("tinggi"));
      hasil.setText(getIntent().getStringExtra("hasil"));
    }
  }
}
