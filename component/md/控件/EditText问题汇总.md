

## 如何监听Action按钮？
一、修改Enter按键样式
```
    <EditText
        android:id="@+id/et_test"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:imeOptions="actionSearch" />
```

```
imeOptions=”actionUnspecified” –> EditorInfo.IME_ACTION_UNSPECIFIED
imeOptions=”actionNone” –> EditorInfo.IME_ACTION_NONE
imeOptions=”actionGo” –> EditorInfo.IME_ACTION_GO
imeOptions=”actionSearch” –> EditorInfo.IME_ACTION_SEARCH
imeOptions=”actionSend” –> EditorInfo.IME_ACTION_SEND
imeOptions=”actionNext” –> EditorInfo.IME_ACTION_NEXT
imeOptions=”actionDone” –> EditorInfo.IME_ACTION_DONE
imeOptions=”actionPrevious” –> EditorInfo.IME_ACTION_PREVIOUS
```
二、监听Enter按键
```
        etTest.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                
            }
            // true保留软键盘，false隐藏软键盘。
            return false;
        });
```


## 如何隐藏和显示软键盘？
显示软键盘
```
    public static void showSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        }
    }
```
隐藏软键盘
```
    public static void hideSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

```
切换软件盘。如果软键盘没有显示，则显示；如果已经显示，则隐藏。
```
    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


## 如何隐藏和显示密码？