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