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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * XsSheet.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XsSheet implements ESheet {

    /**
     * Array of rows.
     */
    private final Array<ERow> rows;

    /**
     * Ctor.
     * @param elements Rows
     */
    public XsSheet(final ERow... elements) {
        this(new Array<>(elements));
    }

    /**
     * Ctor.
     * @param elements Rows
     */
    public XsSheet(final Iterable<ERow> elements) {
        this.rows = new Array<>(elements);
    }

    @Override
    public Sheet attachTo(final Workbook workbook) {
        final Sheet sheet = workbook.createSheet();
        for (final ERow row : this.rows) {
            row.attachTo(sheet);
        }
        return sheet;
    }

    @Override
    public ESheet with(final ERow element) {
        return new XsSheet(this.rows.with(element));
    }

    @Override
    public ESheet with(final Style style) {
        final List<ERow> elements = new ArrayList<>(this.rows.size());
        for (final ERow row : this.rows) {
            elements.add(row.with(style));
        }
        return new XsSheet(elements);
    }
}
