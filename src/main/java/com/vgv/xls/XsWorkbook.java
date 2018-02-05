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
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * XsWorkbook.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XsWorkbook implements EWorkbook {

    /**
     * Array of sheets.
     */
    private final Array<ESheet> sheets;

    /**
     * Workbook.
     */
    private final Workbook workbook;

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final Iterable<ESheet> elements) {
        this(elements, new XSSFWorkbook());
    }

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final ESheet... elements) {
        this(new Array<>(elements), new XSSFWorkbook());
    }

    /**
     * Ctor.
     * @param elements Sheets
     * @param wbook Workbook
     */
    public XsWorkbook(final Iterable<ESheet> elements, final Workbook wbook) {
        this.sheets = new Array<>(elements);
        this.workbook = wbook;
    }

    @Override
    public ByteArrayOutputStream asStream() throws IOException {
        this.attachSheets();
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.workbook.write(stream);
        return stream;
    }

    @Override
    public Workbook asWorkbook() {
        this.attachSheets();
        return this.workbook;
    }

    @Override
    public void saveTo(final String path) throws IOException {
        this.attachSheets();
        try (final FileOutputStream file = new FileOutputStream(path)) {
            this.workbook.write(file);
            file.flush();
        }
    }

    @Override
    public EWorkbook with(final ESheet sheet) {
        return new XsWorkbook(this.sheets.with(sheet));
    }

    @Override
    public EWorkbook with(final Style style) {
        final List<ESheet> elements = new ArrayList<>(this.sheets.size());
        for (final ESheet sheet : this.sheets) {
            elements.add(sheet.with(style));
        }
        return new XsWorkbook(elements);
    }

    /**
     * Attach sheets to workbook.
     */
    private void attachSheets() {
        for (final ESheet sheet : this.sheets) {
            sheet.attachTo(this.workbook);
        }
    }
}
