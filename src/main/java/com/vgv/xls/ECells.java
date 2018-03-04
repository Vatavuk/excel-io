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

/**
 * ECells.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface ECells {

    /**
     * Describe object as array of cells.
     * @return Array of cells
     */
    Array<ECell> asArray();

    /**
     * Add style to cells.
     * @param style Style
     * @return Cell
     */
    ECells with(Style style);

    /**
     * Cells with specific style.
     */
    final class WithStyle implements ECells {

        /**
         * Origin cell.
         */
        private final ECells origin;

        /**
         * List of styles.
         */
        private final Array<Style> styles;

        /**
         * Ctor.
         * @param cells Cells
         * @param style Style
         */
        public WithStyle(final ECells cells, final Style style) {
            this(cells, new Array<>(style));
        }

        /**
         * Ctor.
         * @param cells Cells
         * @param styls Style
         */
        public WithStyle(final ECells cells, final Iterable<Style> styls) {
            this.origin = cells;
            this.styles = new Array<>(styls);
        }

        @Override
        public Array<ECell> asArray() {
            final List<ECell> cells = new ArrayList<>(0);
            for (final ECell cell: this.origin.asArray()) {
                ECell scell = cell;
                for (final Style style: this.styles) {
                    scell = cell.with(style);
                }
                cells.add(scell);
            }
            return new Array<>(cells);
        }

        @Override
        public ECells with(final Style style) {
            return new ECells.WithStyle(this.origin, this.styles.with(style));
        }
    }
}
