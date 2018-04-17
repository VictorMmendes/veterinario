package com.percenter.victor.veterinarian

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.main_window.*
import java.util.*

class MainActivity : AppCompatActivity(), Activity1Manager, SearchView.OnQueryTextListener {

    lateinit var db:DbConnection

    override fun onQueryTextSubmit(p0: String?): Boolean
    {
        return searchAnimais()
    }

    override fun onQueryTextChange(p0: String?): Boolean
    {
        return searchAnimais()
    }

    override fun loadAll()
    {
        var animaisList = read()
        updateWindow(animaisList)
    }

    fun updateWindow(animais: List<Animal>)
    {
        val search_hint = resources.getStringArray(R.array.search_hint)
        var i = arrayListOf<Int>(0,1,2,3,4,5)
        i.shuffle(Random())
        searchTf.queryHint = search_hint[i[0]]

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

        loadAll()

        searchTf.setOnQueryTextListener(this)
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

    private fun searchAnimais(): Boolean
    {
        val searchString = "%${searchTf.query}%"
        val animaisList = db.animalDAO().search(searchString)
        updateWindow(animaisList)

        return animaisList.isEmpty()
    }

    private fun read(): List<Animal>
    {
        lateinit var animais: List<Animal>
        animais = db.animalDAO().animais()
        return animais
    }
}
