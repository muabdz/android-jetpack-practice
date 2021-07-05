package com.muabdz.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getBookMarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}