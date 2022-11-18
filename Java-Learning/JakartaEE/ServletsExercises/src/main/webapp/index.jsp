<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Tests Servlets</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
 <div id="wrapper_main">
  <h3>Compute your age</h3>
  <form action="/TestsServlets/AgeServlet" method="post" id="ageForm"
   role="form" aria-label="formAge">
   <div>
    <div>
     <input type="text" id="birthdate" name="birthdate"
      placeholder="type your birthdate" />
    </div>
    <br> <br>
    <div>
     <button type="submit">
      <span>Send</span>
     </button>
    </div>
   </div>
  </form>
 </div>
</body>
</html>
