package com.example.myapplication.data

import com.example.myapplication.R

object MenuData {
    val menuList = listOf(
        MenuItem(
            "1",
            "Margherita Pizza",
            120000,
            "Pizza klasik dengan saus tomat San Marzano, mozzarella kerbau segar, basil, dan minyak zaitun extra virgin.",
            R.drawable.piza.toString()
        ),
        MenuItem(
            "2",
            "Fettuccine Carbonara",
            95000,
            "Pasta fettuccine dengan saus creamy telur, keju Pecorino Romano, dan guanciale krispi yang otentik.",
            R.drawable.carbo.toString()
        ),
        MenuItem(
            "3",
            "Lasagna Classica",
            110000,
            "Lapisan pasta lembut dengan saus bolognese daging sapi, béchamel, dan keju parmesan yang dipanggang sempurna.",
            R.drawable.lasa.toString()
        ),
        MenuItem(
            "4",
            "Risotto ai Funghi",
            105000,
            "Nasi arborio yang dimasak perlahan dengan kaldu kaya rasa dan berbagai jenis jamur hutan segar.",
            R.drawable.riso.toString()
        ),
        MenuItem(
            "5",
            "Spaghetti Aglio e Olio",
            80000,
            "Sajian pasta sederhana namun kaya rasa dengan bawang putih goreng, cabai, dan minyak zaitun.",
            R.drawable.chili.toString()
        ),
        MenuItem(
            "6",
            "Bruschetta Pomodoro",
            55000,
            "Roti panggang dengan topping tomat segar, bawang putih, basil, dan percikan cuka balsamik.",
            R.drawable.bruscheta.toString()
        ),
        MenuItem(
            "7",
            "Tiramisu Tradizionale",
            65000,
            "Dessert Italia klasik dengan biskuit ladyfinger yang direndam kopi espresso dan lapisan krim mascarpone lembut.",
            R.drawable.tiramisu.toString()
        ),
        MenuItem(
            "8",
            "Panna Cotta ai Frutti di Bosco",
            60000,
            "Puding krim Italia yang lembut dengan topping saus beri hutan yang menyegarkan.",
            R.drawable.panacota.toString()
        ),
        MenuItem(
            "9",
            "Osso Buco alla Milanese",
            185000,
            "Daging betis sapi yang dimasak perlahan dengan anggur putih, sayuran, dan kaldu, disajikan dengan risotto.",
            R.drawable.osso.toString()
        ),
        MenuItem(
            "10",
            "Gelato Artigianale",
            45000,
            "Es krim Italia buatan tangan dengan berbagai pilihan rasa otentik seperti Pistachio dan Stracciatella.",
            R.drawable.gelato.toString()
        )
    )

    fun getMenuById(id: String): MenuItem? = menuList.find { it.id == id }
}
