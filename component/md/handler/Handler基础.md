

## 在主线程创建Handler，在子线程发送消息
```
    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    
                }
            }
        }
    }

    override fun initView() {
        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            handler.sendEmptyMessage(1)
        }.start()
    }
```