package com.muabdz.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}