package es.npatarino.android.gotchallenge.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import es.npatarino.android.gotchallenge.Adapters.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.Engine.Parser;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class GoTHousesListFragment extends Fragment {

    private ContentLoadingProgressBar progresBar;
    private RecyclerView recycleview;
    private TextView emptyview;

    @Inject
    GoTHouseAdapter houseAdapter;
    @Inject
    TaskLauncher launcherTask;
    @Inject
    Context context;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        ((GoTApplication) getActivity().getApplication()).getTaskComponent().inject(this);

        View rootView = inflater.inflate(R.layout.fragment_list_house, container, false);

        recycleview = (RecyclerView) rootView.findViewById(R.id.rv_house);
        recycleview.setLayoutManager(new LinearLayoutManager(context));
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(houseAdapter);

        progresBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        emptyview = (TextView) rootView.findViewById(R.id.empty_view);

        getData();
        return rootView;
    }

    private void getData() {

        launcherTask.launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                final Parser houseParsed = new Parser(value.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        houseAdapter.setHousesArray(houseParsed.getAllHouses());
                        if (houseParsed.getAllHouses().size() < 1) {
                            emptyview.setVisibility(View.VISIBLE);
                        }
                        progresBar.hide();
                    }
                });
            }

            @Override
            public void onError(Object value) {
                progresBar.hide();
                emptyview.setVisibility(View.VISIBLE);
                emptyview.setText(value.toString());
            }
        });

    }
}