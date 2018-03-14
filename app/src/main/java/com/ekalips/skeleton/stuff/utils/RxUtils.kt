package com.ekalips.skeleton.stuff.utils

import com.ekalips.skeleton.stuff.InsignificantError
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxUtils {
    companion object {

        fun <T> wrapAsIO(observable: Observable<T>): Observable<T> {
            return wrapAs(observable, Schedulers.io())
        }

        fun <T> wrapAsNewThread(observable: Observable<T>): Observable<T> {
            return wrapAs(observable, Schedulers.newThread())
        }

        fun <T> wrapAs(observable: Observable<T>, scheduler: Scheduler): Observable<T> {
            return observable
                    .subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
        }

        fun <T> applyObservalbeIOSchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> applySignleIOSchedulers(): SingleTransformer<T, T> {
            return SingleTransformer { observable ->
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun applyCompletableIOSchedulers(): CompletableTransformer {
            return CompletableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> throwOrWrapObservable(handler: (Throwable) -> Boolean): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.onErrorResumeNext { t: Throwable -> if (handler(t)) Observable.error(InsignificantError(t)) else Observable.error(t) }
            }
        }

        fun throwOrWrapCompletable(handler: (Throwable) -> Boolean): CompletableTransformer {
            return CompletableTransformer {
                it.onErrorResumeNext { t: Throwable -> if (handler(t)) Completable.error(InsignificantError(t)) else Completable.error(t) }
            }
        }

        fun <T> throwOrWrapSingle(handler: (Throwable) -> Boolean): SingleTransformer<T, T> {
            return SingleTransformer {
                it.onErrorResumeNext { t: Throwable -> if (handler(t)) Single.error(InsignificantError(t)) else Single.error(t) }
            }
        }

        fun <T> Observable<T>.wrap(): Observable<T> {
            return compose(RxUtils.applyObservalbeIOSchedulers())
        }

        fun <T> Single<T>.wrap(): Single<T> {
            return compose(RxUtils.applySignleIOSchedulers())
        }

        fun Completable.wrap(): Completable {
            return compose(RxUtils.applyCompletableIOSchedulers())
        }

        fun <T> Observable<T>.wrap(errorHandler: ((Throwable) -> Boolean)): Observable<T> {
            return wrap()
                    .compose(RxUtils.throwOrWrapObservable(errorHandler))
        }

        fun <T> Single<T>.wrap(errorHandler: (Throwable) -> Boolean): Single<T> {
            return wrap()
                    .compose(RxUtils.throwOrWrapSingle(errorHandler))
        }

        fun Completable.wrap(errorHandler: (Throwable) -> Boolean): Completable {
            return wrap()
                    .compose(RxUtils.throwOrWrapCompletable(errorHandler))
        }

    }

}
