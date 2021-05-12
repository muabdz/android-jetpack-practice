package com.muabdz.academy.data

data class CourseEntity(
    var courseId: String,
    var title: String,
    var description: String,
    var deadLine: String,
    var bookmarked: Boolean = false,
    var imagePath: String
)
