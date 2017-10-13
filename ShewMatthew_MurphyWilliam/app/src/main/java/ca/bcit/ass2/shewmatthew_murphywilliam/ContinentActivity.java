package ca.bcit.ass2.shewmatthew_murphywilliam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContinentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);

        ListView listContinents = (ListView) findViewById(R.id.continents);


        listContinents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContinentActivity.this, CountryActivity.class);

                switch (i) {
                    case 0:
                        intent.putExtra("selected", "americas");
                        break;
                    case 1:
                        intent.putExtra("selected", "africa");
                        break;
                    case 2:
                        intent.putExtra("selected", "asia");
                        break;
                    case 3:
                        intent.putExtra("selected", "europe");
                        break;
                    case 4:
                        intent.putExtra("selected", "oceania");
                        break;
                    default:
                        break;
                }

                startActivity(intent);
            }
        });
    }
}
