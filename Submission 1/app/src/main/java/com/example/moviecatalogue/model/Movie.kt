package com.example.moviecatalogue.model

data class Movie(
    var movieId: String,
    var title: String,
    var overview: String,
    var director: String,
    var userScore: String,
    var releaseDate: String,
    var imagePath: Int
)