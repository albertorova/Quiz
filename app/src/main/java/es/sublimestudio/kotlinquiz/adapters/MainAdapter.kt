package es.sublimestudio.kotlinquiz.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.sublimestudio.kotlinquiz.R
import es.sublimestudio.kotlinquiz.models.Game

class MainAdapter(private val mDataSet: List<Game>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet[position]
        data.let {
            holder.bindItems(it)
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size ?: 0
    }

    inner class MainViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var v1 = v.findViewById(R.id.txtnames) as TextView
        var v2 = v.findViewById(R.id.txtpoints) as TextView


        fun bindItems(data: Game) {
            v1.text = data.name
            v2.text = data.points.toString()
        }
    }
}