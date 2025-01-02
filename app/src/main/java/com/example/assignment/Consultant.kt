package com.example.assignment

class Consultant(
    val consultantId: Int,
    val name: String) {

    companion object{
        val consultantData= listOf<Consultant>(
            Consultant(1,"Oliver Tan Zu Zhuo"),
            Consultant(2,"Law Qian Wen"),
            Consultant(3, "Lem Chao Han")
        )
    }
    val getConsultant get() = consultantData

}