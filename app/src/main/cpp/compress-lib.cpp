//
// Created by 耿其权 on 2018/7/6.
//

#include <jni.h>
#include <string>
#include <iostream>
#include <algorithm>
#include <fstream>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <opencv2/opencv.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/core/mat.inl.hpp>
#include <opencv2/highgui/highgui.hpp>
//
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
    std::string path = jstringTostring(env, str);
    const string imageName(path);
    Mat image = cv::imread(imageName);  //读取图像
////    imshow("image", image);      //显示原图像
//    cout << imageName << "'s width is"
//         << image.size().width << endl;
//    cout << imageName << "'s height is"
//         << image.size().height << endl;
//    cout << image.depth() << endl;
    Mat newimage;
    resize(image, newimage, Size(100, 100)); //图像大小转换
//
////    imshow("newimage", newimage);  //显示变换大小后的图像
////    waitKey(0);
    std::string new_path = path.replace(path.find("."), 4, "000005.jpg");
    imwrite(new_path, newimage); //保存图片
    return env->NewStringUTF(new_path.c_str());
}




