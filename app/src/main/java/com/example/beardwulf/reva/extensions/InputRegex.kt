package com.example.beardwulf.reva.extensions

class InputRegex {
    companion object {
        fun controleerLettersCijfers(input: String) : Boolean = "[a-zA-Z0-9 ]+".toRegex().matches(input)
        fun controleerLetters(input:String):Boolean="[a-zA-Z]+".toRegex().matches(input)
    }

}