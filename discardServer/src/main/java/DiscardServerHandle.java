import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty 第一个httpServer。丢弃任何的消息
 *
 * @author Jerry
 * @Date 2019/10/5 12:05 下午
 */
public class DiscardServerHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
        ctx.flush();

//        ByteBuf byteBuf = (ByteBuf)msg;
//        System.out.println(byteBuf.toString(CharsetUtil.US_ASCII));
//        //直接丢弃服务器
//        byteBuf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
