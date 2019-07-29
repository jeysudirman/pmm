package id.co.bspguard.android.pmmapps.Bioskop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.bspguard.android.pmmapps.R;

public class BioskopAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<BioskopDataSet> list;
    private ArrayList<BioskopDataSet> arrayList;


    public BioskopAdapter(Activity activity, List<BioskopDataSet> list) {
        this.activity = activity;
        this.list = list;
        this.arrayList = new ArrayList<BioskopDataSet>();
        this.arrayList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row_bioskop, null);
        TextView namafilm = (TextView) convertView.findViewById(R.id.namafilm);
        TextView noteater = (TextView) convertView.findViewById(R.id.noteater);
        TextView jammulai = (TextView) convertView.findViewById(R.id.jam_mulai);
        TextView jamselesai = (TextView) convertView.findViewById(R.id.jam_selesai);
        TextView harga = (TextView) convertView.findViewById(R.id.harga);

        final BioskopDataSet mds = list.get(position);
        namafilm.setText(mds.getNama_film());
        noteater.setText(mds.getNomor_teater());
        jammulai.setText(mds.getJam_mulai());
        jamselesai.setText(mds.getJam_selesai());
        harga.setText(mds.getHarga());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSlider = new Intent(activity, Update.class);
                Bundle extras = new Bundle();
                extras.putString("id", list.get(position).getId());
                extras.putString("nama_film",  list.get(position).getNama_film());
                extras.putString("nomor_teater",  list.get(position).getNomor_teater());
                extras.putString("jam_mulai",  list.get(position).getJam_mulai());
                extras.putString("jam_selesai",  list.get(position).getJam_selesai());
                extras.putString("harga",  list.get(position).getHarga());
                iSlider.putExtras(extras);
                activity.startActivity(iSlider);
            }
        });

        return convertView;
    }
}
