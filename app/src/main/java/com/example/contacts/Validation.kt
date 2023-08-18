package com.example.contacts

class Validation {
    companion object {
        fun  isEmailValid(email: String): Boolean {
            val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            return pattern.matches(email)
        }

        fun isPhoneNumberValid(phoneNumber: String): Boolean {
            val pattern = "^[1-9][0-9]{9}\$".toRegex()
            return pattern.matches(phoneNumber)
        }
    }

}