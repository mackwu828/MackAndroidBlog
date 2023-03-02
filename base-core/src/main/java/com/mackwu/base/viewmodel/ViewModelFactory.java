package com.mackwu.base.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

/**
 * @author MackWu
 * @since 2021/3/15 16:16
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            if (AppViewModel.class.isAssignableFrom(modelClass)) {
                return modelClass.getConstructor(Application.class).newInstance(application);
            }
            return modelClass.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

}
