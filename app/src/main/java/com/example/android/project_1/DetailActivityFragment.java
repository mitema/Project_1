package com.example.android.project_1;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    String movie_id;
    //  MovieInfoAdapter movie_info_adapter;
    Movie movie;
    View rootview;
    int trailer_length;
    //  static int count = 0;
    // ArrayList<Integer> layout = new ArrayList();


    public DetailActivityFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FetchTrailerReview obj = new FetchTrailerReview();
        obj.execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        movie = intent.getParcelableExtra("movie");

        ImageView imageview = (ImageView) rootview.findViewById(R.id.imageView);
        Picasso
                .with(getActivity())
                .load(movie.getPosterpath())
                .into(imageview);

        TextView title = (TextView) rootview.findViewById(R.id.textView);
        title.setText(movie.original_title);

        TextView release_date = (TextView) rootview.findViewById(R.id.textView2);
        release_date.setText(movie.release_date[0]);

        TextView movie_length = (TextView) rootview.findViewById(R.id.textView3);
        movie_length.setText("120min");

        TextView user_rating = (TextView) rootview.findViewById(R.id.textView4);
        user_rating.setText(movie.user_rating);

        Button favourite_btn = (Button) rootview.findViewById(R.id.button);
        //zfavourite_btn.setWidth(10);
        //favourite_btn.setLayoutParams(new RelativeLayout.LayoutParams(10, 10));

        //TextView plot_synopsis = (TextView) rootview.findViewById(R.id.textView5);
        //plot_synopsis.setText(movie.plot_synopsis);

        movie_id = movie.Id;
        Log.v(LOG_TAG, movie_id);


        //LayoutInflater vi = (LayoutInflater)getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View v = vi.inflate(R.layout.fragment_detail, null);

// fill in any details dynamically here
        //TextView textView = (TextView) v.findViewById(R.id.a_text_view);
        //textView.setText("your text");

