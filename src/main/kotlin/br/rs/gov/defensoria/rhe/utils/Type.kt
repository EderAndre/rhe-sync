package br.rs.gov.defensoria.rhe.utils

import jakarta.inject.Singleton

@Singleton
class Type (
    var name: String? = null,
    var expressionFromDetection: String? = null,
    var extension: String? = null,
    var columnLimits: List<Int>? = null,
    var columnLabels: List<String>? = null
)