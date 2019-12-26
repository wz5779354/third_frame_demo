package com.letmecook.letmecook.myapplication.contract;

import com.letmecook.letmecook.myapplication.base.BaseModle;
import com.letmecook.letmecook.myapplication.base.BasePresenter;
import com.letmecook.letmecook.myapplication.base.Baseview;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Author by wangze, Date on 2019/12/3.
 */

public class MyContract  {

    public interface Modle extends BaseModle{
     Observable banner();
     Observable otherVersion();
     Observable signin(RequestBody body);
     Observable signin2(Map<String,Object> map);
    }

    public interface View extends Baseview{

    }

    public interface Preserent extends BasePresenter{
        void banner();
        void otherVersion();
        void signin(Map<String,Object> map);
        void signin2(Map<String,Object> map);
    }
}
