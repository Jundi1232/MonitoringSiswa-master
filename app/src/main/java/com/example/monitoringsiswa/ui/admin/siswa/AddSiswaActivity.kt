package com.example.monitoringsiswa.ui.admin.siswa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.dicoding.todoapp.utils.DatePickerFragment
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.login.Login
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.data.remote.entity.utils.ViewModelFactorySiswa
import com.example.monitoringsiswa.databinding.ActivityAddSiswaBinding
import java.text.SimpleDateFormat
import java.util.*

class AddSiswaActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener,  AdapterView.OnItemSelectedListener  {
    private var dueDateMillis: Long = System.currentTimeMillis()
    private lateinit var binding: ActivityAddSiswaBinding

    private val viewModel: SiswaViewModel by viewModels {
        ViewModelFactorySiswa.getInstance(this)
    }
    private var kelas:String?=null
    private var jenis:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = findViewById(R.id.spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.kelas_array,
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
            val radio: RadioButton
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_pirates ->
                    {
                        radio= findViewById(R.id.radio_pirates)
                        jenis=radio.text.toString()
                    }
                R.id.radio_ninjas ->
                     {
                         radio= findViewById(R.id.radio_ninjas)
                         // Pirates are the best
                         jenis=radio.text.toString()
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
                    val nis=tvNip.text.toString()
                    val nama=tvNama.text.toString()
                    val alamat=tvAlamat.text.toString()
                    val tempat=tvTempat.text.toString()
                    val tgl=addTvDueDate.text.toString()
                    if(nis.isEmpty() || nama.isEmpty() || alamat.isEmpty() || tempat.isEmpty() || jenis==null || tgl.isEmpty() ){
                        Toast.makeText(this@AddSiswaActivity,"Field Tidak Boleh Kosong",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val siswa = Siswa(
                            nis,
                            nama,
                            jenis!!,
                            tempat,
                            dueDateMillis,
                            kelas.toString(),
                            alamat
                        )
                        val login = Login(nis, nis, "Siswa")
                        viewModel.insertSiswa(siswa)
                        viewModel.insertLogin(login)
                        val intent = Intent(this@AddSiswaActivity, ListSiswactivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this@AddSiswaActivity,"Berhasil Ditambah", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        kelas = parent?.getItemAtPosition(position).toString()
    }

}