package com.example.assignment

class BorrowData(
    val country:String,
    val organizationName:String,
    val url:String) {

    companion object{
        val borrow_data= listOf<BorrowData>(
            BorrowData("Malaysia","WCM Machinery","https://www.wcmmachinery.com.my/"),
            BorrowData("Malaysia","KS Engineering","https://www.kskimsui.com.my/"),
            BorrowData("Malaysia","Tractor Malaysia","https://www.tractors.com.my/products/rental-equipment"),
            BorrowData("Malaysia","CK Heavy Rentals Equipment","https://equipmentrentalmalaysia.com/index.php/earthmoving/tractors/"),
            BorrowData("Singapore","Rentuu","https://www.rentuu.com/"),
            BorrowData("Singapore","AntBuildz","https://www.rentuu.com/"),
            BorrowData("Singapore","Multi Ways Equipment","https://www.multiways.com.sg/collections/wheel-loader"),
            BorrowData("Singapore","Agrithing","https://agrithing.com/"),
            BorrowData("Thailand","Helal Group","http://www.helalgroup.org.af/services-agri-fishery.php")
        )
    }

    val getBorrow get() =borrow_data

}