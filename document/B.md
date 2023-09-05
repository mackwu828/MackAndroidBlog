【InitMgr接口运行在子线程】
【ResMgr管理所有资源，包含图片和视频轮播及策略】
【DownloadMgr负责下载图片和视频，各自有一个下载队列，图片使用Glide下载，视频使用第三方Downloader下载】
【StorageMgr管理资源大小和统计的】
列表全部继承自ResListActivity、
数据库使用GreenDao,
核心实体类有NetRes这是资源未下载之前的数据载体，下载成功后转换成Res实体
设置Item全都是继承自SettingItemViewBuilder

主页轮播图片的逻辑？
配置Config、FrameConfig





图片的下载策略？
图片的存储策略？
数据库的设计？


## 相框接收图片流程
```
InitMgr#initHttpInfo 
    => InitMgr#requestToken 获取token 
        => InitMgr#requestStartupInfo 设备初始化
            => FrameConfig#updateStartupData 数据库保存设备初始化信息 
            => InitMgr#requestBindDeviceList 获取绑定的设备列表 => 数据库合并绑定的设备列表
            => InitMgr#updateFrameInfo 更新相册信息
            => InitMgr#initMqtt 初始化Mqtt
            
MqttService#init Mqtt初始化
    => MqttService#messageArrived Mqtt接收消息 
        => MqttService#onArriveMsg
            => MqttService#pingMqttServer 收到消息后通知服务端
            => MqttService#onMessage       
                => DOWNLOAD_RES 通知下载图片 => MattService#onDownloadMsg 下载图片
                => REVOKE_RES 通知撤回图片
                => DEVICE_BIND_SUCCESS 设备绑定成功

{"data":{"fileId":"10360595f9f0e540e7bcf44f811354c6da","fileSubject":"img_music.png",
"fileUrl":"http://cache.zeasn.tv/prod/whale-photo-api/file/2021/10360595f9f0e540e7bcf44f811354c6da.jpg",
"height":0,"mobileId":433680619860923112,"pivotX":0.5,"pivotY":0.5,"width":0},"msgType":2,"timeStamp":"2021-12-24 09:02:06.199"}
DOWNLOAD_RES => MattService#onDownloadMsg 通知下载图片
    => NetRes不存在
        => RevokeRes不存在
            => 
        => RevokeRes存在
            => RevokeRes#revokedRes
            => RevokeRes#notifyResRevoked
            => RevokeResDao#deleteInTx 删除撤回图片数据库数据
            => RevokeResNotifyServer#startNotify 更新撤回文件结果
    => NetRes存在
        => Res存在表示已经下载成功 => DownloadMgr#setFileSuccessState 更新文件状态
        => Res不存在表示正在下载，收到相同的图片。

REVOKE_RES 通知撤回图片
        
DownloadMgr


StorageMgr
ResMgr
```


## 数据库设计
```
Res: 下载完成的相片数据。
NetRes: 等待下载的相片数据。
RevokeRes: 等待撤回的相片数据。

User: 用户数据。包含用户名、性别、头像、bindDeviceMap已绑定的设备、groupList
Group: 
BindDevice: 绑定的设备。
FrameConfig: 
IMessage: 
```


## Mqtt没有设置自动重连，为啥还会自动重连？
```
2021-12-24 10:04:01.575 3091-3091/com.zeasn.frame D/Frame: MqttService prepareMqttClient connectionLost
2021-12-24 10:04:18.916 3091-3091/com.zeasn.frame D/Frame: MqttService connect
2021-12-24 10:04:21.295 3091-3091/com.zeasn.frame D/Frame: MqttService connect onSuccess 452542690526565320
2021-12-24 10:04:21.295 3091-3091/com.zeasn.frame D/Frame: MqttService subscribeTopics  prod/whale/client/disconnect
2021-12-24 10:04:21.296 3091-3091/com.zeasn.frame D/Frame: MqttService subscribeTopics  prod/whale/1/452542690526565320
2021-12-24 10:04:21.298 3091-3091/com.zeasn.frame D/Frame: MqttServiceconnectComplete 
2021-12-24 10:04:21.596 3091-3091/com.zeasn.frame D/Frame: MqttService subscribeTopics onSuccess prod/whale/client/disconnect
2021-12-24 10:04:21.904 3091-3091/com.zeasn.frame D/Frame: MqttService subscribeTopics onSuccess prod/whale/1/452542690526565320

MqttGuardService mqtt守护服务，判断mqtt断开，重启Mqtt服务
```



## 数据备份
```
备份数据库：data/data/com.zeasn.frame/databases/whale_photo.db
        => 外置sdcard/ZWhalePhoto/backup/TerminalId/DataTime/whale_photo.db

备份图片和视频数据：内置sdcard/ZWhalePhoto/resource/...
        => 外置sdcard/ZWhalePhoto/backup/TerminalId/DataTime/...

保存备份信息Json：BackupInfo 
        => 外置sdcard/ZWhalePhoto/backup/TerminalId/DataTime/BackupInfo.json
```



```




1. 集成ABI库的Launcher。
2. 集成ABI库单独做成一个apk。
3. 集成WWA库的Launcher。
```