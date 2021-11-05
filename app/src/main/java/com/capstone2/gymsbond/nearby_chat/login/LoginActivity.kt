package com.capstone2.gymsbond.nearby_chat.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import com.capstone2.gymsbond.MainActivity
import com.capstone2.gymsbond.R
import com.capstone2.gymsbond.nearby_chat.NearbyChatActivity
import com.capstone2.gymsbond.nearby_chat.Util
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "LoginActivity"
        const val KEY_USERNAME = "username"
        const val KEY_USER_UUID = "user-uuid"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Util.clearSharedPreferences(this)

        username_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
                true
            } else {
                false
            }
        }
        login_button.setOnClickListener {
            login()
        }
        home_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun login() {
        login_container_view.requestFocus()
        Util.hideKeyboard(this)

        if (Util.isConnected(this@LoginActivity)) {
            login_button.isEnabled = false

            var username = username_input.text.toString()
            if (username.isEmpty()) username = "Anonymous"

            val userUUID = UUID.randomUUID().toString()

            Util.getSharedPreferences(this).edit {
                putString(KEY_USER_UUID, userUUID)
                putString(KEY_USERNAME, username)
            }

            Log.i(TAG, "Logging is user.")
            val intent = Intent(this@LoginActivity, NearbyChatActivity::class.java)
            startActivity(intent)

            intent.putExtra(KEY_USER_UUID, userUUID)
                .putExtra(KEY_USERNAME, username)
        } else Snackbar.make(container, "No Internet Connection", Snackbar.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        login_button.isEnabled = true
    }
}