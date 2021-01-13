package com.pgyer.shophelper;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.pgyer.shophelper.util.ShearPlateUtil;
import com.wbql.mylibrary.utils.DataUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuqiang 2021-01-11 .
 * onServiceConnected()：做一些初始化的操作
 * onInterrupt ()：AccessibilityService被中断时会调用，在整个生命周期里会被调用多次。
 * onUnbind(intent: Intent)：你可以做一些初始化的操作
 * onServiceConnected：AccessibilityService将要关闭时会被调用，这个方法做一些释放资源的操作。
 * onAccessibilityEvent(event: AccessibilityEvent?)：核心API，AccessibilityEvent事件的回调函数，系统通过sendAccessibiliyEvent()方法发送AccessibilityEvent事件到这里
 * getRootInActiveWindow()：则会返回当前活动窗口的根结点，查找View的时候用到它
 * findFoucs(int falg)：查找拥有特定焦点类型的控件
 * disableSelf()：禁用当前服务

 *
 */
public class ShopHelperService extends AccessibilityService {

    public static ShopHelperService mService;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                //.......
                Log.e("liuqiang-->","TYPE_NOTIFICATION_STATE_CHANGED event="+event.toString());

                break;


            case AccessibilityEvent.MAX_TEXT_LENGTH:
                //.......
                Log.e("liuqiang-->","MAX_TEXT_LENGTH");

                break;

            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_CLICKED");
//                String ssss = event.getSource().getText().toString();
//                Log.e("liuqiang-->","TYPE_VIEW_CLICKED className="+ssss);
                break;

            case AccessibilityEvent.TYPE_VIEW_LONG_CLICKED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_LONG_CLICKED");

                break;

            case AccessibilityEvent.TYPE_VIEW_SELECTED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_SELECTED");

                break;

            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_FOCUSED");

                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_TEXT_CHANGED");

                break;

            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                //.......
                Log.e("liuqiang-->","TYPE_WINDOW_STATE_CHANGED");

                String name = event.getClassName().toString();
                Log.e("liuqiang-->","TYPE_WINDOW_STATE_CHANGED className ="+name);
//                Log.e("liuqiang-->","TYPE_WINDOW_STATE_CHANGED className ="+event.getSource().toString());
                if (name.equals("com.taobao.tao.TBMainActivity")) {
                    Log.e("liuqiang-->","当前在商品详情界面");
                    handler.postDelayed(runnable,0);
                }else if("com.taobao.android.purchase.TBPurchaseActivity".equals(name)){
                    Log.e("liuqiang-->","提交订单界面");
                    handler.postDelayed(runnable,0);
                }else if("com.jd.lib.productdetail.ProductDetailActivity".equals(name)){
                    handler.postDelayed(run,10);
                }else if("com.jd.lib.settlement.fillorder.activity.NewFillOrderActivity".equals(name)){
                    handler.postDelayed(run,10);
                }
                break;


            case AccessibilityEvent.TYPE_VIEW_HOVER_ENTER:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_HOVER_ENTER");

                break;

            case AccessibilityEvent.TYPE_VIEW_HOVER_EXIT:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_HOVER_EXIT");

                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_START:
                //.......
                Log.e("liuqiang-->","TYPE_TOUCH_EXPLORATION_GESTURE_START");

                break;

            case AccessibilityEvent.TYPE_TOUCH_EXPLORATION_GESTURE_END:
                //.......
                Log.e("liuqiang-->","TYPE_TOUCH_EXPLORATION_GESTURE_END");

                break;

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED :
                //.......
                Log.e("liuqiang-->","TYPE_WINDOW_CONTENT_CHANGED  event = ");


                break;

            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_SCROLLED");

                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_TEXT_SELECTION_CHANGED");

                break;

            case AccessibilityEvent.TYPE_ANNOUNCEMENT:
                //.......
                Log.e("liuqiang-->","TYPE_ANNOUNCEMENT");

                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_ACCESSIBILITY_FOCUSED");

                break;

            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED");

                break;

            case AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY:
                //.......
                Log.e("liuqiang-->","TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY");

                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                //.......
                Log.e("liuqiang-->","TYPE_GESTURE_DETECTION_START");

                break;

            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                //.......
                Log.e("liuqiang-->","TYPE_GESTURE_DETECTION_END");

                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_START:
                //.......
                Log.e("liuqiang-->","TYPE_TOUCH_INTERACTION_START");

                break;

            case AccessibilityEvent.TYPE_TOUCH_INTERACTION_END:
                //.......
                Log.e("liuqiang-->","TYPE_TOUCH_INTERACTION_END");

                break;

            case AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED:
                //.......
                Log.e("liuqiang-->","CONTENT_CHANGE_TYPE_UNDEFINED");

                break;



        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onServiceConnected() {

        mService = this;
        Log.e("liuqiang-->","被锁定");
        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 100;
        setServiceInfo(info);
        info.packageNames = new String[]{"com.taobao.taobao", "com.jingdong.app.mall"};
        setServiceInfo(info);
        super.onServiceConnected();
    }



    @SuppressLint("NewApi")
    private void getPacket() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if(rootNode==null){
            return;
        }
         recycle(rootNode);
    }

    boolean isFast = true;

    /**
     * 打印一个节点的结构
     * @param info
     */
    @SuppressLint("NewApi")
    public void recycle(AccessibilityNodeInfo info) {

        if (info.getChildCount() == 0) {
            if(info.getText() != null){
                String content = info.getText().toString().trim();
                Log.e("liuqiang-->", content);
                if(content.equals("开始抢购")){
                    Log.i("liuqiang-->", "Click"+",isClick:"+info.isClickable());
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    AccessibilityNodeInfo parent = info.getParent();
                    while(parent != null){
                        if(parent.isClickable()){
                            isFast = false;
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }else if(content.contains("结算")||content.equals("立即购买")){
                    //这里有一个问题需要注意，就是需要找到一个可以点击的View
                    Log.i("liuqiang-->", "Click"+",isClick:"+info.isClickable());
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    AccessibilityNodeInfo parent = info.getParent();
                    while(parent != null){
                        Log.i("liuqiang-->", "parent isClick:"+parent.isClickable());
                        if(parent.isClickable()){
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }

                }else if(content.equals("提交订单")){

                    Log.i("liuqiang-->", "Click"+",isClick:"+info.isClickable());
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    AccessibilityNodeInfo parent = info.getParent();
                    while(parent != null){
                        if(parent.isClickable()){
                            isFast = false;
                            parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            break;
                        }
                        parent = parent.getParent();
                    }
                }
            }
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if(info.getChild(i)!=null){
                    recycle(info.getChild(i));
                }
            }
        }
    }

    /**
     * 辅助功能是否启动
     */
    public static boolean isStart() {
        return mService != null;
    }

    @Override
    public void onInterrupt() {
        mService = null;
        Log.e("liuqiang-->","断开");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("liuqiang-->","关闭");
        mService = null;
    }


    private Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            if(DataUtil.getStringDate()){
                getPacket() ;
            }else{
                handler.postDelayed(runnable,10);
            }
        }
    };

    Runnable run = new Runnable() {
        @Override
        public void run() {

            if(!isFast){
                Log.e("liuqiang-->","false");
                return;
            }
            Log.e("liuqiang-->","222");
            handler.postDelayed(run,10);
            getPacket() ;
        }
    };


    //判断是否存在汉字
    public boolean checkcountname(String countname){
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
