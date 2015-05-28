package lukesterlee.c4q.nyc.lunacalculator;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by Luke on 5/28/2015.
 */
public class SuggestionAdapter extends BaseAdapter implements Filterable {

    final String[] SUGGESTIONS = new String[]{
            "kg to lbs ",
            "lbs to kg ",
            "kg to oz ",
            "celsius to fahrenheit ",
            "fahrenheit to celsius ",
            "km to m ",
            "km to cm ",
            "km to mi ",
            "km to ft ",
            "km to in ",
            "km to yd ",
            "m to km ",
            "m to cm ",
            "m to mm ",
            "m to mi ",
            "m to ft ",
            "m to in ",
            "m to yd ",
            "mi to in ",
            "mi to ft ",
            "mi to m ",
            "mi to km ",
            "mi to yd ",
            "in to m ",
            "in to ft ",
            "in to yd ",
            "in to mm ",
            "in to m ",
            "in to cm ",
            "ft to yd ",
            "ft to m ",
            "ft to cm ",
            "ft to mm ",
            "ft to in ",
            "year to hour ",
            "year to day ",
            "year to min ",

    };

    @Override
    public int getCount() {
        return SUGGESTIONS.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
