package com.ekalips.skeleton.di.scopes

import javax.inject.Scope

/**
 * Created by Ekalips on 9/22/17.
 */

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class ActivityScope
