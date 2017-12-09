package dyrax.klingon.Views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import dyrax.klingon.R;

public class DoubleProgressBar extends ConstraintLayout {
    public DoubleProgressBar(Context context) {
        super(context);
        init(context);
    }

    public DoubleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DoubleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_double_progressbar, this);
    }

    public void setPercent(float left, float right) {
        int l = Math.min(100, Math.max(0, (int)(left * 100.0f)));
        int r = Math.min(100, Math.max(0, (int)(right * 100.0f)));

        ProgressBar progressBarL = findViewById(R.id.progressBarL);
        progressBarL.setProgress(l);
        progressBarL.setMax(200);
        ProgressBar progressBarR = findViewById(R.id.progressBarR);
        progressBarR.setProgress(r);
        progressBarR.setMax(200);

        TextView text = findViewById(R.id.textView);
        text.setText(String.format("%d%%", (l+r)/2));
    }

    public void setProgress(int left, int leftMax, int right, int rightMax) {
        left = Math.min(left, leftMax);
        right = Math.min(right, rightMax);

        ProgressBar progressBarL = findViewById(R.id.progressBarL);
        progressBarL.setProgress(left);
        progressBarL.setMax(leftMax * 2);
        ProgressBar progressBarR = findViewById(R.id.progressBarR);
        progressBarR.setProgress(right);
        progressBarR.setMax(rightMax * 2);

        TextView text = findViewById(R.id.textView);
        text.setText(String.format("%d%%", ((left*100)/(leftMax*2))+((right*100)/(rightMax*2))));
        findViewById(R.id.imageView).setVisibility(left >= leftMax && right >= rightMax ? VISIBLE : GONE);
        text.setVisibility(left >= leftMax && right >= rightMax ? GONE : VISIBLE);
    }
}
