package es.npatarino.android.gotchallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Engine.ApiUrls;
import es.npatarino.android.gotchallenge.Engine.Parser;
import es.npatarino.android.gotchallenge.Engine.TaskConfiguration;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.Engine.TaskManager;
import es.npatarino.android.gotchallenge.Interfaces.TaskInterface;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.SyncData.SyncDataManager;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTCharactersListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<GoTCharacter> charactersArray;
    private GoTCharacterAdapter characterAdapter;
    private RecyclerView recycleview;
    private ContentLoadingProgressBar progresBar;
    private TextView emptyview;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_character, container, false);
        progresBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        recycleview = (RecyclerView) rootView.findViewById(R.id.rv_character);
        emptyview = (TextView) rootView.findViewById(R.id.empty_view);

        setHasOptionsMenu(true);

        final FragmentActivity activity = super.getActivity();
        getData(activity);

        return rootView;
    }

    private void getData(final FragmentActivity activity) {
        characterAdapter = new GoTCharacterAdapter(activity);
        recycleview.setLayoutManager(new LinearLayoutManager(activity));
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(characterAdapter);

        TaskConfiguration config = new TaskConfiguration();
        config.setUrl(ApiUrls.CHARACTERS);

        TaskInterface task = new TaskManager(activity, config, Utils.isNetworkAvailable(activity)).getTask();
        SyncDataManager<Object> syncData = new SyncDataManager<>(activity, config);

        new TaskLauncher(task, syncData).launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                final Parser charactersParsed = new Parser(value.toString());
                charactersArray = charactersParsed.getAllCharacters();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        characterAdapter.setCharacterArray(charactersArray);
                        if (charactersParsed.getAllCharacters().size() < 1) {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        final MenuItem item = menu.findItem(R.id.ic_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final ArrayList<GoTCharacter> filteredModelList = Utils.filterList(charactersArray, query);
        characterAdapter.animateTo(filteredModelList);
        recycleview.scrollToPosition(0);
        return true;
    }

}