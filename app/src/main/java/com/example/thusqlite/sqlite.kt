package com.example.thusqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class sqlite(context: Context):SQLiteOpenHelper(context, TEN_CSDL,null, PHIEN_BAN) {
    companion object{
        const val TEN_CSDL="quanlyhocsinhvaolop1"
        const val PHIEN_BAN=1
        const val TEN_BANG="hocsinhtronglop1"
        const val COT_ID="id"
        const val COT_TEN="ten"
        const val COT_GIOITINH="gioitinh"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table $TEN_BANG($COT_ID integer primary key autoincrement, $COT_TEN text, $COT_GIOITINH text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists $TEN_BANG")
        onCreate(p0)
    }
    fun them(sqlten: String, sqlgioitinh: String):Long{
        var mocsdl = writableDatabase
        var giaitri = ContentValues()
        giaitri.put(COT_TEN,sqlten)
        giaitri.put(COT_GIOITINH,sqlgioitinh)
        var ketqua = mocsdl.insert(TEN_BANG,null,giaitri)
        return ketqua
    }

    fun laytatca():MutableList<thongtinhs>{
        var mocsdl = readableDatabase
        var danhsachsql = mutableListOf<thongtinhs>()
        var truyvan = mocsdl.query(TEN_BANG,null,null,null,null,null,null)
        if(truyvan.moveToFirst()){
            do {
                var idsql = truyvan.getInt(0)
                var tensaisql = truyvan.getString(1)
                var gioitinhsql = truyvan.getString(2)
                danhsachsql.add(thongtinhs(idsql,tensaisql,gioitinhsql))
            }while (truyvan.moveToNext())
        }
        return danhsachsql
    }
    fun xoa(id:Int){
        var mocsdl = writableDatabase
        mocsdl.delete(TEN_BANG,"$COT_ID=?", arrayOf(id.toString()))
    }
    fun sua(idSua:Int,ten:String,gioitinh:String):Int{
        var mocsdl = writableDatabase
        var giaitri = ContentValues()
        giaitri.put(COT_TEN,ten)
        giaitri.put(COT_GIOITINH,gioitinh)
        return mocsdl.update(TEN_BANG,giaitri,"$COT_ID=?", arrayOf(idSua.toString()))
    }
    fun timkiem(tentimkiem:String):MutableList<thongtinhs>{
        var mocsdl = readableDatabase
        var danhsachsql = mutableListOf<thongtinhs>()
        var truyvan = mocsdl.query(TEN_BANG,null,"$COT_TEN like ?", arrayOf("%$tentimkiem%"),null,null,null)
        if (truyvan.moveToFirst()){
            do {
                var idsql = truyvan.getInt(0)
                var tensaisql = truyvan.getString(1)
                var gioitinhsql = truyvan.getString(2)
                danhsachsql.add(thongtinhs(idsql,tensaisql,gioitinhsql))
            }while (truyvan.moveToNext())
        }
        return danhsachsql
    }
}