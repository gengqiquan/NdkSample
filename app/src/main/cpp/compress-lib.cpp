//
// Created by 耿其权 on 2018/7/6.
//

#include <jni.h>
#include <string>
#include "md5.h"
#include "Md5.cpp"
#include <iostream>
#include <algorithm>
#include <fstream>
#include <string.h>
#include "opencv/cv.h"
#include "opencv2/opencv.hpp"
using namespace cv;
using namespace std;

/*JNI字符串转C字符串*/
char *jstringTostring(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("utf-8");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;
}

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_gengqiquan_ndksample_Compress_compress(
        JNIEnv *env, jobject, jobject,
        jstring str /* this */) {
    std::string hello = jstringTostring(env, str);

    string m1 = MD5(hello + "DFABE927309A5035").toString().substr(8, 16);
    transform(m1.begin(), m1.end(), m1.begin(), ::toupper);
    string m2 = MD5(m1 + "DFABE927309A5035" + "ESCFSOUNAL").toString().substr(8, 16);
    transform(m2.begin(), m2.end(), m2.begin(), ::toupper);
    return env->NewStringUTF(m2.c_str());
}




