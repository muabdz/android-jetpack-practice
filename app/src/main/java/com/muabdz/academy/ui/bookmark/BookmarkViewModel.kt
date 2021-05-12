package com.muabdz.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.utils.DataDummy

class BookmarkViewModel: ViewModel() {
    fun getBookMarks(): List<CourseEntity> = DataDummy.generateDummyCourse()
}