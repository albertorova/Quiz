package es.sublimestudio.kotlinquiz.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import es.sublimestudio.kotlinquiz.DataHolder
import es.sublimestudio.kotlinquiz.HomeActiviy
import es.sublimestudio.kotlinquiz.R
import es.sublimestudio.kotlinquiz.models.Game
import io.paperdb.Paper
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size

class ReviewFragment : Fragment() {

    val DBName = "KotlinQuiz"
    val USERNAME = "username"

    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.txtTitle)
        val emoji = view.findViewById<TextView>(R.id.txtEmoji)
        val btnPlay = view.findViewById<TextView>(R.id.btnPlayAgain)
        val btnScore = view.findViewById<TextView>(R.id.btnSeeScore)
        val btnChangeUser = view.findViewById<TextView>(R.id.btnChangeUser)
        val viewKonfetti = view.findViewById<KonfettiView>(R.id.viewKonfetti)

        val points = DataHolder.points
        sharedPref = requireActivity().getSharedPreferences(DBName, Context.MODE_PRIVATE)
        val nombre = sharedPref.getString(USERNAME, "")

        if (points > 20000) {

            title.text = "Lo has hecho muy bien!\nHas conseguido $points puntos"
            emoji.text = "\uD83D\uDE01\uD83D\uDE01\uD83D\uDE01"
            showKonfetti(viewKonfetti)
        } else {
            title.text = "Tienes que practicar mas!\nSolo has sacado $points puntos"
            emoji.text = "\uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D"

        }
        btnPlay.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, QuestionsFragment()).commit()
        }

        sharedPref = requireActivity().getSharedPreferences(DBName, Context.MODE_PRIVATE)

        btnChangeUser.setOnClickListener {

            sharedPref.edit().remove(USERNAME).apply()

            requireActivity().startActivity(Intent(context, HomeActiviy::class.java))
            requireActivity().finish()
        }

        val misPartidas = Paper.book().read<ArrayList<Game>>(DataHolder.KEY_GAMES, arrayListOf())
        misPartidas.add(Game(nombre ?: "", points))
        Paper.book().write(DataHolder.KEY_GAMES, misPartidas)

        Log.v("AAA","Datos $misPartidas")

        btnScore.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, ScoreFragment()).commit()
        }
    }

    fun showKonfetti(viewKonfetti: KonfettiView) {
        viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(Shape.Square, Shape.Circle)
            .addSizes(Size(12))
            .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
            .streamFor(300, 5000L)


    }

}