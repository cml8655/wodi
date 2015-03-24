package com.cml.wodi.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 使用自定义矢量字体
 * 
 * @author guxiangguo
 * 
 */
public class ElloFontTextView extends TextView {

	public ElloFontTextView(Context context) {
		super(context);
		Typeface fzzy = Typeface.createFromAsset(context.getAssets(),
				"font/fontello.ttf");
		setTypeface(fzzy);
	}

	public ElloFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Typeface fzzy = Typeface.createFromAsset(context.getAssets(),
				"font/fontello.ttf");
		setTypeface(fzzy);
	}

	public ElloFontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Typeface fzzy = Typeface.createFromAsset(context.getAssets(),
				"font/fontello.ttf");
		setTypeface(fzzy);
	}
	
	
}
