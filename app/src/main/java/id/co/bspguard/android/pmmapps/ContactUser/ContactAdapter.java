package id.co.bspguard.android.pmmapps.ContactUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.bspguard.android.pmmapps.MainActivity;
import id.co.bspguard.android.pmmapps.R;

public class ContactAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ContactDataSet> list;
    private ArrayList<ContactDataSet> arrayList;


    public ContactAdapter(Activity activity, List<ContactDataSet> list) {
        this.activity = activity;
        this.list = list;
        this.arrayList = new ArrayList<ContactDataSet>();
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
            convertView = inflater.inflate(R.layout.list_row_contact, null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView email = (TextView) convertView.findViewById(R.id.email);
        TextView nohp = (TextView) convertView.findViewById(R.id.nohp);

        
        final ContactDataSet mds = list.get(position);
        name.setText(mds.getNama());
        email.setText(mds.getEmail());
        nohp.setText(mds.getNohp());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iSlider = new Intent(activity, Update.class);
                Bundle extras = new Bundle();
                extras.putString("id", list.get(position).getId());
                extras.putString("nama",  list.get(position).getNama());
                extras.putString("email",  list.get(position).getEmail());
                extras.putString("alamat",  list.get(position).getAlamat());
                extras.putString("nohp",  list.get(position).getNohp());
                iSlider.putExtras(extras);
                activity.startActivity(iSlider);
            }
        });

        return convertView;
    }
}
