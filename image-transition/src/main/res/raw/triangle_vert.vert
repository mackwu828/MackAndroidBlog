#version 100

attribute vec4 a_Position;
uniform vec2 u_Translate;
void main() {
    mat4 translareMat = mat4(1.0, 0.0, 0.0, 0.0,
                             0.0, 1.0, 0.0, 0.0,
                             0.0, 0.0, 1.0, 0.0,
                             u_Translate.x, u_Translate.y, 0.0, 1.0);
    gl_Position = a_Position;
}