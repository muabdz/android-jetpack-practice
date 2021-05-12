package com.muabdz.academy.ui.bookmark

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookMarks() {
        val courseEntities = viewModel.getBookMarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}