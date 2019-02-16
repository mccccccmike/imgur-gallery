package com.example.imgurgallery

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> =
    subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.smartSubscribe(
    onStart: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onSuccess: (T) -> Unit = {}
): Disposable =
    addStartFinishActions(onStart, onFinish)
        .subscribe(onSuccess, { onError?.invoke(it) })

fun <T> Observable<T>.addStartFinishActions(
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null
): Observable<T> {
    onStart?.invoke()
    return doOnTerminate({ onFinish?.invoke() })
}

fun Context.toast(text: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, length).show()
}