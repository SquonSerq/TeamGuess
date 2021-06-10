package com.example.teamguess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext

class GamePrepare : AppCompatActivity() {

    val recyclerGamePrepareAdapter = RecyclerAdapterr(Teams.getAllTeamsNames())

    object updateGameMain{
        var updateIt: (() -> Unit)? = null
        fun update(){
            updateIt?.invoke()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_prepare)

        Teams.prepareTeamsList()
        updateActivity()
        val gamePrepareRecyclerView = findViewById<RecyclerView>(R.id.gamePrepareRecycler)
        gamePrepareRecyclerView.setHasFixedSize(true)
        gamePrepareRecyclerView.adapter = recyclerGamePrepareAdapter
        gamePrepareRecyclerView.layoutManager = LinearLayoutManager(this)
        updateGameMain.updateIt = { ->
            updateActivity()
        }
    }

    fun startTurn(view: View){
        startActivity(Intent(this, GameMain::class.java))
    }

    fun updateActivity(){
        findViewById<TextView>(R.id.nextTeamNameTextView).text = Teams.teamsList[GameCycle.currentTurn].name
        recyclerGamePrepareAdapter.texts = Teams.getAllTeamsNames()
        recyclerGamePrepareAdapter.notifyDataSetChanged()
    }
}