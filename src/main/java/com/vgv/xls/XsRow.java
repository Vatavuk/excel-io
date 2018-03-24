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
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Row in an excel table.
 *
 * <p>This is how you can use it:</p>
 *
 * new XsRow(new TextCell("txt"), new NumberCell(2))
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class XsRow implements ERow {

    /**
     * Row position.
     */
    private final int index;

    /**
     * Array of cells.
     */
    private final Array<ECell> cells;

    /**
     * Ctor.
     * @param elements Cells
     */
    public XsRow(final ECell... elements) {
        this(new Array<>(elements));
    }

    /**
     * Ctor.
     * @param position Row position
     * @param elements Cells
     */
    public XsRow(final int position, final ECell... elements) {
        this(position, new Array<>(elements));
    }

    /**
     * Ctor.
     * @param elements Cells
     */
    public XsRow(final Iterable<ECell> elements) {
        this(-1, new Array<>(elements));
    }

    /**
     * Ctor.
     * @param position Row position
     * @param elements Cells
     */
    public XsRow(final int position, final Iterable<ECell> elements) {
        this.index = position;
        this.cells = new Array<>(elements);
    }

    @Override
    public Row attachTo(final Sheet sheet) {
        final Row row = this.createRow(sheet);
        for (final ECell cell : this.cells) {
            cell.attachTo(row);
        }
        return row;
    }

    @Override
    public ERow with(final Style style) {
        final List<ECell> elements = new ArrayList<>(this.cells.size());
        for (final ECell cell : this.cells) {
            elements.add(cell.with(style));
        }
        return new XsRow(elements);
    }

    @Override
    public ERow with(final ECell... elements) {
        return new XsRow(this.cells.with(new Array<>(elements)));
    }

    @Override
    public ERow with(final ECells elements) {
        return new XsRow(this.cells.with(elements.asArray()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public XsRow.WithProps with(final Props<Row> props) {
        return new XsRow.WithProps(this, props);
    }

    /**
     * Create new row.
     * @param sheet Sheet
     * @return Row Row
     */
    private Row createRow(final Sheet sheet) {
        Row row;
        if (this.index == -1) {
            final int num = sheet.getLastRowNum();
            row = sheet.createRow(num);
            sheet.createRow(num + 1);
        } else {
            row = sheet.getRow(this.index - 1);
            if (row == null) {
                row = sheet.createRow(this.index - 1);
            }
        }
        return row;
    }

    /**
     * Row with additional properties.
     */
    public static final class WithProps implements ERow {

        /**
         * Row origin.
         */
        private final ERow origin;

        /**
         * Properties.
         */
        private final Props<Row> props;

        /**
         * Ctor.
         * @param row Row
         * @param properties Properties
         */
        public WithProps(final ERow row, final Props<Row> properties) {
            this.origin = row;
            this.props = properties;
        }

        @Override
        public Row attachTo(final Sheet sheet) {
            final Row row = this.origin.attachTo(sheet);
            this.props.accept(row);
            return row;
        }

        @Override
        public ERow with(final Style style) {
            return this.origin.with(style);
        }

        @Override
        public ERow with(final ECell... cells) {
            return this.origin.with(cells);
        }

        @Override
        public ERow with(final ECells cells) {
            return this.origin.with(cells);
        }

        @Override
        public ERow with(final Props<Row> properties) {
            return this;
        }
    }
}
