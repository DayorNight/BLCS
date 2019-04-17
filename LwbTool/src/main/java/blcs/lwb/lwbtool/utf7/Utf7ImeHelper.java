/*
 *    Copyright 2013 TOYAMA Sumio <jun.nama@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ----
 * getBytes(), encode() and allocateMore() are modifications based on
 * work copyrighted and licensed as follows:
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package blcs.lwb.lwbtool.utf7;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/**
 * Utf7ImeHelper provides a simple Modified UTF-7 encoder. <br/>
 * If you use it in uiautomator, you can write simply as follows:
 * 
 * <pre>
 * ....
 * 
 * UiObject editText = ...; 
 * editText.setText(Utf7ImeHelper.e("こんにちは")); // any Unicode String
 * 
 * ....
 * </pre>
 * 
 * @author TOYAMA Sumio
 * 
 */
public class Utf7ImeHelper {

    private static final Charset CHARSET_MODIFIED_UTF7 = new CharsetProvider().charsetForName("X-MODIFIED-UTF-7");

    /**
     * Encodes the specified text into modified UTF-7.
     * 
     * @param text
     *            plain unicode text
     * @return encoded text in modified UTF-7.
     */
    public static String e(String text) {
        byte[] encoded = getBytes(text, CHARSET_MODIFIED_UTF7);
        return new String(encoded, Charset.forName("US-ASCII"));
    }

    /**
     * Returns a new byte array containing the characters of the specified
     * string encoded using the given charset.
     * 
     * It is equivalent to <code>input.getBytes(charset)</code> except it has
     * workaround for the bug ID 61917.
     * 
     */
    //@formatter:off
    /*
     * The original code is available from
     *     https://android.googlesource.com/platform/libcore/+/android-4.4_r1.2/libdvm/src/main/java/java/lang/String.java
     */
    //@formatter:on
    public static byte[] getBytes(String input, Charset charset) {
        CharBuffer chars = CharBuffer.wrap(input.toCharArray());
        // @formatter:off
        CharsetEncoder encoder = charset.newEncoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);
        // @formatter:on
        ByteBuffer buffer;
        buffer = encode(chars.asReadOnlyBuffer(), encoder);
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;

    }

    //@formatter:off
    /*
     * The original code is available from
     *     https://android.googlesource.com/platform/libcore/+/android-4.4_r1.2/luni/src/main/java/java/nio/charset/CharsetEncoder.java
     */
    //@formatter:on
    private static ByteBuffer encode(CharBuffer in, CharsetEncoder encoder) {
        int length = (int) (in.remaining() * (double) encoder.averageBytesPerChar());
        ByteBuffer out = ByteBuffer.allocate(length);

        encoder.reset();
        CoderResult flushResult = null;

        while (flushResult != CoderResult.UNDERFLOW) {
            CoderResult encodeResult = encoder.encode(in, out, true);
            if (encodeResult == CoderResult.OVERFLOW) {
                out = allocateMore(out);
                continue;
            }

            flushResult = encoder.flush(out);
            if (flushResult == CoderResult.OVERFLOW) {
                out = allocateMore(out);
            }
        }

        out.flip();
        return out;
    }

    //@formatter:off
    /*
     * The original code is available from
     *     https://android.googlesource.com/platform/libcore/+/master/luni/src/main/java/java/nio/charset/CharsetEncoder.java
     */
    //@formatter:on
    private static ByteBuffer allocateMore(ByteBuffer output) {
        if (output.capacity() == 0) {
            return ByteBuffer.allocate(1);
        }
        ByteBuffer result = ByteBuffer.allocate(output.capacity() * 2);
        output.flip();
        result.put(output);
        return result;
    }

}
