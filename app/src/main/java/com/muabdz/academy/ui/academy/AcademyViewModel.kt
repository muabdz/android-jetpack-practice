package com.muabdz.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}