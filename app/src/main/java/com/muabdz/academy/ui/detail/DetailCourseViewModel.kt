package com.muabdz.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.ModuleEntity
import com.muabdz.academy.data.source.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository): ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCoursesWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}