package com.muabdz.academy.data.source

import androidx.lifecycle.LiveData
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCoursesWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}