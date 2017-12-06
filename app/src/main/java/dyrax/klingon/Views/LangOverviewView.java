package dyrax.klingon.Views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import dyrax.klingon.Data.LangProgress;
import dyrax.klingon.R;


public class LangOverviewView extends ConstraintLayout {

    private long langId;

    public LangOverviewView(Context context, LangProgress lang) {
        super(context);
        this.init(context, lang);
    }

    private void init(Context context, LangProgress lang) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_language_overview, this);

        TextView langName = findViewById(R.id.textLangName);
        langName.setText(lang.getLanguage().getName());
        ProgressBar bar = findViewById(R.id.progressBar);
        bar.setMax(100);
        bar.setProgress(66);

        this.langId = lang.id;
    }

    public long getLangId() {
        return langId;
    }

}
