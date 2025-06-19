package org.example.project.utils.extension

fun String?.orDefault(defaultValue: String = "") = this ?: defaultValue