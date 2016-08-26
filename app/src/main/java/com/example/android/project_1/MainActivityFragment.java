package com.example.android.project_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivityFragment extends Fragment {
    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    ImageListAdapter img_list_adapter;
    ArrayList<Movie> movie_list;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            movie_list = new ArrayList<Movie>();

        }
        else {
            movie_list = savedInstanceState.getParcelableArrayList("movies");
            for(int i=0; i<movie_list.size(); i++)
            {
                img_list_adapter.add(movie_list.get(i));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies",  movie_list);
        super.onSaveInstanceState(outState);
    }

    public MainActivityFragment() {
    }

    @Override
    public void onStart()
    {
        super.onStart();
        sort();
    }


    public void sort()
    {
        String sort_info = "";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sort_by = preferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_popular));
        if(sort_by.equals(null))
            Log.v(LOG_TAG, "Errorrrrrrrrrrrrrrrrrrrr");
        else if(sort_by.equals(getString(R.string.pref_popular)))
        {
            sort_info = "popularity.desc";
        }
        else if (sort_by.equals(getString(R.string.pref_high_rating)))
        {
            sort_info = "vote_average.desc";
        }
        else
        {
            Log.e(LOG_TAG, "Error");
        }

        FetchImage obj = new FetchImage();

        Log.v(LOG_TAG, sort_info);
        obj.execute(sort_info);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Get a reference to the ListView, and attach this adapter to it.

        View root_view = inflater.inflate(R.layout.fragment_main, container, false);
        img_list_adapter = new ImageListAdapter(getActivity());
        GridView gridview = (GridView) root_view.findViewById(R.id.gridview);
        gridview.setAdapter(img_list_adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "weather data", Toast.LENGTH_SHORT).show();
                Movie movie = img_list_adapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);


            }
        });
        return root_view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }


    public class ImageListAdapter extends ArrayAdapter<Movie>
    {
        private final String LOG_TAG = ImageListAdapter.class.getSimpleName();
        private Context context;
        private LayoutInflater inflater;


        public ImageListAdapter(Context context) {
            super(context, R.layout.movie_view);

            this.context = context;
            inflater = LayoutInflater.from(context);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View imageView;
            if (convertView == null)
            {
                   imageView = inflater.inflate(R.layout.movie_view, parent, false);
            }
            else
            {
                    imageView = (View)convertView;
            }


             Picasso
                    .with(context)
                    .load(img_list_adapter.getItem(position).getPosterpath())
                    .into((ImageView)imageView);
                Log.v(LOG_TAG, "PICA " +img_list_adapter.getItem(0).getPosterpath());

            return imageView;

        }

    }



    public class FetchImage extends AsyncTask<String,Void, ArrayList<Movie>>
    {   private final String LOG_TAG = FetchImage.class.getSimpleName();

        private ArrayList<Movie> getPosterIDFromJson(String movieJsonStr) throws JSONException
        {
            final String MBD_RESULTS = "results";
            final String MBD_ID = "id";
            final String MBD_TITLE = "original_title";
            final String MBD_POSTER_PATH = "poster_path";
            final String MBD_PLOT_SYNOPSIS = "overview";
            final String MBD_USER_RATING = "vote_average";
            final String MBD_RELEASE_DATE = "release_date";

            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieArray = movieJson.getJSONArray(MBD_RESULTS);




            for (int i = 0; i < movieArray.length(); i++) {

                JSONObject movieObject = movieArray.getJSONObject(i);

                 movie_list.add(i, new Movie(movieObject.getString(MBD_ID),
                         movieObject.getString(MBD_TITLE),
                         movieObject.getString(MBD_POSTER_PATH),
                         movieObject.getString(MBD_PLOT_SYNOPSIS),
                         movieObject.getString(MBD_USER_RATING),
                         movieObject.getString(MBD_RELEASE_DATE)));

            }

            return movie_list;
        }


        @Override
        protected ArrayList<Movie> doInBackground(String... params)
        {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;
            String apiKey = "7690bb1c1447599f64e0901944ee9bab";
            String sort_details = params[0];

            try
            {
                final String MOVIE_BASE_URL =  "http://api.themoviedb.org/3/discover/movie?";
                final String SORT =  "sort_by";
                final String API_KEY = "api_key";


                Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT, sort_details)
                        .appendQueryParameter(API_KEY, apiKey)
                        .build();

                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read Input Stream into String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                movieJsonStr = buffer.toString();


            }
            catch(IOException e)
            {
                Log.e(LOG_TAG, " Error: Url not parsing ");

            }
            finally

            {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                //Log.v(LOG_TAG, sort_details);
                return getPosterIDFromJson(movieJsonStr);
            } catch (JSONException e) {
                Log.v(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();

            }

            return null;
        }


        @Override
        protected void onPostExecute(ArrayList<Movie> movie_details) {
            super.onPostExecute(movie_details);

            if (movie_details != null) {
                  img_list_adapter.clear();
                  for (int i = 0; i < movie_details.size(); i++) {
                    img_list_adapter.add(movie_details.get(i));
                }


            }
            Log.v(LOG_TAG, img_list_adapter.getItem(0).getPosterpath());
            Log.v(LOG_TAG, "I'm here again");
        }

    }

}
