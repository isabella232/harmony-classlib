/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 /**
 * @author Oleg V. Khaschansky
 */
/* DO NOT EDIT THIS FILE - it is machine generated */

#include "org_apache_harmony_awt_gl_opengl_GL.h"

static libHandler libGL;
static libHandler libGLU;
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_init (JNIEnv * env, jclass cls) {
    LOAD_LIB(libGL, GL);
    LOAD_LIB(libGLU, GLU);
    INIT_GL_GET_PROC_ADDRESS
}


void (__stdcall* p_nbridge_glVertexPointer) (int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glVertexPointer( JNIEnv *env, jobject self, jint size, jint type, jint stride, jlong pointer) {
    if (p_nbridge_glVertexPointer == NULL) {
        p_nbridge_glVertexPointer = (void (__stdcall*) (int, int, int, void *)) FindFunction(libGL, "glVertexPointer");
    }
    (* p_nbridge_glVertexPointer)((int) size, (int) type, (int) stride, (void *)(size_t) pointer);
}

void (__stdcall* p_nbridge_glPixelStoref) (int, float) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPixelStoref( JNIEnv *env, jobject self, jint pname, jfloat param) {
    if (p_nbridge_glPixelStoref == NULL) {
        p_nbridge_glPixelStoref = (void (__stdcall*) (int, float)) FindFunction(libGL, "glPixelStoref");
    }
    (* p_nbridge_glPixelStoref)((int) pname, (float) param);
}

void (__stdcall* p_nbridge_glRectd) (double, double, double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glRectd( JNIEnv *env, jobject self, jdouble x1, jdouble y1, jdouble x2, jdouble y2) {
    if (p_nbridge_glRectd == NULL) {
        p_nbridge_glRectd = (void (__stdcall*) (double, double, double, double)) FindFunction(libGL, "glRectd");
    }
    (* p_nbridge_glRectd)((double) x1, (double) y1, (double) x2, (double) y2);
}

void (__stdcall* p_nbridge_glTranslated) (double, double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTranslated( JNIEnv *env, jobject self, jdouble x, jdouble y, jdouble z) {
    if (p_nbridge_glTranslated == NULL) {
        p_nbridge_glTranslated = (void (__stdcall*) (double, double, double)) FindFunction(libGL, "glTranslated");
    }
    (* p_nbridge_glTranslated)((double) x, (double) y, (double) z);
}

void (__stdcall* p_nbridge_glLineStipple) (int, short) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glLineStipple( JNIEnv *env, jobject self, jint factor, jshort pattern) {
    if (p_nbridge_glLineStipple == NULL) {
        p_nbridge_glLineStipple = (void (__stdcall*) (int, short)) FindFunction(libGL, "glLineStipple");
    }
    (* p_nbridge_glLineStipple)((int) factor, (short) pattern);
}

void (__stdcall* p_nbridge_glBlendFunc) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glBlendFunc( JNIEnv *env, jobject self, jint sfactor, jint dfactor) {
    if (p_nbridge_glBlendFunc == NULL) {
        p_nbridge_glBlendFunc = (void (__stdcall*) (int, int)) FindFunction(libGL, "glBlendFunc");
    }
    (* p_nbridge_glBlendFunc)((int) sfactor, (int) dfactor);
}

void (__stdcall* p_nbridge_glColor4ubv) (void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glColor4ubv( JNIEnv *env, jobject self, jlong v) {
    if (p_nbridge_glColor4ubv == NULL) {
        p_nbridge_glColor4ubv = (void (__stdcall*) (void *)) FindFunction(libGL, "glColor4ubv");
    }
    (* p_nbridge_glColor4ubv)((void *)(size_t) v);
}

void (__stdcall* p_nbridge_glTexImage1D) (int, int, int, int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexImage1D( JNIEnv *env, jobject self, jint target, jint level, jint internalformat, jint width, jint border, jint format, jint type, jlong pixels) {
    if (p_nbridge_glTexImage1D == NULL) {
        p_nbridge_glTexImage1D = (void (__stdcall*) (int, int, int, int, int, int, int, void *)) FindFunction(libGL, "glTexImage1D");
    }
    (* p_nbridge_glTexImage1D)((int) target, (int) level, (int) internalformat, (int) width, (int) border, (int) format, (int) type, (void *)(size_t) pixels);
}

void (__stdcall* p_nbridge_gluOrtho2D) (double, double, double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_gluOrtho2D( JNIEnv *env, jobject self, jdouble left, jdouble right, jdouble bottom, jdouble top) {
    if (p_nbridge_gluOrtho2D == NULL) {
        p_nbridge_gluOrtho2D = (void (__stdcall*) (double, double, double, double)) FindFunction(libGLU, "gluOrtho2D");
    }
    (* p_nbridge_gluOrtho2D)((double) left, (double) right, (double) bottom, (double) top);
}

void (__stdcall* p_nbridge_glDrawArrays) (int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDrawArrays( JNIEnv *env, jobject self, jint mode, jint first, jint count) {
    if (p_nbridge_glDrawArrays == NULL) {
        p_nbridge_glDrawArrays = (void (__stdcall*) (int, int, int)) FindFunction(libGL, "glDrawArrays");
    }
    (* p_nbridge_glDrawArrays)((int) mode, (int) first, (int) count);
}

void (__stdcall* p_nbridge_glPixelStorei) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPixelStorei( JNIEnv *env, jobject self, jint pname, jint param) {
    if (p_nbridge_glPixelStorei == NULL) {
        p_nbridge_glPixelStorei = (void (__stdcall*) (int, int)) FindFunction(libGL, "glPixelStorei");
    }
    (* p_nbridge_glPixelStorei)((int) pname, (int) param);
}

void (__stdcall* p_nbridge_glTexParameteri) (int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexParameteri( JNIEnv *env, jobject self, jint target, jint pname, jint param) {
    if (p_nbridge_glTexParameteri == NULL) {
        p_nbridge_glTexParameteri = (void (__stdcall*) (int, int, int)) FindFunction(libGL, "glTexParameteri");
    }
    (* p_nbridge_glTexParameteri)((int) target, (int) pname, (int) param);
}

void (__stdcall* p_nbridge_glRotated) (double, double, double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glRotated( JNIEnv *env, jobject self, jdouble angle, jdouble x, jdouble y, jdouble z) {
    if (p_nbridge_glRotated == NULL) {
        p_nbridge_glRotated = (void (__stdcall*) (double, double, double, double)) FindFunction(libGL, "glRotated");
    }
    (* p_nbridge_glRotated)((double) angle, (double) x, (double) y, (double) z);
}

void (__stdcall* p_nbridge_glGetIntegerv) (int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glGetIntegerv( JNIEnv *env, jobject self, jint pname, jlong params) {
    if (p_nbridge_glGetIntegerv == NULL) {
        p_nbridge_glGetIntegerv = (void (__stdcall*) (int, void *)) FindFunction(libGL, "glGetIntegerv");
    }
    (* p_nbridge_glGetIntegerv)((int) pname, (void *)(size_t) params);
}

void (__stdcall* p_nbridge_glTexGendv) (int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexGendv( JNIEnv *env, jobject self, jint coord, jint pname, jlong params) {
    if (p_nbridge_glTexGendv == NULL) {
        p_nbridge_glTexGendv = (void (__stdcall*) (int, int, void *)) FindFunction(libGL, "glTexGendv");
    }
    (* p_nbridge_glTexGendv)((int) coord, (int) pname, (void *)(size_t) params);
}

void (__stdcall* p_nbridge_glTexCoord2d) (double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexCoord2d( JNIEnv *env, jobject self, jdouble s, jdouble t) {
    if (p_nbridge_glTexCoord2d == NULL) {
        p_nbridge_glTexCoord2d = (void (__stdcall*) (double, double)) FindFunction(libGL, "glTexCoord2d");
    }
    (* p_nbridge_glTexCoord2d)((double) s, (double) t);
}

void (__stdcall* p_nbridge_glPixelTransferf) (int, float) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPixelTransferf( JNIEnv *env, jobject self, jint pname, jfloat param) {
    if (p_nbridge_glPixelTransferf == NULL) {
        p_nbridge_glPixelTransferf = (void (__stdcall*) (int, float)) FindFunction(libGL, "glPixelTransferf");
    }
    (* p_nbridge_glPixelTransferf)((int) pname, (float) param);
}

void (__stdcall* p_nbridge_glCopyTexSubImage2D) (int, int, int, int, int, int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glCopyTexSubImage2D( JNIEnv *env, jobject self, jint target, jint level, jint xoffset, jint yoffset, jint x, jint y, jint width, jint height) {
    if (p_nbridge_glCopyTexSubImage2D == NULL) {
        p_nbridge_glCopyTexSubImage2D = (void (__stdcall*) (int, int, int, int, int, int, int, int)) FindFunction(libGL, "glCopyTexSubImage2D");
    }
    (* p_nbridge_glCopyTexSubImage2D)((int) target, (int) level, (int) xoffset, (int) yoffset, (int) x, (int) y, (int) width, (int) height);
}

void (__stdcall* p_nbridge_glEnd) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glEnd( JNIEnv *env, jobject self) {
    if (p_nbridge_glEnd == NULL) {
        p_nbridge_glEnd = (void (__stdcall*) (void)) FindFunction(libGL, "glEnd");
    }
    (* p_nbridge_glEnd)();
}

void (__stdcall* p_nbridge_glColorMask) (char, char, char, char) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glColorMask( JNIEnv *env, jobject self, jbyte red, jbyte green, jbyte blue, jbyte alpha) {
    if (p_nbridge_glColorMask == NULL) {
        p_nbridge_glColorMask = (void (__stdcall*) (char, char, char, char)) FindFunction(libGL, "glColorMask");
    }
    (* p_nbridge_glColorMask)((char) red, (char) green, (char) blue, (char) alpha);
}

void (__stdcall* p_nbridge_glFlush) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glFlush( JNIEnv *env, jobject self) {
    if (p_nbridge_glFlush == NULL) {
        p_nbridge_glFlush = (void (__stdcall*) (void)) FindFunction(libGL, "glFlush");
    }
    (* p_nbridge_glFlush)();
}

void (__stdcall* p_nbridge_glMatrixMode) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glMatrixMode( JNIEnv *env, jobject self, jint mode) {
    if (p_nbridge_glMatrixMode == NULL) {
        p_nbridge_glMatrixMode = (void (__stdcall*) (int)) FindFunction(libGL, "glMatrixMode");
    }
    (* p_nbridge_glMatrixMode)((int) mode);
}

void (__stdcall* p_nbridge_glTexEnvf) (int, int, float) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexEnvf( JNIEnv *env, jobject self, jint target, jint pname, jfloat param) {
    if (p_nbridge_glTexEnvf == NULL) {
        p_nbridge_glTexEnvf = (void (__stdcall*) (int, int, float)) FindFunction(libGL, "glTexEnvf");
    }
    (* p_nbridge_glTexEnvf)((int) target, (int) pname, (float) param);
}

void (__stdcall* p_nbridge_glPixelZoom) (float, float) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPixelZoom( JNIEnv *env, jobject self, jfloat xfactor, jfloat yfactor) {
    if (p_nbridge_glPixelZoom == NULL) {
        p_nbridge_glPixelZoom = (void (__stdcall*) (float, float)) FindFunction(libGL, "glPixelZoom");
    }
    (* p_nbridge_glPixelZoom)((float) xfactor, (float) yfactor);
}

void (__stdcall* p_nbridge_glScissor) (int, int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glScissor( JNIEnv *env, jobject self, jint x, jint y, jint width, jint height) {
    if (p_nbridge_glScissor == NULL) {
        p_nbridge_glScissor = (void (__stdcall*) (int, int, int, int)) FindFunction(libGL, "glScissor");
    }
    (* p_nbridge_glScissor)((int) x, (int) y, (int) width, (int) height);
}

unsigned int  (__stdcall* p_nbridge_glGetError) (void) = NULL;

JNIEXPORT jint  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glGetError( JNIEnv *env, jobject self) {
    if (p_nbridge_glGetError == NULL) {
        p_nbridge_glGetError = (unsigned int  (__stdcall*) (void)) FindFunction(libGL, "glGetError");
    }
    return (jint) (* p_nbridge_glGetError)();
}

void (__stdcall* p_nbridge_glDrawPixels) (int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDrawPixels( JNIEnv *env, jobject self, jint width, jint height, jint format, jint type, jlong pixels) {
    if (p_nbridge_glDrawPixels == NULL) {
        p_nbridge_glDrawPixels = (void (__stdcall*) (int, int, int, int, void *)) FindFunction(libGL, "glDrawPixels");
    }
    (* p_nbridge_glDrawPixels)((int) width, (int) height, (int) format, (int) type, (void *)(size_t) pixels);
}

void (__stdcall* p_nbridge_glCopyPixels) (int, int, int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glCopyPixels( JNIEnv *env, jobject self, jint x, jint y, jint width, jint height, jint type) {
    if (p_nbridge_glCopyPixels == NULL) {
        p_nbridge_glCopyPixels = (void (__stdcall*) (int, int, int, int, int)) FindFunction(libGL, "glCopyPixels");
    }
    (* p_nbridge_glCopyPixels)((int) x, (int) y, (int) width, (int) height, (int) type);
}

void (__stdcall* p_nbridge_glBlendFuncSeparate) (int, int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glBlendFuncSeparate( JNIEnv *env, jobject self, jint param_0, jint param_1, jint param_2, jint param_3) {
    if (p_nbridge_glBlendFuncSeparate == NULL) {
        p_nbridge_glBlendFuncSeparate = (void (__stdcall*) (int, int, int, int)) FindFunction(libGL, "glBlendFuncSeparate");
    }
    (* p_nbridge_glBlendFuncSeparate)((int) param_0, (int) param_1, (int) param_2, (int) param_3);
}

void (__stdcall* p_nbridge_glEnableClientState) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glEnableClientState( JNIEnv *env, jobject self, jint array) {
    if (p_nbridge_glEnableClientState == NULL) {
        p_nbridge_glEnableClientState = (void (__stdcall*) (int)) FindFunction(libGL, "glEnableClientState");
    }
    (* p_nbridge_glEnableClientState)((int) array);
}

void (__stdcall* p_nbridge_glMultMatrixd) (void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glMultMatrixd( JNIEnv *env, jobject self, jlong m) {
    if (p_nbridge_glMultMatrixd == NULL) {
        p_nbridge_glMultMatrixd = (void (__stdcall*) (void *)) FindFunction(libGL, "glMultMatrixd");
    }
    (* p_nbridge_glMultMatrixd)((void *)(size_t) m);
}

void (__stdcall* p_nbridge_glClear) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glClear( JNIEnv *env, jobject self, jint mask) {
    if (p_nbridge_glClear == NULL) {
        p_nbridge_glClear = (void (__stdcall*) (int)) FindFunction(libGL, "glClear");
    }
    (* p_nbridge_glClear)((int) mask);
}

void (__stdcall* p_nbridge_glPopMatrix) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPopMatrix( JNIEnv *env, jobject self) {
    if (p_nbridge_glPopMatrix == NULL) {
        p_nbridge_glPopMatrix = (void (__stdcall*) (void)) FindFunction(libGL, "glPopMatrix");
    }
    (* p_nbridge_glPopMatrix)();
}

void (__stdcall* p_nbridge_glColorPointer) (int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glColorPointer( JNIEnv *env, jobject self, jint size, jint type, jint stride, jlong pointer) {
    if (p_nbridge_glColorPointer == NULL) {
        p_nbridge_glColorPointer = (void (__stdcall*) (int, int, int, void *)) FindFunction(libGL, "glColorPointer");
    }
    (* p_nbridge_glColorPointer)((int) size, (int) type, (int) stride, (void *)(size_t) pointer);
}

void (__stdcall* p_nbridge_glScaled) (double, double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glScaled( JNIEnv *env, jobject self, jdouble x, jdouble y, jdouble z) {
    if (p_nbridge_glScaled == NULL) {
        p_nbridge_glScaled = (void (__stdcall*) (double, double, double)) FindFunction(libGL, "glScaled");
    }
    (* p_nbridge_glScaled)((double) x, (double) y, (double) z);
}

void (__stdcall* p_nbridge_glDisable) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDisable( JNIEnv *env, jobject self, jint cap) {
    if (p_nbridge_glDisable == NULL) {
        p_nbridge_glDisable = (void (__stdcall*) (int)) FindFunction(libGL, "glDisable");
    }
    (* p_nbridge_glDisable)((int) cap);
}

void (__stdcall* p_nbridge_glRasterPos2i) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glRasterPos2i( JNIEnv *env, jobject self, jint x, jint y) {
    if (p_nbridge_glRasterPos2i == NULL) {
        p_nbridge_glRasterPos2i = (void (__stdcall*) (int, int)) FindFunction(libGL, "glRasterPos2i");
    }
    (* p_nbridge_glRasterPos2i)((int) x, (int) y);
}

void (__stdcall* p_nbridge_glEnable) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glEnable( JNIEnv *env, jobject self, jint cap) {
    if (p_nbridge_glEnable == NULL) {
        p_nbridge_glEnable = (void (__stdcall*) (int)) FindFunction(libGL, "glEnable");
    }
    (* p_nbridge_glEnable)((int) cap);
}

void (__stdcall* p_nbridge_glBitmap) (int, int, float, float, float, float, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glBitmap( JNIEnv *env, jobject self, jint width, jint height, jfloat xorig, jfloat yorig, jfloat xmove, jfloat ymove, jlong bitmap) {
    if (p_nbridge_glBitmap == NULL) {
        p_nbridge_glBitmap = (void (__stdcall*) (int, int, float, float, float, float, void *)) FindFunction(libGL, "glBitmap");
    }
    (* p_nbridge_glBitmap)((int) width, (int) height, (float) xorig, (float) yorig, (float) xmove, (float) ymove, (void *)(size_t) bitmap);
}

void (__stdcall* p_nbridge_glColorTable) (int, int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glColorTable( JNIEnv *env, jobject self, jint param_0, jint param_1, jint param_2, jint param_3, jint param_4, jlong param_5) {
    if (p_nbridge_glColorTable == NULL) {
        p_nbridge_glColorTable = (void (__stdcall*) (int, int, int, int, int, void *)) FindFunction(libGL, "glColorTable");
    }
    (* p_nbridge_glColorTable)((int) param_0, (int) param_1, (int) param_2, (int) param_3, (int) param_4, (void *)(size_t) param_5);
}

void (__stdcall* p_nbridge_glGenTextures) (int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glGenTextures( JNIEnv *env, jobject self, jint n, jlong textures) {
    if (p_nbridge_glGenTextures == NULL) {
        p_nbridge_glGenTextures = (void (__stdcall*) (int, void *)) FindFunction(libGL, "glGenTextures");
    }
    (* p_nbridge_glGenTextures)((int) n, (void *)(size_t) textures);
}

void (__stdcall* p_nbridge_glFinish) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glFinish( JNIEnv *env, jobject self) {
    if (p_nbridge_glFinish == NULL) {
        p_nbridge_glFinish = (void (__stdcall*) (void)) FindFunction(libGL, "glFinish");
    }
    (* p_nbridge_glFinish)();
}

void (__stdcall* p_nbridge_glDeleteTextures) (int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDeleteTextures( JNIEnv *env, jobject self, jint n, jlong textures) {
    if (p_nbridge_glDeleteTextures == NULL) {
        p_nbridge_glDeleteTextures = (void (__stdcall*) (int, void *)) FindFunction(libGL, "glDeleteTextures");
    }
    (* p_nbridge_glDeleteTextures)((int) n, (void *)(size_t) textures);
}

void (__stdcall* p_nbridge_glDisableClientState) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDisableClientState( JNIEnv *env, jobject self, jint array) {
    if (p_nbridge_glDisableClientState == NULL) {
        p_nbridge_glDisableClientState = (void (__stdcall*) (int)) FindFunction(libGL, "glDisableClientState");
    }
    (* p_nbridge_glDisableClientState)((int) array);
}

void (__stdcall* p_nbridge_glBegin) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glBegin( JNIEnv *env, jobject self, jint mode) {
    if (p_nbridge_glBegin == NULL) {
        p_nbridge_glBegin = (void (__stdcall*) (int)) FindFunction(libGL, "glBegin");
    }
    (* p_nbridge_glBegin)((int) mode);
}

void (__stdcall* p_nbridge_glVertex2d) (double, double) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glVertex2d( JNIEnv *env, jobject self, jdouble x, jdouble y) {
    if (p_nbridge_glVertex2d == NULL) {
        p_nbridge_glVertex2d = (void (__stdcall*) (double, double)) FindFunction(libGL, "glVertex2d");
    }
    (* p_nbridge_glVertex2d)((double) x, (double) y);
}

void (__stdcall* p_nbridge_glLineWidth) (float) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glLineWidth( JNIEnv *env, jobject self, jfloat width) {
    if (p_nbridge_glLineWidth == NULL) {
        p_nbridge_glLineWidth = (void (__stdcall*) (float)) FindFunction(libGL, "glLineWidth");
    }
    (* p_nbridge_glLineWidth)((float) width);
}

void (__stdcall* p_nbridge_glLoadMatrixd) (void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glLoadMatrixd( JNIEnv *env, jobject self, jlong m) {
    if (p_nbridge_glLoadMatrixd == NULL) {
        p_nbridge_glLoadMatrixd = (void (__stdcall*) (void *)) FindFunction(libGL, "glLoadMatrixd");
    }
    (* p_nbridge_glLoadMatrixd)((void *)(size_t) m);
}

void (__stdcall* p_nbridge_glReadPixels) (int, int, int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glReadPixels( JNIEnv *env, jobject self, jint x, jint y, jint width, jint height, jint format, jint type, jlong pixels) {
    if (p_nbridge_glReadPixels == NULL) {
        p_nbridge_glReadPixels = (void (__stdcall*) (int, int, int, int, int, int, void *)) FindFunction(libGL, "glReadPixels");
    }
    (* p_nbridge_glReadPixels)((int) x, (int) y, (int) width, (int) height, (int) format, (int) type, (void *)(size_t) pixels);
}

void (__stdcall* p_nbridge_glBindTexture) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glBindTexture( JNIEnv *env, jobject self, jint target, jint texture) {
    if (p_nbridge_glBindTexture == NULL) {
        p_nbridge_glBindTexture = (void (__stdcall*) (int, int)) FindFunction(libGL, "glBindTexture");
    }
    (* p_nbridge_glBindTexture)((int) target, (int) texture);
}

void (__stdcall* p_nbridge_glPushMatrix) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPushMatrix( JNIEnv *env, jobject self) {
    if (p_nbridge_glPushMatrix == NULL) {
        p_nbridge_glPushMatrix = (void (__stdcall*) (void)) FindFunction(libGL, "glPushMatrix");
    }
    (* p_nbridge_glPushMatrix)();
}

void (__stdcall* p_nbridge_glTexGeni) (int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexGeni( JNIEnv *env, jobject self, jint coord, jint pname, jint param) {
    if (p_nbridge_glTexGeni == NULL) {
        p_nbridge_glTexGeni = (void (__stdcall*) (int, int, int)) FindFunction(libGL, "glTexGeni");
    }
    (* p_nbridge_glTexGeni)((int) coord, (int) pname, (int) param);
}

void (__stdcall* p_nbridge_glStencilFunc) (int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glStencilFunc( JNIEnv *env, jobject self, jint func, jint ref, jint mask) {
    if (p_nbridge_glStencilFunc == NULL) {
        p_nbridge_glStencilFunc = (void (__stdcall*) (int, int, int)) FindFunction(libGL, "glStencilFunc");
    }
    (* p_nbridge_glStencilFunc)((int) func, (int) ref, (int) mask);
}

void (__stdcall* p_nbridge_glTexImage2D) (int, int, int, int, int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexImage2D( JNIEnv *env, jobject self, jint target, jint level, jint internalformat, jint width, jint height, jint border, jint format, jint type, jlong pixels) {
    if (p_nbridge_glTexImage2D == NULL) {
        p_nbridge_glTexImage2D = (void (__stdcall*) (int, int, int, int, int, int, int, int, void *)) FindFunction(libGL, "glTexImage2D");
    }
    (* p_nbridge_glTexImage2D)((int) target, (int) level, (int) internalformat, (int) width, (int) height, (int) border, (int) format, (int) type, (void *)(size_t) pixels);
}

void (__stdcall* p_nbridge_glPixelMapusv) (int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glPixelMapusv( JNIEnv *env, jobject self, jint map, jint mapsize, jlong values) {
    if (p_nbridge_glPixelMapusv == NULL) {
        p_nbridge_glPixelMapusv = (void (__stdcall*) (int, int, void *)) FindFunction(libGL, "glPixelMapusv");
    }
    (* p_nbridge_glPixelMapusv)((int) map, (int) mapsize, (void *)(size_t) values);
}

void (__stdcall* p_nbridge_glViewport) (int, int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glViewport( JNIEnv *env, jobject self, jint x, jint y, jint width, jint height) {
    if (p_nbridge_glViewport == NULL) {
        p_nbridge_glViewport = (void (__stdcall*) (int, int, int, int)) FindFunction(libGL, "glViewport");
    }
    (* p_nbridge_glViewport)((int) x, (int) y, (int) width, (int) height);
}

void (__stdcall* p_nbridge_glLoadIdentity) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glLoadIdentity( JNIEnv *env, jobject self) {
    if (p_nbridge_glLoadIdentity == NULL) {
        p_nbridge_glLoadIdentity = (void (__stdcall*) (void)) FindFunction(libGL, "glLoadIdentity");
    }
    (* p_nbridge_glLoadIdentity)();
}

void (__stdcall* p_nbridge_glTexSubImage2D) (int, int, int, int, int, int, int, int, void *) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glTexSubImage2D( JNIEnv *env, jobject self, jint target, jint level, jint xoffset, jint yoffset, jint width, jint height, jint format, jint type, jlong pixels) {
    if (p_nbridge_glTexSubImage2D == NULL) {
        p_nbridge_glTexSubImage2D = (void (__stdcall*) (int, int, int, int, int, int, int, int, void *)) FindFunction(libGL, "glTexSubImage2D");
    }
    (* p_nbridge_glTexSubImage2D)((int) target, (int) level, (int) xoffset, (int) yoffset, (int) width, (int) height, (int) format, (int) type, (void *)(size_t) pixels);
}

void (__stdcall* p_nbridge_glDrawBuffer) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDrawBuffer( JNIEnv *env, jobject self, jint mode) {
    if (p_nbridge_glDrawBuffer == NULL) {
        p_nbridge_glDrawBuffer = (void (__stdcall*) (int)) FindFunction(libGL, "glDrawBuffer");
    }
    (* p_nbridge_glDrawBuffer)((int) mode);
}

void (__stdcall* p_nbridge_glReadBuffer) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glReadBuffer( JNIEnv *env, jobject self, jint mode) {
    if (p_nbridge_glReadBuffer == NULL) {
        p_nbridge_glReadBuffer = (void (__stdcall*) (int)) FindFunction(libGL, "glReadBuffer");
    }
    (* p_nbridge_glReadBuffer)((int) mode);
}

void (__stdcall* p_nbridge_glStencilOp) (int, int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glStencilOp( JNIEnv *env, jobject self, jint fail, jint zfail, jint zpass) {
    if (p_nbridge_glStencilOp == NULL) {
        p_nbridge_glStencilOp = (void (__stdcall*) (int, int, int)) FindFunction(libGL, "glStencilOp");
    }
    (* p_nbridge_glStencilOp)((int) fail, (int) zfail, (int) zpass);
}

unsigned int  (__stdcall* p_nbridge_glGenLists) (int) = NULL;

JNIEXPORT jint  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glGenLists( JNIEnv *env, jobject self, jint range) {
    if (p_nbridge_glGenLists == NULL) {
        p_nbridge_glGenLists = (unsigned int  (__stdcall*) (int)) FindFunction(libGL, "glGenLists");
    }
    return (jint) (* p_nbridge_glGenLists)((int) range);
}

unsigned char  (__stdcall* p_nbridge_glIsList) (int) = NULL;

JNIEXPORT jbyte  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glIsList( JNIEnv *env, jobject self, jint list) {
    if (p_nbridge_glIsList == NULL) {
        p_nbridge_glIsList = (unsigned char  (__stdcall*) (int)) FindFunction(libGL, "glIsList");
    }
    return (jbyte) (* p_nbridge_glIsList)((int) list);
}

void (* p_nbridge_glDeleteLists) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glDeleteLists( JNIEnv *env, jobject self, jint list, jint range) {
    if (p_nbridge_glDeleteLists == NULL) {
        p_nbridge_glDeleteLists = (void (*) (int, int)) FindFunction(libGL, "glDeleteLists");
    }
    (* p_nbridge_glDeleteLists)((int) list, (int) range);
}

void (__stdcall* p_nbridge_glNewList) (int, int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glNewList( JNIEnv *env, jobject self, jint list, jint mode) {
    if (p_nbridge_glNewList == NULL) {
        p_nbridge_glNewList = (void (__stdcall*) (int, int)) FindFunction(libGL, "glNewList");
    }
    (* p_nbridge_glNewList)((int) list, (int) mode);
}

void (* p_nbridge_glCallList) (int) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glCallList( JNIEnv *env, jobject self, jint list) {
    if (p_nbridge_glCallList == NULL) {
        p_nbridge_glCallList = (void (*) (int)) FindFunction(libGL, "glCallList");
    }
    (* p_nbridge_glCallList)((int) list);
}

void (__stdcall* p_nbridge_glEndList) (void) = NULL;

JNIEXPORT void  JNICALL Java_org_apache_harmony_awt_gl_opengl_GL_glEndList( JNIEnv *env, jobject self) {
    if (p_nbridge_glEndList == NULL) {
        p_nbridge_glEndList = (void (__stdcall*) (void)) FindFunction(libGL, "glEndList");
    }
    (* p_nbridge_glEndList)();
}

