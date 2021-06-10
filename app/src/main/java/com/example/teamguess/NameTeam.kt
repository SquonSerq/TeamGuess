package com.example.teamguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class NameTeam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_team)
    }

    fun endAct(view: View){
        var personName = findViewById<EditText>(R.id.teamNameInput).text.toString()
        Teams.names.add(personName)
        MainActivity.UpdateMainMenuRcl.update()
        finish()
    }
}