package hr.algebra.java.edb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MoviesDetails extends AppCompatActivity {
    MoviesDatabase db;
    String id, name, genre, rating, year;
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
        setContentView(R.layout.details_movies);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        genre = intent.getStringExtra("GENRE");
        rating = intent.getStringExtra("RATING");
        year = intent.getStringExtra("YEAR");
        ((TextView) findViewById(R.id.tvName)).setText(name);
        ((TextView) findViewById(R.id.tvGenre)).setText(genre);
        ((TextView) findViewById(R.id.tvRating)).setText(rating);
        ((TextView) findViewById(R.id.tvYear)).setText(year);

        db = new MoviesDatabase(this);
        db.open();
    }

    public void editM(View v) {
        Intent edit = new Intent(MoviesDetails.this, EditMovie.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAME", name);
        edit.putExtra("GENRE", genre);
        edit.putExtra("RATING", rating);
        edit.putExtra("YEAR", year);
        startActivity(edit);
    }

    public void deleteM(View v) {
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, Movies.class);
        startActivity(i);
    }
}