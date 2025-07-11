package com.example.alfa.utills

import android.util.Log

object LogUtil {
    private const val TAG = "APP_LOG"

    // Логирование для отладочных сообщений (Debug)
    // Используется для вывода сообщений, которые важны только в процессе разработки
    // Например, отслеживание значений переменных или потока выполнения
    fun d(message: String) = Log.d(TAG, message)

    // Логирование ошибок (Error)
    // Используется для логирования ошибок или исключений, которые требуют внимания
    // Также можно передать Throwable, если нужно логировать исключение
    fun e(message: String, throwable: Throwable? = null) = Log.e(TAG, message, throwable)

    // Логирование информационных сообщений (Info)
    // Используется для логирования нормальных событий приложения, которые не являются ошибками
    // Например, успешная загрузка данных или успешная авторизация пользователя
    fun i(message: String) = Log.i(TAG, message)

    // Логирование предупреждений (Warning)
    // Используется для сообщений, которые указывают на потенциальные проблемы, но не являются ошибками
    // Например, когда что-то происходит не так, но приложение продолжает работать
    fun w(message: String) = Log.w(TAG, message)
}
