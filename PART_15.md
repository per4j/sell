
####1. pom.xml添加poi和commons-fileupload

```
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>3.14</version>
</dependency>

<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
</dependency>

<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
</dependency>
```

####2. 上传excel service

```
@Override
public boolean batchImport(String fileName, MultipartFile file) throws IOException {

    boolean notNull = false;

    if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
        throw new ExcelException("上传文件格式不正确");
    }

    boolean isExcel2003 = true;
    if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
        isExcel2003 = false;
    }

    InputStream inputStream = file.getInputStream();
    Workbook wb = null;
    if (isExcel2003) {
        wb = new HSSFWorkbook(inputStream);
    } else {
        wb = new XSSFWorkbook(inputStream);
    }

    Sheet sheet = wb.getSheetAt(0);
    if (sheet != null) {
        notNull = true;
    }

    ArrayList<WxProduct> wxProductArrayList = new ArrayList<>();

    for (int i = 1; i < sheet.getLastRowNum(); i++) {
        Row cells = sheet.getRow(i);
        if (cells == null) {
            continue;
        }

        WxProduct wxProduct = new WxProduct();

        
        // ...

        wxProductArrayList.add(wxProduct);

        repository.saveAll(wxProductArrayList);
    }
    return notNull;
}
```

####3. upload_excel.ftl

```
<!DOCTYPE html>
<html>
<head>
    <title>文件上传示例</title>
</head>
<body>
<h2>文件上传示例</h2>
<hr/>
<form method="POST" enctype="multipart/form-data" action="/sell/wx/import">
    <p>
        文件：<input type="file" name="file" />
    </p>
    <p>
        <input type="submit" value="上传" />
    </p>
</form>
</body>
</html>
```