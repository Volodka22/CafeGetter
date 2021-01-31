package com.example.myApp;

import java.io.Serializable

class User : Serializable {

    class Arr : Serializable{
        var name = ""
        var count = 4
    }

    var key:String = ""
    var numberTable:Int = 0
    val array = mutableListOf<Arr>()
}
