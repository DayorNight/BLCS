package blcs.lwb.lwbtool.utils.svg;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 调用方法如下：
 RequestBuilder<PictureDrawable> requestBuilder = Glide.with(this)
 .as(PictureDrawable.class)
 .transition(withCrossFade())
 .listener(new SvgSoftwareLayerSetter());
 requestBuilder.load(CodeUrl).into(iv_register_code);

 加载svg图片
 */
public class SvgDecoder implements ResourceDecoder<InputStream, SVG> {

    @Override
    public boolean handles(InputStream source, Options options) {
        // TODO: Can we tell?
        return true;
    }

    public Resource<SVG> decode(InputStream source, int width, int height,
                                Options options)
            throws IOException {
        try {
            SVG svg = SVG.getFromInputStream(source);
            svg.setDocumentWidth(width);
            svg.setDocumentHeight(height);
            return new SimpleResource<>(svg);
        } catch (SVGParseException ex) {
            throw new IOException("Cannot load SVG from stream", ex);
        }
    }
}