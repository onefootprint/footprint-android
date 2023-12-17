package com.example.foortpint_verification

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.footprint.verify.Footprint
import com.footprint.verify.FootprintUserData

class MainActivity : AppCompatActivity() {
    private lateinit var verificationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userData = FootprintUserData(email = "ahsan@email.com", phoneNumber = ("+1 (555) 555-0100"))

        verificationButton = findViewById(R.id.verify_button)
        verificationButton.setOnClickListener {
            val footprint = Footprint(publicKey = "pb_test_aSzwnZecnXS4faoyhxrocW", scheme = "com.test.verify", host = "kyc", userData = userData);
            footprint.startVerification(this@MainActivity);
        }

        val applinkIntent = getIntent();
        val action: String? = applinkIntent?.action
        val data: Uri? = applinkIntent?.data
        Log.d("TAG", "The data url with token is: "+data.toString())
    }
}