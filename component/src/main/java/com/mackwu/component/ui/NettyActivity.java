package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityTestBinding;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.resolver.dns.DnsNameResolver;

/**
 * @author MackWu
 * @since 2023/5/29 16:27
 */
public class NettyActivity extends BaseActivity<BaseViewModel, ActivityTestBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    private void a() {
        EventLoopGroup eventExecutors = new NioEventLoopGroup(5);

        Bootstrap bootstrap = new Bootstrap();
        // 设置通道
        bootstrap.channel(NioSocketChannel.class);
        // 设置通道初始化
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // 向管道中添加自定义业务处理
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {

                    }
                });
            }
        });
        try {
            // 连接
            ChannelFuture channelFuture = bootstrap.connect("", 8080).sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
