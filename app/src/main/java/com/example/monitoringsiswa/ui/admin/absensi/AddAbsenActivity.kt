package com.example.monitoringsiswa.ui.admin.absensi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.todoapp.utils.DateConverter
import com.example.monitoringsiswa.Constanta.EXTRA_TGL
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.data.remote.entity.siswa.Siswa
import com.example.monitoringsiswa.databinding.ActivityAddAbsenBinding
import java.util.*

class AddAbsenActivity : AppCompatActivity(),  AdapterView.OnItemSelectedListener {
    private lateinit var recycler: RecyclerView
    private lateinit var addAbsenAdapter: AddAbsenAdapter
    private lateinit var binding:ActivityAddAbsenBinding
    private val viewModel: AbsenViewModel by viewModels {
        ViewModelFactoryAbsen.getInstance(this)
    }
    private var kelas:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddAbsenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addAbsenAdapter= AddAbsenAdapter()
        val spinner: Spinner = findViewById(R.id.spinner)
        val tgl=intent.getLongExtra(EXTRA_TGL,0)
// Create an ArrayAdapter using the string array and a default spinner layout
        binding.tvTgl.text=tgl.let { DateConverter.convertMillisToString(it) }

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
        binding.rvProduct.apply {
            adapter=addAbsenAdapter
            setHasFixedSize(true)
            layoutManager=
                LinearLayoutManager(this@AddAbsenActivity, LinearLayoutManager.VERTICAL,false)
        }
        binding.fab.setOnClickListener {

        }
        addAbsenAdapter.setSaveItemClickCallback(object :AddAbsenAdapter.OnItemClickCallback{
            override fun onItemClicked(radioButton: RadioButton,ket: String ,data: Siswa) {
                if (radioButton.isChecked){
                    val uniqueID = UUID.randomUUID().toString()
                    val date=binding.tvTgl.text.toString()
                    val absen=Absen(uniqueID,data.nis,date,ket,"Sejarah",kelas!!)
                    viewModel.insertAbsen(absen)
                    Log.d("Coba","noactive $absen")
                }else{
                Log.d("Coba","noactive $ket")}
            }
        })

    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        kelas = parent?.getItemAtPosition(position).toString()
        viewModel.getSiswabyKelas(kelas!!).observe(this){it1->
            if (it1.isEmpty() ){
                binding.rvProduct.visibility=View.GONE
                binding.tvNodata.visibility=View.VISIBLE
            }
            else  {
                addAbsenAdapter.setData(it1)
                binding.rvProduct.visibility=View.VISIBLE
                binding.tvNodata.visibility=View.GONE
            }
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
                        Log.d("Coba","Berhasil")
                    }
                R.id.radio_ninjas ->
                    if (checked) {
                        // Ninjas rule
                        Log.d("Coba","Berhasil")
                    }
            }
        }
    }

}