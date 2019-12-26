package com.letmecook.letmecook.myapplication;

import android.util.Log;
import android.view.View;
import com.letmecook.letmecook.myapplication.base.BaseActivity;
import com.letmecook.letmecook.myapplication.bean.HeaderBean;
import com.letmecook.letmecook.myapplication.bean.ResultBean;
import com.letmecook.letmecook.myapplication.contract.MyContract;
import com.letmecook.letmecook.myapplication.contract.MyPreserent;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends BaseActivity<MyPreserent> implements MyContract.View {

    public static final String TAG = TestActivity.class.getSimpleName();
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyPreserent(this);

    }

    @Override
    public void initNetData() {


    }

    public void getBanner(View view) {
        mPresenter.banner();
        Log.i(TAG,"11111");
        mPresenter.otherVersion();



        Map<String,Object> map = new HashMap<>();
        map.put("country", "1");
        map.put("phone", "6175100537");
        map.put("password", "letme123");
        map.put("phoneToken", "");
        mPresenter.signin2(map);
    }
    @Override
    public void getSuccess(int type, ResultBean o) {
        super.getSuccess(type, o);
        switch (type){
            case C.BANNER:
                HeaderBean headerBean = (HeaderBean) o.getData();

                break;
            case C.OTHERVERSION:

                break;
            case C.SIGNIN:

                break;
        }
    }
}
/**
 * "returnCode":"200",
 "msg":"login successful",
 "secure":0,
 "returnTime":1575634534,
 "data":{
 "id":"7",
 "phone":"6175100537",
 "email":"",
 "nickName":"Letmecook7",
 "accountPhoto":"http://www.letmecook.net/upload/2019/09/05/20190905083451XYRa1243.jpg",
 "cookId":"5",
 "token":"2rXzT5tO+8j3blhsD3ZLRPBEobjbczx2ErXffc10qcPgTFEndmEhvB+hw9ubc9NR",
 "phoneToken":"eCNREbGJSPg:APA91bF4wXEQZ9Dz26a5nSZnU_pWMKDXPYuC_mYHAjJcyRUuq6FCz_rOH9Ky611BNU1bqyiZ9q04m3TVXk7-wLPDa9ETRHxkuDGW7sSW65XqbnhLvpP3NH3uXII96NDSJTMu_JBE_EKm",
 "accid":"letmecook2018_7",
 "accidToken":"a693f5d431ae2582f7f6621976364060",
 "language":"en",
 "country":"1",
 "gender":"0",
 "birthDate":"1562347202",
 "inChina":false,
 "coverPhoto":"https://source.letmecook.com/",
 "qrcode":"https://source.letmecook.com/public/QRCode/K1Zbr3BWgYvBNenT_NY2nqsfWxHvHcSeZ04FPv6amRM64HxOeQsLgBVj9J9z6kpluss9",
 "cookMobile":"6175100537",
 "cookName":"young111",
 "cookNickName":"young ",
 "cookIntro":"a good chef",
 "cookLocation":"150 New Boston St, Woburn, MA 01801美国",
 "cookAddress":"150 Huntington Ave",
 "cookLongitude":"-71.1453158",
 "cookLatitude":"42.5082629",
 "status":"1",
 "isVideo":0,
 "portfolio":"https://source.letmecook.com/public/portfolio/15676725317078026219526.jpg",
 "portfolioThump":"",
 "errorReason":"",
 "popUpUpdateInfo":0
 }
 */