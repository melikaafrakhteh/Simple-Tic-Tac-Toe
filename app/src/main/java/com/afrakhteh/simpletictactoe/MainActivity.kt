package com.afrakhteh.simpletictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstTime()
    }


    var player1Count = 0
    var player2Count = 0
    var gameLevel = 0
    private fun firstTime() {
        tv_gamelevel.text = getString(R.string.Level, gameLevel)
        tv_yourscore.text = getString(R.string.player1score, player1Count)
        tv_devicescore.text = getString(R.string.devicescore, player2Count)
        tv_reset.setOnClickListener { resetApp() }
    }

    fun clicking(view: View) {
        val btnSelected = view as Button
        var btnId = 0
        when (btnSelected.id) {
            R.id.button1 -> btnId = 1
            R.id.button2 -> btnId = 2
            R.id.button3 -> btnId = 3
            R.id.button4 -> btnId = 4
            R.id.button5 -> btnId = 5
            R.id.button6 -> btnId = 6
            R.id.button7 -> btnId = 7
            R.id.button8 -> btnId = 8
            R.id.button9 -> btnId = 9
        }
        playingGame(btnId, btnSelected)

    }

    //player turn( who is playing)
    var playerTurn = 1
    //You
    var player1 = ArrayList<Int>()
    //device
    var player2 = ArrayList<Int>()

    fun playingGame(btnId: Int, btnSelected: Button) {
        if (playerTurn == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundColor(getColor(R.color.player1))
            player1.add(btnId)
            playerTurn = 2
            playerAuto()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundColor(getColor(R.color.player2))
            player2.add(btnId)
            playerTurn = 1
        }
        btnSelected.isEnabled = false
        showTheWinner()

    }

    private fun playerAuto() {
        //playing with your mobile
        var mobileTurn = ArrayList<Int>()

        for (btnId in 1..9) {
            if (!(player1.contains(btnId) || player2.contains(btnId))) {
                mobileTurn.add(btnId)
            }
        }

        //prevent to crash
        if (mobileTurn.size == 0) {
            resetApp()
        }
        var random = Random()
        var randomIndex = random.nextInt(mobileTurn.size)
        val btnId = mobileTurn[randomIndex]


        var selected: Button?
        selected = when (btnId) {
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> button1
        }
        playingGame(btnId, selected)
    }


    private fun showTheWinner() {
        var winner = -1
        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        //2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner == 1) {
            player1Count++
            tv_yourscore.text = getString(R.string.player1score, player1Count)
            gameLevel++
            tv_gamelevel.text = getString(R.string.Level, gameLevel)
            resetApp()

        } else if (winner == 2) {
            player2Count++
            tv_devicescore.text = getString(R.string.devicescore, player2Count)
            gameLevel++
            tv_gamelevel.text = getString(R.string.Level, gameLevel)
            resetApp()

        }
    }

    private fun resetApp() {
        playerTurn = 1
        player1.clear()
        player2.clear()

        for (btnId in 1..9) {
            var selected: Button?
            selected = when (btnId) {
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> button1
            }
            selected.text = ""
            selected.setBackgroundColor(Color.LTGRAY)
            selected.isEnabled = true
        }

    }


}
