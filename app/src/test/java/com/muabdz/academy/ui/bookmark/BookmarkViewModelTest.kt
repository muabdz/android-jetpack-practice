package com.muabdz.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.source.AcademyRepository
import com.muabdz.academy.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookMarks() {
        val dummyCourses = DataDummy.generateDummyCourse()
        val courses = MutableLiveData<List<CourseEntity>>()
        courses.value = dummyCourses

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(courses)
        val courseEntities = viewModel.getBookMarks().value
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getBookMarks().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}