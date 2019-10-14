import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Jerry
 * @Date 2019/10/11 10:43 下午
 */
public class TimerServer {
    private int port;
    TimerServer(int port){
        this.port = port;
    }

    public static void main(String[] args) {
        int port;
        if (args.length>0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8080;
        }

        new TimerServer(port).start();
    }

    private void start() {
        EventLoopGroup serverGoup = new NioEventLoopGroup();
        EventLoopGroup clientGoup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(serverGoup,clientGoup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new TimerEncoder(),new  TimerServerHandle());
                    }
                })
                .option(ChannelOption.SO_BACKLOG,128)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                ;
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            serverGoup.shutdownGracefully();
            clientGoup.shutdownGracefully();
        }

    }
}
