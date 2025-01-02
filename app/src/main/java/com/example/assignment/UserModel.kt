package com.example.assignment

data class UserModel(
    var id: Int,
    var profilePic: Int,
    var username: String,
    var email: String,
    var pw: String,
    var contactNum: String,
    var occupation: String,
    var country: String
    ){
    constructor(username: String, email: String, pw: String, contactNum: String, occupation: String, country: String):
            this(0,0, username, email, pw, contactNum, occupation, country) {
                this.profilePic = R.drawable.baseline_account_circle_24
            }

    constructor(): this(0, 0, "", "", "", "", "", "")
}
