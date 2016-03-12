package es.npatarino.android.gotchallenge.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import es.npatarino.android.gotchallenge.Adapters.GoTCharacterAdapter;
import es.npatarino.android.gotchallenge.Models.GoTStruct;
import es.npatarino.android.gotchallenge.R;

/**
 * Created by Usuario on 12/03/2016.
 */
public class GoTCharactersListFragment extends Fragment {

    private static final String TAG = "GoTListFragment";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        final ContentLoadingProgressBar pb = (ContentLoadingProgressBar) rootView.findViewById(R.id.pb);
        final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);

        new Thread(new Runnable() {

            @Override
            public void run() {
                String url = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000/characters";

                try {
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    GoTStruct charactersStruct = new GoTStruct(response.toString());

                    final GoTCharacterAdapter characterAdapter = new GoTCharacterAdapter(charactersStruct.getAllCharacters());

                    GoTCharactersListFragment.this.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv.setHasFixedSize(true);
                            rv.setAdapter(characterAdapter);
                            pb.hide();
                        }
                    });

                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }


            }
        }).start();
        return rootView;
    }
}