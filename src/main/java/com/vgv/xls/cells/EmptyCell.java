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
package com.vgv.xls.cells;

import com.vgv.xls.ECell;
import com.vgv.xls.Style;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Empty cell.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class EmptyCell implements ECell {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Ctor.
     */
    public EmptyCell() {
        this(-1);
    }

    /**
     * Ctor.
     * @param column Cell position
     */
    public EmptyCell(final int column) {
        this.position = column;
    }

    @Override
    public Cell attachTo(final Row row) {
        Cell cell;
        if (this.position == -1) {
            cell = EmptyCell.createCell((int) row.getLastCellNum(), row);
        } else {
            cell = row.getCell(this.position - 1);
            if (cell == null) {
                cell = EmptyCell.createCell(this.position - 1, row);
            }
        }
        return cell;
    }

    @Override
    public ECell with(final Style style) {
        return new ECell.WithStyle(this, style);
    }

    /**
     * Create cell in given position.
     * @param position Position
     * @param row Row
     * @return Cell cell
     */
    private static Cell createCell(final int position, final Row row) {
        final int index;
        if (position < 0) {
            index = 0;
        } else {
            index = position;
        }
        return row.createCell(index);
    }
}
