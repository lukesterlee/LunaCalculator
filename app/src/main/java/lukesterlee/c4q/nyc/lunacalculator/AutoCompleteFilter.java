//package lukesterlee.c4q.nyc.lunacalculator;
//
//import android.widget.Filter;
//
//import java.util.ArrayList;
//
///**
// * Created by Luke on 6/9/2015.
// */
//public class AutoCompleteFilter extends Filter {
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//        FilterResults results = new FilterResults();
//
//        if (mOriginalValues == null) {
//            synchronized (mLock) {
//                mOriginalValues = new ArrayList<T>(mObjects);
//            }
//        }
//
//        if (prefix == null || prefix.length() == 0) {
//            ArrayList<T> list;
//            synchronized (mLock) {
//                list = new ArrayList<T>(mOriginalValues);
//            }
//            results.values = list;
//            results.count = list.size();
//        } else {
//            String prefixString = prefix.toString().toLowerCase();
//
//            ArrayList<T> values;
//            synchronized (mLock) {
//                values = new ArrayList<T>(mOriginalValues);
//            }
//
//            final int count = values.size();
//            final ArrayList<T> newValues = new ArrayList<T>();
//
//            for (int i = 0; i < count; i++) {
//                final T value = values.get(i);
//                final String valueText = value.toString().toLowerCase();
//
//                // First match against the whole, non-splitted value
//                if (valueText.startsWith(prefixString)) {
//                    newValues.add(value);
//                } else {
//                    final String[] words = valueText.split(" ");
//                    final int wordCount = words.length;
//
//                    // Start at index 0, in case valueText starts with space(s)
//                    for (int k = 0; k < wordCount; k++) {
//                        if (words[k].startsWith(prefixString)) {
//                            newValues.add(value);
//                            break;
//                        }
//                    }
//                }
//            }
//
//            results.values = newValues;
//            results.count = newValues.size();
//        }
//
//        return results;
//        return null;
//    }
//
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//
//    }
//}
