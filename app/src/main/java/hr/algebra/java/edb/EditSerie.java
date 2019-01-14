package hr.algebra.java.edb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditSerie extends AppCompatActivity {
    SeriesDatabase db;
    String id, name, genre, rating, year;
    EditText etname, etgenre, etrating, etyear;
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
        setContentView(R.layout.store_series);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        genre = intent.getStringExtra("GENRE");
        rating = intent.getStringExtra("RATING");
        year = intent.getStringExtra("YEAR");

        ((EditText) findViewById(R.id.etName)).setText(name);
        ((EditText) findViewById(R.id.etGenre)).setText(genre);
        ((EditText) findViewById(R.id.etRating)).setText(rating);
        ((EditText) findViewById(R.id.etYear)).setText(year);

        db = new SeriesDatabase(this);
        db.open();

        etname = findViewById(R.id.etName);
        etgenre = findViewById(R.id.etGenre);
        etrating = findViewById(R.id.etRating);
        etyear = findViewById(R.id.etYear);
    }

    public void saveS(View v) {
        name = etname.getText().toString();
        genre = etgenre.getText().toString();
        rating = etrating.getText().toString();
        year = etyear.getText().toString();
        db.update(Integer.parseInt(id), name, genre, rating, year);
        Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, Series.class);
        startActivity(i);
    }
}