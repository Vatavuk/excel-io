package com.vgv.xls.style;

import com.vgv.xls.XsCellStyle;
import com.vgv.xls.styles.FontStyle;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FontStyleTest {

    @Test
    public void addNewFont() throws IOException {
        try (final Workbook wbook = new XSSFWorkbook()) {
            final String name = "TimesNewRoman";
            final CellStyle style = new XsCellStyle(wbook);
            new FontStyle()
                .withName(name)
                .accept(style);
            MatcherAssert.assertThat(
                wbook.getFontAt(style.getFontIndex()).getFontName(),
                Matchers.equalTo(name)
            );
        }
    }
}
