package com.percenter.victor.veterinarian

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.percenter.victor.veterinarian.remote.services.DAO.AnimalDaoService
import com.percenter.victor.veterinarian.remote.services.services.AnimalRemotoListener
import kotlinx.android.synthetic.main.second_window.*

class Main2Activity : AppCompatActivity(), Activity2Manager, AnimalRemotoListener {

    override fun onGetAnimaisReturn(animais: List<Animal>)
    {
        animal = animais[0]
        if(tipo == 1)
        {
            val adapter = InfoAdapter(animal, this)
            secondViewContent.adapter = adapter
            val layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            secondViewContent.layoutManager = layout
        } else {
            val adapter = InfoAdapter(animal, this)
            secondViewContent.adapter = adapter
            val layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            secondViewContent.layoutManager = layout
        }
    }

    override fun onAnimalError(mensagem: String)
    {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    var dao = AnimalDaoService(this)
    var idAnimal:Int = 0
    lateinit var animal:Animal
    var tipo: Int = 0

    override fun editWindow()
    {
        tipo = 1
        dao.getAnimal(idAnimal)
    }

    override fun infoWindow()
    {
        tipo = 2
        dao.getAnimal(idAnimal)
    }

    override fun insertWindow()
    {
        val adapter = InsertAdapter()
        secondViewContent.adapter = adapter
        val layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        secondViewContent.layoutManager = layout
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_window)

        if(intent.extras.get("cod") == null)
        {
            val extras = intent.extras
            idAnimal = extras.get("id").toString().toInt()
            dao.getAnimal(extras.get("id").toString().toInt())
            infoWindow()
        } else {
            insertWindow()
        }
    }
}
