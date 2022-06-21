# DevTimer
### 使用示例代码
```java
    private void initRegisterLOUDNESS() {
        DevTimer devTimer = new DevTimer.Builder(0, 1000, 10, "register").build();
        devTimer.setHandler(new Handler());//加上这行代码 setcallback的回调就是主线程了，否则是子线程
        devTimer.setCallback(new DevTimer.Callback() {
            @Override
            public void callback(DevTimer timer, int number, boolean end, boolean infinite) {
                LogUtil.d(TAG, "number =" + number);
                boolean b = ShareDataManager.getShareDataManager().registerShareDataListener(ParamConstant.SHARE_INFO_ID_LOUDNESS, mIShareDataInterface);
                if (b) {
                    devTimer.stop();
                    String loudnessJson = ShareDataManager.getShareDataManager().getShareData(ParamConstant.SHARE_INFO_ID_LOUDNESS);//从shareinfo取数据（包含很多信息）我们需要自己解析筛选出我们想要的
                    parseBeepSwitch(loudnessJson);
                    parseSpeedLevel(loudnessJson);
                    parseLoudness(loudnessJson);//音量设置
                }
                if (end) {
                    LogUtil.w(TAG, "register SHARE_INFO_ID_LOUDNESS 10 time fail");
                }
            }
        });
    }
```
