package dyrax.klingon.Views;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import dyrax.klingon.R;


public class CircularTimerView extends ConstraintLayout {

    CountDownTimer countDownTimer;

    public interface TimerListener {
        void onTimerCompleted();
    }

    public CircularTimerView(Context context) {
        super(context);
        init(context);
    }

    public CircularTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircularTimerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_circular_timer, this);
    }

    public void start(final int fullTime, final TimerListener listener) {
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final TextView textView = findViewById(R.id.textView);
        progressBar.setProgress(0);
        progressBar.setMax(fullTime);
        if(countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(fullTime,30) {

            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int)millisUntilFinished);
                textView.setText(String.format("%d", (millisUntilFinished/1000)+1));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                textView.setText("");
                listener.onTimerCompleted();
            }
        };
        countDownTimer.start();
    }

    public void stop() {
        if(countDownTimer != null) countDownTimer.cancel();
    }

}
