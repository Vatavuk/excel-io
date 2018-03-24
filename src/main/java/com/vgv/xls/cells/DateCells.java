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

import com.jcabi.immutable.Array;
import com.vgv.xls.ECell;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Multiple cells with date values.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.3
 */
public final class DateCells extends AbstractStyleableCells {

    /**
     * Cell position.
     */
    private final int position;

    /**
     * Array of date values.
     */
    private final Array<Date> dates;

    /**
     * Ctor.
     * @param values Values
     */
    public DateCells(final Date... values) {
        this(new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param values Values
     */
    public DateCells(final int column, final Date... values) {
        this(column, new Array<>(values));
    }

    /**
     * Ctor.
     * @param values Values
     */
    public DateCells(final Iterable<Date> values) {
        this(-1, new Array<>(values));
    }

    /**
     * Ctor.
     * @param column Position of cells
     * @param values Values
     */
    public DateCells(final int column, final Iterable<Date> values) {
        super();
        this.position = column;
        this.dates = new Array<>(values);
    }

    @Override
    public Array<ECell> asArray() {
        return new Array<>(this.dates.stream()
            .map(date -> new DateCell(this.position, date))
            .collect(Collectors.toList())
        );
    }
}
