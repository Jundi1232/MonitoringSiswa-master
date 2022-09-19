package com.example.monitoringsiswa.ui.admin.mapel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class AddMapelActivity : AppCompatActivity() {
    private val viewModel:MapelViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mapel)
        supportActionBar?.title = getString(R.string.add_mapel)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {

                //TODO 12 : Create AddTaskViewModel and insert new task to database
                var uniqueID = UUID.randomUUID().toString()
                val tittle=findViewById<TextInputEditText?>(R.id.edt_mapel).text.toString().trim()
                val desription=findViewById<TextInputEditText?>(R.id.edt_kkm).text.toString().trim()

                val kkm:Float = desription.toFloat()
                val mapel=Mapel(uniqueID,tittle,kkm)
                viewModel.insertMapel(mapel)
                val intent = Intent(this,MapelMainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Berhasil Ditambah", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}