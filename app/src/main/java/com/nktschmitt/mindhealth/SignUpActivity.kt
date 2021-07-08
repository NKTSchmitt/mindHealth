package com.nktschmitt.mindhealth

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        var checkmate: Boolean = false

        // EditText
        val eT_name: EditText = findViewById(R.id.editText_name)
        val eT_email: EditText = findViewById(R.id.editText_email)
        val eT_password: EditText = findViewById(R.id.editText_password)


        // TextView
        val tV_signin: TextView = findViewById(R.id.textView_signin)


        // Firebase
        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        val button: Button = findViewById(R.id.btn_createaccount)


        tV_signin.setOnClickListener {

            val signinintent: Intent = Intent(this, SignInActivity::class.java)
            startActivity(signinintent)
        }


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


            auth.createUserWithEmailAndPassword(
                eT_email.text.toString(),
                eT_password.text.toString()
            ).addOnCompleteListener {
            }

            val Data = Data(eT_name.text.toString(), eT_email.text.toString())
            databaseReference.child(auth.currentUser?.uid.toString()).setValue(Data).addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(this, "Account successfully created!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed! Try again", Toast.LENGTH_SHORT).show()
                }

            }

            val mainintent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainintent)


        }


    }


}