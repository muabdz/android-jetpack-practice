package com.muabdz.academy.data

import com.muabdz.academy.data.source.remote.RemoteDataSource
import com.muabdz.academy.data.source.remote.response.CourseResponse
import com.muabdz.academy.utils.DataDummy
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourse()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        `when`(remote.getAllCourses()).thenReturn(DataDummy.generateRemoteDummyCourse() as ArrayList<CourseResponse>)
        val courseEntities = academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        `when`(remote.getModules(courseId)).thenReturn(DataDummy.generateRemoteDummyModules(courseId))
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        Assert.assertNotNull(moduleEntities)
        Assert.assertEquals(moduleResponse.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        `when`(remote.getAllCourses()).thenReturn(DataDummy.generateRemoteDummyCourse() as ArrayList<CourseResponse>)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        Assert.assertNotNull(courseEntities)
        Assert.assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        Assert.assertNotNull(resultModule)
        Assert.assertEquals(content.content, resultModule.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse = academyRepository.getCoursesWithModules(courseId)
        verify(remote).getAllCourses()
        Assert.assertNotNull(resultCourse)
        Assert.assertEquals(courseResponse[0].title, resultCourse.title)
    }
}