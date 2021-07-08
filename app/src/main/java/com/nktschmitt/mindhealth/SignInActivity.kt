package com.nktschmitt.mindhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


        // EditText
        val eT_email: EditText = findViewById(R.id.editText_email2)
        val eT_password: EditText = findViewById(R.id.editText_password2)


        // Button
        val button: Button = findViewById(R.id.btn_signin)


        // TextView
        val tV_signup: TextView = findViewById(R.id.textView_signup)


        // Firebase
        auth = FirebaseAuth.getInstance()


        if (auth != null) {
            val Intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }


        tV_signup.setOnClickListener {
            val signupintent: Intent = Intent(this, SignUpActivity::class.java)
            startActivity(signupintent)
        }


        button.setOnClickListener {


            if (eT_email.text.toString().isEmpty()) {
                eT_email.requestFocus()
                eT_email.error = "Please enter a Email"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(eT_email.text.toString()).matches()) {
                eT_email.requestFocus()
                eT_email.error = "Please enter a validate Email"
                return@setOnClickListener
            }

            if (eT_password.text.toString().isEmpty()) {
                eT_password.requestFocus()
                eT_password.error = "Please enter a Password"
                return@setOnClickListener
            }


            auth.signInWithEmailAndPassword(eT_email.text.toString(), eT_password.text.toString()).addOnCompleteListener {

                if (it.isSuccessful) {
                    val Intent: Intent = Intent(this, MainActivity::class.java)
                    startActivity(Intent)
                } else {
                    Toast.makeText(this, "Failed! Try again!", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
}