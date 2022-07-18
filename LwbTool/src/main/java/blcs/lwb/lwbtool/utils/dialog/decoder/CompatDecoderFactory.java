package blcs.lwb.lwbtool.utils.dialog.decoder;


/**
 * @param <T> The base type of the decoder this factory will produce.
 *            <p>
 *            Compatibility factory to instantiate decoders with empty public constructors.
 * @author vondear
 */
public class CompatDecoderFactory<T> implements DecoderFactory<T> {
    private final Class<? extends T> clazz;

    public CompatDecoderFactory(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T make() throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
