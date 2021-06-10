package com.example.teamguess

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameMain : AppCompatActivity() {

    val rclGameMainAdapter = GameCycleRecyclerAdapter(GameCycle.getGuessListNames())
    var timerEnd = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_main)

        val rclGameMain = findViewById<RecyclerView>(R.id.gameMainRcl)
        rclGameMain.setHasFixedSize(true)
        rclGameMain.adapter = rclGameMainAdapter
        rclGameMain.layoutManager = LinearLayoutManager(this)

        val chronoView = findViewById<Chronometer>(R.id.MainGameChrono)
        val skipButton = findViewById<Button>(R.id.MainGameSkip)

        skipButton.visibility = View.INVISIBLE
        chronoView.visibility = View.INVISIBLE

        chronoView.isCountDown = true

        chronoView.setOnChronometerTickListener {
            if( chronoView.base <= SystemClock.elapsedRealtime()){
                chronoView.stop()
                timerEnd = true
            }
        }
    }

    fun updateListIngame(){
        rclGameMainAdapter.names = GameCycle.getGuessListNames()
        rclGameMainAdapter.notifyDataSetChanged()
        if (timerEnd == true){
            GameCycle.updateCurrentTurn()
            GamePrepare.updateGameMain.update()
            GameCycle.resetNamesAfterTurn()
            finish()
        }
    }

    fun updateListByButton(v: View){
        getNamesOutOfPoolInTurn(rclGameMainAdapter.names)
        updateListIngame()
    }

    fun getNamesOutOfPoolInTurn(names: List<String>){
        GameCycle.deleteNamesInTurn(names)
    }

    fun startGame(view: View){
        val chronoView = findViewById<Chronometer>(R.id.MainGameChrono)
        val skipButton = findViewById<Button>(R.id.MainGameSkip)
        val updateButton = findViewById<Button>(R.id.MainGameUpdate)
        val startButton = findViewById<Button>(R.id.MainGameStart)
        skipButton.visibility = View.VISIBLE
        chronoView.visibility = View.VISIBLE
        startButton.visibility = View.GONE
        updateButton.visibility = View.GONE

        rclGameMainAdapter.itemClickListener = {position, name ->
            Teams.teamsList[GameCycle.currentTurn].rightAnswers.add(name)
            GameCycle.deleteName(name)

            var deleteNames = rclGameMainAdapter.names.toMutableList()
            deleteNames.remove(name)
            getNamesOutOfPoolInTurn(deleteNames.toList())

            updateListIngame()
        }

        chronoView.base = SystemClock.elapsedRealtime() + 60000
        chronoView.start()
    }
}