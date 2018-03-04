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

import com.vgv.xls.cells.TextCell;
import com.vgv.xls.styles.FillPattern;
import com.vgv.xls.styles.ForegroundColor;
import com.vgv.xls.templates.CellTemplate;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link CellTemplateTest}.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class CellTemplateTest {

    /**
     * Create custom cell.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomCell() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Cell cell = new CellTemplateTest.GrayTextCell()
                .attachTo(workbook.createSheet().createRow(0));
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillForegroundColor(),
                Matchers.equalTo(IndexedColors.GREY_25_PERCENT.getIndex())
            );
        }
    }

    /**
     * Create custom cell with additional style.
     * @throws IOException If fails
     */
    @Test
    public void createsCustomCellWithStyle() throws IOException {
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Cell cell = new CellTemplateTest.GrayTextCell()
                .with(
                    new XsStyle(
                        new FillPattern(FillPatternType.SOLID_FOREGROUND)
                    )
                )
                .attachTo(workbook.createSheet().createRow(0));
            MatcherAssert.assertThat(
                cell.getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
        }
    }

    /**
     * Custom Gray cell.
     */
    private static final class GrayTextCell extends CellTemplate {

        /**
         * Ctor.
         */
        GrayTextCell() {
            super(
                new TextCell("someText")
                    .with(
                        new XsStyle(
                            new ForegroundColor(
                                IndexedColors.GREY_25_PERCENT.getIndex()
                            )
                        )
                    )
            );
        }
    }
}
