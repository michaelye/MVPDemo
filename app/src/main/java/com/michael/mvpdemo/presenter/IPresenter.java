package com.michael.mvpdemo.presenter;

/**
 * 为了能够在Activity生命周期发生变化的时候，收到相应的回调
 * 我们通常需要定义这些相应的接口
 * */
public interface IPresenter
{
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
