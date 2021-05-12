package com.muabdz.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.utils.DataDummy

class AcademyViewModel: ViewModel() {
    fun getCourses(): List<CourseEntity> = DataDummy.generateDummyCourse()
}