window.addEventListener("load", function() {

	var section = document.querySelector("#stockScroll");
	var tbody = section.querySelector("table tbody");
	var pager = section.querySelector(".pager");

	var load = function(page) {

		var request = new XMLHttpRequest();
		request.open("GET", "../../card/board/stock_board_list?p="
				+ page);

		request.onload = function() {
			var list = JSON.parse(request.responseText);
			var trTemplate = section.querySelector(".tr-template");
			

			tbody.innerHTML = "";

			for (var i = 0; i < list.length; i++) {
				var cloneTr = document.importNode(trTemplate.content,
						true);

				var tds = cloneTr.querySelectorAll("td");
				var title = "[" + list[i].stockName + "] "
						+ list[i].title + "(" + list[i].replyCnt + ")";

				tds[0].innerText = list[i].id;
				var aTagDetail = tds[1].firstElementChild;
				aTagDetail.dataset.id = list[i].id;
				aTagDetail.innerText = title;
				tds[2].innerText = list[i].regdate;
				tds[3].innerText = list[i].hit;
				var aTagFavo = tds[4].firstElementChild;
				aTagFavo.dataset.id = list[i].id;
				var aTagDel = tds[5].firstElementChild;
				aTagDel.dataset.id = list[i].id;
				tds[6].innerText = list[i].writerId;
				tbody.append(cloneTr);
			}
			;
		};
		request.send();
	}

	load(1)
	
	pager.onclick = function(e) {
		if (e.target.nodeName != "A")
			return;

		e.preventDefault();
		load(e.target.innerText);
	};

	
	var titleClickHandler = function(e) {
		var currentTr = e.target.parentNode.parentNode;
		var nextTr = currentTr.nextElementSibling.nextElementSibling;

		
		//이미 내용이 있으면 닫아주세요.
		if (nextTr.classList.contains("content-row")) {

			  var row = nextTr.nextElementSibling;
			  row.parentNode.removeChild(row);
			  nextTr.parentNode.removeChild(nextTr);
			  return;
		}

		if (e.target.parentNode.lastChild.nodeName == "IMG") {
			alert("로딩중입니다~");
			return;
		}
		var id = e.target.dataset.id;

		var ajaxIcon = document.createElement("img");
		ajaxIcon.src = "../../images/delay-icon.gif";
		e.target.parentNode.append(ajaxIcon);

		var request = new XMLHttpRequest();
		request.open("GET", "../../card/board/detail?id=" + id, true);
		request.onload = function() {
			var detail = JSON.parse(request.responseText);
			var template = section.querySelector(".detail-template");
			var cloneTr = document.importNode(template.content, true);
			var td = cloneTr.querySelector(".content-row td");
			td.innerHTML = detail.board.content;
			var replyContent = cloneTr
					.querySelector(".replyTable tbody tr td");
			var contentSum = "";
			var aTagDetail = cloneTr.querySelector(".reg-reply-button");
			aTagDetail.dataset.id = id;
			for (var i = 0; i < detail.replys.length; i++) {
				contentSum += '<div><span class="re-content">' +detail.replys[i].writerId + 
				" : </span><span>" + detail.replys[i].reContent+"</span></div>";
			}
			replyContent.innerHTML = contentSum;
			tbody.insertBefore(cloneTr, nextTr);

			ajaxIcon.remove();
			ajaxIcon = undefined;
		};
		request.send();
	};
	
	//========= 댓글쓰기 ==================
	var regButtonClickHandler = function(e){
		
		// 1. 입력한 값을 얻어온다.
		
		var boardId = e.target.dataset.id;
		var reContent = e.target.parentNode.parentNode.querySelector('.reply-content').value;
		reContent = encodeURI(reContent);
		
		var data = [
			["boardId", boardId],
			["reContent", reContent],
			]
		var sendData = [];
		
		for (var i = 0; i < data.length; i++) {
			sendData[i] = data[i].join("=");
			console.log(sendData.join("&"));
		}
		sendData = sendData.join("&");
		
		// 2. 값을 서버에 보낸다.
		
		var request = new XMLHttpRequest(); 
		request.open("POST", "../../card/board/Reply", true); 
		request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		request.send(sendData);
		
		// 3. 요청이 완료되었는지 결과를 확인한다.
		
		alert("등록되었습니다.");
		
		// 4. 결과를 확인하고 결과를 표시한다.

		
        

	};
	
	//========= 내용 클릭 핸들러 ==================	
	tbody.onclick = function(e) {
		e.preventDefault();
				
		if(e.target.parentNode.classList.contains("board-title"))
			titleClickHandler(e);		
		else if(e.target.parentNode.classList.contains("reply-submit-button"))
			regButtonClickHandler(e);	
		

	};

})


