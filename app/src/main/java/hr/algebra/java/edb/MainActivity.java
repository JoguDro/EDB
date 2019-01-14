package hr.algebra.java.edb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spChoice;
    SharedPref sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightheme() == true) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.WhiteTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spChoice = findViewById(R.id.spChoice);


        ArrayAdapter<CharSequence> adapterChoice = ArrayAdapter.createFromResource(this, R.array.choice, R.layout.my_spinner);
        adapterChoice.setDropDownViewResource(R.layout.my_spinner);
        spChoice.setAdapter(adapterChoice);
        spChoice.setOnItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.black:
                Toast.makeText(this, "Dark theme is selected", Toast.LENGTH_SHORT).show();
                sharedPref.setNightTheme(true);
                recreate();
                break;
            case R.id.white:
                Toast.makeText(this, "Light theme is selected", Toast.LENGTH_SHORT).show();
                sharedPref.setNightTheme(false);
                recreate();
                break;

        }
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                return;
            case 1:
                Intent a = new Intent(MainActivity.this, Anime.class);
                startActivity(a);
                return;
            case 2:
                Intent m = new Intent(MainActivity.this, Movies.class);
                startActivity(m);
                return;
            case 3:
                Intent s = new Intent(MainActivity.this, Series.class);
                startActivity(s);
                return;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
