package ca.bcit.ass2.shewmatthew_murphywilliam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView name = (TextView) findViewById(R.id.detailName);
        TextView capital = (TextView) findViewById(R.id.detailCapital);
        TextView region = (TextView) findViewById(R.id.detailRegion);
        TextView population = (TextView) findViewById(R.id.detailPopulation);
        TextView area = (TextView) findViewById(R.id.detailArea);
        TextView borders = (TextView) findViewById(R.id.detailBorders);
        WebView flag = (WebView) findViewById(R.id.flag);

        String flagString = bundle.getString("flag");
        name.setText((String) bundle.get("name"));
        capital.setText(getResources().getString(R.string.capital, bundle.get("capital")));
        region.setText(getResources().getString(R.string.region, bundle.get("region")));
        population.setText(getResources().getString(R.string.population, bundle.get("population")));
        area.setText(getResources().getString(R.string.area, bundle.get("area")));

        String borderString = bundle.getString("borders");
        if ("[]".equalsIgnoreCase(borderString)) {
            borderString = "N/A";
        }
        borders.setText(getResources().getString(R.string.borders, borderString));

        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);
        flag.loadUrl(flagString);
    }
}
