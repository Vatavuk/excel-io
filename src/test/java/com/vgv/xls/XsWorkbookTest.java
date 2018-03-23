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
package com.vgv.xls;

import com.vgv.xls.cells.FormulaCell;
import com.vgv.xls.cells.NumberCells;
import com.vgv.xls.cells.TextCell;
import com.vgv.xls.cells.TextCells;
import com.vgv.xls.styles.FillPattern;
import com.vgv.xls.styles.ForegroundColor;
import java.io.IOException;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link XsWorkbook}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XsWorkbookTest {

    /**
     * Create workbook containing rows and cells.
     * @throws IOException If fails
     */
    @Test
    public void createsWorkbookWithRowsAndCells() throws IOException {
        final String text = "someText";
        final Workbook workbook = new XsWorkbook(
            new XsSheet(
                new XsRow(
                    new TextCell(text)
                )
            )
        ).asWorkbook();
        MatcherAssert.assertThat(
            workbook.getSheetAt(0).getRow(1)
                .getCell(0).getStringCellValue(),
            Matchers.containsString(text)
        );
    }

    /**
     * Create styled workbook.
     * @throws IOException If fails
     */
    @Test
    public void createsWorkbookWithStyles() throws IOException {
        final Workbook workbook = new XsWorkbook(
            new XsSheet(
                new XsRow(
                    new TextCell("text")
                )
            )
        ).with(
            new XsStyle(
                new ForegroundColor(
                    IndexedColors.GOLD.getIndex()
                )
            )
        ).asWorkbook();
        MatcherAssert.assertThat(
            workbook.getSheetAt(0).getRow(1)
                .getCell(0).getCellStyle().getFillForegroundColor(),
            Matchers.equalTo(IndexedColors.GOLD.getIndex())
        );
    }

    @Test
    public void tmp() throws Exception {
        new XsWorkbook(
            new XsSheet(
                new XsRow()
                    .with(new TextCells("name", "email", "salary", "bonus", "total"))
                    .with(
                        new XsStyle(
                            new ForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()),
                            new FillPattern(FillPatternType.SOLID_FOREGROUND)
                        )
                    ),
                new XsRow()
                    .with(new TextCells("Steve Hook", "steve.hook@gmail.com"))
                    .with(new NumberCells(160000.0, 35337.6))
                    .with(new FormulaCell("SUM(C3:D3)")
                        .with(
                            new XsStyle(
                                new ForegroundColor(IndexedColors.RED.getIndex()),
                                new FillPattern(FillPatternType.SOLID_FOREGROUND)
                            )
                        )
                    )
                    //.with(new XsProps<>(new Height((short) 20)))
            )
        ).saveTo("Test.xlsx");
    }
}
