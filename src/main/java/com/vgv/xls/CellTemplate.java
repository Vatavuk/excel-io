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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Template to build custom cells.
 *
 * <p>This is how you're supposed to use it:</p>
 *
 * <pre> class MyGoldCell extends CellTemplate {
 *      public MyGoldCell(final ECell cell) {
 *          super(cell.with(
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
public class CellTemplate implements ECell {

    /**
     * Origin cell.
     */
    private final ECell origin;

    /**
     * Ctor.
     * @param cell Cell
     */
    public CellTemplate(final ECell cell) {
        this.origin = cell;
    }

    @Override
    public final Cell attachTo(final Row row) {
        return this.origin.attachTo(row);
    }

    @Override
    public final ECell with(final Style style) {
        return this.origin.with(style);
    }
}
