package com.guava.guavacake.network

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri: String = chain.request().url.toUri().toString()

        val responseString: String = when {
            uri.endsWith("drivers") -> DRIVER_RESPONSE
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .message(responseString)
            .body(responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")
            .build()
    }
}

const val DRIVER_RESPONSE = """
    {
        "shipments": [
            "215 Osinski Manors",
            "9856 Marvin Stravenue",
            "7127 Kathlyn Ferry",
            "987 Champlin Lake",
            "63187 Voltman Garden Suite 447",
            "75855 Dessie Lights",
            "1797 Adolf Island Apt. 744",
            "2431 Lindgreen Corners",
            "8725 Aufderhar River Suite 859",
            "79035 Shanna Light Apt. 322"
        ],
        "drivers": [
            "Everardo Welch",
            "Orval Mayert",
            "Howard Emmerich",
            "Iziah Lowe",
            "Monica Hermann",
            "Ellis Wisozk",
            "Noemie Murphy",
            "Cleve Durgan",
            "Murphy Mosciski",
            "Kaiser Sose"
        ]
    }
"""