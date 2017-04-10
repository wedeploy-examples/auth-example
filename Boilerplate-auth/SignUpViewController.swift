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

class SignUpViewController: BaseViewController {
	
	@IBOutlet weak var nameTextField: UITextField!
	@IBOutlet weak var emailTextField: UITextField!
	@IBOutlet weak var passwordTextField: UITextField!

	@IBAction func signUpButtonClick() {
		guard let name = nameTextField.text,
			let email = emailTextField.text,
			let password = passwordTextField.text else {
				print("you have to fill the credentials fields")
				return
		}
		
		WeDeploy.auth(authUrl)
			.createUser(email: email, password: password, name: name)
			.toCallback { auth, error in
				if let _ = auth {
					self.showAlert(with: "Success", message: "Sign-up successfully")
				}
				else {
					self.showAlert(with: "Error", message: "Sign-up failed.")
				}
			}
	}

}
