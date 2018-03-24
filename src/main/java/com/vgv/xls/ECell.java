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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Cell.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("PMD.AvoidUsingShortType")
public interface ECell {

    /**
     * Attach cell to a row.
     * @param row Row
     * @return Cell
     */
    Cell attachTo(Row row);

    /**
     * Add style to the cell.
     * @param style Style
     * @return Cell
     */
    ECell with(Style style);

    /**
     * Cell that contains specific style.
     */
    @SuppressWarnings("PMD.LooseCoupling")
    final class WithStyle implements ECell {

        /**
         * Cell.
         */
        private final ECell origin;

        /**
         * List of styles.
         */
        private final LinkedList<Style> styles;

        /**
         * Ctor.
         * @param cell Cell
         * @param style Style
         */
        public WithStyle(final ECell cell, final Style style) {
            this(cell, new LinkedList<>(Collections.singletonList(style)));
        }

        /**
         * Ctor.
         * @param cell Cell
         * @param styls Styles
         */
        public WithStyle(final ECell cell, final Collection<Style> styls) {
            this.origin = cell;
            this.styles = new LinkedList<>(styls);
        }

        @Override
        public Cell attachTo(final Row row) {
            final Cell cell = this.origin.attachTo(row);
            cell.setCellStyle(row.getSheet().getWorkbook().createCellStyle());
            for (final Style style : this.styles) {
                style.attachTo(cell);
            }
            return cell;
        }

        @Override
        public ECell with(final Style style) {
            this.styles.addFirst(style);
            return new ECell.WithStyle(this, this.styles);
        }
    }
}
