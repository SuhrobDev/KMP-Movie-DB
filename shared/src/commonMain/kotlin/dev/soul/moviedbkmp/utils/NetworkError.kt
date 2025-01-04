package dev.soul.moviedbkmp.utils

import dev.soul.moviedbkmp.utils.Error

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    INVALID_PARAMETER,
    UNKNOWN;
}