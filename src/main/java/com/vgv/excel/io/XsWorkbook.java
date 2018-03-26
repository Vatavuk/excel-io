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
package com.vgv.excel.io;

import com.jcabi.immutable.Array;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cactoos.Scalar;
import org.cactoos.scalar.IoCheckedScalar;

/**
 * Excel workbook.
 *
 * <p>This is how you can use it:</p>
 *
 * new XsWorkbook(
 * new XsSheet(
 * new XsRow().with(new TextCells("name", "email"))
 * )
 * )
 * ).saveTo("Test.xlsx");
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
public final class XsWorkbook implements EWorkbook {

    /**
     * Array of sheets.
     */
    private final Array<ESheet> sheets;

    /**
     * Workbook.
     */
    private final IoCheckedScalar<Workbook> workbook;

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final Iterable<ESheet> elements) {
        this(elements, XSSFWorkbook::new);
    }

    /**
     * Ctor.
     * @param elements Sheets
     */
    public XsWorkbook(final ESheet... elements) {
        this(new Array<>(elements), XSSFWorkbook::new);
    }

    /**
     * Ctor.
     * @param path File path
     */
    public XsWorkbook(final String path) {
        this(new File(path));
    }

    /**
     * Ctor.
     * @param file File
     */
    public XsWorkbook(final File file) {
        this(new Array<>(), () -> new XSSFWorkbook(new FileInputStream(file)));
    }

    /**
     * Ctor.
     * @param stream InputStream
     */
    public XsWorkbook(final InputStream stream) {
        this(new Array<>(), () -> new XSSFWorkbook(stream));
    }

    /**
     * Ctor.
     * @param elements Sheets
     * @param scalar Scalar
     */
    public XsWorkbook(final Iterable<ESheet> elements,
        final Scalar<Workbook> scalar) {
        this.sheets = new Array<>(elements);
        this.workbook = new IoCheckedScalar<>(scalar);
    }

    @Override
    public ByteArrayOutputStream asStream() throws IOException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (final Workbook wbook = this.workbook.value()) {
            this.attachSheets(wbook);
            wbook.write(stream);
        }
        return stream;
    }

    @Override
    public Workbook asWorkbook() throws IOException {
        final Workbook wbook = this.workbook.value();
        this.attachSheets(wbook);
        return wbook;
    }

    @Override
    public void saveTo(final String path) throws IOException {
        try (final FileOutputStream file = new FileOutputStream(path);
            final Workbook wbook = this.workbook.value()) {
            this.attachSheets(wbook);
            wbook.write(file);
            file.flush();
        }
    }

    @Override
    public EWorkbook with(final ESheet sheet) {
        return new XsWorkbook(this.sheets.with(sheet), this.workbook);
    }

    @Override
    public EWorkbook with(final Style style) {
        final List<ESheet> elements = new ArrayList<>(this.sheets.size());
        for (final ESheet sheet : this.sheets) {
            elements.add(sheet.with(style));
        }
        return new XsWorkbook(elements, this.workbook);
    }

    /**
     * Attach sheets.
     * @param wbook Workbook
     * @throws IOException If fails
     */
    private void attachSheets(final Workbook wbook) throws IOException {
        for (final ESheet sheet : this.sheets) {
            sheet.attachTo(wbook);
        }
    }
}
