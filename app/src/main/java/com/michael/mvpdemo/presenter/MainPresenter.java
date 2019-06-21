package com.michael.mvpdemo.presenter;

import com.michael.mvpdemo.model.AirConditioner;
import com.michael.mvpdemo.view.MainView;

/**
 * Presenter持有View和Model
 *
 * 持有View的引用，这样View（Activity）就不能与Model直接通信，彻底实现了Model和View解耦
 *
 * Activity因为实现了MainView接口，通过构造器传递进来，所以Presenter能够通过MainView中的回调方法直接对Activity进行操作
 *
 * 所以Presenter既能对Model操作，也能对View（Activity）操作，但Model和View之间不会进行直接的通信，Presenter起到桥梁的作用
 *
 * 坏处：
 * 1.相对于MVC代码量明显增加，当页面复杂时，代码的直观度会比较差
 *
 * 2.与Activity的耦合度较高，当Activity的生命周期发生变化时，需要通知Presenter
 *
 * */
public class MainPresenter implements IPresenter
{
    //View（Activity）
    private MainView mainView;

    //Model
    private AirConditioner airConditionerModel;

    public MainPresenter(MainView mainView)
    {
        this.mainView = mainView;
        this.airConditionerModel = new AirConditioner();
    }

    @Override
    public void onCreate()
    {
        this.airConditionerModel = new AirConditioner();
        updateUI();
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onResume()
    {

    }

    @Override
    public void onDestroy()
    {

    }

    /**
     * 温度发生变化时候调用
     * */
    public void onTemperatureChanged(int temperature)
    {
        airConditionerModel.changeTemperature(temperature);
        updateUI();
    }

    /**
     * 风力发生变化时候调用
     * */
    public void onWindLevelChanged()
    {
        airConditionerModel.changeWindLevel();
        updateUI();
    }

    /**
     * 更新UI时调用
     * */
    public void updateUI()
    {
        mainView.setTextViewText(airConditionerModel.getCurrentCondition());
    }
}
