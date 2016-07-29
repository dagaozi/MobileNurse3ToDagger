package com.wyyy.mobilenurse;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wyyy.corelibrary.base.BaseActivity;
import com.wyyy.corelibrary.base.BaseSubscriber;
import com.wyyy.corelibrary.base.IBaseSubscriber;
import com.wyyy.corelibrary.net.HttpResultFunc;
import com.wyyy.corelibrary.utils.LogUtils;
import com.wyyy.corelibrary.utils.NetUtils;
import com.wyyy.corelibrary.utils.RxBus;
import com.wyyy.corelibrary.utils.SnackbarUtil;
import com.wyyy.corelibrary.utils.ToastUtil;
import com.wyyy.mobilenurse.model.TaskModel;
import com.wyyy.mobilenurse.model.TestModel;
import com.wyyy.mobilenurse.net.ApiMethods;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DemoActivity extends BaseActivity implements IBaseSubscriber {


    @Bind(R.id.tvTest)
    TextView tvTest;
    @Bind(R.id.btnTest)
    Button btnTest;
    @Bind(R.id.btnTest1)
    Button btnTest1;
    @Bind(R.id.btnTest2)
    Button btnTest2;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_demo);
        setTitleName("测试页面1");
        LogUtils.d("测试");

    }

    @Override
    protected void initEvents() {

    }
    @OnClick({R.id.btnTest, R.id.btnTest1, R.id.btnTest2, R.id.btnTest3})
    public void btnClink(Button btn) {
        switch ((btn.getId())) {
            case R.id.btnTest:
                getTaobaodata("21.22.11.33");
                break;
            case R.id.btnTest1:
                getTaobaodata2("21.22.11.33");
                break;
            case R.id.btnTest2:
                rxBusTest();
                break;
            case R.id.btnTest3:
                getTasksData();
                break;
            default:
                break;
        }
    }
    //使用ApiMethods访问网络
    private void getTaobaodata(String ip) {
        //showProgressDialog();
        Subscription sn = ApiMethods.getInstance().getTaoboData(new BaseSubscriber<TestModel>(this, 1), ip);
        addSubscription(sn);
    }
    //使用ApiStores访问网络
    private void getTaobaodata2(String ip) {
        showProgressDialog();
        Observable observable = ApiMethods.getInstance().getApiStores().getTaobaoData(ip);
        Subscription sn= observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new HttpResultFunc<TestModel>())
                .subscribe(new BaseSubscriber<TestModel>(this, 2));
        addSubscription(sn);

    }
    //使用ApiStores访问网络
    private void getTasksData() {
        SnackbarUtil.showSnackbarShort("getTasksData",SnackbarUtil.Info).show();
        showProgressDialog();
        Subscription sn = ApiMethods.getInstance().getTasksData(new BaseSubscriber<List<TaskModel>>(this, 3));
        addSubscription(sn);

    }
    private void rxBusTest(){
        //接收事件
        Subscription sn = RxBus.getDefault().toOserverble(TestModel.class)
                .subscribe(new Action1<TestModel>() {
                    @Override
                    public void call(TestModel testModel) {
                        setTitleName(testModel.getCountry());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        setTitleName("出错了");
                    }
                });
        //发送事件
        RxBus.getDefault().post(new TestModel("RXBus测试"));//再也不用写接口回调啦
        addSubscription(sn);
    }

    @Override
    public void onNext(Object o, int flag) {
        switch (flag) {
            case 1:
                ToastUtil.showToast(DemoActivity.this, ((TestModel) o).getCountry()+1);
                break;
            case 2:
                ToastUtil.showToast(DemoActivity.this, ((TestModel) o).getCountry()+2);
                break;
            case 3:
                ToastUtil.showToast(DemoActivity.this, ((List<TaskModel>) o).size()+"");

                break;
            default:

        }
    }

    @Override
    public void onCompleted() {
       hideProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        hideProgressDialog();
        LogUtils.e(TAG_LOG,e.getMessage());
      // ToastUtil.showToast(DemoActivity.this, NetUtils.checkApiException(e));
        SnackbarUtil.showSnackbarLong( NetUtils.checkApiException(e),SnackbarUtil.Alert).setActionTextColor(Color.WHITE)
                .setAction("网络设置",new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                BaseActivity.currentActivity.startActivity(intent);
            }
        }).show();
    }

}
