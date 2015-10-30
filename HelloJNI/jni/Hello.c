#include <stdio.h>
#include <jni.h>
#include "com_example_hellojni_Test.h"

JNIEXPORT jstring JNICALL Java_com_example_hellojni_Test_Hello
  (JNIEnv * env, jobject obj){
	return (*(*env)).NewStringUTF(env,"HELLO,JNI....");
}
JNIEXPORT jstring JNICALL Java_com_example_hellojni_Test_HttpRequest
  (JNIEnv *, jobject);
