package com.muabdz.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muabdz.academy.data.ContentEntity
import com.muabdz.academy.data.CourseEntity
import com.muabdz.academy.data.ModuleEntity
import com.muabdz.academy.data.source.remote.RemoteDataSource
import com.muabdz.academy.data.source.remote.response.ContentResponse
import com.muabdz.academy.data.source.remote.response.CourseResponse
import com.muabdz.academy.data.source.remote.response.ModuleResponse

class AcademyRepository private constructor(private val remoteDataSource: RemoteDataSource): AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getAllCourses(): LiveData<List<CourseEntity>> {
        val courseResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponse) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResult.postValue(courseList)
            }

        })
        return courseResult
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResult = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in courseResponse) {
                    val course = CourseEntity(
                        response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath
                    )
                    courseList.add(course)
                }
                courseResult.postValue(courseList)
            }

        })

        return courseResult
    }

    override fun getCoursesWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult = MutableLiveData<CourseEntity>()
        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponse: List<CourseResponse>) {
                lateinit var course: CourseEntity
                for (response in courseResponse) {
                    if (courseId == response.id) {
                        course = CourseEntity(
                            response.id,
                            response.title,
                            response.description,
                            response.date,
                            false,
                            response.imagePath
                        )
                        break
                    }
                }
                courseResult.postValue(course)
            }
        })

        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResult = MutableLiveData<List<ModuleEntity>>()
        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in moduleResponse) {
                    val course = ModuleEntity(
                        response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false
                    )
                    moduleList.add(course)
                }
                moduleResult.postValue(moduleList)
            }

        })

        return moduleResult
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()
        remoteDataSource.getModules(courseId, object : RemoteDataSource.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponse: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                for (response in moduleResponse) {
                    if (response.moduleId == moduleId) {
                        module = ModuleEntity(
                            response.moduleId,
                            response.courseId,
                            response.title,
                            response.position,
                            false
                        )
                        remoteDataSource.getContent(moduleId, object : RemoteDataSource.LoadContentCallback {
                            override fun onContentReceived(contentResponse: ContentResponse) {
                                module.contentEntity = ContentEntity(contentResponse.content)
                                moduleResult.postValue(module)
                            }
                        })
                        break
                    }
                }
            }

        })

        return moduleResult
    }

}