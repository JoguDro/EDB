package hr.algebra.java.edb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Series extends AppCompatActivity {

    SeriesDatabase db;
    SimpleCursorAdapter adapter;
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
        setContentView(R.layout.activity_series);

        getSupportActionBar().setTitle("Series");

        db = new SeriesDatabase(this);
        db.open();

        ListView lv = findViewById(R.id.listView1);
        int layoutstyle = R.layout.liststyle;
        int[] xml_id = new int[]{
                R.id.name, R.id.genre

        };
        String[] column = new String[]{
                "name", "genre",
        };
        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle, row, column, xml_id, 0);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String name = row.getString(row.getColumnIndexOrThrow("name"));
                String genre = row.getString(row.getColumnIndexOrThrow("genre"));
                String rating = row.getString(row.getColumnIndexOrThrow("rating"));
                String year = row.getString(row.getColumnIndexOrThrow("year"));

                Intent sd = new Intent(Series.this, SerieDetails.class);
                sd.putExtra("ID", _id);
                sd.putExtra("NAME", name);
                sd.putExtra("GENRE", genre);
                sd.putExtra("RATING", rating);
                sd.putExtra("YEAR", year);
                startActivity(sd);
            }
        });

        EditText et = findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(), "name");
            }
        });
    }

    public void addSerie(View v) {
        Intent addNewContact = new Intent(Series.this, AddNewSerie.class);
        startActivity(addNewContact);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Series.this, MainActivity.class);
        startActivity(i);
    }
}
