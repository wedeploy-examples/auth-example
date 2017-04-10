/**
* Copyright (c) 2000-present Liferay, Inc. All rights reserved.
*
* This library is free software; you can redistribute it and/or modify it under
* the terms of the GNU Lesser General Public License as published by the Free
* Software Foundation; either version 2.1 of the License, or (at your option)
* any later version.
*
* This library is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
* details.
*/

import UIKit
import WeDeploy

class LoginViewController: BaseViewController {

	@IBOutlet weak var emailTextField: UITextField!
	@IBOutlet weak var passwordTextField: UITextField!
	
	
	@IBAction func loginButtonClick() {
		guard let username = emailTextField.text,
			let password = passwordTextField.text else {
			print("you have to fill the credentials fields")
			return
		}
		
		WeDeploy.auth(authUrl)
			.signInWith(username: username, password: password)
			.toCallback { auth, error in
				self.handleLoginResult(auth: auth, error: error)
			}
	}
	
	@IBAction func githubButtonClick() {
		let provider = AuthProvider(provider: .github, redirectUri: "my-app://")
		
		WeDeploy.auth(authUrl)
			.signInWithRedirect(provider: provider) { auth, error in
				print("entro aqui")
				self.handleLoginResult(auth: auth, error: error)
			}
	}
	
	@IBAction func googleButtonClick() {
		let provider = AuthProvider(provider: .google, redirectUri: "my-app://")
		
		WeDeploy.auth(authUrl)
			.signInWithRedirect(provider: provider) { auth, error in
				self.handleLoginResult(auth: auth, error: error)
			}
	}
	
	func handleLoginResult(auth: Auth?, error: Error?) {
		if let _ = auth {
			showAlert(with: "Success", message: "Sign-in successfully")
		}
		else {
			showAlert(with: "Error", message: "Sign-in failed.")
		}
	}

}

