package com.example.nisaapps.pertemuan_2

fun main() {
    println("Hai rekan-rekan...")
    println("Selamat datang di bahasa pemograman Kotlin")

    println("===============")

    var angka = 15
    println("Hasil dari 15+10 = ${angka + 10}")

    val nilaiInt = 10000
    val nilaiDouble = 100.003
    val nilaiFloat = 1000.0f

    println("Nilai intger = $nilaiInt")
    println("Nilai double = $nilaiDouble")
    println("Nilai float = $nilaiFloat")

    println("======= String ======")
    val huruf = 'a' //Petik 1 untuk karakter
    println("Ini penggunaan karakter '$huruf'")

    val nilaiString = "Mawar" //Petik 2 untuk string
    println("Halo $nilaiString! Apa kabar?")

    println("======= Kondisi =========")

    val nilai = 10
    if (nilai < 10)
        println("Bilangan Negaif")
    else {
        if (nilai % 2 === 0) //=== 3 kali artinya kita juga membandingkan sampai ke tipe datanya, kalau cuma 2 hanya  menyamakan value
            println("Bilangan Genap")
        else
            println("Bilangan Ganjil")
    }

    println("====== Perulangan ========")
    val kampusku: Array<String> = arrayOf("Kampus", "Politeknik", "Caltex", "Riau")
    for (kampus: String in kampusku) {
        println(kampus)
    }

    //cmd + shift + 4 buat ss
}
