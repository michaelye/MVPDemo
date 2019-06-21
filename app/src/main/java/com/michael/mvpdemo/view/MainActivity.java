package com.michael.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.michael.mvpdemo.R;
import com.michael.mvpdemo.presenter.MainPresenter;

/**
 * 并没有任何业务逻辑，遇到需要处理业务逻辑时，交给Presenter来做
 *
 * 当Presenter完成对Model的操作后，再通过回调，交给View来显示，此时，Activity变成比较纯粹的View层
 *
 * */
public class MainActivity extends AppCompatActivity implements MainView//实现接口
{

    private TextView tvTemperatureAndWindLevel;

    private MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniComponent();
        //在生命周期变化时，通知Presenter
        mainPresenter.onCreate();
    }

    private void iniComponent()
    {
        tvTemperatureAndWindLevel = findViewById(R.id.tvTemperatureAndWindLevel);
        SeekBar sbTemperature = findViewById(R.id.sbTemperature);
        Button btnChangeWindLevel = findViewById(R.id.btnChangeWindLevel);

        sbTemperature.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //将业务逻辑交给Presenter来做
                mainPresenter.onTemperatureChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        btnChangeWindLevel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //将业务逻辑交给Presenter来做
                mainPresenter.onWindLevelChanged();
            }
        });
    }

    /**
     * Presenter中完成具体的业务（修改了Model层后），回调该方法，修改View
     * */
    @Override
    public void setTextViewText(String text)
    {
        tvTemperatureAndWindLevel.setText(text);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //在生命周期变化时，通知Presenter
        mainPresenter.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //在生命周期变化时，通知Presenter
        mainPresenter.onResume();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //在生命周期变化时，通知Presenter
        mainPresenter.onDestroy();
    }
}
