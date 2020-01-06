
Activity是用户界面，负责和用户交互

## startActivity
```
startActivity(Intent(this, TargetActivity::class.java))
```


## startActivityForResult
重写onActivityResult，根据requestCode和resultCode做判断
```
    startActivityForResult(Intent(this, TargetActivity::class.java), 0x01)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            
        }
    }
```
目标activity设置resultCode，默认是Activity.RESULT_OK。如果不需要根据resultCode判断可以省略
```
setResult(Activity.RESULT_OK)
setResult(0x02)
```


## 启动Activity的最佳方式
在目标activity中定义一个静态方法，参数是context和要传入的参数
```
    companion object {
        fun start(context: Context, param: String){
            val intent = Intent(context, TargetActivity::class.java)
            intent.putExtra("param", param)
            context.startActivity(intent)
        }
    }
```