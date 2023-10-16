package com.alf.tecnica.model




data class Batter(
    val id: String,
    val type: String
)

data class Topping(
    val id: String,
    val type: String
)

data class Batters(
    val batter: java.util.ArrayList<Batter>
)

data class MockiDataModel(
    val id: String,
    val type: String,
    val name: String,
    val ppu: Double,
    val batters: Batters,
    val topping: java.util.ArrayList<Topping> = arrayListOf()
)
