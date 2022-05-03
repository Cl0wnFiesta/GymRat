package com.example.gymrat.Muistutukset;
/**
 * @author Jonne
 *
 */

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

    // A constructor.
    public ListTaskAdapter(Activity myactivity, ArrayList<HashMap<String, String>> mydata, Database mydatabase) {
        activity = myactivity;
        data = mydata;
        database = mydatabase;
    }

    /**
     * Returns the number of items in the data set.
     *
     * @return The number of items in the data array.
     */
    public int getCount() {
        return data.size();
    }

    /**
     * Return the item at the specified position in the data set.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we
     * want.
     * @return The position of the item in the list.
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * Return the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set of the item whose view we
     * want.
     * @return The position of the item in the list.
     */
    public long getItemId(int position) {
        return position;
    }

    // A method that is used to create a new view for each item in the list.

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
                /**
                 * It updates the status of the task in the database.
                 *
                 * @param buttonView The button view whose state has changed.
                 * @param isChecked The current checked state of the view
                 */
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

/**
 * The ListTaskViewHolder class is a class that holds the views that are used in the ListView
 */
class ListTaskViewHolder {
    TextView task_name;
    CheckBox checkbar;
}