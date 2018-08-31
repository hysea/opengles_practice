package com.hysea.opengles_practice;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TutorialPartOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tutorial_part_one);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        GLSurfaceView surfaceView = new GLSurfaceView(this);
        surfaceView.setRenderer(new OpenGLRenderer());
        setContentView(surfaceView);
    }


    private class OpenGLRenderer implements GLSurfaceView.Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // Called when the surface is created or recreated
            // 主要用来设置一些绘制时不常变化的参数，比如：背景色，是否打开z-buffer等
            gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
            gl.glShadeModel(GL10.GL_SMOOTH);
            gl.glClearDepthf(1.0f);
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glDepthFunc(GL10.GL_LEQUAL);
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            // Called when the surface changed size.
            // 如果设备支持屏幕横向和纵向切换，这个方法将发生在横向 <-> 纵向互换时。此时可以重新设置绘制的纵横比率。
            gl.glViewport(0, 0, width, height);// OpenGL docs.
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 45.0f,
                    (float) width / (float) height,
                    0.1f, 100.0f);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            // Called to draw the current frame.
            // 定义实际的绘图操作
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        }
    }
}
