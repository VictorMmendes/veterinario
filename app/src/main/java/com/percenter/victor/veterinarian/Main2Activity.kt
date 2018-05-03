package com.percenter.victor.veterinarian

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.percenter.victor.veterinarian.remote.services.DAO.AnimalDaoService
import com.percenter.victor.veterinarian.remote.services.services.AnimalRemotoListener
import kotlinx.android.synthetic.main.second_window.*

class Main2Activity : AppCompatActivity(), Activity2Manager, AnimalRemotoListener {

    override fun onGetAnimaisReturn(animais: List<Animal>)
    {

    }

    override fun onAnimalError(mensagem: String)
    {

    }

    var dao = AnimalDaoService(this)
    lateinit var animal:Animal

    override fun editWindow()
    {
        animal = db.animalDAO().findById(animal.id!!.toInt())
        val adapter = EditAdapter(animal, this)
        secondViewContent.adapter = adapter
        val layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        secondViewContent.layoutManager = layout
    }

    override fun infoWindow()
    {
        animal = db.animalDAO().findById(animal.id!!.toInt())
        val adapter = InfoAdapter(animal, this)
        secondViewContent.adapter = adapter
        val layout = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        secondViewContent.layoutManager = layout
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
            animal = db.animalDAO().findById(extras.get("id").toString().toInt())
            infoWindow()
        } else {
            insertWindow()
        }
    }
}
