package com.example.memorygame;

import android.content.Context;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class TileButton extends androidx.appcompat.widget.AppCompatButton {

    private String tileContent;
    private boolean isMatched = false;

    public TileButton(Context context) {
        super(context);
        hide(); // Start hidden
        setAllCaps(false);
        setTextSize(32f);
    }

    public void setTileContent(String content) {
        this.tileContent = content;
    }

    public String getTileContent() {
        return tileContent;
    }

    public void reveal() {
        Animation flipIn = AnimationUtils.loadAnimation(getContext(), R.anim.flip_in);
        startAnimation(flipIn);
        setText(getTileContent());
        setEnabled(false);
    }

    public void hide() {
        Animation flipOut = AnimationUtils.loadAnimation(getContext(), R.anim.flip_out);
        startAnimation(flipOut);
        new Handler().postDelayed(() -> {
            setText("❓");
            setEnabled(true);
        }, 300);
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        if (matched) setEnabled(false);
    }
}