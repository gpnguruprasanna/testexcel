<!DOCTYPE html>
<html lang="en">
<head>
  <title>Excel sheet read</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Extract excel and dump to database</h2>
  <form action="/extractExcel/fileupload">
    <div class="form-group">
      <label for="pwd">Excel file:</label>
      <input type="file" class="form-control" id="pwd" placeholder="upload file" name="file">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>
</body>
</html>
