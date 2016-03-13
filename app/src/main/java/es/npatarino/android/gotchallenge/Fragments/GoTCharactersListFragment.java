package es.npatarino.android.gotchallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.Engine.TaskManager;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.Models.GoTStruct;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTCharactersListFragment extends Fragment {

    private static final String URL_SERVER = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar pb = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

        FragmentActivity activity = super.getActivity();

        final GoTCharacterAdapter characterAdapter = new GoTCharacterAdapter(activity);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        rv.setHasFixedSize(true);
        rv.setAdapter(characterAdapter);

        TaskConfiguration config = new TaskConfiguration();
        config.setUrl(URL_SERVER);

        TaskInterface task = new TaskManager(activity, config).getTask();

        new TaskLauncher(activity, task).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                Log.e("CHARACTER", "ONRESULT");
                GoTStruct charactersStruct = new GoTStruct(value.toString());
                characterAdapter.setCharactersArray(charactersStruct.getAllCharacters());
                pb.hide();
            }

            @Override
            public void onError(Object value) {
                pb.hide();
                Toast.makeText(getContext(), value.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}