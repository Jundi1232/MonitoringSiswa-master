package com.example.monitoringsiswa.ui.admin.guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.dicoding.todoapp.utils.DatePickerFragment
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.guru.Guru
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.mapel.Mapel
import com.example.monitoringsiswa.databinding.ActivityAddGuruBinding
import com.example.monitoringsiswa.ui.admin.mapel.MapelMainActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddGuruActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener  {
    private var dueDateMillis: Long = System.currentTimeMillis()
    private lateinit var binding: ActivityAddGuruBinding
    private var jk:String?=null
    private val viewModel: GuruViewModel by viewModels {
        ViewModelFactoryguru.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddGuruBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.show()
        supportActionBar?.title = getString(R.string.add_guru)

        viewModel.getAllmapel().observe(this){
            showMapel(it)
        }

    }

    private fun showMapel(data: List<Mapel>?) {
//        val listMapel = ArrayList(data).toArray()
        val spinner: Spinner = findViewById(R.id.spinner)
        var coba= arrayListOf<String>()

        for (item in 0..data!!.size-1){
          coba= arrayListOf(data[item].namemapel.toString())
            Log.d("coba","$coba")
        }
       ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            coba,
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner

            spinner.adapter = adapter
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
                        jk=R.id.radio_pirates.toString()
                    }
                R.id.radio_ninjas ->
                    if (checked) {
                        // Ninjas rule
                        jk=R.id.radio_ninjas.toString()
                    }
            }
        }
    }
    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.add_tv_due_date).text = dateFormat.format(calendar.time)
        dueDateMillis = calendar.timeInMillis
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {

                binding.apply {
                    val nip=tvNip.text.toString()
                    val nama=tvNama.text.toString()
                    val mapel=tvMapel.text.toString()
                    val alamat=tvAlamat.text.toString()
                    val tempat=tvTempat.text.toString()
                    val notelp=tvNotelp.text.toString()
                    var level=""
                    if (mapel=="Walas"){
                        level="Walas"
                    }
                    else{
                        level="Guru"
                    }
                    val login=Login(nip,nip,level)
                    val guru=Guru(nip,nama,mapel,jk.toString(),tempat,dueDateMillis,alamat,notelp)
                    viewModel.insertGuru(guru)
                    viewModel.insertLogin(login)
                    Log.d("Database","$login")
                }
                val intent = Intent(this, GuruMainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Berhasil Ditambah", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}