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
package com.vgv.excel.io.props.sheet;

import com.vgv.excel.io.Props;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Auto size column.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class AutoSizeColumn implements Props<Sheet> {

    /**
     * Column.
     */
    private final int value;

    /**
     * Are cells to be merged.
     */
    private final boolean ismerged;

    /**
     * Ctor.
     * @param column Column
     */
    public AutoSizeColumn(final int column) {
        this(column, false);
    }

    /**
     * Ctor.
     * @param column Column
     * @param merged Is cells to be merged
     */
    public AutoSizeColumn(final int column, final boolean merged) {
        this.value = column;
        this.ismerged = merged;
    }

    @Override
    public void accept(final Sheet sheet) {
        sheet.autoSizeColumn(this.value, this.ismerged);
    }
}
