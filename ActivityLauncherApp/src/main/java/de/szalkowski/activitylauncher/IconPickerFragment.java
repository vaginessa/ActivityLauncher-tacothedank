package de.szalkowski.activitylauncher;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

public class IconPickerFragment extends Fragment implements IconListAsyncProvider.Listener<IconListAdapter> {
    protected GridView grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.icon_picker, null);

        this.grid = (GridView) view;
        this.grid.setOnItemClickListener((view1, item, index, id) ->
                Toast.makeText(getActivity(),
                        view1.getAdapter().getItem(index).toString(),
                        Toast.LENGTH_SHORT).show());

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        IconListAsyncProvider provider = new IconListAsyncProvider(this.getActivity(), this);
        provider.execute();
    }

    @Override
    public void onProviderFinished(AsyncProvider<IconListAdapter> task,
                                   IconListAdapter value) {
        try {
            this.grid.setAdapter(value);
        } catch (Exception e) {
            Toast.makeText(this.getActivity(), R.string.error_icons, Toast.LENGTH_SHORT).show();
        }
    }
}
