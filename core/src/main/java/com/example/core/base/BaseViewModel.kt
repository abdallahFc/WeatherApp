package com.example.core.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.util.EmptyResponseException
import com.example.core.util.ErrorHandler
import com.example.core.util.NetworkErrorException
import com.example.core.util.ServerErrorException
import com.example.core.util.UnknownErrorException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(initialState: T) : ViewModel() {
    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (t: ErrorHandler) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                val result = function()
                onSuccess(result)
            } catch (exception: NetworkErrorException) {
                onError(ErrorHandler.NetworkError(exception.message.toString()))
            } catch (exception: EmptyResponseException) {
                onError(ErrorHandler.EmptyResponse(exception.message.toString()))
            } catch (exception: ServerErrorException) {
                onError(ErrorHandler.ServerError(exception.message.toString()))
            } catch (exception: UnknownErrorException) {
                onError(ErrorHandler.UnknownError(exception.message.toString()))
            }
        }

    }
}

