package dyrax.klingon.Activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dyrax.klingon.Data.LanguageDatabase;
import dyrax.klingon.Data.Languages;
import dyrax.klingon.R;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int count = sharedPref.getInt(getString(R.string.saved_counter), 0);
        TextView textView = findViewById(R.id.counterView);
        textView.setText(Integer.toString(count));

        Languages.initialize(getAssets());
        LanguageDatabase.setInstance(Room.databaseBuilder(getApplicationContext(),
                LanguageDatabase.class, "database-lang").build());
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(TrainingActivity.EXTRA_ALPHABET, 0);
        startActivity(intent);
    }

    public void clickCounter(View view) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int count = 1 + sharedPref.getInt(getString(R.string.saved_counter), 0);
        editor.putInt(getString(R.string.saved_counter), count);
        editor.apply();
        TextView textView = findViewById(R.id.counterView);
        textView.setText(Integer.toString(count));
    }
}
