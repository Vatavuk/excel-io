/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vedran Grgo Vatavuk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vgv.xls.styles;

import com.jcabi.immutable.Array;
import com.vgv.xls.ECellStyle;
import com.vgv.xls.Props;
import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.Font;

/**
 * Font.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FontStyle implements Props<ECellStyle> {

    /**
     * Font properties.
     */
    private final Array<Consumer<Font>> props;

    /**
     * Ctor.
     */
    public FontStyle() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param consumers Font properties
     */
    public FontStyle(final Iterable<Consumer<Font>> consumers) {
        this.props = new Array<>(consumers);
    }

    @Override
    public void accept(final ECellStyle style) {
        final Font font = style.workbook().createFont();
        this.props.forEach(
            prop -> prop.accept(font)
        );
        style.setFont(font);
    }

    /**
     * Font name.
     * @param name Name
     * @return FontStyle Font style
     */
    public FontStyle withName(final String name) {
        return new FontStyle(this.props.with(font -> font.setFontName(name)));
    }
}
