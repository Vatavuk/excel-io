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
 * Sheet in an excel table.
 *
 * <p>This is how you should use it:</p>
 *
 *     new XsSheet(
 *        new XsRow()
 *           .with(new TextCells("name", "email"))
 *        )
 *     )
 *
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class XsSheet implements ESheet {

    /**
     * Array of rows.
     */
    private final Array<ERow> rows;

    /**
     * Sheet name.
     */
    private final String name;

    /**
     * Ctor.
     * @param title Title
     */
    public XsSheet(final String title) {
        this(title, new Array<>());
    }

    /**
     * Ctor.
     * @param elements Elements
     */
    public XsSheet(final ERow... elements) {
        this("", new Array<>(elements));
    }

    /**
     * Ctor.
     * @param title Title
     * @param elements Elements
     */
    public XsSheet(final String title, final ERow... elements) {
        this(title, new Array<>(elements));
    }

    /**
     * Ctor.
     * @param title Title
     * @param elements Elements
     */
    public XsSheet(final String title, final Iterable<ERow> elements) {
        this.name = title;
        this.rows = new Array<>(elements);
    }

    @Override
    public Sheet attachTo(final Workbook workbook) {
        final Sheet sheet;
        if (this.name.isEmpty()) {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(this.name);
        }
        for (final ERow row : this.rows) {
            row.attachTo(sheet);
        }
        return sheet;
    }

    @Override
    public ESheet with(final ERow element) {
        return new XsSheet(this.name, this.rows.with(element));
    }

    @Override
    public ESheet with(final Style style) {
        final List<ERow> elements = new ArrayList<>(this.rows.size());
        for (final ERow row : this.rows) {
            elements.add(row.with(style));
        }
        return new XsSheet(this.name, elements);
    }

    /**
     * Sheet with additional properties.
     * @param props Properties
     * @return WithProps WithProps
     */
    public XsSheet.WithProps with(final Props<Sheet> props) {
        return new XsSheet.WithProps(this, props);
    }

    /**
     * Sheet with additional properties.
     */
    public static final class WithProps implements ESheet {

        /**
         * Sheet origin.
         */
        private final ESheet origin;

        /**
         * Properties.
         */
        private final Props<Sheet> props;

        /**
         * Ctor.
         * @param sheet Sheet
         * @param properties Properties
         */
        public WithProps(final ESheet sheet, final Props<Sheet> properties) {
            this.origin = sheet;
            this.props = properties;
        }

        @Override
        public Sheet attachTo(final Workbook workbook) {
            final Sheet sheet = this.origin.attachTo(workbook);
            this.props.accept(sheet);
            return sheet;
        }

        @Override
        public ESheet with(final ERow row) {
            return this.origin.with(row);
        }

        @Override
        public ESheet with(final Style style) {
            return this.origin.with(style);
        }
    }
}
