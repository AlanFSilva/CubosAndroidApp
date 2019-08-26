package com.example.cubosapp.data

data class MovieListRequest (val page: Int, val total_results: Int, val total_pages: Int, val results: List<MovieCard>)