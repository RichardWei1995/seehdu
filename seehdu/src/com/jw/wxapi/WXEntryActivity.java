package com.jw.wxapi;


import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.os.Bundle;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {  
	// IWXAPI 是第三方app和微信通信的openapi接口  
    private IWXAPI api;  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        api = WXAPIFactory.createWXAPI(this, "wx9622f3e3368d5c25", false);  
        api.handleIntent(getIntent(), this);  
        super.onCreate(savedInstanceState);  
    }  
    @Override  
    public void onReq(BaseReq arg0) { }  
  
    @Override  
    public void onResp(BaseResp resp) {  
        switch (resp.errCode) {  
        case BaseResp.ErrCode.ERR_OK:  
            //分享成功  
            break;  
        case BaseResp.ErrCode.ERR_USER_CANCEL:  
            //分享取消  
            break;  
        case BaseResp.ErrCode.ERR_AUTH_DENIED:  
            //分享拒绝  
            break;  
        }  
    }  
}  

