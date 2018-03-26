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
package com.vgv.excel.io;

import com.vgv.excel.io.cells.TextCell;
import com.vgv.excel.io.styles.ForegroundColor;
import java.io.IOException;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link XsSheet}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XsSheetTest {

    /**
     * Add sheet to a workbook.
     * @throws IOException IOException
     */
    @Test
    public void addsSheetToWorkbook() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final int expected = 1;
            final String title = "testTitle";
            final Sheet sheet = new XsSheet(title)
                .with(new XsRow())
                .attachTo(workbook);
            MatcherAssert.assertThat(
                sheet.getLastRowNum(),
                Matchers.equalTo(expected)
            );
            MatcherAssert.assertThat(
                sheet.getSheetName(),
                Matchers.equalTo(title)
            );
        }
    }

    /**
     * Add styled sheet to a workbook.
     * @throws IOException IOException
     */
    @Test
    public void addsSheetWithStyleToWorkbook() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Sheet sheet = new XsSheet(new XsRow(new TextCell("text")))
                .with(
                    new XsStyle(
                        new ForegroundColor(
                            IndexedColors.GOLD.getIndex()
                        )
                    )
                )
                .attachTo(workbook);
            MatcherAssert.assertThat(
                sheet.getRow(0).getCell(0)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GOLD.getIndex())
            );
        }
    }
}
