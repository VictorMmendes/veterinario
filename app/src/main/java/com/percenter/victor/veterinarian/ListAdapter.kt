package com.percenter.victor.veterinarian

/**
 * Created by victor on 09/04/18.
 */
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.percenter.victor.veterinarian.remote.services.DAO.AnimalDaoService
import com.percenter.victor.veterinarian.remote.services.services.AnimalRemotoListener
import kotlinx.android.synthetic.main.animal_item.view.*

class ListAdapter(val animais: List<Animal>, changer: Activity1Manager) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val windowManager = changer


    override fun onCreateViewHolder(
            parent: ViewGroup?, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.animal_item,
                        parent, false)
        return ViewHolder(view, windowManager)
    }

    override fun getItemCount() = animais.size

    override fun onBindViewHolder(
            holder: ViewHolder?, position: Int) {
        val animal = animais[position]
        holder?.let {
            it.preencherView(animal)
        }
    }

    class ViewHolder(view: View, changer: Activity1Manager) :
            RecyclerView.ViewHolder(view), AnimalRemotoListener {

        override fun onGetAnimaisReturn(animais: List<Animal>)
        {

        }

        override fun onAnimalError(mensagem: String)
        {

        }

        val vw = view
        val changer = changer
        var dao = AnimalDaoService(this)

        fun preencherView(animal: Animal)
        {
            itemView.txtAnimalNome.text = animal.nome
            itemView.txtAnimalEspecie.text = "Espécie: ${animal.especie}"
            itemView.setOnClickListener({ loadInfo(animal) })
            itemView.deleteBt.setOnClickListener({ delAnimal(animal) })
        }

        fun loadInfo(animal: Animal)
        {
            val intent = Intent(vw.context, Main2Activity::class.java)
            intent.putExtra("id", animal.id)
            vw.context.startActivity(intent)
        }

        fun delAnimal(animal: Animal)
        {
            dao.delete(animal)

            changer.loadAll()
        }
    }
}
