/**
 * ページスクロール処理
 */
$(function() {
    var pageTop = $("#page-top");
    pageTop.click(function () {
        $('body, html').animate({ scrollTop: 0 }, 500);
        return false;
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() >= 200) {
            pageTop.css( "bottom", "10px" );
        } else {
            pageTop.css( "bottom", "-70px" );
        }
    });
});

/**
 * 貸出履歴画面から貸出処理を行う
 */
// モーダルに書籍詳細を表示する
$(document).on('click', '.book-lend', function() {
	var id = $(this).parents('.book').find('#book-id').val();
	var title = $(this).parents('.book').find('#book-title').text();
	var img = $(this).parents('.book').find('#book-img').attr('src');
	var author = $(this).parents('.book').find('#book-author').text();
	var name = $(this).parents('.book').find('#book-name').text();
	var publisher = $(this).parents('.book').find('#book-publisher').text();
	var detail = $(this).parents('.book').find('#book-detail').text();
	$('#modal-book-id').val(id);
	$('#modal-book-title').text(title);
	$('#modal-book-img').attr('src', img);
	$('#modal-book-author').text(author);
	$('#modal-book-name').text(name);
	$('#modal-book-publisher').text(publisher);
	$('#modal-book-detail').html(detail.replace(/\r?\n/g, '<br>'));
	$('#lendModal').modal();
});

// 履歴からの書籍貸出処理(Ajax)
$(document).on('click', '#modal-lend-button', function(e) {
	e.preventDefault();
	var bookNameId = $(this).parents('.modal-content').find('#modal-book-id').val();
	
	var sendData = {
		bookNameId : bookNameId,
	};
	$.ajax({
		url: '/ajax/complete',
		headers: {
			'X-CSRF-TOKEN': $("*[name=_csrf]").val(),
			'Content-Type': 'application/json'
		},
		type: 'POST',
		data: JSON.stringify(sendData),
		dataType: 'JSON',
	})
	.then(
		// 通信成功時のコールバック
		function(data, status) {
			if (status == 'success') {
				if (data.complete != 'false') {
					$('#lend-alert').html('<div class="alert alert-primary alert-dismissible fade show" role="alert">' + data.message + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
					$('#lendModal').modal('hide');
				} else {
					$('#lend-alert').html('<div class="alert alert-warning alert-dismissible fade show" role="alert">' + data.message + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
				}
			}
		},
		// 通信失敗時のコールバック
		function(data) {
			if (data['status'] == 'error') {
				var alert = 'エラー：セッションタイムアウト';
			} else {
				var alert = 'エラー：データの取得に失敗しました';
			} 
			$('#lend-alert').html('<div class="alert alert-warning alert-dismissible fade show" role="alert">' + alert + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
		}
	);
	return false;
})

/**
 * トップページのニュースティッカー表示
 */
$(function() {
	var path = location.pathname
	if (path == "/") { // トップページのみ処理が動くようにする
		const animTime = 5000; // アニメーション時間(ms)
		const speed = 1; // テキストの移動速度(px)
		const limit = 0; // ブレークポイント(px)
		let isRunning = false;
	
		const ticker = document.querySelector('.ticker');
		loadTicker();
	
		function loadTicker() {
			tickerAnim();
			animId = setInterval(tickerAnim, animTime);
			isRunning = true;
		}
	
		// アニメーション処理
		function tickerAnim() {
			const items = ticker.querySelectorAll('.ticker-item');
			const running = ticker.querySelector('.run');
			let idx, link, first, next;
			if (!running) { // 実行中の要素がない場合（初回のみ）
				first = items[0];
				link = first.querySelector('a');
				first.classList.add('fadeInDown', 'run');
				first.style.zIndex = 1;
				setTimeout(textMove, 1000, link); // 第3引数に引数linkを指定
			} else {
				for (let i = 0; i < items.length; i++) {
					if (items[i] == running) {
						idx = i; // 実行中要素のインデックスを取得
						break;
					}
				}
				next = items[(idx + 1) % items.length];
				running.classList.replace('fadeInDown', 'fadeOutDown');
				setTimeout(() => {
					running.classList.remove('fadeOutDown', 'run');
					running.style.zIndex = 0;
					link = running.querySelector('a');
					link.style.transform = 'none';
					next.classList.add('fadeInDown', 'run');
					next.style.zIndex = 1;
					link = next.querySelector('a');
					setTimeout(textMove, 1000, link);
				}, 300);
			}
		}
	
		// テキスト移動処理
		function textMove(elm) {
			const move = elm.parentNode.clientWidth - elm.clientWidth;
			if (move < 0) {
				elm.style.transform = 'translateX(' + move + 'px)';
				elm.style.transitionDuration = Math.abs(move) / speed + 's';
			}
		}
	}
});

//チェックボックスの処理
$(function () {
  const checkbox = document.getElementById("lend-ok-checkbox");
  if (!checkbox) return; // 要素がないページでは何もしない

  const results = Array.from(document.querySelectorAll(".search-result"));
  const countSpan = document.getElementById("result-count");

  function updateCount() {
    const visible = results.filter(item => item.style.display !== "none");
    if (countSpan) {
      countSpan.textContent = visible.length + "件";
    }
  }

  checkbox.addEventListener("change", function () {
    const showOnlyOk = this.checked;
    results.forEach(item => {
      const isOk = item.getAttribute("data-lendok") === "true";
      const sameBook = item.getAttribute("data-samebook") === "true";
      item.style.display = (showOnlyOk && (!isOk || sameBook)) ? "none" : "";
    });
    updateCount();
  });

  updateCount();
});
