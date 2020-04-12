package com.canter.fullscreendemo.view;

import android.content.Context;
import android.graphics.Typeface;
import android.mtp.MtpEvent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

/**
 * Description : Text.java
 * Creation    : 2019-12-16
 * Author      : cangui.ccg
 */
public class Text {

    public static Builder create(Context context) {
        final Builder builder = new Builder();
        TextView textView = new TextView(context);
        builder.init(textView);
        return builder;
    }

    public static final class Builder {

        private TextView mTextView;

        private void init(TextView textViewRef) {
            this.mTextView = textViewRef;
        }

        public Builder textSize(int textSize) {
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            return this;
        }

        public Builder textColor(int color) {
            mTextView.setTextColor(color);
            return this;
        }

        public Builder bold() {
            mTextView.setTypeface(Typeface.DEFAULT_BOLD);
            return this;
        }

        public Builder textGravity(int gravity) {
            mTextView.setGravity(gravity);
            return this;
        }

        public Builder maxLines(int maxLines) {
            mTextView.setMaxLines(maxLines);
            return this;
        }

        public Builder text(String text) {
            mTextView.setText(text);
            return this;
        }

        public Builder ellipSize(TextUtils.TruncateAt where) {
            mTextView.setEllipsize(TextUtils.TruncateAt.END);
            return this;
        }

        public Builder singleLine(boolean singleLine) {
            mTextView.setSingleLine(singleLine);
            return this;
        }

        public Builder maxWidth(int maxPixels) {
            mTextView.setMaxWidth(maxPixels);
            return this;
        }

        public Builder maxEms(int maxEms) {
            mTextView.setMaxEms(maxEms);
            return this;
        }

        public TextView build() {
            return mTextView;
        }
    }
}
