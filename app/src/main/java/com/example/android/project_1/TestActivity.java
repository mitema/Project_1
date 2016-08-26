package com.example.android.project_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class TestActivity extends ActionBarActivity {

    String movie_id;
    MovieInfoAdapter movie_info_adapter;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        Intent intent = this.getIntent();
        movie = intent.getParcelableExtra("movie");
        movie_id = movie.Id;


        //movie_info_adapter = new MovieInfoAdapter(this); //Arrays.asList(androidFlavors));
        //ListView listView = (ListView)findViewById(R.id.list);
        //listView.setAdapter(movie_info_adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







        /*@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.listitem3, container, false);
           ;

      /* ImageView imageview = new ImageView(getActivity());
        imageview = (ImageView)view.findViewById(R.id.imageView);
        Picasso
                .with(getActivity())
                .load(movie.getPosterpath())
                .into(imageview);

        TextView title = (TextView) view.findViewById(R.id.textView);
         title.setText(movie.original_title);

        TextView release_date = (TextView) view.findViewById(R.id.textView2);
        release_date.setText(movie.release_date[0]);

        TextView movie_length = (TextView) view.findViewById(R.id.textView3);
        movie_length.setText("120min");

        TextView user_rating = (TextView) view.findViewById(R.id.textView4);
        user_rating.setText(movie.user_rating);

        Button favourite = (Button) view.findViewById(R.id.button);
        favourite.setWidth(10);

        //TextView plot_synopsis = (TextView) view.findViewById(R.id.textView5);
       // plot_synopsis.setText(movie.plot_synopsis); */




            //  weather_data.setText(forecast);
        /*MenuItem item= (MenuItem)getActivity().findViewById(R.id.menu_item_share);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShareActionProvider mShareActionProvider = (ShareActionProvider) item.getActionProvider();

                return false;
            }

        })


            return view;
        }*/

        public class MovieInfoAdapter extends BaseAdapter {
            Context context;
            LayoutInflater inflater;

            public MovieInfoAdapter(Context context) {
               // super(context, R.layout.fragment_detail);
                this.context = context;
                inflater = LayoutInflater.from(context);

            }
            private class ViewHolder {
                ImageView imageView;
                TextView txtTitle;
                TextView trailerName;
            }


            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = null;
                ViewHolder holder = null;
            /*if(position == 0) {
                if (convertView == null)

                {
                    view = inflater.inflate(R.layout.listitem2, parent, false);
                    holder.txtTitle.setText(movie.plot_synopsis);
                    view.setTag(holder);
                } else {
                    view = (View) convertView;
                }
            }*/
                if (convertView == null)
                {
                    convertView = inflater.inflate(R.layout.listitem2, parent, false);

                    holder = new ViewHolder();
                    holder.txtTitle = (TextView) convertView.findViewById(R.id.textView6);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.imageView2);
                    // holder.trailerName = (TextView) convertView.findViewById(R.id.list_item_movie_textview2);
                    convertView.setTag(holder);
                }
                else
                {
                    //convertView = (View) convertView;
                    convertView = (View) convertView.getTag();
                }

               holder.txtTitle.setText(movie.plot_synopsis);
               // holder.trailerName.setText("yes");
                holder.imageView.setImageResource(R.drawable.play_icon);
               // ((TextView) convertView.findViewById(R.id.textView6)).setText("hettttttt");
                return convertView;
            }


        }

        public class FetchTrailerReview extends AsyncTask<String,Void, Integer> {
            private final String LOG_TAG = FetchTrailerReview.class.getSimpleName();

            private Integer getReviewFromJson(String movieJsonStr) throws JSONException {
                final String MBD_RESULTS = "results";
                final String MBD_ID = "id";
                final String MBD_TITLE = "original_title";
                final String MBD_POSTER_PATH = "poster_path";
                final String MBD_PLOT_SYNOPSIS = "overview";
                final String MBD_USER_RATING = "vote_average";
                final String MBD_RELEASE_DATE = "release_date";

                JSONObject movieJson = new JSONObject(movieJsonStr);
                JSONArray movieArray = movieJson.getJSONArray(MBD_RESULTS);
                return movieArray.length();
            }


            @Override
            protected Integer doInBackground(String... params) {
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                String movieJsonStr = null;


                try {
                    final String TRAILER_URL = "http://api.themoviedb.org/3/movie/" + movie_id + "/videos?";


                    Uri builtUri = Uri.parse(TRAILER_URL).buildUpon()
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


                } catch (IOException e) {
                    Log.e(LOG_TAG, " Error: Url not parsing ");

                } finally

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
                    return getReviewFromJson(movieJsonStr);
                } catch (JSONException e) {
                    Log.v(LOG_TAG, e.getMessage(), e);
                    e.printStackTrace();

                }

                return null;
            }


            @Override
            protected void onPostExecute(Integer trailer_length) {
                // super.onPostExecute(trailer_length);

               /* if (movie_details != null) {
                    movie_info_adapter.clear();
                    for (int i = 0; i < movie_details.size(); i++) {
                        movie_info_adapter.add(movie_details.get(i));
                    }


                }
                //Log.v(LOG_TAG, m.getItem(0).getPosterpath());
               // Log.v(LOG_TAG, "I'm here again");
            } */

            }

        }
    }

