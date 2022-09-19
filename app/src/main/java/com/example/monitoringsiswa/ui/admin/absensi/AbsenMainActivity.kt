package com.example.monitoringsiswa.ui.admin.absensi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.todoapp.utils.DatePickerFragment
import com.example.monitoringsiswa.Constanta.EXTRA_LEVEL
import com.example.monitoringsiswa.Constanta.EXTRA_TGL
import com.example.monitoringsiswa.R
import com.example.monitoringsiswa.data.remote.entity.absensi.Absen
import com.example.monitoringsiswa.databinding.ActivityAbsenMainBinding
import com.example.monitoringsiswa.ui.admin.siswa.SiswaAdapter
import java.text.SimpleDateFormat
import java.util.*

class AbsenMainActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener {
    private var dueDateMillis: Long = System.currentTimeMillis()
    private lateinit var binding: ActivityAbsenMainBinding
    private val viewModel: AbsenViewModel by viewModels {
        ViewModelFactoryAbsen.getInstance(this)
    }
    private lateinit var absenAdapter: AbsensiAdapter
    private lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        absenAdapter = AbsensiAdapter()
        val level = intent.getStringExtra(EXTRA_LEVEL)
        when (level) {
            "Walas" -> {

            }
            "Guru" -> {
                val tgl=binding.fabTgl.text.toString()
                viewModel.getAbsenBytgl(tgl).observe(this) {
                    if (it.isEmpty()){
                        Log.d("Coba","$it $dueDateMillis")
                    }else {
                        absenAdapter.setData(it)
                        Log.d("Coba", "$it $dueDateMillis")
                    }
                }
                absenAdapter.setDeleteItemClickCallback(object : AbsensiAdapter.OnItemClickCallback{
                    override fun onItemClicked(data: Absen) {
                        viewModel.delete(data)
                    }
                })
                recycler= binding.rvProduct
                initAction()
            }
            "Siswa" -> {
                binding.apply {
                    fab.visibility = View.INVISIBLE
                }
            }
            else -> {
            }
        }
        binding.rvProduct.apply {
            adapter = absenAdapter
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@AbsenMainActivity, LinearLayoutManager.VERTICAL, false)
        }
        binding.fab.setOnClickListener {
            val intent = Intent(this, AddAbsenActivity::class.java)
            intent.putExtra(EXTRA_LEVEL, level)
            intent.putExtra(EXTRA_TGL, dueDateMillis)
            startActivity(intent)
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
        findViewById<TextView>(R.id.fab_tgl).text = dateFormat.format(calendar.time)
        dueDateMillis = calendar.timeInMillis

    }

    private fun initAction() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = (viewHolder as AbsensiAdapter.AbsenViewHolder).getabsen
                viewModel.delete(task)
            }

        })
        itemTouchHelper.attachToRecyclerView(recycler)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
//                val settingIntent = Intent(this, SettingsActivity::class.java)
//                startActivity(settingIntent)
                true
            }
            R.id.action_filter -> {
                showFilteringPopUpMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showFilteringPopUpMenu() {
        val view = findViewById<View>(R.id.action_filter) ?: return
        PopupMenu(this, view).run {
            menuInflater.inflate(R.menu.filter_tasks, menu)

            setOnMenuItemClickListener {

                    when (it.itemId) {
                        R.id.active -> {

                        }
                        R.id.completed -> {}
                        else -> {}
                    }

                true
            }
            show()
        }
    }

}