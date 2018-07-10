package com.example.tl.recyclerviewtest;

import android.util.Log;

public class Caller {
    public static void getCaller()
    {
        String TAG = "callerTrace";
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        for (int i = 0; i < stack.length; i++)
        {
            StackTraceElement s = stack[i];
            Log.d(TAG, i + "ClassName:" + s.getClassName());
            Log.d(TAG, i + "ClassName:" + s.getMethodName());
            Log.d(TAG, i + "ClassName:" + s.getLineNumber());
            Log.d(TAG, "---------------------------------------------------------------");
        }
    }
}
