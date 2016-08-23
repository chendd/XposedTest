package com.example.xposedtest;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookUtil implements IXposedHookLoadPackage{

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// 标记目标app包名
        if (!lpparam.packageName.equals("com.example.logintest"))
            return;
        XposedBridge.log("Loaded app: " + lpparam.packageName);
        
        // Hook MainActivity中的isCorrectInfo(String,String)方法
        // findAndHookMethod(hook方法的类名，classLoader，hook方法名，hook方法参数...，XC_MethodHook)
//        XposedHelpers.findAndHookMethod("com.example.logintest.MainActivity", lpparam.classLoader, "isCorrectInfo", String.class,
//                String.class, new XC_MethodHook() {
//
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("开始hook");
//                        XposedBridge.log("参数1 = " + param.args[0]);
//                        XposedBridge.log("参数2 = " + param.args[1]);
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        XposedBridge.log("结束hook");
//                        XposedBridge.log("参数1 = " + param.args[0]);
//                        XposedBridge.log("参数2 = " + param.args[1]);
//
//                    }
//                });
        
        // Hook MainActivity中的onClick(View v)方法
        XposedHelpers.findAndHookMethod("com.example.logintest.MainActivity", lpparam.classLoader, "onClick", View.class, new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        Class clazz = param.thisObject.getClass();
                        XposedBridge.log("class name:"+clazz.getName());
                        
                        
                        // 输入框不为私有private 可通过以下方式获取 
//                        Field field = clazz.getField("et_password");// 密码输入框 id

                        // 通过类的字节码得到该类中声明的所有属性，无论私有或公有
                        Field field = clazz.getDeclaredField("et_password");
                        // 设置访问权限（这点对于有过android开发经验的可以说很熟悉）
                        field.setAccessible(true);

                        EditText password = (EditText) field.get(param.thisObject);
                        
                        String string = password.getText().toString();
                        XposedBridge.log("密码 = " + string);
                        // 设置新密码
                        password.setText("改你妹啊!!");
                        
                    }
                });
        
        
        
        
	}

}
