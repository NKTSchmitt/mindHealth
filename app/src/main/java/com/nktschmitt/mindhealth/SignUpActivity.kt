package com.nktschmitt.mindhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // EditText
        val eT_name: EditText = findViewById(R.id.editText_name)
        val eT_email: EditText = findViewById(R.id.editText_email)
        val eT_password: EditText = findViewById(R.id.editText_password)

        // Firebase
        auth = FirebaseAuth.getInstance()



        val button: Button = findViewById(R.id.btn_createaccount)

        button.setOnClickListener {

            if (eT_name.text.toString().isEmpty()) {
                eT_name.requestFocus()
                eT_name.error = "Please enter a name"
                return@setOnClickListener
            }

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

            if (eT_password.text.toString().length < 6 || eT_password.text.toString().length == 6) {
                eT_password.requestFocus()
                eT_password.error = "Your password must have more then 6 chars"
                return@setOnClickListener
            }


            auth.createUserWithEmailAndPassword(eT_email.text.toString(), eT_password.text.toString()).addOnCompleteListener {

            }


        }

    }
}