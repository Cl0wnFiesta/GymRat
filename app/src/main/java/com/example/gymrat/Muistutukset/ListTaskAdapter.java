package com.example.gymrat.Muistutukset;


import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.example.gymrat.R;
import java.util.ArrayList;
import java.util.HashMap;


public class ListTaskAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private Database database;

    public ListTaskAdapter(Activity myactivity, ArrayList<HashMap<String, String>> mydata, Database mydatabase) {
        activity = myactivity;
        data = mydata;
        database = mydatabase;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListTaskViewHolder holder = null;
        if (convertView == null) {
            holder = new ListTaskViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.task_list_row, parent, false);
            holder.task_name = convertView.findViewById(R.id.task_name);
            holder.checkbar = convertView.findViewById(R.id.checkbar);
            convertView.setTag(holder);
        } else {
            holder = (ListTaskViewHolder) convertView.getTag();
        }


        final HashMap<String, String> singleTask = data.get(position);
        final ListTaskViewHolder Holder = holder;

        holder.task_name.setId(position);
        holder.checkbar.setId(position);

        try {
            holder.checkbar.setOnCheckedChangeListener(null);
            if (singleTask.get("status").contentEquals("1")) {
                holder.task_name.setText(Html.fromHtml("<strike>" + singleTask.get("task") + "</strike>"));
                holder.checkbar.setChecked(true);
            } else {
                holder.task_name.setText(singleTask.get("task"));
                holder.checkbar.setChecked(false);
            }

            holder.checkbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        database.updateTaskStatus(singleTask.get("id"), 1);
                        Holder.task_name.setText(Html.fromHtml("<strike>" + singleTask.get("task") + "</strike>"));
                    } else {
                        database.updateTaskStatus(singleTask.get("id"), 0);
                        Holder.task_name.setText(singleTask.get("task"));
                    }

                }
            });


        } catch (Exception e) {
        }
        return convertView;
    }
}

class ListTaskViewHolder {
    TextView task_name;
    CheckBox checkbar;
}