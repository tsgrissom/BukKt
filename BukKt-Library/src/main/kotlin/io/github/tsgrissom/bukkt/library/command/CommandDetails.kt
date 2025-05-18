package io.github.tsgrissom.bukkt.library.command

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class CommandDetails(val name: String, val aliases: Array<String> = [], val permission: String = "")