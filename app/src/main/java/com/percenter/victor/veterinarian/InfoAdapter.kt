package com.percenter.victor.veterinarian

/**
 * Created by victor on 09/04/18.
 */
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.info_window.view.*

class InfoAdapter(val animal: Animal, changer: Activity2Manager) :
        RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    val windowManager = changer

    override fun onCreateViewHolder(
            parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.info_window,
                        parent, false)
        return ViewHolder(view, windowManager)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(
            holder: ViewHolder?, position: Int) {
        holder?.preencherView(animal)
    }

    class ViewHolder(view: View, changer: Activity2Manager) :
            RecyclerView.ViewHolder(view) {

        val changer = changer

        fun preencherView(animal: Animal)
        {
            itemView.nomeTf.text = animal.nome
            itemView.racaTf.text = animal.raca
            itemView.especieTf.text = animal.especie
            itemView.nascimentoTf.text = animal.nascimento
            itemView.pesoTf.text = animal.peso.toString()
            itemView.porteTf.text = animal.porte.toString()
            itemView.editBt.setOnClickListener({ loadEditView() })
        }

        private fun loadEditView()
        {
            changer.editWindow()
        }
    }
}
