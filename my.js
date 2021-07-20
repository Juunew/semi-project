$(document).ready(function(){
	const login = $.cookie('logined');
	$("#welcomeMsg").html(login);
	/**
	회원가입
	 */
	$("#insertBtn").click(function(){
		const name = $("#name").val();
		const id = $("#id").val();
		const pw = $("#pw").val();
		
		alert(name + " : " + id + " : " + pw);
		$.post("../insertUser.semi",
			{
				name,
				id,
				pw
			},
			function(data, status) {
				alert(data);
				window.close();
			}
		);
	});	// end insertBtn
	
	/**
	로그인
	 */
	$("#loginBtn").click(function(){
		const id = $("#id").val();
		const pw = $("#pw").val();
		
		$.post("login.semi",
			{
				id,
				pw
			},
			function(data, status) {
				const obj = JSON.parse(data);
				if(obj.name) {
					data = obj.name + "<input type='button' value='logout' id='logoutBtn' class='btn btn-primary'><input type='button' value='탈퇴' id='deleteBtn' class='btn btn-danger'>";
					$.cookie("logined", data)
					$("#welcomeMsg").html(data);
				} else {
					alert(obj.msg);
					location.reload();
				}
			}
		);
	});	// end loginBtn
	
	/**
	로그아웃
	 */
	$(document).on("click", "#logoutBtn", function(event) {
		$.post("logout.semi",
			{
				
			},
			function(data, status) {
				$.removeCookie("logined");
				location.reload();
			}
		);
	});	// end logout
	
	/**
	회원탈퇴
	 */
	$(document).on("click", "#deleteBtn", function(event){
		alert("탈퇴를 완료하시겠습니까?")
		$.post("deleteUser.semi",
			{
				
			},
			function(data, status) {
				$.removeCookie("logined");
				location.reload();
			}
		)
	});
});	// end document