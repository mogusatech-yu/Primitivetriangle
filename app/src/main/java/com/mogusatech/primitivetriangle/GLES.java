package com.mogusatech.primitivetriangle;
import android.opengl.GLES20;

public class GLES {

    private final static String VERTEX_CODE=
        "attribute vec4 a_Position;"+
        "void main(){"+
            "gl_Position=a_Position;"+
        "}";

    private final static String FRAGMENT_CODE= 
        "precision mediump float;"+
        "uniform vec4 u_Color;"+
        "void main(){"+
            "gl_FragColor=u_Color;"+
        "}";

    private static int program;

    public static int positionHandle;
    public static int colorHandle;

    public static void makeProgram() {
        int vertexShader=loadShader(GLES20.GL_VERTEX_SHADER,VERTEX_CODE);
        int fragmentShader=loadShader(GLES20.GL_FRAGMENT_SHADER,FRAGMENT_CODE);

        program=GLES20.glCreateProgram();
        GLES20.glAttachShader(program,vertexShader);
        GLES20.glAttachShader(program,fragmentShader);
        GLES20.glLinkProgram(program);

        positionHandle=GLES20.glGetAttribLocation(program,"a_Position");
        colorHandle=GLES20.glGetUniformLocation(program,"u_Color");

        GLES20.glUseProgram(program);
    }

    private static int loadShader(int type,String shaderCode) {
        int shader=GLES20.glCreateShader(type); 
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}