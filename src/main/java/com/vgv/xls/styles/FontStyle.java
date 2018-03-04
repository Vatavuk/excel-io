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
import com.vgv.xls.Props;
import java.util.function.Consumer;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * Font.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.2
 */
@SuppressWarnings({"PMD.AvoidUsingShortType", "PMD.TooManyMethods"})
public final class FontStyle implements Props<CellStyle> {

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
    public void accept(final CellStyle style) {
        final Font font = ((XSSFCellStyle) style).getFont();
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

    /**
     * Font height.
     * @param height Height
     * @return FontStyle Font style
     */
    public FontStyle withHeight(final short height) {
        return new FontStyle(
            this.props.with(font -> font.setFontHeight(height))
        );
    }

    /**
     * Font height in points.
     * @param height Height
     * @return FontStyle Font style
     */
    public FontStyle withHeightInPoints(final short height) {
        return new FontStyle(
            this.props.with(font -> font.setFontHeightInPoints(height))
        );
    }

    /**
     * Italic font.
     * @return FontStyle Font style
     */
    public FontStyle withItalic() {
        return new FontStyle(
            this.props.with(font -> font.setItalic(true))
        );
    }

    /**
     * Strike out.
     * @return FontStyle Font style
     */
    public FontStyle withStrikeOut() {
        return new FontStyle(
            this.props.with(font -> font.setStrikeout(true))
        );
    }

    /**
     * Font color.
     * Usage example:
     * withColor(XSSFColor.RED.index)
     * @param color Color
     * @return FontStyle Font style
     */
    public FontStyle withColor(final short color) {
        return new FontStyle(
            this.props.with(font -> font.setColor(color))
        );
    }

    /**
     * Font offset.
     * Available values:
     * 0: BASELINE
     * 1: SUPERSCRIPT
     * 2: SUBSCRIPT
     * @param offset Offset
     * @return FontStyle Font style
     */
    public FontStyle withTypeOffset(final short offset) {
        return new FontStyle(
            this.props.with(font -> font.setTypeOffset(offset))
        );
    }

    /**
     * Font underline.
     * Usage example:
     * withUnderline(FontUnderline.SINGLE.getByteValue())
     * @param underline Underline
     * @return FontStyle Font style
     */
    public FontStyle withUnderline(final byte underline) {
        return new FontStyle(
            this.props.with(font -> font.setUnderline(underline))
        );
    }

    /**
     * Charset.
     * Usage example:
     * withCharset(Font.ANSI_CHARSET)
     * @param charset Cahrset
     * @return FontStyle Font style
     */
    public FontStyle withCharset(final byte charset) {
        return new FontStyle(
            this.props.with(font -> font.setCharSet(charset))
        );
    }

    /**
     * Charset.
     * Usage example:
     * withCharset(0) -> ANSI
     *
     * See {@link org.apache.poi.common.usermodel.fonts.FontCharset}
     * for more examples
     * @param charset Cahrset
     * @return FontStyle Font style
     */
    public FontStyle withCharset(final int charset) {
        return new FontStyle(
            this.props.with(font -> font.setCharSet(charset))
        );
    }

    /**
     * Bold font.
     * @return FontStyle Font style
     */
    public FontStyle withBold() {
        return new FontStyle(
            this.props.with(font -> font.setBold(true))
        );
    }
}
