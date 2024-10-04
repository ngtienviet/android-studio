package com.example.thusqlite

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var ketnoisqlite: sqlite
    private lateinit var ketnoiRCVadapter: Rcvadapter
    private var vitri = -1
    private var idDuocChon: Int? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var btthem = findViewById<Button>(R.id.btthem)
        var btxoa = findViewById<Button>(R.id.btxoa)
        var btsua = findViewById<Button>(R.id.btsua)
        var bttim = findViewById<Button>(R.id.bttim)
        var rcv = findViewById<RecyclerView>(R.id.rcv)
        var edten = findViewById<EditText>(R.id.edten)
        var radioNamNu = findViewById<RadioGroup>(R.id.radioNamNu)

        ketnoisqlite = sqlite(this)
        var danhsach = ketnoisqlite.laytatca()

        ketnoiRCVadapter = Rcvadapter(danhsach){vitrichon->
            vitri=danhsach.indexOf(vitrichon)
            edten.setText(danhsach[vitri].ten)
            idDuocChon = danhsach[vitri].id
        }
        rcv.adapter = ketnoiRCVadapter
        rcv.layoutManager = LinearLayoutManager(this)
        btthem.setOnClickListener(){
            var ten = edten.text.toString()
            var gioitinh = if(radioNamNu.checkedRadioButtonId==R.id.ranam)"nam" else "nữ"
            ketnoisqlite.them(ten,gioitinh)
            danhsach.clear()
            danhsach.addAll(ketnoisqlite.laytatca())
            ketnoiRCVadapter.notifyDataSetChanged()
        }
        btxoa.setOnClickListener(){
            if(idDuocChon!=null){
                ketnoisqlite.xoa(idDuocChon!!)
                danhsach.clear()
                danhsach.addAll(ketnoisqlite.laytatca())
                ketnoiRCVadapter.notifyDataSetChanged()
                edten.setText("")
                idDuocChon = null
            }
        }
        btsua.setOnClickListener(){
            if(idDuocChon!=null){
                var ten = edten.text.toString()
                var gioitinh = if(radioNamNu.checkedRadioButtonId==R.id.ranam)"nam" else "nữ"
                ketnoisqlite.sua(idDuocChon!!,ten,gioitinh)
                danhsach.clear()
                danhsach.addAll(ketnoisqlite.laytatca())
                ketnoiRCVadapter.notifyDataSetChanged()
                edten.setText("")
            }
        }
        bttim.setOnClickListener() {
            var tentimkiem = edten.text.toString()
            danhsach.clear()
            danhsach.addAll(ketnoisqlite.timkiem(tentimkiem))
            danhsach.addAll(ketnoisqlite.laytatca())
            ketnoiRCVadapter.notifyDataSetChanged()

        }
    }
}
