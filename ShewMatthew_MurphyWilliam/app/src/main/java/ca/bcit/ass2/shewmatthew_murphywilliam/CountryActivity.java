package ca.bcit.ass2.shewmatthew_murphywilliam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {


    private String TAG = CountryActivity.class.getSimpleName();
    private ListView lv;
    private ProgressDialog pDialog;
    // URL to get contacts JSON
    private String SERVICE_URL = "http://restcountries.eu/rest/v2/region/";

    ArrayList<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Intent intent = getIntent();
        String continent = (String) intent.getExtras().get("selected");
        SERVICE_URL = SERVICE_URL + continent;
        System.out.println(continent);

        countryList = new ArrayList<Country>();
        new GetCountries().execute();

        lv = (ListView) findViewById(R.id.countries);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CountryActivity.this, DetailsActivity.class);
                Country c = countryList.get(i);
                intent.putExtra("name", c.getName());
                intent.putExtra("capital", c.getCapital());
                intent.putExtra("region", c.getRegion());
                intent.putExtra("population", c.getPopulation());
                intent.putExtra("area", c.getArea());
                intent.putExtra("borders", c.getBorders());
                intent.putExtra("flag", c.getFlag());

                startActivity(intent);
            }
        });



    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetCountries extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(CountryActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray countryJsonArray = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < countryJsonArray.length(); i++) {
                        JSONObject c = countryJsonArray.getJSONObject(i);

                        String name = c.getString("name");
                        String capital = c.getString("capital");
                        String region = c.getString("region");
                        String population = c.getString("population");
                        String area = c.getString("area");
                        String borders = c.getString("borders");
                        String flag = c.getString("flag");

                        // tmp hash map for single contact
                        Country country = new Country();

                        if (area.equalsIgnoreCase("null")) {
                            country.setArea(0);
                        } else {
                            String[] a = area.split("\\.");
                            String fg = a[0];
                            int jk = Integer.parseInt(fg);
                            country.setArea(jk);
                        }


                        // adding each child node to HashMap key => value
                        country.setName(name);
                        country.setCapital(capital);
                        country.setRegion(region);
                        country.setPopulation(Integer.parseInt(population));

                        country.setBorders(borders);
                        country.setFlag(flag);

                        // adding contact to contact list
                        countryList.add(country);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            CountryAdapter adapter = new CountryAdapter(CountryActivity.this, countryList);

            // Attach the adapter to a ListView
            lv.setAdapter(adapter);
        }

    }
}
