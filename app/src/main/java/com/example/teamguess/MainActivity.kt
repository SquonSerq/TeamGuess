package com.example.teamguess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val rclMainMenuAdapter = GameCycleRecyclerAdapter(Teams.getPeopleNames())

    object UpdateMainMenuRcl{
        var updateMainMenuRcl: (() -> Unit)? = null
        var endGameInv: (() -> Unit)? = null
        fun update(){
            updateMainMenuRcl?.invoke()
        }
        fun endGame(){
            endGameInv?.invoke()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rclMainMenu = findViewById<RecyclerView>(R.id.peopleRecycleView)
        rclMainMenu.setHasFixedSize(true)
        rclMainMenu.adapter = rclMainMenuAdapter
        rclMainMenu.layoutManager = LinearLayoutManager(this)

        rclMainMenuAdapter.itemClickListener = { _, name ->
            Teams.names.remove(name)
            updateRcl()
        }

        UpdateMainMenuRcl.updateMainMenuRcl = {
            updateRcl()
        }

        UpdateMainMenuRcl.endGameInv = {
            startActivity(Intent(parent.applicationContext, NameTeam::class.java))
        }

        findViewById<Button>(R.id.startGameBtn).visibility = View.INVISIBLE
    }

    fun startNextScreen(view: View){
        startActivity(Intent(this, NameTeam::class.java))
    }

    fun startGame(view: View){
        startActivity(Intent(this, GamePrepare::class.java))
    }

    fun updateRcl(){
        rclMainMenuAdapter.names = Teams.getPeopleNames()
        rclMainMenuAdapter.notifyDataSetChanged()

        if(Teams.names.size >= 4 && Teams.names.size % 2 == 0){
            findViewById<Button>(R.id.startGameBtn).visibility = View.VISIBLE
        }
        else{
            findViewById<Button>(R.id.startGameBtn).visibility = View.INVISIBLE
        }
    }

}