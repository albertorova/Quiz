package es.sublimestudio.kotlinquiz.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import es.sublimestudio.kotlinquiz.DataHolder
import es.sublimestudio.kotlinquiz.R
import es.sublimestudio.kotlinquiz.adapters.MainAdapter
import es.sublimestudio.kotlinquiz.models.Game
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment() {

    var q: ArrayList<Game> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val misPartidas = Paper.book().read<ArrayList<Game>>(DataHolder.KEY_GAMES, arrayListOf())

        val mAdapter = MainAdapter(misPartidas)
        mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView.adapter = mAdapter

    }



}