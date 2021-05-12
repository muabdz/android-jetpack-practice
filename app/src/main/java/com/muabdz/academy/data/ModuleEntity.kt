package com.muabdz.academy.data

data class ModuleEntity(
    var moduleId: String,
    var courseid: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
) {
    var contentEntity: ContentEntity? = null
}
