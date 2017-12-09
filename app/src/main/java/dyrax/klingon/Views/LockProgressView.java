package dyrax.klingon.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.util.AttributeSet;
import android.view.View;

import dyrax.klingon.R;


public class LockProgressView extends View {
    public LockProgressView(Context context) {
        super(context);
        init(context);
    }

    public LockProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LockProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    VectorDrawableCompat blue;
    VectorDrawableCompat black;

    private void init(Context context) {
        blue = VectorDrawableCompat.create(getContext().getResources(), R.drawable.ic_lock_blue_48px, null);
        black = VectorDrawableCompat.create(getContext().getResources(), R.drawable.ic_lock_black_48px, null);
    }

    float percent;

    public void setPercent(float percent) {
        this.percent = Math.max(0.0f, Math.min(1.0f, percent));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.blue.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.blue.draw(canvas);
        canvas.clipRect(0, 0, canvas.getWidth(), (1.0f-percent) * (float)canvas.getHeight());
        this.black.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        this.black.draw(canvas);
    }
}
