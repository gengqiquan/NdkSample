#cmake_minimum_required(VERSION 3.4)
#project(Video)
#
## 添加OpenCV库
#find_package(OpenCV)
#include_directories(${OpenCV_INCLUDE_DIRS})
#
#set(CMAKE_CXX_STANDARD 14)
#set(OpenCV_INCLUDE_DIRS /usr/local/Cellar/opencv/opencv4)

cmake_minimum_required(VERSION 3.4.1)
#该变量为真时会创建完整版本的Makefile
set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")
#定义变量ocvlibs使后面的命令可以使用定位具体的库文件
set(ocvlibs "src/main/cpp/native/libs")

#调用头文件的具体路径
include_directories(src/main/cpp/native/jni/include)
#增加我们的动态库
add_library(libopencv_java SHARED IMPORTED)
#建立链接
set_target_properties(libopencv_java PROPERTIES
        IMPORTED_LOCATION
        "/Users/gengqiquan/Android/NdkSample/app/src/main/cpp/native/libs/${ANDROID_ABI}/libopencv_java4.so")

add_library( # Sets the name of the library.
        compress-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/compress-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#
#add_executable(libopencv_java4 ${SOURCE_FILES})
target_link_libraries( # Specifies the target library增加库的链接
        compress-lib android log libopencv_java

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
