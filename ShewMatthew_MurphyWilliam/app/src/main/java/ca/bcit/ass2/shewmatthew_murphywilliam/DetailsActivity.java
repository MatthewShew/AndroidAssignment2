package ca.bcit.ass2.shewmatthew_murphywilliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        TextView name = (TextView) findViewById(R.id.detailName);
        TextView capital = (TextView) findViewById(R.id.detailCapital);
        TextView region = (TextView) findViewById(R.id.detailRegion);
        TextView population = (TextView) findViewById(R.id.detailPopulation);
        TextView area = (TextView) findViewById(R.id.detailArea);
        TextView borders = (TextView) findViewById(R.id.detailBorders);
        WebView flag = (WebView) findViewById(R.id.flag);

        String borderString = (String) intent.getExtras().get("borders");
        String flagString = intent.getExtras().getString("flag");
        name.setText((String) intent.getExtras().get("name"));
        capital.setText("Capital: " + (String) intent.getExtras().get("capital"));
        region.setText("Region: " + (String) intent.getExtras().get("region"));
        population.setText("Population: " + intent.getExtras().get("population"));
        area.setText("Area: " +  intent.getExtras().get("area"));

        if (borderString.equalsIgnoreCase("[]")) {
            borders.setText("Borders: N/A");
        } else {
            borders.setText((String) intent.getExtras().get("borders"));
        }

        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);
        flag.loadUrl(intent.getExtras().getString("flag"));

    }
}
