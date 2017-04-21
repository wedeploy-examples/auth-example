package com.wedeploy.boilerplate_auth;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.wedeploy.boilerplate_auth.binding.Font;
import com.wedeploy.boilerplate_auth.databinding.LoginActivityBinding;
import com.wedeploy.sdk.Callback;
import com.wedeploy.sdk.WeDeploy;
import com.wedeploy.sdk.auth.Auth;
import com.wedeploy.sdk.auth.AuthProvider;
import com.wedeploy.sdk.auth.TokenAuth;
import com.wedeploy.sdk.transport.Response;

import static com.wedeploy.boilerplate_auth.binding.Font.ICONS_12;
import static com.wedeploy.boilerplate_auth.binding.Font.ICONS_16;

public class LoginActivity extends BaseActivity {

	private LoginActivityBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		if (getIntent().getData() != null) {
			parseOAuthToken();
		}

		binding = DataBindingUtil.setContentView(this, R.layout.login_activity);

		binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
			}
		});

		binding.goToForgotPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
			}
		});

		binding.login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = binding.email.getText().toString();
				String password = binding.password.getText().toString();

				if (!email.isEmpty() && !password.isEmpty()) {
					doLogin(email, password);
				}
				else {
					Toast.makeText(LoginActivity.this,
						"You have to fill all the fields", Toast.LENGTH_SHORT).show();
				}
			}
		});

		binding.googleSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doOAuthLogin(AuthProvider.Provider.GOOGLE);
			}
		});

		binding.githubSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doOAuthLogin(AuthProvider.Provider.GITHUB);
			}
		});

	}

	private void doLogin(String email, String password) {
		weDeploy.auth(AUTH_URL)
			.signIn(email, password)
			.execute(new Callback() {
				@Override
				public void onSuccess(Response response) {
					showAlert("Success", "Signed in");
				}

				@Override
				public void onFailure(Exception e) {
					showAlert("Error", "Login error");
				}
			});
	}

	private void doOAuthLogin(AuthProvider.Provider provider) {
		AuthProvider authProvider = new AuthProvider.Builder()
			.redirectUri("oauth-wedeploy-boilerplate://io.wedeploy.boilerplate-auth")
			.providerScope("email")
			.provider(provider)
			.build();

		weDeploy.auth(AUTH_URL)
			.signIn(this, authProvider);
	}

	private void parseOAuthToken() {
		Auth auth = TokenAuth.getAuthFromIntent(getIntent());

		if (auth != null) {
			showAlert("Success", "Signed in");
		}
	}
}
