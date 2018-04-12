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
import kotlinx.android.synthetic.main.insert_window.view.*

class InsertAdapter() :
        RecyclerView.Adapter<InsertAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.insert_window,
                        parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(
            holder: ViewHolder?, position: Int) {
        holder?.createListener()
    }

    class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {

        val vw = view
        lateinit var db:DbConnection

        fun createListener()
        {
            itemView.voltarBt.setOnClickListener({ loadMainWindow() })
            itemView.CadastrarBt.setOnClickListener({ addAnimal() })
        }

        private fun loadMainWindow()
        {
            val intent = Intent(vw.context, MainActivity::class.java)
            vw.context.startActivity(intent)
        }

        private fun addAnimal()
        {
            db = Room.databaseBuilder(
                    vw.context,
                    DbConnection::class.java,
                    "animal_models"
            ).allowMainThreadQueries().build()

            val animal = Animal(
                        vw.nomeInsert.text.toString(),
                        vw.especieInsert.text.toString(),
                        vw.racaInsert.text.toString(),
                        vw.pesoInsert.text.toString().toDouble(),
                        vw.nascimentoInsert.text.toString(),
                        vw.porteInsert.selectedItem.toString().toCharArray().get(0))
            db.animalDAO().addAnimal(animal)

            loadMainWindow()
        }
    }
}
