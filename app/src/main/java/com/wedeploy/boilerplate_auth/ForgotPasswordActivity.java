package com.wedeploy.boilerplate_auth;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.wedeploy.boilerplate_auth.databinding.ForgotPasswordActivityBinding;
import com.wedeploy.sdk.Callback;
import com.wedeploy.sdk.transport.Response;

public class ForgotPasswordActivity extends BaseActivity {

	private ForgotPasswordActivityBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password_activity);

		binding = DataBindingUtil.setContentView(this, R.layout.forgot_password_activity);

		binding.goToLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		binding.sendEmail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = binding.email.getText().toString();

				if (!email.isEmpty()) {
					sendResetPasswordEmail(email);
				}
			}
		});
	}

	private void sendResetPasswordEmail(String email) {
		weDeploy.auth(AUTH_URL)
			.sendPasswordResetEmail(email)
			.execute(new Callback() {
				@Override
				public void onSuccess(Response response) {
					showAlert("Success", "Email sent");
				}

				@Override
				public void onFailure(Exception e) {
					Log.e("", e.toString());
					showAlert("Error", "Could not send email");
				}
			});
	}
}
