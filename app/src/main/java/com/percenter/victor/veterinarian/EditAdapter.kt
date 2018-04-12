package com.percenter.victor.veterinarian

/**
 * Created by victor on 09/04/18.
 */
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.edit_window.view.*

class EditAdapter(val animal: Animal, changer: Activity2Manager) :
        RecyclerView.Adapter<EditAdapter.ViewHolder>() {

    val windowManager = changer

    override fun onCreateViewHolder(
            parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.edit_window,
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

        val vw = view
        lateinit var db:DbConnection
        val changer = changer

        fun preencherView(animal: Animal)
        {
            val portes = listOf<Char>('P', 'M', 'G')

            itemView.nomeEdit.setText(animal.nome)
            itemView.racaEdit.setText(animal.raca)
            itemView.especieEdit.setText(animal.especie)
            itemView.nascimentoEdit.setText(animal.nascimento)
            itemView.pesoEdit.setText(animal.peso.toString())
            itemView.porteEdit.setSelection(portes.indexOf(animal.porte))
            itemView.cancelarBt.setOnClickListener({ loadInfoView() })
            itemView.SalvarBt.setOnClickListener({ updateAnimal(animal) })
        }

        private fun loadInfoView()
        {
            changer.infoWindow()
        }

        private fun updateAnimal(animal: Animal)
        {
            db = Room.databaseBuilder(
                    vw.context,
                    DbConnection::class.java,
                    "animal_models"
            ).allowMainThreadQueries().build()

            animal.nome = vw.nomeEdit.text.toString()
            animal.especie = vw.especieEdit.text.toString()
            animal.raca = vw.racaEdit.text.toString()
            animal.nascimento = vw.nascimentoEdit.text.toString()
            animal.peso = vw.pesoEdit.text.toString().toDouble()
            animal.porte = vw.porteEdit.selectedItem.toString().toCharArray().get(0)

            db.animalDAO().updateAnimal(animal)

            loadInfoView()
        }
    }
}
