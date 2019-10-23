package server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @author Jerry
 * @Date 2019/10/19 9:27 上午
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
             .addLast("decoder",new StringDecoder())
            .addLast("encoder",new StringDecoder())
            .addLast("handle",new ServerHander())
                ;
        System.out.println("SimpleChatClient:"+ch.remoteAddress() +"连接上");
    }
}
