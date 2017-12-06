package dyrax.klingon.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.List;

import dyrax.klingon.Data.LangProgress;
import dyrax.klingon.Data.Language;
import dyrax.klingon.Data.LanguageDatabase;
import dyrax.klingon.Data.Languages;
import dyrax.klingon.R;
import dyrax.klingon.Views.LangOverviewView;

public class LanguageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {

    private static final int ID_ADD_LANG_BUTTON = 0;
    private static final int ID_LANG_VIEW = 1;

    private static class FillContentTask extends AsyncTask<Void, Void, List<LangProgress>> {
        LanguageActivity activity;

        FillContentTask(LanguageActivity activity) {
            this.activity = activity;
        }

        protected List<LangProgress> doInBackground(Void... params) {
            return LanguageDatabase.getInstance().languageDAO().loadAllLangProgress();
        }

        protected void onPostExecute(List<LangProgress> languageProgresses) {
            LinearLayout scrollLayout = activity.findViewById(R.id.scrollLayout);
            for (LangProgress l : languageProgresses) {
                LangOverviewView langView = new LangOverviewView(activity, l);
                langView.setId(ID_LANG_VIEW);
                langView.setOnClickListener(activity);
                scrollLayout.addView(langView, scrollLayout.getChildCount()-1);

            }
        }
    }

    private static class NewLangTask extends AsyncTask<String, Void, LangProgress> {

        LanguageActivity activity;

        NewLangTask(LanguageActivity activity) {
            this.activity = activity;
        }

        protected LangProgress doInBackground(String... params) {
            String langId = params[0];

            return LangProgress.CreateNew(langId);
        }

        protected void onPostExecute(LangProgress progress) {
            LinearLayout scrollLayout = this.activity.findViewById(R.id.scrollLayout);
            LangOverviewView langView = new LangOverviewView(this.activity, progress);
            langView.setId(ID_LANG_VIEW);
            langView.setOnClickListener(this.activity);
            scrollLayout.addView(langView, scrollLayout.getChildCount()-1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setId(ID_ADD_LANG_BUTTON);
        fab.setImageResource(R.drawable.ic_add_black_24px);
        fab.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //My Content
        new FillContentTask(this).execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getResources().getText(R.string.invite_text));
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.invite_friends)));
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case ID_ADD_LANG_BUTTON:
                List<Language> langs = Languages.ALL_LANGUAGES;
                final CharSequence langNames[] = new CharSequence[langs.size()];
                int i = 0;
                for (Language l : langs) {
                    langNames[i++] = l.getName();
                }
                final LanguageActivity activity = this;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose a new Language");
                builder.setItems(langNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new NewLangTask(activity).execute(langNames[which].toString());
                    }
                });
                builder.show();
                break;
            case ID_LANG_VIEW:
                LangOverviewView langView = (LangOverviewView) view;
                Intent intent = new Intent(this, ProgressActivity.class);
                intent.putExtra(ProgressActivity.EXTRA_LANG_ID, langView.getLangId());
                startActivity(intent);
                break;
        }
    }
}
