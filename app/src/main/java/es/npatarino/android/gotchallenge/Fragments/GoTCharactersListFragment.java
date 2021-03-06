package es.npatarino.android.gotchallenge.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import javax.inject.Inject;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Engine.Parser;
import es.npatarino.android.gotchallenge.Engine.TaskLauncher;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.Interfaces.TaskResultCalback;
import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.Utils.Utils;

/**
 * Created by Carlos Carrasco on 12/03/2016.
 */
public class GoTCharactersListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private ArrayList<GoTCharacter> charactersArray;

    private RecyclerView recycleview;
    private ContentLoadingProgressBar progresBar;
    private TextView emptyview;

    @Inject
    GoTCharacterAdapter characterAdapter;
    @Inject
    TaskLauncher launcherTask;
    @Inject
    Context context;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        ((GoTApplication) getActivity().getApplication()).getTaskComponent().inject(this);

        View rootView = inflater.inflate(R.layout.fragment_list_character, container, false);

        recycleview = (RecyclerView) rootView.findViewById(R.id.rv_character);
        recycleview.setLayoutManager(new LinearLayoutManager(context));
        recycleview.setHasFixedSize(true);
        recycleview.setAdapter(characterAdapter);

        progresBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        emptyview = (TextView) rootView.findViewById(R.id.empty_view);

        setHasOptionsMenu(true);

        getData();

        return rootView;
    }

    private void getData() {
        //Dagger 2
        //characterAdapter = new GoTCharacterAdapter(activity);
        /*TaskConfiguration config = new TaskConfiguration();
        config.setUrl(ApiUrls.CHARACTERS);
        TaskInterface task = new TaskManager(activity, config, Utils.isNetworkAvailable(activity)).getTask();
        SyncDataManager<Object> syncData = new SyncDataManager<>(activity, config);*/
        /*new TaskLauncher(task, syncData)*/

        launcherTask.launchTask(new TaskResultCalback() {
            @Override
            public void onResult(Object value) {
                final Parser charactersParsed = new Parser(value.toString());
                charactersArray = charactersParsed.getAllCharacters();
                getActivity().runOnUiThread(new Runnable() {
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