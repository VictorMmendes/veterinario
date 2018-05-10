package com.percenter.victor.veterinarian

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.SearchView
import android.widget.Toast
import com.percenter.victor.veterinarian.remote.services.DAO.AnimalDaoService
import com.percenter.victor.veterinarian.remote.services.services.AnimalRemotoListener
import kotlinx.android.synthetic.main.main_window.*
import java.util.*

class MainActivity : AppCompatActivity(), Activity1Manager, AnimalRemotoListener, SearchView.OnQueryTextListener
{
    override fun onGetAnimaisReturn(animais: List<Animal>)
    {
        updateWindow(animais)
    }

    override fun onAnimalError(mensagem: String)
    {
       Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }

    var dao = AnimalDaoService(this)

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
        read()
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
        dao.searchAnimal(searchString)

        return false
    }

    private fun read()
    {
        dao.buscarTodas()
    }
}
