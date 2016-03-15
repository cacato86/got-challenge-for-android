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

    private static final String URL_SERVER = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";
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

    private void getData(FragmentActivity activity) {
        final GoTHouseAdapter houseAdapter = new GoTHouseAdapter(activity);
        recycleview.setLayoutManager(new LinearLayoutManager(activity));
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(houseAdapter);

        TaskConfiguration config = new TaskConfiguration();
        config.setUrl(URL_SERVER);

        TaskInterface task = new TaskManager(activity, config, Utils.isNetworkAvailable(activity)).getTask();
        SyncDataManager<Object> syncData = new SyncDataManager<>(activity, config);

        new TaskLauncher(task, syncData).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                Parser houseStruct = new Parser(value.toString());
                houseAdapter.setHousesArray(houseStruct.getAllHouses());
                if (houseStruct.getAllHouses().size() < 1) {
                    emptyview.setVisibility(View.VISIBLE);
                }
                progresBar.hide();
            }

            @Override
            public void onError(Object value) {
                emptyview.setVisibility(View.VISIBLE);
                progresBar.hide();
                Toast.makeText(getContext(), value.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}