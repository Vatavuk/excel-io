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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Expanded CellStyle interface.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface ECellStyle extends CellStyle {

    /**
     * Workbook.
     * @return Workbook
     */
    Workbook workbook();

    /**
     * Default implementation.
     */
    class Default implements ECellStyle {

        /**
         * CellStyle origin.
         */
        private final CellStyle origin;

        /**
         * Workbook.
         */
        private final Workbook workbook;

        /**
         * Ctor.
         * @param wbook Workbook
         */
        public Default(final Workbook wbook) {
            this(wbook, wbook.createCellStyle());
        }

        /**
         * Ctor.
         * @param wbook Workbook
         * @param cstyle CellStyle
         */
        public Default(final Workbook wbook, final CellStyle cstyle) {
            this.origin = cstyle;
            this.workbook = wbook;
        }

        @Override
        public short getIndex() {
            return this.origin.getIndex();
        }

        @Override
        public void setDataFormat(final short fmt) {
            this.origin.setDataFormat(fmt);
        }

        @Override
        public short getDataFormat() {
            throw new UnsupportedOperationException("#getDataFormat()");
        }

        @Override
        public String getDataFormatString() {
            throw new UnsupportedOperationException("#getDataFormatString()");
        }

        @Override
        public void setFont(final Font font) {
            this.origin.setFont(font);
        }

        @Override
        public short getFontIndex() {
            return this.origin.getFontIndex();
        }

        @Override
        public void setHidden(final boolean hidden) {
            throw new UnsupportedOperationException("#setHidden()");
        }

        @Override
        public boolean getHidden() {
            throw new UnsupportedOperationException("#getHidden()");
        }

        @Override
        public void setLocked(final boolean locked) {
            throw new UnsupportedOperationException("#setLocked()");
        }

        @Override
        public boolean getLocked() {
            throw new UnsupportedOperationException("#getLocked()");
        }

        @Override
        public void setQuotePrefixed(final boolean prefixed) {
            throw new UnsupportedOperationException("#setQuotePrefixed()");
        }

        @Override
        public boolean getQuotePrefixed() {
            throw new UnsupportedOperationException("#getQuotePrefixed()");
        }

        @Override
        public void setAlignment(final HorizontalAlignment align) {
            throw new UnsupportedOperationException("#setAlignment()");
        }

        @Override
        @Deprecated
        public short getAlignment() {
            throw new UnsupportedOperationException("#getAlignment()");
        }

        @Override
        public HorizontalAlignment getAlignmentEnum() {
            throw new UnsupportedOperationException("#getAlignmentEnum()");
        }

        @Override
        public void setWrapText(final boolean wrapped) {
            throw new UnsupportedOperationException("#setWrapText()");
        }

        @Override
        public boolean getWrapText() {
            throw new UnsupportedOperationException("#getWrapText()");
        }

        @Override
        public void setVerticalAlignment(final VerticalAlignment align) {
            throw new UnsupportedOperationException("#setVerticalAlignment()");
        }

        @Override
        @Deprecated
        public short getVerticalAlignment() {
            throw new UnsupportedOperationException("#getVerticalAlignment()");
        }

        @Override
        public VerticalAlignment getVerticalAlignmentEnum() {
            throw new UnsupportedOperationException("#getVerticalAlignmentEnum()");
        }

        @Override
        public void setRotation(final short rotation) {
            throw new UnsupportedOperationException("#setRotation()");
        }

        @Override
        public short getRotation() {
            throw new UnsupportedOperationException("#getRotation()");
        }

        @Override
        public void setIndention(final short indent) {
            throw new UnsupportedOperationException("#setIndention()");
        }

        @Override
        public short getIndention() {
            throw new UnsupportedOperationException("#getIndention()");
        }

        @Override
        public void setBorderLeft(final BorderStyle border) {
            throw new UnsupportedOperationException("#setBorderLeft()");
        }

        @Override
        @Deprecated
        public short getBorderLeft() {
            throw new UnsupportedOperationException("#getBorderLeft()");
        }

        @Override
        public BorderStyle getBorderLeftEnum() {
            throw new UnsupportedOperationException("#getBorderLeftEnum()");
        }

        @Override
        public void setBorderRight(final BorderStyle border) {
            throw new UnsupportedOperationException("#setBorderRight()");
        }

        @Override
        @Deprecated
        public short getBorderRight() {
            throw new UnsupportedOperationException("#getBorderRight()");
        }

        @Override
        public BorderStyle getBorderRightEnum() {
            throw new UnsupportedOperationException("#getBorderRightEnum()");
        }

        @Override
        public void setBorderTop(final BorderStyle border) {
            throw new UnsupportedOperationException("#setBorderTop()");
        }

        @Override
        @Deprecated
        public short getBorderTop() {
            throw new UnsupportedOperationException("#getBorderTop()");
        }

        @Override
        public BorderStyle getBorderTopEnum() {
            throw new UnsupportedOperationException("#getBorderTopEnum()");
        }

        @Override
        public void setBorderBottom(final BorderStyle border) {
            throw new UnsupportedOperationException("#setBorderBottom()");
        }

        @Override
        @Deprecated
        public short getBorderBottom() {
            throw new UnsupportedOperationException("#getBorderBottom()");
        }

        @Override
        public BorderStyle getBorderBottomEnum() {
            throw new UnsupportedOperationException("#getBorderBottomEnum()");
        }

        @Override
        public void setLeftBorderColor(final short color) {
            throw new UnsupportedOperationException("#setLeftBorderColor()");
        }

        @Override
        public short getLeftBorderColor() {
            throw new UnsupportedOperationException("#getLeftBorderColor()");
        }

        @Override
        public void setRightBorderColor(final short color) {
            throw new UnsupportedOperationException("#setRightBorderColor()");
        }

        @Override
        public short getRightBorderColor() {
            throw new UnsupportedOperationException("#getRightBorderColor()");
        }

        @Override
        public void setTopBorderColor(final short color) {
            throw new UnsupportedOperationException("#setTopBorderColor()");
        }

        @Override
        public short getTopBorderColor() {
            throw new UnsupportedOperationException("#getTopBorderColor()");
        }

        @Override
        public void setBottomBorderColor(final short color) {
            throw new UnsupportedOperationException("#setBottomBorderColor()");
        }

        @Override
        public short getBottomBorderColor() {
            throw new UnsupportedOperationException("#getBottomBorderColor()");
        }

        @Override
        public void setFillPattern(final FillPatternType fp) {
            throw new UnsupportedOperationException("#setFillPattern()");
        }

        @Override
        @Deprecated
        public short getFillPattern() {
            throw new UnsupportedOperationException("#getFillPattern()");
        }

        @Override
        public FillPatternType getFillPatternEnum() {
            throw new UnsupportedOperationException("#getFillPatternEnum()");
        }

        @Override
        public void setFillBackgroundColor(final short bgc) {
            throw new UnsupportedOperationException("#setFillBackgroundColor()");
        }

        @Override
        public short getFillBackgroundColor() {
            throw new UnsupportedOperationException("#getFillBackgroundColor()");
        }

        @Override
        public Color getFillBackgroundColorColor() {
            throw new UnsupportedOperationException("#getFillBackgroundColorColor()");
        }

        @Override
        public void setFillForegroundColor(final short bgc) {
            throw new UnsupportedOperationException("#setFillForegroundColor()");
        }

        @Override
        public short getFillForegroundColor() {
            throw new UnsupportedOperationException("#getFillForegroundColor()");
        }

        @Override
        public Color getFillForegroundColorColor() {
            throw new UnsupportedOperationException("#getFillForegroundColorColor()");
        }

        @Override
        public void cloneStyleFrom(final CellStyle source) {
            throw new UnsupportedOperationException("#cloneStyleFrom()");
        }

        @Override
        public void setShrinkToFit(final boolean shrink) {
            throw new UnsupportedOperationException("#setShrinkToFit()");
        }

        @Override
        public boolean getShrinkToFit() {
            throw new UnsupportedOperationException("#getShrinkToFit()");
        }

        @Override
        public Workbook workbook() {
            return this.workbook;
        }
    }
}
