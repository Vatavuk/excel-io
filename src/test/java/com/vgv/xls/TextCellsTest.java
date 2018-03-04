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

import com.jcabi.immutable.Array;
import com.vgv.xls.styles.FillPattern;
import java.io.IOException;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Test case for {@link TextCells}.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TextCellsTest {

    /**
     * Create multiple cells containing text values.
     */
    @Test
    public void createsMultipleTextCells() {
        final int expected = 3;
        final Array<ECell> cells = new TextCells("a", "b", "c")
            .asArray();
        MatcherAssert.assertThat(
            cells.size(),
            Matchers.equalTo(expected)
        );
        MatcherAssert.assertThat(
            cells.get(0),
            Matchers.instanceOf(TextCell.class)
        );
    }

    /**
     * Create multiple cells containing text values and specific style.
     * @throws IOException If fails
     */
    @Test
    public void createsMultipleTextCellsWithStyle() throws IOException {
        final Array<ECell> cells = new TextCells("a", "b", "c")
            .with(
                new XsStyle(
                    new FillPattern(FillPatternType.SOLID_FOREGROUND)
                )
            ).asArray();
        try (final Workbook wbook = new XSSFWorkbook()) {
            final Row row = wbook.createSheet().createRow(0);
            for (final ECell cell : cells) {
                cell.attachTo(row);
            }
            MatcherAssert.assertThat(
                row.getCell(0).getCellStyle().getFillPatternEnum(),
                Matchers.equalTo(FillPatternType.SOLID_FOREGROUND)
            );
        }
    }
}
