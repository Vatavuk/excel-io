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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * Cell style which can be applied to cell/row/sheet/workbook.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XsStyle implements Style {

    /**
     * Array of style properties.
     */
    private final Array<Props<CellStyle>> properties;

    /**
     * Ctor.
     */
    public XsStyle() {
        this(new Array<>());
    }

    /**
     * Ctor.
     * @param props Properties
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public XsStyle(final Props<CellStyle>... props) {
        this(new Array<>(props));
    }

    /**
     * Ctor.
     * @param props Properties
     */
    public XsStyle(final Iterable<Props<CellStyle>> props) {
        this.properties = new Array<>(props);
    }

    @Override
    public CellStyle attachTo(final Cell cell) {
        final CellStyle style = cell.getCellStyle();
        for (final Props<CellStyle> property : this.properties) {
            property.accept(style);
        }
        return style;
    }

    @Override
    public Style with(final Props<CellStyle> element) {
        return new XsStyle(this.properties.with(element));
    }
}
