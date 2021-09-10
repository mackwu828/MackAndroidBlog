package com.mackwu.component.bean;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/9/9 14:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * {
 * "pkg": "com.google.android.youtube.tv",
 * "className": "",
 * "data": {
 * "uri": "https://www.youtube.com/watch?v=V7zAymon84Q"
 * },
 * "flag": "268435456",
 * "action": "android.intent.action.VIEW",
 * "extra": [
 * {
 * "key": "force_fullscreen",
 * "value": "true"
 * }
 * ]
 * }
 */
public class DeeplinkDetail {

    private String pkg;
    private String className;
    private Data data;
    private int flag;
    private String action;
    private List<Extra> extra;

    public static class Data {
        private String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }
    }

    public static class Extra {
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Extra> getExtra() {
        return extra;
    }

    public void setExtra(List<Extra> extra) {
        this.extra = extra;
    }

//    public static void main(String[] args) {
//        String a = "{\"pkg\":\"com.google.android.youtube.tv\",\"className\":\"\",\"data\":{\"uri\":\"https://www.youtube.com/watch?v=V7zAymon84Q\"},\"flag\":\"268435456\",\"action\":\"android.intent.action.VIEW\",\"extra\":[{\"key\":\"force_fullscreen\",\"value\":\"true\"}]}";
//        Gson gson = new Gson();
//        DeepLinkInfo deepLinkInfo = gson.fromJson(a, DeepLinkInfo.class);
//        System.out.println(deepLinkInfo.toString());
//    }

}
