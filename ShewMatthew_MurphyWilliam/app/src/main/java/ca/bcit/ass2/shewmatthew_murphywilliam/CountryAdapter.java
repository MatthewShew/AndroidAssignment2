package ca.bcit.ass2.shewmatthew_murphywilliam;

/**
 * Created by Matt on 10/12/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by A00127241 on 2017-10-01.
 */

public class CountryAdapter extends ArrayAdapter<Country> {
    Context _context;
    public CountryAdapter(Context context, ArrayList<Country> country) {
        super(context, 0, country);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Country country = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        // Lookup view for data population

        TextView name = (TextView) convertView.findViewById(R.id.Name);
        // Populate the data into the template view using the data object
        name.setText(country.getName());



        // Return the completed view to render on screen
        return convertView;
    }
}