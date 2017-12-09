package dyrax.klingon.Data;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.Collections;

import dyrax.klingon.R;

public class Keyboard {

    private static class KeyboardOnClickListener implements OnClickListener {
        private ClickListener listener;
        private String character;

        KeyboardOnClickListener(ClickListener listener, String character) {
            this.listener = listener;
            this.character = character;
        }

        @Override
        public void onClick(View view) {
            this.listener.keyboardClick((Button)view, this.character);
        }
    }

    public interface ClickListener {
        void keyboardClick(Button bttn, String character);
    }

    public static class Row {
        private String[] characters;
        private float paddingLeft;
        private float paddingRight;
        private int charCount;
        private float backSpace;

        Row(float paddingLeft, int charCount, float paddingRight) {
            this(paddingLeft, charCount, paddingRight, null, -1.0f);
        }

        Row(float paddingLeft, int charCount, float paddingRight, String[] characters) {
            this(paddingLeft, charCount, paddingRight, characters, -1.0f);
        }

        Row(float paddingLeft, int charCount, float paddingRight, String[] characters, float backSpace) {
            this.charCount = charCount;
            this.paddingLeft = paddingLeft;
            this.paddingRight = paddingRight;
            this.characters = characters;
            this.backSpace = backSpace;
        }

        public float getBackSpace() {
            return backSpace;
        }

        public String[] getCharacters() {
            return characters;
        }

        public float getPaddingLeft() {
            return paddingLeft;
        }

        public float getPaddingRight() {
            return paddingRight;
        }

        public int getCharCount() {
            return charCount;
        }

        public void setCharacters(String[] characters) {
            this.characters = characters;
        }
    }

    private Row[] rows;
    private float size;
    private boolean randomized;
    private ArrayList<String> enabledChars;
    private int keyCount;
    private String neededChar;

    public Keyboard(Row[] rows, float size, boolean randomized) {
        this.rows = rows;
        this.size = size;
        this.randomized = randomized;
        this.keyCount = 0;
        for (Row r : this.rows) {
            keyCount += r.charCount;
        }
    }

    public void insert(ArrayList<String> characters, String neededChar) {
        this.neededChar = neededChar;
        if(this.randomized) {
            ArrayList<String> copied = new ArrayList<>(characters);
            copied.remove(neededChar);
            Collections.shuffle(copied);
            while(copied.size() > keyCount-1) copied.remove(copied.get(0));
            copied.add(neededChar);
            Collections.shuffle(copied);
            int i = 0;
            for (Row r : this.rows) {
                String[] arr = new String[r.charCount];
                for (int x = 0; x < r.charCount; x++, i++) {
                    arr[x] = copied.get(i);
                }
                r.setCharacters(arr);
            }
        }
        this.enabledChars = characters;
        if(!enabledChars.contains(neededChar))
            throw new IllegalArgumentException("neededChar not on Keyboard");
    }

    public LinearLayout createKeyboard(Activity activity, final ClickListener listener, Typeface font, float sizeFactor) {
        LinearLayout result = new LinearLayout(activity);
        result.setOrientation(LinearLayout.VERTICAL);
        result.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        for(Row l : this.rows) {
            LinearLayout lineLayout = new LinearLayout(activity);
            lineLayout.setOrientation(LinearLayout.HORIZONTAL);
            lineLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            lineLayout.setWeightSum(l.characters.length + l.paddingLeft + l.paddingRight);

            if(l.paddingLeft > 0.01) {
                View space = new View(activity);
                space.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT, l.paddingLeft));
                lineLayout.addView(space);
            }

            for(String c : l.characters) {
                Button bttn = new Button(activity, null, R.style.Widget_AppCompat_Button_Borderless);
                bttn.setMinWidth(0);
                //bttn.setTextAppearance(R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored);
                bttn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                bttn.setText(c);
                bttn.setTypeface(font);
                bttn.setTextSize(this.size * sizeFactor);
                if(!enabledChars.contains(c))
                    bttn.setTextColor(ContextCompat.getColor(activity, R.color.keyboard_disabled));
                bttn.setOnClickListener(new KeyboardOnClickListener(listener, c));
                bttn.setLayoutParams(new LayoutParams(1, LayoutParams.WRAP_CONTENT, 1));

                lineLayout.addView(bttn);
            }

            if(l.paddingRight > 0.01) {
                View space = new View(activity);
                space.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT, l.paddingRight));
                lineLayout.addView(space);
            }

            result.addView(lineLayout);
        }

        return result;
    }

    public LinearLayout createMarkedKeyboard(Activity activity, Typeface font, float sizeFactor) {
        LinearLayout result = new LinearLayout(activity);
        result.setOrientation(LinearLayout.VERTICAL);
        result.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        for(Row l : this.rows) {
            LinearLayout lineLayout = new LinearLayout(activity);
            lineLayout.setOrientation(LinearLayout.HORIZONTAL);
            lineLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            lineLayout.setWeightSum(l.characters.length + l.paddingLeft + l.paddingRight);

            if(l.paddingLeft > 0.01) {
                View space = new View(activity);
                space.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT, l.paddingLeft));
                lineLayout.addView(space);
            }

            for(String c : l.characters) {
                Button bttn = new Button(activity, null, R.style.Widget_AppCompat_Button_Borderless);
                bttn.setMinWidth(0);
                //bttn.setTextAppearance(R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored);
                bttn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                bttn.setText(c);
                bttn.setTypeface(font);
                bttn.setTextSize(this.size * sizeFactor);
                bttn.setTextColor(c.equals(neededChar) ? ContextCompat.getColor(activity, R.color.correct) : ContextCompat.getColor(activity, R.color.wrong));
                bttn.setLayoutParams(new LayoutParams(1, LayoutParams.WRAP_CONTENT, 1));

                lineLayout.addView(bttn);
            }

            if(l.paddingRight > 0.01) {
                View space = new View(activity);
                space.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT, l.paddingRight));
                lineLayout.addView(space);
            }

            result.addView(lineLayout);
        }

        return result;
    }
}
