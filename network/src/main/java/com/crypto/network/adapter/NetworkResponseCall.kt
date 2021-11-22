package com.crypto.network.adapter

import com.crypto.network.model.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response


internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.Success(
                                    body
                                )
                            )
                        )
                    } else {
                        // Response is successful but the body is null

                        try {
                            val jsonObject = JSONObject(error?.string() ?: "{}")
                            val errorMessage: String = jsonObject.getString("error")

                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.UnknownError(
                                        RuntimeException(errorMessage)
                                    )
                                )
                            )

                        } catch (e: Exception) {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.UnknownError(
                                        RuntimeException(error?.string() ?: "Unknown Error")
                                    )
                                )
                            )

                        }


                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> {
                            try {
                                val jsonObject = JSONObject(error.string())

                                val errorMessage: String = jsonObject.getString("error")
                                errorMessage

                            } catch (ex: Exception) {
                                null
                            }
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.NetworkError(IOException(errorBody)))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.NetworkError(IOException("UnknownError")))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}