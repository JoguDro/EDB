package hr.algebra.java.edb;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedPref;

    public SharedPref(Context context) {
        sharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    public void setNightTheme(Boolean state) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    public Boolean loadNightheme(){
        Boolean state = sharedPref.getBoolean("NightMode", false);
        return state;
    }

}
