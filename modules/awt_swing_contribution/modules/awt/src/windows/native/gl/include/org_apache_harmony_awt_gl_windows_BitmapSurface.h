/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
 * @author Alexey A. Petrenko
 * @version $Revision$
 */
/*
 * THE FILE HAS BEEN AUTOGENERATED BY INTEL IJH TOOL.
 * Please be aware that all changes made to this file manually
 * will be overwritten by the tool if it runs again.
 */

#include <jni.h>


/* Header for class org.apache.harmony.awt.gl.windows.BitmapSurface */

#ifndef _ORG_APACHE_HARMONY_AWT_GL_WINDOWS_BITMAPSURFACE_H
#define _ORG_APACHE_HARMONY_AWT_GL_WINDOWS_BITMAPSURFACE_H

#ifdef __cplusplus
extern "C" {
#endif


/* Static final fields */

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_sRGB_CS
#define org_apache_harmony_awt_gl_windows_BitmapSurface_sRGB_CS 1L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_Linear_RGB_CS
#define org_apache_harmony_awt_gl_windows_BitmapSurface_Linear_RGB_CS 2L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_Linear_Gray_CS
#define org_apache_harmony_awt_gl_windows_BitmapSurface_Linear_Gray_CS 3L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_Custom_CS
#define org_apache_harmony_awt_gl_windows_BitmapSurface_Custom_CS 0L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_SPPSM
#define org_apache_harmony_awt_gl_windows_BitmapSurface_SPPSM 1L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_MPPSM
#define org_apache_harmony_awt_gl_windows_BitmapSurface_MPPSM 2L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_CSM
#define org_apache_harmony_awt_gl_windows_BitmapSurface_CSM 3L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_PISM
#define org_apache_harmony_awt_gl_windows_BitmapSurface_PISM 4L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_BSM
#define org_apache_harmony_awt_gl_windows_BitmapSurface_BSM 5L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_ALPHA_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_ALPHA_MASK -16777216L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_RED_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_RED_MASK 16711680L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_MASK 65280L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_MASK 255L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_RED_BGR_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_RED_BGR_MASK 255L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_BGR_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_BGR_MASK 65280L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_BGR_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_BGR_MASK 16711680L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_RED_565_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_RED_565_MASK 63488L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_565_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_565_MASK 2016L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_565_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_565_MASK 31L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_RED_555_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_RED_555_MASK 31744L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_555_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_GREEN_555_MASK 992L

#undef org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_555_MASK
#define org_apache_harmony_awt_gl_windows_BitmapSurface_BLUE_555_MASK 31L


/* Native methods */

/*
 * Method: org.apache.harmony.awt.gl.windows.BitmapSurface.dispose(J)V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_awt_gl_windows_BitmapSurface_dispose(JNIEnv *, jobject, 
    jlong);

/*
 * Method: org.apache.harmony.awt.gl.windows.BitmapSurface.createSurfData(JII)J
 */
JNIEXPORT jlong JNICALL
Java_org_apache_harmony_awt_gl_windows_BitmapSurface_createSurfData(JNIEnv *, jobject, 
    jlong, jint, jint);


#ifdef __cplusplus
}
#endif

#endif /* _ORG_APACHE_HARMONY_AWT_GL_WINDOWS_BITMAPSURFACE_H */

