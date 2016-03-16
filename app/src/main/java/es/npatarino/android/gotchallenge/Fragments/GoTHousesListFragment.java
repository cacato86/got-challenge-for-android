package es.npatarino.android.gotchallenge.Fragments;

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
import android.widget.Toast;

import es.npatarino.android.gotchallenge.Adapters.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.Engine.ApiUrls;
import es.npatarino.android.gotchallenge.Engine.Parser;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.Engine.TaskManager;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTHousesListFragment extends Fragment {

    private ContentLoadingProgressBar progresBar;
    private RecyclerView recycleview;
    private TextView emptyview;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        progresBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        recycleview = (RecyclerView) rootView.findViewById(R.id.rv);
        emptyview = (TextView) rootView.findViewById(R.id.empty_view);

        FragmentActivity activity = super.getActivity();
        getData(activity);
        return rootView;
    }

    private void getData(final FragmentActivity activity) {
        final GoTHouseAdapter houseAdapter = new GoTHouseAdapter(activity);
        recycleview.setLayoutManager(new LinearLayoutManager(activity));
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(houseAdapter);

        TaskConfiguration config = new TaskConfiguration();
        config.setUrl(ApiUrls.HOUSES);

        TaskInterface task = new TaskManager(activity, config, Utils.isNetworkAvailable(activity)).getTask();
        SyncDataManager<Object> syncData = new SyncDataManager<>(activity, config);

        new TaskLauncher(task, syncData).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                final Parser houseParsed = new Parser(value.toString());
                activity.runOnUiThread(new Runnable() {
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
                //Toast.makeText(getContext(), value.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}