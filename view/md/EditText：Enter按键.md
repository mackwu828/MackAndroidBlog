
## 一、修改Enter按键样式
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


## 二、监听Enter按键
```
        etTest.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                
            }
            // true保留软键盘，false隐藏软键盘。
            return false;
        });
```