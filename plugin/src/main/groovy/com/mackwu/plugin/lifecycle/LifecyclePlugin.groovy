package com.mackwu.plugin.lifecycle

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LifecyclePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println 'apply...  ' + getClass().name
        def appExtension = project.extensions.getByType(AppExtension.class)
        appExtension.registerTransform(new LifecycleTransform())
    }

}