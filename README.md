# Excel-io
### Java excel library (Apache POI inside)
<img src="https://png.icons8.com/color/100/000000/ms-excel.png">

[![EO principles respected here](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/Vatavuk/excel-io)](http://www.rultor.com/p/Vatavuk/excel-io)

[![Build Status](https://travis-ci.org/Vatavuk/excel-io.svg?branch=master)](https://travis-ci.org/Vatavuk/excel-io)
[![Javadocs](http://javadoc.io/badge/hr.com.vgv/excel-io.svg)](http://javadoc.io/doc/hr.com.vgv/excel-io)
[![Maven Central](https://img.shields.io/maven-central/v/hr.com.vgv/excel-io.svg)](https://maven-badges.herokuapp.com/maven-central/hr.com.vgv/excel-io)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Vatavuk/excel-io/blob/master/LICENSE.txt)

[![Test Coverage](https://codecov.io/gh/Vatavuk/excel-io/branch/master/graph/badge.svg)](https://codecov.io/gh/Vatavuk/excel-io)
[![SonarQube](https://img.shields.io/badge/sonar-ok-green.svg)](https://sonarcloud.io/dashboard/index/hr.com.vgv:excel-io)

This is an object-oriented java library for reading and writing Microsoft Office Excel spreadsheets.
It is a wrapper around Apache POI that provides elegant and user friendly interface for creating Excel documents. 

**Note**.
This is work in progress. Some styles and properties are missing.
I will be regularly committing more and more functionality. Feel free to contribute.


**How to use**.
Latest version [here](https://github.com/Vatavuk/excel-io/releases)

```xml
<dependency>
    <groupId>hr.com.vgv</groupId>
    <artifactId>excel-io</artifactId>
</dependency>
```

Java version required: 1.8+.


## Create spreadsheet
```java
new XsWorkbook(
    new XsSheet(
        new XsRow()
            .with(new TextCells("name", "email", "salary", "bonus", "total"))
            .with(
                new XsStyle(
                    new ForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()),
                    new FillPattern(FillPatternType.SOLID_FOREGROUND)
                )
            ),
        new XsRow()
            .with(new TextCells("Steve Hook", "steve.hook@gmail.com"))
            .with(new NumberCells(160000.0, 35337.6))
            .with(new FormulaCell("SUM(C3:D3)")
                .with(
                    new XsStyle(
                        new ForegroundColor(IndexedColors.RED.getIndex()),
                        new FillPattern(FillPatternType.SOLID_FOREGROUND)
                    )
                )
            )
            .with(new XsProps<>(new Height((short) 500)))
    )
).saveTo("Test.xlsx");
```
This is how the result looks like:
<img src="https://i.imgur.com/3hUJkJ2.png"/>

## Read and modify spreadsheet
Read from "Test.xlsx" file and modify cell int the second row/first column.
```java
new XsWorkbook("Test.xlsx")
    .with(new XsSheet.ReadFrom(0)
        .with(
            new XsRow(2,
                new TextCell(1, "UPDATED")
            )
        )
    ).saveTo("Updated.xlsx");
```

## Custom styles
You can create custom cells/rows/sheets:
```java
new XsWorkbook(
    new XsSheet(
        new MyCustomRow("Boris", "Miksic", "ID:2450"),
        new MyCustomRow("Mirko", "Mirkic", "ID:1690")
    )
).saveTo("Test.xlsx");

```
Just extend appropriate template class and pass custom row/cell object to its constructor:
```java
private static class MyCustomRow extends RowTemplate {

    public MyCustomRow(final String name, final String surname, final String id) {
        super(
            new XsRow()
                .with(new TextCells(name, surname))
                .with(new MyGreyCell(new TextCell(id)))
        );
    }
}

private static class MyGreyCell extends CellTemplate {

    public MyGreyCell(final ECell cell) {
        super(cell.with(
            new XsStyle(
                new ForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()),
                new FillPattern(FillPatternType.SOLID_FOREGROUND)
            )
        ));
    }
}
```

The result:  
<img src="https://i.imgur.com/9BzW6VR.png"/>

## Contribution
You can contribute by forking the repo and sending a pull request.
Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```

