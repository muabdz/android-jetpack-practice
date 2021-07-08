package com.muabdz.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.muabdz.academy.data.source.remote.RemoteDataSource
import com.muabdz.academy.utils.DataDummy
import com.muabdz.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourse()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remote).getAllCourses(any())
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        val courseEntities =
            LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
        verify(remote).getModules(eq(courseId), any())
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(moduleResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(remote).getAllCourses(any())
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentCallback)
                .onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), any())
        val courseEntitiesContent =
            LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote).getModules(eq(courseId), any())
        verify(remote).getContent(eq(moduleId), any())
        Assert.assertNotNull(courseEntitiesContent)
        Assert.assertNotNull(courseEntitiesContent.contentEntity)
        Assert.assertNotNull(courseEntitiesContent.contentEntity?.content)
        Assert.assertEquals(content.content, courseEntitiesContent.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)
            null
        }.`when`(remote).getAllCourses(any())
        val resultCourse =
            LiveDataTestUtil.getValue(academyRepository.getCoursesWithModules(courseId))
        verify(remote).getAllCourses(any())
        Assert.assertNotNull(resultCourse)
        Assert.assertNotNull(resultCourse.title)
        Assert.assertEquals(courseResponse[0].title, resultCourse.title)
    }
}