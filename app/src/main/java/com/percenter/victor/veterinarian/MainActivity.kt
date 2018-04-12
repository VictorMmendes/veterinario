package com.percenter.victor.veterinarian

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.main_window.*

class MainActivity : AppCompatActivity(), TextWatcher, Activity1Manager
{
    lateinit var db:DbConnection

    override fun afterTextChanged(p0: Editable?){}
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int)
    {
        searchAnimais()
    }

    override fun loadAll()
    {
        var animaisList = read()
        updateWindow(animaisList)
    }

    fun updateWindow(animais: List<Animal>)
    {
        val adapter = ListAdapter(animais, this)
        listAnimais.adapter = adapter
        val layout = StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL)
        listAnimais.layoutManager = layout
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_window)

        db = Room.databaseBuilder(
                applicationContext,
                DbConnection::class.java,
                "animal_models"
        ).allowMainThreadQueries().build()

        addBt.setOnClickListener({})
        searchBt.setOnClickListener({})

        loadAll()

        searchTf.addTextChangedListener(this)
        addBt.setOnClickListener({ loadInsertView() })
    }

    override fun onBackPressed()
    {
        moveTaskToBack(true)
    }

    private fun loadInsertView()
    {
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("cod", 2)
        startActivity(intent)
    }

    override fun onResume()
    {
        super.onResume()
        loadAll()
    }

    private fun searchAnimais()
    {
        val searchString = "%${searchTf.text.toString()}%"
        val animaisList = db.animalDAO().search(searchString)
        updateWindow(animaisList)
    }

    private fun read(): List<Animal>
    {
        lateinit var animais: List<Animal>
        animais = db.animalDAO().animais()
        return animais
    }
}
