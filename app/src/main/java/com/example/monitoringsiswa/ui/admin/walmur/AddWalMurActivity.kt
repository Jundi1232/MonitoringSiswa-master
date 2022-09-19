package com.example.monitoringsiswa.ui.admin.walmur

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.walmur.WaliMurid
import com.example.monitoringsiswa.databinding.ActivityAddWalMurBinding

class AddWalMurActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityAddWalMurBinding
    private val viewModel: WalMurViewModel by viewModels {
        ViewModelFactoryWalmur.getInstance(this)
    }
    private var jk:String?=null
    private var status:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddWalMurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = getString(R.string.add_walmur)

        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.absen_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }
    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_pirates ->
                    if (checked) {
                        // Pirates are the best
                        jk="Laki-Laki"
                    }
                R.id.radio_ninjas ->
                    if (checked) {
                        // Ninjas rule
                        jk="Perempuan"
                    }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                binding.apply {
                    val nik=tvNik.text.toString()
                    val nama=tvNama.text.toString()
                    val nis=tvNis.text.toString()
                    val notelp=tvNotelp.text.toString()
                    if (nik.isEmpty()&& nama.isEmpty() && nis.isEmpty()){
                        Log.d("coba", "gagal")
                    }
                    else {
                        val waliMurid = WaliMurid(nik, nama, jk.toString(), status, nis, notelp)
                        viewModel.insertWalmur(waliMurid)
                        Log.d("coba", "$waliMurid")
                    }
                }
                val intent = Intent(this, ListMainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Berhasil Ditambah", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        status = parent?.getItemAtPosition(position).toString()
    }
}