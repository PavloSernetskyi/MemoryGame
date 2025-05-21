package com.example.memorygame;

import android.content.Context;

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
        setText(tileContent);
    }

    public void hide() {
        setText("❓");
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        if (matched) setEnabled(false);
    }
}