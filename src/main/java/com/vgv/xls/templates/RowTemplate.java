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
package com.vgv.xls.templates;

import com.vgv.xls.ECell;
import com.vgv.xls.ECells;
import com.vgv.xls.ERow;
import com.vgv.xls.Props;
import com.vgv.xls.Style;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Template to build custom rows.
 *
 *  <p>This is how you can use it:</p>
 *
 * <pre> class MyGoldRow extends RowTemplate {
 *      public MyGoldRow(final ERow row) {
 *          super(row.with(
 *              new XsStyle(
 *                  new ForegroundColor(IndexedColors.GOLD.getIndex()),
 *                  new FillPattern(FillPatternType.SOLID_FOREGROUND)
 *              )
 *          ));
 *      }
 *  }
 * </pre>
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RowTemplate implements ERow {

    /**
     * Origin row.
     */
    private final ERow origin;

    /**
     * Ctor.
     * @param row Row
     */
    public RowTemplate(final ERow row) {
        this.origin = row;
    }

    @Override
    public final Row attachTo(final Sheet sheet) {
        return this.origin.attachTo(sheet);
    }

    @Override
    public final ERow with(final Style style) {
        return this.origin.with(style);
    }

    @Override
    public final ERow with(final ECell... cells) {
        return this.origin.with(cells);
    }

    @Override
    public final ERow with(final ECell cell) {
        return this.origin.with(cell);
    }

    @Override
    public final ERow with(final ECells cells) {
        return this.origin.with(cells);
    }

    @Override
    public final ERow with(final Props<Row> props) {
        return this.origin.with(props);
    }
}
