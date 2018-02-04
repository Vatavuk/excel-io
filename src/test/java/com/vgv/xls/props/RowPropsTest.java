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
package com.vgv.xls.props;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test cases for {@link Height}, {@link HeightInPoints}, {@link ZeroHeight}.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public final class RowPropsTest {

    /**
     * Set row height.
     * @throws IOException If fails
     */
    @Test
    public void setsRowHeight() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final short height = (short) 10;
            final Row row = wbook.createSheet().createRow(0);
            new Height(height).accept(row);
            MatcherAssert.assertThat(
                row.getHeight(),
                Matchers.equalTo(height)
            );
        }
    }

    /**
     * Set row height in points.
     * @throws IOException If fails
     */
    @Test
    public void setsRowHightInPoints() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final float points = 10.0F;
            final Row row = wbook.createSheet().createRow(0);
            new HeightInPoints(points).accept(row);
            MatcherAssert.assertThat(
                row.getHeightInPoints(),
                Matchers.equalTo(points)
            );
        }
    }

    /**
     * Set row zero height.
     * @throws IOException If fails
     */
    @Test
    public void setsZeroHeight() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final boolean zero = true;
            final Row row = wbook.createSheet().createRow(0);
            new ZeroHeight(zero).accept(row);
            MatcherAssert.assertThat(
                row.getZeroHeight(),
                Matchers.equalTo(zero)
            );
        }
    }
}
