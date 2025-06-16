package org.example.project.utils.extension

/**
 * Returns one of two values based on the value of the Boolean.
 *
 * @param ifTrue the value to return if the Boolean is `true`.
 * @param ifFalse the value to return if the Boolean is `false`.
 * @return `ifTrue` if the Boolean is `true`, otherwise `ifFalse`.
 *
 * @sample
 * val result = isDarkMode.select("Dark Mode", "Light Mode")
 */
fun <T> Boolean.select(ifTrue: T, ifFalse: T): T = if (this) ifTrue else ifFalse