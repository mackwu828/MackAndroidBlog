//package com.mackwu.base.viewmodel;
//
//import androidx.annotation.Nullable;
//import androidx.lifecycle.ViewModel;
//
//import com.uber.autodispose.lifecycle.CorrespondingEventsFunction;
//import com.uber.autodispose.lifecycle.LifecycleEndedException;
//import com.uber.autodispose.lifecycle.LifecycleScopeProvider;
//
//import io.reactivex.Observable;
//import io.reactivex.subjects.BehaviorSubject;
//
///**
// * ===================================================
// * Created by MackWu on 2021/10/11 14:54
// * <a href="mailto:wumengjiao828@163.com">Contact me</a>
// * <a href="https://github.com/mackwu828">Follow me</a>
// * ===================================================
// */
//public class AutoDisposeViewModel extends ViewModel implements LifecycleScopeProvider<AutoDisposeViewModel.ViewModelEvent> {
//
//    private final BehaviorSubject<ViewModelEvent> lifecycleEvents = BehaviorSubject.createDefault(ViewModelEvent.CREATED);
//    private static final CorrespondingEventsFunction<ViewModelEvent> correspondingEventsFunction = event -> {
//        if (event == ViewModelEvent.CREATED) {
//            return ViewModelEvent.CLEARED;
//        } else {
//            throw new LifecycleEndedException("Cannot bind to ViewModel lifecycle after onCleared.");
//        }
//    };
//
//    @Override
//    public Observable<ViewModelEvent> lifecycle() {
//        return lifecycleEvents.hide();
//    }
//
//    @Override
//    public CorrespondingEventsFunction<ViewModelEvent> correspondingEvents() {
//        return correspondingEventsFunction;
//    }
//
//    @Nullable
//    @Override
//    public ViewModelEvent peekLifecycle() {
//        return lifecycleEvents.getValue();
//    }
//
//    @Override
//    protected void onCleared() {
//        lifecycleEvents.onNext(ViewModelEvent.CLEARED);
//        super.onCleared();
//    }
//
//    enum ViewModelEvent {
//        CREATED, CLEARED
//    }
//
//}
