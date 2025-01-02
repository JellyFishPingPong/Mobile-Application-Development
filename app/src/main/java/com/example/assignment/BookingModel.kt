package com.example.assignment

data class BookingModel(
    var bookingId: Int,
    var memId : Int,
    var consultantId : Int,
    var timeSelected : String,
    var dateSelected: String
    ) {

    constructor(memID: Int, consultantId: Int, timeSelected: String, dateSelected: String):
            this(0, memID, consultantId,timeSelected, dateSelected)

    constructor():this(0,0,0,"", "")
}


