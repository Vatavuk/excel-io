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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Workbook.
 * @author Vedran Grgo Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface EWorkbook {

    /**
     * Describe object as a byte stream.
     * @return Byte array output stream
     * @throws IOException IOException
     */
    ByteArrayOutputStream asStream() throws IOException;

    /**
     * Describe object as a workbook.
     * @return Workbook
     */
    Workbook asWorkbook();

    /**
     * Save workbook to a file.
     * @param path Path
     * @throws IOException IOException
     */
    void saveTo(String path) throws IOException;

    /**
     * Add sheet to the workbook.
     * @param sheet Sheet
     * @return Workbook
     */
    EWorkbook with(ESheet sheet);

    /**
     * Add style to the workbook.
     * @param style Style
     * @return Workbook
     */
    EWorkbook with(Style style);
}
