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
import com.vgv.excel.io.cells.TextCells;
import com.vgv.excel.io.styles.ForegroundColor;
import java.io.IOException;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link XsRow}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XsRowTest {

    /**
     * Add row to a sheet.
     * @throws IOException If fails
     */
    @Test
    @SuppressWarnings("PMD.AvoidUsingShortType")
    public void addsRowToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int expected = 4;
            final Row row =
                new XsRow()
                    .with(new TextCell("someText"))
                    .with(new TextCells("a", "b", "c"))
                    .attachTo(wbook.createSheet());
            MatcherAssert.assertThat(
                row.getLastCellNum(),
                Matchers.equalTo((short) expected)
            );
        }
    }

    /**
     * Add styled row to a sheet.
     * @throws IOException If fails
     */
    @Test
    public void addsRowWithStyleToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Row row =
                new XsRow()
                    .with(new TextCells("a", "b", "c"))
                    .with(
                        new TextCell("text")
                            .with(
                                new XsStyle(
                                    new ForegroundColor(
                                        IndexedColors.GOLD.getIndex()
                                    )
                                )
                            )
                    )
                    .with(
                        new XsStyle(
                            new ForegroundColor(
                                IndexedColors.GREY_50_PERCENT.getIndex()
                            )
                        )
                    )
                    .attachTo(
                        wbook.createSheet()
                    );
            MatcherAssert.assertThat(
                row.getCell((int) row.getLastCellNum() - 2)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_50_PERCENT.getIndex())
            );
            MatcherAssert.assertThat(
                row.getCell((int) row.getLastCellNum() - 1)
                    .getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GOLD.getIndex())
            );
        }
    }

    /**
     * Add row to specific position in sheet.
     * @throws IOException If fails
     */
    @Test
    public void addsRowWithAbsolutePositionToSheet() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final int position = 2;
            final int expected = 1;
            final Row row = new XsRow(
                position,
                new TextCell("textPos")
            ).attachTo(wbook.createSheet());
            MatcherAssert.assertThat(
                row.getRowNum(),
                Matchers.equalTo(expected)
            );
        }
    }
}