// insert into main view
        // ViewGroup insertPoint = (ViewGroup) findViewById(R.id.insert_point);


       // RelativeLayout relativelayout = (RelativeLayout) rootview.findViewById(R.id.m2_relative);
        TextView plot_synop = (TextView) rootview.findViewById(R.id.m_textView);
        plot_synop.setText(movie.plot_synopsis);
        // LinearLayout.LayoutParams para1 = linear_layout.getLayoutParams();
        //ViewGroup insertPoint = (ViewGroup)rootview.findViewById(R.id.textView5);
        //insertPoint.addView(rootview, 0);
        // button will be displayed
        // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        // add the rule that places your button below your EditText object
        //params.addRule(RelativeLayout.BELOW, imageview.getId());
        //textView.setLayoutParams(params);
        //relativelayout.addView(textView);


        //RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //ImageView play_icon = new ImageView(getActivity());
        //play_icon.setImageResource(R.drawable.play_icon);
        //play_icon.setId((Integer.parseInt("2")));
        //param1.addRule(RelativeLayout.BELOW, textView.getId());
        // play_icon.setLayoutParams(param1);
        //play_icon.getLayoutParams().height = 50;
        // relativelayout.addView(play_icon);
        // play_icon.getLayoutParams().height = 500;

        //LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //TextView textView2 = new TextView(getActivity());
        // textView2.setText(movie.plot_synopsis);
        //textView.setId((Integer.parseInt("1")));
        //LinearLayout.LayoutParams para1 = linear_layout.getLayoutParams();
        //ViewGroup insertPoint = (ViewGroup)rootview.findViewById(R.id.textView5);
        //insertPoint.addView(rootview, 0);
        // button will be displayed
        //RelativeLayout.LayoutParams param2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        // add the rule that places your button below your EditText object
        //param2.addRule(RelativeLayout.BELOW, play_icon.getId());
        //textView2.setLayoutParams(param2);
        //relativelayout.addView(textView2);


        //  weather_data.setText(forecast);
        /*MenuItem item= (MenuItem)getActivity().findViewById(R.id.menu_item_share);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShareActionProvider mShareActionProvider = (ShareActionProvider) item.getActionProvider();

                return false;
            }

        });*/
        //   MovieInfoAdapter movie_info_adapter = new MovieInfoAdapter(getActivity()); //Arrays.asList(androidFlavors));
        //  ScrollView scrollView = (ScrollView) rootview.findViewById(R.id.scrollView);
        //  scrollView.setAdapter(movie_info_adapter);
        //
        // movie_info_adapter = new MovieInfoAdapter(getActivity()); //Arrays.asList(androidFlavors));
        // ListView listView = (ListView)rootview.findViewById(R.id.list);
        //  listView.setAdapter(movie_info_adapter);


        Log.v(LOG_TAG, "cgghhhh" + trailer_length);
       /* if(trailer_length!=0) {
            Log.v(LOG_TAG, "" + trailer_length);
            TextView txtTitle = (TextView) rootview.findViewById(R.id.l2_textView_1);
            txtTitle.setText("Trailer");
            ImageView imageView = (ImageView) rootview.findViewById(R.id.l2_imageView);
            imageView.setImageResource(R.drawable.play_icon);
            TextView trailerName = (TextView) rootview.findViewById(R.id.l2_textView_2);
            trailerName.setText("Trailer1");
            //TextView trailerName_2 = (TextView) rootview.findViewById(R.id.l2_textView_2);
            //trailerName_2.setText("GASKIA");\


            /*for(int i= 1; i<trailer_length; i++) {

                RelativeLayout.LayoutParams layoutParams_img = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);


                ImageView img = new ImageView(getActivity());
                img.setImageResource(R.drawable.play_icon);
                img.setId(Integer.parseInt("10"));
                //layoutParams_img.addRule(RelativeLayout.BELOW, );
                img.setLayoutParams(layoutParams_img);
                img.getLayoutParams().height = 200;
                relativelayout.addView(img);


                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                //imageView.setImageResource(R.drawable.play_icon);
                TextView trailer_Number = new TextView(getActivity());
                trailer_Number.setText("Trailer" + i);
                //layoutParams.addRule(RelativeLayout.BELOW, img.getId());
                layoutParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
                layoutParams.addRule(RelativeLayout.END_OF, img.getId());
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, img.getId());
                trailer_Number.setLayoutParams(layoutParams);
                //play_icon.getLayoutParams().height = 50;
                relativelayout.addView(trailer_Number);
                // play_icon.getLayoutParams().height = 500;
                // Button button2 = new Button(this);



            }
            //break;*/
      //  FetchTrailerReview obj = new FetchTrailerReview();
      //  obj.execute();

        return rootview;
    }

    /* public  class MovieInfoAdapter extends ArrayAdapter<Movie> {
         Context context;
         LayoutInflater inflater;
         int[] list = new int[trailer_length];

         public MovieInfoAdapter(Context context) {
             super(context, R.layout.listitem2);
             this.context = context;
             inflater = LayoutInflater.from(context);

         }

         private class ViewHolder {
             ImageView l2_imageView;
             TextView l2_txtTitle;
             TextView l2_trailerName;
             ImageView l3_imageView;
             TextView l3_txtTitle;
             TextView l3_trailerName;
             ;
         }

        /* @Override
         public int getViewTypeCount() {
             return trailer_length + 1;
         }

         @Override
         public int getItemViewType(int position) {
            int pos =0;
             if( position == 1)
                 pos = 1;
             else if(position==2)
                 pos = 2;
             else
                 pos = 3;

             return pos;

         }

         @Override
         public int getCount() {
             return trailer_length + 1;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             View view = null;
             Log.v(LOG_TAG, "come" + trailer_length);
             LinearLayout layout;



             int type = getItemViewType(count);

             ViewHolder holder_1 = new ViewHolder();
             ViewHolder holder_2;



                 count = count+ 1;
                 Log.v(LOG_TAG, "possssss" + count);
                 switch (type) {
                     case (1):
                     if (convertView == null) {

                             Log.v(LOG_TAG, "position" + position);
                             view = inflater.inflate(R.layout.listitem1, parent, false);
                             holder_1.l2_txtTitle = (TextView) view.findViewById(R.id.text_1);
                            holder_1.l2_txtTitle.setText(movie.plot_synopsis);
                             view.setTag(holder_1);

                     }
                     else {

                         view = (View) convertView;
                         holder_1 = (ViewHolder) view.getTag();
                         // count = count+ 1;
                     }

                         return view;
                     case (2):
                         if (convertView == null) {
                             view = inflater.inflate(R.layout.listitem2, parent, false);
                             holder_1.l2_txtTitle = (TextView) view.findViewById(R.id.l2_textView_1);
                             holder_1.l2_imageView = (ImageView) view.findViewById(R.id.l2_imageView);
                             holder_1.l2_trailerName = (TextView) view.findViewById(R.id.l2_textView_2);
                             view.setTag(holder_1);
                             //break;
                         }
                         else {

                             view = (View) convertView;
                             holder_1 = (ViewHolder) view.getTag();
                             // count = count+ 1;
                         }
                         holder_1.l2_txtTitle.setText("Trailer");
                         holder_1.l2_trailerName.setText("Trailer" + count);
                         holder_1.l2_imageView.setImageResource(R.drawable.play_icon);
                         return view;
                     case (3):
                         if (convertView == null) {
                         view = inflater.inflate(R.layout.listitem3, parent, false);
                         holder_1.l3_imageView = (ImageView) view.findViewById(R.id.l3_imageView);
                         holder_1.l3_txtTitle = (TextView) view.findViewById(R.id.l3_textView);
                         view.setTag(holder_1);
                        // break;
                         }
                         else {

                             view = (View) convertView;
                             holder_1 = (ViewHolder) view.getTag();
                             // count = count+ 1;
                         }
                         holder_1.l3_txtTitle.setText("Trailer" + count);
                         holder_1.l3_imageView.setImageResource(R.drawable.play_icon);
                         return view;
                 }




             return null;
         }
     }  */


    public class FetchTrailerReview extends AsyncTask<String, Void, Integer> {
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
            String apiKey = "7690bb1c1447599f64e0901944ee9bab";

            try {
                final String TRAILER_BASE_URL = "http://api.themoviedb.org/3/movie/" + movie_id + "/videos?";
                final String API_KEY = "api_key";

                Uri builtUri = Uri.parse(TRAILER_BASE_URL).buildUpon()
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
        protected void onPostExecute(Integer trailerlength) {
            super.onPostExecute(trailerlength);
            trailer_length = trailerlength;

            Log.v(LOG_TAG, "huuuuuu" + trailer_length);
           /* if (trailer_length != 0) {
                Log.v(LOG_TAG, "" + trailer_length);
                TextView txtTitle = (TextView) rootview.findViewById(R.id.l2_textView_1);
                txtTitle.setText("Trailer");
                ImageView imageView = (ImageView) rootview.findViewById(R.id.l2_imageView);
                imageView.setImageResource(R.drawable.play_icon);
                TextView trailerName = (TextView) rootview.findViewById(R.id.l2_textView_2);
                trailerName.setText("Trailer1");
                //TextView trailerName_2 = (TextView) rootview.findViewById(R.id.l2_textView_2);
                //trailerName_2.setText("GASKIA");\
            }*/
                 LinearLayout relativelayout = (LinearLayout) rootview.findViewById(R.id.linear);
                for (int i = 0; i < trailer_length; i++) {


                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    TextView trailer_Number = new TextView(getActivity());
                    int pos = i+1;
                    trailer_Number.setText("Trailer" + pos);
                    // layoutParams.addRule(RelativeLayout.BELOW, img.getId());
                    //layoutParams.addRule(RelativeLayout.RIGHT_OF, img.getId());
                  //  layoutParams.addRule(RelativeLayout.END_OF, img.getId());
                    //layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, img.getId());
                    trailer_Number.setLayoutParams(layoutParams);
                    //play_icon.getLayoutParams().height = 50;
                    relativelayout.addView(trailer_Number);

                    RelativeLayout.LayoutParams layoutParams_img = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    ImageView img = new ImageView(getActivity());
                    img.setImageResource(R.drawable.play_icon);
                    img.setId(Integer.parseInt("10"));
                    //layoutParams_img.addRule(RelativeLayout.BELOW, );
                    img.setLayoutParams(layoutParams_img);
                    img.getLayoutParams().height = 200;
                    relativelayout.addView(img);




                    //imageView.setImageResource(R.drawable.play_icon);


                }


        }
    }
}