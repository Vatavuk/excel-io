# Vgv-xls

[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/cactoos)](http://www.rultor.com/p/yegor256/cactoos)

[![codecov](https://codecov.io/gh/Vatavuk/vgv-xls/branch/master/graph/badge.svg)](https://codecov.io/gh/Vatavuk/vgv-xls)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/Vatavuk/vgv-xls/LICENSE.txt)


This is an object-oriented java library for reading and writing Microsoft Office Excel spreadsheets.
It is a wrapper around Apache POI that provides elegant and user friendly interface for creating Excel documents. 

**Note**.
This is an early version of the product with a lot of features missing (mainly styles and properties).
I will be regularly committing more and more functionality.


**How to use**.
Latest version [here](https://github.com/Vatavuk/vgv-xls/releases)

```xml
<dependency>
    <groupId>hr.com.vgv</groupId>
    <artifactId>vgv-xls</artifactId>
    <version>0.2</version>
</dependency>
```

Java version required: 1.8+.


## Example
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
            .with(new XsProps<>(new Height((short) 20)))
    )
).saveTo("Test.xlsx");
```
This is how the result looks like:
<img src="https://i.imgur.com/3hUJkJ2.png"/>

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

