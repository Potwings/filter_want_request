<html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body>

<form action="/login" method="post">
    <input name="name" value="form name">
    <button type="submit" > 확인 </button>
</form>

<button class="jsBtn">json 전송</button>
<button class="js2Btn">json2 전송</button>
<button class="dataBtn">data 전송</button>
<button class="data2Btn">data2 전송</button>

<script>
document.querySelector(".jsBtn").addEventListener("click", function(){
    fetch("/jslogin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: "js Name"
        })
    })
});document.querySelector(".js2Btn").addEventListener("click", function(){
    fetch("/requestBody", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: "js Name"
        })
    })
});

document.querySelector(".dataBtn").addEventListener("click", function(){
    fetch("/datalogin", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "name=body Name"
    })
});

document.querySelector(".data2Btn").addEventListener("click", function(){
  fetch("/dataObjectlogin", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: "name=objectName"
  })
});


</script>
</body>
</html>
