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
package com.vgv.excel.io.cells;

import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link DateCell}.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.3
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class DateCellTest {

    @Test
    public void addsCellContainingDateToRow() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Date date = new Date();
            final Cell cell = new DateCell(date).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getDateCellValue(),
                Matchers.equalTo(date)
            );
        }
    }

    @Test
    public void addsCellContainingDateWithPosition() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int position = 2;
            final int expected = 1;
            final Date date = new Date();
            final Cell cell = new DateCell(position, date).attachTo(
                workbook.createSheet().createRow(0)
            );
            MatcherAssert.assertThat(
                cell.getColumnIndex(),
                Matchers.equalTo(expected)
            );
        }
    }
}
