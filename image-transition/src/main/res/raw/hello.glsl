#version 100
//// transition of a simple fade.
//vec4 transition (vec2 uv) {
//    return mix(
//        getFromColor(uv),
//        getToColor(uv),
//        progress
//    );
//}
// 输入一个二维向量
// 返回表示源纹理和目标纹理混合颜色

uniform sampler2D fromTexture;
uniform sampler2D toTexture;

vec4 getFromColor(vec2 uv) {
    return texture(fromTexture, vec2(uv.x, 1.0 - uv.y));
}
vec4 getToColor(vec2 uv){
    return texture(fromTexture, vec2(uv.x, 1.0 - uv.y));
}


uniform float progress; // 进度。取值范围0.0-1.0



//vec4 transition(vec2 uv) {
//    return mix(
//        texture(u_texture0, uv),
//        texture(u_texture1, uv),
//        u_offset
//    );
//}

void main() {
}