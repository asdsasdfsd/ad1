function searchByNickname() {
    // 获取输入框的值
    var nickname = document.getElementById("nickname").value;
    window.location.href = '/comments/management?userName=' + nickname;
}

function resetSearch() {
    // 将输入框的值置为空字符串
    document.getElementById("nickname").value = "";
}

function deleteWindow(event) {
	event.preventDefault(); // 阻止表单默认提交
	document.getElementById('deleteWindow').style.display = "block";
	document.getElementById('overlay').style.display = "block";
}

function confirm() {
	document.getElementById('deleteWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function cancel() {
	document.getElementById('deleteWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function banWindow(event, username) {
	event.preventDefault(); // 阻止表单默认提交
    document.getElementById('banUsername').innerText = username;  // 设置封禁弹窗中的用户名
	document.getElementById('banWindow').style.display = "block";
	document.getElementById('overlay').style.display = "block";
}

function confirm2() {
	document.getElementById('banWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function cancel2() {
	document.getElementById('banWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}