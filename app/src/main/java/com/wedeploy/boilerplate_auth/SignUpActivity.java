package com.wedeploy.boilerplate_auth;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.wedeploy.boilerplate_auth.databinding.SignUpActivityBinding;
import com.wedeploy.sdk.Callback;
import com.wedeploy.sdk.transport.Response;

public class SignUpActivity extends BaseActivity {

	private SignUpActivityBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up_activity);

		binding = DataBindingUtil.setContentView(this, R.layout.sign_up_activity);

		binding.goToLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		binding.signUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = binding.name.getText().toString();
				String email = binding.email.getText().toString();
				String password = binding.password.getText().toString();

				if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
					doSignUp(name, email, password);
				}
				else {
					Toast.makeText(SignUpActivity.this,
						"You have to fill all the fields", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void doSignUp(String name, String email, String password) {
		weDeploy.auth(AUTH_URL)
			.createUser(email, password, name)
			.execute(new Callback() {
				@Override
				public void onSuccess(Response response) {
					showAlert("Success", "Signed up successfully");
				}

				@Override
				public void onFailure(Exception e) {
					Log.e("", e.toString());
					showAlert("Error", "Sign up error");
				}
			});
	}
}
