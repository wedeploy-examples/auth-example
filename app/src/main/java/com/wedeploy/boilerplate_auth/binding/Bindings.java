package com.wedeploy.boilerplate_auth.binding;

import android.databinding.BindingAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Silvio Santos
 */

public class Bindings {

	@BindingAdapter({ "font" })
	public static void font(Button button, String fontName) {
		Font.setFont(button, fontName);
	}

	@BindingAdapter({ "font" })
	public static void font(TextView textView, String fontName) {
		Font.setFont(textView, fontName);
	}
}
