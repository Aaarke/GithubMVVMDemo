package com.roshi.ufabertask.network

import com.roshi.ufabertask.model.GitData

/**
 * Response holder provided to the UI
 */
class Response private constructor(val status: Status, val data:  List<GitData>?, val error: Throwable?) {
    companion object {
        fun loading(): Response {
            return Response(Status.LOADING, null, null)
        }

        fun success(data: List<GitData>): Response {
            return Response(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): Response {
            return Response(Status.ERROR, null, error)
        }
    }
}