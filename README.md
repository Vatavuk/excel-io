This is an object-oriented java library for reading and writing Microsoft Office Excel spreadsheets.
It is a wrapper around Apache POI that provides elegant and user friendly interface for creating Excel documents. 

## Usage
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
    )
).saveTo("Test.xlsx");
```
This is how the result looks like:
<img src="https://i.imgur.com/3hUJkJ2.png" width="1122" height="242"/>

## Custom styles
You can create custom cells/rows/sheets by extending appropriate template class and implement its constructor.
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
Then use it like this:
```java
new XsWorkbook(
    new XsSheet(
        new MyCustomRow("Boris", "Miksic", "ID:2450"),
        new MyCustomRow("Mirko", "Mirkic", "ID:1690")
    )
).saveTo("Test.xlsx");
```

The result looks like:  
<img src="https://i.imgur.com/9BzW6VR.png" width="770" height="212"/>

## Note
Please bare in mind that this is a poc version with 90% of features missing
(mainly styles and properties). I will be regularly committing more and more functionality.
