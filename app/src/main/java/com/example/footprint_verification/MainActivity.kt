package com.example.footprint_verification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.footprint.android.FootprintAndroid
import com.footprint.android.FootprintAppearance
import com.footprint.android.FootprintAppearanceRules
import com.footprint.android.FootprintAppearanceTheme
import com.footprint.android.FootprintAppearanceVariables
import com.footprint.android.FootprintConfiguration
import com.footprint.android.FootprintL10n
import com.footprint.android.FootprintOptions
import com.footprint.android.FootprintSupportedLocale
import com.footprint.android.FootprintUserData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}