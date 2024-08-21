package com.jy.quizappfirebase.core.utils

import com.jy.quizappfirebase.core.data.model.ValidationField

object ValidationUtils {
    fun validate(vararg fields: ValidationField): String? {
        fields.forEach { field ->
            if(!Regex(field.regExp).matches(field.value)) {
                return field.errMsg
            }
        }
        return null
    }
}