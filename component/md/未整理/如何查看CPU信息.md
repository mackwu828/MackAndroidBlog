
## adb命令
```
adb shell cat /proc/cpuinfo
```

## cpu信息
```
processor       : 0
model name      : ARMv7 Processor rev 0 (v7l)
BogoMIPS        : 54.00
Features        : half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt vfpd32 lpae evtstrm aes pmull sha1 sha2 crc32
CPU implementer : 0x41
CPU architecture: 7
CPU variant     : 0x1
CPU part        : 0xd05
CPU revision    : 0
```

- processor: 表示第几个核。
- BogoMIPS: 伪MIPS，用于测量CPU速度
- Features: 表示当前CPU所支持的特性，比如neon，vfp等。
- CPU architecture: 7表示arm-v7，8表示arm-v8。