import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/10/14 16:46
 * @description:
 **/
public class TimerDecode extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()<4){
            return;
        }
        out.add(new UnixTime(in.readUnsignedInt()));
    }
}
