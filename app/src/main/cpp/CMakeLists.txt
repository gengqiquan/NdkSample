# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
cmake_minimum_required(VERSION 3.4)#根据你的cmake版本定
project(NdkSample)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp)
add_executable(NdkSample ${SOURCE_FILES})
find_package(OpenCV REQUIRED)
target_link_libraries(NdkSample ${OpenCV_LIBS})

add_library( # Sets the name of the library.
        compress-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        compress-lib.cpp)

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

target_link_libraries( # Specifies the target library.
        compress-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})