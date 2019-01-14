package hr.algebra.java.edb;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewAnime extends AppCompatActivity {

    AnimeDatabase db;
    EditText etName, etGenre, etRating, etYear;
    String name, genre, rating, year;
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
        setContentView(R.layout.store_anime);

        etName = findViewById(R.id.etName);
        etGenre = findViewById(R.id.etGenre);
        etRating = findViewById(R.id.etRating);
        etYear = findViewById(R.id.etYear);

        db = new AnimeDatabase(this);
        db.open();
    }

    public void saveA(View v) {
        if (db.isExist(name)) {
            Toast.makeText(getApplicationContext(), "already exist", Toast.LENGTH_SHORT).show();
        } else {
            name = etName.getText().toString();
            genre = etGenre.getText().toString();
            rating = etRating.getText().toString();
            year = etYear.getText().toString();
            db.insert(name, genre, rating, year);
            Toast.makeText(getApplicationContext(), "anime added", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, Anime.class);
        startActivity(i);
    }
}