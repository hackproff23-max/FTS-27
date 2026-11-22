package com.firsttouchgames.fts15;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class GLRender implements android.opengl.GLSurfaceView.Renderer, GLSurfaceView.Renderer {
    @Override // android.opengl.GLSurfaceView.Renderer, com.firsttouchgames.fts15.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl) {
        JNI.step();
    }

    @Override // android.opengl.GLSurfaceView.Renderer, com.firsttouchgames.fts15.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        try {
            Thread.sleep(750L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (height > width) {
            JNI.SetScreen(height, width);
        } else {
            JNI.SetScreen(width, height);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer, com.firsttouchgames.fts15.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        JNI.SetScreen(0, 0);
        gl.glClear(0);
    }

    public void onSurfaceDestroyed(GL10 gl, EGLConfig config) {
    }

    @Override // com.firsttouchgames.fts15.GLSurfaceView.Renderer
    public void onSurfaceLost() {
    }
}
