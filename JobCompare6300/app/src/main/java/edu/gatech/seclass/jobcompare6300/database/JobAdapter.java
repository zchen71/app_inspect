package edu.gatech.seclass.jobcompare6300.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.ComparisonItemModel;
import edu.gatech.seclass.jobcompare6300.database.Job.JobEntry;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.R;

public class JobAdapter extends BaseAdapter {

    private Context context;
    public static ArrayList<ComparisonItemModel> modelArrayList;

    public JobAdapter(Context context, ArrayList<ComparisonItemModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return this.modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0; // Not used/implemented
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_job, null, true);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            holder.itemCompany = (TextView) convertView.findViewById(R.id.itemCompany);


            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }


        holder.checkBox.setText("Checkbox " + position);
        holder.itemTitle.setText(modelArrayList.get(position).getJobTitle());
        holder.itemCompany.setText(modelArrayList.get(position).getCompany());

        holder.checkBox.setChecked(modelArrayList.get(position).isSelected());
        holder.checkBox.setTag(R.integer.btnplusview, convertView);
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView itemTitle = (TextView) tempview.findViewById(R.id.itemTitle);
                Integer pos = (Integer) holder.checkBox.getTag();
                //Toast.makeText(context, "Checkbox "+pos+" for item "+itemTitle.getText().toString()+" clicked!", Toast.LENGTH_SHORT).show();

                if (modelArrayList.get(pos).isSelected()) {
                    modelArrayList.get(pos).setSelected(false);
                } else {
                    modelArrayList.get(pos).setSelected(true);
                }

            }
        });

        return convertView;
    }

    private class ViewHolder {
        protected CheckBox checkBox;
        private TextView itemTitle;
        private TextView itemCompany;
    }


}
