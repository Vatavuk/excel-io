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

import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test for {@link FontStyle}.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.3
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class FontStyleTest {

    /**
     * Add new font style.
     * @throws IOException If fails
     */
    @Test
    public void addNewFont() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final String name = "TimesNewRoman";
            final short height = 11;
            final short color = 128;
            final short offset = 0;
            final int ansi = 0;
            final CellStyle style = wbook.createCellStyle();
            new FontStyle()
                .withName(name)
                .withHeight(height)
                .withItalic()
                .withStrikeOut()
                .withColor(color)
                .withTypeOffset(offset)
                .withUnderline(FontUnderline.SINGLE.getByteValue())
                .withCharset(Font.ANSI_CHARSET)
                .withCharset(ansi)
                .withBold()
                .accept(style);
            final Font font = wbook.getFontAt(style.getFontIndex());
            MatcherAssert.assertThat(
                font.getFontName(),
                Matchers.equalTo(name)
            );
            MatcherAssert.assertThat(
                font.getFontHeight(),
                Matchers.equalTo(height)
            );
            MatcherAssert.assertThat(
                font.getItalic(),
                Matchers.equalTo(true)
            );
            MatcherAssert.assertThat(
                font.getStrikeout(),
                Matchers.equalTo(true)
            );
            MatcherAssert.assertThat(
                font.getColor(),
                Matchers.equalTo(color)
            );
            MatcherAssert.assertThat(
                font.getTypeOffset(),
                Matchers.equalTo(offset)
            );
            MatcherAssert.assertThat(
                font.getUnderline(),
                Matchers.equalTo(FontUnderline.SINGLE.getByteValue())
            );
            MatcherAssert.assertThat(
                font.getCharSet(),
                Matchers.equalTo(ansi)
            );
            MatcherAssert.assertThat(
                font.getBold(),
                Matchers.equalTo(true)
            );
        }
    }
}
