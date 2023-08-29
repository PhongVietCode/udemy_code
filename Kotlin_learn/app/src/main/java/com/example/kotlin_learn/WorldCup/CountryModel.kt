package com.example.kotlin_learn.WorldCup

class CountryModel {
    var flagCountry:Int = 0
    var name:String = ""
    var wins :String =""

    constructor()
    constructor(flagCountry: Int, name: String, wins: String) {
        this.flagCountry = flagCountry
        this.name = name
        this.wins = wins
    }
}